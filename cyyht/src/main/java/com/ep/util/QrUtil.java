package com.ep.util;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QrUtil {
    public static String writeQrBase64(String content) {
    	 QRCodeWriter qrCodeWriter = new QRCodeWriter();
	        BitMatrix bitMatrix;
			try {
				bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 600, 600);
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

		        Base64.Encoder encoder = Base64.getEncoder();

		        String text = encoder.encodeToString(outputStream.toByteArray());

		        return text;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}

	}
}
