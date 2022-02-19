package utill.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QrUtil {

	/**
	 * 生成二维码图片
	 *
	 * @param text   转化文本
	 * @param width  图片高
	 * @param height 图片宽
	 * @return 图片二进制
	 */
	private static BitMatrix generateImageMatrix(String text, int width, int height) throws WriterException, IOException {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

		return bitMatrix;
	}

	public static void generateImageToFile(String text, int width, int height, String fillPath) throws Exception {
		try {
			BitMatrix bitMatrix = generateImageMatrix(text, width, height);
			Path path = FileSystems.getDefault().getPath(fillPath);
			MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
		} catch (WriterException | IOException e) {
			throw new Exception("二维码生成失败");
		}
	}

	public static byte[] generateImageToByte(String text, int width, int height) throws Exception {
		try {
			BitMatrix bitMatrix = generateImageMatrix(text, width, height);
			ByteArrayOutputStream pngByteOutStream=new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngByteOutStream);
			return pngByteOutStream.toByteArray();
		} catch (WriterException | IOException e) {
			throw new Exception("二维码生成失败");
		}
	}
}
