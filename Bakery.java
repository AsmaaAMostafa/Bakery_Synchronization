/*Sumaya Altamimi
Hala Alajlan
Sara Alobaid
Sara Alhendi
Sultana Almutlaq
Asmaa Mustafa
*/
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Bakery extends Thread {
	
	 
 	  public static LinkedPQ <Long> pq =new LinkedPQ<Long>();
 	  public static LinkedPQ <Bakery> pqTh =new LinkedPQ<Bakery>();

 	public int thread_id;  
	public static final int countT = 1;
	public static int numberOfThreads=0;
	public static volatile int count = 0;  
	public static String output="No output";
   
    
	// Global variables for the bakery's algorithm.
	public static volatile boolean[] choosing;
		public static Bakery[] threads ;  
		public static volatile long[] stD ;  
		public static volatile long[] st2D ;  
 		public static volatile long[] st4D ;  

private static volatile int[] ticket ;
	public static long st;
	public static long st2;
	long busy; 
	public long st3;
	public static long   st4;
	long burst;
	public Bakery(int id) {
		thread_id = id;
 	}

	public void run() {
		
		int scale = 2;

		for (int i = 0; i < countT; i++) {

			lock(thread_id);
			  st2 = System.currentTimeMillis();
              st2D[thread_id]=st2;

			  count++;
              pq.enqueue(st2 , count);
              ///

			  busy= st2-st;
			
			output+="busy waiting for thread: "+thread_id+" is "+busy+" ms\n";
			
			 
 			output+="Time to enter critical section for thread: "+thread_id+" is "+st2+"ms"+"\n";
  				
 				try {
					sleep((int) (Math.random() * scale));
				} catch (InterruptedException e) { /* nothing */ }
				
				  st3 = System.currentTimeMillis();
 
 			unlock(thread_id);
			  st4 = System.currentTimeMillis();
			  
	          st4D[thread_id]=st4;

			  ///
			  count++;
              pq.enqueue(st4 , count);
			  ///
			  
			  
			output+="Time to Enter remainder section for thread: "+thread_id+" is "+st4+" ms"+"\n";
 			  burst=st4-st2;
 			output+="Burst time for thread: "+thread_id+" is "+burst+" ms\n";

			
		}  

	}  

	 
	public void lock(int id) {
 		choosing[id] = true;

 		ticket[id] = findMax() + 1;
		choosing[id] = false;

		  st = System.currentTimeMillis();
         stD[id]=st;

		  count++;
          pq.enqueue(st , count);

		for (int j = 0; j < numberOfThreads; j++) {

 			if (j == id)
				continue;
			
 			while (choosing[j]) { /* nothing */ }
 			

			
			while (ticket[j] != 0 && (ticket[id] > ticket[j] || (ticket[id] == ticket[j] && id > j))) { /* nothing */ }
						 
		} // for
	}

	 
	private void unlock(int id) {
		ticket[id] = 0;
 	}

	 
	private int findMax() {
		
		int m = ticket[0];

		for (int i = 1; i < ticket.length; i++) {
			if (ticket[i] > m)
				m = ticket[i];
		}
		return m;
	}
	
	 

	
public static void method () {
	// Initialization of the global variables (it is not necessary at all).
	output = "";

		for (int i = 0; i < numberOfThreads; i++) {
		choosing[i] = false;
		ticket[i] = 0;
	}
		st4D=new long[numberOfThreads];
		stD=new long[numberOfThreads];
		st2D=new long[numberOfThreads];

		 threads = new Bakery[numberOfThreads]; 

		for (int i = 0; i < threads.length; i++) {
		threads[i] = new Bakery(i);
		threads[i].start();
	}

		for (int i = 0; i < threads.length; i++) {
		try {
			threads[i].join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	 


}

public static void setNumberOfThreads (int num ) {
	numberOfThreads = num;
	choosing = new boolean[numberOfThreads]; 
	 ticket = new int[numberOfThreads]; 
}

public static String getOutput () {
	return output;
	
}

}


/////////////

class LinkedPQ<T> {
	   private int size;
	   private PQNode<T> head;


		/* tail is of no use here. */
	   public LinkedPQ() {
	      head = null;
	      size = 0;
	   }
	   public int length (){
	      return size;
	   }

		

	   public boolean full () {
	      return false;
	   }
	   public void enqueue(T e, int pty) {
	      PQNode<T> tmp = new PQNode<T>(e, pty);
	      if((size == 0) || (pty < head.priority)) {
	         tmp.next = head;
	         head = tmp;
	      }
	      else {
	         PQNode<T> p = head;
	         PQNode<T> q = null;
	         while((p != null) && (pty >= p.priority)) {
	            q = p;
	            p = p.next;
	         }
	         tmp.next = p;
	         q.next = tmp;
	      }
	      size++;
	   }
	   
	   public PQElement<T> serve(){
	      PQNode<T> node = head;
	      PQElement<T> pqe=new PQElement<T>(node.data,(int)node.priority);
	      head = head.next;
	      size--;
	      return pqe;
	   }
	}

	class PQElement<T>
	{
	   public T data;
	   public int p; 
	   public PQElement(T e, int pr){
	      data=e;
	      p=pr;
	   }
	}

	class PQNode<T> {
	   public T data;
	   public int priority;
	   public PQNode<T> next;
		
	   public PQNode() {
	      next = null;
	   }
		
	   public PQNode(T e, int p) {
	      data = e;
	      priority = p;
	   }

		// Setters/Getters?
	}



