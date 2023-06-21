package com.example.myapplication;



import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;


public class apiTest extends Thread {





    public String func(String date, int x, int y) throws IOException{
        String endPoint = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/";
        String serviceKey = "ASE4bZ0DJsBdssZbJzwUU7CswYHMKv7DdZ0VEXw9Cmp18FfGi9kJoa%2BS%2BBoQ4%2FuAJkUtb0WapPydrlg0D78HMw%3D%3D";
        String pageNo = "1";
        String numOfRows = "254";
        String baseDate = date; //원하는 날짜
        String baseTime = "0200"; //원하는 시간
        String nx = Integer.toString(x); //위경도임.
        String ny = Integer.toString(y); //위경도 정보는 api문서 볼 것

        String s = endPoint + "getVilageFcst?serviceKey=" + serviceKey
                + "&pageNo=" + pageNo
                + "&numOfRows=" + numOfRows
                + "&dataType=JSON"
                + "&base_date=" + baseDate
                + "&base_time=" + baseTime
                + "&nx=" + nx
                + "&ny=" + ny;

        URL url = new URL(s);
        //어떻게 넘어가는지 확인하고 싶으면 아래 출력분 주석 해제
        System.out.println(url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

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
        String result = sb.toString();




        return result;
    }








}
