package io.github.biezhi.java11.time;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Time convert
 *
 * @author biezhi
 * @date 2018/8/1
 */
public class Example {

    public static void main(String[] args) {
        long day = TimeUnit.DAYS.convert(Duration.ofHours(24));
        System.out.println(day == 1);

        // 1 天
        System.out.println(TimeUnit.DAYS.convert(Duration.ofHours(48)));
        System.out.println(TimeUnit.MILLISECONDS.convert(Duration.ofSeconds(1)));

        // 1 分钟
        System.out.println(TimeUnit.MINUTES.convert(Duration.ofSeconds(60)));

        //2小时等于多少秒
        Duration duration = Duration.ofHours(2);
        System.out.println(duration.getSeconds());

        Duration duration2 = Duration.ZERO;
        System.out.println("持续时间常量值:"+duration2.toNanos());
        System.out.println("持续时间常量值是否为零:"+duration2.isZero());
        System.out.println("持续时间常量值是正数:"+duration2.abs().toNanos());

        Duration durationParse =  Duration.parse("PT15M");
        System.out.println("格式转化15分钟:"+durationParse.toMinutes()+"M");
        Duration durationBetween =  Duration.between(LocalTime.NOON,LocalTime.MAX);
        System.out.println("距离今天结束还有多少时间:"+durationBetween.toHours()+"时"+durationBetween.toMinutes()+"分"+durationBetween.getSeconds()+"秒");
        LocalDate localDate = LocalDate.now();

        System.out.println("localDate获取当前yyyy-mm-dd :\n" + localDate);

        LocalDate localDateSystem = LocalDate.now(ZoneId.systemDefault());

        System.out.println("当前系统默认时间:\n" + localDateSystem);

        LocalDate localDateChinese = LocalDate.now(ZoneId.of("GMT+8", new HashMap<>(64)));

        System.out.println("获取某个时区的国家当前日期:\n" + localDateChinese);

        /**
         * of(T)——创建一个非空的Optional实例（使用empty创建一个空的Optional实例）
         *
         * 　　　　ofNullable(T)——若参数不为Null，则创建一个非空实例，否则创建一个空实例
         *
         * 　　　　isPresent——是否存在值（存在返回true，否则返回false）
         *
         * 　　　　orElse(T)——有值则将其返回，否则返回参数中的指定值
         *
         * 　　　　get——有值则返回，否则抛出异常
         *
         * 　　　　orElseGet(Supplier)——与orElse类似，但他可以接收Supplier接口返回的值
         *
         * 　　　　map(Function)——有值则对其进行Function处理返回处理后的Optional（实际上之前两节已经接触过）
         *
         * 　　　　flatMap——与map类似，但是返回值必须是Optional
         */
/*
        // of()——获取Optional实例
        Optional<Employee> op = Optional.of(new Employee("张三", 10, Status.VOCATION));
        // get()——获取Optional中的实例
        System.out.println(op.get());
        // empty()——构建空的Optional实例
        Optional<Object> op2 = Optional.empty();
        // ofNullable()——若参数非空构建实例，否则构建空实例（允许构建空实例）
        Optional<Employee> op3 = Optional.ofNullable(null);
        if (op3.isPresent()) { // isPresent()——是否存在值
            System.out.println(op3.get());
        } else {
            System.out.println("实例为空！");
        }
        // orElse()——有值则获取，否则返回指定参数的值
        Employee employee = op3.orElse(new Employee("李四", 11, Status.FREE));
        System.out.println(employee);
        // orElseGet()——可以接收供给型接口返回的值
        Employee employee1 = op3.orElseGet(() -> new Employee("王五", 12, Status.FREE));
        System.out.println("employee1 = " + employee1);
        // map()——Stream中已经用过，返回处理后的值
        Optional<String> optional = op.map(Employee::getName);
        System.out.println(optional.get());*/

        List<String> list = new ArrayList<String>();
        list.add("I aaam a boy");
        list.add("I looove the girl");
        list.add("But the girl loves another girl");
        List<String> collect = list.stream()
                //.map(line -> line.split(" "))
                //.flatMap(Arrays::stream)
                .flatMap(word -> Arrays.stream(word.split("")))
                .distinct()
                .collect(toList());
        collect.stream().forEach(System.out::print);


        List<String> words = new ArrayList<String>();
        words.add("hello");
        words.add("word");
        System.out.println();

        //将words数组中的元素再按照字符拆分，然后字符去重，最终达到["h", "e", "l", "o", "w", "r", "d"]
        //如果使用map，是达不到直接转化成List<String>的结果
        List<String> stringList = words.stream()
                .flatMap(word -> Arrays.stream(word.split("")))
                .distinct()
                .collect(Collectors.toList());
        stringList.forEach(System.out::print);
        System.out.println();

        List<String> lst1 = Arrays.asList("Jhonny", "David", "Jack", "Duke", "Jill","Dany","Julia","Jenish","Divya");
        List<String> lst2 = Arrays.asList("Jhonny", "David", "Jack", "Duke", "Jill","Dany","Julia","Jenish","Divya");

        Optional<String> findFirst = lst1.parallelStream().filter(s -> s.startsWith("D")).findFirst();
        Optional<String> fidnAny = lst2.parallelStream().filter(s -> s.startsWith("J")).findAny();

        System.out.println(findFirst.get()); //总是打印出David
        System.out.println(fidnAny.get()); //会随机地打印出Jack/Jill/Julia

        //orElse 有，就用自身值。  为null，就用orElse后面的值。
//有正品用正品，没正品用替代品
        System.out.println(Optional.ofNullable("正品").orElse("替代品"));  // orElse
        System.out.println(Optional.ofNullable(null).orElse("替代品"));
// orElseGet 它可以传入一个supplier接口，里面可以花样实现逻辑
        System.out.println(Optional.ofNullable("宝马").orElseGet(()->"走路"));  // 有宝马就不用走路
        System.out.println(Optional.ofNullable(null).orElseGet(()->"自行车"));  // 没宝马，可以骑自行车
        System.out.println(Optional.ofNullable(null).orElseGet(()->"电动车"));  // 没宝马，也可以骑电动车

// 有钱就没异常
        try {
            System.out.println(Optional.ofNullable("钱").orElseThrow(()->new Exception()));  // 有钱不会抛异常
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

// 没钱就会抛异常
        try {
            System.out.println(Optional.ofNullable(null).orElseThrow(()->new Exception()));  // 没钱抛异常
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

}
