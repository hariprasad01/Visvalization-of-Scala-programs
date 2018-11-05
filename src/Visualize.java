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
		JLabel labels[] = new JLabel[(seq.length)*2];
		ImageIcon image = new ImageIcon(
				"C:\\Users\\Hari Prasad\\Documents\\GitHub\\Visvalization-of-Scala-programs\\images\\tinyArrow.png");
		boolean flag = true;
		for (int i = 0, j = 0; i < (seq.length)*2; i++) {
			if(flag)
			{
				
				labels[i] = new JLabel("", JLabel.CENTER);
				labels[i].setText(seq[j]);
				labels[i].setForeground(Color.BLACK);
				labels[i].setOpaque(true);
				labels[i].setBackground(Color.orange);
				labels[i].setBorder(compound);
				labels[i].setHorizontalAlignment(SwingConstants.CENTER);
				labels[i].setVerticalAlignment(SwingConstants.CENTER);
				labels[i].setAlignmentX(Component.CENTER_ALIGNMENT);
				p1.add(labels[i]);
				j++;
				flag = false;
			}
			else
			{
				labels[i] = new JLabel("", JLabel.CENTER);
				labels[i].setOpaque(true);
				labels[i].setIcon(image);
				labels[i].setSize(1, 1);
				labels[i].setHorizontalAlignment(SwingConstants.CENTER);
				labels[i].setVerticalAlignment(SwingConstants.CENTER);
				labels[i].setAlignmentX(Component.CENTER_ALIGNMENT);
				p1.add(labels[i]);
				flag = true;
			}
				
		}
		/*JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(50, 30, 300, 500);
        p1.add(scrollPane);*/
        mainFrame.setContentPane(p1);
        mainFrame.pack();
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setVisible(true);
		//mainFrame.add(p1, BorderLayout.CENTER);
		mainFrame.setVisible(true);
   
   }
}
