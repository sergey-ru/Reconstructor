/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stscorrection;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.SystemUtils;
import stscorrection.Evaluation.AbsLogEvaluator;
import stscorrection.Evaluation.*;

/**
 *
 * @author sergei
 */
public class StSCorrection {

    private static final OperationType splitByStart = OperationType.TCPReceive;
    private static final OperationType splitByEnd = OperationType.ThreadExit;
    private static final double[] scale = new double[]{0, 0.3, 0.5, 0.55, 0.575, 0.6, 0.625, 0.65, 0.675, 0.7, 0.725, 0.75, 0.775, 0.8, 0.825, 0.85, 0.875, 0.9, 0.925, 0.95, 0.975, 1};

    public static void main(String[] args) {

//        if (args.length == 0 || Integer.parseInt(args[0]) != 0) {
//            SubMain(args);
//        }

        OperationType.init();
        
        if (SystemUtils.IS_OS_LINUX) {
            EventComparator_NF.init("/home/sergei/Dropbox/~Modeling and Simulation of Advanced Persistent Threat/DarkCommet/Expiriment/taxonomy", splitByStart, splitByEnd);
        } else {
            EventComparator_NF.init("C:\\Users\\sergeyru\\Downloads\\1\\taxonomy", splitByStart, splitByEnd);
        }

        SystemEvent[][] splitedArray = 
                LogReader.splitEventArray(LogReader.readLogFile("/home/sergei/Dropbox/~Modeling and Simulation of Advanced Persistent Threat/DarkCommet/Logs/Logfile_2314.CSV", 1)
                        , splitByStart, splitByEnd);
        
        Test_Method(splitedArray, scale[scale.length - 1], Integer.MAX_VALUE, false, null, null);
    }

    private static void Run_Log_NF(AbsLogEvaluator evaluator) {
        try {
            StringBuffer sb;
            BufferedWriter bwr;
            System.out.println("Log_NF");

            sb = new StringBuffer();
            System.out.println(evaluator.getName());
            Log_NF(sb, evaluator);

            bwr = new BufferedWriter(new FileWriter(evaluator.get_Log_NF_ResultPath()));
            bwr.write(sb.toString());
            bwr.flush();
            bwr.close();

        } catch (IOException ex) {
            Logger.getLogger(StSCorrection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void Run_Log_NF_maxLength(AbsLogEvaluator evaluator) {
        try {
            StringBuffer sb;
            BufferedWriter bwr;
            System.out.println("maxLength");

            sb = new StringBuffer();
            System.out.println(evaluator.getName());
            Log_NF_maxLength(sb, evaluator);

            bwr = new BufferedWriter(new FileWriter(evaluator.get_Log_NF_maxLength_ResultPath()));
            bwr.write(sb.toString());
            bwr.flush();
            bwr.close();

        } catch (IOException ex) {
            Logger.getLogger(StSCorrection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void Run_Log_NF_LogRemove(AbsLogEvaluator evaluator) {
        try {
            StringBuffer sb;
            BufferedWriter bwr;
            System.out.println("LogRemove");

            sb = new StringBuffer();
            System.out.println(evaluator.getName());
            Log_NF_LogRemove(evaluator.getLogPath(), sb, evaluator);

            bwr = new BufferedWriter(new FileWriter(evaluator.get_Log_NF_LogRemove_ResultPath()));
            bwr.write(sb.toString());
            bwr.flush();
            bwr.close();

        } catch (IOException ex) {
            Logger.getLogger(StSCorrection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void Log_NF(StringBuffer sb, AbsLogEvaluator evaluator) {

        OperationType.init();

        if (SystemUtils.IS_OS_LINUX) {
            EventComparator_NF.init("/home/sergei/Dropbox/~Modeling and Simulation of Advanced Persistent Threat/DarkCommet/Expiriment/taxonomy", splitByStart, splitByEnd);
        } else {
            EventComparator_NF.init("C:\\Users\\sergeyru\\Downloads\\1\\taxonomy", splitByStart, splitByEnd);
        }

        SystemEvent[][] splitedArray = LogReader.splitEventArray(LogReader.readLogFile(evaluator.getLogPath(), 1), splitByStart, splitByEnd);

        if (sb == null) {
            System.out.println("Coefficient"
                    + ","
                    + "Multiply"
                    + ","
                    + "maxLength"
                    + ","
                    + "Distance");
        } else {
            sb.append("Coefficient,Multiply,maxLength,Distance").append(System.lineSeparator());
        }

        System.out.println("maxLength=all, accumulation=false");
        sb.append("maxLength=all, accumulation=false").append(System.lineSeparator());
        for (int i = 0; i < scale.length; i++) {
            Test_Method(splitedArray, scale[i], Integer.MAX_VALUE, false, sb, evaluator);
        }

        System.out.println("maxLength=all, accumulation=true");
        sb.append("maxLength=all, accumulation=true").append(System.lineSeparator());
        for (int i = 0; i < scale.length; i++) {
            Test_Method(splitedArray, scale[i], Integer.MAX_VALUE, true, sb, evaluator);
        }

        System.out.println("maxLength=5, accumulation=false");
        sb.append("maxLength=5, accumulation=false").append(System.lineSeparator());
        for (int i = 0; i < scale.length; i++) {
            Test_Method(splitedArray, scale[i], 5, false, sb, evaluator);
        }

        System.out.println("maxLength=5, accumulation=true");
        sb.append("maxLength=5, accumulation=true").append(System.lineSeparator());
        for (int i = 0; i < scale.length; i++) {
            Test_Method(splitedArray, scale[i], 5, true, sb, evaluator);
        }
    }

    private static void Log_NF_maxLength(StringBuffer sb, AbsLogEvaluator evaluator) {

        OperationType.init();

        if (SystemUtils.IS_OS_LINUX) {
            EventComparator_NF.init("/home/sergei/Dropbox/~Modeling and Simulation of Advanced Persistent Threat/DarkCommet/Expiriment/taxonomy", splitByStart, splitByEnd);
        } else {
            EventComparator_NF.init("C:\\Users\\sergeyru\\Downloads\\1\\taxonomy", splitByStart, splitByEnd);
        }

        SystemEvent[][] splitedArray = LogReader.splitEventArray(LogReader.readLogFile(evaluator.getLogPath(), 1), splitByStart, splitByEnd);

        if (sb == null) {
            System.out.println("Coefficient"
                    + ","
                    + "Multiply"
                    + ","
                    + "maxLength"
                    + ","
                    + "Distance");
        } else {
            sb.append("Coefficient,Multiply,maxLength,Distance").append(System.lineSeparator());
        }

        System.out.println("maxLength=all, accumulation=false");
        sb.append("maxLength=all, accumulation=false").append(System.lineSeparator());

        for (int i = 0; i < scale.length; i++) {
            Test_Method(splitedArray, scale[i], Integer.MAX_VALUE, false, sb, evaluator);
        }

        for (int j = 0; j < 10; j++) {

            System.out.println("maxLength=" + (j + 1) + ", accumulation=false");
            sb.append("maxLength=").append(j + 1).append(", accumulation=false").append(System.lineSeparator());

            for (int i = 0; i < scale.length; i++) {
                Test_Method(splitedArray, scale[i], j, false, sb, evaluator);
            }
        }
    }

    private static void Log_NF_LogRemove(String name, StringBuffer sb, AbsLogEvaluator evaluator) {
        //System.out.println("No Multiply - All length");

        System.out.println("maxLength=2, accumulation=false");
        sb.append("maxLength=2, accumulation=false").append(System.lineSeparator());

        System.out.println("Coefficient" + "," + "Multiply" + "," + "maxLength" + "," + "Distance");
        sb.append("Coefficient,Multiply,maxLength,Distance").append(System.lineSeparator());

        for (double logPercent = 1; logPercent > 0.45; logPercent -= 0.05) {

            sb.append("logPercent=").append(round(logPercent, 2)).append(System.lineSeparator());

            OperationType.init();

            if (SystemUtils.IS_OS_LINUX) {
                EventComparator_NF.init("/home/sergei/Dropbox/~Modeling and Simulation of Advanced Persistent Threat/DarkCommet/Expiriment/taxonomy", splitByStart, splitByEnd);
            } else {
                EventComparator_NF.init("C:\\Users\\sergeyru\\Downloads\\1\\taxonomy", splitByStart, splitByEnd);
            }

            for (int i = 0; i < scale.length; i++) {
                sb.append(round(scale[i], 4))
                        .append(",")
                        .append(false)
                        .append(",")
                        .append(2)
                        .append(",");
                ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
                for (int runNum = 0; runNum < 10; runNum++) {

                    SystemEvent[][] splitedArray = LogReader.splitEventArray(LogReader.readLogFile(evaluator.getLogPath(), logPercent), splitByStart, splitByEnd);
                    System.out.println("scale " + scale[i] + ", runNum=" + runNum + ", logPercent=" + round(logPercent, 2));
                    
                    Future<Double> accuracy = pool.submit(new DetectionMethod(splitedArray, scale[i], 1, false, null, evaluator));
                    //double accuracy = Test_Method(splitedArray, scale[i], 1, false, null, evaluator);
                    try {
                        sb.append(accuracy.get()).append(",");
                    } catch (InterruptedException | ExecutionException ex) {
                        Logger.getLogger(StSCorrection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                pool.shutdown();
                sb.append(System.lineSeparator());
            }
        }

    }

    private static double Test_Method(SystemEvent[][] splitedArray, double coefficient, int maxLength, boolean accumulation, StringBuffer sb, AbsLogEvaluator evaluator) {

        HashSet<Integer> foundActions = new HashSet();
        ArrayList<Integer> seenActionsList = new ArrayList<>(splitedArray.length);
        ArrayList<ActionsPair> ansList = new ArrayList<>(splitedArray.length);

        //try{
        //ExecutorService pool = Executors.newFixedThreadPool(1);//Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < splitedArray.length; i++) {

            ActionsPair ap_old = null;
            SystemEvent[] toCompare;

            ActionsPair future = null;
            toCompare = splitedArray[i];

            future = EventComparator_NF.CompareEvents(toCompare, foundActions, seenActionsList, i, i, coefficient, maxLength, accumulation);

            //Future<ActionsPair> future = pool.submit(new EventComparator_NF(toCompare, foundActions, seenActionsList, i, i, coefficient, maxLength, accumulation));
            if ((future != null && future.getKey() > 0.5) && (ap_old == null || ap_old.getKey() < future.getKey())) {
                ap_old = future;
            //}

                //if (ap_old != null) {
                foundActions.add(ap_old.getAction().getActionId());
                seenActionsList.add(ap_old.getAction().getActionId());
                ansList.add(ap_old);
            }
        }

        //pool.shutdown();
//        }
//        catch(Exception ex)
//        {
//            System.out.println("Error !!!!");
//        }
        double accuracy = 0;
        if (evaluator != null) {
            accuracy = round(evaluator.evaluateLog(ansList), 4);
            if (sb != null) {
                sb.append(round(coefficient, 4))
                        .append(",")
                        .append(accumulation)
                        .append(",")
                        .append(maxLength)
                        .append(",")
                        .append(accuracy)
                        .append(System.lineSeparator());
            }
        } else {
            for (ActionsPair ansList1 : ansList) {
                System.out.println(ansList1.getAction().getName());
            }
        }
        return accuracy;
    }

    private static void SubMain(String[] args) {
        if (args.length == 0) {
            System.out.println("\t*********************************************************************");
            System.out.println("\t* Second param:                                                     *");
            System.out.println("\t*     1: Log_NF                                                     *");
            System.out.println("\t*     2: Log_NF_maxLength                                           *");
            System.out.println("\t*     3: Log_NF_LogRemove                                           *");
            System.out.println("\t*********************************************************************");
            System.out.println("\t*     1: Sergei                                                     *");
            System.out.println("\t*     2: Slava                                                      *");
            System.out.println("\t*     3: Alex                                                       *");
            System.out.println("\t*     4: Amit                                                       *");
            System.out.println("\t*     5: Guy                                                        *");
            System.out.println("\t*     6: 1243                                                       *");
            System.out.println("\t*     7: 3124                                                       *");
            System.out.println("\t*     8: 4213                                                       *");
            System.out.println("\t*     9: 4231                                                       *");
            System.out.println("\t*    10: 4312                                                       *");
            System.out.println("\t*    11: 4321                                                       *");
            System.out.println("\t*    12: 4132                                                       *");
            System.out.println("\t*    13: 4123                                                       *");
            System.out.println("\t*    14: 3421                                                       *");
            System.out.println("\t*    15: 3241                                                       *");
            System.out.println("\t*    16: 3412                                                       *");
            System.out.println("\t*    17: 1234                                                       *");
            System.out.println("\t*    18: 3214                                                       *");
            System.out.println("\t*    19: 3142                                                       *");
            System.out.println("\t*    20: 2431                                                       *");
            System.out.println("\t*    21: 2413                                                       *");
            System.out.println("\t*    22: 2341                                                       *");
            System.out.println("\t*********************************************************************");
            System.out.println("");
        } else if (args.length == 2) {
            switch (Integer.parseInt(args[0])) {
                case 1:
                    SubSubMain(new LogSergei(), Integer.parseInt(args[1]));
                    return;
                case 2:
                    SubSubMain(new LogSlava(), Integer.parseInt(args[1]));
                    return;
                case 3:
                    SubSubMain(new LogAlex(), Integer.parseInt(args[1]));
                    return;
                case 4:
                    SubSubMain(new LogAmit(), Integer.parseInt(args[1]));
                    return;
                case 5:
                    SubSubMain(new LogGay(), Integer.parseInt(args[1]));
                    return;
                case 6:
                    SubSubMain(new Log1243(), Integer.parseInt(args[1]));
                    return;
                case 7:
                    SubSubMain(new Log3124(), Integer.parseInt(args[1]));
                    return;
                case 8:
                    SubSubMain(new Log4213(), Integer.parseInt(args[1]));
                    return;
                case 9:
                    SubSubMain(new Log4231(), Integer.parseInt(args[1]));
                    return;
                case 10:
                    SubSubMain(new Log4312(), Integer.parseInt(args[1]));
                    return;
                case 11:
                    SubSubMain(new Log4321(), Integer.parseInt(args[1]));
                    return;
                case 12:
                    SubSubMain(new Log4132(), Integer.parseInt(args[1]));
                    return;
                case 13:
                    SubSubMain(new Log4123(), Integer.parseInt(args[1]));
                    return;
                case 14:
                    SubSubMain(new Log3421(), Integer.parseInt(args[1]));
                    return;
                case 15:
                    SubSubMain(new Log3241(), Integer.parseInt(args[1]));
                    return;
                case 16:
                    SubSubMain(new Log3412(), Integer.parseInt(args[1]));
                    return;
                case 17:
                    SubSubMain(new Log1234(), Integer.parseInt(args[1]));
                    return;
                case 18:
                    SubSubMain(new Log3214(), Integer.parseInt(args[1]));
                    return;
                case 19:
                    SubSubMain(new Log3142(), Integer.parseInt(args[1]));
                    return;
                case 20:
                    SubSubMain(new Log2431(), Integer.parseInt(args[1]));
                    return;
                case 21:
                    SubSubMain(new Log2413(), Integer.parseInt(args[1]));
                    return;
                case 22:
                    SubSubMain(new Log2341(), Integer.parseInt(args[1]));
                    return;
                default:
                    System.out.println("Error. No file selected .... ");

            }
            System.out.println("Error. Wrong number of parameters .... ");
        }
    }

    private static void SubSubMain(AbsLogEvaluator evaluator, int x) {
        switch (x) {
            case 1:
                Run_Log_NF(evaluator);
                return;
            case 2:
                Run_Log_NF_maxLength(evaluator);
                return;
            case 3:
                Run_Log_NF_LogRemove(evaluator);
                return;
            default:
                System.out.println("Error. No function selected .... ");

        }
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
