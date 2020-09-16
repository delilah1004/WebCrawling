package com.mynetflix.db;

import com.mynetflix.db.crawl.CrawlingDynamic;
import com.mynetflix.db.file.MakeFile;
import com.mynetflix.db.jdbc.ConnectionProvider;
import com.mynetflix.db.parser.JsonGetter;

public class Crawling extends CrawlingDynamic {

    public static void main(String[] args) {
        CrawlingDynamic crawlingDynamic = new CrawlingDynamic();
        //crawlingDynamic.CrawlAllTVIds();
        crawlingDynamic.CrawlPopularDescTVIds();

        MakeFile makeFile = new MakeFile(crawlingDynamic.tvIdList, StaticData.POPULAR_DESC_TV_ID_LIST_FILE_PATH);
        makeFile.makeFile();

//        crawlingDynamic = new CrawlingDynamic();
//        crawlingDynamic.CrawlPopularAscTVIds();
//
//        makeFile = new MakeFile(crawlingDynamic.tvIdList, StaticData.POPULAR_ASC_TV_ID_LIST_FILE_PATH);
//        makeFile.makeFile();
//
//        crawlingDynamic = new CrawlingDynamic();
//        crawlingDynamic.CrawlLatestTVIds();
//
//        makeFile = new MakeFile(crawlingDynamic.tvIdList, StaticData.LATEST_TV_ID_LIST_FILE_PATH);
//        makeFile.makeFile();
//
//        crawlingDynamic = new CrawlingDynamic();
//        crawlingDynamic.CrawlOldestTVIds();
//
//        MakeFile makeFile = new MakeFile(crawlingDynamic.tvIdList, StaticData.OLDEST_TV_ID_LIST_FILE_PATH);
//        makeFile.makeFile();


        //crawlingDynamic.CrawlAllMovieIds();


//        crawlingDynamic = new CrawlingDynamic();
//        crawlingDynamic.CrawlPopularDescMovieIds();
//
//        makeFile = new MakeFile(crawlingDynamic.movieIdList, StaticData.POPULAR_DESC_MOVIE_ID_LIST_FILE_PATH);
//        makeFile.makeFile();

//        crawlingDynamic = new CrawlingDynamic();
//        crawlingDynamic.CrawlPopularAscMovieIds();
//
//        MakeFile makeFile = new MakeFile(crawlingDynamic.movieIdList, StaticData.POPULAR_ASC_MOVIE_ID_LIST_FILE_PATH);
//        makeFile.makeFile();
//
//        crawlingDynamic = new CrawlingDynamic();
//        crawlingDynamic.CrawlLatestMovieIds();
//
//        makeFile = new MakeFile(crawlingDynamic.movieIdList, StaticData.LATEST_MOVIE_ID_LIST_FILE_PATH);
//        makeFile.makeFile();

//        crawlingDynamic = new CrawlingDynamic();
//        crawlingDynamic.CrawlOldestMovieIds();
//
//        makeFile = new MakeFile(crawlingDynamic.movieIdList, StaticData.OLDEST_MOVIE_ID_LIST_FILE_PATH);
//        makeFile.makeFile();



        //MakeFile makeFile = new MakeFile(crawlingDynamic.tvIdList, StaticData.TV_ID_LIST_FILE_PATH);
        //MakeFile makeFile = new MakeFile(crawlingDynamic.tvIdList, StaticData.POPULAR_DESC_TV_ID_LIST_FILE_PATH);
        //MakeFile makeFile = new MakeFile(crawlingDynamic.tvIdList, StaticData.POPULAR_ASC_TV_ID_LIST_FILE_PATH);
        //MakeFile makeFile = new MakeFile(crawlingDynamic.tvIdList, StaticData.LATEST_TV_ID_LIST_FILE_PATH);
        //MakeFile makeFile = new MakeFile(crawlingDynamic.tvIdList, StaticData.OLDEST_TV_ID_LIST_FILE_PATH);


        //MakeFile makeFile = new MakeFile(crawlingDynamic.movieIdList, StaticData.MOVIE_ID_LIST_FILE_PATH);
        //MakeFile makeFile = new MakeFile(crawlingDynamic.movieIdList, StaticData.POPULAR_DESC_MOVIE_ID_LIST_FILE_PATH);
        //MakeFile makeFile = new MakeFile(crawlingDynamic.movieIdList, StaticData.POPULAR_ASC_MOVIE_ID_LIST_FILE_PATH);
        //MakeFile makeFile = new MakeFile(crawlingDynamic.movieIdList, StaticData.LATEST_MOVIE_ID_LIST_FILE_PATH);
        //MakeFile makeFile = new MakeFile(crawlingDynamic.movieIdList, StaticData.OLDEST_MOVIE_ID_LIST_FILE_PATH);

        //makeFile.makeFile();

        //JsonGetter jsonGetter = new JsonGetter();
        //jsonGetter.getTVProgramList();
        //jsonGetter.getAllTvIdListSorted();
        //jsonGetter.getMovie();

        //MakeFile makeFile = new MakeFile(jsonGetter.getAllTvIdListSorted(), StaticData.SORTED_TV_ID_LIST_FILE_PATH);
        //makeFile.makeFile();

        //ConnectionProvider.getConnection();

    }
}
