package org.example;

import com.google.gson.Gson;

public class App {
    public static void main(String[] args) {
        //구글 Gson 라이브러리는 자바객체를 JSON으로 변환해준다
        Gson gson = new Gson();
        //자바 직원 객체 생성
        Employee emp = new Employee(1234, "길동", "gill@google.com");
        //emp 객체를 json 형식으로 변환 (문자열)
        String json = gson.toJson(emp);
        System.out.println(json);
        //제이슨 문자열
        String jsonString = "{\"id\":1234,\"name\":\"길동\",\"email\":\"gill@google.com\"}";
        //제이슨 문자열을 다시 자바 employee 객체로 변환함
        Employee emp2 = gson.fromJson(jsonString, Employee.class);
        System.out.println(emp2);
    }

}
