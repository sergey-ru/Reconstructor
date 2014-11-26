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
public class ActionsPair {
        
        private final double _key;
        private final ActionSignature _action;
        
        private int _start;

        public int getStart() {
            return _start;
        }

        public int getEnd() {
            return _end;
        }
        private int _end;
        
        public ActionsPair(double _key, ActionSignature _action,int start,int end) {
            this._key = _key;
            this._action = _action;
            this._start=start;
            this._end=end;
        }

        public double getKey() {
            return _key;
        }

        public ActionSignature getAction() {
            return _action;
        }
        
    }
