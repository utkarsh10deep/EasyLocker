/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easylocker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author utkarsh.deep
 */
public class Utilities {
        public static boolean changePassword(String userName, String password) throws IOException
        {
            //String userName = EasyLocker.currentUser.email;
        String dir = System.getProperty("user.dir");
        String pass = Utilities.hashCode(password);
        pass = Utilities.encryptPassword(pass);
        File f1 = new File(dir+"\\metadata\\users\\"+userName+".txt");
        if(!f1.delete())
        {
            return false;
        }
        File f2 = new File(dir+"\\metadata\\users\\"+userName+".txt");
        FileWriter fw = new FileWriter(f2);
        fw.write(pass);
        fw.close();
        
        
        return true;
        }
        public static String decryptPassword(String password)
        {
            String pass = "";
            
            
            
            for(int i=0;i<password.length();i++)
            {
                
                String dig = Character.toString(password.charAt(i));
            int digit = Integer.parseInt(dig);
                switch(digit)
                {
                    case 0: pass+='5';
                            break;
                    case 1: pass+='9';
                            break;
                    case 2: pass+='6';
                            break;
                    case 3: pass+='8';
                            break;
                    case 4: pass+='7';
                            break;
                    case 5: pass+='0';
                            break;
                    case 6: pass+='2';
                            break;
                    case 7: pass+='4';
                            break;
                    case 8: pass+='3';
                            break;
                    case 9: pass+='1';
                            break;
                    default: break;
                }
            }
        
            return pass;
        }
    
    
    public static String encryptPassword(String password)
    {
        String pass = "";
        for(int i=0;i<password.length();i++)
        {
            String dig = Character.toString(password.charAt(i));
            int digit = Integer.parseInt(dig);
            switch(digit)
            {
                case 0: pass+='5';
                        break;
                case 1: pass+='9';
                        break;
                case 2: pass+='6';
                        break;
                case 3: pass+='8';
                        break;
                case 4: pass+='7';
                        break;
                case 5: pass+='0';
                        break;
                case 6: pass+='2';
                        break;
                case 7: pass+='4';
                        break;
                case 8: pass+='3';
                        break;
                case 9: pass+='1';
                        break;
                default: break;
            }
        }
        
        return pass;
    }
    public static boolean checkInternetConnection()
    {
        try 
		{
			URL url = new URL("http://www.google.com");
 
			URLConnection connection = url.openConnection();
			connection.connect();   
 
			System.out.println("Internet Connected");
                        return true;
            
		}
		catch (Exception e){
     
			System.out.println("Sorry, No Internet Connection");     
                        return false;                                    
		} 
    }
    public static boolean emailFieldCheck(String email)
    {
        boolean ret=false;
        for(int i=0;i<email.length();i++)
        {
            if(email.charAt(i)=='@')
            {
                ret = true;
                break;
            }
            
        }
        return ret;
    }
    public static boolean createUser(String username,String password,String name) throws Exception
    {
        
        //String pass = hashCode(password);
        String pass = encryptPassword(password);
        String curDir = System.getProperty("user.dir");
        
        
        String userDir = curDir+"\\metadata\\users";
        File file= new File(userDir+"\\"+username+".txt");
        if(file.exists())
        {
           //file.delete();
            System.out.println("User already exists!");
           return false;
        }
        else
        {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        fw.write(pass);
        System.out.println("Password written into users directory:\n"+pass);
        fw.close();
        
        String userDataDir = curDir+"\\metadata\\userData";
        file = new File(userDataDir+"\\"+username+".txt");
        if(file.exists())
        {
            System.out.println("User data already exists");
            return false;        
        }
        else
        {
            file.createNewFile();
        }
        fw = new FileWriter(file);
        fw.write(name+"\n"+username);
        fw.close();
        
        return true;
    }
    public static String fetchName(String username) throws Exception
    {
        String dir = System.getProperty("user.dir");
        dir = dir+"\\metadata\\userData\\"+username+".txt";
        File file = new File(dir);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String name = reader.readLine();
        reader.close();
        return name;
    }
    public static boolean loginCheck(String username,String password) throws FileNotFoundException, IOException
    {
        //String pass = hashCode(password);
        String pass = decryptPassword(password);
        String curDir = System.getProperty("user.dir");
        curDir += "\\metadata\\users";
        File file = new File(curDir+"\\"+username+".txt");
        if(!file.exists())
        {
            System.out.println("User doesn't exists!");
            return false;
        }
        FileReader fr = new FileReader(file);
        String value="";
        int i=0;
        while((i=fr.read())!=-1)
        {
            value = value+(char)i;
        }
        fr.close();
        if(pass.equals(value))
        {
            return true;
        }
        else
        {
            System.out.println("Incorrect Password");
            return false;
        }
    }
    public static String hashCode(String str)
    {
        String retHash = "";
        
        for (int i = 0; i < str.length(); i++) {
            int hash = 7;
            char ch = str.charAt(i);
            String temp = ""+ch+ch+ch;
            for(int j=0;j<temp.length();j++)
                hash = (hash*17) + temp.charAt(j);
            retHash += ""+hash;
        }
        return retHash;

    }
    public static String generateOTP()
    {
        String retOTP="";
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	Date date = new Date();
	//System.out.println(dateFormat.format(date));
        String str = dateFormat.format(date);
        //System.out.println(str);
        int num=0;
        for(int i=0;i<str.length();i++)
        {
            int n = str.charAt(i);
            num = num + n;
        }
        retOTP += num%5;
        retOTP += num%6;
        retOTP += num%7;
        retOTP += num%8;
        
        
        return retOTP;
    }
    /*
    public static void main(String []args)
    {
        String str = "Hello@123#hjagdjavaJHDABDNAM ANBDJABDjAJKDHakjhkhdahac";
        System.out.println(str);
        System.out.println("Hash: "+hashCode(str));
        System.out.println("OTP = "+generateOTP());
    }
    */
}
