/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task1;
import java.util.Arrays;

public class Task1 {

	/**
	 * A method which returns a fixed array of size 10
	 * @return
	 */
	public static int [] getFixedArray() {
		return new int[]{4,8,2,9,1,3,5,7,0,6};
	}

	/**
	 * This method starts the recursion for radix sort
	 * using a helper method which then recursively
	 * calls itself
	 * @param a
	 * @return
	 */
	public static int [] recurRadixSort(int [] a) {
		int [] b = Arrays.copyOf(a, a.length);
		
		// computing max
		int max = b[0];
		for (int i = 1; i < b.length; i++)
			if (b[i] > max)
				max = b[i];
		
		int [] newBuckets = new int[10];
		
		recurRadixSortHelper(b, max, 1, newBuckets);
		
		return b;
	}
	/**
	 * The method which recursively performs radix sort
	 * of array
	 * @param a
	 * @param max
	 * @param exponent
	 * @param newBuckets
	 */
	public static void recurRadixSortHelper(int [] a, int max, int exponent, int [] newBuckets) {
		if (max / exponent > 0) {
			int [] buckets = new int[10];
			// initializing buckets with 0
			for (int i = 0; i < 10; i++)
				buckets[i] = 0;			
			
			// depending upon the exponent position of value,
			// increment that particular bucket
			for (int i = 0; i < a.length; i++) {
				buckets[(a[i] / exponent) % 10] += 1;
			}
			
			// iteratively add previous bucket's value to the current bucket.
			// This way the last bucket will contain the complete sum
			for (int i = 1; i < 10; i++) {
				buckets[i] = buckets[i] + buckets[i - 1];
			}
			
			for (int i = a.length - 1; i >= 0 ; i--) {
				buckets[(a[i] / exponent) % 10] -= 1;
				newBuckets[buckets[(a[i] / exponent) % 10]] = a[i];
			}
			
			for (int i = 0; i < a.length; i++) {
				a[i] = newBuckets[i];
			}
			
			exponent = exponent * 10;
		}
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
		
		// computing max
		int max = b[0];
		for (int i = 1; i < b.length; i++)
			if (b[i] > max)
				max = b[i];
		
		int exponent = 1;
		
		int [] newBuckets = new int[10];
		
		// while there is a digit position
		while (max / exponent > 0) {
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
			}
			
			exponent = exponent * 10;
		}
		
		return b;
	}
	
	/**
	 * This method starts the recursion for quick sort
	 * using a helper method which then recursively
	 * calls itself
	 * @param a
	 * @return
	 */
	public static int [] recurQuickSort(int [] a) {
		int [] b = Arrays.copyOf(a, a.length);
		
		recurQuickSortHelper(b, 0, b.length - 1);
		
		return b;
	}
	
	/**
	 * The method which recursively performs quick sort
	 * of array
	 * @param a
	 * @param low
	 * @param high
	 */
	public static void recurQuickSortHelper(int [] a, int low, int high) {
		if (low < high) {
			// compute pivot and adjust array around that pivot
			int pivot = quicksortPartition(a, low, high);
			// recursively call quick sort on left of pivot
			recurQuickSortHelper(a, low, pivot - 1);
			// recursively call quick sort on right of pivot
			recurQuickSortHelper(a, pivot + 1, high);			
		}
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
				pivot++;
				int tmp = a[pivot];
				a[pivot] = a[i];
				a[i] = tmp;
			}
		}
		int tmp2 = a[pivot + 1];
		a[pivot + 1] = a[high];
		a[high] = tmp2;
		return pivot + 1;
	}
	
	/**
	 * This method starts the recursion for merge sort
	 * using a helper method which then recursively
	 * calls itself
	 * @param a
	 * @return
	 */
	public static int [] recurMergeSort(int [] a) {
		int [] b = Arrays.copyOf(a, a.length);

		recurMergeSortHelper(b, 0, b.length - 1);

		return b;
	}

	/**
	 * The method which recursively performs merge sort
	 * of array
	 * @param a
	 * @param left
	 * @param right
	 */
	public static void recurMergeSortHelper(int [] a, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			recurMergeSortHelper(a, left, mid); // recursively sort left array
			recurMergeSortHelper(a, mid + 1, right); // recursively sort right array
			merge(a, left, mid, right); // merge the sorted left and right arrays
		}
	}

	/**
	 * A method which performs sorted merge of left
	 * and right portions of the array
	 * @param a
	 * @param left
	 * @param mid
	 * @param right
	 */
	public static void merge(int [] a, int left, int mid, int right) {

		int leftSize = mid - left + 1;
		int rightSize = right - mid;

		int [] leftArr = new int[leftSize];
		int [] rightArr = new int[rightSize];

		for (int k = 0; k < leftSize; k++)
			leftArr[k] = a[left + k];
		for (int k = 0; k < rightSize; k++)
			rightArr[k] = a[mid + 1 + k];

		int i, j, ct;
		i = j = 0;
		ct = left;
		
		// merge left and right splits
		while (i < leftSize && j < rightSize) {
			if (leftArr[i] > rightArr[j]) {
				a[ct] = rightArr[j];
				ct++;
				j++;
			} else {
				a[ct] = leftArr[i];
				ct++;
				i++;
			}
		}

		// merge remaining of left
		while (i < leftArr.length) {
			a[ct] = leftArr[i];
			ct++;
			i++;
		}

		// merge remaining of right
		while (j < rightArr.length) {
			a[ct] = rightArr[j];
			ct++;
			j++;
		}
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
			leftSplit[i] = b[i];

		// filling right split
		for (int i = mid; i < b.length; i++)
			rightSplit[i - mid] = b[i];

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
		}

		// merge remaining of left
		while (i < leftSplit.length) {
			b[ct] = leftSplit[i];
			ct++;
			i++;
		}

		// merge remaining of right
		while (j < rightSplit.length) {
			b[ct] = rightSplit[j];
			ct++;
			j++;
		}

		return b;
	}

	/**
	 * Recursive version of Hibbard's shell sort.
	 * Simply makes a call to a helper method 
	 * which then recursively gets executed.
	 * @param a
	 * @return
	 */
	public static int [] recurShellSort(int [] a) {
		int [] b = Arrays.copyOf(a, a.length);

		recurShellSortHelper(b, 1, 0);

		return b;
	}

	/**
	 * The actual recursive implementation of
	 * Hibbard's shell sort algo
	 * @param a
	 * @param count
	 * @param idx
	 */
	public static void recurShellSortHelper(int [] a, int count, int idx) {

		if (count <= a.length + 1) {
			for (int i = 0; i < a.length; i++) {
				int j = i;
				int val = a[i];

				while (j >= count && a[j - count] > val) {
					a[j] = a[j - count];
					j = j - count;
				}
				a[j] = val;
			}
			idx++;

			// Hibbard sequence (2^k - 1)
			count = ((int)Math.pow(2, idx)) - 1;

			recurShellSortHelper(a, count, idx);
		}

	}

	/**
	 * Method which implements iterative version
	 * of Hibbard's shell sort
	 * @param a
	 * @return
	 */
	public static int [] iterShellSort(int [] a) {
		int [] b = Arrays.copyOf(a, a.length);

		int count = 1;
		int idx = 0;

		while (count <= b.length + 1) {
			for (int i = 0; i < b.length; i++) {
				int j = i;
				int val = b[i];

				while (j >= count && b[j - count] > val) {
					b[j] = b[j - count];
					j = j - count;
				}
				b[j] = val;
			}
			idx++;

			// Hibbard sequence (2^k - 1)
			count = ((int)Math.pow(2, idx)) - 1;
		}

		return b;
	}

	/**
	 * Recursive implementation of Insertion sort.
	 * Makes use of helper function for recursively
	 * doing insertion sort of sub arrays
	 * @param a
	 * @return
	 */
	public static int [] recurInsertionSort(int [] a) {
		int [] b = Arrays.copyOf(a, a.length);
		recurInsertionSortHelper(b, 0);
		return b;
	}

	/**
	 * The actual method which gets recursively called
	 * during recursive insertion sort step
	 * @param a
	 * @param idx
	 */
	private static void recurInsertionSortHelper(int [] a, int idx) {
		int j = idx;
		int val = a[idx];

		// while the val is smaller, keep on shifting the 
		// objects to right
		while (j > 0 && a[j - 1] > val) {
			a[j] = a[j - 1];
			j--;
		}
		a[j] = val;

		// Make a recursive call only 
		// if idx+1 is present
		if (a.length > idx + 1) {
			recurInsertionSortHelper(a, idx + 1);
		}
	}

	/**
	 * Iterative implementation of insertion sort
	 * @param a
	 * @return
	 */
	public static int [] iterInsertionSort(int [] a) {
		int [] b = Arrays.copyOf(a, a.length);
		for (int i = 1; i < b.length; i++) {
			int val = b[i];
			int j = i;
			// while the val is smaller, keep on shifting the 
			// objects to right
			while (j > 0 && b[j - 1] > val) {
				b[j] = b[j - 1];
				j--;
			}
			b[j] = val;
		}
		return b;
	}

	/**
	 * Iterative implementation of selection sort
	 * @param a
	 * @return
	 */
	public static int [] iterSelectSort(int [] a) {
		int [] b = Arrays.copyOf(a, a.length);
		for (int i = 0; i < b.length - 1; i++) {
			for (int j = i + 1 ; j < b.length; j++) {
				// Do a swap if a later element is
				// smaller than the current former element
				if (b[i] > b[j]) {
					int tmp = b[i];
					b[i] = b[j];
					b[j] = tmp;
				}
			}
		}
		return b;
	}

	/**
	 * Recursive implementation of select sort
	 * @param a
	 * @return
	 */
	public static int [] recurSelectSort(int [] a) {
		int [] b = Arrays.copyOf(a, a.length);
		recurSelectSortHelper(b, 0);
		return b;
	}

	/**
	 * Function which recursively computes the index of min value ina rray starting from i
	 * @param a
	 * @param i
	 * @param j
	 * @return
	 */
	private static int minIdxInArr(int [] a, int i, int j) {
		if (i == j)
			return i;

		int minIdx = minIdxInArr(a, i + 1, j);

		if (a[i] > a[minIdx])
			return minIdx;
		else
			return i;
	}

	/**
	 * Functions which helps in recursively doing select sort
	 * @param a
	 * @param idx
	 */
	private static void recurSelectSortHelper(int [] a, int idx) {
		if (idx == a.length)
			return;

		// find index which contains MIN value
		int minIdx = minIdxInArr(a, idx, a.length - 1);

		// swap if indices don't match
		if (idx != minIdx) {
			int tmp = a[idx];
			a[idx] = a[minIdx];
			a[minIdx] = tmp;
		}

		recurSelectSortHelper(a, idx + 1);
	}

	/**
	 * Method which prints the array contents
	 * @param arr
	 */
	public static void printArr(String prefix, int [] arr) {
		System.out.print(prefix + " => ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String [] args) {

		int [] arr = getFixedArray();

		int [] sortedArr = null;

		printArr("Unsorted Array", arr);

		sortedArr = iterSelectSort(arr);
		printArr("Iterative Selection Sort", sortedArr);

		sortedArr = recurSelectSort(arr);
		printArr("Recursive Selection Sort", sortedArr);

		sortedArr = iterInsertionSort(arr);
		printArr("Iterative Insertion Sort", sortedArr);

		sortedArr = recurInsertionSort(arr);
		printArr("Recursive Insertion Sort", sortedArr);

		sortedArr = iterShellSort(arr);
		printArr("Iterative Shell Sort", sortedArr);

		sortedArr = recurShellSort(arr);
		printArr("Recursive Shell Sort", sortedArr);

		sortedArr = iterMergeSort(arr);
		printArr("Iterative Merge Sort", sortedArr);
		
		sortedArr = recurMergeSort(arr);
		printArr("Recursive Merge Sort", sortedArr);
		
		sortedArr = iterQuickSort(arr);
		printArr("Iterative Quick Sort", sortedArr);
		
		sortedArr = recurQuickSort(arr);
		printArr("Recursive Quick Sort", sortedArr);
		
		sortedArr = iterRadixSort(arr);
		printArr("Iterative Radix Sort", sortedArr);
        }
}