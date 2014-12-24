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
import java.io.IOException;
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
import stscorrection.Evaluation.ILogEvaluator;
import stscorrection.Evaluation.LogAlex;
import stscorrection.Evaluation.LogAmit;
import stscorrection.Evaluation.LogGay;
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
        //"/home/sergei/Dropbox/~Modeling and Simulation of Advanced Persistent Threat/DarkCommet/Logs/Logfile_Slava.CSV"

        printStart();

        Date d = new Date();

//        Run_Log_NF();
//
//        System.out.println("Log_NF Done :)");
//
//        Run_Log_NF_maxLenght();
//
//        System.out.println("Log_NF_maxLenght Done :)");

        Run_Log_NF_LogRemove();

        System.out.println("Log_NF_LogRemove Done :)");

        System.out.println("The end :)   " + (new Date().getTime() - d.getTime()) + "ms");
    }

    private static void Run_Log_NF() {
        try {
            StringBuffer sb = new StringBuffer();
            BufferedWriter bwr;

            Log_NF("C:\\Users\\sergeyru\\Downloads\\1\\Logfile_Sergei.CSV", sb, new LogSergei());

            bwr = new BufferedWriter(new FileWriter("C:\\Users\\sergeyru\\Downloads\\1\\results\\NF_Log_Sergei.CSV"));
            bwr.write(sb.toString());
            bwr.flush();
            bwr.close();

            sb = new StringBuffer();
            Log_NF("C:\\Users\\sergeyru\\Downloads\\1\\Logfile_Slava.CSV", sb, new LogSlava());

            bwr = new BufferedWriter(new FileWriter("C:\\Users\\sergeyru\\Downloads\\1\\results\\NF_Log_Slava.CSV"));
            bwr.write(sb.toString());
            bwr.flush();
            bwr.close();

            sb = new StringBuffer();
            Log_NF("C:\\Users\\sergeyru\\Downloads\\1\\Logfile_alex.CSV", sb, new LogAlex());

            bwr = new BufferedWriter(new FileWriter("C:\\Users\\sergeyru\\Downloads\\1\\results\\NF_Log_alex.CSV"));
            bwr.write(sb.toString());
            bwr.flush();
            bwr.close();

            sb = new StringBuffer();
            Log_NF("C:\\Users\\sergeyru\\Downloads\\1\\Logfile_gay.CSV", sb, new LogGay());

            bwr = new BufferedWriter(new FileWriter("C:\\Users\\sergeyru\\Downloads\\1\\results\\NF_Log_gay.CSV"));
            bwr.write(sb.toString());
            bwr.flush();
            bwr.close();

            sb = new StringBuffer();
            Log_NF("C:\\Users\\sergeyru\\Downloads\\1\\Logfile_amit.CSV", sb, new LogAmit());

            bwr = new BufferedWriter(new FileWriter("C:\\Users\\sergeyru\\Downloads\\1\\results\\NF_Log_amit.CSV"));
            bwr.write(sb.toString());
            bwr.flush();
            bwr.close();

        } catch (IOException ex) {
            Logger.getLogger(StSCorrection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void Run_Log_NF_maxLenght() {
        try {
            StringBuffer sb = new StringBuffer();
            BufferedWriter bwr;

            Log_NF_maxLenght("C:\\Users\\sergeyru\\Downloads\\1\\Logfile_Sergei.CSV", sb, new LogSergei());

            bwr = new BufferedWriter(new FileWriter("C:\\Users\\sergeyru\\Downloads\\1\\results\\NF_maxLenght_Log_Sergei.CSV"));
            bwr.write(sb.toString());
            bwr.flush();
            bwr.close();

            sb = new StringBuffer();
            Log_NF_maxLenght("C:\\Users\\sergeyru\\Downloads\\1\\Logfile_Slava.CSV", sb, new LogSlava());

            bwr = new BufferedWriter(new FileWriter("C:\\Users\\sergeyru\\Downloads\\1\\results\\NF_maxLenght_Log_Slava.CSV"));
            bwr.write(sb.toString());
            bwr.flush();
            bwr.close();

            sb = new StringBuffer();
            Log_NF_maxLenght("C:\\Users\\sergeyru\\Downloads\\1\\Logfile_alex.CSV", sb, new LogAlex());

            bwr = new BufferedWriter(new FileWriter("C:\\Users\\sergeyru\\Downloads\\1\\results\\NF_maxLenght_Log_alex.CSV"));
            bwr.write(sb.toString());
            bwr.flush();
            bwr.close();

            sb = new StringBuffer();
            Log_NF_maxLenght("C:\\Users\\sergeyru\\Downloads\\1\\Logfile_gay.CSV", sb, new LogGay());

            bwr = new BufferedWriter(new FileWriter("C:\\Users\\sergeyru\\Downloads\\1\\results\\NF_maxLenght_Log_gay.CSV"));
            bwr.write(sb.toString());
            bwr.flush();
            bwr.close();

            sb = new StringBuffer();
            Log_NF_maxLenght("C:\\Users\\sergeyru\\Downloads\\1\\Logfile_amit.CSV", sb, new LogAmit());

            bwr = new BufferedWriter(new FileWriter("C:\\Users\\sergeyru\\Downloads\\1\\results\\NF_maxLenght_Log_amit.CSV"));
            bwr.write(sb.toString());
            bwr.flush();
            bwr.close();

        } catch (IOException ex) {
            Logger.getLogger(StSCorrection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void Run_Log_NF_LogRemove() {
        try {
            StringBuffer sb = new StringBuffer();
            BufferedWriter bwr;

            Log_NF_LogRemove("C:\\Users\\sergeyru\\Downloads\\1\\Logfile_Sergei.CSV", sb, new LogSergei());

            bwr = new BufferedWriter(new FileWriter("C:\\Users\\sergeyru\\Downloads\\1\\results\\NF_LogRemove_Log_Sergei.CSV"));
            bwr.write(sb.toString());
            bwr.flush();
            bwr.close();

            sb = new StringBuffer();
            Log_NF_LogRemove("C:\\Users\\sergeyru\\Downloads\\1\\Logfile_Slava.CSV", sb, new LogSlava());

            bwr = new BufferedWriter(new FileWriter("C:\\Users\\sergeyru\\Downloads\\1\\results\\NF_LogRemove_Log_Slava.CSV"));
            bwr.write(sb.toString());
            bwr.flush();
            bwr.close();

            sb = new StringBuffer();
            Log_NF_LogRemove("C:\\Users\\sergeyru\\Downloads\\1\\Logfile_alex.CSV", sb, new LogAlex());

            bwr = new BufferedWriter(new FileWriter("C:\\Users\\sergeyru\\Downloads\\1\\results\\NF_LogRemove_Log_alex.CSV"));
            bwr.write(sb.toString());
            bwr.flush();
            bwr.close();

            sb = new StringBuffer();
            Log_NF_LogRemove("C:\\Users\\sergeyru\\Downloads\\1\\Logfile_gay.CSV", sb, new LogGay());

            bwr = new BufferedWriter(new FileWriter("C:\\Users\\sergeyru\\Downloads\\1\\results\\NF_LogRemove_Log_gay.CSV"));
            bwr.write(sb.toString());
            bwr.flush();
            bwr.close();

            sb = new StringBuffer();
            Log_NF_LogRemove("C:\\Users\\sergeyru\\Downloads\\1\\Logfile_amit.CSV", sb, new LogAmit());

            bwr = new BufferedWriter(new FileWriter("C:\\Users\\sergeyru\\Downloads\\1\\results\\NF_LogRemove_Log_amit.CSV"));
            bwr.write(sb.toString());
            bwr.flush();
            bwr.close();

        } catch (IOException ex) {
            Logger.getLogger(StSCorrection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void Log_NF(String name, StringBuffer sb, ILogEvaluator evaluator) {
        //System.out.println("No Multiply - All length");
        if (sb == null) {
            System.out.println("Coefficient"
                    + "\t\t"
                    + "Multiply"
                    + "\t\t"
                    + "maxLenght"
                    + "\t\t"
                    + "Diferential"
                    + "\t\t"
                    + "Distance");
        } else {
            sb.append("Coefficient\t\tMultiply\t\tmaxLenght\t\tDiferential\t\tDistance").append(System.lineSeparator());
        }

        if (sb == null) {
            System.out.println("maxLenght=all, isDiferential=false, multiply=false");
        } else {
            sb.append("maxLenght=all, isDiferential=false, multiply=false").append(System.lineSeparator());
        }

        for (double i = 0; i < 2.1; i = i + 0.1) {
            Parallel_Test_Method(i, -1, false, false, name, 1, sb, evaluator);
        }

        if (sb == null) {
            System.out.println("maxLenght=all, isDiferential=false, multiply=true");
        } else {
            sb.append("maxLenght=all, isDiferential=false, multiply=true").append(System.lineSeparator());
        }

        for (double i = 0; i < 2.1; i = i + 0.1) {
            Parallel_Test_Method(i, -1, false, true, name, 1, sb, evaluator);
        }

        if (sb == null) {
            System.out.println("maxLenght=all, isDiferential=true, multiply=false");
        } else {
            sb.append("maxLenght=all, isDiferential=true, multiply=false").append(System.lineSeparator());
        }

        for (double i = 0; i < 2.1; i = i + 0.1) {
            Parallel_Test_Method(i, -1, true, false, name, 1, sb, evaluator);
        }

        if (sb == null) {
            System.out.println("maxLenght=all, isDiferential=true, multiply=true");
        } else {
            sb.append("maxLenght=all, isDiferential=true, multiply=true").append(System.lineSeparator());
        }

        for (double i = 0; i < 2.1; i = i + 0.1) {
            Parallel_Test_Method(i, -1, true, true, name, 1, sb, evaluator);
        }

        if (sb == null) {
            System.out.println("maxLenght=5, isDiferential=false, multiply=false");
        } else {
            sb.append("maxLenght=5, isDiferential=false, multiply=false").append(System.lineSeparator());
        }

        for (double i = 0; i < 2.1; i = i + 0.1) {
            Parallel_Test_Method(i, 5, false, false, name, 1, sb, evaluator);
        }

        if (sb == null) {
            System.out.println("maxLenght=5, isDiferential=false, multiply=true");
        } else {
            sb.append("maxLenght=5, isDiferential=false, multiply=true").append(System.lineSeparator());
        }

        for (double i = 0; i < 2.1; i = i + 0.1) {
            Parallel_Test_Method(i, 5, false, true, name, 1, sb, evaluator);
        }

        if (sb == null) {
            System.out.println("maxLenght=5, isDiferential=true, multiply=false");
        } else {
            sb.append("maxLenght=5, isDiferential=true, multiply=false").append(System.lineSeparator());
        }

        for (double i = 0; i < 2.1; i = i + 0.1) {
            Parallel_Test_Method(i, 5, true, false, name, 1, sb, evaluator);
        }

        if (sb == null) {
            System.out.println("maxLenght=5, isDiferential=true, multiply=true");
        } else {
            sb.append("maxLenght=5, isDiferential=true, multiply=true").append(System.lineSeparator());
        }

        for (double i = 0; i < 2.1; i = i + 0.1) {
            Parallel_Test_Method(i, 5, true, true, name, 1, sb, evaluator);
        }
    }

    private static void Log_NF_maxLenght(String name, StringBuffer sb, ILogEvaluator evaluator) {
        //System.out.println("No Multiply - All length");
        if (sb == null) {
            System.out.println("Coefficient"
                    + "\t\t"
                    + "Multiply"
                    + "\t\t"
                    + "maxLenght"
                    + "\t\t"
                    + "Diferential"
                    + "\t\t"
                    + "Distance");
        } else {
            sb.append("Coefficient\t\tMultiply\t\tmaxLenght\t\tDiferential\t\tDistance").append(System.lineSeparator());
        }

        if (sb == null) {
            System.out.println("maxLenght=all, isDiferential=true, multiply=false");
        } else {
            sb.append("maxLenght=all, isDiferential=true, multiply=false").append(System.lineSeparator());
        }

        for (double i = 0; i < 2.1; i = i + 0.1) {
            Parallel_Test_Method(i, -1, true, false, name, 1, sb, evaluator);
        }

        for (int j = 0; j < 10; j++) {
            if (sb == null) {
                System.out.println("maxLenght=" + (j + 1) + ", isDiferential=true, multiply=false");
            } else {
                sb.append("maxLenght=").append(j + 1).append(", isDiferential=true, multiply=false").append(System.lineSeparator());
            }

            for (double i = 0; i < 2.1; i = i + 0.1) {
                Parallel_Test_Method(i, j, true, false, name, 1, sb, evaluator);
            }
        }
    }

    private static void Log_NF_LogRemove(String name, StringBuffer sb, ILogEvaluator evaluator) {
        //System.out.println("No Multiply - All length");
        if (sb == null) {
            System.out.println("Coefficient"
                    + "\t\t"
                    + "Multiply"
                    + "\t\t"
                    + "maxLenght"
                    + "\t\t"
                    + "Diferential"
                    + "\t\t"
                    + "Distance");
        } else {
            sb.append("Coefficient\t\tMultiply\t\tmaxLenght\t\tDiferential\t\tDistance").append(System.lineSeparator());
        }

        if (sb == null) {
            System.out.println("maxLenght=1, isDiferential=true, multiply=false");
        } else {
            sb.append("maxLenght=1, isDiferential=true, multiply=false").append(System.lineSeparator());
        }

        for (double logPercent = 1; logPercent >= 0.5; logPercent -= 0.05) {

            for (int runNum = 0; runNum < 10; runNum++) {

                if (sb == null) {
                    System.out.println("runNum=" + runNum + ", logPercent=" + logPercent);
                } else {
                    sb.append("runNum=").append(runNum).append(", logPercent=").append(round(logPercent, 2)).append(System.lineSeparator());
                }

                for (double i = 0; i < 2.1; i = i + 0.1) {
                    Parallel_Test_Method(i, 0, true, false, name, logPercent, sb, evaluator);
                }
            }
        }

    }

    private static void Parallel_Test_Method(double coefficient, int maxLenght, boolean isDiferential, boolean multiply, String logURI, double logPercentage, StringBuffer sb, ILogEvaluator evaluator) {

        OperationType.init();

        //maxOverLap = EventComparator_NF.init("/home/sergei/Dropbox/~Modeling and Simulation of Advanced Persistent Threat/DarkCommet/Expiriment/taxonomy", splitByStart, splitByEnd);
        maxOverLap = EventComparator_NF.init("C:\\Users\\sergeyru\\Downloads\\1\\taxonomy", splitByStart, splitByEnd);

        maxOverLap = 1;

        if (sb == null) {

            System.out.println("Checking Log file");
            System.out.println("maxOverLap=" + maxOverLap);
            System.out.println("maxSignature=" + maxSignature);
            System.out.println();
        }

        //SystemEvent[][] splitedArray = LogReader.splitEventArray(LogReader.readLogFile("/home/sergei/Dropbox/~Modeling and Simulation of Advanced Persistent Threat/DarkCommet/Logfile_042.CSV"), splitByStart, splitByEnd);
        SystemEvent[][] splitedArray = LogReader.splitEventArray(LogReader.readLogFile(logURI, logPercentage), splitByStart, splitByEnd);
        HashSet<Integer> foundActions = new HashSet();
        LinkedList<Integer> seenActionsList = new LinkedList<>();
        ArrayList<ActionsPair> ansList = new ArrayList<>();

        for (int i = 0; i < splitedArray.length; i++) {
            ActionsPair ap_old = null;
            SystemEvent[] toCompare = new SystemEvent[0];
            try {
                ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2);
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
                if (sb == null) {
                    System.out.println(round(ap_old.getKey(), 3) + "\t" + ap_old.getStart() + "\t" + ap_old.getEnd() + "\t" + ap_old.getAction().getName());
                }
                i = ap_old.getEnd();
                foundActions.add(ap_old.getAction().getActionId());
                seenActionsList.add(ap_old.getAction().getActionId());
                ansList.add(ap_old);
            }
        }

        if (sb == null) {
            System.out.println(round(coefficient, 2)
                    + "\t\t"
                    + multiply
                    + "\t\t"
                    + maxLenght
                    + "\t\t"
                    + isDiferential
                    + "\t\t"
                    + round(evaluator.evaluateLog(ansList), 4));
        } else {
            sb.append(round(coefficient, 2)
                    + "\t\t"
                    + multiply
                    + "\t\t"
                    + maxLenght
                    + "\t\t"
                    + isDiferential
                    + "\t\t"
                    + round(evaluator.evaluateLog(ansList), 4)
                    + System.lineSeparator());
        }
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
