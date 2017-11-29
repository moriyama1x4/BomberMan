
public class Ai {
<<<<<<< HEAD
  private int id;
  private int ctrMove = 0;
  private boolean ctrBomb = false;

  Ai(int id){
    this.id = id;
  }

  public int getId() {
  	return this.id;
  }

  public int getCtrMove() {
  	return this.ctrMove;
  }

  public boolean getCtrBomb() {
  	return this.ctrBomb;
  }

  public void setCtrMove(int ctrMove){
  	this.ctrMove = ctrMove;
  }

  public void setCtrBomb(boolean ctrBomb){
  	this.ctrBomb = ctrBomb;
  }

  public void resetCtr() {
  	this.ctrMove = 0;
  	this.ctrBomb = false;
  }

=======
  int id;
  
  Ai(int id){
    this.id = id;
  }
>>>>>>> origin/master
}
