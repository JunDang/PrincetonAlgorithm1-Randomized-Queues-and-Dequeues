import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
   private Item[] a;
   private int head;
   private int tail;
   private int N;
   public RandomizedQueue()           // construct an empty randomized queue
   {
      a = (Item[]) new Object[1];
      head = -1;
      tail = -1;
      N = 0;
   }
   public boolean isEmpty()           // is the queue empty?
   {
      return N == 0;
   }
   public int size()                  // return the number of items on the queue
   {
      return N;
   }
   private void resize(int max)
   {
      Item[] temp = (Item[]) new Object[max];
      for (int i = 0; i < N; i++)
      {
         int index = (head + i) % a.length;
         temp[i] = a[index];
      }
      head = 0;
      tail = N - 1;
      a = temp;
   }
   public void enqueue(Item item)     // add the item
   {
      if (item == null)
      {
         throw new java.lang.NullPointerException();
      }
       
      if (N == a.length) resize(2*a.length);
      tail++;
      tail = tail % a.length;
      a[tail] = item;
      N++;
      if (N == 1) head = tail;
   }
   public Item dequeue()              // delete and return a random item
   {
      if (isEmpty())
      {
         throw new java.util.NoSuchElementException();
      }      
      int removeIndx = (StdRandom.uniform(N) + head) % a.length;
      Item item = a[removeIndx];
      a[removeIndx] = a[head];
      a[head] = null;
      head++;
      head = head % a.length;
      N--;
      if (N == 1) tail = head;
      if (N > 0 && N == a.length/4) resize(a.length/2);      
      return item;
   }
   public Item sample()               // return (but do not delete) a random item
   {
     if (isEmpty())
     {
        throw new java.util.NoSuchElementException();
     }            
      int indx = (StdRandom.uniform(N) + head) % a.length;
      return a[indx];      
   }
   public Iterator<Item> iterator()   // return an independent iterator over items in random order
   {
      return new ListIterator();
   }
   private class ListIterator implements Iterator<Item>
   {
      private int current; // = head;
      private int [] iteratorSequence;
      public ListIterator()
      {
         current = 0;
         iteratorSequence = new int[N];
         for (int i = 0; i < N; i++)
            iteratorSequence[i] = (head + i) % a.length;
         StdRandom.shuffle(iteratorSequence);
      }
      public boolean hasNext()
      {
         return current < N;
      }
       
      public void remove()
      { 
         throw new java.lang.UnsupportedOperationException();
      }
       
      public Item next()
      {
          if (current >= N)
          {
             throw new java.util.NoSuchElementException();
          }
          Item item = a[iteratorSequence[current]];
          current++;
          return item;
      }
   }
}