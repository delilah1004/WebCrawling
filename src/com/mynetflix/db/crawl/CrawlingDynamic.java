package com.mynetflix.db.crawl;

import com.mynetflix.db.StaticData;
import com.mynetflix.db.model.MoviePreview;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class CrawlingDynamic {

    public MoviePreview movie;
    public ArrayList<MoviePreview> movieList = null;
    public String posterPath, title, releaseDate, overview;

    public String tvPath, tvId;
    private String moviePath, movieId;

    public ArrayList<Long> tvIdList, movieIdList;

    // Web Driver
    private final WebDriver driver;

    public CrawlingDynamic() {

        // System Property SetUp
        System.setProperty(StaticData.WEB_DRIVER_ID, StaticData.WEB_DRIVER_PATH);

        // Driver SetUp
        driver = new ChromeDriver();
    }

    public void CrawlAllTVIds() {

        // 웹사이트의 전체 html 문서 crawl
        driver.get(StaticData.TV_PROGRAMS_NETFLIX_URL);

        tvIdList = new ArrayList<>();

        try {
            // TV Program 목록 전체를 포함하는 가장 하위 태그 select
            WebElement elements = driver.findElement(By.cssSelector("div.items_wrapper"));

            for (int i = 1; i <= 45; i++) {
                WebElement page = elements.findElement(By.id("results_page_" + i));
                //WebElement page = elements.findElement(By.id("results_page_1"));

                for (WebElement card : page.findElements(By.className("card"))) {

                    // 카드의 포스터 부분
                    WebElement poster = card.findElement(By.className("poster"));

                    // TV Program 정보가 담겨있는 URI
                    tvPath = poster.findElement(By.tagName("a")).getAttribute("href");

                    tvId = tvPath.split("/")[4].split("\\?")[0];

                    tvIdList.add(Long.parseLong(tvId));
                }

                if (i == 1) {
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

    public void CrawlPopularDescTVIds() {

        // 웹사이트의 전체 html 문서 crawl
        driver.get(StaticData.TV_PROGRAMS_NETFLIX_URL);

        tvIdList = new ArrayList<>();

        try {

            WebElement elements = driver.findElement(By.id("media_v4"));

            // 필터와 TV 프로그램 목록 전체를 포함하는 가장 하위 태그 select
            WebElement content = elements.findElement(By.cssSelector("div.content"));

            WebElement filter = content.findElements(By.tagName("div")).get(0);

            WebElement whereToWatch = filter.findElements(By.className("closed")).get(1);

            // 닫혀있는 필터 열기
            whereToWatch.click();

            Thread.sleep(1000);

            WebElement buttons = whereToWatch.findElements(By.className("filter")).get(1).findElement(By.id("ott_providers"));

            WebElement netflixButton = buttons.findElement(By.cssSelector("a[title=Netflix]"));

            // 넷플릭스 필터 클릭
            netflixButton.click();

            Thread.sleep(1000);

            WebElement search = filter.findElement(By.className("apply")).findElement(By.tagName("a"));

            // 검색 클릭
            search.click();

            Thread.sleep(1500);

            // TV 프로그램 영역
            WebElement tvs = content.findElement(By.id("media_results"));

            for (int i = 1; i <= 72; i++) {
                WebElement page = tvs.findElement(By.id("page_" + i));

                for (WebElement card : page.findElements(By.cssSelector("div.image"))) {

                    // card 의 wrapper 부분
                    WebElement wrapper = card.findElement(By.className("wrapper"));

                    // tv_id 가 담겨있는 URI
                    tvPath = wrapper.findElement(By.tagName("a")).getAttribute("href");

                    tvId = tvPath.split("/")[4].split("\\?")[0];

                    tvIdList.add(Long.parseLong(tvId));

                }

                if (i == 1) {
                    page.findElement(By.cssSelector("p.load_more")).click();
                } else {
                    driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
                }

                System.out.print(i + " ");

                if(i%20==0) System.out.println();

                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close();
        }
    }

    public void CrawlPopularAscTVIds() {

        // 웹사이트의 전체 html 문서 crawl
        driver.get(StaticData.TV_PROGRAMS_NETFLIX_URL);

        tvIdList = new ArrayList<>();

        try {

            WebElement elements = driver.findElement(By.id("media_v4"));

            // 필터와 TV 프로그램 목록 전체를 포함하는 가장 하위 태그 select
            WebElement content = elements.findElement(By.cssSelector("div.content"));

            WebElement filter = content.findElements(By.tagName("div")).get(0);

            // 정렬 버튼
            WebElement sortButton = filter.findElement(By.className("filter")).findElements(By.tagName("span")).get(0);

            // 정렬 버튼 클릭
            sortButton.click();

            WebElement sortingBox = driver.findElement(By.id("sort_by_listbox"));

            WebElement popularAscButton = sortingBox.findElements(By.className("k-item")).get(1);

            // 인기 오름차순 버튼 클릭
            popularAscButton.click();

            WebElement whereToWatch = filter.findElements(By.className("closed")).get(1);

            // 닫혀있는 필터 열기
            whereToWatch.click();

            Thread.sleep(1000);

            WebElement buttons = whereToWatch.findElements(By.className("filter")).get(1).findElement(By.id("ott_providers"));

            WebElement netflixButton = buttons.findElement(By.cssSelector("a[title=Netflix]"));

            // 넷플릭스 필터 클릭
            netflixButton.click();

            Thread.sleep(1000);

            WebElement search = filter.findElement(By.className("apply")).findElement(By.tagName("a"));

            // 검색 클릭
            search.click();

            Thread.sleep(1500);

            // TV 프로그램 영역
            WebElement tvs = content.findElement(By.id("media_results"));

            for (int i = 1; i <= 72; i++) {
                WebElement page = tvs.findElement(By.id("page_" + i));

                for (WebElement card : page.findElements(By.cssSelector("div.image"))) {

                    // card 의 wrapper 부분
                    WebElement wrapper = card.findElement(By.className("wrapper"));

                    // tv_id 가 담겨있는 URI
                    tvPath = wrapper.findElement(By.tagName("a")).getAttribute("href");

                    tvId = tvPath.split("/")[4].split("\\?")[0];

                    tvIdList.add(Long.parseLong(tvId));

                }

                if (i == 1) {
                    page.findElement(By.cssSelector("p.load_more")).click();
                } else {
                    driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
                }

                System.out.print(i + " ");

                if(i%20==0) System.out.println();

                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close();
        }
    }

    public void CrawlAllMovieIds() {

        // 웹사이트의 전체 html 문서 crawl
        driver.get(StaticData.MOVIES_NETFLIX_URL);

        movieIdList = new ArrayList<>();

        try {

            WebElement elements = driver.findElement(By.id("media_v4"));

            // 필터와 영화 목록 전체를 포함하는 가장 하위 태그 select
            WebElement content = elements.findElement(By.cssSelector("div.content"));

            WebElement filter = content.findElements(By.tagName("div")).get(0);

            WebElement whereToWatch = filter.findElements(By.className("closed")).get(1);

            // 닫혀있는 필터 열기
            whereToWatch.click();

            Thread.sleep(1000);

            WebElement buttons = whereToWatch.findElements(By.className("filter")).get(1).findElement(By.id("ott_providers"));

            WebElement netflixButton = buttons.findElement(By.cssSelector("a[title=Netflix]"));

            // 넷플릭스 필터 클릭
            netflixButton.click();

            Thread.sleep(1000);

            WebElement search = filter.findElement(By.className("apply")).findElement(By.tagName("a"));

            // 검색 클릭
            search.click();

            Thread.sleep(1500);

            // 영화 영역
            WebElement movies = content.findElement(By.id("media_results"));


            for (int i = 1; i <= 157; i++) {
                WebElement page = movies.findElement(By.id("page_" + i));
                //WebElement page = movies.findElement(By.id("page_1"));

                for (WebElement card : page.findElements(By.cssSelector("div.image"))) {

                    // card 의 wrapper 부분
                    WebElement wrapper = card.findElement(By.className("wrapper"));

                    // movie_id 가 담겨있는 URI
                    moviePath = wrapper.findElement(By.tagName("a")).getAttribute("href");

                    movieId = moviePath.split("/")[4].split("\\?")[0];

                    movieIdList.add(Long.parseLong(movieId));

                }

                if (i == 1) {
                    page.findElement(By.cssSelector("p.load_more")).click();
                } else {
                    driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
                }

                System.out.print(i + " ");

                if(i%20==0) System.out.println();

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

                if (i == 1) {
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
