public class Character {
	private int color, id, direction, bombRemain, posX, posY;
	private int bombPower = 2;
	private int bombQuantity = 2;
	private int maxBombPower = 9;
	private int maxBombQuantity = 9;
	private boolean alive = true;


	Character(int color, int id, int posX, int posY, int direction){
		this.color = color;
		this.id = id;
		this.posX = posX;
		this.posY = posY;
		this.direction = direction;
		this.bombRemain = this.bombQuantity;
	}

	public int getId() {
		return this.id;
	}

	public int getColor() {
		return this.color;
	}

	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}

	public int getDirection() {
		return this.direction;
	}

	public int getBombPower() {
		return this.bombPower;
	}

	public int getBombQuantity() {
		return this.bombQuantity;
	}

	public int getMaxBombPower() {
		return this.maxBombPower;
	}

	public int getMaxBombQuantity() {
		return this.maxBombQuantity;
	}

	public int getBombRemain() {
		return this.bombRemain;
	}

	public boolean getAlive() {
		return this.alive;
	}

	public void setDirection(int ctrMove) {
		this.direction = ctrMove;
	}

	public void move(int ctrMove) {
		this.setDirection(ctrMove);

		switch(ctrMove){
			case 1:
				this.posY++;
				break;
			case 2:
				this.posY--;
				break;
			case 3:
				this.posX--;
				break;
			case 4:
				this.posX++;
		}
	}

	public Bomb putBomb() {
		return new Bomb(this.posX, this.posY, this.bombPower, this.id);
	}

	public void bombed() {
		this.alive = false;
	}
}
