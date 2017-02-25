import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class InfectionDeck 
{
	private static ArrayList<Card> infectionDeck = new ArrayList<Card>();
	private static ArrayList<Card> infectionDiscardDeck = new ArrayList<Card>();
	private ArrayList<Card> shuffleTemp = new ArrayList<Card>();

	static Random randy = new Random();
	
	private Color blue = Color.blue;
	private Color red = Color.red;
	private Color yellow = Color.yellow;
	private Color black = Color.black;

	
	public InfectionDeck()
	{
		populateDecks();
	}
	
	public static void discardCard(Card card)
	{
		//infectionDiscardDeck.add(card);
		Card.getInfectionDiscarding().add(card);
		card.setDiscard(true);
	}
	
	
	
	/*
	 * remove the top card of the infection deck and place it 
	 * in the infection discard pile.  The game will keep track of the 
	 * infection rate.  So, no loop here.
	 * 
	 * last    =       top
	 * first   =       bottom
	 */
	public static Card infectTop(ArrayList<Card> to, ArrayList<Card> from)
	{
		Card top = from.remove(from.size()-1);
		to.add(top);
		return top;
	}
	
	/*
	 *  remove the card from the bottom of the infection deck
	 *  and place it in the infection discard pile
	 *  
	 * last    =       top
	 * first   =       bottom
	 */
	public static Card infectBottom(ArrayList<Card> to, ArrayList<Card> from)
	{
		Card bottom = from.remove(0);
		to.add(bottom);
		return bottom;
	}
	
	/* 
	 * When the game starts, all cards will start out in the 
	 * infection discard pile, get shuffled, then become the infection
	 * deck. 
	 * 
	 * After that the discard pile gets shuffled then placed on 
	 * top of the discard pile. <<infection deck?
	 */
	public static void shuffle(ArrayList<Card> toShuffle, ArrayList<Card> remadeDeck)
	{
		int i=0;
		while(i<toShuffle.size())
		{
			int takeOut = randy.nextInt(toShuffle.size());
			remadeDeck.add(toShuffle.get(takeOut));
			toShuffle.remove(takeOut);	
		}
	}
	
	public static ArrayList<Card> getInfectionDeck() {return infectionDeck;}
	public static ArrayList<Card> getInfectionDiscardDeck() {return infectionDiscardDeck;}
	

	
	public String toString(ArrayList<Card> cards)
	{
		int counter = 0;
		String toRet = "";
		for(Card card : cards)
		{
			counter++;
			if(counter % 6 == 0)
				toRet += card.getID() + "\n";
			else
				toRet += card.getID() + ", ";
		}
		return toRet;
	}

	// put in the other 47 cards into the infectionDiscardDeck, then shuffle 
        // from the infectionDiscardDeck to the infectionDeck
	
	public void populateDecks()
	{
		infectionDiscardDeck.add(new Card("Infection", Color.BLUE,"San Francisco")); //0, San Francisco
		infectionDiscardDeck.add(new Card("Infection", Color.BLUE,"Chicago")); //1, Chicago
		infectionDiscardDeck.add(new Card("Infection", Color.BLUE, "Atlanta")); //Atlanta
		infectionDiscardDeck.add(new Card("Infection", Color.BLUE,"Toronto")); // 3, Toronto
		//infectionDiscardDeck.add(new Card("City", Color.BLUE,"Montreal"));
		infectionDiscardDeck.add(new Card("Infection", Color.BLUE, "Washington")); //Washington
		infectionDiscardDeck.add(new Card("Infection", Color.BLUE,"New York")); //New York
		infectionDiscardDeck.add(new Card("Infection", Color.BLUE, "London")); //London
		infectionDiscardDeck.add(new Card("Infection", Color.BLUE, "Madrid")); //Madrid
		infectionDiscardDeck.add(new Card("Infection", Color.BLUE, "Paris")); //Paris
		infectionDiscardDeck.add(new Card("Infection", Color.BLUE, "Essen")); // Essen
		infectionDiscardDeck.add(new Card("Infection", Color.BLUE, "Milan")); //Milan
		infectionDiscardDeck.add(new Card("Infection", Color.BLUE, "St. Petersburg")); //St. Petersburg
		
		infectionDiscardDeck.add(new Card("Infection", Color.YELLOW, "Los Angeles")); //13, Los Angeles
		infectionDiscardDeck.add(new Card("Infection", Color.YELLOW, "Mexico City")); //14, Mexico City
		infectionDiscardDeck.add(new Card("Infection", Color.YELLOW, "Miami")); //Miami
		infectionDiscardDeck.add(new Card("Infection", Color.YELLOW, "Bogota")); //Bogota
		infectionDiscardDeck.add(new Card("Infection", Color.YELLOW, "Lima")); //Lima
		infectionDiscardDeck.add(new Card("Infection", Color.YELLOW,"Santiago")); //Santiago
		infectionDiscardDeck.add(new Card("Infection", Color.YELLOW,"Buenos Aires")); //Buenos Aires
		infectionDiscardDeck.add(new Card("Infection", Color.YELLOW,"Sao Paulo")); //Sao Paulo
		infectionDiscardDeck.add(new Card("Infection", Color.YELLOW,"Lagos")); //Lagos
		infectionDiscardDeck.add(new Card("Infection", Color.YELLOW, "Kinshasa")); //Kinshasa
		infectionDiscardDeck.add(new Card("Infection", Color.YELLOW, "Johannesburg")); //Johannesburg
		infectionDiscardDeck.add(new Card("Infection", Color.YELLOW, "Khartoum")); //Khartoum
		
		infectionDiscardDeck.add(new Card("Infection", Color.BLACK, "Algiers")); //25, Algiers
		infectionDiscardDeck.add(new Card("Infection", Color.BLACK, "Istanbul")); //26, Istanbul
		infectionDiscardDeck.add(new Card("Infection", Color.BLACK, "Moscow")); //27, Moscow
		infectionDiscardDeck.add(new Card("Infection", Color.BLACK, "Tehran")); //Tehran
		infectionDiscardDeck.add(new Card("Infection", Color.BLACK, "Baghdad")); //Baghdad
		infectionDiscardDeck.add(new Card("Infection", Color.BLACK,"Riyadh")); //Riyadh
		infectionDiscardDeck.add(new Card("Infection", Color.BLACK,"Karachi")); //Karachi
		infectionDiscardDeck.add(new Card("Infection", Color.BLACK, "Delhi")); // Delhi
		infectionDiscardDeck.add(new Card("Infection", Color.BLACK, "Mumbai")); //Mumbai
		infectionDiscardDeck.add(new Card("Infection", Color.BLACK, "Chennai")); //Chennai
		infectionDiscardDeck.add(new Card("Infection", Color.BLACK, "Kolkata")); //Kolkata
		infectionDiscardDeck.add(new Card("Infection", Color.BLACK, "Cairo")); //Cairo
		
		infectionDiscardDeck.add(new Card("Infection", Color. RED, "Beijing")); //37, Beijing
		infectionDiscardDeck.add(new Card("Infection",Color. RED, "Seoul")); //38, Seoul
		infectionDiscardDeck.add(new Card("Infection",Color. RED, "Tokyo")); //Tokyo
		infectionDiscardDeck.add(new Card("Infection",Color. RED, "Shanghai")); //Shanghai
		infectionDiscardDeck.add(new Card("Infection",Color. RED, "Osaka")); //Osaka
		infectionDiscardDeck.add(new Card("Infection",Color. RED, "Hong Kong")); //Hong Kong
		infectionDiscardDeck.add(new Card("Infection",Color. RED, "Taipei")); //Taipei
		infectionDiscardDeck.add(new Card("Infection",Color. RED,"Manila")); //Manila
		infectionDiscardDeck.add(new Card("Infection",Color. RED, "Ho Chi Minh City")); //Ho Chi Minh City
		infectionDiscardDeck.add(new Card("Infection",Color. RED,  "Bangkok")); //Bangkok
		infectionDiscardDeck.add(new Card("Infection",Color. RED, "Jakarta")); //Jakarta
		infectionDiscardDeck.add(new Card("Infection",Color. RED, "Sydney")); //Sydney

		shuffle(infectionDiscardDeck, infectionDeck);
	}
	
	public static ArrayList<Card> getStartInfectionDeck()
	{
		InfectionDeck d = new InfectionDeck();
		return infectionDeck;
	}
	

	
	
	
	
}