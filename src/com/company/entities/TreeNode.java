package com.company.entities;

import java.util.Comparator;

public class TreeNode<E> implements Comparable<TreeNode>{
    protected E element;
    protected TreeNode<E> left;
    protected TreeNode<E> right;
    protected int size;

    public TreeNode(E e) {
        this.element = e;
    }

    public TreeNode<E> getLeft() {
        return left;
    }

    public TreeNode<E> getRight() {
        return right;
    }

    public E getElement() {
        return element;
    }

    public int getSize() {
        return size;
    }

    @Override
    public int compareTo(TreeNode o) {
        if((int)this.element == (int)o.element) return 0;

        if((int)this.element > (int)o.element) return 1;

        else return -1;
    }
}
