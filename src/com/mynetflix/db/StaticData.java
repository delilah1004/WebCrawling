package com.mynetflix.db;

public class StaticData {
    // Web Driver Key 값
    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    // Web Driver 의 로컬 경로
    public static final String WEB_DRIVER_PATH = "C:\\Users\\DIR-P-0074\\Desktop\\MyNetflix\\download\\chromedriver.exe";
    //public static final String WEB_DRIVER_PATH = "D:\\MyNetflix\\download\\selenium\\chromedriver.exe";

    // NETFLIX 에서 방영중인 TV 프로그램 리스트 정보가 있는 URL
    //public static final String TV_PROGRAMS_NETFLIX_URL = "https://www.themoviedb.org/network/213?language=ko-KR";
    public static final String TV_PROGRAMS_NETFLIX_URL = "https://www.themoviedb.org/tv?language=ko-KR";

    // NETFLIX 에서 방영중인 영화 리스트 정보가 있는 URL
    public static final String MOVIES_NETFLIX_URL = "https://www.themoviedb.org/movie?language=ko-KR";


    /* --------- DIREA COM ---------- */

    // TV PROGRAM
    public static final String POPULAR_DESC_TV_ID_LIST_FILE_PATH = "C:\\Users\\DIR-P-0074\\Desktop\\MyNetflix\\file\\popularDescTvIdList.txt";
    public static final String POPULAR_ASC_TV_ID_LIST_FILE_PATH = "C:\\Users\\DIR-P-0074\\Desktop\\MyNetflix\\file\\popularAscTvIdList.txt";
    public static final String TV_ID_LIST_FILE_PATH = "C:\\Users\\DIR-P-0074\\Desktop\\MyNetflix\\file\\tvIdList.txt";

    // MOVIE
    public static final String POPULAR_DESC_MOVIE_ID_LIST_FILE_PATH = "C:\\Users\\DIR-P-0074\\Desktop\\MyNetflix\\file\\popularDescMovieIdList.txt";
    public static final String MOVIE_ID_LIST_FILE_PATH = "C:\\Users\\DIR-P-0074\\Desktop\\MyNetflix\\file\\movieIdList.txt";


    /* --------- MY DESKTOP ---------- */

    // TV PROGRAM
    //public static final String POPULAR_DESC_TV_ID_LIST_FILE_PATH = "D:\\MyNetflix\\file\\popularDescTvIdList.txt";
    //public static final String TV_ID_LIST_FILE_PATH = "D:\\MyNetflix\\file\\tvIdList.txt";


    // MOVIE
    //public static final String MOVIE_ID_LIST_FILE_PATH = "D:\\MyNetflix\\file\\movieIdList.txt";



    // 정렬된 tv_id 목록을 저장할 file 경로
    public static final String SORTED_TV_ID_LIST_FILE_PATH = "C:\\Users\\DIR-P-0074\\Desktop\\MyNetflix\\file\\sortedTvIdList.txt";
    //public static final String SORTED_TV_ID_LIST_FILE_PATH = "D:\\MyNetflix\\file\\sortedTvIdList.txt";


    /* --------- 공통 정보 ---------- */

    public static final String API_MAIN_URL = "https://api.themoviedb.org/3";
    public static final String API_IMAGE_URL = "https://image.tmdb.org/t/p/w600_and_h900_bestv2";

    public static final String API_KEY = "334cc048cf91e9c7e784d8d3241e3b4c";
    public static final String KOREAN = "ko-KR";

    public static final String protocol = "GET";

}
