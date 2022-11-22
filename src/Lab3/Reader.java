package Lab3;


import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Reader {

	static ArrayList<Character> arr = new ArrayList<>();


	public static void readFile() throws IOException {
		String str ="";
		int[][] map = ColorMap.getColorMap("C:\\material\\lab3\\Без названия(coding).bmp");
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++){
				Color c = new Color(map[i][j]);
				int blue = c.getBlue();
				str = Integer.toBinaryString(blue);
				StringBuffer newStr = new StringBuffer(str);
				if (str.length() < 8) {
					for (int l = 0; l < (8 - str.length()); l++) {
						newStr.insert(0, 0);
					}
				}
				str = newStr.toString();
				char[] charArr = str.toCharArray();
				arr.add(charArr[7]);
			}
		}
		ArrayList<Integer> intArr = new ArrayList<>();

		str = "";
		for(int i = 0; i < arr.size(); i++){
			str += arr.get(i);
			if(str.length() == 8){
				intArr.add(Integer.parseInt(str, 2));
				str = "";
			}
		}
		byte b;
		String path = "C:\\material\\lab3\\folder-5121(encoding).bmp";
		FileOutputStream fos = new FileOutputStream(path);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		int count = 0;
		for(int i : intArr){
			bos.write(i);
		}
	}
}
