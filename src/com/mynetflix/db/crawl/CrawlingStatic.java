package com.mynetflix.db.crawl;

import com.mynetflix.db.StaticData;
import com.mynetflix.db.model.MoviePreview;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class CrawlingStatic {

    public MoviePreview movie;
    public ArrayList<MoviePreview> movieList = null;
    public String posterPath, title, releaseDate, overview;

    private Document document;

    public static void main(String[] args) {

        CrawlingStatic TMDbCrawling = new CrawlingStatic();

        TMDbCrawling.crawlDocument();
        //TMDbCrawling.crawlLoop();
        //TMDbCrawling.crawl();
    }

    public void crawlDocument() {

        try {
            // 웹사이트의 전체 html 문서 crawl
            document = Jsoup.connect(StaticData.TV_PROGRAMS_NETFLIX_URL).get();

            System.out.println(document);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // crawl 함수
    public void crawl() {

        // 영화 목록 전체를 포함하는 가장 하위 태그 select
        Elements elements = document.select("div.items_wrapper");

        // 영화 정보가 담긴 하나의 카드 전체를 포함하는 가장 하위 태그 select
        Element card = elements.select("#results_page_1").select(".card").first();

        // 카드에서 포스터 이미지를 제외한 모든 정보가 담겨있는 가장 하위 태그 select
        Elements content = card.select(".details");

        // title 클래스에 개봉 날짜 정보와 개요 정보가 담겨있음
        Elements contentTitle = content.select(".title");

        // 포스터 이미지 URL
        posterPath = "http:" + card.select(".image").select("img").attr("data-src");
        // 영화 제목
        title = contentTitle.select("h2").text();
        // 개봉 날짜
        releaseDate = contentTitle.select(".release_date").text();
        // 영화 개요
        overview = content.select(".overview").select("p").text();

        // MoviePreview 객체를 새로 선언하고 정보 담기
        movie = new MoviePreview(posterPath, title, releaseDate, overview);

        // 정보가 담긴 MoviePreview 객체 출력
        System.out.println(movie.toString());
    }

    // 반복문을 이용한 crawl 함수
    public void crawlLoop() {

        movieList = new ArrayList<>();

        Elements elements = document.select("div.items_wrapper");

        for (int i = 1; i <= 45; i++) {

            Elements page = elements.select("#results_page_" + i);

            for (Element card : page.select(".card")) {

                Elements content = card.select(".details");
                Elements contentTitle = content.select(".title");

                posterPath = "http:" + card.select(".image").select("img").attr("data-src");
                title = contentTitle.select("h2").text();
                releaseDate = contentTitle.select(".release_date").text();
                overview = content.select(".overview").select("p").text();

                movie = new MoviePreview(posterPath, title, releaseDate, overview);
                movieList.add(movie);
            }
        }

        // 각 카드의 정보가 담겨있는 list 모두 출력
        for (MoviePreview m : movieList) {
            System.out.println(m.toString());
        }
    }
}
