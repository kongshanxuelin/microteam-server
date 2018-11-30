package com.sumslack.app.jd.front.tool;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.imageio.ImageIO;

import com.sumslack.jsptagex.util.StrUtil;

import net.coobird.thumbnailator.Thumbnails;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ImageUtils {
	public static final int IMAGE_SIZE = 150;
	public static final AffineTransform transform = new AffineTransform();

	public static void createPreviewImage(String srcFile, String destFile) {
		try {
			Thumbnails.of(srcFile).size(IMAGE_SIZE, IMAGE_SIZE).toFile(destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* 下载图片 */
	public static void download(String urlString, String filename) throws Exception {
		URL url = new URL(urlString);
		URLConnection con = url.openConnection();
		InputStream is = con.getInputStream();
		byte[] bs = new byte[1024];
		int len;
		OutputStream os = new FileOutputStream(filename);
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		os.close();
		is.close();
	}

	/**
	 * 将网络图片进行Base64位编码
	 * 
	 * @param imgUrl
	 *            图片的url路径，如http://.....xx.jpg
	 * @return
	 */
	public static String encodeImgageToBase64(URL imageUrl) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		ByteArrayOutputStream outputStream = null;
		try {
			BufferedImage bufferedImage = ImageIO.read(imageUrl);
			outputStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "jpg", outputStream);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
	}

	public static String encodeImgage(URL imageUrl) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		ByteArrayOutputStream outputStream = null;
		try {
			BufferedImage bufferedImage = ImageIO.read(imageUrl);
			outputStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "jpg", outputStream);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		String s = encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
//		s = StrUtil.replace(s, "\r", "");
//		s = StrUtil.replace(s, "\n", "");
//		try {
//			s = URLEncoder.encode(s, "utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		return s;
	}

	/**
	 * 将本地图片进行Base64位编码
	 */
	public static String encodeImgageToBase64(File imageFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		ByteArrayOutputStream outputStream = null;
		try {
			BufferedImage bufferedImage = ImageIO.read(imageFile);
			outputStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "jpg", outputStream);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		String s = encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
		return s;
	}

	public static String encodeImageNoEncoder(File imageFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		ByteArrayOutputStream outputStream = null;
		try {
			BufferedImage bufferedImage = ImageIO.read(imageFile);
			outputStream = new ByteArrayOutputStream();
			if (imageFile.getName().toLowerCase().endsWith(".png"))
				ImageIO.write(bufferedImage, "png", outputStream);
			else
				ImageIO.write(bufferedImage, "jpg", outputStream);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		String s = encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
		s = StrUtil.replace(s, "\r", "");
		s = StrUtil.replace(s, "\n", "");
		return s;
	}

	public static String encodeImage(File imageFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		ByteArrayOutputStream outputStream = null;
		try {
			BufferedImage bufferedImage = ImageIO.read(imageFile);
			outputStream = new ByteArrayOutputStream();
			if (imageFile.getName().toLowerCase().endsWith(".png"))
				ImageIO.write(bufferedImage, "png", outputStream);
			else
				ImageIO.write(bufferedImage, "jpg", outputStream);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		String s = encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
//		s = StrUtil.replace(s, "\r", "");
//		s = StrUtil.replace(s, "\n", "");
//		try {
//			s = URLEncoder.encode(s, "utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		return s;
	}
	/**
	 * @Description: 将base64编码字符串转换为图片
	 * @Author:
	 * @CreateTime:
	 * @param imgStr
	 *            base64编码字符串
	 * @param path
	 *            图片路径-具体到文件
	 * @return
	 */
	public static boolean generateImage(String imgStr, String path) {
		if (imgStr == null)
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(path);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public final static void addImageWeatermark(String targetImg, String waterImg, int x, int y, float alpha) {
		try {
			File file = new File(targetImg);
			Image image = ImageIO.read(file);
			int width = image.getWidth(null);
			int height = image.getHeight(null);
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bufferedImage.createGraphics();
			g.drawImage(image, 0, 0, width, height, null);

			try {
				ImageUtils.zoomImage(waterImg, waterImg, 150, 150);
			} catch (Exception e) {
				e.printStackTrace();
			}

			Image waterImage = ImageIO.read(new File(waterImg)); // 水印文件
			int width_1 = waterImage.getWidth(null);
			int height_1 = waterImage.getHeight(null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

			// int widthDiff = width - width_1;
			// int heightDiff = height - height_1;
			// if (x < 0) {
			// x = widthDiff / 2;
			// } else if (x > widthDiff) {
			// x = widthDiff;
			// }
			// if (y < 0) {
			// y = heightDiff / 2;
			// } else if (y > heightDiff) {
			// y = heightDiff;
			// }
			g.drawImage(waterImage, width - width_1 - x, height - height_1 - y, width_1, height_1, null); // 水印文件结束
			g.dispose();
			ImageIO.write(bufferedImage, "jpg", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void zoomImage(String src, String dest, int w, int h) throws Exception {

		double wr = 0, hr = 0;
		File srcFile = new File(src);
		File destFile = new File(dest);

		BufferedImage bufImg = ImageIO.read(srcFile); // 读取图片
		Image Itemp = bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH);// 设置缩放目标图片模板

		wr = w * 1.0 / bufImg.getWidth(); // 获取缩放比例
		hr = h * 1.0 / bufImg.getHeight();

		AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
		Itemp = ato.filter(bufImg, null);
		try {
			ImageIO.write((BufferedImage) Itemp, dest.substring(dest.lastIndexOf(".") + 1), destFile); // 写入缩减后的图片
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void makeImageColorToBlackWhite(String imagePath) {
		int[][] result = getImageGRB(imagePath);
		int[] rgb = new int[3];
		BufferedImage bi = new BufferedImage(result.length, result[0].length, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				rgb[0] = (result[i][j] & 0xff0000) >> 16;
				rgb[1] = (result[i][j] & 0xff00) >> 8;
				rgb[2] = (result[i][j] & 0xff);
				int color = (int) (rgb[0] * 0.3 + rgb[1] * 0.59 + rgb[2] * 0.11);
				// color = color > 128 ? 255 : 0;
				bi.setRGB(i, j, (color << 16) | (color << 8) | color);
			}
		}
		try {
			ImageIO.write(bi, "JPEG", new File("d:/w2.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取图片的像素点
	 * 
	 * @param filePath
	 * @return
	 */
	public static int[][] getImageGRB(String filePath) {
		File file = new File(filePath);
		int[][] result = null;
		if (!file.exists()) {
			System.out.println("图片不存在");
			return result;
		}
		try {
			BufferedImage bufImg = ImageIO.read(file);
			int height = bufImg.getHeight();
			int width = bufImg.getWidth();
			result = new int[width][height];
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					result[i][j] = bufImg.getRGB(i, j) & 0xFFFFFF;
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public static void main(String[] args) {
		//String imageUrl = "http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI5snx6fqLSTF77lOeibgcNCF7d4gVeIyp6rRFTPmtNe8BpADvrd8l03CyvtUIibDgCN4Klgaiad4Czw/0";
		// String str = convert2Base64(imageUrl);
		try {
			// download(imageUrl,"f:\\1.jpg");
			//String str = encodeImgageToBase64(new URL(imageUrl));
			//System.out.println(str);
			makeImageColorToBlackWhite("d:/w.jpg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
