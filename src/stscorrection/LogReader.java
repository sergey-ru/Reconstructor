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
public class LogReader {

    public static SystemEvent[] readLogFile(String filePath) {
        SystemEvent[] ans = SystemEvent.readCSV(filePath);
        return ans;
    }

    public static SystemEvent[][] splitEventArray(SystemEvent[] eventArray, OperationType splitByStart, OperationType splitByEnd) {
        List<Integer> actionPossitions = new ArrayList<>();

        SystemEvent[][] ans;

        boolean startMode=false;
        actionPossitions.add(0, 0);
        for (int i = 0; i < eventArray.length; i++) {
            
            if (startMode && eventArray[i]._Operation == splitByStart._ID) {
                actionPossitions.add(i);
                startMode =false;
            }
            if(!startMode && eventArray[i]._Operation == splitByEnd._ID){
                actionPossitions.add(i);
                startMode = true;
            }
        }
        
        if(actionPossitions.size()%2!=0)
        {
            actionPossitions.add(eventArray.length-1);
        }
        
        ans = new SystemEvent[(actionPossitions.size()+1)/2][];
        
        for (int i = 0; i < ans.length; i++) {
            
            int posCount = 0;
            int posStart = actionPossitions.get(i*2);
            int posEnd = actionPossitions.get(i*2+1)+1;
            
            ans[i] = new SystemEvent[posEnd - posStart];

            for (int j = posStart; j < posEnd; j++) {
                ans[i][posCount] = eventArray[j];
                posCount++;
            }
        }
        return ans;
    }

}
