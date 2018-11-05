import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Imperative {
	static String originalcontent="";
	public static String readFileAsString(String fileName) throws Exception {
		originalcontent = new String(Files.readAllBytes(Paths.get(fileName)));
        return originalcontent;
    }
	 public static void loopcontent(String data,String head,String fname)throws Exception{
		 String condition="";
		 boolean find;
		 head = head.substring(head.indexOf("(") + 1);
		 head = head.substring(0, head.indexOf(")"));
		 condition=head;
		 System.out.print("Iterative condition: "+ condition+"{");
		 find=functioncalls(data,fname);
		 if(!find)
		 {
             System.out.print("NIL");
		 }		
		 System.out.println("}");

	 }
	 
	 public static String findfn(String fn)throws Exception{
		 Pattern p = Pattern.compile("def "+ fn.substring(0, fn.indexOf("(")));
         Matcher m = p.matcher(originalcontent);
         while(m.find()) {
             String theGroup = m.group();
             if(!theGroup.matches("(while|println|print|for|if).*"))
             {
            	String body = (bodycontent(originalcontent,m));
            	return body;
            	//tailRecursive(fn.substring(0, fn.indexOf("(")),body);
             }
            
         }
        return ""; 
	 }
	 
	 public static boolean checktailRecursive(String fn,String body)throws Exception{
		 Pattern p = Pattern.compile("([a-zA-Z]+\\([^\\)]*\\))");
         Matcher m = p.matcher(body);
         boolean tail=false;
         while(m.find()) {
             String theGroup = m.group(1);
             if(theGroup.matches("("+fn+").*"))
             {
            	 tail=true;
             }
            
         }
         return tail;
    } 
	 
	 
	 public static void tailRecursive(String fn,String body)throws Exception{
		 Pattern p = Pattern.compile("([a-zA-Z]+\\([^\\)]*\\))");
         Matcher m = p.matcher(body);
         System.out.print("function->"+fn);
         boolean flag=true;
         while(m.find()) {
             String theGroup = m.group(1);
             if(theGroup.matches("(if).*")&&flag)
             {
            	 System.out.print("  Base condition->"+theGroup);
            	 flag=false;
            	 break;
             }
             
             if(theGroup.matches("("+fn.substring(0, theGroup.indexOf("("))+").*"))
             {
            	 System.out.print("  Recursive call->"+theGroup);
             }
             else if(!theGroup.matches("(while|println|print|for|if).*"))
             {
            	 functioncalls(theGroup,fn);
            	 System.out.println(fn);
             }
            
         }
         System.out.println();
    } 
	 
    public static boolean functioncalls(String data,String fname) throws Exception {
    	 boolean find=false;
    	 Pattern p = Pattern.compile("([a-zA-Z]+\\([^\\)]*\\))");
         Matcher m = p.matcher(data);
         while(m.find()) {
             String theGroup = m.group(1);
             if(theGroup.matches("(while|for).*"))
             {       
            	 String loopbody=bodycontent(data,m);
            	 loopcontent(loopbody,theGroup,fname); 
            	 data = data.substring(m.end()+loopbody.length());
            	 m = p.matcher(data);
             }
             else if(theGroup.matches("(println|print|if).*"))
             {
            	 theGroup = theGroup.substring(theGroup.indexOf("(") + 1);
            	 theGroup = theGroup.substring(0, theGroup.length()-1);
            	 functioncalls(theGroup,fname);
             }
             else if(checktailRecursive(theGroup.substring(0, theGroup.indexOf("(")),findfn(theGroup)))
             {
            	 tailRecursive(theGroup,findfn(theGroup));
            	 System.out.println(fname);
             }
             else
             {	
            	 System.out.println(theGroup);
            	 functioncalls(findfn(theGroup),theGroup);
            	 System.out.println(fname);
            	 find=true;            	 
             }
          }
         return find;      
        }
    
    public static String bodycontent(String data,Matcher m)throws Exception{
    	 int s=0,e=0,count=0;
         boolean flag=true;
    	 String maincontent=data.substring(m.start());
   	     Pattern ps = Pattern.compile("([{]|([}]))");
         Matcher ms = ps.matcher(maincontent);
         while(ms.find()) {
             String theGroups = ms.group(1);
             if(theGroups.matches("\\{")&&flag)
             {
           	s=ms.start();
           	count+=1;
           	
           	flag=false;
             }
             else if(theGroups.matches("\\{"))
             {
           	 count+=1; 
             }
             else if(theGroups.matches("\\}"))
             {
           	  count-=1;
           	  if(count==0)
           	  {
           		  e=ms.end();
           		  break;
                 }
             }
         }
         return(maincontent.substring(s,e));
    }

    public static void main(String[] args) throws Exception {
        String data = readFileAsString("C:\\Users\\Hari Prasad\\Desktop\\loopfunction.txt");
        PrintStream executionSequence = new PrintStream(new File("ExeSeq.txt"));
        System.setOut(executionSequence);
        Pattern p = Pattern.compile("(main\\([^\\)]*\\))");
        Matcher m = p.matcher(data);
        while(m.find()) {
        		  System.out.println(m.group());
                  functioncalls(bodycontent(data,m),m.group()); 
            }
        String out = readFileAsString(
				"C:\\Users\\Hari Prasad\\Documents\\GitHub\\Visvalization-of-Scala-programs\\ExeSeq.txt");
        Visualize swingContainerDemo = new Visualize(out);  
        swingContainerDemo.showJFrameDemo();
        }
    	
}
