package com.company;

// i - is a Node index
// 2*i - is a left child index
// 2*i+1 - is a right child index
// floor(i/2) - is parent node index

public class MaxHeap {

    private final Post[] heap;
    private int size;

    public MaxHeap (int height) {
        heap = new Post[(int) Math.pow(2, height + 1) + 1];
        size = 1;
    }

    public void add (Post post) {
        heap[size] = post;

        int i = size;
        while (i > 0) {
//            System.out.println(heap[i].toString());
            if (i / 2 > 0 && heap[i].getNumOfLikes() > heap[i / 2].getNumOfLikes()) {
                Post temp = heap[i];
                heap[i] = heap[i / 2];
                heap[i / 2] = temp;
            }else {
                break;
            }
            i /= 2;
        }
        size++;
    }

    public Post poll () {
        Post res = heap[1];
        heap[1] = heap[size-1];
        heap[size-1] = null;
        size--;

        int i = 1;
        while (i * 2 + 1 < size) {
            int j;

            if (heap[2 * i].getNumOfLikes() >= heap[2 * i + 1].getNumOfLikes())
                j = 2 * i;
            else
                j = 2 * i + 1;

            if (heap[i].getNumOfLikes() < heap[j].getNumOfLikes()) {
                Post temp = heap[i];
                heap[i] = heap[j];
                heap[j] = temp;
                i = j;
            } else
                break;


        }

        return res;
    }

    public void print () {
        for (Post post : heap) {
            if (post != null) System.out.println(post.toString());
        }
    }
}
