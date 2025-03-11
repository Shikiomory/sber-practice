package com.sbertech;

public interface ListFunctions {
    void push_back(Object data);
    void pop_back();
    void push_front(Object data);
    void pop_front();
    int size();
    void print();
    void clear();
    //void copy(LinkedList list)
    Object get_front();
    Object get_back();
    Object get_element(int index);
    void insert(int index, Object data);
}
