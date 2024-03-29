package org.example;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class 리스트변환 {
    public static void main(String[] args) {
        Employee emp1 = new Employee(1234,"길동", "gill@google.com");
        Employee emp2 = new Employee(1235,"펭수", "peng@google.com");
        List<Employee> employees = Arrays.asList(emp1, emp2);

        Gson gson = new Gson(); //구글 Gson객체
        String json = gson.toJson(employees);
        System.out.println(json);

    }
}
