import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * BMP файл для чтения и сохранения
 */
public class Projiect  extends JFrame {
	int map[][]; // Сохранить массив цветов пикселей
	MyPanel center; // Панель рисования
	File selectFile; // Файл прочитан
	int width; // Ширина изображения
	int height; // Высота изображения
	byte temp1[]; // Чтение первых 18 байтов информации файла BMP
	byte temp2[]; // Считать последние 28 байтов информации файла BMP
	JScrollPane scrollpane; // Панель прокрутки

	public Projiect() {

		this.setLayout(new BorderLayout()); // Лучше установить макет перед добавлением компонентов
		// Инициализируем панель рисования
		center = new MyPanel();
		center.setBackground(Color.WHITE);
		center.setPreferredSize(new Dimension(400, 200));
		scrollpane = new JScrollPane(center); // Инициализация панели прокрутки с центром
		scrollpane.setPreferredSize(new Dimension(500, 300));
		MyListener lis = new MyListener();

		// Инициализация строки меню
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("file");
		JMenuItem open = new JMenuItem("open");
		JMenuItem save = new JMenuItem("save");
		open.addActionListener(lis);
		save.addActionListener(lis);
		fileMenu.add(open);
		fileMenu.add(save);
		menuBar.add(fileMenu);
		//
		JPanel left = new JPanel();
		left.setBackground(Color.DARK_GRAY);
		left.setPreferredSize(new Dimension(50, 0));
		this.add(left, BorderLayout.WEST);
		this.setJMenuBar(menuBar);
		this.add(scrollpane, BorderLayout.CENTER); // Добавлена ​​панель прокрутки
		this.setTitle("bmp");
		this.setLocation(300, 150);
		this.setSize(800, 500);
		this.setDefaultCloseOperation(3);
		this.setVisible(true);

	}

	/**
	 * Читать файл bmp
	 */
	public void readBMP() {
		try {
			FileInputStream fis=new FileInputStream(selectFile);
			BufferedInputStream bis=new BufferedInputStream(fis);
			byte [] wb = new byte [4]; // Чтение массива байтов
			byte [] hb = new byte [4]; // Считать байтовый массив высоты
			temp1=new byte[18];
			bis.read (temp1); // bis.skip (18); // пропустить первые 18 байт
			bis.read (wb); // ширина считывания
			bis.read (hb); // Считать высоту
			width = byteToint(wb);
			height = byteToint(hb);
			System.out.println(width+"<>"+height);
			map=new int[height][width];
			int skip = 4-width * 3% 4; // Получить число для пропуска в строке (связано с механизмом системы Windows)
			temp2 = new byte[28];
			bis.read(temp2);//bis.skip(28);
			for(int i=0;i<height;i++)
			{
				for(int j=0;j<width;j++)
				{
					int blue=bis.read();
					int green=bis.read();
					int red=bis.read();
					Color c=new Color(red,green,blue);
					map[i][j]=c.getRGB();
				}
				if(skip!=4)
					bis.skip(skip);
			}
			bis.close();
			center.setPreferredSize(new Dimension(width,height));
			javax.swing.SwingUtilities.updateComponentTreeUI (center); // Здесь должна использоваться функция updateComponentTreeUI (center)
			// Невозможно использовать функцию repaint ()

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public void writeBMP() {
		try {
			FileInputStream fis = new FileInputStream("C:\\material\\circle-rgb-color-mix.bmp");
			BufferedInputStream bis = new BufferedInputStream(fis);

			bis.read(temp1);
			width = bis.read();
			bis.read(temp2);


			FileOutputStream fos=new FileOutputStream(selectFile);
			BufferedOutputStream bos=new BufferedOutputStream(fos);
			bos.write(temp1);//
			bos.write (intTobyte (width, 4)); // ширина записи
			bos.write (intTobyte (height, 4)); // Высота записи
			bos.write(temp2);
			int skip=0;
			if(width*3/4!=0)
			{
				skip=4-width*3%4;
			}
			for(int i=0;i<height;i++)
			{
				for(int j=0;j<width;j++)
				{
					Color c=new Color(map[i][j]);
					int blue=c.getBlue();
					int green=c.getGreen();
					int red=c.getRed();
					bos.write(red);
					bos.write(green);
					bos.write(blue);
				}
				if(skip!=0)
					bos.write(new byte[skip]);
			}
			bos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// байт до int
	public static int byteToint(byte b[]) {
		int t1 = (b[3] & 0xff) << 24;
		int t2 = (b[2] & 0xff) << 16;
		int t3 = (b[1] & 0xff) << 8;
		int t4 = b[0] & 0xff;
		//System.out.println(b[1]&0xff);// Выходными данными являются фасонные данные
		// В java design int и тип b, который меньше цифр int, таких как byte, char и т. Д., Должны расширить маленький тип до int и затем вычислить,
		// return (t1 << 24) + (t2 << 16) + (t3 << 8) + t4; // должны быть в скобках
		return t1 + t2 + t3 + t4;
	}

	// int в байт
	public static byte[] intTobyte(int a, int len) {
		byte[] t = new byte[len];
		t[0] = (byte) ((a & 0xff));
		if (len > 1)
			t[1] = (byte) ((a & 0xff00) >> 8);
		if (len > 2)
			t[2] = (byte) ((a & 0xff0000) >> 16);
		if (len > 3)
			t[3] = (byte) ((a & 0xff000000) >> 24);
		return t;
	}

	/**
	 * Панель рисования
	 *
	 * @author ZhangZunC
	 */
	class MyPanel extends JPanel {
		public void paint(Graphics g) {
			super.paint(g);
			if (map != null) {
				for (int i = 0; i < map.length; i++) {
					for (int j = 0; j < map[i].length; j++) {
						g.setColor(new Color(map[i][j]));
						g.drawLine(j, height - i, j, height - i);
					}
				}
			}
		}
	}

	class MyListener implements ActionListener {
		JFileChooser fileChosser;

		MyListener() {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("24-битное растровое изображение (* .bmp)", "bmp");
			fileChosser = new JFileChooser();
			fileChosser.setFileFilter(filter);
			fileChosser.setCurrentDirectory(new File("\\"));
		}

		public void actionPerformed(ActionEvent e) {

			File file = new File("C:\\material\\circle-rgb-color-mix.bmp");
			selectFile = file;
			readBMP();

			if (e.getActionCommand().equals("save")) // Выбор открыт
			{
				int choose = fileChosser.showSaveDialog(null);
				if (choose == JFileChooser.APPROVE_OPTION) {
					selectFile = fileChosser.getSelectedFile();
					writeBMP();
					System.out.println("save ");
				}
			}
		}

	}
static int check = 3;

	public static void main(String[] args) {
		System.out.println("""
				Выберите цвет:
				1) Красный
				2) Зеленый
				3) Синий
				""");
		new Projiect();
//		byte[] b=new byte[4];
//		b[0]=0;
//		b[1]=(byte) 0xaf;
//		b[2]=0;
//		b[3]=0;
		//	System.out.println(byteToint(b));
//		byte b1[]=intTobyte(byteToint(b),4);
//		System.out.println(b1[0]+"<>"+b1[1]+"<>"+b1[2]+"<>"+b1[3]);
	}
}
