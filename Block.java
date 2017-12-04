import java.util.Random;
public class Block {
	private int posX, posY;
	private boolean itemDrop = false;;

	Block(int posX, int posY){
		this.posX = posX;
		this.posY = posY;

		Random rnd = new Random();
        int judgeItem = rnd.nextInt(2);
        if(judgeItem == 1) {
        	itemDrop = true;
        }
	}

	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}

	public boolean getItemDrop(){
		return this.itemDrop;
	}

	public boolean bombed(){
		if(itemDrop) {
			return true;
		}else {
			return false;
		}
	}
}
