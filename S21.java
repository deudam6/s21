import java.util.Iterator; 
import java.util.LinkedList; 
import java.util.Scanner; 
public class SubjectNames { 
 public static void main(String[] args) { 
 Scanner sc = new Scanner(System.in); 
 System.out.print("Enter the number of subjects: "); 
 int n = sc.nextInt(); 
 LinkedList<String> subjects = new LinkedList<String>(); 
 System.out.println("Enter the subject names:"); 
 for (int i = 0; i < n; i++) { 
 String subject = sc.next(); 
 subjects.add(subject); 
 } 
 System.out.println("Subject names:"); 
 Iterator<String> iterator = subjects.iterator(); 
 while (iterator.hasNext()) { 
 String subject = iterator.next(); 
 System.out.println(subject); 
 } 
 } 
} 
2.o/p
import java.util.LinkedList; 
public class ProducerConsumer { 
 public static void main(String[] args) { 
 LinkedList<Integer> buffer = new LinkedList<>(); 
 int bufferSize = 5; 
 Thread producerThread = new Thread(new Producer(buffer, bufferSize), 
"Producer"); 
 Thread consumerThread = new Thread(new Consumer(buffer), 
"Consumer"); 
 producerThread.start(); 
 consumerThread.start(); 
 } 
} 
class Producer implements Runnable { 
 private final LinkedList<Integer> buffer; 
 private final int bufferSize; 
 private int value = 0; 
 public Producer(LinkedList<Integer> buffer, int bufferSize) { 
 this.buffer = buffer; 
 this.bufferSize = bufferSize; 
 } 
 @Override 
 public void run() { 
 while (true) { 
 synchronized (buffer) { 
 if (buffer.size() < bufferSize) { 
 buffer.add(value); 
 System.out.println(Thread.currentThread().getName() + " produced " 
+ value); 
 value++; 
 buffer.notifyAll(); 
 } else { 
 try { 
 buffer.wait(); 
 } catch (InterruptedException e) { 
 e.printStackTrace(); 
 } 
 } 
 } 
 } 
 } 
} 
class Consumer implements Runnable { 
 private final LinkedList<Integer> buffer; 
 public Consumer(LinkedList<Integer> buffer) { 
 this.buffer = buffer; 
 } 
 @Override 
 public void run() { 
 while (true) { 
 synchronized (buffer) { 
 if (buffer.isEmpty()) { 
 try { 
 buffer.wait(); 
 } catch (InterruptedException e) { 
 e.printStackTrace(); 
 } 
 } else { 
 int value = buffer.removeFirst(); 
 System.out.println(Thread.currentThread().getName() + " consumed 
" + value); 
 buffer.notifyAll(); 
 } 
 } 
 } 
 } 
} 