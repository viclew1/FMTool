package fr.lewon.dofus.fm.imageprocessing;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import fr.lewon.dofus.fm.common.CaracType;
import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Word;
import nu.pattern.OpenCV;

public enum ImageOCR {

	INSTANCE;

	private ImageOCR() {}

	public CaracType getCaracType(BufferedImage img) throws Exception{
		Mat imgMat = img2Mat(img);
		Imgproc.threshold(imgMat, imgMat, 60, 255, Imgproc.THRESH_BINARY);
		Core.bitwise_not( imgMat, imgMat );
		//Imgcodecs.imwrite("test.png",imgMat);
		BufferedImage imgBuffered = Mat2BufferedImage(imgMat);

		File outputfile = new File("image.jpg");
		ImageIO.write(imgBuffered, "jpg", outputfile);

		Tesseract tessInst = new Tesseract();
		tessInst.setDatapath("Tess4J");
		tessInst.setLanguage("eng");
		tessInst.setPageSegMode(ITessAPI.TessPageSegMode.PSM_SINGLE_LINE);
		tessInst.setOcrEngineMode(ITessAPI.TessOcrEngineMode.OEM_DEFAULT);
		
		String result=tessInst.doOCR(imgBuffered);
		List<Word> words = tessInst.getWords(imgBuffered, ITessAPI.TessPageIteratorLevel.RIL_BLOCK);
		
		System.out.println(words);
		
		return null;
	}

	protected BufferedImage Mat2BufferedImage(Mat matrix)throws Exception {        
		MatOfByte mob=new MatOfByte();
		Imgcodecs.imencode(".jpg", matrix, mob);
		byte ba[]=mob.toArray();

		BufferedImage bi=ImageIO.read(new ByteArrayInputStream(ba));
		return bi;
	}

	protected Mat img2Mat(BufferedImage in) {
		Mat out;
		byte[] data;
		int r, g, b;

		if (in.getType() == BufferedImage.TYPE_INT_RGB) {
			out = new Mat(in.getHeight(), in.getWidth(), CvType.CV_8UC3);
			data = new byte[in.getWidth() * in.getHeight() * (int) out.elemSize()];
			int[] dataBuff = in.getRGB(0, 0, in.getWidth(), in.getHeight(), null, 0, in.getWidth());
			for (int i = 0; i < dataBuff.length; i++) {
				data[i * 3] = (byte) ((dataBuff[i] >> 0) & 0xFF);
				data[i * 3 + 1] = (byte) ((dataBuff[i] >> 8) & 0xFF);
				data[i * 3 + 2] = (byte) ((dataBuff[i] >> 16) & 0xFF);
			}
		} else {
			out = new Mat(in.getHeight(), in.getWidth(), CvType.CV_8UC1);
			data = new byte[in.getWidth() * in.getHeight() * (int) out.elemSize()];
			int[] dataBuff = in.getRGB(0, 0, in.getWidth(), in.getHeight(), null, 0, in.getWidth());
			for (int i = 0; i < dataBuff.length; i++) {
				r = (byte) ((dataBuff[i] >> 0) & 0xFF);
				g = (byte) ((dataBuff[i] >> 8) & 0xFF);
				b = (byte) ((dataBuff[i] >> 16) & 0xFF);
				data[i] = (byte) ((0.21 * r) + (0.71 * g) + (0.07 * b));
			}
		}
		out.put(0, 0, data);
		return out;
	}

	public static void main(String[] args){
		OpenCV.loadShared();
		try {
			int thresh = 60;
			BufferedImage img1 = ImageIO.read(new File("imgTest/rougeSURgrisX2.png"));
			ImageOCR.INSTANCE.getCaracType(img1);				
		} catch (Exception e) {
		}
	}
}
