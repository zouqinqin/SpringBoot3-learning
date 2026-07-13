package com.qin.boot.controller;

import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;


@RestController
public class TestController {
    private static int index = 0;

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }


    }
  // [1,3,5,2,4]
    public static void main(String[] args) {

        ListNode head = new ListNode(5,
                new ListNode(4,
                        new ListNode(2,
                                new ListNode(1))));

        /*ListNode head = new ListNode(1,
                new ListNode(100000));*/

        System.out.println(pairSum2(head));

       /* ListNode result = reverseList(head);
        while(result != null){
            System.out.print(result.val + " -> ");
            result = result.next;
        }*/


    }

    public static int pairSum2(ListNode head) {
        int l = 0;
        ListNode temp = head;
        while(head != null){
            l++;
            head = head.next;
        }

        int[] nums = new int[l];

        int i =0;
        while(temp != null){

           nums[i] = temp.val;

            temp = temp.next;
            i++;
        }

        int left = 0;
        int right = nums.length -1;
        int sumMax = 0;
        while(right > left){
            int vL = nums[left];
            int rL = nums[right];

            int pairSum = vL+rL;
            sumMax = Math.max(pairSum,sumMax);

            left++;
            right--;

        }
        return sumMax;
    }

    public static int pairSum(ListNode head) {

        ListNode slow = head,fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode pre = null;
        while(slow != null){
            ListNode next = slow.next;
            //反转
            slow.next =pre;

            pre = slow;
            slow = next;
        }
        int maxSum = 0;
        //两个指针同时向尾部移动，head和pre
        while( pre != null && head !=null ){
            int varSum = head.val + pre.val;
            maxSum = Math.max(varSum,maxSum);

            head = head.next;
            pre = pre.next;

        }
        return maxSum;

    }

    public  static ListNode reverseList(ListNode head) {
        if(head == null){
            return null;
        }

        ListNode pre = null;

        while(head != null){
            //保存next
           ListNode next = head.next;
           //反转
            head.next = pre;

            pre = head;
            head = next;

        }

        return pre;

    }

    public static ListNode oddEvenList(ListNode head) {
        ListNode odd  =  head;
        ListNode even = head.next;

        ListNode oddHead = odd;
        ListNode evenHead = even;

        while(odd.next!= null && even.next != null){
            odd.next = even.next;
            even.next = even.next.next;

            odd = odd.next;
            even = even.next;
        }

        odd.next = evenHead;

        return oddHead;

    }
    public static ListNode deleteMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        if(head.next == null){
            return null;
        }
        //往前遍历
        while( fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

        return head;

    }

    public static String decodeStringByCycle(String s) {
        int num = 0;

        StringBuilder result = new StringBuilder();

        while(index < s.length()){

           char c = s.charAt(index);

            if (Character.isDigit(c)) {
                num = num * 10 + (c -'0');

                index ++;
            }else if('[' == c){
                index++;
                String inner = decodeStringByCycle(s);
                for (int i = 0; i < num; i++) {
                    result.append(inner);
                }

                num = 0;
            }else if(']' == c){
                index++;
                return result.toString();
            }else{
                result.append(c);
                index++;
            }
        }

        return result.toString();

    }

  // s = 3[a2[c]]
    public static String decodeString(String s) {
        Deque<Integer> numDeque = new ArrayDeque<>();
        Deque<String> strDeque = new ArrayDeque<>();
        int num = 0;
        StringBuilder builder = new StringBuilder();

        for (char c : s.toCharArray()) {

            if (Character.isDigit(c)) {
                num = num * 10 + (c -'0');
            }else if('[' == c){
                numDeque.push(num);
                strDeque.push(builder.toString());

                num = 0;
                builder = new StringBuilder();
            }else if(']' == c){
               int k = numDeque.peek();
                numDeque.pop();
                StringBuilder temp = new StringBuilder(strDeque.peek());
                strDeque.pop();

                for(int i =0;i<k;i++){
                    temp.append(builder);
                }
                builder = temp;
            }else{
                builder.append(c);
            }
        }

        return builder.toString();

    }

    public static double findMaxAverage(int[] nums, int k) {

        int sum = 0;
        int n = nums.length;
        for(int i = 0;i <= k -1; i++){
            sum += nums[i];
        }
        int maxSum = sum;
        for (int j = 1; j < n - k +1; j++) {
            int temp  = sum - nums[j-1] + nums[k + j -1];
            sum = temp;
            if(temp > maxSum){
                maxSum = temp;
            }
        }

        return (double) maxSum / k;

    }
   // 快速排序

    public static void quickSort(int[] nums, int left, int right) {
        if (left >= right) return;

        int p = partition(nums, left, right);
        quickSort(nums, left, p - 1);
        quickSort(nums, p + 1, right);
    }

     static int  partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (nums[j] < pivot) {
                i++;
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }

        int temp = nums[i + 1];
        nums[i + 1] = nums[right];
        nums[right] = temp;

        return i + 1;
    }


    public int maxOperations(int[] nums, int k) {

        // 1,3,3,3,4 k =6
        int n = nums.length;
        int left =0;
        int right=n -1;
        int count =0;



        while(left < right){

            if(nums[left] + nums[right] == k){
                count++;
                left ++;
                right --;
            }else if(nums[left] + nums[right] < k){
                left ++;
            }else{
                right--;
            }
        }

        return count;
    }

    public static int maxArea(int[] height) {
        int n = height.length;
        int left =0;
        int right = n-1;
        int maxArea = 0;

        while(left < right && right < n){
            int areaTemp = (right - left) * Math.min(height[left],height[right]);
            if(areaTemp > maxArea){
                maxArea = areaTemp;
            }
            if(height[left] < height[right]){
                left ++;
            }else{
                right --;
            }
        }
        return maxArea;
    }

 /*   public int maxArea(int[] height) {
        int maxArea = 0;
        for(int i=0;i < height.length;i++){
            for(int j=i+1;j <height.length;j++){
                int lengthMin = Math.min(height[i], height[j]);
                int width = j-i;
                int tempArea = lengthMin * width;
                if(tempArea > maxArea){
                    maxArea = tempArea;
                }
            }
        }

        return maxArea;
    }*/


 //输入：s = "abc", t = "ahbgdc"
    public static boolean isSubsequence(String s, String t) {
        int i = 0;
        int j = 0;

        while(j < t.length() && i < s.length()){
            if(s.charAt(i) == t.charAt(j)){
                i++;
            }
            j++;
        }

        return i == s.length();

    }


    public static int[]  moveZeroes(int[] nums) {
        int read= 0;
        int write =0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if(nums[i] != 0){
                nums[write ++] = nums[i];
            }

        }

       return nums;
    }

    public static int compress(char[] chars) {
        int c = 1;
        int write = 0;

        if(chars.length == 1){
            return 1;
        }

        for (int i = 1; i < chars.length; i++) {
            if (chars[i - 1] == chars[i]) {
                c++;
            }
            if (chars[i - 1] != chars[i] || i == chars.length - 1) {

                // 第一步：写入字符（是哪个字符？）
                chars[write++] = chars[i - 1];

                // 第二步：如果 c > 1，写入次数
                // 注意：c 可能是多位数，比如 12 要拆成 '1' '2' 分别写
                if (c >= 10) { // 写入多位
                    for(char a : String.valueOf(c).toCharArray()){
                        chars[write++] = a;
                    }

                } else if(c >1){
                    chars[write++] = (char)('0' + c);
                }


                c = 1;

            }
            if (chars[i - 1] != chars[i] && i == chars.length - 1) {
                chars[write++] = chars[i];
            }


        }
        return write ;

    }


    public static int maxProfit(int[] prices) {

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {

            // A：今天卖，利润比历史最大还大？是就更新
            if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
            // B：今天的价格比历史最低还低？是就刷新最低价
            if (price < minPrice) {
                minPrice = price;
            }
        }

        return maxProfit;

    }

    public static String reverseVowels(String s) {
        char[] chars = s.toCharArray();

        String voewls = "aeiouAEIOU";
        int left = 0;
        int right= chars.length -1;
        while(left < right){
            if (voewls.indexOf(chars[left]) == -1){
                left ++;
            }else if(voewls.indexOf(chars[right]) == -1){
                right--;
            }else{
                // 两边都是元音 → 交换，然后 left++、right--
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left ++;
                right--;

            }
        }
        return new String(chars);

    }


    public static boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE,second = Integer.MAX_VALUE;
        for (int num : nums) {
            if (second < num) {
                return true;
            } else if (first < num) {
                second = num;
            } else {
                first = num;
            }
        }
        return false;

    }
    
}
