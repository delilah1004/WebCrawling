package com.mynetflix.db.parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mynetflix.db.StaticData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class JsonGetter {

    public void getTV() {

        BufferedReader br;
        URL getTvURL;
        HttpURLConnection conn;

        String url = StaticData.API_MAIN_URL;
        url += "/tv/63174";
        url += "?api_key=" + StaticData.API_KEY;
        url += "&language=" + StaticData.KOREAN;

        try {
            getTvURL = new URL(url);
            conn = (HttpURLConnection) getTvURL.openConnection();
            conn.setRequestMethod(StaticData.protocol);
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            JsonObject json = JsonParser.parseReader(br).getAsJsonObject();

            System.out.println(json);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getMovie() {

        BufferedReader br;
        URL getTvURL;
        HttpURLConnection conn;

        String url = StaticData.API_MAIN_URL;
        url += "/movie/605116";
        url += "?api_key=" + StaticData.API_KEY;
        url += "&language=" + StaticData.KOREAN;

        try {
            getTvURL = new URL(url);
            conn = (HttpURLConnection) getTvURL.openConnection();
            conn.setRequestMethod(StaticData.protocol);
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            br.readLine();

            System.out.println(br.readLine());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTVById(ArrayList<Long> tvIdList) {

        BufferedReader br;
        URL getTvURL;
        HttpURLConnection conn;

        for(long id : tvIdList) {

            String url = StaticData.API_MAIN_URL;
            url += "/tv/" + id;
            url += "?api_key=" + StaticData.API_KEY;
            url += "&language=" + StaticData.KOREAN;

            try {
                getTvURL = new URL(url);
                conn = (HttpURLConnection) getTvURL.openConnection();
                conn.setRequestMethod(StaticData.protocol);
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                br.readLine();

                System.out.println(br.readLine());
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
