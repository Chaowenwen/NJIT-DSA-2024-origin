
package oy.tol.tra;

public class QueueImplementation<E> implements QueueInterface<E> {

   /**
    * For querying the current capacity of the queue.
    @return The number of elements the queue can currently hold.
    */
    private Object[] itemArray; 
   private int capacity;
   private int head;  
   private int tail;  
   private int size;

   
   public QueueImplementation(int capacity) {  
    if (capacity <= 0) {  
        throw new IllegalArgumentException("Capacity must be positive");  
    }  
    this.capacity = capacity;  
    this.itemArray = new Object[capacity];  
    this.head = 0;  
    this.tail = 0;  
    this.size = 0;  
}  

@Override  
public int capacity() {  
    return capacity;  
}  
   
   /**
    * Add an element to the queue.
    * @param element The element to add, must not be null.
    * @throws QueueAllocationException If the reallocation for the queue failed in case queue needs reallocation.
    * @throws NullPointerException If the element is null.
    */
    @Override 
   public void enqueue(E element) throws QueueAllocationException, NullPointerException{
    if (element == null) {  
        throw new NullPointerException("Element cannot be null");  
    }  
    if (isFull()) {  
        reallocate();  
    }  
    itemArray[tail] = element;  
    tail = (tail + 1) % capacity;  
    size++;  
   }

   /**
    * Removes an element from the queue.
    * @return The element from the head of the queue.
    * @throws QueueIsEmptyException If the queue is empty.
    */
    @Override 
   public E dequeue() throws QueueIsEmptyException{
    if (isEmpty()) {  
        throw new QueueIsEmptyException("Queue is empty");  
    }  
    @SuppressWarnings("unchecked")  
    E element = (E) itemArray[head];  
    itemArray[head] = null;
    head = (head + 1) % capacity;  
    size--;  
    return element;  
   }

   /**
    * Returns the element at the head of the queue, not removing it from the queue.
    * @return The element in the head of the queue.
    * @throws QueueIsEmptyException If the queue is empty.
    */
    @Override 
   public E element() throws QueueIsEmptyException{
    if (isEmpty()) {  
        throw new QueueIsEmptyException("Queue is empty");  
    }  
    @SuppressWarnings("unchecked")  
    E element = (E) itemArray[head];  
    return element;  
   }

   /**
    * Returns the count of elements currently in the queue.
    * @return Count of elements in the queue.
    */
    @Override
   public int size(){
    return size ;
   }

   /**
    * Can be used to check if the queue is empty.
    * @return True if the queue is empty, false otherwise.
    */
    @Override
   public boolean isEmpty(){
    return size==0;
   }

   /**
    * Resets the queue to empty state, removing the objects.
    */
    @Override
   public void clear(){
    head = 0;  
    tail = 0;  
    size = 0;  
    // Optionally, you can set all elements to null to aid garbage collection  
    for (int i = 0; i < capacity; i++) {  
        itemArray[i] = null;  
    }  
   }

   private boolean isFull() {  
    return size == capacity;  
}  

private void reallocate() throws QueueAllocationException {  
    int newCapacity = capacity * 2;  
    Object[] newArray = new Object[newCapacity];  
      
    // Copy elements from old array to new array  
    for (int i = 0; i < size; i++) {  
        newArray[i] = itemArray[(head + i) % capacity];  
    }  
      
    capacity = newCapacity;  
    head = 0;  
    tail = size;  
    itemArray = newArray;  
}  
@Override
public String toString() {
    if (isEmpty()) {
        return "[]";
    }

    StringBuilder sb = new StringBuilder();
    sb.append("[");
    int index = head;
    sb.append(itemArray[index]);

    for (int i = 1; i < size; i++) {
        index = (index + 1) % capacity;
        sb.append(", ").append(itemArray[index]);
    }

    sb.append("]");
    return sb.toString();
}

}

