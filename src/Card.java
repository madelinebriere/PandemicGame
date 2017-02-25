import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Card 
{
	/**
	 * InfectionDeck: type = "City"
	 * 
	 * PlayerDeck: type = "City" or "SpecialEvent" or "Epidemic"
	 */
	
	private String myType, myId; 
	private Color myColor;
	private CardImage myImage;
	private boolean top = false;
	private static CardImage hidden;
	private static CardImage discard;
	private static CardImage hiddenPlayer;
	private static CardImage discardPlayer;
	private String name="";
	private boolean beingDiscarded = false;
	private static int c=0;
	private boolean first;
	private static ArrayList <Card> beingPlayerDiscardedPile = new ArrayList();
	private static ArrayList <Card> beingPlayerHandDiscardedPile = new ArrayList();
	private static ArrayList <Card> beingInfectionDiscardedPile = new ArrayList();
	
	public boolean hover;
	public boolean getHover(){return hover;}
	public void setHover(boolean c){hover = c;}
	
	/*public boolean cardClicked=false;
	public boolean getClicked() {return cardClicked;}
	public void setClicked(boolean c){cardClicked=c;}*/
	
	public Card(String type, Color color, String id)
	{
		myType = type;
		myId = id;
		if(color != null)
			myColor = color;
		for(int i=0; i<id.length(); i++)
		{
			if (id.charAt(i) != ' ' && id.charAt(i) != '.')
			{
				name+=id.charAt(i);
			}
		}
		if(type.equals("Infection"))
		{
			name+="Infection.png";
			myImage = new CardImage(590*Background.getResize()/2, 50*Background.getResize()/2, 
					160*Background.getResize()/2, 110*Background.getResize()/2, name, true);
		}
		if(type.equals("Player")||type.equals("Epidemic"))
		{
			name+="Player.png";
			myImage = new CardImage(625*Background.getResize()/2, 560*Background.getResize()/2,
					110*Background.getResize()/2, 160*Background.getResize()/2, name); 
		}
		if(type.equals("SpecialEvent"))
		{
			name="SpecialEvent"+name+".png";
			myImage = new CardImage(625*Background.getResize()/2, 560*Background.getResize()/2,
					110*Background.getResize()/2, 160*Background.getResize()/2, name); 
		}
		
	}
	/*
	 * A constructor that produces a City card based on the information from
	 * an input city
	 */
	
	
	public Card(City city)
	{
		myType="City";
		myId=city.getName();
		myColor=city.getColor();
	}
	
	/*public static void drawCard(Graphics g, int x, int y, int shift)
	{
		g.fillRoundRect(x+shift,y, 160, 110, 30, 30);
	}*/
	
	public boolean equals(City city)
	{
		if(this.getID().equals(city.getName()))
			return true;
		else
			return false;
	}
	
	public void resetInfectionCard()
	{
		getCardImage().setX(590*Background.getResize()/2);
		getCardImage().setY(50*Background.getResize()/2);
	}
	
	public static int containsCard(ArrayList<Card> cards, String name)
	{
		int toRet=0;
		for(int i=0; i<cards.size();i++)
		{
			if(name.equals(cards.get(i).getID()))
			{
				toRet=i;
			}
		}
		return toRet;
	}
	
	/*
	 * Move card over each time this is called
	 */
	public static void movingInfectionCard (Graphics window, Card card, int startX, int finishX)
	{
		card.getCardImage().setX(card.getCardImage().getX()+1);
	}
	
	/*
	 * Go through the cards and draw them
	 */
	public static void drawCards2(Graphics window, int x)
	{
		
			window.drawImage(CardImage.getInfectionBack(), 
					InfectionDeck.getInfectionDeck().get(0).getCardImage().getX()*Background.getResize()/2,
					InfectionDeck.getInfectionDeck().get(0).getCardImage().getY()*Background.getResize()/2,
					InfectionDeck.getInfectionDeck().get(0).getCardImage().getWidth()*Background.getResize()/2,
					InfectionDeck.getInfectionDeck().get(0).getCardImage().getHeight()*Background.getResize()/2, null);
		
			int temp =InfectionDeck.getInfectionDiscardDeck().size()-1;
			try
			{
			window.drawImage(InfectionDeck.getInfectionDiscardDeck().get(temp).getCardImage().getCurrentImage(), 
					760*Background.getResize()/2,
					InfectionDeck.getInfectionDiscardDeck().get(temp).getCardImage().getY()*Background.getResize()/2,
					InfectionDeck.getInfectionDiscardDeck().get(temp).getCardImage().getWidth()*Background.getResize()/2,
					InfectionDeck.getInfectionDiscardDeck().get(temp).getCardImage().getHeight()*Background.getResize()/2, null);
			}
			catch(Exception e){}

		
		for (int i=0; i<beingInfectionDiscardedPile.size(); i++)
		{
			Card c = beingInfectionDiscardedPile.get(i);
			
			
			if(InfectionDeck.getInfectionDeck().size()!=0)
			{
				window.drawImage(CardImage.getInfectionBack(), 
						590*Background.getResize()/2, 50*Background.getResize()/2, 
						160*Background.getResize()/2, 110*Background.getResize()/2, null);
				
			}
			if(c.getCardImage().getX()<650)
			{
				c.getCardImage().setX(c.getCardImage().getX()+5);
				window.drawImage(CardImage.getInfectionBack(), 
						c.getCardImage().getX()*Background.getResize()/2,
						c.getCardImage().getY()*Background.getResize()/2,
						c.getCardImage().getWidth()*Background.getResize()/2,
						c.getCardImage().getHeight()*Background.getResize()/2, null);
				
			}
			else if(c.getCardImage().getX()<760)
			{
				c.getCardImage().setX(c.getCardImage().getX()+5);
				window.drawImage(c.getCardImage().getCurrentImage(), 
						c.getCardImage().getX()*Background.getResize()/2,
						c.getCardImage().getY()*Background.getResize()/2,
						c.getCardImage().getWidth()*Background.getResize()/2,
						c.getCardImage().getHeight()*Background.getResize()/2, null);
				
			}
			else
			{
				//c.getCardImage().setX(c.getCardImage().getX()+5);
				window.drawImage(c.getCardImage().getCurrentImage(), 
						c.getCardImage().getX()*Background.getResize()/2,
						c.getCardImage().getY()*Background.getResize()/2,
						c.getCardImage().getWidth()*Background.getResize()/2,
						c.getCardImage().getHeight()*Background.getResize()/2, null);
				InfectionDeck.getInfectionDiscardDeck().add(beingInfectionDiscardedPile.remove(i));
				
			}
			
			break;
		}
		
		/*
		 * STOPS DISCARD ANIMATING INFECTION DECK AFTER YOU DISCARD FROM HAND
		 */
		
		
		
		if(PlayerDeck.getPlayerDeck().size()!=0)
		{
			window.drawImage(CardImage.getPlayerBack(),
					PlayerDeck.getPlayerDeck().get(0).getCardImage().getX(),
					PlayerDeck.getPlayerDeck().get(0).getCardImage().getY(),
					PlayerDeck.getPlayerDeck().get(0).getCardImage().getWidth(),
					PlayerDeck.getPlayerDeck().get(0).getCardImage().getHeight(), null);
		}
		
		int k = PlayerDeck.getDiscardDeck().size()-1;
		{
			try
			{
			window.drawImage(PlayerDeck.getDiscardDeck().get(k).getCardImage().getCurrentImage(), 
							PlayerDeck.getDiscardDeck().get(k).getCardImage().getX()*Background.getResize()/2,
							PlayerDeck.getDiscardDeck().get(k).getCardImage().getY()*Background.getResize()/2,
							PlayerDeck.getDiscardDeck().get(k).getCardImage().getWidth()*Background.getResize()/2,
							PlayerDeck.getDiscardDeck().get(k).getCardImage().getHeight()*Background.getResize()/2, null);
			}
			catch(Exception e){}
			
		}
		
		for (int i=0; i<beingPlayerDiscardedPile.size(); i++)
		{
			try
			{
				Thread t = new Thread();
				t.sleep(3);
			}
			catch(Exception e){}
			Card c = beingPlayerDiscardedPile.get(i);
			if(PlayerDeck.getPlayerDeck().size()!=0)
			{
				window.drawImage(CardImage.getPlayerBack(),625*Background.getResize()/2, 560*Background.getResize()/2,
				110*Background.getResize()/2, 160*Background.getResize()/2,null);
			}
			if(c.getCardImage().getX()<760)
			{
				c.getCardImage().setX(c.getCardImage().getX()+5);
				window.drawImage(c.getCardImage().getCurrentImage(), 
						c.getCardImage().getX()*Background.getResize()/2,
						c.getCardImage().getY()*Background.getResize()/2,
						c.getCardImage().getWidth()*Background.getResize()/2,
						c.getCardImage().getHeight()*Background.getResize()/2, null);
			}
			else
			{
				window.drawImage(c.getCardImage().getCurrentImage(), 
						c.getCardImage().getX()*Background.getResize()/2,
						c.getCardImage().getY()*Background.getResize()/2,
						c.getCardImage().getWidth()*Background.getResize()/2,
						c.getCardImage().getHeight()*Background.getResize()/2, null);
				PlayerDeck.getDiscardDeck().add(beingPlayerDiscardedPile.remove(i));
			}
			break;
		}
		
		for (int i=0; i<beingPlayerHandDiscardedPile.size(); i++)
		{
			try
			{
				Thread t = new Thread();
				t.sleep(3);
			}
			catch(Exception e){}
			Card c = beingPlayerHandDiscardedPile.get(i);
			if(c.getCardImage().getX()<760)
			{
				c.getCardImage().setX(c.getCardImage().getX()+5);
				c.getCardImage().setY(c.getCardImage().getY()+5);
				window.drawImage(c.getCardImage().getCurrentImage(), 
						c.getCardImage().getX()*Background.getResize()/2,
						c.getCardImage().getY()*Background.getResize()/2,
						c.getCardImage().getWidth()*Background.getResize()/2,
						c.getCardImage().getHeight()*Background.getResize()/2, null);
			}
			else
			{
				window.drawImage(c.getCardImage().getCurrentImage(), 
						c.getCardImage().getX()*Background.getResize()/2,
						c.getCardImage().getY()*Background.getResize()/2,
						c.getCardImage().getWidth()*Background.getResize()/2,
						c.getCardImage().getHeight()*Background.getResize()/2, null);
				PlayerDeck.getDiscardDeck().add(beingPlayerHandDiscardedPile.remove(i));
			}
			break;
		}
	}
	
	

	
	public String getType() {return myType;}
	public String getID() {return myId;}
	public Color getColor() {return myColor;}
	public CardImage getCardImage (){return myImage;}
	public boolean getTop() {return top;}
	public void setTop(boolean s){top=s;}
	public String getName(){return name;}
	public boolean getDiscard(){return beingDiscarded;}
	public void setDiscard(boolean b) {beingDiscarded=b;}
	public static ArrayList<Card> getPlayerDiscarding(){return beingPlayerDiscardedPile;}
	public static ArrayList<Card> getPlayerHandDiscarding(){return beingPlayerHandDiscardedPile;}
	public static ArrayList<Card> getInfectionDiscarding(){return beingInfectionDiscardedPile;}
	

	
}
