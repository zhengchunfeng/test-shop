package com.test.lambda;

import javax.sound.midi.Soundbank;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className LambdaTest
 * @description Lambda表达式应用
 * @date 2019/11/27 15:54
 */
public class LambdaTest {

    public static void main(String[] args) {

        /**------------------------1.四大函数式接口-------------------------------------------------**/

        // 1.消费型函数式接口
        Consumer<String> consumer = c -> System.out.println(c);
        consumer.accept("我是消费型函数式接口");

        // 2.供给型函数式接口
        Supplier<String> supplier = () -> "我是供给型函数式接口";
        System.out.println(supplier.get());

        // 3.函数型函数式接口
        Function<String, Integer> function = Integer::valueOf;
        Integer i = function.apply("1");
        System.out.println("我是函数型函数式接口" +i);

        // 4.断言型函数式接口
        Predicate<String> predicate = p -> p.equals("1");
        boolean b = predicate.test("1");
        System.out.println("我是断言型函数式接口" +b);

        /**------------------------2.Stream流操作-------------------------------------------------**/

        List<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c", "d"));
        // 2.1 过滤
        List<String> list1 = list.stream().filter(p -> p.equals("a")).collect(Collectors.toList());
        System.out.println(list1);

        // 2.2 转换
        List<String> list2 = list.stream().map(f -> f + "1").collect(Collectors.toList());
        System.out.println(list2);

        // 2.3 全符合匹配
        boolean allMatch = list.stream().allMatch(p -> p.length() < 5);
        System.out.println("全符合匹配-" + allMatch);

        // 2.4 任意匹配
        boolean anyMatch = list.stream().anyMatch(p -> p.length() > 5);
        System.out.println("任意匹配-" + anyMatch);

        // 2.5 全不匹配
        boolean noneMatch = list.stream().noneMatch(p -> p.equals("hello"));
        System.out.println("全不匹配-" + noneMatch);

        // 2.6 sorted给集合排序
        List<Integer> list22 = new ArrayList<>(Arrays.asList(6, 10, 9, 3));
        System.out.println(list22);
        //list22.sort((sum1, sum2) -> sum1-sum2);
        list22 = list22.stream().sorted().collect(Collectors.toList());
        System.out.println(list22);

        // 2.7 flatMap扁平化流
        List<String> stringList = new ArrayList<>(Arrays.asList("hello", "world"));
        String string = stringList.stream().flatMap(x -> Stream.of(x)).collect(Collectors.joining());
        System.out.println(string);

        // 2.8 Collectors.groupingBy分组
        List<Map<String, Object>> initList = new ArrayList<>();
        Map<String, Object> ageMap = new HashMap<>();
        ageMap.put("age", 20);
        ageMap.put("name", "zhangsan");
        initList.add(ageMap);

        Map<String, Object> ageMap1 = new HashMap<>();
        ageMap1.put("age", 20);
        ageMap1.put("name", "zhangsan1");
        initList.add(ageMap1);

        Map<String, Object> ageMap2 = new HashMap<>();
        ageMap2.put("age", 21);
        ageMap2.put("name", "zhangsan2");
        initList.add(ageMap2);

        List<Map<String, Object>> groupList = new ArrayList<>();
        // 以age分组，注意groupingBy分组后是以分组的字段为Key，返回值类型为Map，这里故进行二次遍历组装数据
        initList.stream().collect(Collectors.groupingBy(f -> f.get("age"))).forEach((k, v) -> {
            Map<String, Object> ageMap3 = new HashMap<>();
            ageMap3.put("age", k);
            ageMap3.put("group", v);
            groupList.add(ageMap3);
        });
        System.out.println(groupList);


        /**------------------------3.Optional流操作-------------------------------------------------**/

        Optional<String> optional = Optional.ofNullable(null);
        boolean o = optional.map(f -> f.equals("hello")).orElseGet(() -> false);
        System.out.println("我是Optional-" + o);

        /** forEach遍历map **/
        HashMap<String, Object> map = new HashMap<>();
        map.put("age", "26");
        map.put("name", "豆芽菜");
        map.forEach((key, value) -> {
            System.out.println(key + " = " + value);
        });

    }
}
