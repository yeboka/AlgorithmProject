package com.company;

public class MyList <T>{
    private T[] list;
    private int size;

    public MyList() {
        this.list = (T[]) new Object[10];
        this.size = 0;
    }

    public void add (T value) {
        list[size++] = value;
        if (size == list.length) {
            T[] newList = (T[]) new Object[list.length * 2];
            for (int i = 0; i < list.length; i++) {
                newList[i] = list[i];
            }
            list = newList;
        }
    }

    public T[] getList() {
        return list;
    }

    public void remove (int index) {
        if (index < 0 || index >= list.length) return;

        if (list.length - 1 - index >= 0) System.arraycopy(list, index + 1, list, index, list.length - 1 - index);
        size--;
    }

    public void remove (T temp) {
        int index = -1;

        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(temp)) {
                index = i;
                break;
            }
        }
        remove(index);

    }

    public int size() {
        return size;
    }

    public T get(int index) {
        return list[index];
    }

    public boolean contains (T value) {
        for (T item:
             list) {
            if (value.equals(item)) return true;
        }
        return false;
    }
}
