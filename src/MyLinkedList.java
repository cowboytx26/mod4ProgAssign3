/*
Short Description:  This class will create a TwoWayLinkedList by creating a list with nodes that point to the next
                    node and the previous node.
Author:  Brian Wiatrek
Date:  September 15, 2024
*/
import java.util.ListIterator;

class TwoWayLinkedList<E> implements MyList<E> {
    private Node<E> head, tail;
    private int size=0;

    /** Create a default list */
    public TwoWayLinkedList() {
    }

    /** Create a list from an array of objects */
    public TwoWayLinkedList(E[] objects) {
        for (E e : objects) {
            add(e);
            size++;
        }
    }

    /** Return the head element in the list */
    public E getFirst() {
        if (size == 0) {
            return null;
        } else {
            return head.element;
        }
    }

    /** Return the last element in the list */
    public E getLast() {
        if (size == 0) {
            return null;
        } else {
            return tail.element;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", "); // Separate two elements with a comma
            } else {
                result.append("]"); // Insert the closing ] in the string
            }
        }

        return result.toString();
    }

    /** Clear the list */
    public void clear() {
        head = tail = null;
    }

    /** Return true if this list contains the element o */
    public boolean contains(Object e) {
        System.out.println("Implementation left as an exercise");
        return true;
    }

    /** Return the element from this list at the specified index */
    public E get(int index) {
        System.out.println("Implementation left as an exercise");
        return null;
    }

    /**
     * Return the index of the head matching element in this list. Return -1 if
     * no match.
     */
    public int indexOf(Object e) {
        System.out.println("Implementation left as an exercise");
        return 0;
    }

    /**
     * Return the index of the last matching element in this list Return -1 if
     * no match.
     */
    public int lastIndexOf(Object e) {
        System.out.println("Implementation left as an exercise");
        return 0;
    }

    /**
     * Replace the element at the specified position in this list with the
     * specified element.
     */
    public E set(int index, E e) {
        System.out.println("Implementation left as an exercise");
        return null;
    }

    private class LinkedListIterator implements java.util.ListIterator<E> {
        private Node<E> current = head; // Current index

        public LinkedListIterator() {
        }

        public LinkedListIterator(int index) {
            if (index < 0 || index >= size)
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
                        + size);
            for (int nextIndex = 0; nextIndex < index; nextIndex++) {
                current = current.next;
            }
        }

        public void setLast() {
            current = tail;
        }

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public E next() {
            E e = current.element;
            current = current.next;
            return e;
        }

        @Override
        public void remove() {
            size--;
            System.out.println("Implementation left as an exercise");
        }

        @Override
        public void add(E e) {
            size++;
            System.out.println("Implementation left as an exercise");
        }

        @Override
        public boolean hasPrevious() {
            return current != null;
        }

        @Override
        public int nextIndex() {
            System.out.println("Implementation left as an exercise");
            return 0;
        }

        @Override
        public E previous() {
            E e = current.element;
            current = current.previous;
            return e;
        }

        @Override
        public int previousIndex() {
            System.out.println("Implementation left as an exercise");
            return 0;
        }

        @Override
        public void set(E e) {
            current.element = e; // TODO Auto-generated method stub
        }
    }

    public class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;

        public Node(E o) {
            element = o;
        }
    }

    @Override
    public int size() {
        return size;
    }

    public LinkedListIterator listIterator() {
        return new LinkedListIterator();
    }

    public LinkedListIterator listIterator(int index) {
        return new LinkedListIterator(index);
    }

    //remove the return value of Iterator and made it ListIterator (which is what is implemented above)
    @Override
    public ListIterator<E> iterator() {
        return new LinkedListIterator();
    }

    /** Add an element to the beginning of the list */
    public void addFirst(E e) {
        // Write your code here
        if (size == 0) {
            this.head = new Node<>(e);
            this.tail = head;
            this.head.previous = null;
            this.head.next = null;
            size++;
        } else {
            Node<E> tempNode = new Node<>(e);
            this.head.previous = tempNode;
            tempNode.next = this.head;
            this.head = tempNode;
            size++;
        }
    }

    /** Add an element to the end of the list */
    public void addLast(E e) {
        // Write your code here
        if (size == 0) {
            this.tail = new Node<>(e);
            this.head = tail;
            this.head.previous = null;
            this.head.next = null;
            size++;
        } else {
            Node<E> tempNode = new Node<>(e);
            this.tail.next = tempNode;
            tempNode.previous = this.tail;
            this.tail = tempNode;
            size++;
        }
    }

    /**
     * Add a new element at the specified index in this list The index of the
     * head element is 0
     */
    public void add(int index, E e) {
        //This should be a for loop that counts through each Node; when I get to the count at which point
        //the element should be inserted, I will create a new node and modify the appropriate next and previous
        //of the nodes (both before and after).  I'll need to check whether Index = 0; if so, insert first.
        //I'll also need to check whether index = size; if so, insert last.
        if (index==0) addFirst(e);
        else if (index == size) addLast(e);
        else {
            Node<E> currentNode = this.head;
            for (int i=0; i < index; i++){
                currentNode = currentNode.next;
            }
            Node<E> tempNew = new Node<>(e);
            currentNode.previous.next = tempNew;
            tempNew.previous = currentNode.previous;
            tempNew.next = currentNode;
            currentNode.previous = tempNew;
            size++;
        }
    }

    /**
     * Remove the head node and return the object that is contained in the
     * removed node.
     */
    public E removeFirst() {
        //First check to see if there are any nodes in the list.  if size == 0, then just return null; otherwise,
        //save off the current head and then make the 2nd item in the list the new head.
        if (size == 0) return null;
        else {
            Node<E> tempFirst = this.head;
            this.head = this.head.next;
            this.head.previous = null;
            size--;
            return tempFirst.element;
        }
    }

    /**
     * Remove the last node and return the object that is contained in the
     * removed node.
     */
    public E removeLast() {
        //First check to see if there are any nodes in the list.  If size == 0, then just return null; otherwise,
        //save off the current tail and then make the penultimate item in the list the new tail.
        if (size == 0) return null;
        else {
            Node<E> tempLast = this.tail;
            this.tail = this.tail.previous;
            this.tail.next = null;
            size--;
            return tempLast.element;
        }
    }

    /**
     * Remove the element at the specified position in this list. Return the
     * element that was removed from the list.
     */
    public E remove(int index) {
        //If the index is 0, then just remove the head of the list.  If the index is equal to zero, just remove the
        //first node.  If the index is equal to size - 1, just remove the tail.  If an index is passed that is less
        //than zero or greater than or equal to size, throw an index out of bounds exception.  Otherwise, step
        //through the list and remove the requested node by making sure the previous node's next field points to the
        //removed node's next node.  Also, ensure the requested node's previous node's next points to the requested
        //node's next node.
        E retElement = null;
        if (index==0) retElement = removeFirst();
        else if (index == size - 1) retElement = removeLast();
        else if ((index < 0) || (index >= size)) throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
                + size);
        else {
            Node<E> currentNode = this.head;
            for (int i=0; i < index; i++){
                currentNode = currentNode.next;
            }
            currentNode.previous.next = currentNode.next;
            currentNode.next.previous = currentNode.previous;
            retElement = currentNode.element;
            size--;
        }
        return retElement;
    }
}