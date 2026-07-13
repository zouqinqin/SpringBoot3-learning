package com.practice.boot;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
class RobotSpringBootStarterApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(111);
    }
    @Test
    void TestStr(){



        List<AmountBean> amountBeans =new ArrayList<>();
        AmountBean bean1 = new AmountBean("USD",new BigDecimal("110"));
        AmountBean bean2 = new AmountBean("RMB",new BigDecimal("50"));
        amountBeans.add(bean1);
        amountBeans.add(bean2);

        System.out.println(parseToStr(amountBeans));

       /* String recAmount = "USD:100|RMB:50";
        Map<String, BigDecimal> stringBigDecimalMap = parseRecAmount(recAmount);

        String s = compareAmount(stringBigDecimalMap, amountBeans);

        System.out.println(s);*/

    }

    /**
     * 将list 中的 币种：金额 转为 "USD:100|RMB:50" 格式
     * @param list
     * @return
     */
    private String parseToStr(List<AmountBean> list){

        if(list == null || list.isEmpty() ){
            return "";
        }

        return list.stream().filter(bean -> bean.getCurrency() != null && bean.getAmount() != null)
                .map(bean -> bean.getCurrency() + ":" + bean.getAmount())
                .collect(Collectors.joining("|"));
    }


    private Map<String, BigDecimal> parseRecAmount(String recAmount){
        Map<String, BigDecimal> map = new HashMap<>();

        for (String pair : recAmount.split("\\|")) {
            String[] split = pair.split(":");

            if(split.length == 2){
                map.put(split[0], new BigDecimal(split[1]));
            }else{
                log.info("金额格式异常！");
            }

        }

        return map;
    }


    private String compareAmount(Map<String, BigDecimal> map,List<AmountBean> amountBeans){
        StringBuilder builder = new StringBuilder();
        for (AmountBean bean : amountBeans) {
            if(map.containsKey(bean.getCurrency())){

                BigDecimal amount = map.get(bean.getCurrency());
                int i = amount.compareTo(bean.getAmount());

                if( i< 0){
                    builder.append("应收中的币种:").append(bean.getCurrency()).append("小于实际的客户应收金额");
                }else if(i >0){
                    log.info("aaa");
                }
            }else {
                builder.append("币种:").append(bean.getCurrency()).append("不存在map中");
            }
        }

        return builder.toString();
    }
    @Test
    void testQueue(){
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(1);
        deque.offer(2);

        System.out.println(deque.peek());// 1

        int size = deque.size();

        for(int i = 0;i < size;i++){
            Integer poll = deque.poll();

            if(i == size -1){
                System.out.println(poll);
            }
        }
    }
    @Test
    void testQueue2(){
        Map<Integer,Integer> map = new HashMap<>();
        map.put(1,10);
        map.put(2,20);
        map.put(3,20);

        int maxValue = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            maxValue = Math.max(maxValue,value);
        }
        System.out.println(maxValue);



    }



}
