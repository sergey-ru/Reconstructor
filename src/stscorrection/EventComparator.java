/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stscorrection;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.lang3.SystemUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author sergei
 */
public class EventComparator implements Callable<ActionsPair> {

    private static ActionSignature[] _actions;
    private SystemEvent[] _events;
    private final HashSet<Integer> _seenActionsHash;
    private final LinkedList<Integer> _seenActionsList;
    private final int _start;
    private final int _end;
    private final int _maxLenght;
    private final double _coefficient;
    private final boolean _isDiferential;
    private final boolean _multiply;

    //loadKnownActionsFromFolder
    public static int init(String URL, OperationType splitByStart, OperationType splitByEnd) {
        try {
            File fXmlFile = new File(URL);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            Element e = (Element) doc.getElementsByTagName("actions").item(0);

            int ans = LoadActions(e.getAttribute("path"), splitByStart, splitByEnd);

            LoadActionIDs(doc);

            LoadActionPredecessors(doc);

            return ans;

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(EventComparator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    private static void LoadActionPredecessors(Document doc) throws NumberFormatException {
        Element e;
        NodeList nodes = doc.getElementsByTagName("step");
        int action_id;
        int previous_action_id;
        double coefficient;
        for (int i = 0; i < nodes.getLength(); i++) {
            e = (Element) nodes.item(i);
            action_id = Integer.parseInt(e.getAttribute("action_id"));
            previous_action_id = Integer.parseInt(e.getAttribute("previous_action_id"));
            coefficient = Double.parseDouble(e.getAttribute("coefficient"));
            for (ActionSignature _action : _actions) {
                if (_action.getActionId() == action_id) {
                    _action.getPrevious_actions().add(new PreviousAction(previous_action_id, coefficient));
                }
            }
        }
    }

    private static void LoadActionIDs(Document doc) throws NumberFormatException {
        Element e;
        NodeList nodes = doc.getElementsByTagName("action");
        String nodeName;
        String nodePayload;
        int nodeId;
        for (int i = 0; i < nodes.getLength(); i++) {
            e = (Element) nodes.item(i);
            nodeName = e.getAttribute("name");
            nodePayload = e.getAttribute("payload");
            nodeId = Integer.parseInt(e.getAttribute("id"));
            for (ActionSignature _action : _actions) {
                if (_action.getName().equals(nodeName)) {
                    _action.setActionId(nodeId);
                    _action.setPayload(nodePayload);
                }
            }
        }
    }

    private static int LoadActions(String URL, OperationType splitByStart, OperationType splitByEnd) {
        File folder = new File(URL);
        File[] listOfFiles = folder.listFiles();
        List<ActionSignature> list = new ArrayList<>();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                String path;
                if (SystemUtils.IS_OS_LINUX) {
                    path = URL + "/" + file.getName();
                } else {
                    path = URL + "\\" + file.getName();
                }
                String name = file.getName();
                list.add(new ActionSignature(SystemEvent.readCSV(path,1), trimName(name)));
            }
        }
        _actions = new ActionSignature[list.size()];
        list.toArray(_actions);
        int ans = 0, start = 0, end = 0;
        for (ActionSignature systemAction : _actions) {
            for (SystemEvent event : systemAction.getEvents()) {
                if (event._Operation == splitByStart._ID) {
                    start++;
                }
                if (event._Operation == splitByEnd._ID) {
                    end++;
                }
            }

            start = Math.min(start, end);

            if (ans < start) {
                ans = start;
            }

            start = 0;
            end = 0;
        }
        return ans;
    }

    public static void compareAllKnownActions() {

        double[][] ans = new double[_actions.length][_actions.length];
        System.out.println("**************************************");
        System.out.print(" ,");
        for (ActionSignature action : _actions) {
            System.out.print(action.getName() + ",");
        }
        System.out.println();
        for (int i = 0; i < _actions.length; i++) {
            System.out.print(_actions[i].getName() + ",");
            for (int j = 0; j < _actions.length; j++) {
                ans[i][j] = calcScore(_actions[i].getEvents(), _actions[j].getEvents());
                System.out.print(ans[i][j] + ",");
            }
            System.out.println();
        }
    }

    private static double calcScore(SystemEvent[] i, SystemEvent[] j) {
        return 1 - algX(i, j) / Math.max(i.length, j.length);
    }

    //Levenshtein Distance
    private static double algX(SystemEvent[] A, SystemEvent[] B) {
        int[][] D = new int[A.length + 1][B.length + 1];

        for (int i = 1; i <= A.length; i++) {
            D[i][0] = D[i - 1][0] + cost(A[i - 1], null);
        }
        for (int j = 1; j <= B.length; j++) {
            D[0][j] = D[0][j - 1] + cost(null, B[j - 1]);
        }

        int m1, m2, m3;
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                m1 = D[i - 1][j - 1] + cost(A[i - 1]._Operation, B[j - 1]._Operation);
                m2 = D[i - 1][j] + cost(A[i - 1]._Operation, -1);
                m3 = D[i][j - 1] + cost(-1, B[j - 1]._Operation);
                D[i][j] = Math.min(m1, Math.min(m2, m3));
            }
        }
        return D[A.length][B.length];
    }

    private static int cost(int a, int b) {
        if (a == b) {
            return 0;
        }
        return 1;
    }

    private static int cost(SystemEvent a, SystemEvent b) {
        if (a == null && b == null) {
            return 0;
        } else if (a != null && b != null) {
            return cost(a._Operation, b._Operation);
        }
        return 1;
    }

    private static String trimName(String name) {
        name = name.replace("DM_", "").replace('_', ' ');
        return name.substring(0, name.lastIndexOf(' '));
    }

    public EventComparator(SystemEvent[] events, HashSet<Integer> seenActions, LinkedList<Integer> seenActionsList, int start, int end, double coefficient, int maxLenght, boolean isDiferential, boolean multiply) {
        this._events = events;
        this._start = start;
        this._end = end;
        this._seenActionsHash = seenActions;
        this._seenActionsList = seenActionsList;
        this._coefficient = coefficient;
        this._multiply = multiply;
        this._maxLenght = maxLenght;
        this._isDiferential = isDiferential;
    }

    @Override
    public ActionsPair call() throws Exception {
        TreeMap map = new TreeMap();

        for (ActionSignature systemAction : _actions) {
            double score = calcScore(_events, systemAction.getEvents());

            for (PreviousAction _prevAction : systemAction.getPrevious_actions()) {
                if (_maxLenght < 0)//No max Length 
                {
                    //no difrential 
                    if (!_isDiferential) {
                        if (_seenActionsHash.contains(_prevAction.getPrevious_action_id())) {
                            if (this._coefficient < 0) {
                                score = score * _prevAction.getCoefficient();
                            } else {
                                score = score * this._coefficient;
                            }
                            if (!_multiply) {
                                break;
                            }
                        }
                    } else {
                        //difrential 
                        if (_seenActionsHash.contains(_prevAction.getPrevious_action_id())) {

                            double pos = 1;

                            for (int i = 0; i < _seenActionsList.size(); i++) {
                                if (_seenActionsList.get(i) == _prevAction.getPrevious_action_id()) {
                                    pos = (i + 1) / _seenActionsList.size();
                                    break;
                                }
                            }

                            if (this._coefficient < 0) {
                                
                                //pos = 1 + pos * (_prevAction.getCoefficient() -1 );
                                
                                score = pos * score * _prevAction.getCoefficient();
                            } else {
                                
                                //pos = 1 + pos * (this._coefficient -1 );
                                
                                score = pos * score * this._coefficient;
                            }
                            if (!_multiply) {
                                break;
                            }
                        }
                    }

                } else // Max Length 
                {
                    //no difrential 
                    if (!_isDiferential) {
                        if (_seenActionsHash.contains(_prevAction.getPrevious_action_id())) {

                            double pos = Double.NEGATIVE_INFINITY;

                            for (int i = 0; i < _seenActionsList.size(); i++) {
                                if (_seenActionsList.get(i) == _prevAction.getPrevious_action_id()) {
                                    pos = (i + 1);
                                    break;
                                }
                            }
                            if (_seenActionsList.size() - pos <= _maxLenght) {
                                if (this._coefficient < 0) {
                                    score = score * _prevAction.getCoefficient();
                                } else {
                                    score = score * this._coefficient;
                                }
                                if (!_multiply) {
                                    break;
                                }
                            }
                        }
                    } else {
                        //difrential 
                        if (_seenActionsHash.contains(_prevAction.getPrevious_action_id())) {

                            double pos = Double.NEGATIVE_INFINITY;

                            for (int i = 0; i < _seenActionsList.size(); i++) {
                                if (_seenActionsList.get(i) == _prevAction.getPrevious_action_id()) {
                                    pos = (i + 1);
                                    break;
                                }
                            }
                            if (_seenActionsList.size() - pos <= _maxLenght) {

                                pos = pos / _seenActionsList.size();
                                
                                if (this._coefficient < 0) {
                                    
                                    //pos = 1 + pos * (_prevAction.getCoefficient() -1 );
                                    
                                    score = pos * score * _prevAction.getCoefficient();
                                } else {
                                    
                                    //pos = 1 + pos * (this._coefficient -1 );
                                    
                                    score = pos * score * this._coefficient;
                                }
                                if (!_multiply) {
                                    break;
                                }
                            }
                        }
                    }
                }

            }

            map.put(score, systemAction);
        }
        _events = null;
        System.gc();
        return new ActionsPair((double) map.lastEntry().getKey(), (ActionSignature) map.lastEntry().getValue(), _start, _end);
    }

}
