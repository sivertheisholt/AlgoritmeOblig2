package com.company.entities;

import java.util.ArrayList;
import java.util.Iterator;

public class AVLTree<E extends Comparable<E>> extends BST<E>{

    public AVLTree() {
        super();
    }

    public AVLTree(E[] objects) {
        super(objects);
    }

    @Override
    protected AVLTreeNode<E> createNewNode(E e) {
        return new AVLTreeNode<E>(e);
    }

    @Override
    public boolean insert(E e) {
        boolean successful = super.insert(e);
            if(!successful) {
                System.out.println("Failed");
                return false;
            } else {
                System.out.println("Balancing tree");
                balancePath(e);
            }
            return true;
    }

    private void updateHeight(AVLTreeNode<E> node ){
        if(node.left == null && node.right == null)
            node.height = 0;
        else if(node.left == null)
            node.height = 1 + ((AVLTreeNode<E>)(node.right)).height;
        else if(node.right == null)
            node.height = 1 + ((AVLTreeNode<E>)(node.left)).height;
        else
            node.height = 1 +
                    Math.max(((AVLTreeNode<E>)(node.right)).height,
                    ((AVLTreeNode<E>)(node.left)).height);
    }

    private void balancePath(E e) {
        //Skaffer nodes
        ArrayList<TreeNode<E>> path = path(e);

        //Looper imellom nodes
        for(int i = path.size() - 1; i >= 0; i--) {

            //Skaffer node A
            AVLTreeNode<E> A = (AVLTreeNode<E>)(path.get(i));

            //Oppdaterer node A
            updateHeight(A);

            //Skaffer parent for A
            AVLTreeNode<E> parentOfA = (A == root) ? null : (AVLTreeNode<E>) (path.get(i - 1));

            //Balanserer
            switch(balanceFactor(A)) {
                case -2:
                    if(balanceFactor((AVLTreeNode<E>)A.left) <= 0) {
                        balanceLL(A, parentOfA);
                    }
                    else {
                        balanceLR(A, parentOfA);
                    }
                    break;
                case +2:
                    if(balanceFactor((AVLTreeNode<E>) A.right) >= 0) {
                        balanceRR(A, parentOfA);
                    }
                    else {
                        balanceRL(A, parentOfA);
                    }
            }
        }
    }

    private ArrayList<TreeNode<E>> path(E e) {
        ArrayList<TreeNode<E>> output = new ArrayList<>();
        getAncestors(this.root, e, output);


        /** PRINT DEBUG */

        Iterator<TreeNode<E>> debug = output.iterator();

        System.out.println("Start");
        while(debug.hasNext()) {
            System.out.println(debug.next().element);
        }
        System.out.println("End");
        System.out.println();

        return output;
    }

    public ArrayList<TreeNode<E>> getNodes() {
        ArrayList<TreeNode<E>> output = new ArrayList<>();
        getNode(root, output);
        return output;
    }
    public void getNode(TreeNode<E> node, ArrayList<TreeNode<E>> output) {
        if(node.left != null) {
            output.add(node.left);
            getNode(node.left, output);
        }
        if(node.right != null) {
            output.add(node.right);
            getNode(node.right, output);
        }
    }

    private boolean getAncestors(TreeNode<E> node, E e, ArrayList<TreeNode<E>> output) {
        //First we need to check node is null
        if(node == null) {
            return false;
        } else {

            //Need to check if we found the element
            if(e.compareTo(node.element) == 0) {
                return true;
            }
            output.add(node);

            //Recursion ->
            boolean isLeft = getAncestors(node.left, e, output);
            if(isLeft) {
                return true;
            }
            boolean isRight = getAncestors(node.right, e, output);
            if(isRight){
                return true;
            }
            output.remove(node);
            return false;
        }
    }

    private int balanceFactor(AVLTreeNode<E> node ) {
        if(node.right == null)
            return -node.height;
        else if (node.left == null)
            return +node.height;
        else
            return ((AVLTreeNode<E>)node.right).height -
                    ((AVLTreeNode<E>)node.left).height;
    }

    private void balanceLL(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.left;

        if(A == root) {
            root = B;
        } else {
            if(parentOfA.left == A) {
                parentOfA.left = B;
            } else {
                parentOfA.right = B;
            }
        }

        A.left = B.left;
        B.right = A;
        updateHeight((AVLTreeNode<E>) A);
        updateHeight((AVLTreeNode<E>) B);
    }

    private void balanceLR(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.left;
        TreeNode<E> C = B.right;

        if(A == root) {
            root = C;
        } else {
            if(parentOfA.left == A) {
                parentOfA.left = C;
            } else {
                parentOfA.right = C;
            }
        }

        A.left = C.right;
        B.right = C.left;
        C.left = B;
        C.right = A;

        updateHeight((AVLTreeNode<E>) A);
        updateHeight((AVLTreeNode<E>) B);
        updateHeight((AVLTreeNode<E>) C);
    }


    private void balanceRR(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.right;

        if(A == root) {
            root = B;
        } else {
            if(parentOfA.left == A) {
                parentOfA.left = B;
            } else {
                parentOfA.right = B;
            }
        }
        A.right = B.left;
        B.left = A;
        updateHeight((AVLTreeNode<E>) A);
        updateHeight((AVLTreeNode<E>) B);
    }
    private void balanceRL(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.right;
        TreeNode<E> C = B.left;

        if(A == root) {
            root = C;
        } else {
            if(parentOfA.left == A) {
                parentOfA.left = C;
            } else {
                parentOfA.right = C;
            }
        }

        A.right = C.left;
        B.left = C.right;
        C.left = A;
        C.right = B;

        updateHeight((AVLTreeNode<E>) A);
        updateHeight((AVLTreeNode<E>) B);
        updateHeight((AVLTreeNode<E>) C);
    }
    protected static class AVLTreeNode<E extends Comparable<E>> extends TreeNode<E> {
        protected int height = 0;

        public AVLTreeNode(E e){
            super(e);
        }
    }
}
