

import java.nio.file.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcher2{
    public static String readFileAsString(String fileName) throws Exception {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }
    public static void calls(String data) throws Exception {
         Pattern p = Pattern.compile("([a-zA-Z]+\\([^\\)]*\\)(\\.[^\\)]*\\))?)");
         Matcher m = p.matcher(data);
         while(m.find()) {
             String theGroup = m.group();
             if(!theGroup.matches("(while|println|print|for|if|Array).*"))
             {
                 System.out.println(theGroup);
             }
            
         }
    }

    public static void main(String[] args) throws Exception {
        String data = readFileAsString("C:\\Users\\Hari Prasad\\Desktop\\test.txt");
        Pattern p = Pattern.compile("(main)");
        Matcher m = p.matcher(data);
        int s=0,e=0,count=0;
        boolean flag=true;
        while(m.find()) {
            String theGroup = m.group();
            //System.out.println(theGroup);
            if(theGroup.matches("main(.*)"))
            {
                  String maincontent=data.substring(m.end());
                  Pattern ps = Pattern.compile("([{]|([}]))");
                  Matcher ms = ps.matcher(maincontent);
                  while(ms.find()) {
                      String theGroups = ms.group();
                      //System.out.println(theGroups);
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
                              e=ms.end();
                         
                          }
                      }
                  }
                  calls(maincontent.substring(s,e));
              }
            }
        }
    }





