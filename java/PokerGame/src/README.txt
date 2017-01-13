Texas Hold 'Em Poker Game by Scott Sandre - V1.0, DEC 2016


Computer Move Decision Algorithm:
	- ComputerMoveCalculator Class receives the following variables in its constructor:
		(int personality, String name, int amountToCall, int playerMoney, int playerChips[][], Card playerOwnCards[], Card faceUpCards[], int turnCounter, int minRaise, int amountBetSoFarThisTurn, boolean canRaise, int lastRaiseAmount, int pot) {

	- The ComputerMoveCalculator object then generates the PLAYER PERSONALITY VARIABLE VALUES for the 4 main variables that affect the computer player's 	 play style: howEasyToBluff_level, bettingRiskTolerance_level, willBluff_level, patience_level. Each of these variables are selected via 			generating a random integer within a certain range. That range is given by the PLAYER PERSONALITY TYPE, which is a combination of [TIGHT or 		LOOSE] paired with [PASSIVE or AGGRESSIVE].
	
	-	Next is checks, before even looking at their hand, if the last raise amount made during the round is large enough to make them fold via 			“lastRaiseAmount >= minRaise * howEasyToBluff_level * 3 / 2”
	-	Next, there is a switch statement that makes the ComputerMoveCalculator complete different tasks depending on the number of face cards on the 		table
	-	Case 0: determines SUM of hand value, adding to that sum various amounts depending if the player’s own two cards fit criteria for: high card, 		pair, consecutive, or same suit. Depending on that SUM value, the ComputerMoveCalculator will either have the player fold, call, or raise (with 	that raise value dependent on the value of SUM).
	-	- Case 3 (face up cards): calls “determineHowGoodHandIs”, which checks if all five cards the player can use match criteria of: Royal Flush, 		Straight Flush, Four of a Kind, Full House, Flush, Straight, 3 of a Kind, Two Pair, Pair, or High Card, and in that order. - It also checks if 		player is 1, or 2, cards away from a STRAIGHT, or a FLUSH.
	-   It also counts the number of OUTS that the computer’s hand has: outs are cards that MAKE their hand. It uses that number of outs and then 			determines the PERCENT CHANCE of the computer making that hand, via referring to a reference table that contains the percent chances of such 		happenings, depending on the number of outs and how many face up cards there are, and whether or not someone has gone all in on the FLOP. 			Depending 	on the SUM, or the NUM_OUTS, and the player’s RISK BETTING TOLERANCE, the computerMoveCalculator will raise, call, or fold.
		-	Case 4 face up cards: follows same steps as case 3.
	-	Case 5: determines how good hand is, and raises, calls, or folds depending on that value, their bluffing and risk tolerance levels.
	-	For each of the decisions involving SUM of points indicating the goodness of a hand, the computerMoveCalculator compares the hand’s PLAYER ODDS 	against the pot’s POT ODDS, and makes a decision based on the difference between those two values.

