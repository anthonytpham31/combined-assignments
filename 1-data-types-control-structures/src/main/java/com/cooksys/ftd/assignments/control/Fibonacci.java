package com.cooksys.ftd.assignments.control; 

/**
 * The Fibonacci sequence is simply and recursively defined: the first two elements are `1`, and
 * every other element is equal to the sum of its two preceding elements. For example:
 * <p>
 * [1, 1] =>
 * [1, 1, 1 + 1]  => [1, 1, 2] =>
 * [1, 1, 2, 1 + 2] => [1, 1, 2, 3] =>
 * [1, 1, 2, 3, 2 + 3] => [1, 1, 2, 3, 5] =>
 * ...etc
 */
public class Fibonacci {

    /**
     * Calculates the value in the Fibonacci sequence at a given index. For example,
     * `atIndex(0)` and `atIndex(1)` should return `1`, because the first two elements of the
     * sequence are both `1`.
     *
     * @param i the index of the element to calculate
     * @return the calculated element
     * @throws IllegalArgumentException if the given index is less than zero
     */
    public static int atIndex(int i) throws IllegalArgumentException {
    	
    	if(i < 0){
    		throw new IllegalArgumentException();
    	}
    	return fibonacci(i+1)[i];
    }

	/**
     * Calculates a slice of the fibonacci sequence, starting from a given start index (inclusive) and
     * ending at a given end index (exclusive).
     *
     * @param start the starting index of the slice (inclusive)
     * @param end   the ending index of the slice(exclusive)
     * @return the calculated slice as an array of int elements
     * @throws IllegalArgumentException if either the given start or end is negative, or if the
     *                                  given end is less than the given start
     */
    public static int[] slice(int start, int end) throws IllegalArgumentException {
        
    	if(end < start || end < 0 || start < 0){
    		throw new IllegalArgumentException();
    	}
    	
    	if(start == 0 && end == 0){	
    	}
    	
    	int[] wholeArray = new int[end];
    	wholeArray = fibonacci(end);
    	
    	int[] sliceArray = new int[end - start];
    	int sliceCounter = 0;
    	
    	for(int i = start; i < end; i++){
    		sliceArray[sliceCounter] = wholeArray[i];
    		sliceCounter = sliceCounter + 1;
    	}

    	return sliceArray;
    	
    }

    /**
     * Calculates the beginning of the fibonacci sequence, up to a given count.
     *
     * @param count the number of elements to calculate
     * @return the beginning of the fibonacci sequence, up to the given count, as an array of int elements
     * @throws IllegalArgumentException if the given count is negative
     */
    public static int[] fibonacci(int count) throws IllegalArgumentException {
        
    	if (count < 0) {
			throw new IllegalArgumentException();
		}
  
    	int[] fibonacciArray = new int[count];
    	
    	if (count == 0){
    	}
    	else if (count == 1){
    		fibonacciArray[0] = 1;
    	}
    	else if (count ==2){
    		fibonacciArray[0] = 1;
    		fibonacciArray[1] = 1;
    	}
    	for(int i = 2; i < count; i++){
    		fibonacciArray[0] = 1;
    		fibonacciArray[1] = 1;
    		fibonacciArray[i] = fibonacciArray[i - 1] + fibonacciArray[i - 2];
    	}

    	return fibonacciArray;
    }
}
