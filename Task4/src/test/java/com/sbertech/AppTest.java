package com.sbertech;

import static org.junit.jupiter.api.Assertions.*;

import com.sbertech.object.LinkedList;
import org.junit.jupiter.api.Test;


/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void push_back() {
        LinkedList ls = new LinkedList();
        ls.push_front(1);
        ls.push_front(2);
        ls.push_back(3);
        Object answer = ls.get_back();
        Object expected = 3;
        assertEquals(expected, answer);

        ls.pop_back();
        answer = ls.get_back();
        expected = 1;
        assertEquals(expected, answer);
    }

    @Test
    public void push_front() {
        LinkedList ls = new LinkedList();
        ls.push_back(1);
        ls.push_back(2);
        ls.push_front(3);
        Object answer = ls.get_front();
        Object expected = 3;
        assertEquals(expected, answer);

        ls.pop_front();
        answer = ls.get_front();
        expected = 1;
        assertEquals(expected, answer);
    }

    @Test
    public void get_element() {
        LinkedList ls = new LinkedList();
        ls.push_back(1);
        ls.push_front(2);
        ls.insert(1,3);
        Object answer = ls.get_element(1);
        Object expected = 3;
        assertEquals(expected, answer);
    }

    @Test
    public void size() {
        LinkedList ls = new LinkedList();
        ls.push_back(1);
        ls.push_front(2);
        ls.insert(1,3);
        int answer = ls.size();
        int expected = 3;
        assertEquals(expected, answer);

        ls.pop_front();
        answer = ls.size();
        expected = 2;
        assertEquals(expected, answer);
    }

    @Test
    public void delete() {
        LinkedList ls = new LinkedList();
        ls.push_back(1);
        ls.push_front(2);
        ls.insert(1,3);
        ls.delete(1);

        Object[] answer = ls.to_array();
        Object[] expected = {2, 1};
        assertArrayEquals(expected, answer);

    }

    @Test
    public void copy() {
        LinkedList ls = new LinkedList();
        ls.push_back(1);
        ls.push_front(2);
        ls.insert(1,3);

        Object[] expected = ls.to_array();
        LinkedList ls2 = new LinkedList(ls);
        Object[] answer = ls2.to_array();

        assertArrayEquals(expected, answer);
    }

    @Test
    public void clear() {
        LinkedList ls = new LinkedList();
        ls.push_back(1);
        ls.push_front(2);
        ls.insert(1,3);
        ls.clear();

        Object[] answer = ls.to_array();
        Object[] expected = {};

        assertArrayEquals(expected, answer);
    }
}
