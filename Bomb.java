
public class Bomb {
	private int posX, posY, characterId, power;
	private int liveTime = 7;

	Bomb(int characterPosX, int characterPosY, int characterBombPower, int characterId){
		this.posX = characterPosX;
		this.posY = characterPosY;
		this.power = characterBombPower;
		this.characterId = characterId;
	}

	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}

	public int getPower() {
		return this.power;
	}

	public int getCharacterId() {
		return this.characterId;
	}

	public int getLiveTime() {
		return this.liveTime;
	}

	public int[] explode() {
		return new int[] {this.posX, this.posY, this.power, this.characterId};
	}

	public boolean passTime() {
		this.liveTime--;
		if(this.liveTime == 0) {
			return true;
		}else {
			return false;
		}
	}
}
