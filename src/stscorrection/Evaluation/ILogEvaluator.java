/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stscorrection.Evaluation;

import java.util.ArrayList;
import stscorrection.ActionsPair;

/**
 *
 * @author sergei
 */
public interface ILogEvaluator {
    public double evaluateLog(ArrayList<ActionsPair> actionList);
}
