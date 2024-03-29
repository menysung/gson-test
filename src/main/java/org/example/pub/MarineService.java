package org.example.pub;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class MarineService {
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6260000/MarintimeService/getMaritimeKr"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=cUeyX2yZW9WF18a16qOjwNFbn/5sQhplKnbfQFEffghtnwSy/45loAPoEWWw/ElzuukRjLaRVjwbG0W8BD5DDw=="); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("resultType", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*JSON방식으로 호출 시 파라미터 resultType=json 입력*/
        //urlBuilder.append("&" + URLEncoder.encode("UC_SEQ", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*콘텐츠 ID*/

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        System.out.println("Response code: " + conn.getResponseCode());

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

        //System.out.println(sb.toString());


        JSONTokener tokener = new JSONTokener(sb.toString());
        JSONObject object = new JSONObject(tokener);
        System.out.println(object);
        JSONObject getMaritimeKr = object.getJSONObject("getMaritimeKr");
        JSONArray item = getMaritimeKr.getJSONArray("item");
        System.out.println(item);


            for (Object one : item) {
                JSONObject ob = (JSONObject) one;
                System.out.print(ob.get("MAIN_TITLE") + "\t");
                System.out.print(ob.get("LNG") + "\t");
                System.out.print(ob.get("MIDDLE_SIZE_RM1") + "\t");
                System.out.print(ob.get("UC_SEQ") + "\t");
                System.out.print(ob.get("USAGE_AMOUNT") + "\t");
                System.out.print(ob.get("CNTCT_TEL") + "\t");
                System.out.print(ob.get("MAIN_IMG_NORMAL") + "\t");
                System.out.print(ob.get("TRFC_INFO") + "\t");
                System.out.print(ob.get("HLDY_INFO") + "\t");
                System.out.print(ob.get("ITEMCNTNTS") + "\t");
                System.out.print(ob.get("PLACE") + "\t");
                System.out.print(ob.get("SUBTITLE") + "\t");
                System.out.print(ob.get("USAGE_DAY") + "\t");
                System.out.print(ob.get("ADDR2") + "\t");
                System.out.print(ob.get("USAGE_DAY_WEEK_AND_TIME") + "\t");
                System.out.print(ob.get("GUGUN_NM") + "\t");
                System.out.print(ob.get("ADDR1") + "\t");
                System.out.print(ob.get("HOMEPAGE_URL") + "\t");
                System.out.print(ob.get("TITLE") + "\t");
                System.out.print(ob.get("MAIN_IMG_THUMB") + "\t");
                System.out.print(ob.get("LAT") + "\t");

            }

    }
}


