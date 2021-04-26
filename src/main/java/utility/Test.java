package utility;

public class Test {
		
	public static void main(String[] args) {
			Dice dice1 = new Dice(1);
			Dice dice2 = new Dice(2);
			Dice dice3 = new Dice(3);
			Dice dice4 = new Dice(4);
			Dice dice5 = new Dice(5);
			dice1.roll();
			dice2.roll();
			dice3.roll();
			dice4.roll();
			dice5.roll();
			
			Util.calculateScore(1, 1, 1, 1, 1, 1);
			Util.calculateScore(2, 1, 4, 1, 1, 2);
			Util.calculateScore(3, 1, 3, 2, 1, 3);
			Util.calculateScore(4, 1, 1, 6, 6, 5);
			Util.calculateScore(5, 1, 4, 3, 7, 6);
			Util.calculateScore(6, 3, 2, 6, 5, 5);
			Util.calculateScore(7, 1, 3, 1, 1, 2);
			Util.calculateScore(8, 2, 2, 1, 1, 1);
			Util.calculateScore(9, 3, 3, 3, 5, 6);
			Util.calculateScore(10, 1, 6, 6, 2, 2);
			Util.calculateScore(11, 5, 2, 2, 1, 1);
			Util.calculateScore(12, 6, 4, 6, 4, 6);
			Util.calculateScore(13, 1, 6, 5, 5, 1);
			Util.calculateScore(14, 6, 4, 4, 4, 1);
			Util.calculateScore(15, 1, 4, 2, 1, 3);
		}
}
