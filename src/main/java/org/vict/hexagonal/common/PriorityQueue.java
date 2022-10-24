package org.vict.hexagonal.common;

import java.util.ArrayList;
import java.util.List;

public class PriorityQueue<T extends Comparable> {
    List<T> data;
    public int getCount(){return data.size();}
    public PriorityQueue() {
        this.data = new ArrayList<>();
    }

    public void Enqueue(T item) {
        data.add(item);
        int childIndex = data.size() - 1;
        while (childIndex > 0) {
            int parentIndex = (childIndex - 1) / 2;
            if (data.get(childIndex).compareTo(data.get(parentIndex)) >= 0) {
                break;
            }
            T tmp = data.get(childIndex);
            data.set(childIndex,data.get(parentIndex));
            data.set(parentIndex,tmp);
            childIndex = parentIndex;
        }
    }

    public T Dequeue() {
        int lastIndex = data.size() - 1;
        T frontItem = data.get(0);

        data.set(0,data.get(lastIndex));
        data.remove(lastIndex);
        lastIndex--;
        int parentIndex = 0;
        while (true) {
            int childIndex = parentIndex * 2 + 1;
            if(childIndex > lastIndex) {
                break;
            }
            int rightChild = childIndex + 1;
            if(rightChild <= lastIndex && data.get(rightChild).compareTo(data.get(childIndex)) < 0) {
                childIndex = rightChild;
            }
            if (data.get(parentIndex).compareTo(data.get(childIndex)) <= 0) {
                break;
            }
            T tmp = data.get(parentIndex);
            data.set(parentIndex,data.get(childIndex));
            data.set(childIndex,tmp);

            parentIndex = childIndex;
        }
        return frontItem;
    }

    public T Peek() {
        T frontItem = data.get(0);
        return frontItem;
    }
    public boolean Contains(T item) {
        return data.contains(item);
    }
    public List<T> ToList() {
        return data;
    }

}
