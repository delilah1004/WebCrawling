package com.mynetflix.db;

import com.mynetflix.db.crawl.CrawlingDynamic;
import com.mynetflix.db.file.MakeFile;
import com.mynetflix.db.jdbc.ConnectionProvider;
import com.mynetflix.db.parser.JsonGetter;

public class Crawling extends CrawlingDynamic {

    public static void main(String[] args) {
        CrawlingDynamic crawlingDynamic = new CrawlingDynamic();
        //crawlingDynamic.CrawlAllTVIds();
        //crawlingDynamic.CrawlAllMovieIds();
        //crawlingDynamic.CrawlPopularDescTVIds();
        crawlingDynamic.CrawlPopularAscTVIds();

        //MakeFile makeFile = new MakeFile(crawlingDynamic.tvIdList, StaticData.TV_ID_LIST_FILE_PATH);
        //MakeFile makeFile = new MakeFile(crawlingDynamic.movieIdList, StaticData.MOVIE_ID_LIST_FILE_PATH);
        //MakeFile makeFile = new MakeFile(crawlingDynamic.tvIdList, StaticData.POPULAR_DESC_TV_ID_LIST_FILE_PATH);
        MakeFile makeFile = new MakeFile(crawlingDynamic.tvIdList, StaticData.POPULAR_ASC_TV_ID_LIST_FILE_PATH);

        makeFile.makeFile();

        //JsonGetter jsonGetter = new JsonGetter();
        //jsonGetter.getTVProgramList();
        //jsonGetter.getAllTvIdListSorted();
        //jsonGetter.getMovie();

        //MakeFile makeFile = new MakeFile(jsonGetter.getAllTvIdListSorted(), StaticData.SORTED_TV_ID_LIST_FILE_PATH);
        //makeFile.makeFile();

        //ConnectionProvider.getConnection();

    }
}
