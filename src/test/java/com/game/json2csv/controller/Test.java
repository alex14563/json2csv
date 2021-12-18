package com.game.json2csv.controller;

import cn.hutool.json.JSONArray;

public class Test {

    @org.junit.jupiter.api.Test
    public void testJSON(){
        String jsonString = "[{\"name\":\"张三\",\"code\":\"123\"},{\"name\":\"李四\",\"code\":\"123\"}]";
        JSONArray jsonArray = new JSONArray(jsonString);
        System.out.println("String转JSONArray: "+jsonArray);
        System.out.println();
    }
}
