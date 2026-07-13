package com.practice.boot;

import javax.swing.tree.TreeNode;
import java.util.*;

public class TreeNodeTest {

      public static class TreeNode { int val;
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
        TreeNode root = new TreeNode(10,
                new TreeNode(5,
                        new TreeNode(3,
                                new TreeNode(3),
                                new TreeNode(-2)
                        ),
                        new TreeNode(2,
                                null,
                                new TreeNode(1)
                        )
                ),
                new TreeNode(-3,
                        null,
                        new TreeNode(11)
                )
        );
        TreeNode root2 = new TreeNode(4);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(7);
        root2.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(3);


         Map<Integer, List<Integer>> adjList = new HashMap<>();
        adjList.computeIfAbsent(1,k->new ArrayList<>()).add(2);

    }

    private static void preOrder(TreeNode root){
          if(root == null){
              return;
          }
        System.out.println("preOrder"+root.val);
        preOrder(root.left);
        preOrder(root.right);
    }
    // 二叉搜索树的查找key
    private static TreeNode inOrder(TreeNode root,int key){
        if(root == null){
            return null;
        }
        while(root.val != key){
            root = root.val > key ? root.left: root.right;
        }

        return root;


    }

    private static void postOrder(TreeNode root){
        if(root == null){
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println("postOrder=="+root.val);
    }




    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i = 0;i < size;i++){

                TreeNode node = queue.poll();

                if(node.left !=null){
                    queue.offer(node.left);
                }
                if(node.right !=null){
                    queue.offer(node.right);
                }
                if(i == size-1){
                    res.add(node.val);
                }
            }
        }
        return res;

    }


    public static String serialize(TreeNode root) {
        if (root == null) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append("null,");
            } else {
                sb.append(node.val).append(",");
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        // 去掉末尾多余的 null
        String result = sb.toString().replaceAll("(,?null)+,$", "]");
        return result.endsWith("]") ? result : result.substring(0, result.length()-1) + "]";
    }



     static int ans = 0;
      static int longestZigZag(TreeNode root) {
        dfs(root, "left",  0,"root");
        dfs(root, "right", 0,"root");
        return  ans;
    }

   static void dfs(TreeNode node, String direction, int length,String path){
        if(node == null) return;
        ans = Math.max(ans, length);

        System.out.println("path ="+path + ",dir = "+ direction+",length ="+length);

        if("left".equals(direction)){
            dfs(node.right, "right", length+1, path + "->R");
            dfs(node.left,  "left",  1, path + "->L");
        } else {
            dfs(node.left,  "left",  length+1, path + "->L");
            dfs(node.right, "right", 1, path + "->R");
        }
    }


    public static int pathSum(TreeNode root, int targetSum) {
          Map<Integer,Integer> map = new HashMap<>();
          map.put(0,1);
          int currentSum = 0;
        int dfs = dfs(root, currentSum, targetSum, map);

        return dfs;
    }

    static int dfs(TreeNode root,int currentSum,int targetSum,Map<Integer,Integer> map){
        if(root == null){
            return 0;
        }
        int count = 0;
        currentSum += root.val;

        int subSum = currentSum- targetSum;

        //查询map，计算count
        if(map.containsKey(subSum)){
            count += map.get(subSum);
        }

        //更新map
        map.put(currentSum, map.getOrDefault(currentSum,0) + 1 );

        int left = dfs(root.left,currentSum,targetSum,map);

        int right =  dfs(root.right,currentSum,targetSum,map);

        //更新map
        map.put(currentSum, map.getOrDefault(currentSum,0) -1);

        return count + left + right;
    }

    public static int subarraySum(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0;
        int prefix = 0;
        for (int j = 0; j < nums.length; j++) {
            prefix += nums[j];  // 这里 prefix 相当于 prefix[j+1]
            // 1. 查 map，累加 count
            int subSum = prefix -k;

            if(map.containsKey(subSum)){
                count += map.get(subSum);
            }
            // 2. 更新 map
            map.put(prefix, map.getOrDefault(prefix,0) + 1 );

        }
        return count;
    }



//   static int count = 0;
    //计算当前节点和它的左右节点和
   static int countCurrent(TreeNode root,int targetSum,int currentSum){
        if(root == null){
            return 0;
        }

        currentSum = root.val + currentSum;
        int count = currentSum == targetSum ? 1 :0;

        int L = countCurrent(root.left,targetSum,currentSum);
        int R = countCurrent(root.right,targetSum,currentSum);
        return count + L +R ;
    }
}
