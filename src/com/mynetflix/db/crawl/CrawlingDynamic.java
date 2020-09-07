package com.mynetflix.db.crawl;

import com.mynetflix.db.StaticData;
import com.mynetflix.db.model.MoviePreview;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CrawlingDynamic {

    public MoviePreview movie;
    public ArrayList<MoviePreview> movieList = null;
    public String posterPath, title, releaseDate, overview;
    public String tvPath, tvId;
    public ArrayList<Long> tvIdList = null;

    // Web Driver
    private final WebDriver driver;

    public CrawlingDynamic() {

        // System Property SetUp
        System.setProperty(StaticData.WEB_DRIVER_ID, StaticData.WEB_DRIVER_PATH);

        // Driver SetUp
        driver = new ChromeDriver();
        // 웹사이트의 전체 html 문서 crawl
        driver.get(StaticData.TV_PROGRAMS_NETFLIX_URL);
    }

    public void CrawlAllTVIds() {

        tvIdList = new ArrayList<>();

        try {
            // 영화 목록 전체를 포함하는 가장 하위 태그 select
            WebElement elements = driver.findElement(By.cssSelector("div.items_wrapper"));

            for (int i = 1; i <= 45; i++) {
                WebElement page = elements.findElement(By.id("results_page_" + i));
                //WebElement page = elements.findElement(By.id("results_page_1"));

                for (WebElement card : page.findElements(By.className("card"))) {

                    // 카드의 포스터 부분
                    WebElement poster = card.findElement(By.className("poster"));

                    // tv 프로그램 정보가 담겨있는 URI
                    tvPath = poster.findElement(By.tagName("a")).getAttribute("href");

                    tvId = tvPath.split("/")[4].split("\\?")[0];

                    tvIdList.add(Long.parseLong(tvId));
                }

                if(i==1) {
                    page.findElement(By.cssSelector("p.load_more")).click();
                } else {
                    driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
                }

                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close();
        }
    }

    public void crawl() {

        try {
            // 영화 목록 전체를 포함하는 가장 하위 태그 select
            WebElement elements = driver.findElement(By.cssSelector("div.items_wrapper"));

            // 영화 정보가 담긴 하나의 카드 전체를 포함하는 가장 하위 태그 select
            WebElement card = elements.findElement(By.id("results_page_1")).findElements(By.className("card")).get(0);

            // 카드에서 포스터 이미지를 제외한 모든 정보가 담겨있는 가장 하위 태그 select
            WebElement content = card.findElement(By.className("details"));

            // title 클래스에 개봉 날짜 정보와 개요 정보가 담겨있음
            WebElement contentTitle = content.findElement(By.className("title"));

            // 포스터 이미지 URL
            posterPath = "http:" + card.findElement(By.cssSelector(".image img")).getAttribute("data-src");
            // 영화 제목
            title = contentTitle.findElement(By.tagName("h2")).getText();
            // 개봉 날짜
            releaseDate = contentTitle.findElement(By.className("release_date")).getText();

            // 영화 개요
            overview = content.findElement(By.cssSelector(".overview p")).getText();

            // MoviePreview 객체를 새로 선언하고 정보 담기
            movie = new MoviePreview(posterPath, title, releaseDate, overview);

            System.out.println(movie);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close();
        }
    }

    public void crawlLoop() {

        movieList = new ArrayList<>();

        try {
            // 영화 목록 전체를 포함하는 가장 하위 태그 select
            WebElement elements = driver.findElement(By.cssSelector("div.items_wrapper"));

            for (int i = 1; i < 4; i++) {
                WebElement page = elements.findElement(By.id("results_page_" + i));
                //WebElement page = elements.findElement(By.id("results_page_1"));

                for (WebElement card : page.findElements(By.className("card"))) {

                    // 카드에서 포스터 이미지를 제외한 모든 정보가 담겨있는 가장 하위 태그 select
                    WebElement content = card.findElement(By.className("details"));

                    // title 클래스에 개봉 날짜 정보와 개요 정보가 담겨있음
                    WebElement contentTitle = content.findElement(By.className("title"));

                    // 포스터 이미지 URL
                    posterPath = "http:" + card.findElement(By.cssSelector(".image img")).getAttribute("data-src");
                    // 영화 제목
                    title = contentTitle.findElement(By.tagName("h2")).getText();
                    // 개봉 날짜
                    releaseDate = contentTitle.findElement(By.className("release_date")).getText();

                    try {
                        // 영화 개요
                        overview = content.findElement(By.cssSelector(".overview p")).getText();
                    } catch (NoSuchElementException e) {
                        overview = "";
                    }

                    // MoviePreview 객체를 새로 선언하고 정보 담기
                    movie = new MoviePreview(posterPath, title, releaseDate, overview);
                    movieList.add(movie);

                    //jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                }

                if(i==1) {
                    page.findElement(By.cssSelector("p.load_more")).click();
                    Thread.sleep(2000);
                } else {
                    driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
                    Thread.sleep(2000);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close();
        }

        for (MoviePreview m : movieList) {
            System.out.println(m.toString());
        }
    }
}
