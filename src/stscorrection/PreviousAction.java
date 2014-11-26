/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stscorrection;

/**
 *
 * @author sergei
 */
public class PreviousAction {
    
    private int _previous_action_id;
    private double _coefficient;
    
    public PreviousAction(int previous_action_id, double coefficient)
    {
        this._previous_action_id = previous_action_id;
        this._coefficient = coefficient;
    }

    public int getPrevious_action_id() {
        return _previous_action_id;
    }

    public void setPrevious_action_id(int _previous_action_id) {
        this._previous_action_id = _previous_action_id;
    }

    public double getCoefficient() {
        return _coefficient;
    }

    public void setCoefficient(double _coefficient) {
        this._coefficient = _coefficient;
    }
    
}
