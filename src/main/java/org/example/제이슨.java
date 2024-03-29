package org.example;

import org.json.JSONObject;

public class 제이슨 {
    public static void main(String[] args) {
        JSONObject jo = new JSONObject();
        jo.put("id", 1234);
        jo.put("name", "길동");
        jo.put("email", "gill@naver.com");

        System.out.println(jo.toString());

        Employee emp = new Employee(1235, "펭수","peng@naver.com");
        JSONObject jo2 = new JSONObject(emp);
        System.out.println(jo2);
    }
}
