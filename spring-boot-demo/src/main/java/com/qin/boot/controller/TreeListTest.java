package com.qin.boot.controller;

import java.util.*;

public class TreeListTest {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {

        // 构造树1:  叶值序列应为 [3, 4]
        //      1
        //     / \
        //    4   5
        //   / \
        //  6   8
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(5);
        root1.left.left = new TreeNode(6);
        root1.left.right = new TreeNode(8);

        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(1);
        deque.offer(2);

       /* System.out.println("队列头部的值="+deque.peek()); //1
        System.out.println("移除头部的值="+deque.poll());//1
        System.out.println("number= "+ deque.size());

        for (Integer i : deque) {
            System.out.println("队列中的值"+i);
        }*/

        Deque<Integer> array = new ArrayDeque<>();
        array.push(1);
        array.push(2);
        System.out.println("顶部的元素，即出口 "+array.peek()); // 2
        Integer pop = array.pop();
        System.out.println("跳出元素 "+pop);
        for (Integer i : array) {
            System.out.println(i);
        }


    }

    public static int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        System.out.println("root ->"+root.val);

        int left = maxDepth(root.left);
        System.out.println("left ->"+left);

        int right = maxDepth(root.right);
        System.out.println("right ->"+right);

        return Math.max(left,right) +1;

    }

/*
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = collect(root1);
        List<Integer> list2 = collect(root2);

        return list1.equals(list2);

    }*/

    public static int goodNodes(TreeNode root) {
        return collect2(root, root.val);
    }

    static int goodNodes = 0;
    static int collect2(TreeNode root,int preMax){
        if(root == null){
            return 0;
        }

        if(preMax <= root.val){
            goodNodes++;
        }
        preMax = Math.max(preMax,root.val);

        collect2(root.left,preMax);
        collect2(root.right,preMax);

        return goodNodes;

    }

    List<Integer> collect(TreeNode node, int depth) {
        String indent = "  ".repeat(depth);  // 按深度缩进
        if (node == null) {
            System.out.println(indent + "null -> []");
            return new ArrayList<>();
        }
        System.out.println(indent + "进入 node=" + node.val);
        List<Integer> list = new ArrayList<>();
        if (node.left == null && node.right == null) {
            System.out.println(indent + "  叶子! 收集 " + node.val);
            list.add(node.val);
        }
        List<Integer> L = collect(node.left, depth + 1);
        List<Integer> R = collect(node.right, depth + 1);
        list.addAll(L);
        list.addAll(R);
        System.out.println(indent + "node=" + node.val + " 返回 " + list);
        return list;
    }
}
