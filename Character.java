import java.util.ArrayList;

public class Character {
	private int color, id, direction,bombRemain;
	private int[] position;
	private int bombPower = 2;
	private int bombQuantity = 2;
	private int maxBombPower = 9;
	private int maxBombQuantity = 9;
	private boolean alive = true;
	private int[] action = {0,0};

	Character(int color, int id, int[] position, int direction){
		//マップ更新
		this.color = color;
		this.id = id;
		this.position = position;
		this.direction = direction;
		this.bombRemain = this.bombQuantity;
	}

	public int getId() {
		return this.id;
	}

	public int getColor() {
		return this.color;
	}

	public int[] getPosition() {
		return this.position;
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

	public int[] getAction() {
		return this.action;
	}

	public boolean judgeMove(){
		return true; //マップ未作成なので仮
	}

	public void move() {
		this.direction = action[0];

		if(this.judgeMove()){
			switch(this.action[0]){
			case 1:
				//マップ更新
				this.position[1]++;
				break;
			case 2:
				//マップ更新
				this.position[1]--;
				break;
			case 3:
				//マップ更新
				this.position[0]--;
				break;
			case 4:
				//マップ更新
				this.position[0]++;
			}
		}
	}

	public boolean judgePutBomb() {
		return true; //マップ未作成なので仮
	}

	public void putBomb(ArrayList<Bomb> bombList) {
		if(this.judgePutBomb()) {
			bombList.add(new Bomb(1,this.position,this.bombPower,this.id)); // ボムIDに渡し方不明
		}
	}
}
