package com.sbertech;
import com.sbertech.Object.LinkedList;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
//        LinkedList<Integer> ls = new LinkedList<>();
        LinkedList ls = new LinkedList();
        ls.push_back(4);
        ls.push_front(3);
        ls.insert(2,5);
//        ls.pop_back();
//        ls.delete(1);
//        System.out.println(ls.get_element(1));
//        ls.push_front(2);
//        ls.insert(2, 10);
//        ls2.copy(ls);
//        ls2.pop_front();
//        System.out.println(ls.get_element(1));
//        System.out.printf("Размер списка: %d\n",ls.size());
        ls.print();
//        Object[] array = ls.to_array();
//        ls.push_front(23);
//        ls.print();
//        LinkedList ls2 = new LinkedList(ls);
//        ls2.pop_back();
//        ls2.insert(0,5);
//        ls2.insert(1,2);
//        ls2.print();
    }
}
