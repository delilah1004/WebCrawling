package com.mynetflix.db.file;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ReadFile {

    private ArrayList<Long> tvIdList;
    private final String filePath;

    public ReadFile(String filePath) {
        this.filePath = filePath;
    }

    public void readFile() {

        FileReader fr = null;
        BufferedReader br = null;
        StringTokenizer st;

        String line;
        tvIdList = new ArrayList<>();

        try {
            fr = new FileReader(new File(filePath));
            br = new BufferedReader(fr);

            // file 한줄씩 읽기
            while ((line = br.readLine()) != null) {

                // StringTokenizer 에 한 줄 담기
                st = new StringTokenizer(line);

                // StringTokenizer 에 담긴 토큰을 list 에 추가
                while (st.hasMoreTokens()) {
                    tvIdList.add(Long.parseLong(st.nextToken()));
                }
            }

        } catch (
                IOException e) {
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
