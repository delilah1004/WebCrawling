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

    // 크롤링할 페이지 개수
    private final int movieCount = 159;
    private final int tvCount = 72;


    public CrawlingDynamic() {

        // System Property SetUp
        System.setProperty(StaticData.WEB_DRIVER_ID, StaticData.WEB_DRIVER_PATH);

        // Driver SetUp
        driver = new ChromeDriver();
    }

    /* ------ Movie ------- */

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


            for (int i = 1; i <= movieCount; i++) {
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

    public void CrawlPopularDescMovieIds() {

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


            for (int i = 1; i <= movieCount; i++) {
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

    public void CrawlPopularAscMovieIds() {

        // 웹사이트의 전체 html 문서 crawl
        driver.get(StaticData.MOVIES_NETFLIX_URL);

        movieIdList = new ArrayList<>();

        try {

            WebElement elements = driver.findElement(By.id("media_v4"));

            // 필터와 영화 목록 전체를 포함하는 가장 하위 태그 select
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

            // 영화 영역
            WebElement movies = content.findElement(By.id("media_results"));


            for (int i = 1; i <= movieCount; i++) {
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

    public void CrawlLatestMovieIds() {

        // 웹사이트의 전체 html 문서 crawl
        driver.get(StaticData.MOVIES_NETFLIX_URL);

        movieIdList = new ArrayList<>();

        try {

            WebElement elements = driver.findElement(By.id("media_v4"));

            // 필터와 영화 목록 전체를 포함하는 가장 하위 태그 select
            WebElement content = elements.findElement(By.cssSelector("div.content"));

            WebElement filter = content.findElements(By.tagName("div")).get(0);

            // 정렬 버튼
            WebElement sortButton = filter.findElement(By.className("filter")).findElements(By.tagName("span")).get(0);

            // 정렬 버튼 클릭
            sortButton.click();

            WebElement sortingBox = driver.findElement(By.id("sort_by_listbox"));

            WebElement popularAscButton = sortingBox.findElements(By.className("k-item")).get(4);

            // 상영일 내림차순 버튼 클릭
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

            // 영화 영역
            WebElement movies = content.findElement(By.id("media_results"));


            for (int i = 1; i <= 157; i++) {
                WebElement page = movies.findElement(By.id("page_" + i));

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

    public void CrawlOldestMovieIds() {

        // 웹사이트의 전체 html 문서 crawl
        driver.get(StaticData.MOVIES_NETFLIX_URL);

        movieIdList = new ArrayList<>();

        try {

            WebElement elements = driver.findElement(By.id("media_v4"));

            // 필터와 영화 목록 전체를 포함하는 가장 하위 태그 select
            WebElement content = elements.findElement(By.cssSelector("div.content"));

            WebElement filter = content.findElements(By.tagName("div")).get(0);

            // 정렬 버튼
            WebElement sortButton = filter.findElement(By.className("filter")).findElements(By.tagName("span")).get(0);

            // 정렬 버튼 클릭
            sortButton.click();

            WebElement sortingBox = driver.findElement(By.id("sort_by_listbox"));

            WebElement popularAscButton = sortingBox.findElements(By.className("k-item")).get(5);

            // 상영일 오름차순 버튼 클릭
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

            // 영화 영역
            WebElement movies = content.findElement(By.id("media_results"));


            for (int i = 1; i <= movieCount; i++) {
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


    /* ------ TV Program ------- */

    public void CrawlAllTVIds() {

        // 웹사이트의 전체 html 문서 crawl
        driver.get(StaticData.TV_PROGRAMS_NETFLIX_URL);

        tvIdList = new ArrayList<>();

        try {
            // TV Program 목록 전체를 포함하는 가장 하위 태그 select
            WebElement elements = driver.findElement(By.cssSelector("div.items_wrapper"));

            for (int i = 1; i <= 45; i++) {
                WebElement page = elements.findElement(By.id("results_page_" + i));

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

            for (int i = 1; i <= tvCount; i++) {
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

            for (int i = 1; i <= tvCount; i++) {
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

    public void CrawlLatestTVIds() {

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

            WebElement popularAscButton = sortingBox.findElements(By.className("k-item")).get(4);

            // 상영일 내림차순 버튼 클릭
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

            for (int i = 1; i <= tvCount; i++) {
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

    public void CrawlOldestTVIds() {

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

            WebElement popularAscButton = sortingBox.findElements(By.className("k-item")).get(5);

            // 상영일 오름차순 버튼 클릭
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

            for (int i = 1; i <= tvCount; i++) {
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

}
