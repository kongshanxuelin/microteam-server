package com.sumslack.app.jd.front.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpTools {
	public static String https(String url) {

        CloseableHttpClient httpClient = null;

        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            }).build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();


            if (httpClient != null) {
                HttpGet httpGet = new HttpGet(url);
                CloseableHttpResponse response = httpClient.execute(httpGet);
                try {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String ret = EntityUtils.toString(entity);
                        EntityUtils.consume(entity);
                        return ret;
                    }
                    return EntityUtils.toString(entity);
                } finally {
                    response.close();
                }
            }

        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	
	private static final String ALGORITHM = "MD5";
	 
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
 
	/**
	 * encode string
	 *
	 * @param algorithm
	 * @param str
	 * @return String
	 */
	public static String encode(String algorithm, String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(str.getBytes());
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
 
	}
 
	/**
	 * encode By MD5
	 *
	 * @param str
	 * @return String
	 */
	public static String encodeByMD5(String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
			messageDigest.update(str.getBytes());
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
 
	}
 
	/**
	 * Takes the raw bytes from the digest and formats them correct.
	 *
	 * @param bytes
	 *            the raw bytes from the digest.
	 * @return the formatted bytes.
	 */
	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		// 把密文转换成十六进制的字符串形式
		for (int j = 0; j < len; j++) { 			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}
	
	
	
	
	 public static StringBuffer httpsRequest(String requestUrl, String requestMethod, String output) throws IOException {
	        URL url = new URL(requestUrl);
	        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
	        connection.setDoOutput(true);
	        connection.setDoInput(true);
	        connection.setUseCaches(false);
	        connection.setRequestMethod(requestMethod);
	        if (null != output) {
	            OutputStream outputStream = connection.getOutputStream();
	            outputStream.write(output.getBytes("UTF-8"));
	            outputStream.close();
	        }
	        // 从输入流读取返回内容
	        InputStream inputStream = connection.getInputStream();
	        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
	        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	        String str = null;
	        StringBuffer buffer = new StringBuffer();
	        while ((str = bufferedReader.readLine()) != null) {
	            buffer.append(str);
	        }
	        bufferedReader.close();
	        inputStreamReader.close();
	        inputStream.close();
	        inputStream = null;
	        connection.disconnect();
	        return buffer;
	    }
	
	
}
