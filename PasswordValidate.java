/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easylocker;

import java.io.File;
import java.io.FileReader;

/**
 *
 * @author utkarsh.deep
 */
public class PasswordValidate {
public static boolean validate(String password,String fName,String path) throws Exception
    {
        System.out.println("HOLA_VALIDATE\n"+path+"\n"+fName);
		int index=0;
        for(index=fName.length()-1;index>=0;index--)
        {
            if(fName.charAt(index)=='.')
                break; 
            
        }
        String fileName = fName.substring(0, index);
		String dir = fileName;
        fileName = "Ch"+fileName+".txt";
        UnlockFile.generateUnlockedFile(fileName,path,path+"\\"+dir);
        File file = new File(path+"\\"+fileName);
        
        if(!file.exists())
        {
            System.out.println("Password file nai hai");
            return false;
        }
        else
        {
            System.out.println("password file hai");
        }
        //System.out.println(path+"\\"+fileName);
        FileReader fr = new FileReader(file);
        String readPassword="";
        int i=0;
        while((i=fr.read())!=-1)
        {
            readPassword += (char)i;
        }
		fr.close();
        if(password.equals(readPassword))
        {
			file.delete();
			
            return true;
        }
        else
        {
                LockFile.generateLockedFile(fileName,path,path+"\\"+dir);
		file.delete();
                System.out.println("Actual Password: "+readPassword);
                System.out.println("entered password: "+password);
                System.out.println("Password NOT CORRECT");
                return false;
        }
    }
}
