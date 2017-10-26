 @author Andrew Gonzales
import java.util.Arrays;
import java.util.Random;

public class Task3 {

	private static int quickSortCountMove = 0;
	private static int quickSortCountCompare = 0;
	private static int mergeSortCountMove = 0;
	private static int mergeSortCountCompare = 0;
	
	
	/**
	 * A method which returns a fixed array of size 10
	 * @return
	 */
	public static int [] getFixedArray(int size) {
		int [] a = new int[size];
		Random rnd = new Random();
		for (int i = 0; i < size; i++)
			a[i] = rnd.nextInt(1000);
		
		return a;
	}
	
	/**
	 * Method that iteratively performs
	 * radix sort
	 * 
	 * @param a
	 * @return
	 */
	public static int [] iterRadixSort(int [] a) {
		int [] b = Arrays.copyOf(a, a.length);
		
		int countMove = 0;
		int countCompare = 0;
		
		// computing max
		int max = b[0];
		for (int i = 1; i < b.length; i++)
			if (b[i] > max) {
				max = b[i];
				countMove++;
				countCompare++;
			}
		
		int exponent = 1;
		
		int [] newBuckets = new int[10];
		
		// while there is a digit position
		while (max / exponent > 0) {
			countCompare++; // for while condition
			int [] buckets = new int[10];
			// initializing buckets with 0
			for (int i = 0; i < 10; i++)
				buckets[i] = 0;			
			
			// depending upon the exponent position of value,
			// increment that particular bucket
			for (int i = 0; i < b.length; i++) {
				buckets[(b[i] / exponent) % 10] += 1;
			}
			
			// iteratively add previous bucket's value to the current bucket.
			// This way the last bucket will contain the complete sum
			for (int i = 1; i < 10; i++) {
				buckets[i] = buckets[i] + buckets[i - 1];
			}
			
			for (int i = b.length - 1; i >= 0 ; i--) {
				buckets[(b[i] / exponent) % 10] -= 1;
				newBuckets[buckets[(b[i] / exponent) % 10]] = b[i];
			}
			
			for (int i = 0; i < b.length; i++) {
				b[i] = newBuckets[i];
				countMove++;
			}
			
			exponent = exponent * 10;
		}
		System.out.println("CountMove = " + countMove);
		System.out.println("CountCompare = " + countCompare);
		return b;
	}
	
	/**
	 * Iterative version of quick sort algo.
	 * Uses stack to replace recursion.
	 * 
	 * @param a
	 * @return
	 */
	public static int [] iterQuickSort(int [] a) {
		int [] b = Arrays.copyOf(a, a.length);
		
		int [] stack = new int[b.length];
		
		int nextStackPtr = 0;		
		
		stack[nextStackPtr] = 0;
		nextStackPtr++;
		stack[nextStackPtr] = b.length - 1;
		nextStackPtr++;
		
		while (nextStackPtr > 0) { // till stack isn't empty
			// pick the next pair of low and high
			int low = stack[nextStackPtr];
			nextStackPtr--;
			int high = stack[nextStackPtr];
			nextStackPtr--;
			
			// compute pivot and adjust array around that pivot
			int pivot = quicksortPartition(b, low, high);
			
			if (pivot > low + 1) {
				// add a pair of low and high which is pivot-1 to stack
				stack[nextStackPtr] = low;
				nextStackPtr++;
				stack[nextStackPtr] = pivot - 1;
				nextStackPtr++;
			}			
			if (pivot < high - 1) {
				// add a pair of low (which is pivot+1) and high to stack
				stack[nextStackPtr] = pivot + 1;
				nextStackPtr++;
				stack[nextStackPtr] = high;
				nextStackPtr++;
			}			
		}
		
		return b;
	}

	/**
	 * A helper method which considers last element as pivot
	 * and adjusts remaining element of the array around that pivot.
	 * 
	 * @param a
	 * @param low
	 * @param high
	 * @return
	 */
	private static int quicksortPartition(int [] a, int low, int high) {
		int val = a[high];
		int pivot = (low - 1);
		for (int i = low; i < high; i++) {
			if (a[i] <= val) {
				quickSortCountCompare++;
				pivot++;
				int tmp = a[pivot];
				a[pivot] = a[i];
				a[i] = tmp;
				quickSortCountMove += 2;
			}
		}
		int tmp2 = a[pivot + 1];
		a[pivot + 1] = a[high];
		a[high] = tmp2;
		quickSortCountCompare++;
		quickSortCountMove += 2;
		return pivot + 1;
	}

	/**
	 * Iterative version of merge sort
	 * @param a
	 * @return
	 */
	public static int [] iterMergeSort(int [] a) {
		int [] b = Arrays.copyOf(a, a.length);
		
		// if a single element is there, no need to sort
		if (b.length == 1)
			return b;

		int mid = b.length / 2; // computing mid

		int [] leftSplit = new int[mid];
		int [] rightSplit = new int[b.length - mid];

		// Filling left split
		for (int i = 0; i < mid; i++)
			leftSplit[i] = b[i];mergeSortCountMove++;

		// filling right split
		for (int i = mid; i < b.length; i++)
			rightSplit[i - mid] = b[i];mergeSortCountMove++;

		// sort left split separately
		leftSplit = iterMergeSort(leftSplit);
		// sort right split separately
		rightSplit = iterMergeSort(rightSplit);

		int i,j,ct;
		i = j = ct = 0;		

		// merge left and right splits
		while (i < leftSplit.length && j < rightSplit.length) {
			if (leftSplit[i] > rightSplit[j]) {
				b[ct] = rightSplit[j];
				ct++;
				j++;
			} else {
				b[ct] = leftSplit[i];
				ct++;
				i++;
			}
			mergeSortCountMove++;
			mergeSortCountCompare = mergeSortCountCompare + 1;
		}

		// merge remaining of left
		while (i < leftSplit.length) {
			b[ct] = leftSplit[i];
			mergeSortCountMove++;
			ct++;
			i++;
		}

		// merge remaining of right
		while (j < rightSplit.length) {
			b[ct] = rightSplit[j];
			mergeSortCountMove++;
			ct++;
			j++;
		}

		return b;
	}

	/**
	 * Method which implements iterative version
	 * of Hibbard's shell sort
	 * @param a
	 * @return
	 */
	public static int [] iterShellSort(int [] a) {
		int [] b = Arrays.copyOf(a, a.length);

		int countMove = 0;
		int countCompare = 0;
		
		int count = 1;
		int idx = 0;

		while (count <= b.length + 1) {
			for (int i = 0; i < b.length; i++) {
				int j = i;
				int val = b[i];

				while (j >= count && b[j - count] > val) {
					b[j] = b[j - count];
					j = j - count;
					countMove++;
					countCompare++;
				}
				b[j] = val;
				countMove++;
			}
			idx++;

			// Hibbard sequence (2^k - 1)
			count = ((int)Math.pow(2, idx)) - 1;
		}
		System.out.println("CountMove = " + countMove);
		System.out.println("CountCompare = " + countCompare);
		return b;
	}

	/**
	 * Iterative implementation of insertion sort
	 * @param a
	 * @return
	 */
	public static int [] iterInsertionSort(int [] a) {
		int countMove = 0;
		int countCompare = 0;
		int [] b = Arrays.copyOf(a, a.length);
		for (int i = 1; i < b.length; i++) {
			int val = b[i];
			int j = i;
			// while the val is smaller, keep on shifting the 
			// objects to right
			while (j > 0 && b[j - 1] > val) {
				b[j] = b[j - 1];
				countMove++;
				countCompare++;
				j--;
			}
			b[j] = val;
			countMove++;
		}
		System.out.println("CountMove = " + countMove);
		System.out.println("CountCompare = " + countCompare);
		return b;
	}

	/**
	 * Iterative implementation of selection sort
	 * @param a
	 * @return
	 */
	public static int [] iterSelectSort(int [] a) {
		
		int countMove = 0;
		int countCompare = 0;
		
		int [] b = Arrays.copyOf(a, a.length);
		for (int i = 0; i < b.length - 1; i++) {
			for (int j = i + 1 ; j < b.length; j++) {
				// Do a swap if a later element is
				// smaller than the current former element
				if (b[i] > b[j]) {
					int tmp = b[i];
					b[i] = b[j];
					b[j] = tmp;
					countMove = countMove + 2;
				}
				countCompare++;
			}
		}
		System.out.println("CountMove = " + countMove);
		System.out.println("CountCompare = " + countCompare);
		return b;
	}

	/**
	 * Method which prints the array contents
	 * @param arr
	 */
	public static void printArr(String prefix, int [] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String [] args) {

		int [] arr = getFixedArray(10);

		int [] sortedArr = null;

		printArr("Unsorted Array", arr);

		System.out.println("\nIterative Selection Sort");
		sortedArr = iterSelectSort(arr);
		printArr("Iterative Selection Sort", sortedArr);

		System.out.println("\nIterative Insertion Sort");
		sortedArr = iterInsertionSort(arr);
		printArr("Iterative Insertion Sort", sortedArr);

		System.out.println("\nIterative Shell Sort");
		sortedArr = iterShellSort(arr);
		printArr("Iterative Shell Sort", sortedArr);

		System.out.println("\nIterative Merge Sort");
		sortedArr = iterMergeSort(arr);
		System.out.println("CountMove = " + mergeSortCountMove);
		System.out.println("CountCompare = " + mergeSortCountCompare);
		printArr("Iterative Merge Sort", sortedArr);
		
		System.out.println("\nIterative Quick Sort");
		sortedArr = iterQuickSort(arr);
		System.out.println("CountMove = " + quickSortCountMove);
		System.out.println("CountCompare = " + quickSortCountCompare);
		printArr("Iterative Quick Sort", sortedArr);
