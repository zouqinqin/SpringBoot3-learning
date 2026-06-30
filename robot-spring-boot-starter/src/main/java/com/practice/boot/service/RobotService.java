package com.practice.boot.service;

import com.practice.boot.properties.RobotProperties;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.tree.TreeNode;
import java.util.*;


public class RobotService {

    @Autowired
    RobotProperties properties;

    public String getRobotMessage() {
        return "你好，" + properties.getName() + "年龄：" + properties.getAge();
    }

    public static <Char> void main(String[] args) {
        String s = "RDD";
        char[] b = new char[]{'a','a','b','b','c','c','c'};
        int[] nums1 = new int[]{-2,-1,1,-2};
        int[] nums2 = new int[]{2,4,6};
//        System.out.println(maxVowels("a",1));
//        System.out.println(longestOnes(nums,2));

//        System.out.println(findDifference(nums1, nums2));


//        System.out.println(Arrays.toString(asteroidCollision(nums1)));


//        System.out.println(decodeString(s));
        System.out.println(predictPartyVictory(s));


    }





    public static String predictPartyVictory(String senate) {
        Deque<Integer> r = new ArrayDeque<>();
        Deque<Integer> d = new ArrayDeque<>();

        Deque<TreeNode> root = new LinkedList<>();

        char[] sc = senate.toCharArray();
        for(int i =0;i < sc.length;i++){
            if(sc[i] == 'R'){
                r.offer(i);
            }else{
                d.offer(i);
            }
        }
        while(!r.isEmpty() && ! d.isEmpty()){
            // 队列出列，怎么获取下标？
            int rIdx = r.poll();
            int dIdx = d.poll();
            // 小的先执行
            if(rIdx < dIdx){
                r.offer(rIdx + senate.length());
            }else{
               d.offer(dIdx + senate.length());
            }
        }
        return r.isEmpty() ? "Dire" :"Radiant";
    }

    // 输入：s = "3[a2[c]]"
    //输出："accaccacc"
    public static String decodeString(String s) {
        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<String> strStack = new ArrayDeque<>();
        StringBuilder builder =new StringBuilder();
        int num = 0;

        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            if(Character.isDigit(c)){
                num = num * 10 + (c - '0');
            }else if('[' == c){
                numStack.push(num);
                strStack.push(builder.toString());
                num = 0;
                builder = new StringBuilder();

            } else if (c == ']') {
                int k = numStack.pop();
                StringBuilder temp = new StringBuilder(strStack.pop());
                String inner = builder.toString();
                for (int i = 0; i < k; i++) {
                    temp.append(inner);
                }
                builder = temp;
            }else {
                builder.append(c);
            }
        }

        return builder.toString();
    }

    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> s = new Stack<>();

        for(int v:asteroids){
            if(s.empty()|| s.peek() <0){
                s.add(v);
            }else if(s.peek() > 0 && v >0){
                s.add(v);
            }else{
                while(!s.empty() && s.peek() >0 && Math.abs(s.peek()) < Math.abs(v) ){
                    s.pop();
                }
                if(s.empty() || s.peek() < 0) {
                    s.add(v);
                }
                if(s.peek() == -v){
                    s.pop();
                }

            }
        }
        int[] a = new int[s.size()];
        for (int i = 0; i < s.size(); i++) {
            a[i] = s.get(i);
        }
        return a;
    }

    public static String removeStars(String s) {
        Stack<Character> stack = new Stack<>();
        char[] a = s.toCharArray();
        for(int i=0;i<s.length();i++){
            if(a[i] == '*'){
                stack.pop();
            }else{
               stack.add(a[i]);
            }
        }
        StringBuilder builder =new StringBuilder();
        for (Character c : stack) {
            builder.append(c);
        }
       return null;
        
    }


   //arr = [1,2,2,1,1,3]
    public static boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int j : arr) {
            Integer orDefault = map.getOrDefault(j, 0);
            map.put(j, orDefault + 1);
        }
        Set<Integer> set = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            set.add(entry.getValue());
        }
       return set.size() == map.size();
    }

/*    输入：nums1 = [1,2,3], nums2 = [2,4,6]
    输出：[[1,3],[4,6]]*/
    public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
        }
        for (int i : nums2) {
            set2.add(i);
        }
        List<Integer> deffer1 = new ArrayList<>();
        List<Integer> deffer2 = new ArrayList<>();

        for (Integer i : set1) {
            if(!set2.contains(i)) deffer2.add(i);
        }

        for (Integer i : set2) {
            if(!set1.contains(i)) deffer1.add(i);
        }
        return List.of(deffer1,deffer2);
    }

    public static int pivotIndex(int[] nums) {
        int sumLeft = 0;
        int total = Arrays.stream(nums).sum();

        for(int i=0;i<nums.length;i++){
            if(total - nums[i] == 2 * sumLeft){
                return i;
            }
            sumLeft = sumLeft  + nums[i];
        }
        return -1;

    }

    public static int largestAltitude(int[] gain) {
        //如果可以得到海外高度数组，然后求出最大的值，就可以的得到结果
        int[] hight = new int[gain.length+1];
        int max =0;
        hight[0] = 0;
        for(int i=0;i<gain.length;i++){
            hight[i+1] = hight[i] + gain[i];
        }
        for (int i : hight) {
             max = Math.max(i, max);
        }
        return max;
    }

    public static int longestOnes(int[] nums, int k) {
        //nums = [1, 1, 0, 0, 1, 1, 1, 0, 1], k = 2
        int left =0,zeros =0, maxLen =0;

        for(int right =0;right < nums.length;right++){
            if(nums[right] ==0) zeros ++;
            // 1. right 纳入窗口，更新 zeros
            // 2. zeros 超过 k 时，移动 left 直到合法
            while(zeros > k){
                if(nums[left] ==0){
                    zeros--;
                }
                left++;
            }

            // 3. 更新 maxLen
            maxLen = Math.max(right -  left +1,maxLen)  ;
        }
        return maxLen;
    }


    public static int maxVowels(String s, int k) {
        int count = 0;
        String vowels = "aeiou";
        char[] sc = s.toCharArray();
        int maxVowels = 0;
        for (int i = 0; i < k; i++) {
            if (vowels.indexOf(sc[i]) != -1) {
                count++;
            }
        }
        maxVowels = count;

        for (int j = 1; j < s.length() - k +1; j++) {
            if (vowels.indexOf(sc[j - 1]) != -1) {
                count--;
            }
            if (vowels.indexOf(sc[k + j - 1]) != -1) {
                count++;
            }
            if (count > maxVowels) {
                maxVowels = count;
            }
        }
        return maxVowels;

    }

    public static void moveZeroes(int[] nums) {
        int n =nums.length;

        int[] temp = new int[n];
        int idx = 0;

        for(int i = 0; i < n; i++){
            if(nums[i] != 0){
                // 不会空的，放在temp前面，怎么给temp中新增值
                temp[idx++] = nums[i];
            }
            nums[i] = temp[i];
        }

        for (int i = 0; i < n; i++) {
            nums[i] = temp[i];
        }


    }

    public static int compress(char[] chars) {
        int read = 0;
        int wride = 0;
        int n = chars.length;

        while(read < n){
            char cur = chars[read];
            int count = 0;

            // 判断字符重复的次数
            while(read < n && chars[read] == cur){
                read++;
                count++;
            }
            // 保存当前字符
            chars[wride++] = cur;

            // 当count > 1 时保存当前字符出现的次数
            if(count >1){
                for(char c:String.valueOf(count).toCharArray()){
                    chars[wride++] = c;
                }
            }

        }
        return wride;

    }

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
      /*遍历数组，检查每个位置能否种花（当前是 0，左右邻居也是 0，注意边界）
        能种就种下去（把该位置置为 1），计数器 +1，然后跳到 i+2
        最后判断计数器是否 >= n
        */
        int anount = 0;
        for(int i = 0; i < flowerbed.length; i++){
            if( flowerbed[i] == 0
                    && ( i == 0 || flowerbed[i-1] ==0 )
                   && (i == flowerbed.length -1 || flowerbed[i +1] == 0)){
                flowerbed[i] = 1;
                i++;
                ++anount;
            }

                /*if(i ==0 && (flowerbed.length == 1 || flowerbed[i+1] == 0)){
                    flowerbed[i] = 1;
                    i++;
                    ++anount;
                }else if(i == flowerbed.length - 1 && flowerbed[flowerbed.length - 2] == 0){
                    flowerbed[i] = 1;
                    i++;
                    ++anount;
                }else if(i != 0 && flowerbed[i-1] == 0 && flowerbed[i+1]==0 ){
                    flowerbed[i] = 1;
                    i++;
                    ++anount;
                }*/

        }
        return anount >=n;


    }


    public static String gcdOfStrings(String str1, String str2) {

        int minLed = Math.min(str1.length(), str2.length());

        for (int i = minLed; i > 0; i--) {
            // 取出候选 t，长度为 i
            String t = str1.substring(0, i);
            // 判断 t 能否同时整除 str1 和 str2
            if (canDivide(str1, t) && canDivide(str2, t)) {
                return t;
            }
        }
        return "";
    }

    static boolean canDivide(String s, String t) {
        int n = s.length() / t.length();
        return s.length() % t.length() == 0 && t.repeat(n).equals(s);
    }

}



