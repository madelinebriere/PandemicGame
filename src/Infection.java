import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Infection 
/*
 * General class for drawing pieces and moving items
 */
{
	private static Image infectionMarker;
	private static Image outbreakMarker;
	private static Image blackCure;
	private static Image yellowCure;
	private static Image redCure;
	private static Image blueCure;
	private static int infectionRate=2;
	private static int infectionCounter=0;
	private static int outbreak=0;
	
	private static boolean blueCured;
	private static boolean yellowCured;
	private static boolean redCured;
	private static boolean blackCured;
	
	private static boolean blueEradicated;
	private static boolean yellowEradicated;
	private static boolean redEradicated;
	private static boolean blackEradicated;
	
	public static int getInfectionRate() {return infectionRate;}
	public static int getInfectionCounter() {return infectionCounter;}
	public static boolean getBlue(){return blueCured;}
	public static boolean getRed() {return redCured;}
	public static boolean getYellow() {return yellowCured;}
	public static boolean getBlack() {return blackCured;}
	
	public static void setBlue(boolean c) {blueCured=c;}
	public static void setBlack(boolean c) {blackCured=c;}
	public static void setYellow(boolean c) {yellowCured=c;}
	public static void setRed(boolean c) {redCured=c;}
	
	public static boolean needToDiscard = false; //Should the program pause to let the player discard?
	public static void setNeed(boolean c) {needToDiscard = c;}
	public static boolean getNeed() {return needToDiscard;}
	
	private int exClick;
	private int whyClick;
	
	private int counter=0;
	
	private static Image no =Toolkit.getDefaultToolkit().getImage("no.png");
	private static boolean borrowedTime=false;
	private static boolean travelBan=false;
	private static boolean grant=false;
	private static boolean mobile = false;
	private static boolean quietNight=false;
	private static boolean airlift=false;
	private static boolean specialOrders=false;
	private static boolean remoteTreatment = false;
	private static boolean rapidVaccine= false;
	private static boolean operationsBuild = false;
	private static boolean operationsFlight = false;
	
	public static void setOperationsFlight(boolean c) {operationsFlight=c;}
	public static void setOperationsBuild(boolean c){operationsBuild=c;}
	
	/*private static boolean blueJustCured;
	public boolean getBlueJustCured(){return blueJustCured;}
	private static boolean redJustCured;
	public boolean getRedJustCured(){return redJustCured;}
	private static boolean blackJustCured;
	public boolean getBlackJustCured(){return blackJustCured;}
	private static boolean yellowJustCured;
	public boolean getYellowJustCured(){return yellowJustCured;}*/
	
	private static Color rapidVaccineCured;
	private static int rapidVaccineNumCured;
	
	private static City rapidVaccineCenter;
	private static void setRapidVaccineCenter(City c) {rapidVaccineCenter = c;}
	
	private static Pawn airliftPlayer;
	private static Pawn specialPlayer;
	private static Pawn bannedPlayer;
	private static Pawn storedPlayer;
	private static Card plannerStoredCard;
	private static int storedBlack;
	private static int storedBlue;
	private static int storedYellow;
	private static int storedRed;
	
	private static ArrayList<String> cureDiscard = new ArrayList();
	public static ArrayList<String> getCureDiscard(){return cureDiscard;}
	public static void removeCureDiscard(String string){cureDiscard.remove(string);}
	
	private static int curedCards=0;
	public static void addCuredCard(){curedCards++;}
	public static void setCuredCard(int c){curedCards=c;}
	public static int getCuredCard(){return curedCards;}
	
	public static void addStoredBlack(int c){storedBlack+=c;}
	public static void addStoredYellow(int c){storedYellow+=c;}
	public static void addStoredRed(int c){storedRed+=c;}
	public static void addStoredBlue(int c){storedBlue+=c;}
	
	public static int getStoredBlack(){return storedBlack;}
	public static int getStoredYellow(){return storedYellow;}
	public static int getStoredRed(){return storedRed;}
	public static int getStoredBlue(){return storedBlue;}
	
	public static void setPlannerStoredCard(Card c){plannerStoredCard=c;}
	public static Card getPlannerStoredCard(){return plannerStoredCard;}
	
	private static Image move;
	
	public static void setTime(boolean c){borrowedTime=c;}
	public static void setBan(boolean c){travelBan=c;}
	public static void setBannedPlayer(Pawn p){bannedPlayer=p;}
	public static void setAirliftPlayer(Pawn p){airliftPlayer=p;}
	public static Pawn getAirliftPlayer(){return airliftPlayer;}
	public static void setSpecialPlayer(Pawn p){specialPlayer=p;}
	public static Pawn getSpecialPlayer(){return specialPlayer;}
	public static void setStoredPlayer(Pawn p){storedPlayer=p;}
	public static void setRapidVaccine(boolean c){rapidVaccine=c;}
	public static void setGrant(boolean c){grant=c;}
	public static void setMobile(boolean c){mobile=c;}
	public static void setQuietNight(boolean c){quietNight=c;}
	public static void setAirlift(boolean c){airlift = c;}
	public static void setSpecialOrders(boolean c){specialOrders=c;}
	public static void setRemoteTreatment(boolean c){remoteTreatment=c;}
	public static int remoteTreatmentCured=0;
	
	Pawn currentPawn;
	int currentInt;
	City currentCity;
	
	public static boolean popupUp = false;
	
	public static void setPop(boolean c) { popupUp=c;}
	public static boolean getPop(){return popupUp;}
	
	public static boolean lost;
	public static boolean won;
	public static boolean getLost(){return lost;}
	public static boolean getWon() {return won;}
	public static void setLost(boolean c){lost=c;}
	public static void setWon(boolean c){won =c;}
	
	
	public static void increaseInfectionRate() 
	{
		infectionCounter++;
		if(infectionCounter==3)
			infectionRate=3;
		if(infectionCounter==5)
			infectionRate=4;
		if(infectionCounter==7)
		{
			System.out.println("YOU LOSE: INFECTION RATE EXCEEDS LIMIT");
			ScrollingBackground.drawLoseTimedGUI("You lose: Infection rate too high");
			//lost=true;
		}
		
	}
	public static int getOutbreak() {return outbreak;}
	public static void increaseOutbreak()
	{
		outbreak++;
		if(outbreak>7)
		{
			System.out.println("YOU LOSE: TOO MANY OUTBREAKS");
			ScrollingBackground.drawLoseTimedGUI("You lose: Too many outbreaks");
			//lost=true;
		}
	}
	
	public static void drawRoles(Graphics window, int x)
	{
		int i=0;
		for(i=0; i<Pawn.getRoleImages().size()/2;i++)
		{
			window.drawImage(Pawn.getRoleImages().get(i), i*170+ 5, 15, 190, 240, null);
		}
		window.drawImage(Pawn.getRoleImages().get(i), i*170-1000, 270, 190, 240, null);
		i++;
		window.drawImage(Pawn.getRoleImages().get(i), i*170-500, 270, 190, 240, null);
		i++;
		for( ; i<Pawn.getRoleImages().size(); i++)
		{
			window.drawImage(Pawn.getRoleImages().get(i), i*170 - 1350, 520, 190, 240, null);
		}
	}
	
	public static void drawSpecialMove(Graphics window, int x)
	{
		window.setColor(Color.BLUE);
		window.fillRect(930, 100, 100, 25);
		
		window.setColor(Color.WHITE);
		window.drawRect(930, 100, 100, 25);
		window.drawString("Special Move", 940, 120);
		
		
	}
	
	public static void drawEndTurn(Graphics window, int x)
	{
		window.setColor(Color.ORANGE);
		window.fillRect(930, 250, 100, 25);
		
		window.setColor(Color.WHITE);
		window.drawRect(930, 250, 100, 25);
		window.drawString("End Turn", 945, 270);
		
	}
	
	public static void drawCurrentStats(Graphics window, int x)
	{
		window.setColor(Color.GREEN);
		window.fillRect(930, 200, 100, 25);
		
		window.setColor(Color.WHITE);
		window.drawRect(930, 200, 100, 25);
		window.drawString("Current Stats", 940, 220);
	}
	
	public static void drawInstruction(Graphics window, int x)
	{
		window.setColor(Color.MAGENTA);
		window.fillRect(930, 50, 100, 25);
		
		window.setColor(Color.WHITE);
		window.drawRect(930, 50, 100, 25);
		window.drawString("Instructions", 940, 70);
	}
	
	
	public static void drawOutbreak(Graphics window, int x)
	{
		window.setColor(Color.WHITE);
		
		window.fillOval(82*Background.getResize()/2+x,435*Background.getResize()/2,38*Background.getResize()/2,38*Background.getResize()/2);
		window.fillOval(82*Background.getResize()/2+x,499*Background.getResize()/2,38*Background.getResize()/2,38*Background.getResize()/2);
		window.fillOval(115*Background.getResize()/2+x,530*Background.getResize()/2,38*Background.getResize()/2,38*Background.getResize()/2);
		window.fillOval(115*Background.getResize()/2+x,467*Background.getResize()/2,38*Background.getResize()/2,38*Background.getResize()/2);
		window.fillOval(82*Background.getResize()/2+x,560*Background.getResize()/2,38*Background.getResize()/2,38*Background.getResize()/2);
		window.fillOval(115*Background.getResize()/2+x,590*Background.getResize()/2,38*Background.getResize()/2,38*Background.getResize()/2);
		window.fillOval(82*Background.getResize()/2+x,620*Background.getResize()/2,38*Background.getResize()/2,38*Background.getResize()/2);
		window.fillOval(115*Background.getResize()/2+x,650*Background.getResize()/2,38*Background.getResize()/2,38*Background.getResize()/2);
		window.fillOval(82*Background.getResize()/2+x,680*Background.getResize()/2,38*Background.getResize()/2,38*Background.getResize()/2);
		
		window.setColor(Color.BLACK);
		
		window.drawString(""+1, 132*Background.getResize()/2+x, 492*Background.getResize()/2);
		window.drawString(""+0, 99*Background.getResize()/2+x, 460*Background.getResize()/2);
		window.drawString(""+2, 99*Background.getResize()/2+x, 521*Background.getResize()/2);
		window.drawString(""+3, 132*Background.getResize()/2+x, 551*Background.getResize()/2);
		window.drawString(""+4, 99*Background.getResize()/2+x, 583*Background.getResize()/2);
		window.drawString(""+5, 132*Background.getResize()/2+x, 613*Background.getResize()/2);
		window.drawString(""+6, 99*Background.getResize()/2+x, 643*Background.getResize()/2);
		window.drawString(""+7, 132*Background.getResize()/2+x, 673*Background.getResize()/2);
		window.drawString("DEAD", 85*Background.getResize()/2+x, 704*Background.getResize()/2);
		
		window.drawOval(82*Background.getResize()/2+x,435*Background.getResize()/2,38*Background.getResize()/2,38*Background.getResize()/2);
		window.drawOval(82*Background.getResize()/2+x,499*Background.getResize()/2,38*Background.getResize()/2,38*Background.getResize()/2);
		window.drawOval(115*Background.getResize()/2+x,530*Background.getResize()/2,38*Background.getResize()/2,38*Background.getResize()/2);
		window.drawOval(115*Background.getResize()/2+x,467*Background.getResize()/2,38*Background.getResize()/2,38*Background.getResize()/2);
		window.drawOval(82*Background.getResize()/2+x,560*Background.getResize()/2,38*Background.getResize()/2,38*Background.getResize()/2);
		window.drawOval(115*Background.getResize()/2+x,590*Background.getResize()/2,38*Background.getResize()/2,38*Background.getResize()/2);
		window.drawOval(82*Background.getResize()/2+x,620*Background.getResize()/2,38*Background.getResize()/2,38*Background.getResize()/2);
		window.drawOval(115*Background.getResize()/2+x,650*Background.getResize()/2,38*Background.getResize()/2,38*Background.getResize()/2);
		window.drawOval(82*Background.getResize()/2+x,680*Background.getResize()/2,38*Background.getResize()/2,38*Background.getResize()/2);
		
		try
		{
			outbreakMarker=ImageIO.read(new File("outbreak-marker.png"));
		}
		catch(Exception e){}
		
		window.setColor(Color.BLUE);
	
		switch(outbreak)
		{
			case 0: 
				window.drawImage(outbreakMarker, 82*Background.getResize()/2+x,435*Background.getResize()/2,38*Background.getResize()/2,38*Background.getResize()/2, null);
				window.setColor(Color.BLACK);
				window.drawString(""+0, 99*Background.getResize()/2+x, 460*Background.getResize()/2);
				break;
			case 1:
				window.drawImage(outbreakMarker,115*Background.getResize()/2+x,467*Background.getResize()/2,38*Background.getResize()/2,38*Background.getResize()/2, null);
				window.setColor(Color.BLACK);
				window.drawString(""+1, 132*Background.getResize()/2+x, 492*Background.getResize()/2);
				break;
			case 2:
				window.drawImage(outbreakMarker,82*Background.getResize()/2+x,499*Background.getResize()/2,38*Background.getResize()/2,38*Background.getResize()/2, null);
				window.setColor(Color.BLACK);
				window.drawString(""+2, 99*Background.getResize()/2+x, 521*Background.getResize()/2);
				break;
			case 3:
				window.drawImage(outbreakMarker,115*Background.getResize()/2+x,530*Background.getResize()/2,38*Background.getResize()/2,38*Background.getResize()/2, null);
				window.setColor(Color.BLACK);
				window.drawString(""+3, 132*Background.getResize()/2+x, 551*Background.getResize()/2);
				break;
			case 4:
				window.drawImage(outbreakMarker,82*Background.getResize()/2+x,560*Background.getResize()/2,38*Background.getResize()/2,38*Background.getResize()/2, null);
				window.setColor(Color.BLACK);
				window.drawString(""+4, 99*Background.getResize()/2+x, 583*Background.getResize()/2);
				break;
			case 5:
				window.drawImage(outbreakMarker,115*Background.getResize()/2+x,590*Background.getResize()/2,38*Background.getResize()/2,38*Background.getResize()/2, null);
				window.setColor(Color.BLACK);
				window.drawString(""+5, 132*Background.getResize()/2+x, 613*Background.getResize()/2);
				break;
			case 6:
				window.drawImage(outbreakMarker,82*Background.getResize()/2+x,620*Background.getResize()/2,38*Background.getResize()/2,38*Background.getResize()/2, null);
				window.setColor(Color.BLACK);
				window.drawString(""+6, 99*Background.getResize()/2+x, 643*Background.getResize()/2);
				break;
			case 7:
				window.drawImage(outbreakMarker,115*Background.getResize()/2+x,650*Background.getResize()/2,38*Background.getResize()/2,38*Background.getResize()/2, null);
				window.setColor(Color.BLACK);
				window.drawString(""+7, 132*Background.getResize()/2+x, 673*Background.getResize()/2);
				break;
		}
		
		try
		{
			infectionMarker=ImageIO.read(new File("infection-rate-marker.png"));
		}
		catch(Exception e){}
		
		
		window.setColor(Color.RED);
		switch (Infection.getInfectionCounter())
		{
			case 0:
				window.drawImage(infectionMarker, 615*Background.getResize()/2+x,172*Background.getResize()/2,35*Background.getResize()/2,35*Background.getResize()/2, null);
				break;
			case 1:
				window.drawImage(infectionMarker,656*Background.getResize()/2+x,185*Background.getResize()/2,35*Background.getResize()/2,35*Background.getResize()/2, null);
				break;
			case 2:
				window.drawImage(infectionMarker,696*Background.getResize()/2+x,193*Background.getResize()/2,35*Background.getResize()/2,35*Background.getResize()/2, null);
				break;
			case 3:
				window.drawImage(infectionMarker,735*Background.getResize()/2+x,198*Background.getResize()/2,35*Background.getResize()/2,35*Background.getResize()/2, null);
				break;
			case 4:
				window.drawImage(infectionMarker,776*Background.getResize()/2+x,193*Background.getResize()/2,35*Background.getResize()/2,35*Background.getResize()/2, null);
				break;
			case 5:
				window.drawImage(infectionMarker,816*Background.getResize()/2+x,185*Background.getResize()/2,35*Background.getResize()/2,35*Background.getResize()/2, null);
				break;
			case 6:
				window.drawImage(infectionMarker,856*Background.getResize()/2+x,172*Background.getResize()/2,35*Background.getResize()/2,35*Background.getResize()/2, null);
				break;
		}
		
	}
	
	public static void drawCures(Graphics g, int x)
	{
		try
		{
			g.drawImage(City.getBlue(), 328+x, 673, 37, 37, null);
			blueCure=ImageIO.read(new File("vial-blue.png"));
			
			if(!blueCured)
				g.drawImage(blueCure, 330+x, 600, 30+x, 50, null);
			else
			{
				g.drawImage(blueCure, 330+x, 650, 30+x, 50, null);
				if(blueEradicated)
					g.drawImage(no,335+x,670,20,20, null);
					
			}
			
			
		}
		catch(Exception e){}
		
		try
		{
			g.drawImage(City.getYellow(), 400+x, 673, 37, 37, null);
			yellowCure=ImageIO.read(new File("vial-yellow.png"));
			if(!yellowCured)
				g.drawImage(yellowCure, 402+x, 600, 30+x, 50, null);
			else
			{
				g.drawImage(yellowCure, 402+x, 650, 30+x, 50, null);
				if(yellowEradicated)
					g.drawImage(no,407+x,670,20,20, null);
			}
			
		}
		catch(Exception e){}
		
		try
		{
			g.drawImage(City.getBlack(), 471+x, 673, 37, 37, null);
			blackCure=ImageIO.read(new File("vial-black.png"));
			if(!blackCured)
				g.drawImage(blackCure, 473+x, 600, 30+x, 50, null);
			else
			{
				g.drawImage(blackCure, 473+x, 650, 30+x, 50, null);
				if(blackEradicated)
					g.drawImage(no,478+x,670,20,20, null);
			}
			
			
		}
		catch(Exception e){}
		
		try
		{
			g.drawImage(City.getRed(), 542+x, 673, 37, 37, null);
			redCure=ImageIO.read(new File("vial-red.png"));
			if(!redCured)
				g.drawImage(redCure, 544, 600+x, 30, 50+x, null);
			else
			{
				g.drawImage(redCure, 544, 650+x, 30, 50+x, null);
				if(redEradicated)
					g.drawImage(no,549+x,670,20,20, null);
			}
			
			
		}
		catch(Exception e){}
	}
	

	
	public void cycle(int index)
	{
		
		
		endTurn(index);
		resetClick();
		
		int i=index;
		Pawn.whoseTurn().setSpecialMovesTaken(0);
		Pawn.getPawns().get(i).setTurn(false);
		Pawn.getPawns().get(i).setMoves(0);
		
		if(specialOrders)
		{
			specialOrders=false;
			for(int k=0; k<Pawn.getPawns().size();k++)
				if(Pawn.getPawns().get(k).equals(storedPlayer))
					i=k;
		}
	
	
		
		
		borrowedTime=false;
		if(!(Pawn.getPawns().get(i).getRole().equals("Generalist")))
			Pawn.getPawns().get(i).setMovesPerTurn(4);
		else
			Pawn.getPawns().get(i).setMovesPerTurn(5);
		mobile=false;
		quietNight=false;
	
		
		if(i+1 < Pawn.getPawns().size())
		{
			Pawn.getPawns().get(i+1).setTurn(true);
		}
		else
		{
			Pawn.getPawns().get(0).setTurn(true);
		}
		
		if(bannedPlayer!=null && Pawn.whoseTurn().equals(bannedPlayer)) //has it been a full round cycle?
			travelBan=false;
		
		
		
	}
	
	public void resetClick()
	{
		exClick=0;
		whyClick=0;
	}
	
	public void checkWin()
	{
		if(blueCured&&blackCured&&yellowCured&&redCured)
			ScrollingBackground.drawWinTimedGUI("YOU WIN!");
	}
	
	
	public void movePawns(int ex, int why)
	{
		exClick=ex;
		whyClick=why;
		for(int i=0; i<Pawn.getPawns().size();i++)
		{
			Pawn pawn = Pawn.getPawns().get(i);
			if(pawn.getTurn() && pawn.getMoves()<pawn.getMovesPerTurn()) //is it time for this pawn to go
				processClick(i);
			if(pawn.getTurn())
			{
				Rectangle endTurn= new Rectangle(930, 250, 100, 25);//end turn
				if(endTurn.contains(exClick, whyClick))
				{
					ScrollingBackground.drawVoluntaryEndTurnGUI();
					resetClick();
					popupUp=true;
				}	
				/*else if(pawn.getMoves()>=pawn.getMovesPerTurn())
				{
					ScrollingBackground.drawVoluntaryEndTurnGUI();
					resetClick();
					popupUp=true;
				}*/
			}
		}
	}
	
	public static void recycleVolEnd()
	{
		ScrollingBackground.drawVoluntaryEndTurnGUI();
		Infection i = new Infection();
		i.resetClick();
		popupUp=true;
	}
	
	public void resetJustCured()
	{
		rapidVaccineCured = null;
		counter=0;
	}
	
	public void processClick(int currentPawn)
	{
		Pawn pawn = Pawn.getPawns().get(currentPawn);
	
		if(counter>=1)
			resetJustCured();
		if(rapidVaccineCured!=null)
			counter++;
		City clicked = City.cityClicked(exClick, whyClick);
		
		if (clicked!=null) 
			checkCityClickedOptions(pawn, clicked);
				
		else //if you didn't click on a city, what else can you do?
			checkAlternateOptions(exClick,whyClick);
		
		checkWin(); // have you won yet?
		checkEradicate(); //have you eradicated any disease?
			
		/*if(pawn.getMoves()>=pawn.getMovesPerTurn())
			cycle(currentPawn); //finish a single turn*/
		
	}
	
	public void checkCityClickedOptions(Pawn pawn, City clicked)
	{
		if(grant) 
			grant(pawn, clicked);
		else if(airlift)
			airlift(airliftPlayer, clicked);
		else if(remoteTreatment)
			removeCube(clicked);
		else if(rapidVaccine)
			rapidVaccine(clicked);
		else if(operationsFlight)
			operationsFlight(clicked);
		/*else if(operationsBuild)
			operationsBuild(clicked);*/
		else if(pawn.inCity(clicked)&&!ButtonBox.getCharteringFlight())
			removeCube(clicked);
			
		else if(pawn.getLocation().getNeighbors().contains(clicked)||pawn.getLocation().getCrossNeighbors().contains(clicked)
				&&!ButtonBox.getCharteringFlight())
		{
			moveToCity(pawn, clicked);
			if(mobile)
				removeCube(clicked);
			if(pawn.getRole().equals("Medic"))
				medicRemoveCube(clicked);
			if(pawn.getRole().equals("Containment Specialist"))
				containRemoveCube(clicked);
		}
		else if(clicked.getHasResearchCenter() && pawn.getLocation().getHasResearchCenter()
				&&!ButtonBox.getCharteringFlight())
		{
			shuttleFlightToCity(pawn, clicked);
			if(pawn.getRole().equals("Medic"))
				medicRemoveCube(clicked);
			if(pawn.getRole().equals("Containment Specialist"))
				containRemoveCube(clicked);
		}
		else if(ButtonBox.getCharteringFlight())
		{
			charteredFlightToCity(pawn,clicked);
			if(pawn.getRole().equals("Medic"))
				medicRemoveCube(clicked);
			if(pawn.getRole().equals("Containment Specialist"))
				containRemoveCube(clicked);
		}
	}
	
	
	public void checkAlternateOptions(int ex, int why) //did you click on a card or cure?
	{
		
		ArrayList<String> toRet = new ArrayList();
		for(int i=0; i<Pawn.getPawns().size();i++)
		{
			Pawn pawn= Pawn.getPawns().get(i);
			currentPawn = pawn;
			//Infection.checkDiscard(pawn); //does this pawn need to discard?
			currentCity = pawn.getLocation();
			
			if(pawn.getLocation().getHasResearchCenter() && pawn.getTurn()==true)
				checkCure(i); // check if you clicked on a cure and can actually cure it
			
			for(int k=0; k< pawn.getHandNames().size(); k++)
			{
				Card c = pawn.getMyHand().get(k);
				int x = 55+k*45;
				int y =10+55*i;
				Rectangle r = new Rectangle (x, y, 35, 50); //Rectangle where card is
				
				if(r.contains(exClick,whyClick) && pawn.getTurn() && pawn.getMoves()<pawn.getMovesPerTurn()) //if you clicked on a card in your own deck
				{
					currentInt=k;
					if(pawn.inCity(c))
					{
						toRet.add("Research Center");
						toRet.add("Chartered Flight");
						int numPlayers=0;
						for(int m=0; m<Pawn.getPawns().size();m++)
						{
							if(Pawn.getPawns().get(m).inCity(c) && 
									!Pawn.getPawns().get(m).equals(pawn))
							{
								toRet.add("Trade card with " + Pawn.getPawns().get(m).getRole());
							}
						}
								
					}
					else
					{
						for(int p=0; p<City.getCities().size();p++)
						{
							if(City.getCities().get(p).equals(c))
							{
								toRet.add("Direct Flight");
							}
						}
						for(int m=0; m<Pawn.getPawns().size();m++)
						{
							if(pawn.getRole().equals("Researcher") &&
								pawn.getLocation().equals(Pawn.getPawns().get(m).getLocation())
								&&!Pawn.getPawns().get(m).equals(pawn))
							{
								toRet.add("Trade card with " + Pawn.getPawns().get(m).getRole());
							}
						}
						
								
					}
					
					if(c.getType().equals("SpecialEvent")&&!c.getID().equals("RapidVaccineDeployment"))
					{
						toRet.add("Special Event: " + c.getID());
					}
					else if(c.getID().equals("RapidVaccineDeployment") && rapidVaccineCured!=null)
						toRet.add("Special Event: " + c.getID());
					
						
					resetClick();
					ScrollingBackground.drawGUI(toRet, pawn, k);
					popupUp=true;
					
						
						
				}
				else if (r.contains(exClick,whyClick)&&!pawn.getTurn()) // if you clicked on someone else's card
				{
					for(int m=0; m<Pawn.getPawns().size(); m++)
					{
						if(Pawn.getPawns().get(m).inCity(c)&& 
								!Pawn.getPawns().get(m).equals(pawn) &&
								pawn.inCity(c))
						{
							if(!toRet.contains("Steal card from "+ pawn.getRole()))
								toRet.add("Steal card from " + pawn.getRole());
						}
						
					}
					for(int m=0; m<Pawn.getPawns().size();m++)
					{
						if(pawn.getRole().equals("Researcher") &&
							pawn.getLocation().equals(Pawn.getPawns().get(m).getLocation()) &&
							!Pawn.getPawns().get(m).equals(pawn))
						{
							if(!toRet.contains("Steal card from "+ pawn.getRole()))
								toRet.add("Steal card from " + pawn.getRole());
						}
					}
					if(c.getType().equals("SpecialEvent")&&!c.getID().equals("RapidVaccineDeployment"))
					{
						toRet.add("Special Event: " + c.getID());
					}
					else if(c.getID().equals("RapidVaccineDeployment") && (rapidVaccineCured!=null))
						toRet.add("Special Event: " + c.getID());
					resetClick();
					ScrollingBackground.drawGUI(toRet, pawn, k);
					popupUp=true;
				
				}
				
				/*if(pawn.getTurn()&&pawn.getMoves()==pawn.getMovesPerTurn())
				{
					cycle(i);
				}*/
					
			}
			
				
		}
		
		Rectangle specialMove = new Rectangle(930, 100, 100, 25);
		Rectangle instruction = new Rectangle(930, 50, 100, 25);
		Rectangle stats= new Rectangle(930, 200, 100, 25);
		if(specialMove.contains(exClick, whyClick))
		{
			specialMove();
			resetClick();
			popupUp=true;
		}
		if(instruction.contains(exClick, whyClick))
		{
			ScrollingBackground.drawInstructions();
			resetClick();
			popupUp=true;
			
		}
		if(stats.contains(exClick, whyClick))
		{
			ScrollingBackground.drawStats();
			resetClick();
			popupUp=true;
		}
	
	}

	
	
	public void specialEvent(Pawn pawn, int k)
	{
		Card card = pawn.getMyHand().remove(k);
		String shortName = card.getName().substring(0,card.getName().length()-4);
		System.out.println("You played the card: " + shortName);
		PlayerDeck.handDiscardCard(card);
		
		/*
		 * These aren't working at all
		 */
		
		/*
		 * Switch to buttonbox
		 */
		
		
		
		resetClick();
		/*
		 * Program each card individually
		 */
	}
	
	public void specialMove()
	{
		if(Pawn.whoseTurn().getRole().equals("Contingency Planner") && plannerStoredCard==null)
		{
			ArrayList<String> options = new ArrayList();
			for(int i=0; i<PlayerDeck.getDiscardDeck().size();i++)
			{
				if(PlayerDeck.getDiscardDeck().get(i).getType().equals("SpecialEvent"))
				{
					options.add("Store card: " + PlayerDeck.getDiscardDeck().get(i).getID());
				}
			}
			ScrollingBackground.drawLargeStartGUI("Contingency Planner Move", options);
		}
		if(Pawn.whoseTurn().getRole().equals("Contingency Planner") && plannerStoredCard!=null)
		{
			ArrayList<String>options = new ArrayList();
			options.add(("Special Event: " + plannerStoredCard.getID()));
			ScrollingBackground.drawTopGUI("Play stored card",options);
		}
		if(Pawn.whoseTurn().getRole().equals("Dispatcher"))
		{
			ArrayList<String>options = new ArrayList();
			options.add("Special Event: SpecialOrders");
			for(int i=0; i<Pawn.getPawns().size();i++)
			{
				for(int k=0; k<Pawn.getPawns().size();k++)
				{
					if(k!=i && !Pawn.getPawns().get(i).getLocation().equals(Pawn.getPawns().get(k).getLocation()))
						options.add("Dispatch Special Move p1 to p2: " + 
						Pawn.getPawns().get(i).getRole() +", " + Pawn.getPawns().get(k).getRole());
				}
			}
			ScrollingBackground.drawLargeStartGUI("Dispatch Special Move", options);
		}
		if(Pawn.whoseTurn().getRole().equals("Epidemiologist") && Pawn.whoseTurn().getSpecialMovesTaken()==0)
		{

			ArrayList<String> options = new ArrayList();
			for(int k=0; k<Pawn.getPawns().size(); k++)
			{
			
				if(Pawn.getPawns().get(k).getLocation().equals(Pawn.whoseTurn().getLocation()))
				{
					
					for(int i=0; i<Pawn.getPawns().get(k).getMyHand().size();i++)
					{
						if(!Pawn.getPawns().get(k).equals(Pawn.whoseTurn()))
						{
						options.add("Epidemiologist Move: Take " + 
								Pawn.getPawns().get(k).getMyHand().get(i).getID() +" from " + Pawn.getPawns().get(k).getRole());
						}
					}
					if(options.size()!=0)
					{
						ScrollingBackground.drawLargeStartGUI("Epidemiologist Special Move", options);
					}
				}
			}
		}
		if(Pawn.whoseTurn().getRole().equals("Operations Expert"))
		{
			ArrayList<String> options = new ArrayList();
			if(!Pawn.whoseTurn().getLocation().getHasResearchCenter())
			{
				options.add("No cost: build a research center here");
			}
			if(Pawn.whoseTurn().getSpecialMovesTaken()==0 &&Pawn.whoseTurn().getLocation().getHasResearchCenter())
			{
				options.add("Fly anywhere: Discard a card");
			}
			if(options.size()!=0)
			{
				ScrollingBackground.drawLargeStartGUI("Operations Expert Special Move", options);
			}
		}
		if(Pawn.whoseTurn().getMoves()==0 && Pawn.whoseTurn().getRole().equals("Troubleshooter"))
		{
			ArrayList<String> options = new ArrayList();
			options.add("Troubleshooter forecast");
			ScrollingBackground.drawStartGUI("Troubleshooter Special Move", options);
		}
		if(Pawn.whoseTurn().getRole().equals("Field Operative") && Pawn.whoseTurn().getSpecialMovesTaken()==0)
		{
			ArrayList<String> options = new ArrayList();
			if(Pawn.whoseTurn().getLocation().getBlackCubes()>0)
				options.add("Store black cube");
			if(Pawn.whoseTurn().getLocation().getBlueCubes()>0)
				options.add("Store blue cube");
			if(Pawn.whoseTurn().getLocation().getYellowCubes()>0)
				options.add("Store yellow cube");
			if(Pawn.whoseTurn().getLocation().getRedCubes()>0)
				options.add("Store red cube ");
			ScrollingBackground.drawStartGUI("Field Operative Special Move", options);
		}
		if(Pawn.whoseTurn().getRole().equals("Archivist"))
		{
			City city = Pawn.whoseTurn().getLocation();
			ArrayList<String>toRet = new ArrayList();
			Pawn pawn = Pawn.whoseTurn();
			for(int i=0; i < PlayerDeck.getDiscardDeck().size(); i++)
			{
				if(PlayerDeck.getDiscardDeck().get(i).equals(pawn.getLocation()) &&
						pawn.getRole().equals("Archivist"))
				{
					toRet.add("Archivist move: Pick up "+ pawn.getLocation().getName());
				}
			}
			ScrollingBackground.drawStartGUI("Archivist Special Move", toRet);
		}
	}
	
	public void tradeCard(Pawn pawn, int k, String otherPlayer)
	{
		Card card = pawn.getMyHand().remove(k);
		for(int i=0; i<Pawn.getPawns().size();i++)
		{
			if(Pawn.getPawns().get(i).getRole().equals(otherPlayer))
			{
				Pawn.getPawns().get(i).getMyHand().add(card);
				System.out.println("You trade the card " + card.getID() + " with " + Pawn.getPawns().get(i).getRole());
				
				break;
			}
		}
		pawn.addMove();
		resetClick();
	}
	
	public void stealCard(Pawn pawn, int k)
	/*
	 * pawn=pawn with card you want
	 * k = number of card
	 */
	{
		Card take = pawn.getMyHand().remove(k);
		Pawn going = Pawn.whoseTurn();
		System.out.println("You took the card " + take.getID() +" from "+ going.getRole());
		going.getMyHand().add(take);
		going.addMove();
		resetClick();
	}

	public int numColoredCards(Pawn pawn, Color color)
	{
		int num = 0;
		for (int i =0; i<pawn.getMyHand().size();i++)
		{
			if(pawn.getMyHand().get(i).getColor().equals(color))
				num++;
		}
		return num;
	}
	
	public void removeFive(Pawn pawn, Color color)
	{

		
		for(int i=0; i<pawn.getMyHand().size(); i++ )
		{
			if(pawn.getMyHand().get(i).getColor().equals(color))
			{
				cureDiscard.add("Discard towards cure: "+pawn.getMyHand().get(i).getID());
			}
			if(storedRed >=3 && color.equals(Color.RED))
				cureDiscard.add("Play three red cubes as two cards");
			if(storedBlack >=3 && color.equals(Color.BLACK))
				cureDiscard.add("Play three black cubes as two cards");
			if(storedBlue >=3 && color.equals(Color.BLUE))
				cureDiscard.add("Play three blue cubes as two cards");
			if(storedYellow >=3 && color.equals(Color.YELLOW))
				cureDiscard.add("Play three yellow cubes as two cards");
				
		}
		ScrollingBackground.drawTopGUI("Choose 1st discard", cureDiscard);
	}
	
	public void checkCure(int i)
	{
		Pawn pawn= Pawn.getPawns().get(i);
		City location = pawn.getLocation();
		String name = location.getName();
		
		Rectangle blue = new Rectangle(330, 600, 30, 50);
		Rectangle yellow = new Rectangle (402, 600, 30, 50);
		Rectangle black = new Rectangle(473, 600, 30, 50);
		Rectangle red = new Rectangle (544, 600, 30, 50);
		
		if(blue.contains(exClick,whyClick) && !blueCured && numColoredCards(pawn, Color.BLUE)>=Pawn.whoseTurn().getNumToCure())
		{
			blueCured=true;
			rapidVaccineCured=Color.BLUE;
			removeFive(pawn, Color.BLUE);
			resetClick();
			pawn.addMove();
			System.out.println("Blue cure found!");
		}
		if(red.contains(exClick,whyClick) && !redCured && numColoredCards(pawn, Color.RED)>=Pawn.whoseTurn().getNumToCure())
		{
			redCured=true;
			rapidVaccineCured=Color.RED;
			removeFive(pawn, Color.RED);
			resetClick();
			pawn.addMove();
			System.out.println("Red cure found!");
		}
		if(yellow.contains(exClick,whyClick) && !yellowCured && numColoredCards(pawn, Color.YELLOW)>=Pawn.whoseTurn().getNumToCure())
		{
			yellowCured=true;
			rapidVaccineCured=Color.YELLOW;
			removeFive(pawn, Color.YELLOW);
			resetClick();
			pawn.addMove();
			System.out.println("Yellow cure found!");
		}
		if(black.contains(exClick,whyClick) && !blackCured && numColoredCards(pawn, Color.BLACK)>=Pawn.whoseTurn().getNumToCure())
		{
			blackCured=true;
			rapidVaccineCured=Color.BLACK;
			removeFive(pawn, Color.BLACK);
			resetClick();
			pawn.addMove();
			System.out.println("Black cure found!");
		}
		
		
	}
	
	public void checkEradicate()
	{
		int numBlue=0;
		for(int i=0; i<City.getCities().size();i++)
		{
			if(City.getCities().get(i).getBlueCubes()!=0)
				numBlue++;
		}
		if(numBlue==0&&!blueEradicated&&blueCured)
		{
			blueEradicated=true;
			System.out.println("You have eradicated the blue strain");
		}
		
		int numYellow=0;
		for(int i=0; i<City.getCities().size();i++)
		{
			if(City.getCities().get(i).getYellowCubes()!=0)
				numYellow++;
		}
		if(numYellow==0&&!yellowEradicated&&yellowCured)
		{
			yellowEradicated=true;
			System.out.println("You have eradicated the yellow strain");
		}
		
		int numRed=0;
		for(int i=0; i<City.getCities().size();i++)
		{
			if(City.getCities().get(i).getRedCubes()!=0)
				numRed++;
				
		}
		if(numRed==0&&!redEradicated&&redCured)
		{
			redEradicated=true;
			System.out.println("You have eradicated the red strain");
		}
		
		int numBlack=0;
		for(int i=0; i<City.getCities().size();i++)
		{
			if(City.getCities().get(i).getBlackCubes()!=0)
				numBlack++;
				
		}
		if(numBlack==0&&!blackEradicated&&blackCured)
		{
			blackEradicated=true;
			System.out.println("You have eradicated the black strain");
		}
	}
	
	public void buildResearch(Pawn pawn, City city)
	{
		int spot = Card.containsCard(pawn.getMyHand(), pawn.getLocation().getName());
		PlayerDeck.handDiscardCard(pawn.getMyHand().remove(spot));
		city.setHasResearchCenter(true);
		System.out.println(pawn.getRole()+
				" built research center at: " + city.getName());
		pawn.addMove();
		resetClick();
	}
	
	public void grant(Pawn pawn, City city)
	{
		city.setHasResearchCenter(true);
		System.out.println(pawn.getRole()+
				" built research center at: " + city.getName());
		resetClick();
		grant=false;
	}
	
	public void airlift(Pawn pawn, City city)
	{
		pawn.setLocation(city);
		System.out.println(pawn.getRole() +" was moved to " + city.getName());
		resetClick();
		airliftPlayer=null;
		airlift=false;
	}
	
	public void operationsBuild(City city)
	{
		city.setHasResearchCenter(true);
		resetClick();
		Pawn.whoseTurn().addMove();
		System.out.println(Pawn.whoseTurn().getRole()+
				" built research center at: " + city.getName());
		operationsBuild=false;
	}
	public void operationsFlight(City city)
	{
		Pawn.whoseTurn().setLocation(city);
		resetClick();
		Pawn.whoseTurn().addMove();
		System.out.println(Pawn.whoseTurn().getRole() +" was moved to " + city.getName());
		operationsFlight=false;
		
	}
	
	public void rapidVaccine(City city)
	{
		if(rapidVaccineCenter==null && city.getColor().equals(rapidVaccineCured))
		{
			rapidVaccineCenter=city;
			if(rapidVaccineCured.equals(Color.BLUE))
				removeBlueCube(city);
			if(rapidVaccineCured.equals(Color.RED))
				removeRedCube(city);
			if(rapidVaccineCured.equals(Color.BLACK))
				removeBlackCube(city);
			if(rapidVaccineCured.equals(Color.YELLOW))
				removeYellowCube(city);
		}
		else if (city.getColor().equals(rapidVaccineCured)&&
				(rapidVaccineCenter.getNeighbors().contains(city)||
				rapidVaccineCenter.getCrossNeighbors().contains(city) ||
				rapidVaccineCenter.equals(city)))
		{
			if(city.equals(Color.BLUE))
				removeBlueCube(city);
			if(city.equals(Color.RED))
				removeRedCube(city);
			if(city.equals(Color.BLACK))
				removeBlackCube(city);
			if(city.equals(Color.YELLOW))
				removeYellowCube(city);
			/*
			 * adjacent cities
			 */
		}
	}
	
	public void directFlightToCity(Pawn pawn, City city)
	{
		if(pawn.getRole().equals("Medic"))
			medicRemoveCube(city);
		if(pawn.getRole().equals("Containment Specialist"))
			containRemoveCube(city);
		int spot = Card.containsCard(pawn.getMyHand(), city.getName());
		if(!pawn.getRole().equals("Troubleshooter"))
			PlayerDeck.handDiscardCard(pawn.getMyHand().remove(spot));
		pawn.setLocation(city);
		System.out.println(pawn.getRole()+ " took a direct flight to: " + city.getName());
		pawn.addMove();
		resetClick();
		
	}
	public void reexaminedResearch(Pawn pawn, String string)
	{
		for(int i=0; i<PlayerDeck.getDiscardDeck().size();i++)
		{
			if(string.equals(PlayerDeck.getDiscardDeck().get(i).getID()))
				pawn.getMyHand().add(PlayerDeck.getDiscardDeck().remove(i));
		}
	}
	
	public void shuttleFlightToCity(Pawn pawn, City city)
	{
		pawn.setLocation(city);
		pawn.addMove();
		System.out.println(pawn.getRole() + " took a shuttle flight to " + city.getName());
		resetClick();
		
	}
	
	public void charteredFlightToCity(Pawn pawn, City city)
	{
		int spot = Card.containsCard(pawn.getMyHand(), pawn.getLocation().getName());
		PlayerDeck.handDiscardCard(pawn.getMyHand().remove(spot));
		pawn.setLocation(city);
		System.out.println(pawn.getRole()+ " chartered a flight to: " + city.getName());
		pawn.addMove();
		resetClick();
		ButtonBox.setCharteringFlight(false);
	}
	
	public void moveToCity(Pawn pawn, City city)
	{
		pawn.setLocation(city);
		pawn.addMove();
		System.out.println(pawn.getRole() + " moved to " + city.getName());
		resetClick();
	
	}
	
	public static void removeBlueCube(City city)
	{
		if((blueCured|| Pawn.whoseTurn().getRole().equals("Medic")) && !rapidVaccine && !remoteTreatment)
		{
			city.setBlueCubes(0);
			System.out.println("You have cleared out " + city.getName());
			Pawn.whoseTurn().addMove();
		}
		else
			if(city.getBlueCubes()>0)
			{
				city.addCubes(-1,Color.BLUE);
		    	System.out.println(Pawn.whoseTurn().getRole()+
						" removed a cube from: " + city.getName());
		    	Pawn.whoseTurn().addMove();
			}
		if(mobile)
			Pawn.whoseTurn().setMoves(Pawn.whoseTurn().getMoves()-1);
		if(remoteTreatment)
		{
			Pawn.whoseTurn().setMoves(Pawn.whoseTurn().getMoves()-1);
			remoteTreatmentCured++;
		}
		if(rapidVaccine)
		{
			Pawn.whoseTurn().setMoves(Pawn.whoseTurn().getMoves()-1);
			rapidVaccineNumCured++;
		}
		if(remoteTreatmentCured>=2)
		{
			remoteTreatment=false;
			remoteTreatmentCured=0;
			ScrollingBackground.drawTimedMessageGUI("Continue playing!");
		}
		if(rapidVaccineNumCured>=5 || (rapidVaccineCenter!=null && rapidVaccineCenter.cityCleared(Color.BLUE))) //||doesn't have cubes around it
		{
			rapidVaccine=false;
			rapidVaccineCured=null;
			rapidVaccineCenter=null;
			rapidVaccineNumCured=0;
			ScrollingBackground.drawTimedMessageGUI("Continue playing");
		}
	}
	
	public static void removeYellowCube(City city)
	{
		if((yellowCured || Pawn.whoseTurn().getRole().equals("Medic")) && !rapidVaccine&& !remoteTreatment)
		{
			city.setYellowCubes(0);
			System.out.println("You have cleared out " + city.getName());
			Pawn.whoseTurn().addMove();
		}
		else
			if(city.getYellowCubes()>0)
			{
				city.addCubes(-1,Color.YELLOW);
		    	System.out.println(Pawn.whoseTurn().getRole()+
						" removed a cube from: " + city.getName());
		    	Pawn.whoseTurn().addMove();
			}
		if(mobile)
			Pawn.whoseTurn().setMoves(Pawn.whoseTurn().getMoves()-1);
		if(remoteTreatment)
		{
			Pawn.whoseTurn().setMoves(Pawn.whoseTurn().getMoves()-1);
			remoteTreatmentCured++;
		}
		if(rapidVaccine)
		{
			Pawn.whoseTurn().setMoves(Pawn.whoseTurn().getMoves()-1);
			rapidVaccineNumCured++;
		}
		if(remoteTreatmentCured>=2)
		{
			remoteTreatment=false;
			remoteTreatmentCured=0;
			ScrollingBackground.drawTimedMessageGUI("Continue playing!");
		}
		if(rapidVaccineNumCured>=5|| (rapidVaccineCenter!=null && rapidVaccineCenter.cityCleared(Color.YELLOW)))
		{
			rapidVaccine=false;
			rapidVaccineCured=null;
			rapidVaccineCenter=null;
			rapidVaccineNumCured=0;
			ScrollingBackground.drawTimedMessageGUI("Continue playing");
		}
	}
	
	public static void removeRedCube(City city)
	{
		if((redCured|| Pawn.whoseTurn().getRole().equals("Medic")) && !rapidVaccine&& !remoteTreatment)
		{
			city.setRedCubes(0);
			System.out.println("You have cleared out " + city.getName());
			Pawn.whoseTurn().addMove();
		}
		else
			if(city.getRedCubes()>0)
			{
				city.addCubes(-1,Color.RED);
		    	System.out.println(Pawn.whoseTurn().getRole()+
						" removed a cube from: " + city.getName());
		    	Pawn.whoseTurn().addMove();
			}
		if(mobile)
			Pawn.whoseTurn().setMoves(Pawn.whoseTurn().getMoves()-1);
		if(remoteTreatment)
		{
			Pawn.whoseTurn().setMoves(Pawn.whoseTurn().getMoves()-1);
			remoteTreatmentCured++;
		}
		if(rapidVaccine)
		{
			Pawn.whoseTurn().setMoves(Pawn.whoseTurn().getMoves()-1);
			rapidVaccineNumCured++;
		}
		if(remoteTreatmentCured>=2)
		{
			remoteTreatment=false;
			remoteTreatmentCured=0;
			ScrollingBackground.drawTimedMessageGUI("Continue playing!");
		}
		if(rapidVaccineNumCured>=5|| (rapidVaccineCenter!=null && rapidVaccineCenter.cityCleared(Color.RED)))
		{
			rapidVaccine=false;
			rapidVaccineCured=null;
			rapidVaccineCenter=null;
			rapidVaccineNumCured=0;
			ScrollingBackground.drawTimedMessageGUI("Continue playing");
		}
	}
	
	public static void removeBlackCube(City city)
	{
		if((blackCured|| Pawn.whoseTurn().getRole().equals("Medic")) && !rapidVaccine&& !remoteTreatment)
		{
			city.setBlackCubes(0);
			System.out.println("You have cleared out " + city.getName());
			Pawn.whoseTurn().addMove();
		}
		else
			if(city.getBlackCubes()>0)
			{
				city.addCubes(-1,Color.BLACK);
		    	System.out.println(Pawn.whoseTurn().getRole()+
						" removed a cube from: " + city.getName());
		    	Pawn.whoseTurn().addMove();
			}
		if(mobile)
			Pawn.whoseTurn().setMoves(Pawn.whoseTurn().getMoves()-1);
		if(remoteTreatment)
		{
			Pawn.whoseTurn().setMoves(Pawn.whoseTurn().getMoves()-1);
			remoteTreatmentCured++;
		}
		if(rapidVaccine)
		{
			Pawn.whoseTurn().setMoves(Pawn.whoseTurn().getMoves()-1);
			rapidVaccineNumCured++;
		}
		if(remoteTreatmentCured>=2)
		{
			remoteTreatment=false;
			remoteTreatmentCured=0;
			ScrollingBackground.drawTimedMessageGUI("Continue playing!");
		}
		if(rapidVaccineNumCured>=5|| (rapidVaccineCenter!=null && rapidVaccineCenter.cityCleared(Color.BLACK)))
		{
			rapidVaccine=false;
			rapidVaccineCured=null;
			rapidVaccineCenter=null;
			rapidVaccineNumCured=0;
			ScrollingBackground.drawTimedMessageGUI("Continue playing");
		}
	}
	public void medicRemoveCube(City city)
	{
		if(city.getColor().equals(Color.BLACK) && blackCured)
			city.setBlackCubes(0);
		if(city.getColor().equals(Color.BLUE) && blueCured)
			city.setBlueCubes(0);
		if(city.getColor().equals(Color.YELLOW) && yellowCured)
			city.setYellowCubes(0);
		if(city.getColor().equals(Color.RED) && redCured)
			city.setRedCubes(0);
	    	
	}
	
	
	public void removeCube(City city)
	{
		ArrayList <String> toRet= new ArrayList();
		if(city.getBlackCubes()!=0)
			toRet.add("Remove black cube");
		if(city.getRedCubes()!=0)
			toRet.add("Remove red cube");
		if(city.getBlueCubes()!=0)
			toRet.add("Remove blue cube");
		if(city.getYellowCubes()!=0)
			toRet.add("Remove yellow cube");
		
		
		if(toRet.size()==1)
		{
			if(city.getBlackCubes()!=0)
				removeBlackCube(city);
			if(city.getBlueCubes()!=0)
				removeBlueCube(city);
			if(city.getYellowCubes()!=0)
				removeYellowCube(city);
			if(city.getRedCubes()!=0)
				removeRedCube(city);
		}
		
		/*
		 * For archivist, specialist role
		 */
		
		else if (toRet.size()>1)
		{
			ScrollingBackground.drawStartGUI(city.getName(), toRet);
			popupUp=true;
		}
		
		resetClick();
	    	
	}
	
	public void containRemoveCube(City city)
	{
		ArrayList <String> toRet= new ArrayList();
		if(city.getBlackCubes()>=2)
		{
			removeCube(city);
			Pawn.whoseTurn().setMoves(Pawn.whoseTurn().getMoves()-1);
		}
		if(city.getRedCubes()>=2)
		{
			removeCube(city);
			Pawn.whoseTurn().setMoves(Pawn.whoseTurn().getMoves()-1);
		}
		if(city.getBlueCubes()>=2)
		{
			removeCube(city);
			Pawn.whoseTurn().setMoves(Pawn.whoseTurn().getMoves()-1);
		}
		if(city.getYellowCubes()>=2)
		{
			removeCube(city);
			Pawn.whoseTurn().setMoves(Pawn.whoseTurn().getMoves()-1);
		}
		
		resetClick();
	    	
	}
	
	
	
	public void archivist(Pawn pawn, String string)
	{
		for(int i=0; i < PlayerDeck.getDiscardDeck().size(); i++)
		{
			if(PlayerDeck.getDiscardDeck().get(i).getID().equals(string))
			{
				pawn.getMyHand().add(PlayerDeck.getDiscardDeck().remove(i));
				pawn.addMove();
			}
		}
		resetClick();
		/*
		 * Not working!!
		 */
	}
	
	
	public void discardCard(Pawn pawn, int k)
	{
		Card card = pawn.getMyHand().remove(k);
		System.out.println("You discarded " + card.getID());
		PlayerDeck.handDiscardCard(card);
		resetClick();
	}
	
	public boolean canInfect(int x)
	{
		boolean infect = true;
		if((blackEradicated || (City.getCities().get(x).hasPlayer("Medic") && blackCured))
				&& City.getCities().get(x).getColor().equals(Color.BLACK)){infect=false;}
		if((blueEradicated || (City.getCities().get(x).hasPlayer("Medic") && blueCured))
				&& City.getCities().get(x).getColor().equals(Color.BLUE)){infect=false;}
		if((yellowEradicated || (City.getCities().get(x).hasPlayer("Medic") && yellowCured))
				&& City.getCities().get(x).getColor().equals(Color.YELLOW)){infect=false;}
		if((redEradicated || (City.getCities().get(x).hasPlayer("Medic") && redCured))
				&& City.getCities().get(x).getColor().equals(Color.RED)){infect=false;}
		if(City.getCities().get(x).cityQuarantined())
			infect=false;
		if(!infect)
			System.out.println("Can't infect: " + City.getCities().get(x).getName());
		return infect;
	}
	
	
	/*
	 * draw player cards
	 */
	
	/*
	 * SOMETHING IS HAPPENING WITH THE EPIDEMIC CARDS -- THE GAME IS WAY TOO HARD
	 * ASK STRIMPLE ABOUT RULES OF EPIDEMIC AGAIN -- CAN A CITY BE HIT MORE THAN ONCE?
	 */
	public void drawPlayerCards(int j)
	{
		for(int i=0; i<2; i++)
		{
				
			Card c = new Card(City.getCities().get(0));
			try 
			{
				c = PlayerDeck.infectTop2(Pawn.getPawns().get(j).getMyHand(), PlayerDeck.getPlayerDeck());
			}
			catch(Exception e) 
			{
				System.out.println ("YOU LOSE: RAN OUT OF PLAYER CARDS!");
				ScrollingBackground.drawLoseTimedGUI("You lose: Ran out of player cards");
				lost=true;
			}
			System.out.println("You drew the Player card: " + c.getID());
			if(c.getID().equals("Epidemic"))
			{
				Pawn.getPawns().get(j).getMyHand().remove(c);
				PlayerDeck.normalDiscardCard(c);
				increaseInfectionRate();
				Card temp = InfectionDeck.infectBottom(Card.getInfectionDiscarding(), 
						InfectionDeck.getInfectionDeck()); //InfectionDiscardDeck^^
				//InfectionDeck.discardCard(c);
				c.setDiscard(true);
				for(int n=0; n<City.getCities().size(); n++)
				{
					if(City.getCities().get(n).getName().equals(temp.getID())&&canInfect(n))
					{
							boolean hasNoCubes;
							if(temp.getColor().equals(Color.BLUE))
							{
								int numBlue=City.getCities().get(n).getBlueCubes();
								City.getCities().get(n).setBlueCubes(3);
								if(!(numBlue==0))
									City.getCities().get(n).addCubes(1,Color.BLUE);
							}
							if(temp.getColor().equals(Color.YELLOW))
							{
								int numYellow = City.getCities().get(n).getYellowCubes();
								City.getCities().get(n).setYellowCubes(3);
								if(!(numYellow==0))
									City.getCities().get(n).addCubes(1,Color.YELLOW);
							}
							if(temp.getColor().equals(Color.BLACK))
							{
								int numBlack = City.getCities().get(n).getBlackCubes();
								City.getCities().get(n).setBlackCubes(3);
								if(!(numBlack==0))
									City.getCities().get(n).addCubes(1,Color.BLACK);
							}
							if(temp.getColor().equals(Color.RED))
							{
								int numRed=City.getCities().get(n).getRedCubes();
								City.getCities().get(n).setRedCubes(3);
								if(!(numRed==0))
									City.getCities().get(n).addCubes(1,Color.RED);
							}
					}
				}
				for(int m=0; m<InfectionDeck.getInfectionDiscardDeck().size();m++)
				{
					Card card = InfectionDeck.getInfectionDiscardDeck().get(m);
					card.getCardImage().setX(590*Background.getResize()/2);
					card.getCardImage().setY(50*Background.getResize()/2);
				}
				InfectionDeck.shuffle(InfectionDeck.getInfectionDiscardDeck(), 
						InfectionDeck.getInfectionDeck());
				}
			}
	}
	

	
	/*
	 * Draw infection cards according to infection rate
	 */
	
	public void drawInfectionCards(int j)
	{
		int k=0;
		if(travelBan)
			k=1;
		else if (quietNight)
			k=0;
		else
			k=infectionRate;
		
		for(int i=0; i<k;i++)
		{
			
			Card c = InfectionDeck.infectTop(Card.getInfectionDiscarding(), 
					InfectionDeck.getInfectionDeck()); //InfectionDiscardDeck^^
			//InfectionDeck.discardCard(c);
			c.setDiscard(true);
			System.out.println("You drew the Infection card: " + c.getID());
			for(int ex=0; ex<City.getCities().size();ex++)
			{
				if(City.getCities().get(ex).getName().equals(c.getID()) && canInfect(ex))
				{
					City.getCities().get(ex).addCubes(1,City.getCities().get(ex).getColor());
					
				}
			}
		}
	}

	public void endTurn(int j)
	
	{
		drawPlayerCards(j);
		drawInfectionCards(j);
		Pawn.getPawns().get(j).checkDiscard(j); 
		
	}
	
	public Pawn getCurrentPawn(){return currentPawn;}
	public City getCurrentCity() { return currentCity;}
	public int getCurrentInt() { return currentInt;}
	public static Pawn getStoredPlayer() {
		// TODO Auto-generated method stub
		return storedPlayer;
	}
	
	
}
