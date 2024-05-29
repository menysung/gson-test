package org.example.pub;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

//공공데이터 API 활용하기
public class ApiExplorer2 {
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/4050000/libnewbk/getLibnewbk"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=cUeyX2yZW9WF18a16qOjwNFbn%2F5sQhplKnbfQFEffghtnwSy%2F45loAPoEWWw%2FElzuukRjLaRVjwbG0W8BD5DDw%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("3", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("5", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pblshr", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*신간도서의 출판사*/
        urlBuilder.append("&" + URLEncoder.encode("aut_nm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*신간도서의 저자명*/
        urlBuilder.append("&" + URLEncoder.encode("bk_nm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*신간도서의 도서명*/
        urlBuilder.append("&" + URLEncoder.encode("srch_bgn_yr", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*검색 시작년도*/
        urlBuilder.append("&" + URLEncoder.encode("srch_end_yr", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*검색 종료년도*/

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        //System.out.println(sb.toString());

        JSONTokener tokener = new JSONTokener(sb.toString());
        JSONObject jsonObject = new JSONObject(sb.toString());

        System.out.println(jsonObject);
        JSONArray arr = jsonObject.getJSONArray("items");
        System.out.println(arr);

        for(Object one : arr) {
            JSONObject ob = (JSONObject)one;
            //System.out.println(employee.toString());
            System.out.print(ob.get("pblshr") + "\t");
            System.out.print(ob.get("aut_nm") + "\t");
            System.out.print(ob.get("bk_nm") + "\t");
//            System.out.print(ob.get("org") + "\t");
//            System.out.print(ob.get("createdAt") + "\t");
//            System.out.println(ob.get("phoneNumber"));
        }
    }

}
