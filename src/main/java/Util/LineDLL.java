package Util;

import Model.Line;
import Model.Station;

import java.util.List;

public class LineDLL<E extends Station> extends DoublyLinkedList<E> {

    List<E> stations;

    public LineDLL(List<E> stations) {
        super();
        this.stations = stations;
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
