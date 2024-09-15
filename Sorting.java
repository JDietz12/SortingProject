/*
THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
CODE WRITTEN BY OTHER STUDENTS OR COPIED FROM ONLINE RESOURCES. _Jackson Dietz_
*/

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

public class Sorting {

    //-------------------------------------------------------------
    //---------- Bubble Sort --------
    //-------------------------------------------------------------
    public static void BubbleSort(long[] a) {

        for (int i = 0; i < a.length -1 ; i ++) { // Outer Loop through the array
            for (int j = 0; j < a.length - i - 1; j++){ // Inner loop through the array
                // if element to the left is bigger the swap
                if (a[j] > a[j + 1]){
                    long temp = a[j];
                    long temp2 = a[j +1];
                    a[j] = temp2;
                    a[j +1] = temp;
                }
            }
        }
    }

    //-------------------------------------------------------------
    //---------- Optimized Bubble Sort --------
    //-------------------------------------------------------------
    public static void BubbleSortOptimized(long[] a) {

        boolean swapped;

        for (int i = 0; i < a.length -1 ; i ++) { // Outer Loop through the array
            swapped = false; // boolean flag
            for (int j = 0; j < a.length - i - 1; j++){ // Inner loop through the array
                // if element to the left is bigger the swap
                if (a[j] > a[j + 1]){
                    long temp = a[j];
                    long temp2 = a[j +1];
                    a[j] = temp2;
                    a[j +1] = temp;
                    swapped = true; // change the boolean flag
                }
            }
            if(swapped == false){ // If sorted then break
                break;}
        }
    }

    //------------------------------------------------------------------
    //---------- Insertion Sort ----------
    //------------------------------------------------------------------
    public static void InsertionSort(long[] a) {

        for (int i = 1; i < a.length; i++) { // Outer Loop through the array
            long placeHolder = a[i]; //keep track of the farthest right elemnt of the sprted array

            int j;
            for (j = i - 1; j >= 0; j--) { // Inner Loop through the array (traverse backwards)
                // If the item to the left is larger then swap
                if (a[j] > placeHolder) {
                    a[j + 1] = a[j];
                }
                else {break;}
            }
            a[j + 1] = placeHolder; //Reassign the placeHolder to the next element
        }
    }

    //------------------------------------------------------------
    //---------- Selection Sort ----------
    //------------------------------------------------------------
    public static void SelectionSort(long[] a) {

        for (int i = 0; i < a.length - 1; i++) { // Outer Loop through the array
            int minIndex = i; // the index of the minimum value for each inner traversal
            for (int j = i + 1; j < a.length; j++) { // Inner Loop through the array
                ///find the smallest value
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
           //swap the most left and minimum values
            long temp = a[minIndex];
            a[minIndex] = a[i];
            a[i] = temp;
        }
    }

    //-----------------------------------------------------------------------
    //---------- recursive MergeSort ----------
    //-----------------------------------------------------------------------
    public static void MergeSort(long[] a) {
        if (a == null) // Check for an empty array, exit method
            return;
        if (a.length <= 1) // Check if array has one element or less, exit method
            return;

        long[] aux = new long[a.length]; // Auxiliary array for merge

        sort(a, aux, 0, a.length - 1); // Call the sorting method
    }

    // Mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void sort(long[] a, long[] aux, int lo, int hi) {
        if (hi <= lo) return; // Base case: if the array has one or fewer elements, it's already sorted

        int mid = lo + (hi - lo) / 2; // Calculate the midpoint of the array

        // Recursively sort the left and right halves
        sort(a, aux, lo, mid); // Sort the left half
        sort(a, aux, mid + 1, hi); // Sort the right half

        merge(a, aux, lo, mid, hi); // Merge the sorted halves
    }


    private static void merge(long[] a, long[] aux, int lo, int mid, int hi) {
        int i, j, k;

        // Copy elements from a[lo..hi] to aux[lo..hi]
        for (i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }

        i = lo;
        j = mid + 1;
        k = lo;

        // Merge the two halves back into a[]
        while (i <= mid && j <= hi) {
            if (aux[i] <= aux[j]) {
                a[k++] = aux[i++];
            } else {
                a[k++] = aux[j++];
            }
        }

        // Copy any remaining elements from the left half
        while (i <= mid) {
            a[k++] = aux[i++];
        }

        // Copy any remaining elements from the right half
        while (j <= hi) {
            a[k++] = aux[j++];
        }
    }


    //-------------------------------------------------------------
    //---------- Quicksort ----------
    //-------------------------------------------------------------
    public static void QuickSort(long[] a) {
        if (a == null) // Check for an empty array, exit method
            return;
        if (a.length <= 1) // Check if array has one element or less, exit method
            return;

        QuickSort(a, 0, a.length -1 );
    }

    private static void QuickSort(long[] a, int left, int right) {
        // Base case: If the subarray has zero or negative length, it's already sorted
        if (right - left <= 0)
            return;

        // Partition the array around a pivot element
        int p = partition(a, left, right, right); // Selecting right-most element as the pivot

        // Recursively sort the left and right partitions
        QuickSort(a, left, p - 1);  // Sort elements to the left of the pivot
        QuickSort(a, p + 1, right); // Sort elements to the right of the pivot
    }


    private static int partition(long[] a, int left, int right, int pIdx) {

        int s = left; //Store Index

        for (int i = left; i < right; i++) {
            if (a[i] < a[pIdx]) {
                // Swap elements
                long temp = a[i];
                a[i] = a[s];
                a[s] = temp;
                s++;
            }
        }

        // Swap pivot value to its correct position
        long temp = a[s];
        a[s] = a[pIdx];
        a[pIdx] = temp;

        return s; // Return the index of the pivot element to be used in QuickSort as P
    }

    //-----------------------------------------------------------------------
    // ---------- Optimized Quicksort ----------
    //-----------------------------------------------------------------------
    public static void shuffle(long[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            int randomIndex = (int) (Math.random() * (i + 1)); // Cast to an int
            // Swap elements at i and randomIndex
            long temp = a[i];
            a[i] = a[randomIndex];
            a[randomIndex] = temp;
        }
    }

    public static void QuickSortOptimized(long[] a) {
        if (a == null || a.length <= 1) // Check for an empty array or array with one element
            return;

        shuffle(a); // Shuffle

        QuickSortOptimized(a, 0, a.length - 1);
    }

    private static void QuickSortOptimized(long[] a, int left, int right) {

        int cutoff = 10;
        if (right - left < cutoff) {
            InsertionSort(a, left, right);
            return;
        }
        if (right - left <= 0) // Base case: If the subarray has zero or negative length, it's already sorted
            return;


        // Partition the array around a pivot element
        int p = partition(a, left, right, right); // Selecting right-most element as the pivot

        // Recursively sort the left and right partitions
        QuickSortOptimized(a, left, p - 1);  // Sort elements to the left of the pivot
        QuickSortOptimized(a, p + 1, right); // Sort elements to the right of the pivot
    }

    public static void InsertionSort(long[] a, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            long placeHolder = a[i];
            int j = i - 1;

            while (j >= left && a[j] > placeHolder) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = placeHolder;
        }
    }



    //---------------------------------------------------------------------------
    // ---------- Non-Recursive Mergesort ----------
    //---------------------------------------------------------------------------
    public static void MergeSortNonRec(long[] a) {
        if (a == null || a.length <= 1) {
            return; // No need to sort an empty array or an array with one element
        }

        long[] aux = new long[a.length]; // Auxiliary array for merge

        // Loop through various merge sizes  until the merge size exceeds array length
        for (int mergeSize = 1; mergeSize <= a.length / 2; mergeSize *= 2) {
            // Merge subarrays of current size
            for (int i = 0; i < a.length - mergeSize; i += 2 * mergeSize) {
                int lo = i; // Lower bound index of the subarray
                int mid = i + mergeSize - 1; // Middle index of the subarray
                int hi = Math.min(i + 2 * mergeSize - 1, a.length - 1); // Upper bound index of the subarray

                // Call the merge method
                merge(a, aux, lo, mid, hi);
            }
        }
    }


    //---------------------------------------------------------------------
    // ---------- Linked List Mergesort ----------
    //---------------------------------------------------------------------
    public static void MergeSortLL(MyLinkedList list) {

        list.mergeSort();
    }

    //------------------------------------------------------
    //---------- Below are several helper methods ----------
    //------------------------------------------------------
    // This tests whether your sorted result is correct by comparing it to reference result
    public static boolean testSort(long[] a) {
        long[] a2 = new long[a.length];
        System.arraycopy(a, 0, a2, 0, a.length);
        Arrays.sort(a);
        for(int i = 0; i < a.length; i++)
            if(a2[i] != a[i])
                return false;
        return true;
    }

    // This creates an array with n randomly generated elements between (0, n*10]
    private static long[] randArray(int n) {
        long[] rand = new long[n];
        for(int i=0; i<n; i++)
            rand[i] = (int) (Math.random() * n * 10);
        return rand;
    }

    // This creates an ordered array with n elements in ascending order
    private static long[] orderedArray(int n) {
        long[] arr = new long[n];
        for(int i=0; i<n; i++)
            arr[i] = i+1;
        return arr;
    }

    // This method copies the elements from an array to a linked list
    private static void copyArrayToLinkedList(long[] arr, MyLinkedList list) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            list.addLast(arr[i]);
        }
    }

    private static void startTimer() {
        timestamp = System.nanoTime();
    }

    private static double endTimer() {
        return (System.nanoTime() - timestamp)/1000000.0;
    }

    // Exchange a[i] and a[j]
    private static void exch(long[] a, int i, int j) {
        long swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static long timestamp;

    //---------------------------------------------
    //---------- This is the main method ----------
    //---------------------------------------------
    public static void main(String[] args) {

        // TODO: set this flag to true when you want to test your
        // methods with an ordered array (corner case!)
        boolean useOrderedArray = true;

        // run experiments
        final int BUBBLE = 0, BUBBLEOPT=1, SELECT = 2, INSERT = 3, QUICK = 4, QUICKOPT = 5, MERGEREC = 6, MERGENONREC = 7, MERGELINKEDLIST = 8;
        int[] algorithms = {BUBBLE, BUBBLEOPT, SELECT, INSERT, MERGEREC, MERGENONREC, MERGELINKEDLIST, QUICK, QUICKOPT};

        // max defines the maximum size of the array to be tested, which is 2^max
        // runs defines the number of rounds to be performed per test, in order to get an average running time.
        int max = 14, runs = 5;
        double[][] stats = new double[algorithms.length][max];
        MyLinkedList list; //for MergeSortLL
        for(int i=0; i < algorithms.length; i++) {  //loop through each sorting algorithm
            switch(i) {
                case BUBBLE: System.out.print("Running Bubble Sort ..."); break;
                case BUBBLEOPT: System.out.print("Running Optimized Bubble Sort ..."); break;
                case SELECT: System.out.print("Running Selection Sort ..."); break;
                case INSERT: System.out.print("Running Insertion Sort ..."); break;
                case QUICK: System.out.print("Running Quicksort..."); break;
                case QUICKOPT: System.out.print("Running Optimized Quicksort..."); break;
                case MERGEREC: System.out.print("Running MergeSort Recursive ..."); break;
                case MERGENONREC: System.out.print("Running MergeSort Non Recursive ..."); break;
                case MERGELINKEDLIST: System.out.print("Running MergeSort for Linked List ..."); break;
            } //end of switch
            for(int j = 0; j < max; j++) { //loop through each array size
                double avg = 0;
                for(int k = 0; k < runs; k++) { //loop through each run
                    int n = (int) Math.pow(2, j+1);
                    long[] a;
                    if(useOrderedArray){ a = orderedArray(n); }
                    else { a = randArray(n); }

                    list = new MyLinkedList(); //for MergeSortLL
                    copyArrayToLinkedList(a, list); //for MergeSortLL

                    startTimer();
                    switch(i) {
                        case BUBBLE: BubbleSort(a); break;
                        case BUBBLEOPT: BubbleSortOptimized(a); break;
                        case SELECT: SelectionSort(a); break;
                        case INSERT: InsertionSort(a); break;
                        case MERGEREC: MergeSort(a); break;
                        case MERGENONREC: MergeSortNonRec(a); break;
                        case MERGELINKEDLIST: MergeSortLL(list); break;
                        case QUICK: QuickSort(a); break;
                        case QUICKOPT: QuickSortOptimized(a); break;
                    }
                    avg += endTimer();
                    // if this was MergeSortLL, copy list back to array
                    if( i == MERGELINKEDLIST ) a = list.toArray();

                    if (testSort(a) == false)
                        System.out.println("The sorting is INCORRECT!" + "(N=" + a.length + ", round=" + k + ").");
                }//end of k runs
                avg /= runs;
                stats[i][j] = avg;
            }//end of max array sizes
            System.out.println("done.");
        }//done running all sorting algorithms!

        DecimalFormat format = new DecimalFormat("0.0000");
        System.out.println();
        System.out.println("Average running time:");
        System.out.println("N\t BubbleSort\tBubbleSortOpt\tSelectionSort\tInsertionSort\tQuickSort\tQuickSortOpt\tMergeSortRec\tMergeSortNon\tMergeSortList");
        for(int i=0; i<stats[0].length; i++) {
            System.out.print((int) Math.pow(2, i+1) + "\t  ");
            for(int j=0; j<stats.length; j++) {
                System.out.print(format.format(stats[j][i]) + "\t  ");
            }
            System.out.println();
        }
    }//end of main method

}//end of class

