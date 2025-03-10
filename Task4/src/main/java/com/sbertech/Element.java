package com.sbertech;

public class Element {
    Object data;
    Element next;
    Element prev;

    public Element() {
        this.data = null;
        this.next = null;
        this.prev = null;
    }
    public Element(Object data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

}
