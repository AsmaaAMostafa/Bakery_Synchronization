/*Sumaya Altamimi
Hala Alajlan
Sara Alobaid
Sara Alhendi
Sultana Almutlaq
Asmaa Mustafa
*/

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;
import java.awt.Font;

public class Frame extends JFrame{

		//JFrame frame;
		private JTextField textField;
		private String output="No Output";
		private int numOfThreads=4;
		private JButton btnRun;
		private JTextArea textArea_1;
		private JButton btnGantt;
		
		/**
		 * Create the application.
		 */
		
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Frame frame = new Frame();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		public Frame() {
			initialize();
		}

		/**
		 * Initialize the contents of the frame.
		 */
		private void initialize() {
			setBounds(100, 100, 450, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			getContentPane().setLayout(null);
			
			JLabel lblEnter = new JLabel("Enter number of Threads");
			lblEnter.setBounds(42, 25, 166, 16);
			getContentPane().add(lblEnter);
			
			textField = new JTextField();
			textField.setBounds(220, 20, 90, 26);
			
			getContentPane().add(textField);
			textField.setColumns(10);
			JLabel lblOutput = new JLabel("Output : ");
			lblOutput.setBounds(42, 63, 61, 16);
			getContentPane().add(lblOutput);
			
			
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(36, 81, 393, 137);
			getContentPane().add(scrollPane);
			
			 textArea_1 = new JTextArea();
			textArea_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
			textArea_1.setEditable(false);
			textArea_1.setLineWrap(true);
			scrollPane.setViewportView(textArea_1);
			
			 btnRun = new JButton("Run");
			btnRun.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {		
					if(!(textField.getText().equals("")))
               //bakery
					numOfThreads = Integer.parseInt(textField.getText());
					Bakery.setNumberOfThreads(numOfThreads);
					Bakery.method();
             output = Bakery.getOutput();
             //bakery

					textArea_1.setText(output);
				}
			});
			btnRun.setBounds(346, 21, 83, 26);
			getContentPane().add(btnRun);
			
			JButton btnChart = new JButton("Get Chart");
			
			btnChart.addActionListener(
			         new ActionListener() {
			            public void actionPerformed(ActionEvent arg0) {
			            	if(Bakery.numberOfThreads==0)
				               {
				                  JOptionPane.showMessageDialog(null, "there is no thread entered");	
				               
				               }
				               else
				               {
				                  Gchart g = new Gchart(Bakery.pq);
				                  g.setVisible(true);
				                  g.setSize(400, 300);
				               }
			            }
			         
			         });
			btnChart.setBounds(73, 233, 100, 26);
			getContentPane().add(btnChart);
			
		}
		
		public void setOutput (String output) {
			this.output = output;
		}
		public int getNumberOfThreads () {
			return numOfThreads;
		}
}
