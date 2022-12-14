package com.company;


import java.lang.invoke.MutableCallSite;

public class MyMap<K, V> {

    private ListNode<K, V>[] list;
    private final int LENGTH = 100;

    class ListNode<K, V> {
        private K key;
        private V val;

        private ListNode<K, V> next;

        ListNode () {}

        ListNode(K key, V val) {
            this.key = key;
            this.val = val;
            this.next = null;
        }

        public V getVal() {
            return val;
        }

        public void setVal(V val) {
            this.val = val;
        }
    }

    MyMap () {
        list = new ListNode[100];
    }

    public void put(K key, V val) {
        int hash = Math.abs(key.hashCode() % LENGTH);

        if (list[hash] == null) {
            list[hash] = new ListNode<K, V>(key, val);
        } else {
            ListNode<K, V> head = list[hash];
            while (head.next != null) {
                if (head.key.equals(key)) {
                    head.setVal(val);
                    return;
                }
                head = head.next;
            }

            head.next = new ListNode<>(key, val);
        }
    }

    public V get(K key) {
        int hash = Math.abs(key.hashCode() % LENGTH);


        if (list[hash] == null) return null;

        ListNode<K, V> head = list[hash];
        while (head != null) {
            if (head.key.equals(key)) {
                return head.getVal();
            }
            head = head.next;
        }
        return null;
    }

    public void remove(K key) {
        int hash = Math.abs(key.hashCode() % LENGTH);


        if (list[hash] != null) {
            ListNode<K, V> head = list[hash];
            if (head != null && head.next == null) {
                list[hash] = null;
                return;
            }
            while (head != null && head.next != null) {
                if (head.next.key.equals(key)) {
                    head.next = head.next.next;
                    return;
                }
                head = head.next;
            }
        }
    }

    public boolean containsKey(K key) {
        int hash = Math.abs(key.hashCode() % LENGTH);


        if (list[hash] != null) {
            ListNode<K, V> head = list[hash];
            while (head != null) {
                if (head.key.equals(key)) {
                    return true;
                }
                head = head.next;
            }
        }
        return false;
    }

    public MyList<K> keySet() {
        MyList<K> keys = new MyList<>();
        for(int i = 0; i < LENGTH; i++) {
            if(list[i] != null) {
                ListNode<K, V> head = list[i];
                while (head != null) {
                    keys.add(head.key);
                    head = head.next;
                }
            }
        }
        return keys;
    }
}
