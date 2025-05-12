package w4;

import java.util.Random;
import java.util.Scanner;

class RollDice {
	Random rd = new Random();
	private int face;
	private int maxnum = 6;
	
	public RollDice() {
		face = 0;
	}

	public void roll() {
		
		face = rd.nextInt(maxnum) + 1;
	}

	public int getValue() {
		return face;
	}

	public void setValue(int face) {
		this.face = face;
	}
}

public class Dice {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		RollDice Dice1 = new RollDice();
		RollDice Dice2 = new RollDice();
		
		int count = 1;
		Dice1.roll();
		Dice2.roll();
		System.out.printf("주사위1=%d 주사위2=%d",Dice1.getValue(),Dice2.getValue());
		
		while (Dice1.getValue() != 1 || Dice2.getValue() != 1) {
			String st = sc.nextLine();
			Dice1.roll();
			Dice2.roll();
			if(st.equals("back door")) {
				Dice1.setValue(1);
				Dice2.setValue(1);
			}
			System.out.printf("주사위1=%d 주사위2=%d",Dice1.getValue(),Dice2.getValue());
			count ++;
		}
		System.out.println("\n(1,1)이 나오는데 걸린 횟수=" + count);
	}

}
