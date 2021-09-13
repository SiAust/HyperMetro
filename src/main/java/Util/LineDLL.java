package Util;

import Model.Station;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class LineDLL<E extends Station> extends DoublyLinkedList<E> {

    List<E> stations;
    String lineName;

    public LineDLL(List<E> stations, String lineName) {
        super();
        this.stations = stations;
        this.lineName = lineName;
        createLineDLL();
    }

    private void createLineDLL() {
        /* Add stations to DLL */
        for (E station : stations) {
//                System.out.println(station);
            super.addLast(station);
        }
//        System.out.println(this);
    }

    public String getLineName() {
        return lineName;
    }

    public List<E> getStations() {
        return stations;
    }

    /**
     * Adds the element to the start of the line, after the depot
     * */
    @Override
    public void addFirst(E elem) {
        Node<E> temp = new Node<>(elem, super.getHead().next, super.getHead());
        if (super.getSize() == 0) {
            super.setHead(temp);
            super.setTail(temp);
        } else {
            super.getHead().next.prev = temp;
            super.getHead().next = temp;
        }
        super.setSize(super.getSize() + 1);
    }

    /**
     * Adds the element to the end of the line, before the depot
     * */
    @Override
    public void addLast(E elem) {
        Node<E> temp = new Node<>(elem, super.getTail(), super.getTail().prev);
        if (super.getSize() == 0) {
            super.setHead(temp);
            super.setTail(temp);
        } else {
            super.getTail().prev.next = temp;
            super.getTail().prev = temp;
        }
        super.setSize(super.getSize() + 1);
    }

    /**
     * Removes a station by name from the LineDLL
     * */
    public void remove(String stationName) { // TODO station doesn't exist?
       Node<E> temp = super.getHead();
       while (temp.next != null) {
           if (temp.value.getName().equals(stationName)) {
               super.remove(temp);
               return;
           }
           temp = temp.next;
       }
    }

    @Override
    public String toString() {
        Node<E> temp = super.getHead();
        StringBuilder result = new StringBuilder();

        do {
            result.append(temp.value).append("\n");
            temp = temp.next;
        }  while (temp != null);

        return result.toString();
    }
}
