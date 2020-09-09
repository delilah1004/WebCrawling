package com.mynetflix.db.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MakeFile {

    private ArrayList<Long> tvIdList;
    private String filePath;

    public MakeFile(ArrayList<Long> tvIdList, String filePath) {
        this.tvIdList = tvIdList;
        this.filePath = filePath;
    }

    public void makeFile() {

        File file = new File(filePath);
        FileWriter fw = null;

        int index = 1;

        try {
            fw = new FileWriter(file);

            for (long id : tvIdList) {

                fw.write(id + " ");

                if (index % 15 == 0) {
                    fw.write("\n");
                }

                index++;
            }

            fw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
