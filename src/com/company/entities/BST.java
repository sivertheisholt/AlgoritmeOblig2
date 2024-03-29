package com.company.entities;

import java.util.Collection;
import java.util.Iterator;

public class BST<E extends Comparable<E>> implements Tree<E> {
    //Root of the tree
    protected TreeNode<E> root;

    //Size of the tree
    protected int size = 0;

    //Default constructor
    public BST() {} // tom!

    //Constructor with objects
    public BST(E[] objects) {
        for (int i=0; i<objects.length; i++) {
            add(objects[i]);
        }
    }

    @Override
    public boolean search(E e) {
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0)
                current = current.left;
            else if (e.compareTo(current.element) > 0)
                current = current.right;
            else return true;
        }
        return false;
    }

    @Override
    public boolean insertMultiple(E[] objects) {
        boolean insert = false;
        for(E item : objects) {
            insert = insert(item);
        }
        return insert;
    }

    @Override
    public boolean insert(E e) {
        if (root == null) root = createNewNode(e);
        else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null)
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                }
                else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                }
                else return false; // Duplikat
            if (e.compareTo(parent.element) < 0) parent.left = createNewNode(e);
            else parent.right = createNewNode(e);
        }
        size++;
        return true;
    }

    protected TreeNode<E> createNewNode(E e) { // Intern hjelpemetode(?)
        return new TreeNode<>(e);
    }

    @Override
    public boolean delete(E e) {
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) { // Løkke for å finne slettenoden
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else break; // Hopp ut av løkka
        }
        if (current == null) return false; // Fann ikkje «e»
        // Case A: left == null
        if (current.left == null) {
            if (parent == null) root = current.right; // Skal slette rotnoden
            else {
                if (e.compareTo(parent.element) < 0)
                    parent.left = current.right;
                else parent.right = current.right;
            }
        }
        // Case B: right == null
                else if (current.right == null){
                    if (parent == null) root = current.left; // Skal slette rotnoden
                    else {
                        if (e.compareTo(parent.element) < 0)
                            parent.left = current.left;
                        else parent.right = current.left;
                    }
                }
        // Case C: left != null og right != null (Case 2 i boka)
        else {
            TreeNode<E> rightMost = current.left;
            while (rightMost.right != null) {
                parent = rightMost;
                rightMost = rightMost.right;
            }
            current.element = rightMost.element;
        // Slett rightMost:
            if (parent.right == rightMost) parent.right = rightMost.left;
            else parent.left = rightMost.left;
        }
        size--;
        return true;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new InorderIterator();
    }
    private class InorderIterator implements java.util.Iterator<E> {
        private java.util.ArrayList<E> list = new java.util.ArrayList<>();
        private int current = 0;

        public InorderIterator(){
            inorder(); // traverserer inorden og legger i «list»
        }
        private void inorder() {
            inorder(root);
        }

        private void inorder(TreeNode<E> root) {
            if (root == null) return;
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }
        @Override
        public boolean hasNext() {
            return current < list.size();
        }
        @Override
        public E next() {
            return list.get(current++);
        }
        @Override
        public void remove() { // Skal slette «current»
            if (current == 0) // next() har ikkje blitt kallt
                throw new IllegalStateException();
            delete(list.get(--current));
            list.clear();
            inorder(); // Bygg lista på nytt
        }
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    public TreeNode<E> getRoot() {
        return root;
    }
}
