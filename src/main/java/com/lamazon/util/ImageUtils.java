package com.lamazon.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
/*
 *  이미지 리사이징을 위한 함수
 *  일반 크기 수정 및 썸네일을 위한 크기 수정 두가지로 사용됨
 * */
public class ImageUtils {

	// ShowImageServlet
	public static void image_resizing(String imagepath, String savePath, int width, int height) {
		// 일반 이미지
		image_full(imagepath, savePath ,width, height, false);
	}

	// ShowThumbImageServlet
	public static void image_resize_thumb(String imagepath, String savePath, int width, int height) {
		// 섬네일
		image_full(imagepath, savePath, width, height, true);
	}

	private static void image_full(String imagepath, String savepath, int width, int height, boolean isThumb) {
		// 크기 조절 사이즈 결정
		BufferedImage originalImage;

		try {
			originalImage = ImageIO.read(new File(imagepath));

			int oriwidth = originalImage.getWidth();
			int oriheight = originalImage.getHeight();
			int rewidth = 0, reheight = 0;
			double ratio = (double) originalImage.getWidth() / (double) originalImage.getHeight();
			if (width > 0 && height > 0) {
				if (isThumb) {
					rewidth = width;
					reheight = (int) (width / ratio);
					if (reheight < height) {
						reheight = height;
						rewidth = (int) (height * ratio);
					}
				} else {
					rewidth = width;
					reheight = (int) (width / ratio);
					if (reheight > height) {
						reheight = height;
						rewidth = (int) (height * ratio);
					}
				}

			} else if (width > 0) {
				rewidth = width;
				reheight = (int) (width / ratio);
			} else if (height > 0) {
				reheight = height;
				rewidth = (int) (height * ratio);
			}

			if (reheight < oriheight) {
				im_res_wr(originalImage, rewidth, reheight, savepath);
			} else {
				Path source = Paths.get(imagepath);
				Path destination = Paths.get(savepath);

				//Files.copy(source, destination);//이미지가 기존에 존재하고 있는 복사되지 않아서 Method 변경
				//2016.11.15
				Utils.file_copy(source.toString(), destination.toString());

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void im_res_wr(BufferedImage originalImage, int rewidth, int reheight, String savepath) {

		String repath = savepath.substring(0, savepath.lastIndexOf("/"));

		File f = new File(repath);
		if (!f.exists())	f.mkdirs();


		// 이미지 크기 수정 및 저장
		int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB	: originalImage.getType();
		BufferedImage resizedImage = new BufferedImage(rewidth, reheight, type);
		Graphics2D g = resizedImage.createGraphics();

		g.drawImage(originalImage, 0, 0, rewidth, reheight, null);
		g.dispose();
		try {
			ImageIO.write(resizedImage, "jpg", new File(savepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//base64인코딩된 이미지파일내용을 디코딩해서 파일로 저장
	public static boolean imageDecoder(String base64, String target, String extension ){

		String data = base64.split(",")[1];

		byte[] imageBytes = DatatypeConverter.parseBase64Binary(data);

		try {
			BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(imageBytes));

			ImageIO.write(bufImg, extension, new File(target));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return false;
		}

		return true;
	}

	//base64인코딩된 이미지파일내용을 디코딩해서 파일로 저장
	public static boolean imageDecoderWithoutImageType(String base64, String target, String extension ){

		//String data = base64.split(",")[1];

		byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64);

		try {
			BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(imageBytes));

			ImageIO.write(bufImg, extension, new File(target));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return false;
		}

		return true;
	}

	//base64인코딩된 이미지파일내용중 확장자명 가져오기
	public static String getExtensionName(String base64){
		String data = base64.split(",")[0].split("/")[1].split(";")[0].trim();
		return data;
	}
}
