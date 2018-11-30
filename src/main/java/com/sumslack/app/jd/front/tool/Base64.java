package com.sumslack.app.jd.front.tool;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
 
public class Base64 {
	private static sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
	private static sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
 
	/**
	 * base64加密字符串.
	 * 
	 * @param oldStr
	 * @return
	 */
	public static String encode(String oldStr) {
		return encoder.encode(oldStr.getBytes());
	}
 
	/**
	 * base64解密字符串.
	 * @param oldStr
	 * @return
	 * @throws IOException
	 */
	public static String decode(String oldStr) throws IOException {
		return new String(decoder.decodeBuffer(oldStr));
	}
 
	/**
	 * base64编码输入流.
	 * @param inputStream 输入流
	 * @param outputStream 输出流
	 * @throws IOException
	 */
	public static void encode(InputStream inputStream, OutputStream outputStream)
			throws IOException {
		encoder.encode(inputStream, outputStream);
		inputStream.close();
		outputStream.close();
	}
 
	
	/**
	 * base64解密输入流.
	 * @param inputStream
	 * @param outputStream
	 * @throws IOException
	 */
	public static void decode(InputStream inputStream, OutputStream outputStream)
			throws IOException {
		decoder.decodeBuffer(inputStream, outputStream);
		inputStream.close();
		outputStream.close();
	}
 
	/**
	 * base64加密文件.
	 * @param inFileName 源文件
	 * @param outFileName 新的文件
	 * @throws IOException
	 */
	public static void encode(String inFileName,String outFileName) throws IOException{
		File oldFile = new File(inFileName);
		File newFile = new File(outFileName);
		InputStream input = new BufferedInputStream( 
				new FileInputStream(oldFile)); 
		OutputStream out = new BufferedOutputStream(
				new FileOutputStream(newFile));
		Base64.encode(input, out);
	}
	
	/**
	 * base64解密文件.
	 * @param inFileName 源文件
	 * @param outFileName 新的文件
	 * @throws IOException
	 */
	public static void decode(String inFileName,String outFileName) throws IOException{
		File oldFile = new File(inFileName);
		File newFile = new File(outFileName);
		InputStream input = new BufferedInputStream( 
				new FileInputStream(oldFile)); 
		OutputStream out = new BufferedOutputStream(
				new FileOutputStream(newFile));
		Base64.decode(input, out);
	}
	
	public static void main(String[] a) throws IOException {
		Base64.encode("E:\\workplace\\testMyFrame\\bb.mp3","d:\\hah.base64");
		Base64.decode("d:\\hah.base64","d:\\ddd.mp3");
		System.out.println("ok");
	}
}
