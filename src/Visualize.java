import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Visualize {
   private JFrame mainFrame;
   String input;
   
   public Visualize(String s){
      prepareGUI();
      this.input = s;
   }
   
 
   private void prepareGUI(){
      mainFrame = new JFrame("Execution Sequence");
      mainFrame.setSize(500,500);
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      mainFrame.setVisible(true);  
   }
   
   
   void showJFrameDemo(){
	   	String[] seq = input.split("\n");
		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		// p1.setLayout(new GridLayout(seq.length,1));

		EmptyBorder border = new EmptyBorder(5, 20, 5, 20);
		LineBorder line = new LineBorder(Color.blue, 2, true);
		CompoundBorder compound = new CompoundBorder(line, border);
		// p1.setSize(300,400);
		JLabel labels[] = new JLabel[seq.length];
		ImageIcon image = new ImageIcon(
				"C:\\Users\\Hari Prasad\\Documents\\GitHub\\Visvalization-of-Scala-programs\\images\\smalldarrow.jpg");
		JLabel imagelabel = new JLabel();
		imagelabel.setIcon(image);
		imagelabel.setOpaque(true);
		imagelabel.setBorder(compound);
		for (int i = 0; i < seq.length; i++) {
			labels[i] = new JLabel("", JLabel.CENTER);
			labels[i].setText(seq[i]);
			labels[i].setForeground(Color.BLACK);
			labels[i].setOpaque(true);
			labels[i].setBackground(Color.orange);
			labels[i].setBorder(compound);
			labels[i].setHorizontalAlignment(SwingConstants.CENTER);
			labels[i].setVerticalAlignment(SwingConstants.CENTER);
			labels[i].setAlignmentX(Component.CENTER_ALIGNMENT);
			p1.add(labels[i]);
			p1.add(imagelabel);
		}
		
		mainFrame.add(p1, BorderLayout.CENTER);
		mainFrame.setVisible(true);
   
   }
}
