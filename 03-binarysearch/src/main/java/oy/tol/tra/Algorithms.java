package oy.tol.tra;

public class Algorithms {
    public static <T extends Comparable<T>> int binarySearch(T aValue, T [] fromArray, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            return -1;
        }

        int midIndex = (fromIndex + toIndex) / 2;
        int compareResult = aValue.compareTo(fromArray[midIndex]);
        if (compareResult == 0) {
            return midIndex;
        } else if (compareResult < 0) {
            return binarySearch(aValue, fromArray, fromIndex, midIndex - 1);
        } else {
            return binarySearch(aValue, fromArray, midIndex + 1, toIndex);
        }
     }
    public static <T extends Comparable<T>> void sort(T [] array) {
        
            boolean swapped;  
            do {  
                swapped = false;  
                for (int i = 0; i < array.length-1; i++) {  
                    if (array[i].compareTo(array[i+1]) > 0) {  
                        T temp = array[i];  
                        array[i] = array[i+1];  
                        array[i+1] = temp;  
                        swapped = true;  
                    }  
                }  
            } while (swapped);  
        }


    
        
     
    
     public static <T> void reverse(T [] array) {
       
            int i = 0;  
            int j = array.length - 1;  
            while (i < j) {  
                T temp = array[i];  
                array[i] = array[j];  
                array[j] = temp;  
                i++;  
                j--;  
            }  
        }
       
}
