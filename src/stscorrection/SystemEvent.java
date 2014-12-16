/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stscorrection;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergei
 */
public class SystemEvent{
    
    
    // <editor-fold defaultstate="collapsed" desc="Data properties">
    
    public int _PID;
    public int _Operation;
    
    public void setOperation(OperationType operation) {
        this._Operation = operation._ID;
    }
    
    // </editor-fold>
    
    public SystemEvent(String p_CSVRow){
        
        p_CSVRow = p_CSVRow.replace("\"", "");
        
        String [] data = p_CSVRow.split("\\s*,\\s*");
       
        if(data.length >= 6){
            
            //PID
            if(data[2] != null && !"".equals(data[2]))
            {
                try  
                {  
                    this._PID = Integer.parseInt(data[2]);  
                } catch(NumberFormatException nfe){
                    Logger.getLogger(SystemEvent.class.getName()).log(Level.SEVERE, null, "Unable to parse PID to integer in constructor.");
                }
            }
            //Operation
            if(data[3] != null && !"".equals(data[3]))
            {
                boolean opFound =false;
                for (OperationType op : OperationType.values()) {
                    if(op._textValue.equals(data[3]))
                    {
                        this._Operation = op._ID;
                        opFound=true;
                        break;
                    }
                }
                if(!opFound)
                {
                    Logger.getLogger(SystemEvent.class.getName()).log(Level.SEVERE, null, "Unable to parse the operation in constructor.");
                }
                
            }
        }
        else{
            //System.err.println(p_CSVRow);
            //Logger.getLogger(SystemEvent.class.getName()).log(Level.SEVERE, null, "Unable to parse the CSV row in constructor.");
        }
    }
    
    public static SystemEvent[] readCSV(String path, double percentage) {
        List<SystemEvent> events = new LinkedList<>();
        String csvFile = path;
        BufferedReader br = null;
        String line;
        int count = 0;

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                count++;
                if (count != 1 && percentage > Math.random()) {
                    events.add(new SystemEvent(line));
                }
            }

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }
        
        return events.toArray(new SystemEvent[0]);
    }
}
