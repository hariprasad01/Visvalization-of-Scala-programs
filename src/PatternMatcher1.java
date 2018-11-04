
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.nio.file.*;;

public class PatternMatcher1 {
	public static String readFileAsString(String fileName) throws Exception {
		String data = "";
		data = new String(Files.readAllBytes(Paths.get(fileName)));
		return data;
	}

	public static void main(String[] args) throws Exception {
		String data = readFileAsString("C:\\Users\\Hari Prasad\\Desktop\\test.txt");
		Pattern p = Pattern.compile("([a-zA-Z]+\\([^\\)]*\\)(\\.[^\\)]*\\))?)"); 
		Matcher m = p.matcher(data);
		while(m.find()) {
			String theGroup = m.group();
			 if(!theGroup.matches("(while|for|if|Array).*"))
             {
                 System.out.println(theGroup);
             }
		}
	}
}
