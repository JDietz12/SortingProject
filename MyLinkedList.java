/*
THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
CODE WRITTEN BY OTHER STUDENTS OR COPIED FROM ONLINE RESOURCES. _Jackson Dietz_
*/


import java.util.*;

public class MyLinkedList
{
    /*******************************************************/
    class Node
    {
        private long data;
        private Node next;
        public Node(long data, Node next)
        {
            this.data = data;
            this.next = next;
        }
        public String toString(){ return "" + this.data; }

    }
    /********************************************************/

    private Node head;
    private int size;

    public MyLinkedList() {
        head = null;
        size = 0;
    }

    public int getSize(){ return size; }

    // Returns true if the list is empty
    public boolean isEmpty() {
        return head == null;
    }

    // Inserts a new node at the beginning of this list
    public void addFirst(long item) {
        head = new Node(item, head);
        size++;
    }

    // Inserts a new node to the end of this list
    public void addLast(long item) {
        if( isEmpty() )
            addFirst(item);
        else
        {
            Node current = head;
            while( current.next != null ) current = current.next;
            // Now current is pointing to the last element!
            current.next = new Node(item, null);
            size++;
        }
    }

    // Returns the first element (data) in the list
    public long getFirst() {
        if( isEmpty() ) throw new NoSuchElementException();
        return head.data;
    }

    // Returns the last element (data) in the list
    public long getLast() {
        if(head == null) throw new NoSuchElementException();
        Node tmp = head;
        while(tmp.next != null) tmp = tmp.next;
        return tmp.data;
    }

    // Returns a reference to the Node at the given position,
    // assuming that node indexes start at zero
    public Node get(int pos) {
        if (head == null) throw new IndexOutOfBoundsException();
        Node tmp = head;
        for (int k = 0; k < pos; k++){
            tmp = tmp.next;
            if( tmp == null ) throw new IndexOutOfBoundsException();
        }
        return tmp;
    }

    public long[] toArray() {
        if(head == null) throw new IndexOutOfBoundsException();
        long[] result = new long[getSize()];
        int i = 0;
        for(Node tmp = head; tmp != null; tmp = tmp.next){
            result[i] = tmp.data;
            i++;
        }
        return result;
    }

    // Removes and returns the first element (data) in the list.
    public long removeFirst() {
        long temp = getFirst();
        head = head.next;
        size--;
        return temp;
    }

    // Removes the first occurrence of the specified element in this list.
    public void remove(long key) {
        if(head == null)
            throw new RuntimeException("cannot delete");

        if( head.data == key ) {
            head = head.next;
            size--;
            return;
        }

        Node cur  = head;
        Node prev = null;
        while(cur != null && cur.data != key )
        {
            prev = cur;
            cur = cur.next;
        }
        if(cur == null) throw new RuntimeException("cannot delete");
        prev.next = cur.next;
        size--;
    }

    // Returns a string representation
    public String toString() {
        String output = "";
        if(head == null) throw new NoSuchElementException();
        Node tmp = head;
        while(tmp != null) {
            output += tmp + " -> ";
            tmp = tmp.next;
        }
        output += "[NULL]";
        return output;
    }


    //-------------------------------------------------------------------------
    //----- Use MergeSort algorithm to sort the nodes in this linked list -----
    //-------------------------------------------------------------------------
    //Find the middle element of the list that begins at the passed node
    public Node getMiddle(Node node) {
        if (node == null) {  // checks if the list is empty/node is null
            return null;
        }
        Node pointer1 = node;
        Node pointer2 = node;

        while (pointer2.next != null && pointer2.next.next != null) {
            pointer1 =  pointer1.next; // Advance pointer 1 by one node
            pointer2 = pointer2.next.next; // Advance pointer 2 by two nodes
        }

        return  pointer1; //middle

    }

    // MergeSort starting point
    public void mergeSort() {
        if (head == null || head.next == null) { // for an empty list
            return;
        }

        head = sort(head);
    }

    public Node sort(Node node) {
        if (node == null || node.next == null) {
            return node; // Base case: return if the list is empty or contains only one node
        }

        Node middle = getMiddle(node); // middle node
        Node rightOfMiddle = middle.next; // node right of middle

        // Split the list into two halves at the middle node
        middle.next = null;


        Node left = sort(node); // sort left
        Node right = sort(rightOfMiddle); //sort right

        // Merge the sorted halves
        Node sortedList = merge(left, right);
        return sortedList;
    }

    //Merge two sorted lists (can be non-recursive)
    public Node merge(Node left, Node right) {

        if (left == null) {  //check left null
            return right;
        }
        if (right == null) { // check right null
            return left;
        }

        Node result;
        if (left.data <= right.data) {  // merge
            result = left;
            result.next = merge(left.next, right);
        } else {  //merge
            result = right;
            result.next = merge(left, right.next);
        }

        return result;
    }

    public static void main(String[] args) {
        // Test your merge sort implementation here!
        MyLinkedList list = new MyLinkedList();
        list.addLast(3);
        list.addLast(2);
        list.addLast(8);
        list.addLast(10);
        list.addLast(5);
        System.out.println("Before list.mergeSort()...");
        System.out.println(list);
        list.mergeSort();
        System.out.println("\nAfter list.mergeSort()...");
        System.out.println(list);
    }

} // End of MyLinkedList class
