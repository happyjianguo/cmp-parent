package com.jjb.cmp.biz.util;

import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Decoder;

/**
 * @ClassName Base64Util
 * Company jydata-tech
 * @Description TODO
 * Author smh
 * Date 2019/3/26 15:35
 * Version 1.0
 */
public class Base64UtilMultipart {
    public static MultipartFile base64ToMultipart(String base64) {
        try {
            String[] baseStr = base64.split(",");
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = new byte[0];
            b = decoder.decodeBuffer(baseStr[1]);

            for(int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            return new Base64DecodedMultipartFile(b, baseStr[0]);
        } catch (Exception e) {
            System.out.println(e.getMessage());;
            return null;
        }
    }
}
