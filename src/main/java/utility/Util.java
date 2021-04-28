package utility;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Util {
    public static Map<Integer, String> round_map = new HashMap<>();
    static {

        round_map.put(1, "Ones");
        round_map.put(2, "Twos");
        round_map.put(3, "Threes");
        round_map.put(4, "Fours");
        round_map.put(5, "Fives");
        round_map.put(6, "Sixes");
        round_map.put(7, "Bonus");
        round_map.put(8, "Three of a Kind");
        round_map.put(9, "Four of a Kind");
        round_map.put(10, "Full House");
        round_map.put(11, "Small Straight");
        round_map.put(12, "Large Straight");
        round_map.put(13, "Yatzy");
        round_map.put(14, "Chance");
        round_map.put(15, "Total Score");
    }

    public static int diceRoll(){
        return new Random().nextInt(6) + 1;
    }
    
    public static int calculateScore(int round, int dice1, int dice2, int dice3, int dice4, int dice5) {
		int[] dice = {dice1, dice2, dice3, dice4, dice5};
		int score = 0;
		
		/* calculation for ONES */
		if (round == 1) {
			for (int i = 0; i < dice.length; i++) {
				if (dice[i] == 1) score += 1;
			}
		}
		
		/* calculation for TWOS */
		if (round == 2) {
			for (int i = 0; i < dice.length; i++) {
				if (dice[i] == 2) score += 2;
			}
		}
		
		/* calculation for THREES */
		if (round == 3) {
			for (int i = 0; i < dice.length; i++) {
				if (dice[i] == 3) score += 3;
			}
		}
		
		/* calculation for FOURS */
		if (round == 4) {
			for (int i = 0; i < dice.length; i++) {
				if (dice[i] == 4) score += 4;
			}
		}
		
		/* calculation for FIVES */
		if (round == 5) {
			for (int i = 0; i < dice.length; i++) {
				if (dice[i] == 5) score += 5;
			}
		}
	
		/* calculation for SIXES */
		if (round == 6) {
			for (int i = 0; i < dice.length; i++) {
				if (dice[i] == 6) score += 6;
			}
		}
	
		/* calculation for THREE OF A KIND */
		if (round == 9) {
			sort(dice);
			if (dice[0] == dice[1] && dice[1] == dice[2]) {
				score += dice[0] + dice[1] + dice[2];
			}
			else if (dice[1] == dice[2] && dice[2] == dice[3]) 
				score += dice[1] + dice[2] + dice[3];
			
			else if (dice[2] == dice[3] && dice[3] == dice[4])
				score += dice[2] + dice[3] + dice[4];
		}
		
		/* calculation for FOUR OF A KIND */
		if (round == 10) {
			sort(dice);
			if ((dice[0] == dice[1] && dice[1] == dice[2] && dice[2] == dice[3])) {
				score += dice[0] + dice[1] + dice[2] + dice[3];
				}
		
			else if (dice[1] == dice[2] && dice[2] == dice[3] && dice[3] == dice[4]) {
				score += dice[1] + dice[2] + dice[3] + dice[4];
			}
		}
	
		/* calculation for FULL HOUSE */
		if (round == 11) {
			sort(dice);
			if ((dice[0] == dice[1] && dice[1] == dice[2] && dice[3] == dice[4]) || (dice[0] == dice[1] && dice[2] == dice[3] && dice[3] == dice[4])) score = 25;
		}
		
		/* calculation for SMALL STRAIGHT */
		if (round == 12) {
			sort(dice);
			if ((dice[0] == 1 && dice[1] == 2 && dice[2] == 3 && dice[3] == 4) || (dice[0] == 1 && dice[1] == 1 && dice[2] == 2 && dice[3] == 3 && dice[4] == 4) || (dice[0] == 1 && dice[1] == 2 && dice[2] == 2 && dice[3] == 3 && dice[4] == 4) || (dice[0] == 1 && dice[1] == 2 && dice[2] == 3 && dice[3] == 3 && dice[4] == 4) || (dice[0] == 2 && dice[1] == 3 && dice[2] == 4 && dice[3] == 5) || (dice[0] == 2 && dice[1] == 2 && dice[2] == 3 && dice[3] == 4 && dice[4] == 5) || (dice[0] == 2 && dice[1] == 3 && dice[2] == 3 && dice[3] == 4 && dice[4] == 5) || (dice[0] == 2 && dice[1] == 3 && dice[2] == 4 && dice[3] == 4 && dice[4] == 5) || (dice[0] == 3 && dice[1] == 4 && dice[2] == 5 && dice [3] == 6) || (dice[0] == 3 && dice[1] == 3 && dice[2] == 4 && dice [3] == 5 && dice[4] == 6) || (dice[0] == 3 && dice[1] == 4 && dice[2] == 4 && dice [3] == 5 && dice[4] == 6) || (dice[0] == 3 && dice[1] == 4 && dice[2] == 5 && dice [3] == 5 && dice[4] == 6) ) score = 30;
		}
		
		/* calculation for LARGE STRAIGHT */
		if (round == 13) {
			sort(dice);
			
			if ((dice[0] == 1 && dice[1] == 2 && dice[2] == 3 && dice[3] == 4 && dice[4] == 5) || (dice[0] == 2 && dice[1] == 3 && dice[2] == 4 && dice[3] == 5 && dice[4] == 6)) score = 40;
		}
		
		/* calculation for YAHTZEE */
		if (round == 14) {
			if (dice[0] == dice[1] && dice[1] == dice[2] && dice[2] == dice[3] && dice[3] == dice[4]) score = 50;
		}
		
		/* calculation for CHANCE */
		if (round == 15) {
			for (int i = 0; i < dice.length; i++) {
				score += dice[i];
				}	
		}
			return score;
		}
	
    private static void sort(int[] dice) {
        for (int lh = 0; lh < dice.length; lh++) {
            int rh = findSmallest(dice, lh, dice.length);

            swapElements(dice, lh, rh);
        }

    }
    private static int findSmallest(int[] dice, int p1, int p2) {
        int smallestIndex = p1;
        for (int i = p1 + 1; i < p2; i++) {
            if (dice[i] < dice[smallestIndex]) smallestIndex = i;
        }
        return smallestIndex;
    }

    private static void swapElements(int[] dice, int p1, int p2) {
        int temp = dice[p2];
        dice[p2] = dice[p1];
        dice[p1] = temp;

    }
}
