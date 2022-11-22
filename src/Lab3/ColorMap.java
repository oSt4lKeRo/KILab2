package Lab3;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import static Lab3.main.containerFile;

class ColorMap {

	public static int[][] getColorMap(String path) throws IOException {

		FileInputStream fis = new FileInputStream(path);
		BufferedInputStream bis = new BufferedInputStream(fis);
		byte[] wb = new byte[4]; // Чтение массива байтов
		byte[] hb = new byte[4]; // Считать байтовый массив высоты
		bis.skip(18); // bis.skip (18); // пропустить первые 18 байт
		bis.read(wb); // ширина считывания
		bis.read(hb); // Считать высоту
		int width = main.byteToint(wb);
		int height = main.byteToint(hb);
		int[][] map = new int[height][width];
		bis.skip(28);//bis.skip(28);

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int blue = bis.read();
				int green = bis.read();
				int red = bis.read();
				Color c = new Color(red, green, blue);
				map[i][j] = c.getRGB();
			}
		}
		return map;
	}
}


