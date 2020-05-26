package com.xhr.mysql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App {

	/**
	 * 肉鸡
	 */
	public static File rfile = new File("result.txt");

	public static void main(String[] args) throws IOException, InterruptedException {
		File file = new File("ip.txt");
		File passwords = new File("passwords.txt");
		// 使用true，即进行append file

		String s = "";
		int i = 0;
		FileWriter fileWritter = new FileWriter(rfile.getName(), true);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));// 构造一个BufferedReader类来读取文件
		while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
			if (i != 0 && i % 25 == 0) {
				Thread.sleep(600000);
			}
			new Thread(new Mysql(s, 3306, fileWritter, passwords)).start();
			i++;
		}
		fileWritter.close();
	}

	/*
	 * 将时间戳转换为时间
	 */
	public static String stampToDate(String s) {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(s);
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}

}
