import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
  private int limitTime = 50;
	private HashMap<Integer, Integer> bombMap = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> blockMap = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> itemMap = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> characterMap = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> blastMap = new HashMap<Integer, Integer>();
	private ArrayList<Bomb> bombList = new ArrayList<Bomb>();
	private ArrayList<Block> blockList = new ArrayList<Block>();
	private ArrayList<Item> itemList = new ArrayList<Item>();
	private ArrayList<Character> characterList = new ArrayList<Character>();
	private ArrayList<Blast> blastList = new ArrayList<Blast>();
	private ArrayList<Ai> aiList = new ArrayList<Ai>();
	private Stage stage = new Stage(9, 13);

	Controller(){
		for(int i = 1; i <= 2; i++) {
			aiList.add(new Ai(i));
		}

		for(int i = 1; i <= 2; i++) {
			int posX = 0;
			int posY = 0;
			int direction = 1;

			switch(i) {
			case 1:
				posX = 0;
				posY = 0;
				direction = 2;
				break;
			case 2:
				posX = stage.getWidth() - 1;
				posY = stage.getHeight() - 1;
				direction = 1;
				break;
			case 3:
				posX = stage.getWidth() - 1;
				posY = 0;
				direction = 3;
				break;
			case 4:
				posX = 0;
				posY = stage.getHeight() - 1;
				direction = 4;
			}
			characterList.add(new Character(i, posX, posY, direction));
		}
	}

	public int getLimitTime() {
		return this.limitTime;
	}

	public int cnvMapKey(int posX, int posY){
		return posX + (posY * stage.getWidth());
	}

  public void draw(){
    //間藤頼む
  }


  public void clearBlast(){
    for(int i = 0; i <= ((stage.getWidth() - 1) + (stage.getWidth() * (stage.getHeight() - 1))); i++){
      blastMap.put(i, null);
    }
    blastList.clear();
  }


  //キャラ行動ここから
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

	public boolean judgePutBomb(Character character, boolean ctrBomb){
		if(bombMap.get(cnvMapKey(character.getPosX(), character.getPosY())) == null && character.getBombQuantity() > 0 && ctrBomb){
			return true;
		}else{
			return false;
		}
	}

	public void putBomb(Character character, boolean ctrBomb){
		if(judgePutBomb(character, ctrBomb)){
			bombList.add(new Bomb(character.getPosX(), character.getPosY(), character.getBombPower(), character.getId()));
			bombMap.put(cnvMapKey(character.getPosX(), character.getPosY()), 1);
		}
	}

	public void action(Character character) {
  	int ctrMove = 0;
  	boolean ctrBomb = false;

  	for(int i = 0; i < aiList.size(); i++) {
  		Ai ai = aiList.get(i);
  		if(ai.getId() == character.getId()) {
  			ctrMove = ai.getCtrMove();
  			ctrBomb = ai.getCtrBomb();
  		}
  	}
  	move(character, ctrMove);
  	putBomb(character, ctrBomb);
	}
//キャラ行動ここまで



//ボム爆発ここから
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



	public void blastBomb(int index) {
    Bomb bomb = bombList.get(index);
		int posX = bomb.getPosX();
		int posY = bomb.getPosY();
		int characterId = bomb.getCharacterId();
		createBlast(posX, posY);//検討

		for(int i = 1; i <= 4; i++) {
			int posX2 = posX;
			int posY2 = posY;

			for (int j = 1; j <= bomb.getPower(); j++) { //cssにあわせて時計回り
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

				if(judgeCreateBlast(posX2, posY2) == 1) { //judgeCreateBlast１kainimatomeru ]
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
		bombMap.put(cnvMapKey(posX, posY), null);
		bombList.remove(index);

		for(int i = 0; i <= characterList.size(); i++) {
			Character character = characterList.get(i);
			if(character.getId() == characterId) {
				character.pickBomb();
			}
		}
	}

	public void timePassBomb(int index) {
		if(bombList.get(index).passTime()) {
			blastBomb(index);
		}
	}
	//ボム爆発ここまで


	//爆破ここから

	 public void blastBombList(Blast blast){
	    int posX = blast.getPosX();
	    int posY = blast.getPosY();
	    if(bombMap.get(cnvMapKey(posX, posY)) == 1){
	      for(int j = 0; j < bombList.size(); j++){
	        Bomb bomb = bombList.get(j);
	        if(bomb.getPosX() == posX && bomb.getPosY() == posY){
	          blastBomb(j);
	          return;
	        }
	      }
	    }
	  }


	public void blastBlock(int index) {
	    Block block = blockList.get(index);
	    int posX = block.getPosX();
	    int posY = block.getPosY();

	    if(block.getItemDrop()){
	      itemList.add(new Item(posX, posY));
	      itemMap.put(cnvMapKey(posX, posY), 1);
	    }
	    blockMap.put(cnvMapKey(posX, posY), null);
	    blockList.remove(index);
	}

  public void blastBlockList(Blast blast){
    int posX = blast.getPosX();
    int posY = blast.getPosY();
    if(blockMap.get(cnvMapKey(posX, posY)) == 1){
      for(int j = 0; j < blockList.size(); j++){
        Block block = blockList.get(j);
        if(block.getPosX() == posX && block.getPosY() == posY){
          blastBlock(j);
          return;
        }
      }
    }
  }

  public void blastItem(int index) {
    Item item = itemList.get(index);
    int posX = item.getPosX();
    int posY = item.getPosY();
    itemMap.put(cnvMapKey(posX, posY), null);
    itemList.remove(index);
  }

  public void blastItemList(Blast blast){
    int posX = blast.getPosX();
    int posY = blast.getPosY();
    if(itemMap.get(cnvMapKey(posX, posY)) == 1){
      for(int j = 0; j < itemList.size(); j++){
        Item item = itemList.get(j);
        if(item.getPosX() == posX && item.getPosY() == posY){
          blastItem(j);
          return;
        }
      }
    }
  }

	public void blastCharacter(Character character) {
    int posX = character.getPosX();
    int posY = character.getPosY();

    character.bombed();
    if(characterMap.get(cnvMapKey(posX, posY)) >= 2){
      characterMap.put(cnvMapKey(posX, posY), characterMap.get(cnvMapKey(posX, posY)) - 1);
    }else{
      characterMap.put(cnvMapKey(posX, posY), null);
    }
    //キャラクタのaliveをfalseにする
	}

  public void blastCharacterList(Blast blast){
    int posX = blast.getPosX();
    int posY = blast.getPosY();
    if(characterMap.get(cnvMapKey(posX, posY)) >= 1){
      for(int j = 0; j < characterList.size(); j++){
        Character character = characterList.get(j);
        if(character.getPosX() == posX && character.getPosY() == posY){
          blastCharacter(character);
        }
      }
    }
  }
//爆破ここまで

//アイテム取得ここから

  public void pickItem(int index){
    Item item = itemList.get(index);
    int posX = item.getPosX();
    int posY = item.getPosY();

    if(characterMap.get(cnvMapKey(posX, posY)) >= 1){
      for(int i = 0; i < characterList.size(); i++){
        Character character = characterList.get(i);
        if(character.getPosX() == posX && character.getPosY() == posY){
          character.enhance(item.getType());
        }
      }
      itemMap.put(cnvMapKey(posX, posY), null);
      itemList.remove(index);
    }
  }

//アイテム取得ここまで

  public boolean judgeGameContinue() {
  	int aliveCharacter = 0;
  	for(int i = 0; i < characterList.size(); i++) {
  		if(characterList.get(i).getAlive()) {
  			aliveCharacter++;
  		}
  	}

  	if(this.limitTime > 0 && aliveCharacter >= 2) {
  		return true;
  	}else {
  		return false;
  	}
  }


  public void timePass(){
    draw();
    clearBlast();

    for(int i = 0; i < characterList.size(); i++) {
    	action(characterList.get(i));
    }

    for(int i = 0; i < bombList.size(); i++){
      timePassBomb(i);
    }

   for(int i = 0; i < blastList.size(); i++){
      Blast blast = blastList.get(i);
      blastBombList(blast);
      blastBlockList(blast);
      blastItemList(blast);
      blastCharacterList(blast);
    }

    for(int i = 0; i < itemList.size(); i++){
      pickItem(i);
    }

    this.limitTime--;
  }









  public void setAiCtrMove(int ctrMove, int id) {
  	for(int i = 0; i < aiList.size(); i++) {
  		aiList.get(i).setCtrMove(ctrMove);
  	}
  }

  public void setAiCtrMove(boolean ctrBomb, int id) {
  	for(int i = 0; i < aiList.size(); i++) {
  		aiList.get(i).setCtrBomb(ctrBomb);
  	}
  }

}
