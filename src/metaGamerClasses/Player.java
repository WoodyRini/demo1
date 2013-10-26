package metaGamerClasses;

/**
 * The player class is used to represent each individual player in a tournament
 * @author Woody
 *
 */
public class Player {
	
	protected int points;
	protected int playskill;
	protected Deck deckchoice;
	protected String name;
	
	/**
	 * 
	 * @param skill the amount of playskill the player has with the deck
	 * @param whichdeck which deck the player is playing with
	 */
	public Player(int skill, Deck whichdeck, String name){
		playskill = skill;
		deckchoice = whichdeck;
		this.name = name;
		points = 0;
	}
	
	public void setPoints(int number){
		points = number;
	}
	public Deck getDeckType(){
		return deckchoice;
	}
	public int getPoints(){
		return points;
	}
	public String getName(){
		return name;
	}
	/**
	 * 
	 * Returns a string that consists of the players points, playskill, and deck
	 */
	public String toString(){
		
		String toReturn = "";
		toReturn = name + "\n" +  "Points: " + points + " Playskill: " + playskill + "  Deck: " + deckchoice.getDeckName();
		return toReturn;
	}

}
