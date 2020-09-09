package com.mynetflix.db.parser;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mynetflix.db.StaticData;
import com.mynetflix.db.file.ReadFile;
import com.mynetflix.db.model.TVProgram;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.StringTokenizer;

public class JsonGetter extends ReadFile {

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

    // 넷플릭스에서 방영되는 모든 TV Program 들의 tv_id 목록
    private ArrayList<Long> allTvIdList;

    // id 로 프로그램의 모든 정보 JsonObject 로 반환
    public JsonObject getTVById(long id) {

        String url = StaticData.API_MAIN_URL;
        url += "/tv/" + id;
        url += "?api_key=" + StaticData.API_KEY;
        url += "&language=" + StaticData.KOREAN;

        getReader(url);

        return getJson();
    }

    // tvIdList 에 포함된 TV Program 들의 정보 리스트 반환
    public ArrayList<TVProgram> getTVProgramList() {

        getAllTVIds();

        // 반환값을 담을 TVProgram 리스트 선언
        ArrayList<TVProgram> tvPrograms = new ArrayList<>();
        // season 정보와 genre 정보를 담을 리스트 선언
        ArrayList<Integer> seasons, genres;

        for (long tvId : allTvIdList) {
            try {
                JsonObject tv = getTVById(tvId);

                TVProgram tvProgram = new TVProgram();

                // tv_id
                tvProgram.setId(tv.get("id").getAsLong());

                // 제목
                tvProgram.setName(tv.get("name").getAsString());
                // 영상 길이
                try {
                    tvProgram.setEpisodeRunTime(tv.get("episode_run_time").getAsInt());
                } catch (Exception e) {
                    tvProgram.setEpisodeRunTime(0);
                }

                // 장르
                genres = new ArrayList<>();

                for (JsonElement element : tv.get("genres").getAsJsonArray()) {
                    genres.add(element.getAsJsonObject().get("id").getAsInt());
                }

                tvProgram.setGenres(genres);

                // 개요
                tvProgram.setOverview(tv.get("overview").getAsString());
                // 포스터 URI
                tvProgram.setPosterPath(tv.get("poster_path").getAsString());
                // 영상 스트리밍 URL
                tvProgram.setHomepage(tv.get("homepage").getAsString());

                // 방영일 정보
                tvProgram.setFirstAirDate(tv.get("first_air_date").getAsString());
                try {
                    tvProgram.setLastAirDate(tv.get("last_air_date").getAsString());
                } catch (Exception e) {
                    tvProgram.setLastAirDate(null);
                }
                // 인기도
                tvProgram.setPopularity(tv.get("popularity").getAsDouble());

                // 시즌 정보
                seasons = new ArrayList<>();

                for (JsonElement element : tv.get("seasons").getAsJsonArray()) {
                    seasons.add(element.getAsJsonObject().get("season_number").getAsInt());
                }

                tvProgram.setSeasons(seasons);

                // 종영 여부
                tvProgram.setStatus(tv.get("status").getAsString());

                tvPrograms.add(tvProgram);
            } catch (Exception e) {
                System.out.println(tvId);
                e.printStackTrace();
            }
        }

        // lastAirDate 기준 내림차순 정렬
        tvPrograms.sort(new Comparator<TVProgram>() {
            @Override
            public int compare(TVProgram arg1, TVProgram arg2) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

                Date date1 = null;
                Date date2 = null;

                try {
                    date1 = format.parse(arg1.getLastAirDate());
                    date2 = format.parse(arg2.getLastAirDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                int compare = date1.compareTo(date2);

                if (compare < 0) return 1;
                else if (compare > 0) return -1;
                else return 0;
            }
        });

        return tvPrograms;
    }

    /*
    public ArrayList<Long> getAllTvIdListSorted(ArrayList<TVProgram> tvPrograms){

        ArrayList<Long> tvIdList = new ArrayList<>();

        for(TVProgram tv : tvPrograms){
            tvIdList.add(tv.getId());
        }

        return tvIdList;
    }

     */

    public ArrayList<Long> getAllTvIdListSorted() {

        ArrayList<Long> tvIdList = new ArrayList<>();

        for (TVProgram tv : getTVProgramList()) {
            tvIdList.add(tv.getId());
        }

        return tvIdList;
    }

    // 파일을 불러와 tvIdList 정보 업데이트
    // 넷플릭스에서 방영되는 모든 TV Program 들의 tv_id 목록 반환
    public void getAllTVIds() {

        FileReader fr = null;
        BufferedReader br = null;
        StringTokenizer st;

        String line;
        allTvIdList = new ArrayList<>();

        try {
            fr = new FileReader(new File(StaticData.TV_ID_LIST_FILE_PATH));
            br = new BufferedReader(fr);

            // file 한줄씩 읽기
            while ((line = br.readLine()) != null) {

                // StringTokenizer 에 한 줄 담기
                st = new StringTokenizer(line);

                // StringTokenizer 에 담긴 토큰을 list 에 추가
                while (st.hasMoreTokens()) {
                    allTvIdList.add(Long.parseLong(st.nextToken()));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                if (fr != null) fr.close();
                if (br != null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
