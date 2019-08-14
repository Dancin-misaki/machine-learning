package tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

public class Tools {
	
	public static String arrayToStr(double[][] arr) {
		StringBuilder s = new StringBuilder();
		for(double[] a : arr) {
			s.append(Arrays.toString(a));
		}
		return s.toString();
	}
	public static double[] rand1DArr(int i) {
		Random rand = new Random();
		double[] re = new double[i];
		for(int _i = 0; _i < i; _i++) {
			re[_i] = rand.nextDouble();
		}
		return re;
	}
	public static double[][] rand2DArr(int i, int j) {
		double[][] re = new double[i][j];
		for(int _i = 0; _i < i; _i++) {
			re[_i] = rand1DArr(j);
		}
		return re;
	}
	public static double[][][] rand3DArr(int i, int j, int k) {
		double[][][] re = new double[i][j][k];
		for(int _i = 0; _i < i; _i++) {
			re[_i] = rand2DArr(j,k);
		}
		return re;
	}
	
	public static double[] fill1DArr(int i, double n) {
		double[] re = new double[i];
		for(int _i = 0; _i < i; _i++) {
			re[_i] = n;
		}
		return re;
	}
	public static double[][] fill2DArr(int i, int j, double n) {
		double[][] re = new double[i][j];
		for(int _i = 0; _i < i; _i++) {
			re[_i] = fill1DArr(j, n);
		}
		return re;
	}
	public static double[][][] fill3DArr(int i, int j, int k, double n) {
		double[][][] re = new double[i][j][k];
		for(int _i = 0; _i < i; _i++) {
			re[_i] = fill2DArr(j,k,n);
		}
		return re;
	}
	
	public static int[] outOfOrderArr(int length) {
		int[] tempArr = new int[length];
		for(int i = 0; i < length; i++) {
			tempArr[i] = i;
		}
		Random rand = new Random();
		int[] re = new int[length];
		int len = length;
		for(int i = 0; i < length; i++) {
			int select = rand.nextInt(len);
			re[i] = tempArr[select];
			int t = tempArr[select];
			tempArr[select] = tempArr[len-1];
			tempArr[len-1] = t;
			len--;
		}
		return re;
	}
	
	
	public static double[][] getGrayArrays(String path){
		BufferedImage image = null;
		File f = null;
		double[][] arr = null;
		try {
			f = new File(path);
			image = ImageIO.read(f);

			int width = image.getWidth();
			int height = image.getHeight();
			arr = new double[width][height];

			for (int j = 0; j < height; j++) {
				for (int i = 0; i < width; i++) {
					int p = image.getRGB(i, j);
					//int a = (p >> 24) & 0xff;
					int r = (p >> 16) & 0xff;
					int g = (p >> 8) & 0xff;
					int b = p & 0xff;

					arr[i][j] = (int)0.299*r + 0.587*g + 0.114*b;
					if(arr[i][j] > 255)arr[i][j] = 255;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arr;
	}

	public static double[][] normalization1(double[][] arr){
		double maxGray = arr[0][0];
		double minGray = arr[0][0];
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				if(arr[i][j] < minGray)minGray = arr[i][j];
				if(arr[i][j] > maxGray)maxGray = arr[i][j];
			}
		}
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				arr[i][j] = (arr[i][j] - minGray) / (maxGray - minGray);
			}
		}
		return arr;
	}
	
	public static double[][] normalization255(double[][] arr){
		double maxGray = arr[0][0];
		double minGray = arr[0][0];
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				if(arr[i][j] < minGray)minGray = arr[i][j];
				if(arr[i][j] > maxGray)maxGray = arr[i][j];
			}
		}
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				arr[i][j] = (arr[i][j] - minGray) / (maxGray - minGray);
				arr[i][j] = arr[i][j] * 255;
			}
		}
		return arr;
	}
	
	public static double[][] binary1(double[][] arr, double threshold){
		double[][] re = new double[arr.length][arr[0].length];
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				re[i][j] = arr[i][j] > threshold ? 1 : 0;
			}
		}
		return re;
	}
	
	public static double[][] binary255(double[][] arr, int threshold){
		double[][] re = new double[arr.length][arr[0].length];
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				re[i][j] = arr[i][j] > threshold ? 255 : 0;
			}
		}
		return re;
	}
	
	public static double[][] reverse1(double[][] arr){
		double[][] re = new double[arr.length][arr[0].length];
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				re[i][j] = 1 - arr[i][j];
			}
		}
		return re;
	}
	
	public static double[][] reverse255(double[][] arr){
		double[][] re = new double[arr.length][arr[0].length];
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				re[i][j] = 255- arr[i][j];
			}
		}
		return re;
	}
}
