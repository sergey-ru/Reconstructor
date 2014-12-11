/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stscorrection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.ArrayUtils;
import stscorrection.Evaluation.LogSergei;
import stscorrection.Evaluation.LogSlava;

/**
 *
 * @author sergei
 */
public class StSCorrection {

    private static final OperationType splitByStart = OperationType.TCPReceive;
    private static final OperationType splitByEnd = OperationType.ThreadExit;
    private static int maxOverLap = 4;
    private static final int maxSignature = 50000;

    public static void main(String[] args) {
        
//        try
//            {
//                for (double nameIndex = 0.95; nameIndex > 0.45; nameIndex=nameIndex-0.05) {
//                    nameIndex =round(nameIndex, 2);
//                    
//                    String name ="/home/sergei/Dropbox/~Modeling and Simulation of Advanced Persistent Threat/DarkCommet/Logs/Logfile_Slava_"+nameIndex+".CSV";
//                
//                
//                    BufferedReader br = new BufferedReader(new FileReader("/home/sergei/Dropbox/~Modeling and Simulation of Advanced Persistent Threat/DarkCommet/Logs/Logfile_Slava.CSV"));
//                    BufferedWriter bw = new BufferedWriter(new FileWriter(new File(name), true));
//
//                    bw.write(br.readLine());
//                    bw.newLine();
//
//                    String line = br.readLine();
//
//                    while(line!=null)
//                    {
//                        if(Math.random()< nameIndex){
//                            bw.write(line);
//                            bw.newLine();
//                        }
//
//                        line = br.readLine();
//                    }
//                
//                }
//            }
//            catch(Exception e)
//            {
//            System.out.println("Exception caught: "+e.getMessage());
//            }
        
        
        
        
        printStart();

        Date d = new Date();

        if (args.length == 0) {

            Run_type_02();

        } else {
            Parallel_Main_Method(args);
        }

        System.out.println("The end :)   " + (new Date().getTime() - d.getTime()) + "ms");
    }

    private static void Run_type_01() {
        //System.out.println("No Multiply - All length");
        System.out.println("Coefficient"
                + "\t\t"
                + "Multiply"
                + "\t\t"
                + "maxLenght"
                + "\t\t"
                + "Diferential"
                + "\t\t"
                + "Distance");

        System.out.println("maxLenght=all, isDiferential=false, multiply=false");

        String name = "/home/sergei/Dropbox/~Modeling and Simulation of Advanced Persistent Threat/DarkCommet/Logs/Logfile_Sergei.CSV";
        
        for (double i = 0; i < 2; i = i + 0.1) {
            Parallel_Test_Method(i, -1, false, false, false,name);
        }
        for (double i = 2; i < 11; i = i + 1) {
            Parallel_Test_Method(i, -1, false, false, false,name);
        }

        System.out.println("maxLenght=all, isDiferential=false, multiply=true");

        for (double i = 0; i < 2; i = i + 0.1) {
            Parallel_Test_Method(i, -1, false, true, false,name);
        }
        for (double i = 2; i < 11; i = i + 1) {
            Parallel_Test_Method(i, -1, false, true, false,name);
        }

        System.out.println("maxLenght=all, isDiferential=true, multiply=false");

        for (double i = 0; i < 2; i = i + 0.1) {
            Parallel_Test_Method(i, -1, true, false, false,name);
        }
        for (double i = 2; i < 11; i = i + 1) {
            Parallel_Test_Method(i, -1, true, false, false,name);
        }

        System.out.println("maxLenght=all, isDiferential=true, multiply=true");

        for (double i = 0; i < 2; i = i + 0.1) {
            Parallel_Test_Method(i, -1, true, true, false,name);
        }
        for (double i = 2; i < 11; i = i + 1) {
            Parallel_Test_Method(i, -1, true, true, false,name);
        }

        System.out.println("maxLenght=5, isDiferential=false, multiply=false");

        for (double i = 0; i < 2; i = i + 0.1) {
            Parallel_Test_Method(i, 5, false, false, false,name);
        }
        for (double i = 2; i < 11; i = i + 1) {
            Parallel_Test_Method(i, 5, false, false, false,name);
        }

        System.out.println("maxLenght=5, isDiferential=false, multiply=true");

        for (double i = 0; i < 2; i = i + 0.1) {
            Parallel_Test_Method(i, 5, false, true, false,name);
        }
        for (double i = 2; i < 11; i = i + 1) {
            Parallel_Test_Method(i, 5, false, true, false,name);
        }

        System.out.println("maxLenght=5, isDiferential=true, multiply=false");

        for (double i = 0; i < 2; i = i + 0.1) {
            Parallel_Test_Method(i, 5, true, false, false,name);
        }
        for (double i = 2; i < 11; i = i + 1) {
            Parallel_Test_Method(i, 5, true, false, false,name);
        }

        System.out.println("maxLenght=5, isDiferential=true, multiply=true");

        for (double i = 0; i < 2; i = i + 0.1) {
            Parallel_Test_Method(i, 5, true, true, false,name);
        }
        for (double i = 2; i < 11; i = i + 1) {
            Parallel_Test_Method(i, 5, true, true, false,name);
        }
    }

    private static void Run_type_02() {
        //System.out.println("No Multiply - All length");
        System.out.println("Coefficient"
                + "\t\t"
                + "Multiply"
                + "\t\t"
                + "maxLenght"
                + "\t\t"
                + "Diferential"
                + "\t\t"
                + "Distance");

            
            
                System.out.println("maxLenght=0, isDiferential=true, multiply=false");

                String name ="/home/sergei/Dropbox/~Modeling and Simulation of Advanced Persistent Threat/DarkCommet/Logs/Logfile_Slava.CSV";
                System.out.println(name);
                for (double i = 0; i < 2.1; i = i + 0.1) {
                    Parallel_Test_Method(i,0 , true, false, false,name);
                }
                
                
                for (double nameIndex = 0.95; nameIndex > 0.45; nameIndex=nameIndex-0.05) {
                    nameIndex =round(nameIndex, 2);
                    
                    name ="/home/sergei/Dropbox/~Modeling and Simulation of Advanced Persistent Threat/DarkCommet/Logs/Logfile_Slava_"+nameIndex+".CSV";
                    System.out.println(name);
                    for (double i = 0; i < 2.1; i = i + 0.1) {
                        Parallel_Test_Method(i,0 , true, false, false,name);
                    }
                }
    }

    private static void Parallel_Test_Method(double coefficient, int maxLenght, boolean isDiferential, boolean multiply, boolean fullPrint,String logURI) {

        OperationType.init();

        maxOverLap = EventComparator_NF.init("/home/sergei/Dropbox/~Modeling and Simulation of Advanced Persistent Threat/DarkCommet/Expiriment/taxonomy", splitByStart, splitByEnd);

        maxOverLap = 1;

        if (fullPrint) {

            System.out.println("Checking Log file");
            System.out.println("maxOverLap=" + maxOverLap);
            System.out.println("maxSignature=" + maxSignature);
            System.out.println();
        }

        //SystemEvent[][] splitedArray = LogReader.splitEventArray(LogReader.readLogFile("/home/sergei/Dropbox/~Modeling and Simulation of Advanced Persistent Threat/DarkCommet/Logfile_042.CSV"), splitByStart, splitByEnd);
        SystemEvent[][] splitedArray = LogReader.splitEventArray(LogReader.readLogFile(logURI), splitByStart, splitByEnd);
        HashSet<Integer> foundActions = new HashSet();
        LinkedList<Integer> seenActionsList = new LinkedList<>();
        ArrayList<ActionsPair> ansList = new ArrayList<>();

        for (int i = 0; i < splitedArray.length; i++) {
            ActionsPair ap_old = null;
            SystemEvent[] toCompare = new SystemEvent[0];
            try {
                ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
                //ExecutorService pool = Executors.newFixedThreadPool(1);

                List<Future<ActionsPair>> actions = new ArrayList<>();
                for (int j = i; j < splitedArray.length && j < i + maxOverLap; j++) {

                    toCompare = ArrayUtils.addAll(toCompare, splitedArray[j]);
                    if (toCompare.length > maxSignature) {
                        break;
                    }
                    Future<ActionsPair> future = pool.submit(new EventComparator_NF(toCompare, foundActions, seenActionsList, i, j, coefficient, maxLenght, isDiferential, multiply));
                    actions.add(future);
                    System.gc();
                }
                pool.shutdown();
                for (Future<ActionsPair> actionsPair : actions) {
                    if ((actionsPair.get().getKey() > 0.5) && (ap_old == null || ap_old.getKey() < actionsPair.get().getKey())) {
                        ap_old = actionsPair.get();
                    }
                }
                pool = null;
                actions = null;
                System.gc();
            } catch (Exception ex) {
                Logger.getLogger(StSCorrection.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (ap_old != null) {
                if (fullPrint) {
                    System.out.println(round(ap_old.getKey(), 3) + "\t" + ap_old.getStart() + "\t" + ap_old.getEnd() + "\t" + ap_old.getAction().getName());
                }
                i = ap_old.getEnd();
                foundActions.add(ap_old.getAction().getActionId());
                seenActionsList.add(ap_old.getAction().getActionId());
                ansList.add(ap_old);
            }
        }

        System.out.println(round(coefficient, 2)
                + "\t\t"
                + multiply
                + "\t\t"
                + maxLenght
                + "\t\t"
                + isDiferential
                + "\t\t"
                + round(LogSlava.evaluateLog(ansList), 4));
    }

    private static void Parallel_Main_Method(String[] args) {
//        OperationType.init();
//        maxOverLap = EventComparator.init(args[3], splitByStart, splitByEnd);
//        
//        if(Integer.parseInt(args[1]) > 0)
//        {
//            maxOverLap = Integer.parseInt(args[1]);
//        }
//        
//        if(Integer.parseInt(args[2]) > 0)
//        {
//            maxSignature = Integer.parseInt(args[2]);
//        }
//
//        if (args[0].contains("0")) {
//            System.out.println("Compare All Known Actions");
//            EventComparator.compareAllKnownActions();
//        } else if (args[0].contains("1")) {
//
//            System.out.println("Checking Log file");
//            System.out.println("maxOverLap="+maxOverLap);
//            System.out.println("maxSignature="+maxSignature);
//            System.out.println();
//            StringBuilder sb = new StringBuilder();
//            SystemEvent[][] splitedArray = LogReader.splitEventArray(LogReader.readLogFile(args[4]), splitByStart, splitByEnd);
//            HashSet<Integer> foundActions = new HashSet();
//            
//            
//            for (int i = 0; i < splitedArray.length; i++) {
//                
//                SystemEvent[] toCompare = new SystemEvent[0];
//                ActionsPair ap_old = null;
//                
//                System.out.println((i+1)+" from "+splitedArray.length);
//                
//                try {
//                    ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//                    List<Future<ActionsPair>> actions = new ArrayList<>();
//
//                    for (int j = i; j < splitedArray.length && j < i+maxOverLap; j++) {
//                        toCompare = ArrayUtils.addAll(toCompare, splitedArray[j]);
//                        
//                        if(toCompare.length>maxSignature){
//                            break;
//                        }
//                        
//                        Future<ActionsPair> future = pool.submit(new EventComparator(toCompare, foundActions, i, j,-1,true));
//
//                        actions.add(future);
//                    }
//
//                    pool.shutdown();
//
//                    for (Future<ActionsPair> actionsPair : actions) {
//                        if ((actionsPair.get().getKey() > 0.5) && (ap_old == null || ap_old.getKey() < actionsPair.get().getKey())) {
//                            ap_old = actionsPair.get();
//                        }
//                    }
//
//                    pool = null;
//                    actions = null;
//                    System.gc();
//
//                } catch (Exception ex) {
//                    Logger.getLogger(StSCorrection.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                if (ap_old != null) {
//                    sb.append(round(ap_old.getKey(), 3)).append("\t").append(ap_old.getStart()).append("\t").append(ap_old.getEnd()).append("\t").append(ap_old.getAction().getName());
//                    sb.append(System.getProperty("line.separator"));
//                    i = ap_old.getEnd();
//                    foundActions.add(ap_old.getAction().getActionId());
//                }
//            }
//            try {
//                BufferedWriter out = new BufferedWriter(new FileWriter(args[5]));
//                out.write(sb.toString());
//                out.close();
//            } catch (Exception ex) {
//                Logger.getLogger(StSCorrection.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//        }
    }

    private static void printStart() {
        System.out.println("\t*********************************************************************");
        System.out.println("\t* If no arguments where resieved, the program will run in test mode *");
        System.out.println("\t*********************************************************************");
        System.out.println("\t* Arguments:                                                        *");
        System.out.println("\t*     0: if = 0 then Compare All Known Actions                      *");
        System.out.println("\t*        else if = 1 then Check Log file                            *");
        System.out.println("\t*     1: maximum overlap (if negative is taken from signatures)     *");
        System.out.println("\t*     2: maximum signature length                                   *");
        System.out.println("\t*     3: path to taxonomy file                                      *");
        System.out.println("\t*     4: path to LOG file                                           *");
        System.out.println("\t*     5: path to result file to be saved                            *");
        System.out.println("\t*********************************************************************");
        System.out.println("");
    }

    private static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
