
public class UnitTest {

	public static void main(String[] args) {

		System.out.println("キャラ単体テスト");
		Character character = new Character(1,2,3,4);

		System.out.println(character.getDirection());
		System.out.println();
		character.setDirection(3);
		System.out.println(character.getDirection());
		System.out.println(character.getPosX());
		System.out.println(character.getPosY());
		System.out.println();
		character.move(1);
		character.move(4);
		System.out.println(character.getDirection());
		System.out.println(character.getPosX());
		System.out.println(character.getPosY());
		System.out.println();
		System.out.println(character.getAlive());
		character.bombed();
		System.out.println(character.getAlive());

	}
}
