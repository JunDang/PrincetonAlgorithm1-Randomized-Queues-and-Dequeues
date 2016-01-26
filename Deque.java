import java.util.Iterator;
 
public class Deque<Item> implements Iterable<Item> {
   private Node first;
   private Node last;
   private int N;
   private class Node
   {
     private Item item;
     private Node next;
     private Node previous;
   }
   public Deque()                     // construct an empty deque
   {
      first = null;
      last = null;
      N = 0;
   }
   public boolean isEmpty()           // is the deque empty?
   {
      return N == 0;
   }
   public int size()                  // return the number of items on the deque
   {
      return N;
   }
   public void addFirst(Item item)    // insert the item at the front
   {
      if (item == null)
      {
         throw new java.lang.NullPointerException();
      }
       
      Node oldfirst = first;
      first = new Node();
      first.item = item;
      first.next = oldfirst;
      first.previous = null;      
      if (isEmpty()) last = first;
      else           oldfirst.previous = first; 
      N++;
   }
   public void addLast(Item item)     // insert the item at the end
   {
      if (item == null)
      {
         throw new java.lang.NullPointerException();
      }
 
      Node oldlast = last;
      last = new Node();
      last.item = item;
      last.next = null;
      last.previous = oldlast;
      if (isEmpty()) first = last;
      else           oldlast.next = last;
      N++;
   }
   public Item removeFirst()          // delete and return the item at the front
   {
      if (isEmpty())
      {
         throw new java.util.NoSuchElementException();
      }
 
      Item item = first.item;
      first = first.next;      
      N--;      
      if (isEmpty()) last = null;
      else           first.previous = null;
      return item;
   }
   public Item removeLast()           // delete and return the item at the end
   {
      if (isEmpty())
      {
         throw new java.util.NoSuchElementException();
      }
 
      Item item = last.item;
      last = last.previous;      
      N--;      
      if (isEmpty()) first = null;
      else           last.next = null;
      return item;      
   }
   public Iterator<Item> iterator()   // return an iterator over items in order from front to end
   {
      return new ListIterator();
   }
    
   private class ListIterator implements Iterator<Item>
   {
      private Node current = first;
      public boolean hasNext()
      {
         return current != null;
      }
       
      public void remove()
      { 
         throw new java.lang.UnsupportedOperationException();
      }
       
      public Item next()
      {
         if (current == null)
         {
            throw new java.util.NoSuchElementException();
         }
         Item item = current.item;
         current = current.next;
         return item;
      }
   }   
}