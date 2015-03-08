/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stscorrection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.Callable;
import stscorrection.Evaluation.AbsLogEvaluator;

/**
 *
 * @author sergei
 */
public class DetectionMethod implements Callable<Double> {

    private final SystemEvent[][] splitedArray;
    private final double coefficient;
    private final int maxLength;
    private final boolean accumulation;
    private final StringBuffer sb;
    private final AbsLogEvaluator evaluator;
    
    
    public DetectionMethod (SystemEvent[][] splitedArray, double coefficient, int maxLength, boolean accumulation, StringBuffer sb, AbsLogEvaluator evaluator)
    {
        this.splitedArray = splitedArray;
        this.coefficient = coefficient;
        this.maxLength = maxLength;
        this.accumulation = accumulation;
        this.sb = sb;
        this.evaluator = evaluator;
    }
    
    @Override
    public Double call() throws Exception {
        HashSet<Integer> foundActions = new HashSet();
        ArrayList<Integer> seenActionsList = new ArrayList<>(splitedArray.length);
        ArrayList<ActionsPair> ansList = new ArrayList<>(splitedArray.length);

        for (int i = 0; i < splitedArray.length; i++) {

            ActionsPair ap_old = null;
            SystemEvent[] toCompare;

            ActionsPair future = null;
            toCompare = splitedArray[i];

            future = EventComparator_NF.CompareEvents(toCompare, foundActions, seenActionsList, i, i, coefficient, maxLength, accumulation);

            if ((future != null && future.getKey() > 0.5) && (ap_old == null || ap_old.getKey() < future.getKey())) {
                ap_old = future;
            
                foundActions.add(ap_old.getAction().getActionId());
                seenActionsList.add(ap_old.getAction().getActionId());
                ansList.add(ap_old);
            }
        }

        double accuracy = 0;
        if (evaluator != null) {
            accuracy = StSCorrection.round(evaluator.evaluateLog(ansList), 4);
            if (sb != null) {
                sb.append(StSCorrection.round(coefficient, 4))
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
    
}
