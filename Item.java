import java.util.Random;

public class Item {
	private int posX, posY, type;

	Item(int posX, int posY){
		this.posX = posX;
		this.posY = posY;

		Random rnd = new Random();
<<<<<<< HEAD
        this.type = rnd.nextInt(2);
=======
		this.type = rnd.nextInt(2);
>>>>>>> origin/master
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
