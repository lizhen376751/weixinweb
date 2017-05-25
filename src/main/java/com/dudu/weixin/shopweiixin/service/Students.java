package com.dudu.weixin.shopweiixin.service;

/**
 * Created by Administrator on 2017/5/24.
 */

public class Students {
    private int age;
    private String score;
    public Students(int age, String score){
        super();
        this.age = age;
        this.score = score;
    }
    /**
     *  Students(Created by Administrator on 2017524.) 字符串形式
     * @return Students(Created by Administrator on 2017524.)字符串
     */
    @Override
    public String toString() {
        return "age:" + age + ",score:" + score;
    }

    public int getAge() {
        return this.age;
    }

    public Students setAge(int age) {
        this.age = age;
        return this;
    }

    public String getScore() {
        return this.score;
    }

    public Students setScore(String score) {
        this.score = score;
        return this;
    }
}
