/**
 * 
 */

package metaGamerClasses;
import java.util.Scanner;
import java.io.*;
import java.util.Random;
import java.lang.Math;

/**This class creates a tournament field that will be used to simulate a magic tournament.
 * 
 * @author Woody
 *
 */
public class TournaSim {

	/**
	 * The size variable will store how many participants are to be in the simulated tournament.
	 */
	protected static int size;
	/**
	 * How many aggressive decks will be in the tournament
	 */
	protected static int numAggro;
	/**
	 * How many midrange decks will be in the tournament
	 */
	protected static int numMidrange;
	/**
	 * How many control decks will be in the tournament
	 */
	protected static int numControl;
	
	/**
	 * This is a testing deck, used to ensure that it moves up the ranks well and 
	 * that I can add another deck successfully.
	 */
	protected static int numAwesometestdeck;
	
	
	/**
	 * Creates a field from user input of how many decks will be 
	 * @param totalsize Total number of decks in the tournament
	 * @param aggro number of aggro decks in the tournament
	 * @param midrange number of midrange decks in the tournament
	 * @param control number of control decks in the tournament
	 */
	public TournaSim(int totalsize, int aggro, int midrange, int control){
		size = totalsize;
		numAggro = aggro;
		numMidrange = midrange;
		numControl = control;
	}
	
	/**
	 * 
	 * @return returns an array of player's initialized to specification of user input
	 */
	private static Player[] initializePlayers() {
		
		/**
		 * This block of code is going to prompt the user for the number of control, midrange, awesometestdeck, and aggro 
		 * decks and save the numbers in variables that will be used to initialize the tournament. Eventually I hope to convert
		 * these variables to actual decks that are present in a given metagame so that people can create a metagame that 
		 * simulates their real life environment. 
		 */
		System.out.println("enter your number of control decks");
		Scanner input = new Scanner(System.in);
		numControl = input.nextInt();
		System.out.println("enter your number of midrange decks");
		input = new Scanner(System.in);
		numMidrange = input.nextInt();
		System.out.println("enter your number of awesometestdeck decks");
		input = new Scanner(System.in);
		numAwesometestdeck = input.nextInt();
		System.out.println("enter your number of aggro decks");
		input = new Scanner(System.in);
		numAggro = input.nextInt();
		
		size = numAggro + numMidrange + numControl + numAwesometestdeck + 1; //makes size of the tournament all of the initialized decks
		//plus the tracked player's deck, which for now I have called Woody because it is my name. 
		
		Player[] a = new Player[size];// an array of players equal to the size variable.
		Random randomGenerator = new Random();
		
		//the following block just gets information about the individual player we are to track and puts it in the system.
		System.out.println("enter the tracked player's deck");
		input = new Scanner(System.in);
		String playerDeck = input.next();
		Deck realPlayerDeck = new Deck(playerDeck);
		System.out.println("enter player name");
		input = new Scanner(System.in);
		String playerName = input.next();
		int randomNum = randomGenerator.nextInt(size);
		a[randomNum] = new Player(3, realPlayerDeck, playerName);
		
		/**each of the following for loops initializes the number of that type of deck in the tournament.
		 * We need to stick these in randomly because we don't jumble up the decks in between rounds right now, which should
		 * probably be added at some point. Since we are just insertion sorting the decks after each round to ensure the right point
		 * matchups, we need to randomize at the beginning to ensure the same types of decks don't always play each other.
		 */
		for (int i = 0; i < numControl; i++){
			int random = randomGenerator.nextInt(size);
			while (a[random] != null){
				random = (random + 1) % size;}
			a[random] = new Player(3, new Deck("control"), "PlayerwControl");}
		
		for (int i = 0; i < numMidrange; i++){
			int random = randomGenerator.nextInt(size);
			while (a[random] != null){
				random = (random + 1) % size;}
			a[random] = new Player(3, new Deck("midrange"), "PlayerwMidrange");}
		
		for (int i = 0; i < numAwesometestdeck; i++){
			int random = randomGenerator.nextInt(size);
			while (a[random] != null){
				random = (random + 1) % size;}
			a[random] = new Player(3, new Deck("awesometestdeck"), "PlayerwAwesometestdeck");}
		
		for (int i = 0; i < numAggro; i++){
			int random = randomGenerator.nextInt(size);
			while (a[random] != null){
				random = (random + 1) % size;}
			a[random] = new Player(3, new Deck("aggro"), "PlayerwAggro");}
		return a;
	}
	/**
	 * 
	 * @param d1 the first deck passed to be matched against d2
	 * @param d2 the second deck passed, to be matched against d1
	 * @param index the position of the first deck in the player array. We use this to give the winning deck 3 points.
	 * If the first deck wins, we give index +3 points because that is the index we passed. If the second deck wins, we 
	 * give index+1 the 3 points because that is the index of the second deck.
	 * @param pArray the array of players that we are using to simulate the tournament.
	 */
	public static void battleDecks(Deck d1, Deck d2, int index, Player[] pArray)
	{
		
		String matchup = d2.getDeckName(); // gets name of the second deck
		int winpercent = d1.getMatchup(matchup); //checks the first deck's matchup against the second deck
		Random randomGenerator = new Random(); // initialize a Random Object
		int randomNum = randomGenerator.nextInt(100); // makes a Random int up to 100
		if (winpercent > randomNum) pArray[index].setPoints(pArray[index].getPoints() + 3); // if within win percent, p1 gets 3 points
		else pArray[index + 1].setPoints(pArray[index +1].getPoints() + 3);// otherwise, p2 gets 3 points
	}
	
	
	/**
	 * Main is going to initialize the players array, state how many swiss rounds should be in the tournament, and 
	 * then simulate that tournament 100000 times and average those results to get an accurate simulation of the tournament.
	 * It will then store the occurences of top 8, top 16, etc. for the top player in an array and print them as win %.
	 * At some level, this code is not quite working correctly, as the win % predictions come out funky sometimes while looking
	 * somewhat accurate at other times.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*		System.out.println("enter a decktype");
		Scanner input = new Scanner(System.in);*/
		String deck1 = "control";
		Deck deck3 = new Deck(deck1);/*
		System.out.println(deck3); currently printing 3 win %             
		Player woody = new Player(2,deck3, "woody");
		System.out.println(woody);*/
		Player[] a = initializePlayers();
		int[] b = new int[8];
		
		double rounds = (Math.log(size)/Math.log(2));// number of rounds
		System.out.println("there should be no fewer than " + rounds + " rounds");
		for (int k = 0; k < 100000; k++){
		double i = 0.0;
		while (i < rounds){
			for (int j = 0; j < size; j++){
				if (j % 2 == 0){
					if (j == size - 1){
						a[j].setPoints(a[j].getPoints() + 3);
					}
					else{
						Deck tempDeck = a[j].getDeckType();
						Deck tempDeck2 = a[j+1].getDeckType();
						battleDecks(tempDeck,tempDeck2, j, a);
					}
				}
			}
			Insertion.sort(a);
			i = i + 1.0;
		}
		int q;
		for (q = 0; q < a.length; q++){
			if (a[q].getName().equals("Woody")) break;}
		if (q == 0) b[0] = b[0] + 1;
		else if (q == 1) b[1] = b[1] + 1;
		else if (q < 4) b[2] = b[2] + 1;
		else if (q < 8) b[3] = b[3] + 1;
		else if (q < 16) b[4] = b[4] + 1;
		else if (q < 32) b[5] = b[5] + 1;
		else if (q < 64) b[6] = b[6] + 1;
		else b[7] = b[7] + 1;
		}
		
		System.out.println("your chances of winning the tournament are: " + b[0]/1000.0 + " percent");
		System.out.println("your chances of placing second in the tournament are: " + b[1]/1000.0 +" percent");
		System.out.println("your chances of placing top four in the tournament are: " + b[2]/1000.0 +" percent");
		System.out.println("your chances of placing to eight in the tournament are: " + b[3]/1000 +" percent");
		System.out.println("your chances of placing top sixteen in the tournament are: " + b[4]/1000.0 +" percent");
		System.out.println("your chances of placing top 32 in the tournament are: " + b[5]/1000.0 +" percent");
		System.out.println("your chances of placing top 64 in the tournament are: " + b[6]/1000.0 +" percent");
		System.out.println("your chances of scrubbing out are: " + b[7]/1000.0 +" percent");
/*		int counter = 1;
		for (Player j:a){
		
			System.out.println(counter + ". "+ j);
			counter++;
		}*/
		
		
		
		
		
		
		
		
		
		
		
		
/*		a[0] = new Player(2,deck3, "sjjjjjj" );
		a[0].setPoints(2);
		a[1] = new Player(3, deck3, "blergh");
		a[1].setPoints(3);
		a[2] = new Player(5, deck3, "Idabest");
		a[2].setPoints(5);
		Insertion.sort(a);
		for (Player i:a){
			System.out.println(i.getName());}*/
		
	}



}
