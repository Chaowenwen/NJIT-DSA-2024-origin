package oy.tol.tra;

import java.util.Stack;

public class StackImplementation<E> implements StackInterface<E> {

   private Object [] itemArray;
   private int capacity;
   private int currentIndex = -1;
   private static final int DEFAULT_STACK_SIZE = 10;
   private static final int GROW_FACTOR = 2;
  
   /**
    * Allocates a stack with a default capacity.
    * @throws StackAllocationException
    */
   public StackImplementation() throws StackAllocationException {
   
      this(DEFAULT_STACK_SIZE);  
 
   }

   /** TODO: Implement so that
    * - if the size is less than 2, throw StackAllocationException
    * - if the allocation of the array throws with Java exception,
    *   throw StackAllocationException.
    * @param capacity The capacity of the stack.
    * @throws StackAllocationException If cannot allocate room for the internal array.
    */
   public StackImplementation(int capacity) throws StackAllocationException {
      if (capacity < 2) {  
         throw new StackAllocationException("Stack capacity must be at least 2.");  
     }  
     this.capacity = capacity;  
     itemArray = new Object[capacity];  
   }

   @Override
   public int capacity() {
      return capacity;
      
   }

   @Override
   public void push(E element) throws StackAllocationException, NullPointerException {
      if (element == null) {
         throw new NullPointerException("Cannot push null element to stack.");
      }
      if (currentIndex == capacity - 1) {  
        
         resize();  
     }  
     itemArray[++currentIndex] = element;  
               
   }
   private void resize() throws StackAllocationException {  
      capacity *= GROW_FACTOR;  
      Object[] tempArray = new Object[capacity];  
      System.arraycopy(itemArray, 0, tempArray, 0, currentIndex + 1);  
      itemArray = tempArray;  
  }  

   @SuppressWarnings("unchecked")
   @Override
   public E pop() throws StackIsEmptyException {  
      if (currentIndex < 0) {    
          throw new StackIsEmptyException("Stack is empty.");    
      }    
      E element = (E) itemArray[currentIndex--];  
      itemArray[currentIndex + 1] = null; 
      return element;  
  }
   

   @SuppressWarnings("unchecked")
   @Override
   public E peek() throws StackIsEmptyException {
      if (currentIndex < 0) {    
         throw new StackIsEmptyException("Stack is empty.");    
     }    
     return (E) itemArray[currentIndex];  
     
   }

   @Override
   public int size() {
    
      return currentIndex + 1;  
      
   }

   @Override
   public void clear() {

      currentIndex = -1;
      for (int i = 0; i < itemArray.length; i++) {  
              itemArray[i] = null;  
          }  
      
   }

   @Override
   public boolean isEmpty() {
      return currentIndex < 0;  
      
   }
 
   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder("[");
      for (var index = 0; index <= currentIndex; index++) {
         builder.append(itemArray[index].toString());
         if (index < currentIndex) {
            builder.append(", ");
         }
      }
      builder.append("]");
      return builder.toString();
   }
   
}
