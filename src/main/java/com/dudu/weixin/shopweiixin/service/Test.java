package com.dudu.weixin.shopweiixin.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */

public class Test {


    /**
     * @param args
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("id", "5");
        map1.put("name", "p");
        HashMap<String, String> map2 = new HashMap<String, String>();
        map2.put("id", "2");
        map2.put("name", "h");
        HashMap<String, String> map3 = new HashMap<String, String>();
        map3.put("id", "3");
        map3.put("name", "f");
        list.add(map1);
        list.add(map3);
        list.add(map2);
        //排序前
        for (HashMap<String, String> map : list) {

            System.out.println(map.get("id"));
        }
        Collections.sort(list, new Comparator<HashMap<String, String>>() {

            public int compare(HashMap<String, String> o1, HashMap<String, String> o2) {
                String name1 = (String) o1.get("id"); //name1是从你list里面拿出来的一个
                String name2 = (String) o2.get("id"); //name1是从你list里面拿出来的第二个name
                return name1.compareTo(name2);
            }

        });
        //排序后
        System.out.println("-------------------");
        for (HashMap<String, String> map : list) {
            System.out.println(map.get("id"));
        }

    }


//    public static void main(String[] args) {
//        List<Students> students = new ArrayList<Students>();
//        students.add(new Students(23, "很哈"));
//        students.add(new Students(32, "很哈"));
//        students.add(new Students(21, "很哈"));
//        students.add(new Students(29, "很哈"));
//        students.add(new Students(25, "很哈"));
//        List shopList = new ArrayList<HashHashMap<String,String>>();
//
//        HashHashMap<String,String> map = new HashHashMap<String,String>();
//        map.put("lat", "25");
//        map.put("log", "ssddsakj");
//        map.put("sss", "ssss");
//        shopList.add(map);
//
//        HashHashMap<String,String> map1 = new HashHashMap<String,String>();
//        map1.put("lat", "29");
//        map1.put("log", "ssddsakj");
//        map1.put("sss", "ssss");
//        shopList.add(map1);
//
//        HashHashMap<String,String> map2 = new HashHashMap<String,String>();
//        map2.put("lat", "29");
//        map2.put("log", "ssddsakj");
//        map2.put("sss", "ssss");
//        shopList.add(map2);
//
//
//        Collections.sort(shopList, new Comparator<shopList>() {
//
//            @Override
//            public int compare(Students o1, Students o2) {
//                return o1.getAge() - o2.getAge();
//            }
//        });
//        System.out.println(students.toString());
//        for (Students stu : students) {
//            System.out.println("score:" + stu.getScore() + ":age" + stu.getAge());
//        }
//    }

}
