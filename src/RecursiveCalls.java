import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecursiveCalls {
	 public static String readFileAsString(String fileName) throws Exception {
	        String data = "";
	        data = new String(Files.readAllBytes(Paths.get(fileName)));
	        return data;
	        
	    }
	 
	 public static void tailRecursive(String fn,String body)throws Exception{
		 Pattern p = Pattern.compile("([a-zA-Z]+\\([^\\)]*\\)(\\.[^\\)]*\\))?)");
         Matcher m = p.matcher(body);
         System.out.print("function->"+fn);
         boolean flag=true;
         while(m.find()) {
             String theGroup = m.group(1);
             if(theGroup.matches("(if).*")&&flag)
             {
            	 System.out.print("  Base condition->"+theGroup);
            	 flag=false;
             }
             if(theGroup.matches("("+fn+").*"))
             {
            	 System.out.print("  Recursive call->"+theGroup);
             }
            
         }
         System.out.println();
    } 
	 
	  public static void findrecursive(String fn,String originalcontent)throws Exception{
			 Pattern p = Pattern.compile("def "+ fn.substring(0, fn.indexOf("(")));
	         Matcher m = p.matcher(originalcontent);
	         while(m.find()) {
	             String theGroup = m.group();
	             if(!theGroup.matches("(while|println|print|for|if).*"))
	             {
	            	String body = (bodycontent(originalcontent,m));
	            	tailRecursive(fn.substring(0, fn.indexOf("(")),body);
	             }
	            
	         }
	  
	  }
	 
	  public static void functioncalls(String data,String originalcontent) throws Exception {
	    	 Pattern p = Pattern.compile("([a-zA-Z]+\\([^\\)]*\\)(\\.[^\\)]*\\))?)");
	         Matcher m = p.matcher(data);
	         while(m.find()) {
	             String theGroup = m.group();
	             if(!theGroup.matches("(while|println|print|for|if).*"))
	             {
	            	 findrecursive(theGroup,originalcontent);
	             }
	            
	         }
	    }
	 
	 public static String bodycontent(String data,Matcher m)throws Exception{
    	 int s=0,e=0,count=0;
         boolean flag=true;
    	 String maincontent=data.substring(m.start());
   	     Pattern ps = Pattern.compile("([{]|([}]))");
         Matcher ms = ps.matcher(maincontent);
         while(ms.find()) {
             String theGroups = ms.group();
             if(theGroups.matches("\\{")&&flag)
             {
           	s=ms.end();
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
           		  e=ms.start();
           		  break;
                 }
             }
         }
         return(maincontent.substring(s,e));
    }
	 
	public static void main(String[] args) throws Exception {
        String data = readFileAsString("C:\\Users\\Hari Prasad\\Desktop\\recursiveSum.txt");
        Pattern p = Pattern.compile("(main(\\S+))");
        Matcher m = p.matcher(data);
        while(m.find()) {
            String theGroup = m.group();
            if(theGroup.matches("(main).*"))
            {
                  functioncalls(bodycontent(data,m),data);
            }
           }
        }
}
