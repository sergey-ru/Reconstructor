/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stscorrection.Evaluation;

import java.util.ArrayList;
import org.apache.commons.lang3.SystemUtils;
import stscorrection.ActionsPair;

/**
 *
 * @author sergei
 */
public abstract class AbsLogEvaluator {

    private final String _logPath_Win;
    private final String _logPath_UX;
    private final String _logName;
    private final String _log_NF_Result_Folder_Win = "C:\\Users\\sergeyru\\Downloads\\1\\results\\";
    private final String _log_NF_Result_Folder_UX = "/home/sergei/Dropbox/Temp/results/";

    private final String _log_NF_maxLength_Result_Folder_Win = "C:\\Users\\sergeyru\\Downloads\\1\\results\\";
    private final String _log_NF_maxLength_Result_Folder_UX = "/home/sergei/Dropbox/Temp/results/";

    private final String _log_NF_LogRemove_Result_Folder_Win = "C:\\Users\\sergeyru\\Downloads\\1\\results\\";
    private final String _log_NF_LogRemove_Result_Folder_UX = "/home/sergei/Dropbox/Temp/results/";

    public AbsLogEvaluator (String logPath_Win, String logPath_UX, String logName)
    {
            _logPath_Win = logPath_Win;
            _logPath_UX = logPath_UX;
            _logName = logName;
    }
    
    public abstract double evaluateLog(ArrayList<ActionsPair> actionArray);

    public String getName() {
         return _logName;
    }
    
    public String getLogPath() {
        if (SystemUtils.IS_OS_LINUX) {
            return _logPath_UX;
        } else {
            return _logPath_Win;
        }
    }

    public String get_Log_NF_ResultPath() {
        if (SystemUtils.IS_OS_LINUX) {
            return _log_NF_Result_Folder_UX + "NF_Log_" + _logName + ".CSV";
        } else {
            return _log_NF_Result_Folder_Win + "NF_Log_" + _logName + ".CSV";
        }
    }

    public String get_Log_NF_maxLength_ResultPath() {
        if (SystemUtils.IS_OS_LINUX) {
            return _log_NF_maxLength_Result_Folder_UX + "NF_maxLength_Log_" + _logName + ".CSV";
        } else {
            return _log_NF_maxLength_Result_Folder_Win + "NF_maxLength_Log_" + _logName + ".CSV";
        }
    }

    public String get_Log_NF_LogRemove_ResultPath() {
        if (SystemUtils.IS_OS_LINUX) {
            return _log_NF_LogRemove_Result_Folder_UX + "NF_LogRemove_Log_" + _logName + ".CSV";
        } else {
            return _log_NF_LogRemove_Result_Folder_Win + "NF_LogRemove_Log_" + _logName + ".CSV";
        }
    }
}
