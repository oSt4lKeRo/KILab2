package Lab3;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Message {
	static ArrayList<Character> bitArr = new ArrayList<>();

	public static void readFile() throws IOException {
		String path = "C:\\material\\lab3\\folder-5121(2).bmp";
		FileInputStream fis = new FileInputStream(path);
		BufferedInputStream bis = new BufferedInputStream(fis);
		byte[] bit = bis.readAllBytes();
		int count = 0;
		int[] byteArr = new int[bit.length];
		for(int i = 0; i < bit.length; i++){
			byteArr[i] = bit[i] & 0xFF;
		}

		for(int i = 0; i < byteArr.length; i++) {
			String str = Integer.toBinaryString(byteArr[i]);
			StringBuffer newStr = new StringBuffer(str);
			if (str.length() < 8) {
				for (int j = 0; j < (8 - str.length()); j++) {
					newStr.insert(0, 0);
				}
			}
			str = newStr.toString();
			char[] arr = str.toCharArray();
			for(int l = 0; l < arr.length; l++) {
				bitArr.add(arr[l]);
			}
		}
	}
}
