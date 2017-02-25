import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class PlayerDeck 
{
	private static ArrayList<Card> specialEvents = new ArrayList<Card>();
	//private static ArrayList<Card> playerDeck = new ArrayList<Card>();
	//private static ArrayList<Card> playerDiscardDeck = new ArrayList<Card>();
	
	private static ArrayList<ArrayList<Card>> arrayOfSmallDecks = 
			new ArrayList<ArrayList<Card>>();
	
	private Background b = new Background();
	
	static Random randy = new Random();
	
	private Color blue = Color.blue;
	private Color red = Color.red;
	private Color yellow = Color.yellow;
	private Color black = Color.black;
	
	private static ArrayList<Card> playerCards = new ArrayList();
	private static ArrayList<Card> playerDiscards = new ArrayList();
	private static int diff = 4;
	
	public static void populateSpecials()
	{
		specialEvents.add(new Card("SpecialEvent", Color.WHITE, "OneQuietNight"));
		specialEvents.add(new Card("SpecialEvent", Color.WHITE, "Forecast"));
		specialEvents.add(new Card("SpecialEvent", Color.WHITE, "GovernmentGrant"));
		specialEvents.add(new Card("SpecialEvent", Color.WHITE, "ResilientPopulation"));
		specialEvents.add(new Card("SpecialEvent", Color.WHITE, "Airlift"));
		
		specialEvents.add(new Card("SpecialEvent", Color.WHITE, "BorrowedTime"));
		specialEvents.add(new Card("SpecialEvent", Color.WHITE, "RapidVaccineDeployment"));
		specialEvents.add(new Card("SpecialEvent", Color.WHITE, "CommercialTravelBan"));
		specialEvents.add(new Card("SpecialEvent", Color.WHITE, "SpecialOrders"));
		specialEvents.add(new Card("SpecialEvent", Color.WHITE, "RemoteTreatment"));
		
		specialEvents.add(new Card("SpecialEvent", Color.WHITE, "MobileHospital"));
		specialEvents.add(new Card("SpecialEvent", Color.WHITE, "NewAssignment"));
		specialEvents.add(new Card("SpecialEvent", Color.WHITE, "ReExaminedResearch"));
	
	}
	
	public static void populatePlayerDeck()
	{
		/*for(int i = 0; i < 2*Pawn.getNumPlayers(); i++)
			playerCards.add(specialEvents.remove(randy.nextInt(specialEvents.size())));*/
		
		playerCards.add(new Card("Player", Color.BLUE,"San Francisco")); //0, San Francisco
		playerCards.add(new Card("Player", Color.BLUE,"Chicago")); //1, Chicago
		playerCards.add(new Card("Player", Color.BLUE, "Atlanta")); //Atlanta
		playerCards.add(new Card("Player", Color.BLUE,"Toronto")); // 3, Toronto
		//playerCards.add(new Card("Player", Color.BLUE,"Montreal"));
		playerCards.add(new Card("Player", Color.BLUE, "Washington")); //Washington
		playerCards.add(new Card("Player", Color.BLUE,"New York")); //New York
		playerCards.add(new Card("Player", Color.BLUE, "London")); //London
		playerCards.add(new Card("Player", Color.BLUE, "Madrid")); //Madrid
		playerCards.add(new Card("Player", Color.BLUE, "Paris")); //Paris
		playerCards.add(new Card("Player", Color.BLUE, "Essen")); // Essen
		playerCards.add(new Card("Player", Color.BLUE, "Milan")); //Milan
		playerCards.add(new Card("Player", Color.BLUE, "St. Petersburg")); //St. Petersburg*
		
		playerCards.add(new Card("Player", Color.YELLOW, "Los Angeles")); //13, Los Angeles
		playerCards.add(new Card("Player", Color.YELLOW, "Mexico City")); //14, Mexico City
		playerCards.add(new Card("Player", Color.YELLOW, "Miami")); //Miami
		playerCards.add(new Card("Player", Color.YELLOW, "Bogota")); //Bogota
		playerCards.add(new Card("Player", Color.YELLOW, "Lima")); //Lima
		playerCards.add(new Card("Player", Color.YELLOW,"Santiago")); //Santiago
		playerCards.add(new Card("Player", Color.YELLOW,"Buenos Aires")); //Buenos Aires
		playerCards.add(new Card("Player", Color.YELLOW,"Sao Paulo")); //Sao Paulo
		playerCards.add(new Card("Player", Color.YELLOW,"Lagos")); //Lagos
		playerCards.add(new Card("Player", Color.YELLOW, "Kinshasa")); //Kinshasa
		playerCards.add(new Card("Player", Color.YELLOW, "Johannesburg")); //Johannesburg
		playerCards.add(new Card("Player", Color.YELLOW, "Khartoum")); //Khartoum
		
		playerCards.add(new Card("Player", Color.BLACK, "Algiers")); //25, Algiers
		playerCards.add(new Card("Player", Color.BLACK, "Istanbul")); //26, Istanbul
		playerCards.add(new Card("Player", Color.BLACK, "Moscow")); //27, Moscow
		playerCards.add(new Card("Player", Color.BLACK, "Tehran")); //Tehran
		playerCards.add(new Card("Player", Color.BLACK, "Baghdad")); //Baghdad
		playerCards.add(new Card("Player", Color.BLACK,"Riyadh")); //Riyadh
		playerCards.add(new Card("Player", Color.BLACK,"Karachi")); //Karachi
		playerCards.add(new Card("Player", Color.BLACK, "Delhi")); // Delhi
		playerCards.add(new Card("Player", Color.BLACK, "Mumbai")); //Mumbai
		playerCards.add(new Card("Player", Color.BLACK, "Chennai")); //Chennai
		playerCards.add(new Card("Player", Color.BLACK, "Kolkata")); //Kolkata
		playerCards.add(new Card("Player", Color.BLACK, "Cairo")); //Cairo
		
		playerCards.add(new Card("Player", Color. RED, "Beijing")); //37, Beijing
		playerCards.add(new Card("Player",Color. RED, "Seoul")); //38, Seoul
		playerCards.add(new Card("Player",Color. RED, "Tokyo")); //Tokyo
		playerCards.add(new Card("Player",Color. RED, "Shanghai")); //Shanghai
		playerCards.add(new Card("Player",Color. RED, "Osaka")); //Osaka
		playerCards.add(new Card("Player",Color. RED, "Hong Kong")); //Hong Kong
		playerCards.add(new Card("Player",Color. RED, "Taipei")); //Taipei
		playerCards.add(new Card("Player",Color. RED,"Manila")); //Manila
		playerCards.add(new Card("Player",Color. RED, "Ho Chi Minh City")); //Ho Chi Minh City
		playerCards.add(new Card("Player",Color. RED,  "Bangkok")); //Bangkok
		playerCards.add(new Card("Player",Color. RED, "Jakarta")); //Jakarta
		playerCards.add(new Card("Player",Color. RED, "Sydney")); //Sydney
		
		/*ArrayList<Card> temp = new ArrayList();
		PlayerDeck p = new PlayerDeck();
		p.shuffle2(playerCards, temp);
		p.shuffle2(temp, playerCards);
		dealCards();
		p.shuffle2(playerCards, temp);
		
		int size = temp.size();
		int pileSize = temp.size()/diff;
		
		for(int i=1; i<diff; i++)
		{
			ArrayList<Card> tempDeck = new ArrayList();
			ArrayList<Card> tempToDeck = new ArrayList();
			for(int k=0; k<pileSize; k++)
			{
				tempDeck.add(temp.remove(0));
			}
			tempDeck.add(new Card("Epidemic", Color.WHITE, "Epidemic"));
			p.shuffle2(tempDeck, tempToDeck);
			for(int j=0; j<tempToDeck.size(); j++)
			{
				playerCards.add(tempToDeck.get(j));
			}
		}
		ArrayList<Card> tempDeck = new ArrayList();
		ArrayList<Card> tempToDeck = new ArrayList();
		for(int k=0; k<temp.size(); k++)
		{
			tempDeck.add(temp.remove(0));
		}
		tempDeck.add(new Card("Epidemic", Color.WHITE, "Epidemic"));
		p.shuffle2(tempDeck, tempToDeck);
		for(int j=0; j<tempToDeck.size(); j++)
		{
			playerCards.add(tempToDeck.get(j));
		}*/
		
		PlayerDeck p = new PlayerDeck();
		p.shufflePlayerDeck();
	//	System.out.println(p.toString(playerCards));
		
		
		

	}
	
	public void shufflePlayerDeck()
	{
		
		ArrayList<Card> t = new ArrayList();
		PlayerDeck p = new PlayerDeck();
		p.shuffle2(playerCards, t);
		p.shuffle2(t, playerCards);
		dealCards();
		
		// add an epidemic card to each small deck and add the small decks to 
		// arrayOfSmallDecks
		for(int i = 0; i < diff; i++)
		{
			ArrayList<Card> temp = new ArrayList<Card>();
			temp.add(new Card("Epidemic", null, "Epidemic"));
			arrayOfSmallDecks.add(temp);
		}
		for(int i=0; i<Pawn.getNumPlayers()*2; i++)
		{
			int spot=randy.nextInt(specialEvents.size());
			playerCards.add(specialEvents.remove(spot));
		}
	
		// add all player cards to various small decks
		int dc = 0; // deckControl counter
		while(!playerCards.isEmpty()) //shuffle
		{
			if(dc % arrayOfSmallDecks.size() == 0)
				dc = 0;
			arrayOfSmallDecks.get(dc).add

				(playerCards.remove(randy.nextInt(playerCards.size())));
			dc++;
		}
	
		//shuffle each small deck.
		
		for(int i = 0; i < arrayOfSmallDecks.size(); i++)
		{
			ArrayList<Card> temp = shuffleSmallDeck(arrayOfSmallDecks.get(i));
			arrayOfSmallDecks.set(i,temp);
			
		}
		
	
		//shuffle small decks to randomize where extra cards are allocated
		arrayOfSmallDecks = shuffleSmallDecks(arrayOfSmallDecks); 
		
		
		//add cards from the small decks back to the playerDeck.
		while(!arrayOfSmallDecks.isEmpty())
		{	
			while(!arrayOfSmallDecks.get(0).isEmpty())
			{
				playerCards.add(arrayOfSmallDecks.get(0).remove(0));
			}
			arrayOfSmallDecks.remove(0);
		}
		/*for(int i=0; i<13/*Pawn.getNumPlayers()*2; i++)
		{
			int spot=randy.nextInt(specialEvents.size());
			playerCards.add(specialEvents.remove(spot));
		}*/
		
		
		/*p.shuffle2(playerCards, t);
		p.shuffle2(t, playerCards);*/
	}
	
	public ArrayList<Card> shuffleSmallDeck(ArrayList<Card> original)
	{
		ArrayList<Card> toRet = new ArrayList<Card>();
		while(!original.isEmpty())
			toRet.add(original.remove(randy.nextInt(original.size())));
		return toRet;
	}
	
	public ArrayList<ArrayList<Card>> shuffleSmallDecks(ArrayList<ArrayList<Card>> original)
	{
		ArrayList<ArrayList<Card>> toRet = new ArrayList<ArrayList<Card>>();
		while(!original.isEmpty())
			toRet.add(original.remove(randy.nextInt(original.size())));
		return toRet;
	}
	
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
	
	public static void normalDiscardCard(Card card)
	{
		Card.getPlayerDiscarding().add(card);
		//card.getCardImage().setX(760);
		card.setDiscard(true);
		
	}
	
	public static void handDiscardCard(Card card)
	{
		Card.getPlayerHandDiscarding().add(card);
		card.getCardImage().setX(660);
		card.getCardImage().setY(460);
		//card.getCardImage().setX(760);
		card.setDiscard(true);
		
	}
	
	/*public static void animateCard(Card c)
	{
		try
		{
			Thread thread = new Thread();
			thread.sleep(50);
		}
		catch(Exception e){}
		if(c.getCardImage().getX()<900)
			c.getCardImage().setX(c.getCardImage().getX()+9);
		else
		{
			c.setDiscard(false);
			c.getCardImage().setImage(c.getCardImage().getCurrentImage());
		}
	}*/
	
	public static void dealCards()
	{
		PlayerDeck p =new PlayerDeck();
		for(int i = 0; i<Pawn.getPawns().size(); i++)
		{
			if(Pawn.getNumPlayers()==2)
			{
				infectTop2(Pawn.getPawns().get(i).getMyHand(), playerCards);
				infectTop2(Pawn.getPawns().get(i).getMyHand(), playerCards);
				infectTop2(Pawn.getPawns().get(i).getMyHand(), playerCards);
				infectTop2(Pawn.getPawns().get(i).getMyHand(), playerCards);
			}
			if(Pawn.getNumPlayers()==3)
			{
				infectTop2(Pawn.getPawns().get(i).getMyHand(), playerCards);
				infectTop2(Pawn.getPawns().get(i).getMyHand(), playerCards);
				infectTop2(Pawn.getPawns().get(i).getMyHand(), playerCards);
			}
			if(Pawn.getNumPlayers()==4||Pawn.getNumPlayers()==5)
			{
				infectTop2(Pawn.getPawns().get(i).getMyHand(), playerCards);
				infectTop2(Pawn.getPawns().get(i).getMyHand(), playerCards);
			}
		}

	}
	
	public static Card infectTop2(ArrayList<Card> to, ArrayList<Card> from)
	{
		Card top = from.remove(from.size()-1);
		to.add(top);
		return top;
	}
	
	public static Card infectBottom2(ArrayList<Card> to, ArrayList<Card> from)
	{
		Card bottom = from.remove(0);
		to.add(bottom);
		return bottom;
	}
	
	public void shuffle2(ArrayList<Card> toShuffle, ArrayList<Card> remadeDeck)
	{
		Random randy = new Random();
		int i=0;
		while(i<toShuffle.size())
		{
			int takeOut = randy.nextInt(toShuffle.size());
			remadeDeck.add(toShuffle.get(takeOut));
			toShuffle.remove(takeOut);	
		}
	}
	
	public static void main (String [] args)
	{
		PlayerDeck p = new PlayerDeck();

	}
	
	public static ArrayList<Card> getPlayerDeck(){return playerCards;}
	public static ArrayList<Card> getDiscardDeck(){return playerDiscards;}
	public static void setDiff(int c){diff=c;}
	
	

}
