package com.sumslack.web.working.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sumslack.jsptagex.rest.TagRest;
import com.sumslack.jsptagex.tag.TagConst;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.jsptagex.web.servlet.AjaxServlet;
import com.sumslack.web.working.bean.UserBean;

@TagRest(value = "view/qrcode")
public class QrCodeLoginServlet extends AjaxServlet {

	private static final int BLACK = -16777216;
	private static final int WHITE = -1;
	
	public static Map<String,UserBean> qrcodeMap = new HashMap();

	private BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {  
            String loginId = StrUtil.formatNullStr(req.getParameter("loginId"));
            /**/
            String content = StrUtil.formatNullStr(TagConst.globalMap.get("wx.qrcode.login")) + "?loginId="+loginId;
            
            int imgWidth = 180;  
            int imgHeight = 180;  
              
            String width = req.getParameter("w");  
            String height = req.getParameter("h");  
            if(width!=null&&!width.equals("")){  
                try {  
                    imgWidth = Integer.parseInt(width);  
                } catch (Exception e) {}  
            }  
            if(height!=null&&!height.equals("")){  
                try {  
                    imgHeight = Integer.parseInt(height);  
                } catch (Exception e) {}  
            }  
              
            BitMatrix byteMatrix;  
            Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();  
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
            byteMatrix = new MultiFormatWriter().encode(  
                    new String(content.getBytes("UTF-8"),"ISO-8859-1"),  
                    BarcodeFormat.QR_CODE,   
                    imgWidth,   
                    imgHeight,   
                    hints);  
  
            BufferedImage image = toBufferedImage(byteMatrix);  
            resp.setContentType("image/png");  
            ImageIO.write(image, "png", resp.getOutputStream());    
        } catch (Exception e) {  
              
        }  
	}
}
