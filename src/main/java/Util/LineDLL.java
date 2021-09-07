package Util;

import Model.Station;

import java.util.List;

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


    @Override
    public String toString() {
        Node<E> temp = super.getHead();
        StringBuilder result = new StringBuilder();

        do {
            for (int i = 0; i < 3;i++) {
                result.append(temp.value);
                if (i == 2) {
                    result.append("\n");
                } else {
                    result.append(" - ");
                }
                temp = temp.next;
            }
            temp = temp == null ? null : temp.prev.prev;
        }  while (temp != null);

        return result.toString();
    }
}
