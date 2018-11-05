
import java.util.regex.Matcher;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.regex.Pattern;
import java.io.*;
import java.nio.file.*;;

public class PatternMatcher1 {
	   private JFrame mainFrame;
	   private JLabel headerLabel;  
	   private JPanel controlPanel;
	   
	   public  PatternMatcher1() {
		      prepareGUI();
		   }
	private void prepareGUI(){
	      mainFrame = new JFrame("Execution trace");
	      mainFrame.setSize(400,400);
	      //mainFrame.setLayout(new GridLayout());
	      
	      mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });    
	      headerLabel = new JLabel("", JLabel.CENTER);        
	      headerLabel.setSize(400,400);
	      headerLabel.setOpaque(true);
	      headerLabel.setBackground(Color.ORANGE);
	      controlPanel = new JPanel();
	      controlPanel.setLayout(new FlowLayout());

	      mainFrame.add(headerLabel);
	      mainFrame.add(controlPanel);
	    
	      mainFrame.setVisible(true);  
	   }
	   private void showJFrameDemo() throws Exception{
		  String[] seq = readFileAsString("C:\\Users\\Hari Prasad\\Documents\\GitHub\\Visvalization-of-Scala-programs\\ExeSeq.txt").split("\n");
	      String s = "<html>";
		  for(int i = 0; i< seq.length;i++)
	      {
	    	  s = s + seq[i] + "<br><br>"; 
	    	  
	      }
		  s = s + "</html>";
		  headerLabel.setText(s); 
	      final JFrame frame = new JFrame();
	      frame.setSize(300, 300);
	      frame.setLayout(new FlowLayout());       
	      
	      frame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            frame.dispose();
	         }        
	      });    
	      mainFrame.setVisible(true);  
	   }
	public static String readFileAsString(String fileName) throws Exception {
		String data = "";
		data = new String(Files.readAllBytes(Paths.get(fileName)));
		return data;
	}

	public static void main(String[] args) throws Exception {
        // Creating a File object that represents the disk file. 
        PrintStream executionSequence = new PrintStream(new File("ExeSeq.txt")); 
  
        // Store current System.out before assigning a new value 
        PrintStream console = System.out; 
  
        // Assign o to output stream 
        System.setOut(executionSequence); 
        
        
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
		PatternMatcher1  swingContainerDemo = new PatternMatcher1();  
        swingContainerDemo.showJFrameDemo();
	}
}
