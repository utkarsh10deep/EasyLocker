/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easylocker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 *
 * @author utkarsh.deep
 */
public class LockFile {
    
    //The arguments absolutePath and newAbsolutePath includes directory name to be renamed
	public static void renameDirectory(String absolutePath, String newAbsolutePath) throws Exception
	{
		System.out.println("rename dir called");
		System.out.println(absolutePath);
		System.out.println(newAbsolutePath);
		File f1 = new File(absolutePath);
		File f2 = new File(newAbsolutePath);
		f1.renameTo(f2);
	}
    
    //fName includes extensions like .txt,.mp4,.jpg,.docx etc. 
   public static void lock(String password,String fName, String path) throws Exception
    {
		/*int index=0;
		String path = extractPath(fName);
		fName = extractFileName();
        for(index=fName.length()-1;index>=0;index--)
        {
            if(fName.charAt(index)=='.')
                break; 
            
        }
        String dir = fName.substring(0, index);
		*/
            int index =0;
            for(index=fName.length()-1;index>=0;index--)
            {
                if(fName.charAt(index)=='.')
                break; 
            
            }
            String fileName = fName.substring(0, index);
        File file = new File(path+"\\"+fileName);
        file.mkdir();
        //generateMetaData(EasyLocker.currentUser.email,path+"\\"+fName);
        generateLockedFile(fName,path,path+"\\"+fileName);
        PasswordProtect.generatePasswordFile(password,fName,path);
		
    }
	//the path argument passed here doesn't include the new directory created with name fName
	//fName includes extensions like .txt,.mp4,.jpg,.docx etc. 
    public static void generateLockedFile(String fName,String path,String targetPath) throws Exception
    {
        
     	File file = new File(path+"\\"+fName);
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        for (int readNum; (readNum = fis.read(buf)) != -1;) {
        
            bos.write(buf, 0, readNum); //no doubt here is 0
            //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
            //System.out.println("read " + readNum + " bytes,");
        
        }
        
        
        byte[] bytes = bos.toByteArray();
        fis.close();
        
        
        /*int binaryPassword[][] = new int[password.length()][7];
        for(int i=0;i<password.length();i++)
        {
           int ascii = (int) password.charAt(i);
           int n = ascii;
           int a=0;
           String x="";
           while(n > 0)
        	{
            		a = n % 2;
            		x = x + "" + a;
            		n = n / 2;
        	}
         
           for(int j=0;j<x.length();j++)
           {
               binaryPassword[i][j] = (int) x.charAt(j);
           }
           if(x.length()<7)
                for(int j=x.length();j<7;j++)
                {
                    binaryPassword[i][j] = -1; 
                }
           
        }
	for(int i=0;i<password.length();i++)
	{
		System.out.println("Binary for "+password.charAt(i)+" having ASCII "+(int)password.charAt(i)+": ");
		System.out.print(binaryPassword[i][0]+" ");
		System.out.print(binaryPassword[i][1]+" ");
		System.out.print(binaryPassword[i][2]+" ");
		System.out.print(binaryPassword[i][3]+" ");
		System.out.print(binaryPassword[i][4]+" ");
		System.out.print(binaryPassword[i][5]+" ");
		System.out.println(binaryPassword[i][6]);
		
	}
        */
        
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
        
        
        int quotient = bytes.length/7;
        int remainder = bytes.length%7;
        File fArray[] = new File[8];
        byte bArray[][] = new byte[8][];
        for(int i=0;i<7;i++)
        {
            fArray[i] = new File(targetPath+"\\"+fileName+"EL"+(i+1)+".EasyLocker");
            bArray[i] = new byte[quotient];
            int start = i*quotient;
            for(int j=0;j<quotient;j++)
            {
                bArray[i][j] = bytes[start+j];
            }
            
            
        }
		
        fArray[7] = new File(targetPath+"\\"+fileName+"EL8.EasyLocker");
		if(remainder>0)
		{
			bArray[7] = new byte[remainder];
			for(int j=0;j<remainder;j++)
            {
                bArray[7][j] = bytes[(7*quotient)+j];
            }
			
		}
        
        
        
        
        
        
        
       OutputStream opStream = null; 
       
       int sum=0;
       for(int i=0;i<fName.length();i++)
       {
           sum = sum + (int)fName.charAt(i);
       }
       
       if(sum %2 == 0)
       {
	try {
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
				
                File myFile = fArray[num];
                if (!myFile.exists()) {
                    myFile.createNewFile();
                }
                opStream = new FileOutputStream(myFile);
                opStream.write(bArray[i]);
                opStream.flush();
				opStream.close();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(opStream != null) opStream.close();
            } catch(Exception ex){
                 
            }
        }            
       }
       else if(sum%3==0)
       {
           	try {
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
				
                File myFile = fArray[num];
                if (!myFile.exists()) {
                    myFile.createNewFile();
                }
                opStream = new FileOutputStream(myFile);
                opStream.write(bArray[i]);
                opStream.flush();
				opStream.close();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(opStream != null) opStream.close();
            } catch(Exception ex){
                 
            }
        }
       }
       else if(sum%5==0)
       {
           	try {
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
				
                File myFile = fArray[num];
                if (!myFile.exists()) {
                    myFile.createNewFile();
                }
                opStream = new FileOutputStream(myFile);
                opStream.write(bArray[i]);
                opStream.flush();
				opStream.close();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(opStream != null) opStream.close();
            } catch(Exception ex){
                 
            }
        }
       }
       else 
       {
           	try {
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
				
                File myFile = fArray[num];
                if (!myFile.exists()) {
                    myFile.createNewFile();
                }
                opStream = new FileOutputStream(myFile);
                opStream.write(bArray[i]);
                opStream.flush();
				opStream.close();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(opStream != null) opStream.close();
            } catch(Exception ex){
                 
            }
        }
       }
      
       /*OutputStream opStream = null;
	try {
             
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
				
                File myFile = fArray[num];
                if (!myFile.exists()) {
                    myFile.createNewFile();
                }
                opStream = new FileOutputStream(myFile);
                opStream.write(bArray[i]);
                opStream.flush();
				opStream.close();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(opStream != null) opStream.close();
            } catch(Exception ex){
                 
            }
        }
        */
    
        file.delete();
        
        
    }
        
        
    }
    

