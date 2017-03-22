package com.seeyon.Btree;

import org.junit.Test;

import java.util.Stack;

/**
 * Created by yangyu on 2017/3/2.
 */
public class TraverseBtree {

    /**
     * 先序遍历
     *
     * @param args
     */
    public static void main(String[] args) {
        Stack<Btree.Node> stack = new Stack<>();
        for (Btree.Node node = new Btree().getRoot(); !stack.empty() || node != null; ) {

            if (node == null)
                node = stack.pop();

            node.visit();

            if (node.getRight() != null) {
                stack.push(node.getRight());
            }

            node = node.getLeft();

        }
    }

    @Test
    public void first(){
        visitFirst(new Btree().getRoot());
    }

    public void visitFirst(Btree.Node node){
        if (node!=null){
            node.visit();
            visitFirst(node.getLeft());
            visitFirst(node.getRight());
        }
    }

    /**
     * 中序遍历
     */
    @Test
    public void mid() {
        Stack<Btree.Node> stack = new Stack<>();
        for (Btree.Node node = new Btree().getRoot(); !stack.empty() || node != null; ) {

            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }

            if (!stack.empty()){
                node = stack.pop();
                node.visit();
                node = node.getRight();
            }
        }

    }

    @Test
    public void last(){

    }
}
