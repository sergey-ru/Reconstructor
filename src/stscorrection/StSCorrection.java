/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stscorrection;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author sergei
 */
public class StSCorrection {

    private static final OperationType splitByStart = OperationType.TCPReceive;
    private static final OperationType splitByEnd = OperationType.ThreadExit;
    private static int maxOverLap = 4;
    
    public static void main(String[] args) {
        
        printStart();
        
        Date d = new Date();

        if (args.length == 0) {
            Parallel_Test_Method();
        } else {
            Parallel_Main_Method(args);
        }

        System.out.println("The end :)   " + (new Date().getTime() - d.getTime()) + "ms");
    }

    private static void Parallel_Test_Method() {
        
        OperationType.init();
        
        maxOverLap = EventComparator.init("/home/sergei/Dropbox/~Modeling and Simulation of Advanced Persistent Threat/DarkCommet/Expiriment/taxonomy", splitByStart, splitByEnd);
        
        System.out.println("Check Log file");

        //SystemEvent[][] splitedArray = LogReader.splitEventArray(LogReader.readLogFile("/home/sergei/Dropbox/~Modeling and Simulation of Advanced Persistent Threat/DarkCommet/Logfile_042.CSV"), splitByStart, splitByEnd);
        SystemEvent[][] splitedArray = LogReader.splitEventArray(LogReader.readLogFile("/home/sergei/Dropbox/~Modeling and Simulation of Advanced Persistent Threat/DarkCommet/Logs/Logfile_alex.CSV"), splitByStart, splitByEnd);
        HashSet<Integer> foundActions = new HashSet();

        for (int i = 0; i < splitedArray.length; i++) {
            ActionsPair ap_old = null;
            SystemEvent[] toCompare = new SystemEvent[0];
            try {
                //ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
                ExecutorService pool = Executors.newFixedThreadPool(1);

                List<Future<ActionsPair>> actions = new ArrayList<>();
                for (int j = i; j < splitedArray.length && j < i+maxOverLap; j++) {

                    toCompare = ArrayUtils.addAll(toCompare, splitedArray[j]);
                    if(toCompare.length>50000){
                        System.out.println("break");
                        break;
                    }
                    Future<ActionsPair> future = pool.submit(new EventComparator(toCompare,foundActions, i, j));
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
                System.out.println(round(ap_old.getKey(), 3) + "\t" + ap_old.getStart() + "\t" + ap_old.getEnd() + "\t" + ap_old.getAction().getName());
                i = ap_old.getEnd();
                foundActions.add(ap_old.getAction().getActionId());
            }
        }
    }

    
    private static void Parallel_Main_Method(String[] args) {
        OperationType.init();
        maxOverLap = EventComparator.init(args[2], splitByStart, splitByEnd);
        
        if(Integer.parseInt(args[1]) > 0)
        {
            maxOverLap = Integer.parseInt(args[1]);
        }

        if (args[0].contains("0")) {
            System.out.println("Compare All Known Actions");
            EventComparator.compareAllKnownActions();
        } else if (args[0].contains("1")) {

            System.out.println("Checking Log file");
            StringBuilder sb = new StringBuilder();
            SystemEvent[][] splitedArray = LogReader.splitEventArray(LogReader.readLogFile(args[3]), splitByStart, splitByEnd);
            HashSet<Integer> foundActions = new HashSet();
            
            
            for (int i = 0; i < splitedArray.length; i++) {
                
                SystemEvent[] toCompare = new SystemEvent[0];
                ActionsPair ap_old = null;
                
                System.out.println((i+1)+" from "+splitedArray.length);
                
                try {
                    ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
                    List<Future<ActionsPair>> actions = new ArrayList<>();

                    for (int j = i; j < splitedArray.length && j < i+maxOverLap; j++) {
                        toCompare = ArrayUtils.addAll(toCompare, splitedArray[j]);
                        Future<ActionsPair> future = pool.submit(new EventComparator(toCompare, foundActions, i, j));

                        actions.add(future);
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
                    sb.append(round(ap_old.getKey(), 3)).append("\t").append(ap_old.getStart()).append("\t").append(ap_old.getEnd()).append("\t").append(ap_old.getAction().getName());
                    sb.append(System.getProperty("line.separator"));
                    i = ap_old.getEnd();
                    foundActions.add(ap_old.getAction().getActionId());
                }
            }
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(args[4]));
                out.write(sb.toString());
                out.close();
            } catch (Exception ex) {
                Logger.getLogger(StSCorrection.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    private static void printStart()
    {
        System.out.println("If no arguments where resieved, the program will run in test mode");
        System.out.println("");
        System.out.println("Arguments:");
        System.out.println("    0: if = 0 then Compare All Known Actions");
        System.out.println("       else if = 1 then Check Log file");
        System.out.println("    1: maximum overlap (if negative is taken from signatures)");
        System.out.println("    2: path to taxonomy file");
        System.out.println("    3: path to LOG file");
        System.out.println("    4: path to result file to be saved");
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
