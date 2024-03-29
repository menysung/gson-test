package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

public class 제이슨리스트 {
    public static void main(String[] args) {
        Employee emp1 = new Employee(1234, "길동","gill@naver.com");
        Employee emp2 = new Employee(1235, "펭수","peng@naver.com");

        //[직원1, 직원2 묶여서 리턴된다]
        JSONObject jo1 = new JSONObject(emp1);
        JSONObject jo2 = new JSONObject(emp2);

        JSONArray employees = new JSONArray();
        employees.put(jo1);
        employees.put(jo2);

        //{ 직원 : [직원1, 직원2] }
        JSONObject obj = new JSONObject();
        obj.put("employees", employees);
        System.out.println(obj.toString(2));
    }
}
