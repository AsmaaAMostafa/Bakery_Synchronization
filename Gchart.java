/*Sumaya Altamimi
Hala Alajlan
Sara Alobaid
Sara Alhendi
Sultana Almutlaq
Asmaa Mustafa
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

 
public class Gchart extends JFrame {
   LinkedPQ<Long> pq;
   private JPanel contentPane;
  int count=0;
   JPanel panel; 
   JScrollPane scrollPane ;
   int last;
   
   public  Gchart(LinkedPQ<Long> pq){
      JFrame frame = new JFrame();
      FlowLayout f =  new FlowLayout();
      f.setHgap(0);
      panel = new JPanel(f);
    //  panel.setBounds(200, 200, 100, 100);

   
      this.pq=pq;
      scrollPane = new JScrollPane(panel);
      scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);      scrollPane.setBounds(10, 30, 360, 180);     //FlowLayout d =  new FlowLayout();
      JPanel contentPane = new JPanel(null);
      contentPane.setPreferredSize(new Dimension(200, 200));
      contentPane.add(scrollPane);
      this.setContentPane(contentPane);
      last=0;
      
        /// for loop
      String text ="<html>";
      
      for(int i=0;i<Bakery.numberOfThreads;i++) {
    	  
    	  text+= "<div><span>Thread no: "+i+"</span>";
   	     
    		Long first=pq.serve().data ;
    	      if(Bakery.stD[i]!=0) {
    	    	  count++;
    	           
    	    	  text+= " <span style='background-color:White'> waitnig "+ Bakery.stD[i]+" ms </span>";
 
    	       pq.enqueue(Bakery.stD[i], count); 
    	      }
    	      
    	      if(Bakery.st2D[i]!=0)
    	      {
    	    	  count++;
    	    	  text += "<span style='background-color:#FF00FF'> in critical "+ Bakery.st2D[i]+" ms </span>";
    	    	   
 
    	       pq.enqueue(Bakery.st2D[i], count); 
    	      }
    	      
    	      if(Bakery.st4D[i]!=0)
    	      {
    	    	  count++;
    	    	  text+= "<span style='background-color:#00FF00' > in remainder "+ Bakery.st4D[i]+" ms</span></div><br>";
    	    	   

 
    	       pq.enqueue(Bakery.st4D[i], count); 
    	      }
    	      
    	      
     	     
              
     	   }
      JLabel l = new JLabel();
      text+="</html>";
      l.setText(text);
      panel.add(l);

      }
   
      
       
	
	   


	/**
	 * Launch the application.
	 */
   public static void main(String[] args) {
      EventQueue.invokeLater(
         new Runnable() {
            public void run() {
               try {
                  Gchart frame = new Gchart();
                  frame.setVisible(true);
               } 
               catch (Exception e) {
                  e.printStackTrace();
               }
            }
         });
   }

	/**
	 * Create the frame.
	 */
   public Gchart() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 450, 300);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      contentPane.setLayout(new BorderLayout(0, 0));
      setContentPane(contentPane);
   }

}
