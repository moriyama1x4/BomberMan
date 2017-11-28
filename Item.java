import java.util.Random;

public class Item {
	private int posX, posY, type;

	Item(int posX, int posY){
		this.posX = posX;
		this.posY = posY;

		Random rnd = new Random();
		this.type = rnd.nextInt(2);
	}

	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}

	public int getType() {
		return this.type;
	}
}
