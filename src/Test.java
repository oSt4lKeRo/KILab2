import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		int x = 11;
		int y = 8;

		System.out.println(Integer.toBinaryString(x));
		System.out.println(Integer.toBinaryString(y));

		int z = x&y;
		System.out.println();
		System.out.println(Integer.toBinaryString(z) + " " + z);
	}
}
