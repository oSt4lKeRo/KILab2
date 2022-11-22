package Lab3;
import java.io.IOException;
import java.util.ArrayList;

public class main {

	public static int byteToint(byte b[]) {
		int t1 = (b[3] & 0xff) << 24;
		int t2 = (b[2] & 0xff) << 16;
		int t3 = (b[1] & 0xff) << 8;
		int t4 = b[0] & 0xff;
		return t1 + t2 + t3 + t4;
	}

	public static int byteToShort(byte b[]) {
		int t3 = (b[1] & 0xff) << 8;
		int t4 = b[0] & 0xff;
		return t3 + t4;
	}

	static String containerFile = "C:\\material\\lab3\\img.bmp";

	static String messageFile = "C:\\material\\lab3\\folder-5121(2).bmp";

		public static void main(String[] args) throws IOException {
//		WriteBMP wb = new WriteBMP();
//		wb.writeBMP("C:\\material\\Без названия(coding).bmp");


		Message message = new Message();
		Message.readFile();
 		Container container = new Container(message);
			container.readBMP();
			container.addElemInBlue();
			container.write();


			Reader reader = new Reader();
			Reader.readFile();
		}
}
