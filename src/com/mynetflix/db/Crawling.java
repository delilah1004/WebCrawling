package com.mynetflix.db;

import com.mynetflix.db.crawl.CrawlingDynamic;
import com.mynetflix.db.parser.JsonGetter;

public class Crawling extends CrawlingDynamic {

    public static void main(String[] args) {
        //CrawlingDynamic crawlingDynamic = new CrawlingDynamic();
        //crawlingDynamic.CrawlAllTVIds();

        //MakeFile makeFile = new MakeFile(crawlingDynamic.tvIdList, StaticData.TV_ID_LIST_FILE_PATH);
        //makeFile.makeFile();

        JsonGetter jsonGetter = new JsonGetter();
        jsonGetter.getTV();
        //jsonGetter.getMovie();
    }
}
