package com.sbertech;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        LinkedList ls = new LinkedList();
        LinkedList ls2 = new LinkedList();
        ls.push_back(4);
        ls.push_back(3);
        ls.pop_front();
        ls.push_front(2);
        ls.insert(2, 10);
        ls2.copy(ls);
        ls2.pop_back();
        System.out.println(ls.get_element(2));
        System.out.printf("Размер списка: %d\n",ls.size());
        ls.print();
        ls2.print();
    }
}
