/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easylocker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author utkarsh.deep
 */
public class FileRecovery {
    // argument path is full path where key.txt has to be placed
    public static void enableRecovery(String path,String username) throws IOException
    {
        
        String key = "";
        for(int i=0;i<username.length();i++)
        {
            int temp = (int)username.charAt(i);
            key += temp;
            key += "&";
        }
        File file = new File(path+"\\key.txt");
        FileWriter fw = new FileWriter(file);
        fw.write(key);
        fw.close();
        
    }
    public static void disableRecovery(String path)
    {
        File file = new File(path+"\\key.txt");
        file.delete();
    }
    public static String fetchRecoderyId(String path) throws FileNotFoundException, IOException
    {
        String username="";
        File file = new File(path+"\\key.txt");
        FileReader fr = new FileReader(file);
        String readUsername="";
        int i=0;
        while((i=fr.read())!=-1)
        {
            readUsername += (char)i;
        }
	fr.close();
        for(i=0;i<readUsername.length();i++)
        {
            String str = "";
            while(readUsername.charAt(i)!='&')
            {
                str = str + readUsername.charAt(i);
                i++;
            }
            char ch = (char)Integer.parseInt(str);
            username += ch;
        }
        return username;
        
    }
    /*
    public static boolean otpRecovery()
    {
        boolean retValue = true;
        
        
        return retValue;
    }
    */
    
    public static void generateFile(String fName,String path,String pickupPath) throws Exception
    {
        File fArray[] = new File[8];
        //ArrayList<Byte> bList = new ArrayList<>();
        int index=0;
        for(index=fName.length()-1;index>=0;index--)
        {
            if(fName.charAt(index)=='.')
                break; 
            
        }
       
        String fileName1 = fName.substring(0, index);
        String fileName = "";
		for(int i=0;i<fileName1.length();i++)
		{
			int ch = (int)fileName1.charAt(i);
			
			if((int)'a'<=ch)
			{
				if((int)'z'>=ch)
				{
					ch = ch + 25;
					if(ch>(int)'z')
					{
						ch = ch%27;
						ch = ch+'a';
					}
				}
			}
			else if((int)'A'<=ch)
			{
				if((int)'Z'>=ch)
				{
					ch = ch + 25;
					if(ch>(int)'Z')
					{
						ch = ch%27;
						ch = ch+'a';
					}
				}
			}
			fileName  += (char)ch;
		}
		byte b[][] = new byte[8][];
		int length=0;
   
       int sum=0;
       
      
       for(int i=0;i<fName.length();i++)
       {
           sum = sum + (int)fName.charAt(i);
       }
       if(sum%2==0)
       {
                   for(int i=0;i<8;i++)
        {
			int num = i;
				switch(i)
				{
					case 0: num = 4; break;
					case 1: num = 3; break;
					case 2: num = 6; break;
					case 3: num = 1; break;
					case 4: num = 0; break;
					case 5: num = 7; break;
					case 6: num = 2; break;
					case 7: num = 5; break;
					default: break;
				}
            File file = new File(pickupPath+"\\"+fileName+"EL"+(num+1)+".EasyLocker");
            fArray[i] = file;
            FileInputStream fis = null;
            b[i] = new byte[(int) file.length()];
			length = length + b[i].length;
            try
            {
                fis = new FileInputStream(file);
                fis.read(b[i]);
                fis.close();
				
            
            }
            catch(IOException ioExp)
            {
                ioExp.printStackTrace();
            }
        }
       }
       else if(sum%3==0)
       {
                   for(int i=0;i<8;i++)
        {
			int num = i;
				switch(i)
				{
					case 0: num = 6; break;
					case 1: num = 5; break;
					case 2: num = 7; break;
					case 3: num = 4; break;
					case 4: num = 3; break;
					case 5: num = 1; break;
					case 6: num = 0; break;
					case 7: num = 2; break;
					default: break;
				}
            File file = new File(pickupPath+"\\"+fileName+"EL"+(num+1)+".EasyLocker");
            fArray[i] = file;
            FileInputStream fis = null;
            b[i] = new byte[(int) file.length()];
			length = length + b[i].length;
            try
            {
                fis = new FileInputStream(file);
                fis.read(b[i]);
                fis.close();
				
            
            }
            catch(IOException ioExp)
            {
                ioExp.printStackTrace();
            }
        }
       }
       else if(sum%5==0)
       {
                   for(int i=0;i<8;i++)
        {
			int num = i;
				switch(i)
				{
					case 0: num = 7; break;
					case 1: num = 6; break;
					case 2: num = 3; break;
					case 3: num = 2; break;
					case 4: num = 5; break;
					case 5: num = 4; break;
					case 6: num = 1; break;
					case 7: num = 0; break;
					default: break;
				}
            File file = new File(pickupPath+"\\"+fileName+"EL"+(num+1)+".EasyLocker");
            fArray[i] = file;
            FileInputStream fis = null;
            b[i] = new byte[(int) file.length()];
			length = length + b[i].length;
            try
            {
                fis = new FileInputStream(file);
                fis.read(b[i]);
                fis.close();
				
            
            }
            catch(IOException ioExp)
            {
                ioExp.printStackTrace();
            }
        }
       }
       else
       {
                   for(int i=0;i<8;i++)
        {
			int num = i;
				switch(i)
				{
					case 0: num = 6; break;
					case 1: num = 5; break;
					case 2: num = 7; break;
					case 3: num = 4; break;
					case 4: num = 3; break;
					case 5: num = 1; break;
					case 6: num = 0; break;
					case 7: num = 2; break;
					default: break;
				}
            File file = new File(pickupPath+"\\"+fileName+"EL"+(num+1)+".EasyLocker");
            fArray[i] = file;
            FileInputStream fis = null;
            b[i] = new byte[(int) file.length()];
			length = length + b[i].length;
            try
            {
                fis = new FileInputStream(file);
                fis.read(b[i]);
                fis.close();
				
            
            }
            catch(IOException ioExp)
            {
                ioExp.printStackTrace();
            }
        }
       }
		
		byte bArray[] = new byte[length];
		index=0;
		for(int i=0;i<b.length;i++)
		{
			for(int j=0;j<b[i].length;j++)
			{
				bArray[index++] = b[i][j];
			}
			
		}
		/*
        //password verification
        for(int i=0;i<password.length();i++)
        {
            int givenASCII = (int) password.charAt(i);
            String binary = "";
            for(int j=0;j<7;j++)
            {
                if(bArray[j][i] == (byte)-1)
                {
                    break;
                }
                binary = binary+(char)bArray[j][i]; 
            }
            int computedASCII = Integer.parseInt(binary,2);
            //if(computedASCII != givenASCII)
                //return false;
            
        }
        int arrLen=0;
        for(int i=0;i<7;i++)
        {
            arrLen = arrLen + ((bArray[i].length) - (password.length()));
        }
        byte bytes[] = new byte[arrLen];
        index=0;
        for(int i=0;i<7;i++)
        {
            for(int j=password.length();j<bArray[i].length;j++)
            {
                bytes[index++] = bArray[i][j];
            }
        }
        */
        //A multiline comment ended here previously
        
        
        
        
        
        
       
        for(int i=0;i<8;i++)
        {
            
            fArray[i].delete();
        
        }
        
        File f = new File(path+"\\"+fName);
        f.delete();
        System.out.println("Path to watch: "+path+"\\"+fName);
        File someFile = new File(path+"\\"+fName);
        //someFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(someFile);
        fos.write(bArray);
        fos.flush();
        fos.close();
        
//        for(int i=0;i<8;i++)
//        {
//            
//            fArray[i].delete();
//        
//        }
//        
        
        
        
        
        
        
       
    }
    
    //path is full path
    public static void recoverFile(String path) throws Exception
    {
        /*
        if(!otpRecovery())
        {
            System.out.println("can't recover file because of incorrect otp!");
            return;
        }
        */
        System.out.println("path= "+path);
        String fName ="";
        String parentPath = "";
        int i=0,index=0;
        for(i=path.length()-1;i>=0;i--)
        {
            if(index==0)
            {
                if(path.charAt(i)=='.')
                {
                    index = i;
                }
            }
            if(path.charAt(i)=='\\')
            {
                break;
            }
        }
        fName = path.substring(i+1);
        parentPath = path.substring(0,i);
        String targetFile = path.substring(i+1);
        
        System.out.println("fName length: "+fName.length());
        System.out.println("path: "+path);
        
        //File fArray[] = new File[8];
        //ArrayList<Byte> bList = new ArrayList<>();
        index=0;
        for(index=fName.length()-1;index>=0;index--)
        {
            if(fName.charAt(index)=='.')
                break; 
            
        }
        String fileName1 = fName.substring(0, index);
        fileName1 = "Ch"+fileName1;
        String fileName = "";
		for(i=0;i<fileName1.length();i++)
		{
			int ch = (int)fileName1.charAt(i);
			
			if((int)'a'<=ch)
			{
				if((int)'z'>=ch)
				{
					ch = ch + 25;
					if(ch>(int)'z')
					{
						ch = ch%27;
						ch = ch+'a';
					}
				}
			}
			else if((int)'A'<=ch)
			{
				if((int)'Z'>=ch)
				{
					ch = ch + 25;
					if(ch>(int)'Z')
					{
						ch = ch%27;
						ch = ch+'a';
					}
				}
			}
			fileName  += (char)ch;
		}
                for(i=0;i<8;i++)
                {
                    File file = new File(path+"\\"+fileName+"EL"+(i+1)+".EasyLocker");
                    file.delete();
                }
                File f = new File(path+"\\"+"key.txt");
                f.delete();
                generateFile(fName, parentPath, path);
                //File folder = new File(path);
                //folder.delete();
        /*
        //call UnlockFile to unlock the CH<file>.txt file to fetch the password and then call UnlockFile again to recover the file.
        String fileName = "Ch"+fName+".txt";
        UnlockFile.generateUnlockedFile(fileName,parentPath,path);
        File file = new File(parentPath+"\\"+fileName);
        FileReader fr = new FileReader(file);
        String readPassword="";
        i=0;
        while((i=fr.read())!=-1)
        {
            readPassword += (char)i;
        }
	fr.close();
        file.delete();
        UnlockFile.generateUnlockedFile(targetFile,parentPath,path);
        disableRecovery(path);
        File myFolder = new File(path);
        myFolder.delete();
        */
    }
}
