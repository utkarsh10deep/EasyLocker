/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easylocker;

import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author utkarsh.deep
 */
public class PasswordProtect {
    public static void generatePasswordFile(String password,String fName,String path) throws Exception
    {
		String enPassword = "";
		
        int index=0;
        for(index=fName.length()-1;index>=0;index--)
        {
            if(fName.charAt(index)=='.')
                break; 
            
        }
        String fileName = fName.substring(0, index);
        File file = new File(path+"\\Ch"+fileName+".txt");
        FileWriter fw = new FileWriter(file);
        fw.write(password);
        fw.flush();
        fw.close();
        LockFile.generateLockedFile("Ch"+fileName+".txt",path,path+"\\"+fileName);
        
    }
    
    
    
}
