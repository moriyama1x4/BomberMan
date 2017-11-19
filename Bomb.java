
public class Bomb {
	private int id, characterId, power;
	private int[] position;
	private int liveTime = 20;

	Bomb(int bombId, int[] characterPosition, int characterBombPower, int characterId){
		//マップ更新
		//ボムID++
		this.id = bombId;
		this.position = characterPosition;
		this.power = characterBombPower;
		this.characterId = characterId;
	}
}
