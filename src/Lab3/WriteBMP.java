package Lab3;

import java.awt.*;
import java.io.*;


class WriteBMP {

	String fileOrig = main.containerFile;

//	public void writeBMP(String file) throws IOException {
//		FileInputStream fis = new FileInputStream(fileOrig);
//		BufferedInputStream bis = new BufferedInputStream(fis);
//		byte[] temp1 = new byte[18];
//		byte[] wb = new byte[4]; // Чтение массива байтов
//		byte[] hb = new byte[4]; // Считать байтовый массив высоты
//		byte[] temp2 = new byte[28];
//		bis.read(temp1); // bis.skip (18); // пропустить первые 18 байт
//		bis.read(wb); // ширина считывания
//		bis.read(hb); // Считать высоту
//		bis.read(temp2);
//		int width = main.byteToint(wb);
//		int height = main.byteToint(hb);
//		int[][] map = ColorMap.getColorMap();
//		FileOutputStream fos = new FileOutputStream(file);
//		BufferedOutputStream bos = new BufferedOutputStream(fos);
//		bos.write(temp1);
//		bos.write(wb);
//		bos.write(hb);
//		bos.write(temp2);
//		for (int i = 0; i < height; i++) {
//			for (int j = 0; j < width; j++) {
//				Color c = new Color(map[i][j]);
//				int	blue = c.getBlue();
//				int	green = c.getGreen();
//				int	red = c.getRed();
//
//				bos.write(blue);
//				bos.write(green);
//				bos.write(red);
//			}
//		}
//		bos.flush();
//		fos.close();
//	}


	public void writeToBMP(String file, int[][] map) throws IOException {
		FileInputStream fis = new FileInputStream(fileOrig);
		BufferedInputStream bis = new BufferedInputStream(fis);
		byte[] temp1 = new byte[18];
		byte[] wb = new byte[4]; // Чтение массива байтов
		byte[] hb = new byte[4]; // Считать байтовый массив высоты
		byte[] temp2 = new byte[28];
		bis.read(temp1); // bis.skip (18); // пропустить первые 18 байт
		bis.read(wb); // ширина считывания
		bis.read(hb); // Считать высоту
		bis.read(temp2);
		int width = main.byteToint(wb);
		int height = main.byteToint(hb);
		FileOutputStream fos = new FileOutputStream(file);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		bos.write(temp1);
		bos.write(wb);
		bos.write(hb);
		bos.write(temp2);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Color c = new Color(map[i][j]);
				int	blue = c.getBlue();
				int	green = c.getGreen();
				int	red = c.getRed();

				bos.write(blue);
				bos.write(green);
				bos.write(red);
			}
		}
		bos.flush();
		fos.close();
	}
}
