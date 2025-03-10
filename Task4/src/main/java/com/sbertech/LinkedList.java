package com.sbertech;

public class LinkedList implements ListFunctions{
    private Element head;
    private Element tail;
    private int size = 0;

    @Override
    public void push_front(Object data) {
        Element elem = new Element(data);
        if (size == 0) {
            head = elem;
            tail = elem;
        }
        else {
            head.prev = elem;
            elem.next = head;
            head = elem;
        }
        size++;
    }

    @Override
    public void pop_front() {
        if (size != 0) {
            Element elem = new Element();
            elem = head.next;
            head = elem;
            size--;
        }
    }

    @Override
    public void push_back(Object data) {
        Element elem = new Element(data);
        if (size == 0) {
            head = elem;
            tail = elem;
        }
        else {
            elem.prev = tail;
            tail.next = elem;
            tail = elem;
        }
        size++;
    }

    @Override
    public void pop_back() {
        if (size != 0) {
            Element elem = new Element();
            elem = tail.prev;
            tail = null;
            tail = elem;
            size--;
        }
    }

    @Override
    public Object get_front() {
        return size == 0 ? null : head.data;
    }

    @Override
    public Object get_back() {
        return size == 0 ? null :tail.data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        int i = 0;
        Element elem = new Element();

        while (i < size) {
            elem = head;
            head = head.next;
            elem = null;
        }

        size = 0;
    }

//    @Override
//    public void copy(LinkedList list) {
//        int i = 0;
//        Element elem = new Element();
//        while (i < list.size()) {
//            this.push_back(elem.data);
////            head = head.next;
//            elem = list.next_node(elem);
//            i++;
//        }
//    }


    @Override
    public void insert(int index, Object data) {
        Element elem  = new Element(data);

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        else if (index == 0) {
            this.push_front(data);
        }
        else if (index == size ) {
            this.push_back(data);
        }
        else {
            Element currentNode = new Element();
            currentNode = head;
            int i = 0;
            while (i < index) {
                currentNode = currentNode.next;
                i++;
            }

            elem.next = currentNode;
            elem.prev = currentNode.prev;
            currentNode.prev.next = elem;
            currentNode.prev = elem;
            size++;
        }
    }

    @Override
    public void print() {
        int i = 0;
        Element currentNode = new Element();
        currentNode = head;
        while (i < size) {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.next;
            i++;
        }
        System.out.println();
    }
}
