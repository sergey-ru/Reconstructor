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
public class LogAlex extends AbsLogEvaluator {

    public LogAlex() {
        super("C:\\Users\\sergeyru\\Downloads\\1\\Logfile_alex.CSV", 
                "/home/sergei/Dropbox/~Modeling and Simulation of Advanced Persistent Threat/DarkCommet/Logs/Logfile_alex.CSV", "Alex");
    }

    @Override
    public double evaluateLog(ArrayList<ActionsPair> actionList) {
        String[] ref = new String[]{
            "Passwords StoredPasswords Refresh",
            "FileManager ExplorerFiles Open C",
            "FileManager ExplorerFiles Open Users",
            "FileManager ExplorerFiles Open admin",
            "FileManager ExplorerFiles Open AppData",
            "FileManager ExplorerFiles Open Roaming",
            "FileManager ExplorerFiles Open Skype",
            "FileManager ExplorerFiles Open alon aloni2",
            "FileManager ExplorerFiles Receive mainDB",
            "FileManager ExplorerFiles Open Skype",
            "FileManager ExplorerFiles Open Roaming",
            "FileManager ExplorerFiles Open AppData",
            "FileManager ExplorerFiles Open admin",
            "FileManager ExplorerFiles Open Users",
            "FileManager ExplorerFiles Open C",
            "FileManager ExplorerFiles Open inetpub",
            "FileManager ExplorerFiles Open wwwroot",
            "FileManager ExplorerFiles Send Yahoo",
            "SystemFunctions HostsFile UpdateHost",
            "FileManager ExplorerFiles Open inetpub",
            "FileManager ExplorerFiles Open C",
            "FileManager ExplorerFiles Open Users",
            "FileManager ExplorerFiles Open admin",
            "FileManager ExplorerFiles Open AppData",
            "FileManager ExplorerFiles Open Local",
            "FileManager ExplorerFiles Open Google",
            "FileManager ExplorerFiles Open Chrome",
            "FileManager ExplorerFiles Open Chrome UserData",
            "FileManager ExplorerFiles Open Chrome UserData Default",
            "FileManager ExplorerFiles Receive History",
            "SystemFunctions HostsFile UpdateHost",
            "FileManager SearchForFiles Recursive",
            "FileManager ExplorerFiles Open Documents",
            "FileManager ExplorerFiles Receive Grades",
            "FileManager ExplorerFiles Send Grades"
        };
        
        String [] ansArray = new String[actionList.size()];
        int i = 0;
        for (ActionsPair action : actionList) {
           ansArray[i] = action.getAction().getName();
           i++;
        }
        
        return  StrArrayComperator.calcScore(ref, ansArray);
    }
}
