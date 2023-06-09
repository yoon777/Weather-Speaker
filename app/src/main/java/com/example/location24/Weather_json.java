package com.example.location24;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Weather_json {
    private String tmx = "", tmn = "", time = "";
    private


    ArrayList<String> time_array = new ArrayList<String>();


    public String search(String result) throws JSONException {

        try {


            // response 키를 가지고 데이터를 파싱
            JSONObject jsonObj_1 = new JSONObject(result);
            String response = jsonObj_1.getString("response");

            // response 로 부터 body 찾기
            JSONObject jsonObj_2 = new JSONObject(response);
            String body = jsonObj_2.getString("body");

            // body 로 부터 items 찾기
            JSONObject jsonObj_3 = new JSONObject(body);
            String items = jsonObj_3.getString("items");
            Log.i("ITEMS", items);

            // items로 부터 itemlist 를 받기
            JSONObject jsonObj_4 = new JSONObject(items);
            JSONArray jsonArray = jsonObj_4.getJSONArray("item");

            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj_4 = jsonArray.getJSONObject(i);
                String fcstTime = jsonObj_4.getString("fcstTime");
                String fcstValue = jsonObj_4.getString("fcstValue");
                String category = jsonObj_4.getString("category");

                if (category.equals("TMX")) {

                    tmx = "오늘 최고 기온은 " + fcstValue + "℃" + "이고 ";

                }

                if (category.equals("TMN")) {
                    tmn = "최저 기온은 " + fcstValue + "℃" + "입니다. ";
                }

                if (category.equals("PTY")) {
                    if(!fcstValue.equals("0")){
                        time_array.add(fcstTime);
                    }


                }

            }

            if(time_array.size() != 0){
                time = String.join("시, ", time_array) + "시에 강수가 있습니다";
            }
            else{
                time = "";
            }


        }
        catch (JSONException e) {
            throw new RuntimeException(e);
        }





        return tmx + tmn + time;
    }





}
