package com.company.systems;

import com.company.entities.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AksessSystem<E extends Comparable<E>> {
    public int minsteTall(ArrayList<TreeNode<E>> nodes, int number) {

        List<TreeNode<E>> list;
        TreeNode<E> node = null;

        //Start
        if(nodes.get(nodes.size()/2).getSize() == number) {
            return nodes.get(nodes.size()/2).getSize();
        }

        if(nodes.get(nodes.size()/2).getSize() < number){
            list = nodes.subList(0, (nodes.size()/2)-1);
        } else {
            list = nodes.subList((nodes.size()/2)+1, nodes.size());
        }

        //Next
        do {
            int index = list.size()/2;

            if(list.get(index).getSize() == number) {
                node = list.get(index);
            }

            if(list.get(index).getSize() < number){
                list = list.subList(0, index-1);
            } else {
                list = list.subList(index+1, nodes.size());
            }
        } while(node == null);
        return node.getSize();
    }
}