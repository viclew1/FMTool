package fr.lewon.dofus.fm.imageprocessing;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import fr.lewon.dofus.fm.common.CaracType;

public enum ImageOCR {

	INSTANCE;

	private ImageOCR() {}

	public CaracType getCaracType(BufferedImage img) throws IOException{
		Mat imgMat = matify(img);
		Mat imgThresholdMat = new Mat();
		Imgproc.threshold(imgMat, imgThresholdMat, 0, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C);
		Imgcodecs.imwrite("test.jpg",imgThresholdMat);
		return null;
	}

	// Convert image to Mat
	public static Mat matify(BufferedImage im) {
	    // Convert INT to BYTE
	    //im = new BufferedImage(im.getWidth(), im.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
	    // Convert bufferedimage to byte array
	    byte[] pixels = ((DataBufferByte) im.getRaster().getDataBuffer())
	            .getData();

	    // Create a Matrix the same size of image
	    Mat image = new Mat(im.getHeight(), im.getWidth(), CvType.CV_8UC3);
	    // Fill Matrix with image values
	    image.put(0, 0, pixels);

	    return image;

	}
	
	public static void main(String[] args){
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("imgTest/vertSURrouge.png"));
		    ImageOCR.INSTANCE.getCaracType(img);
		} catch (IOException e) {
		}
	}
}
