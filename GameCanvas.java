import java.awt.Canvas;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameCanvas extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int STAGE_WDITH = 15;
	private static final int STAGE_HEIGHT = 11;
	private static final int INIT_PAINT_SPEED = 30; // 初期化の描画速度 (ms)
	private final Container CP = this.getContentPane();

	private BufferedImage greenImage;
	private BufferedImage pillarImage;
	private BufferedImage blockImage;
	private BufferedImage fireImage;
	private BufferedImage fireUpImage;
	private BufferedImage bombImage;
	private BufferedImage bombUpImage;
	private BufferedImage whiteBackImage;
	private BufferedImage whiteRightImage;
	private BufferedImage whiteFrontImage;
	private BufferedImage whiteLeftImage;
	private BufferedImage orangeBackImage;
	private BufferedImage orangeRightImage;
	private BufferedImage orangeFrontImage;
	private BufferedImage orangeLeftImage;

	private JPanel greenPanel = new JPanel();
	private JPanel pillarPanel = new JPanel();
	private JPanel blockPanel = new JPanel();
	private JPanel itemPanel = new JPanel();
	private JPanel chara01Panel = new JPanel();
	private JPanel chara02Panel = new JPanel();
	private JPanel bombPanel = new JPanel();
	private JPanel firePanel = new JPanel();

	private imageCanvas greenMap[][] = new imageCanvas[STAGE_WDITH][STAGE_HEIGHT];
	private imageCanvas pillarMap[][] = new imageCanvas[STAGE_WDITH][STAGE_HEIGHT];
	private imageCanvas blockMap[][] = new imageCanvas[STAGE_WDITH][STAGE_HEIGHT];
	private imageCanvas itemMap[][] = new imageCanvas[STAGE_WDITH][STAGE_HEIGHT];
	private imageCanvas chara01Map[][] = new imageCanvas[STAGE_WDITH][STAGE_HEIGHT];
	private imageCanvas chara02Map[][] = new imageCanvas[STAGE_WDITH][STAGE_HEIGHT];
	private imageCanvas bombMap[][] = new imageCanvas[STAGE_WDITH][STAGE_HEIGHT];
	private imageCanvas fireMap[][] = new imageCanvas[STAGE_WDITH][STAGE_HEIGHT];

	public static void main(String[] args) {
		GameCanvas gameCanvas = new GameCanvas();
		gameCanvas.simulateGame();
	}

	public void simulateGame() {
		setBlock(3, 1);
		setBlock(1, 3);
		setBlock(3, 5);
		setBlock(7, 5);
		setBlock(5, 7);
		gameSleep(1000);
		setChara01(3, 1, 1);
		gameSleep(1000);
		removeChara01All();
		setChara01(2, 2, 1);
		gameSleep(1000);
		setBomb(2, 1);
		gameSleep(1000);
		setChara01(4, 1, 1);
		gameSleep(1000);
		removeChara01All();
		setChara01(3, 1, 2);
		gameSleep(1000);
		removeBomb(2, 1);
		setFire(1, 1);
		setFire(2, 1);
		setFire(3, 1);
		removeBlock(3, 1);
		gameSleep(1000);
		gameSleep(1000);
		removeFireAll();
		setItem("fire-up", 3, 1);
		gameSleep(1000);
		setChara01(1, 1, 1);
		gameSleep(1000);
		setChara01(2, 2, 1);
		gameSleep(1000);
		setChara01(2, 3, 1);
		removeItem(3, 1);
		gameSleep(1000);
		setChara01(2, 4, 1);
		gameSleep(1000);
		setChara01(2, 5, 1);
		gameSleep(1000);
		setChara01(3, 5, 2);
		gameSleep(1000);
		setChara01(3, 5, 3);
		gameSleep(1000);
		setChara01(3, 5, 4);
		gameSleep(1000);
		setChara01(3, 5, 5);
		gameSleep(1000);
		setBomb(5, 5);
		gameSleep(1000);
		setChara01(1, 5, 4);
		gameSleep(1000);
		setChara01(1, 5, 3);
		gameSleep(1000);
		setChara01(2, 6, 3);
		gameSleep(1000);
		setFire(5, 3);
		setFire(5, 4);
		setFire(5, 5);
		setFire(3, 5);
		setFire(4, 5);
		setFire(5, 6);
		setFire(5, 7);
		setFire(6, 5);
		setFire(7, 5);
		gameSleep(2000);
		removeFireAll();
		removeBomb(5, 5);
		removeBlock(3, 5);
		removeBlock(7, 5);
		removeBlock(5, 7);
		}

	public GameCanvas() {
		try {
			greenImage = ImageIO.read(new File("image/Green.png"));
			pillarImage = ImageIO.read(new File("image/Pillar.png"));
			blockImage = ImageIO.read(new File("image/Block.png"));
			fireImage = ImageIO.read(new File("image/Fire.png"));
			fireUpImage = ImageIO.read(new File("image/FireUp.png"));
			bombImage = ImageIO.read(new File("image/Bomb.png"));
			bombUpImage = ImageIO.read(new File("image/BombUp.png"));
			whiteBackImage = ImageIO.read(new File("image/WhiteBomberManBack.png"));		// 白ボン↑向き
			whiteRightImage = ImageIO.read(new File("image/WhiteBomberManRight.png"));		// 白ボン→向き
			whiteFrontImage = ImageIO.read(new File("image/WhiteBomberManFront.png"));		// 白ボン↓向き
			whiteLeftImage = ImageIO.read(new File("image/WhiteBomberManLeft.png"));		// 白ボン←向き
			orangeBackImage = ImageIO.read(new File("image/OrangeBomberManBack.png"));		// オレンジボン↑向き
			orangeRightImage = ImageIO.read(new File("image/OrangeBomberManRight.png"));	// オレンジボン→向き
			orangeFrontImage = ImageIO.read(new File("image/OrangeBomberManFront.png"));	// オレンジボン↓向き
			orangeLeftImage = ImageIO.read(new File("image/OrangeBomberManLeft.png"));		// オレンジボン←向き
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		CP.add(greenPanel);
		CP.add(pillarPanel);
		CP.add(blockPanel);
		CP.add(itemPanel);
		CP.add(chara01Panel);
		CP.add(chara02Panel);
		CP.add(bombPanel);
		CP.add(firePanel);
		this.setBounds(0 ,0, 800, 600);
		this.setVisible(true);
		this.init();
	}

	private void setGreen(int x, int y) {
		greenMap[x][y] = new imageCanvas(greenImage, x * 30, y * 30);
		greenPanel.add(greenMap[x][y]);
	}

	public void setPillar(int x, int y) {
		pillarMap[x][y] = new imageCanvas(pillarImage, x * 30, y * 30);
		pillarPanel.add(pillarMap[x][y]);
	}

	public void setBlock(int x, int y) {
		blockMap[x][y] = new imageCanvas(blockImage, x * 30, y * 30);
		blockPanel.add(blockMap[x][y]);
	}

	public void setItem(String type, int x, int y) {
		String itemType = type;
		if (itemType == "bomb-up") {
			itemMap[x][y] = new imageCanvas(bombUpImage, x * 30, y * 30);
		} else if (itemType == "fire-up") {
			itemMap[x][y] = new imageCanvas(fireUpImage, x * 30, y * 30);
		}
		itemPanel.add(itemMap[x][y]);
	}

	public void setChara01(int direction, int x, int y) {
		int charaDirection = direction; // 1: ↑, 2: →, 3: ↓, 4: ←
		switch (charaDirection) {
			case 1:
			chara01Map[x][y] = new imageCanvas(whiteBackImage, x * 30, y * 30);
			break;
			case 2:
			chara01Map[x][y] = new imageCanvas(whiteRightImage, x * 30, y * 30);
			break;
			case 3:
			chara01Map[x][y] = new imageCanvas(whiteFrontImage, x * 30, y * 30);
			break;
			case 4:
			chara01Map[x][y] = new imageCanvas(whiteLeftImage, x * 30, y * 30);
		}
		chara01Panel.removeAll();
		chara01Panel.add(chara01Map[x][y]);
	}

	public void setChara02(int direction, int x, int y) {
		int charaDirection = direction; // 1: ↑, 2: →, 3: ↓, 4: ←
		switch (charaDirection) {
			case 1:
			chara02Map[x][y] = new imageCanvas(orangeBackImage, x * 30, y * 30);
			break;
			case 2:
			chara02Map[x][y] = new imageCanvas(orangeRightImage, x * 30, y * 30);
			break;
			case 3:
			chara02Map[x][y] = new imageCanvas(orangeFrontImage, x * 30, y * 30);
			break;
			case 4:
			chara02Map[x][y] = new imageCanvas(orangeLeftImage, x * 30, y * 30);
		}
		chara02Panel.removeAll();
		chara02Panel.add(chara02Map[x][y]);
	}


	public void setBomb(int x, int y) {
		bombMap[x][y] = new imageCanvas(bombImage, x * 30, y * 30);
		bombPanel.add(bombMap[x][y]);
	}

	public void setFire(int x, int y) {
		fireMap[x][y] = new imageCanvas(fireImage, x * 30, y * 30);
		firePanel.add(fireMap[x][y]);
	}

	public void removeBlock(int x, int y) {
		blockPanel.remove(blockMap[x][y]);

	}

	public void removeItem(int x, int y) {
		itemPanel.remove(itemMap[x][y]);

	}

	public void removeChara01(int x, int y) {
		chara01Panel.remove(chara01Map[x][y]);
	}

	public void removeChara02(int x, int y) {
		chara02Panel.remove(chara02Map[x][y]);
	}

	public void removeBomb(int x, int y) {
		bombPanel.remove(bombMap[x][y]);
	}

	public void removeFire(int x, int y) {
		firePanel.remove(fireMap[x][y]);
	}

	public void removeFireAll() {
		firePanel.removeAll();
	}

	public void removeChara01All() {
		chara01Panel.removeAll();
	}

	private void init() {
		initGreen();
		initPillar();
	}

	private void initGreen() {
		for (int i = 0; i < STAGE_HEIGHT; i++) {
			for (int j = 0; j < STAGE_WDITH; j++) {
				this.setGreen(j, i);
				gameSleep(INIT_PAINT_SPEED);
			}
		}
	}

	private void initPillar() {
		for (int i = 0; i < STAGE_HEIGHT; i++) {
			if (i == 0 || i == STAGE_HEIGHT - 1) {
				for (int j = 0; j < STAGE_WDITH; j++) {
					this.setPillar(j, i);
					gameSleep(INIT_PAINT_SPEED);
				}
			}
			if (i % 2 == 1) {
				for (int j = 0; j < STAGE_WDITH; j++) {
					if (0 < j && j < STAGE_WDITH - 1) {
						continue;
					}
					this.setPillar(j, i);
					gameSleep(INIT_PAINT_SPEED);
				}
			} else {
				for (int j = 0; j < STAGE_WDITH; j++) {
					if (j % 2 == 1) {
						continue;
					}
					this.setPillar(j, i);
					gameSleep(INIT_PAINT_SPEED);
				}
			}
		}
	}

	public void gameSleep(int time) {
		try {
		        TimeUnit.MILLISECONDS.sleep(time); // ミリ秒単位
		} catch (InterruptedException e) {
		        e.printStackTrace();
		}
	}

}

class imageCanvas extends Canvas
{
	private static final long serialVersionUID = 1L;
	Image presenImage;
	int w, h, x, y;

	public imageCanvas(Image img, int setx, int sety) {
		presenImage = img;
		w = presenImage.getWidth(this);
		h = presenImage.getHeight(this);
		x = setx;
		y = sety;
		this.setBounds(x, y, 30, 30);
	}
	public void paint(Graphics g) {
		g.drawImage(presenImage, 0, 0 ,this);
	}
}

