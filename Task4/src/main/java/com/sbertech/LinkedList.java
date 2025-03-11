package com.sbertech;

public class LinkedList implements ListFunctions{
    private Node head;
    private Node tail;
    private int size;

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public LinkedList(LinkedList list) {
        size = 0;
        this.copy(list);
    }

    private Node get_node(int index) {
        Node currentNode;

        if (index <= size / 2) {
            currentNode = head;

            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }

        }

        else {
            currentNode = tail;

            for (int i = size - 1; i > index; i--) {
                currentNode = currentNode.prev;
            }
        }
        return currentNode;
    }

    @Override
    public void push_front(Object data) {
        Node elem = new Node(data);
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
        try {
            if (size == 0) {
                throw new IllegalStateException("pop_front: Список пуст");
            }
            else if (size == 1) {
                head = null;
                tail = null;
            }
            else {
                head = head.next;
                head.prev = null;
            }
            size--;
        } catch (IllegalStateException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    @Override
    public void push_back(Object data) {
        Node elem = new Node(data);
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
        try {
            if (size == 0) {
                throw new IllegalStateException("pop_front: Список пуст");
            }
            else if (size == 1) {
                head = null;
                tail = null;
            }
            else {
                tail = tail.prev;
                tail.next = null;
            }
            size--;
        } catch (IllegalStateException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    @Override
    public Object get_front() {
        return size == 0 ? null : head.data;
    }

    @Override
    public Object get_back() {
        return size == 0 ? null : tail.data;
    }

    @Override
    public Object get_element(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node currentNode = get_node(index);

        return currentNode.data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void copy(LinkedList list) {
        this.clear();
        this.head = list.head;
        this.tail = list.tail;
        Node currentNode = list.head;

        for (int i = 0; i < list.size(); i++) {
            this.push_back(currentNode.data);
            currentNode = currentNode.next;
        }
    }


    @Override
    public void insert(int index, Object data) {
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
            Node elem  = new Node(data);
            Node currentNode = get_node(index);

            elem.next = currentNode;
            elem.prev = currentNode.prev;
            currentNode.prev.next = elem;
            currentNode.prev = elem;
            size++;
        }
    }
    @Override
    public void delete(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        else if (index == 0) {
            this.pop_front();
        }

        else if (index == size ) {
            this.pop_back();
        }

        else {
            Node currentNode = get_node(index);

            currentNode.prev.next = currentNode.next;
            currentNode.next.prev = currentNode.prev;
            currentNode = null;
            size--;
        }
    }

    @Override
    public void print() {
        if (size == 0) return;

        Node currentNode = head;

        for (int i = 0; i < size; i++) {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.next;
        }

        System.out.println();

    }

    @Override
    public Object[] to_array() {

        Object[] array = new Object[size];

        Node currentNode = head;

        for (int i = 0; i < size; i++) {
            array[i] = currentNode.data;
            currentNode = currentNode.next;
        }
        return array;
    }
}

class Node {
    Object data;
    Node next;
    Node prev;

    public Node() {
        this.data = null;
        this.next = null;
        this.prev = null;
    }
    public Node(Object data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

}
