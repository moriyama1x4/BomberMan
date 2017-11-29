<<<<<<< HEAD
﻿import java.util.Random;
=======
import java.util.Random;
>>>>>>> origin/master
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

<<<<<<< HEAD
	public boolean getItemDrop(){
		return this.itemDrop;
=======
	public Item bombed(){
		if(itemDrop) {
			return new Item(this.posX, this.posY);
		}else {
			return null;
		}
>>>>>>> origin/master
	}
}
