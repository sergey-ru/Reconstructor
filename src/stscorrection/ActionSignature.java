/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stscorrection;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sergei
 */
public class ActionSignature {
    private int _action_id;
    private String _payload;
    private SystemEvent [] _events;
    private String _name;
    private List<PreviousAction> _previous_actions;

    public List<PreviousAction> getPrevious_actions() {
        return _previous_actions;
    }

    public void setPrevious_actions(List<PreviousAction> _previous_actions) {
        this._previous_actions = _previous_actions;
    }
   
    public int getActionId() {
        return _action_id;
    }

    public void setActionId(int _id) {
        this._action_id = _id;
    }

    public String getPayload() {
        return _payload;
    }

    public void setPayload(String _payload) {
        this._payload = _payload;
    }

    public ActionSignature (SystemEvent [] events, String name){
        this._events= events;
        this._name=name;
        this._previous_actions = new ArrayList<>();
    }
    
    public SystemEvent[] getEvents() {
        return _events;
    }

    public void setEvents(SystemEvent[] _events) {
        this._events = _events;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }
}
