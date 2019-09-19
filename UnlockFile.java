/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easylocker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author utkarsh.deep
 */
public class UnlockFile {
        public static boolean generateUnlockedFile(String fName,String path,String pickupPath) throws Exception
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
        
        
        
        
        
        
        //System.out.println("Path to watch: "+path+"\\"+fName);
        
        File someFile = new File(path+"\\"+fName);
        //someFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(someFile);
        fos.write(bArray);
        fos.flush();
        fos.close();
        
        for(int i=0;i<8;i++)
        {
            
            fArray[i].delete();
        
        }
        
        
        
        
        
        
        
       return true; 
    }
}
