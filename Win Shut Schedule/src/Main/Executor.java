package Main;

import javax.swing.JOptionPane;
import java.io.IOException;

public class Executor extends MainForm
{

    static int validSec, hour, min, sec;
    static String cmdForce=null,cmdShut;
    Process Proc;

    public void doShutAct(String h, String m, String s, boolean isForce, String shutAct)
    {
        //try to detect fail time set
        try
        {
            validSec = Integer.parseInt(h);
            validSec = Integer.parseInt(m);
            validSec = Integer.parseInt(s);

            //if valid set to seconds
            hour = Integer.parseInt(h) * 3600;
            min = Integer.parseInt(m) * 60;
            sec = Integer.parseInt(s);

            validSec = hour + min + sec;
            hour = 0;
            min = 0;
            sec = 0;

            //check forced act
            try
            {
               
                if (isForce == true && shutAct == "Shutdown")
                {
                    cmdShut="/s";
                    cmdForce="-f";
                } else if (isForce == false && shutAct == "Shutdown")
                {
                    cmdShut="/s";
                    cmdForce="";
                }
                
                if (isForce == true && shutAct == "Restart")
                {
                    cmdShut="/r";
                    cmdForce="-f";
                } else if (isForce == false && shutAct == "Restart")
                {
                    cmdShut="/r";
                    cmdForce="";
                }
                
                if (shutAct!="Abort Action")
                {
                 
                    Proc = Runtime.getRuntime().exec("shutdown "+cmdForce+" /t " + Integer.toString(validSec)+" "+cmdShut);   
                }
                else
                {
                    
                    Proc = Runtime.getRuntime().exec("shutdown -a");
                }
                
            } catch (IOException ie)
            {
                JOptionPane.showMessageDialog(null, ie.getMessage());
            }
        } catch (NumberFormatException num)
        {
            JOptionPane.showMessageDialog(null, "Set Time Didn't Valid !, Please Check Again...");
        }
    }
}
