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

public class ApiExplorer {
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/15077586/v1/centers"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=cUeyX2yZW9WF18a16qOjwNFbn/5sQhplKnbfQFEffghtnwSy/45loAPoEWWw/ElzuukRjLaRVjwbG0W8BD5DDw=="); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("page","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("perPage","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
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
        JSONObject object = new JSONObject(tokener);
        System.out.println(object);
        JSONArray arr = object.getJSONArray("data");
        System.out.println(arr);

        for(Object one : arr) {
            JSONObject ob = (JSONObject)one;
            //System.out.println(employee.toString());
            System.out.print(ob.get("id") + "\t");
            System.out.print(ob.get("facilityName") + "\t");
            System.out.print(ob.get("address") + "\t");
            System.out.print(ob.get("org") + "\t");
            System.out.print(ob.get("createdAt") + "\t");
            System.out.println(ob.get("phoneNumber"));
        }
    }

}
