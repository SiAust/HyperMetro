package Util;

import java.util.NoSuchElementException;

public class DoublyLinkedList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DoublyLinkedList() {
        size = 0;
    }

    public Node<E> getHead() {
        return head;
    }

    void setHead(Node<E> head) {
        this.head = head;
    }

    public Node<E> getTail() {
        return tail;
    }

    void setTail(Node<E> tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    void setSize(int size) {
        this.size = size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E elem) {
        Node<E> tmp = new Node<>(elem, head, null);

        if (size == 0) {
            head = tail = tmp;
        } else {
            head.prev = tmp;
            head = tmp;
        }
        size++;
    }

    public void addLast(E elem) {
        Node<E> tmp = new Node<>(elem, null, tail);

        if (size == 0) {
            head = tail = tmp;
        } else {
            tail.next = tmp;
            tail = tmp;
        }
        size++;
    }

    public void removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        if (size == 1) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
    }

    public void removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        if (size == 1) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
    }

    public void remove(Node<E> curr) {
        if (curr == null) {
            throw new NoSuchElementException();
        }

        if (curr.prev == null) {
            removeFirst();
            return;
        }
        if (curr.next == null) {
            removeLast();
            return;
        }

        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
        size--;
    }

//    @Override
//    public String toString() {
//        Node<E> temp = head;
//        StringBuilder result = new StringBuilder();
//
////        result.append("depot - ");
//        do {
//            for (int i = 0; i < 3;i++) {
//                result.append(temp.value);
//                if (i == 2) {
//                    result.append("\n");
//                } else {
//                    result.append(" - ");
//                }
//                temp = temp.next;
//            }
//            temp = temp == null ? null : temp.prev.prev;
//        }  while (temp != null);
////        result.append("depot");
//        return result.toString();
//    }


    static class Node<E> {
        final E value;
        Node<E> prev;
        Node<E> next;

        Node(E value, Node<E> next, Node<E> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        Node<E> getNext() {
            return next;
        }

        Node<E> getPrev() {
            return prev;
        }

        public E getValue() {
            return value;
        }

    }
}