package com.mynetflix.db.jdbc;

import oracle.jdbc.OracleDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionProvider {

    public static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    public static final String id ="netflix";
    public static final String pass = "1234";


    public static Connection getConnection() {
        Connection conn = null;
        Statement stmt = null;

        try {

            DriverManager.registerDriver(new OracleDriver());
            System.out.println("드라이버 로드 성공");

            conn = DriverManager.getConnection(url,id,pass);
            System.out.println("DB 연결 성공");

            String sql = "insert into tv_program values(tv_program_tv_seq_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            System.out.println(sql);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return conn;
    }
}