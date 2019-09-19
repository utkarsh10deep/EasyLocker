/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easylocker;
import java.io.*;
/**
 *
 * @author utkarsh.deep
 */
public class AccessRights {
    public static String fetchUserName() throws Exception
    {
        
                Process p=Runtime.getRuntime().exec("whoami"); 
                p.waitFor(); 
                BufferedReader reader=new BufferedReader(
                    new InputStreamReader(p.getInputStream())
                ); 
                String line = reader.readLine();
                return line;
                

           
    }
            // the argument "folder" will have absolute path specified
    public static void denyAccess(String folder) throws Exception
    {
        String drive = "";
        for(int i=0;i<folder.length();i++)
        {
            if(folder.charAt(i)=='\\')
            {
                drive = folder.substring(0, i);
                break;
            }
        }
        String directory = "";
        String fileName = "";
        for(int i=folder.length()-1;i>=0;i--)
        {
            if(folder.charAt(i)=='\\')
            {
                fileName = folder.substring(i+1);
                directory = folder.substring(0,i);
                break;
            }
        }
        String user = fetchUserName();
        Runtime r = Runtime.getRuntime();
	r.exec("cmd /c start cmd.exe /K \""+drive+"&&cd "+directory+"&&icacls \""+fileName+"\" /deny "+user+":W&&exit\"");
        r.exec("cmd /c start cmd.exe /K \""+drive+"&&cd "+directory+"&&icacls \""+fileName+"\" /deny "+user+":R&&exit\"");
        
       /* 
        Process p = Runtime.getRuntime().exec(drive);
        p.waitFor();
        p = Runtime.getRuntime().exec("cd "+directory);
        
        
        p=Runtime.getRuntime().exec("icacls "+fileName+" /deny "+user+":W"); 
        p.waitFor(); 
        p = Runtime.getRuntime().exec("icacls "+fileName+" /deny "+user+":R");
        p.waitFor();
        */
    }
            // the argument "folder" will have absolute path specified
    public static void grantAccess(String folder) throws Exception
    {
        String drive = "";
        for(int i=0;i<folder.length();i++)
        {
            if(folder.charAt(i)=='\\')
            {
                drive = folder.substring(0, i);
                break;
            }
        }
        String directory = "";
        String fileName = "";
        for(int i=folder.length()-1;i>=0;i--)
        {
            if(folder.charAt(i)=='\\')
            {
                fileName = folder.substring(i+1);
                directory = folder.substring(0,i);
                break;
            }
        }
        String user = fetchUserName();
        Runtime r = Runtime.getRuntime();
	r.exec("cmd /c start cmd.exe /K \""+drive+"&&cd "+directory+"&&icacls \""+fileName+"\" /grant "+user+":R&&exit\"");
        r.exec("cmd /c start cmd.exe /K \""+drive+"&&cd "+directory+"&&icacls \""+fileName+"\" /grant "+user+":W&&exit\"");
    }
    
}
