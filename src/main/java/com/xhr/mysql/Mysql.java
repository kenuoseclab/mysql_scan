package com.xhr.mysql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;

public class Mysql implements Runnable {

	private String host;
	private int port;
	private FileWriter fileWritter;
	private File userPass;

	public Mysql(String host, int port, FileWriter fileWriter, File userPass) {
		this.host = host;
		this.port = port;
		this.fileWritter = fileWriter;
		this.userPass = userPass;
	}

	public void run() {
		BufferedReader br2 = null;
		try {
			br2 = new BufferedReader(new InputStreamReader(new FileInputStream(userPass), "GBK"));
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
		} // 构造一个BufferedReader类来读取文件
		String s = null;
		try {
			while ((s = br2.readLine()) != null) {// 使用readLine方法，一次读一行
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager
							.getConnection("jdbc:mysql://" + host + ":3306/mysql?serverTimezone=UTC", "root", s);
					if (conn != null) {
						fileWritter.write(host + "@ root:" + s + "\r\n");
						fileWritter.flush();
						br2.close();
						return;
					}
				} catch (Throwable e) {
					System.out.println(App.stampToDate(String.valueOf(System.currentTimeMillis())) + ": 无法连接到主机" + host + " 用户名:root 密码" + s);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}

}