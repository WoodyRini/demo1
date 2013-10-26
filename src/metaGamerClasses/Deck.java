package metaGamerClasses;

import java.util.Scanner;


/**
 * This is the deck choice of the player that will hold their win-percentages in matchups.
 * @author Woody
 *
 */
public class Deck {

	
	private int winvscontrol;
	private int winvsaggro;
	private int winvsmidrange;
	private int winvsawesometestdeck;
	private String whichdeck;
	
	
	public Deck(String decktype){
		Boolean set = false;
		whichdeck = decktype.toLowerCase();
		while (!set){
		if (whichdeck.equals("control")){
			winvsaggro = 40;
			winvsmidrange = 60;
			winvscontrol = 50;
			winvsawesometestdeck = 0;
			set = true;}
		else if (whichdeck.equals("midrange")){
			winvsaggro = 60;
			winvscontrol = 40;
			winvsmidrange = 50;
			winvsawesometestdeck = 0;
			set = true;
		}
		else if (whichdeck.equals("aggro")){
			winvscontrol = 60;
			winvsmidrange = 40;
			winvsaggro = 50;
			winvsawesometestdeck = 0;
			set = true;
		}
		else if (whichdeck.equals("awesometestdeck")){
			winvscontrol = 100;
			winvsmidrange = 100;
			winvsaggro = 100;
			winvsawesometestdeck = 50;
			set = true;
		}
		else {System.out.println("please enter either control, aggro, or midrange");
		Scanner input = new Scanner(System.in);
		whichdeck = input.next();}
		}
		
	}
	
	public String getDeckName(){
		return whichdeck;
	}
	
	public int getMatchup (String decktype){
		if (decktype.equals("control")) return winvscontrol;
		if (decktype.equals("midrange")) return winvsmidrange;
		if (decktype.equals("awesometestdeck")) return winvsawesometestdeck;
		else return winvsaggro;
	}
	
	public String toString(){
		String toReturn = "deck: " + whichdeck + "\n" + "win % vs. aggro: " + winvsaggro;
		toReturn = toReturn + "\n" + "win % vs. midrange: " + winvsmidrange + "\n" + "win % vs. control " + winvscontrol;
		return toReturn;
	}
}
