package w5;

class Sports{
	String getName() { return "아직 결정되지 않음"; }
	int getPlayer() { return 0; }
}
class Soccer extends Sports{
	String name;
	int player;
	public Soccer(String name, int player) {
		this.name = name;
		this.player = player;
	}
	String getName() { return name; }
	int getPlayer() { return player; }
}
public class SportsTest {

	public static void main(String[] args) {
		Sports soccer = new Soccer("축구",11);
		System.out.printf("경기 이름: %s, 경기자수: %d", soccer.getName(), soccer.getPlayer());
	}
}
