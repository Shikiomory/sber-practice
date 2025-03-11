package com.sbertech.generic;

public interface ListFunctions<T> {
    void push_back(T data);
    void pop_back();
    void push_front(T data);
    void pop_front();
    int size();
    void print();
    void clear();
    void copy(LinkedList<? extends T> list);
    T get_front();
    T get_back();
    T get_element(int index);
    void insert(int index, T data);
    void delete(int index);
    T[] to_array();
}
