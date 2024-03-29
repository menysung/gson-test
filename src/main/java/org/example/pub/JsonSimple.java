package org.example.pub;
import com.google.gson.JsonParser;

import org.json.XML;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
public class JsonSimple {
    public static void main(String[] args) throws IOException, ParseException {
        //urlBuilder로 문자열을 붙힘
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6260000/BusanCultureConcertService/getBusanCultureConcert"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=cUeyX2yZW9WF18a16qOjwNFbn/5sQhplKnbfQFEffghtnwSy/45loAPoEWWw/ElzuukRjLaRVjwbG0W8BD5DDw=="); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("55", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("20", "UTF-8")); /*한 페이지 결과 수*/
        URL url = new URL(urlBuilder.toString()); //서비스 요청 주소
        HttpURLConnection conn = (HttpURLConnection) url.openConnection(); //http 통신으로 요청
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode()); //요청 확인
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
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

        //XML로 받은 데이터를 Json으로 변환 (org.json)
        org.json.JSONObject jsonString = XML.toJSONObject(sb.toString());
        //System.out.println(jsonString.toString(2)); //받은 데이터를 콘솔에 출력

        //제이슨심플 라이브러리
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString.toString());
        JSONObject response = (JSONObject) jsonObject.get("response");
        JSONObject body = (JSONObject) response.get("body");
        JSONObject items = (JSONObject) body.get("items");
        JSONArray item = (JSONArray) items.get("item");

        //System.out.println(item);

        for (Object one : item) {
            JSONObject ob = (JSONObject) one;
            System.out.print(ob.get("place_nm") + "\t");
            System.out.print(ob.get("title") + "\t");
            System.out.print(ob.get("op_st_dt") + "\t");
            System.out.println();

        }

    }
    }


