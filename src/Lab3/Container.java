package Lab3;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import static Lab3.main.containerFile;

public class Container {
	static byte[] temp1 = new byte[18];
	static byte[] temp2 = new byte[28];
	static int height;
	static int width;
	static byte[] wb = new byte[4]; // Чтение массива байтов
	static byte[] hb = new byte[4];
	static int[][] map;

	Message message = new Message();

	Container(Message message){
		this.message = message;
	}

	public void readBMP() throws IOException {
		FileInputStream fis = new FileInputStream(containerFile);
		BufferedInputStream bis = new BufferedInputStream(fis);
		bis.read(temp1);
		bis.read(wb);
		bis.read(hb);
		width = main.byteToint(wb);
		height = main.byteToint(hb);
		map = new int[height][width];
		bis.read(temp2);//bis.skip(28);

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int blue = bis.read();
				int green = bis.read();
				int red = bis.read();
				Color c = new Color(red, green, blue);
				map[i][j] = c.getRGB();
			}
		}
		printMessage();
	}

	public void addElemInBlue() {
		int i = 0; int j = 0; int y = 0;
		int coutn = 0;
		for(int k = 0; k < message.bitArr.size(); k ++) {

			if (j == map[i].length) {
				i++;
				j = 0;
			}
			Color c = new Color(map[i][j]);
			int blue = c.getBlue();
			String str = Integer.toBinaryString(blue);
			StringBuffer newStr = new StringBuffer(str);
			if (str.length() < 8) {
				for (int z = 0; z < (8 - str.length()); z++) {
					newStr.insert(0, 0);
				}
			}
			str = newStr.toString();
			char[] arr = str.toCharArray();
			arr[7] = message.bitArr.get(k);
//			char x = '1';
//			arr[5] =x;
//			arr[4] =x;
//			arr[3] =x;
//			arr[2] =x;
//			arr[1] =x;
//			arr[0] =x;
			str = "";
			for(int l = 0; l < arr.length; l++){
				str += arr[l];
			}
			blue = Integer.parseInt(str, 2);
			Color c2 = new Color(c.getRed(), c.getGreen(), blue);
			map[i][j] = c2.getRGB();
			coutn++;
			j++;
		}
	}

	public static void write() throws IOException {
		WriteBMP wb = new WriteBMP();
		wb.writeToBMP("C:\\material\\lab3\\Без названия(coding).bmp", map);
	}

	public void printMessage() {
		int count = 0;
		for (int i = 0; i < temp1.length; i++) {
//			System.out.print(temp1[i] + " ");

		}
		for (int i = 0; i < wb.length; i++) {
//			System.out.print(wb[i] + " ");

		}
		for (int i = 0; i < hb.length; i++) {
//			System.out.print(hb[i] + " ");

		}
		for (int i = 0; i < temp2.length; i++) {
//			System.out.print(temp2[i] + " ");

		}
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				Color c = new Color(map[i][j]);

//				System.out.print(c.getRed() + " " + c.getGreen() + " " + c.getBlue() + "\t");
				count++;
			}
		}
		System.out.println();
		System.out.println(count);
	}
}
