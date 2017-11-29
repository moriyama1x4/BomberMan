import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
	HashMap<Integer, Integer> blockMap = new HashMap<Integer, Integer>();
	HashMap<Integer, Integer> itemMap = new HashMap<Integer, Integer>();
	HashMap<Integer, Integer> characterMap = new HashMap<Integer, Integer>();
	HashMap<Integer, Integer> bombMap = new HashMap<Integer, Integer>();
<<<<<<< HEAD
	HashMap<Integer, Integer> clastMap = new HashMap<Integer, Integer>();
=======
	HashMap<Integer, Integer> blastMap = new HashMap<Integer, Integer>();
>>>>>>> origin/master
	ArrayList<Block> blockList = new ArrayList<Block>();
	ArrayList<Item> itemList = new ArrayList<Item>();
	ArrayList<Character> characterList = new ArrayList<Character>();
	ArrayList<Bomb> bombList = new ArrayList<Bomb>();
	ArrayList<Blast> blastList = new ArrayList<Blast>();
<<<<<<< HEAD
=======
	Stage stage = new Stage(9, 13);
	Ai Ai1 = new Ai(1);
    Ai Ai2 = new Ai(2);
    Character character1 = new Character(1, 1, 0, 0, 2);
    Character character2 = new Character(2, 2, 8, 12, 1);


	public int cnvMapKey(int posX, int posY){
		return posX + (posY * stage.getWidth());
	}

	public boolean judgeMove(Character character, int ctrMove){
		int posX = character.getPosX();
		int posY = character.getPosY();

		switch(ctrMove){
			case 1:
				posY++;
				break;
			case 2:
				posY--;
				break;
			case 3:
				posX++;
				break;
			case 4:
				posX--;
		}

		if(posX >= 0 && posX < stage.getWidth() && posY >= 0 && posY < stage.getHeight() && (posX % 2 == 0 || posY % 2 == 0) && blockMap.get(cnvMapKey(posX, posY)) == null && bombMap.get(cnvMapKey(posX, posY)) == null){
			return true;
		}else{
			return false;
		}
	}

	public void move(Character character, int ctrMove){
		if(judgeMove(character, ctrMove)){
			character.move(ctrMove);
		}else{
			character.setDirection(ctrMove);
		}
	}

	public boolean judgePutBomb(Character character){
		if(bombMap.get(cnvMapKey(character.getPosX(), character.getPosY())) == null){
			return true;
		}else{
			return false;
		}
	}

	public void putBomb(Character character, int ctrPutBomb){
		if(judgePutBomb(character)){
			bombList.add(new Bomb(character.getPosX(), character.getPosY(), character.getBombPower(), character.getId()));
			bombMap.put(cnvMapKey(character.getPosX(), character.getPosY()), 1);
		}
	}

	public int judgeCreateBlast(int posX, int posY) {
		if(posX >= 0 && posX < stage.getWidth() && posY >= 0 && posY < stage.getHeight() && (posX % 2 == 0 || posY % 2 == 0)) {
			return 1;
		}else if(blockMap.get(cnvMapKey(posX, posY)) == 1) {
			return 2;
		}else if(blastMap.get(cnvMapKey(posX, posY)) == 1) {
			return 3;
		}else {
			return 0;
		}
	}

	public void createBlast(int posX, int posY) {
		blastList.add(new Blast(posX, posY));
		blastMap.put(cnvMapKey(posX, posY), 1);
	}


	public void blastBomb(Bomb bomb) {
		int posX = bomb.getPosX();
		int posY = bomb.getPosY();
		createBlast(posX, posY);

		for(int i = 1; i <= 4; i++) {
			int posX2 = posX;
			int posY2 = posY;

			for (int j = 1; j <= bomb.getPower(); j++) {
				switch(i) {
					case 1:
						posY2++;
						break;
					case 2:
						posY2--;
						break;
					case 3:
						posX2--;
						break;
					case 4:
						posX2++;
				}

				if(judgeCreateBlast(posX2, posY2) == 1) {
					break;
				}else if(judgeCreateBlast(posX2, posY2) == 2) {
					createBlast(posX2, posY2);
					break;
				}else if(judgeCreateBlast(posX2, posY2) == 3) {
					continue;
				}else {
					createBlast(posX2, posY2);
				}
			}
		}

		for(int i = 0; i < bombList.size(); i++) {
			if(bombList.get(i).getPosX() == posX && bombList.get(i).getPosY() == posY) {
				bombList.remove(i);
			}
		}

		bombMap.put(cnvMapKey(posX, posY), null);
	}

	public void passTimeBomb(Bomb bomb) {
		if(bomb.passTime()) {
			blastBomb(bomb);
		}
	}

	public void blastBlock(Block block) {

	}
>>>>>>> origin/master


}
