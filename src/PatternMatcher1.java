
import java.util.regex.Matcher;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.util.regex.Pattern;
import java.io.*;
import java.nio.file.*;;

public class PatternMatcher1 {
	private JFrame mainFrame;
	// private JLabel headerLabel;
	private JPanel controlPanel;

	public PatternMatcher1() {
		prepareGUI();
	}

	private void prepareGUI() {
		mainFrame = new JFrame("Execution trace");
		mainFrame.setSize(1000,1000);
		// mainFrame.setLayout(new GridLayout());

		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		mainFrame.setVisible(true);
	}

	private void showJFrameDemo() throws Exception {
		String[] seq = readFileAsString(
				"C:\\Users\\Hari Prasad\\Documents\\GitHub\\Visvalization-of-Scala-programs\\ExeSeq.txt").split("\n");
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
		final JFrame frame = new JFrame();
		frame.setSize(300, 300);
		frame.setLayout(new FlowLayout());

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				frame.dispose();
			}
		});
		mainFrame.add(p1, BorderLayout.CENTER);
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
		while (m.find()) {
			String theGroup = m.group();
			if (!theGroup.matches("(while|for|if|Array).*")) {
				System.out.println(theGroup);
			}
		}
		PatternMatcher1 swingContainerDemo = new PatternMatcher1();
		swingContainerDemo.showJFrameDemo();
	}
}
