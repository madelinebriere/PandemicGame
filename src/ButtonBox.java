import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

class ButtonBox extends JFrame implements ActionListener
{
  JButton bChange ; // reference to the button object
  static boolean researchCenter=false;
  static boolean charteredFlight=false;
  static boolean discard = false;
  static boolean neededDiscard = false;
  static boolean specialEvent = false;
  static boolean buttonClicked=false;
  static boolean charteringFlight=false;
  
  public static boolean getResearch(){return researchCenter;}
  public static boolean getChartered(){return charteredFlight;}
  public static boolean getDiscard() { return discard;}
  public static boolean getNeededDiscard() { return neededDiscard;}
  public static boolean getSpecial() { return specialEvent;}
  public static boolean getClicked() { return buttonClicked;}
  public static void setResearch(boolean c){researchCenter=c;}
  public static void setChartered(boolean c){charteredFlight=c;}
  public static void setDiscard(boolean c){discard=c;}
  public static void setNeededDiscard(boolean c){neededDiscard=c;}
  public static void setSpecial(boolean c){specialEvent=c;}
  public static void setClicked(boolean c){buttonClicked=c;}
  
  public Pawn myPawn;
  public int myInt;
  public int myCurrentPlayer=1;
  
  public static void setCharteringFlight(boolean c){charteringFlight=c;}
  public static boolean getCharteringFlight(){return charteringFlight;}
  // constructor for ButtonFrame
  
  public  boolean perm;
  public void setPerm(boolean c) {perm=c;}
  public  boolean getPerm(){return perm;}
  
  public static ArrayList<Card> topSix = new ArrayList<Card>();

  

 ButtonBox(String announcement)
 {
	 super();  
	 setLayout(new GridLayout()); 
	 setTitle(announcement);
	 setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  
	 bChange = new JButton(announcement);
	 add(bChange);
	 WindowListener exitListener = new WindowAdapter() {

	      
	    	@Override
	        public void windowClosing(WindowEvent e) {
		        {
		               setVisible(false);
		               Infection.setPop(false);
	            }
	        }
	    };
	    addWindowListener(exitListener);
 }
 
 ButtonBox(String announcement, String imageRoute)
 {
	 super();  
	 setLayout(new GridLayout()); 
	 setTitle(announcement);
	 setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  
	 bChange = new JButton(announcement);
	 add(bChange);
	 WindowListener exitListener = new WindowAdapter() {

	      
	    	@Override
	        public void windowClosing(WindowEvent e) {
		        {
		               setVisible(false);
		               Infection.setPop(false);
	            }
	        }
	    };
	    addWindowListener(exitListener);
 }
 
  ButtonBox()
 {
	super();
	setAlwaysOnTop(true);
	setLayout( new FlowLayout() );   
	setTitle("Instructions");
	setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE); 
    /*
     * Make image later
     */

    JButton b1 = new JButton("1. Goal: Discover all of the cures -- blue, black, red and yellow");
    add(b1);
    
    JButton b2 = new JButton("2. Treat: Help a city you are in (remove a cube) by clicking on that city on the map");
    add(b2);
    
    JButton b3 = new JButton("3. Drive: Move to an adjacent city (connected by a line) by clicking on that city on the map");
    add(b3);
    
    JButton b4 = new JButton("4. Direct Flight: Move from any city to cityX by clicking on the cityX card in your player hand and discarding it");
    add(b4);
    
    JButton b5 = new JButton("5. Chartered Flight: Move from cityZ to any city by clicking on the cityZ card in your player hand and discarding it");
    add(b5);
    
    JButton b6 = new JButton("6. Share: Take/give the cityX card from/to another player when you are both in cityX by clicking on the cityX card in their/your player hand");
    add(b6);
    
    JButton b7 = new JButton ("7. Build: Place a research center in cityX when you are in cityX by clicking on the cityX card in your player hand");
    add(b7);
    
    JButton b8 = new JButton ("8. Cure: Cure a disease of a certain color (when located at a research center "
    		+ "and holding five player cards of that color) by clicking on that color cure vial on the bottom of the board");
    add(b8);
    
    JButton b9 = new JButton ("9. Research center flight: Move from one research center to another by clicking on the research center city you wish to travel to on the map");
    add(b9);	
    
    JButton b10 = new JButton("10. Eradicate: If the disease is cured, and there are no cubes of that color on the board, all the infection "
    		+ "cards of that color are ignored");
    add(b10);
    
    JButton b11 = new JButton("11. Special Event: Play a special event card at any time by clicking on that card in your own or in another player's hand");
    add(b11);
    
    JButton b12 = new JButton("12. Special Skill: Use your role's special skill (if it is not automatic or given as an option when you click on"
    		+ " the related card or city) by clicking on the 'Special Move' button");
    add(b12);
 
    
    
    JButton b = new JButton("CLICK HERE TO CONTINUE");
    b.setActionCommand("Click Here To Continue");
    b.addActionListener(this);
    add(b);
    setVisible(true);
    WindowListener exitListener = new WindowAdapter() {

        
    	@Override
        public void windowClosing(WindowEvent e) {
	        {
	               setVisible(false);
	               Infection.setPop(false);
            }
        }
    };
    addWindowListener(exitListener);
  
 }

 
  ButtonBox(ArrayList<String> names, Pawn pawn, int n) //shows options (names) for pawn when you click on card n
  {
    super();  
    myPawn=pawn;
    myInt=n;
    setLayout( new FlowLayout() );      
    
    for(int i=0; i<names.size();i++)
    {
    	 bChange = new JButton(names.get(i));
    	 bChange.setActionCommand(names.get(i));
    	 bChange.addActionListener(this);
    	    add( bChange );  
    }         
    setTitle(pawn.getHandNames().get(n));
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  
    WindowListener exitListener = new WindowAdapter() {

      
    	@Override
        public void windowClosing(WindowEvent e) {
	        {
	               setVisible(false);
	               Infection.setPop(false);
            }
        }
    };
    addWindowListener(exitListener);
  }
  
  ButtonBox(ArrayList<String> options, String announcement) 
  {
    super();
    setLayout( new FlowLayout() );      
    
    for(int i=0; i<options.size();i++)
    {
    	 bChange = new JButton(options.get(i));
    	 bChange.setActionCommand(options.get(i));
    	 bChange.addActionListener(this);
    	    add( bChange );  
    }         
    setTitle(announcement);
    setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE);  
    WindowListener exitListener = new WindowAdapter() {

        
    	@Override
        public void windowClosing(WindowEvent e) {
	        {
	               setVisible(false);
	               Infection.setPop(false);
            }
        }
    };
    addWindowListener(exitListener);
  }
  
  ButtonBox(ArrayList<String> options, String announcement, boolean bogus) //none exitable
  {
    super();
    setLayout( new FlowLayout() );      
    
    for(int i=0; i<options.size();i++)
    {
    	 bChange = new JButton(options.get(i));
    	 bChange.setActionCommand(options.get(i));
    	 bChange.addActionListener(this);
    	    add( bChange );  
    }         
    setTitle(announcement);
    setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE);  
  }
 
  
 



@Override
	public void actionPerformed(ActionEvent a) 
	{
		
		String action = a.getActionCommand();	
	
		setVisible(false);
		buttonClicked=true;
		Infection.setPop(false);
		Infection i = new Infection();
		String temp="";
		try
		{
			temp = action.substring(0,9);
		}
		catch(Exception e){}
	
		
		if (action.equals("Yes, end my turn"))
		{
			int ex=0;
			for(int k=0; k<Pawn.getPawns().size();k++)
			{
				if(Pawn.getPawns().get(k).equals(Pawn.whoseTurn()))
				{
					ex=k;
				}
			}
			Infection in =new Infection();
			in.cycle(ex);
		}
		/*
		 * Instructions
		 */
		if(action.equals("Return to level selection"))
		{
			ArrayList<String> nums=new ArrayList();
			for(int k=2; k<6; k++)
			{
				nums.add(""+k +" Players");
			}
			nums.add("Return to level selection");
			ScrollingBackground.drawTopGUI("Choose number of players", nums);
			ArrayList<String>levels = new ArrayList();
			levels.add("Introductory: 4");
			levels.add("Standard: 5");
			levels.add("Heroic: 6");
			ScrollingBackground.drawTopGUI("Choose your level", levels);
			
		}
		if(action.equals("Click Here To Continue"))
		{
			
		}
		
		/*
		 * Start GUI -- Levels
		 */
		if(action.equals("Introductory: 4"))
		{
			PlayerDeck.setDiff(4);
		}
		if(action.equals("Standard: 5"))
		{
			PlayerDeck.setDiff(5);
		}
		if(action.equals("Heroic: 6"))
		{
			PlayerDeck.setDiff(6);
		}
		
		
		/*
		 * Start GUI -players
		 */
		if(action.equals("2 Players"))
		{
			Pawn.setNumPlayers(2);
			//PlayerDeck.populatePlayerDeck();
			//Background.setStartDone(true);
		}
		if(action.equals("3 Players"))
		{
			Pawn.setNumPlayers(3);
			//PlayerDeck.populatePlayerDeck();
			//Background.setStartDone(true);
		}
		if(action.equals("4 Players"))
		{
			Pawn.setNumPlayers(4);
			//PlayerDeck.populatePlayerDeck();
			//Background.setStartDone(true);
		}
		if(action.equals("5 Players"))
		{
			Pawn.setNumPlayers(5);
			//PlayerDeck.populatePlayerDeck();
			//Background.setStartDone(true);
		}
		
		for(int k=0; k<Pawn.getRoles().size(); k++)
		{
			if(action.equals(Pawn.getRoles().get(k)))
			{
				Pawn.addNewPlayer(Pawn.getRoles().get(k));
			}
				
		}
		
		if(action.equals("See player details"))
		{
			Background.setDrawRoles(true);
		}
		
		if(action.equals("Click to look at infection discard deck"))
		{
			ArrayList<String>discard = new ArrayList();
			discard.add("Most recent card: " + InfectionDeck.getInfectionDiscardDeck().get(InfectionDeck.getInfectionDiscardDeck().size()-1).getID());
			for(int k=1; k<InfectionDeck.getInfectionDiscardDeck().size()-1; k++)
			{
				discard.add("Card " + (k+1) + ". " + InfectionDeck.getInfectionDiscardDeck().get(k).getID());
			}
			discard.add("Bottom card: " + InfectionDeck.getInfectionDiscardDeck().get(0).getID());
			ScrollingBackground.drawLargeStartGUI("Discarded cards", discard);
		}
		
		
		if(Pawn.getNumPlayers()!=Pawn.getPawns().size())
			ScrollingBackground.drawRoleGUI("Choose a role: Player "+ (Pawn.getPawns().size()+1), Pawn.getRoles());
		else if(Pawn.getNumPlayers()!=0 && Background.getStartDone()==false)
		{
			PlayerDeck.populatePlayerDeck();
			Background.setStartDone(true);
		}
		/*
		 * Contingency Planner -- FINISH
		 * Placed for convenience
		 */
		if(temp.equals("Store car"))
		{
			for(int i1=0; i1<PlayerDeck.getDiscardDeck().size();i1++)
			{
				if(PlayerDeck.getDiscardDeck().get(i1).getID().equals(action.substring(12)))
				{
					Infection.setPlannerStoredCard(PlayerDeck.getDiscardDeck().get(i1));
					PlayerDeck.getDiscardDeck().remove(i1);
				}
			}
			Pawn.whoseTurn().addMove();
		}
		boolean contingencyTemp = false;
		if(temp.equals("Special E") && Infection.getPlannerStoredCard()!=null &&
				action.substring(15).equals(Infection.getPlannerStoredCard().getID()))
		{
			Pawn.whoseTurn().getMyHand().add(Infection.getPlannerStoredCard());
			Infection.setPlannerStoredCard(null);
			contingencyTemp=true;
		} 
		
		/*
		 * Ensure that game continues
		 */
		
	
		/*
		 * Special Events
		 */
		
		if(action.equals("Special Event: Airlift"))
		{

			ArrayList<String>toRet= new ArrayList();
			for(int k=0; k<Pawn.getPawns().size();k++)
			{
				if(!Pawn.getPawns().get(k).equals(Pawn.whoseTurn()))
				{
						toRet.add("Control: " + Pawn.getPawns().get(k).getRole());
				}
			}
			ScrollingBackground.drawStartGUI("Choose a pawn", toRet);
			Infection.setAirlift(true);
		    /*
		     * Working
		     */
		  
		}
	

		if(action.equals("Special Event: BorrowedTime"))
		{
			Pawn.whoseTurn().addMovesPerTurn(2);
			Infection.setTime(true);
			/*
			 * Working
			 */
		}
		
		if(action.equals("Special Event: CommercialTravelBan"))
		{
			
			Infection.setBan(true);
			Infection.setBannedPlayer(Pawn.whoseTurn());
			/*
			 * Working
			 */
		}
		
		if(action.equals("Special Event: Forecast"))
		{
			ArrayList <Card> discard = InfectionDeck.getInfectionDiscardDeck();
			int index=6;
			if(!(discard.size()>=6))
				index=discard.size();
			for(int k=0; k<index; k++)
			{
				topSix.add(discard.get(discard.size()-1));
				discard.remove(discard.size()-1);
			}
			ArrayList<String>toRet=new ArrayList();
			for(int k=0; k<topSix.size();k++)
			{
				toRet.add("Put down " + topSix.get(k).getID());
			}
			ScrollingBackground.drawTopGUI("Choose forecasted card to put back down", toRet);
			/*
			 * Working
			 */
		}
		
		if(action.equals("Special Event: GovernmentGrant"))
		{
			
			Infection.setGrant(true);
			ScrollingBackground.drawTimedMessageGUI("Government Grant: Choose a city to build on");
		    /*
		     * Working
		     */
		}
		if(action.equals("Special Event: MobileHospital"))
		{
			
			Infection.setMobile(true);
			/*
			 * Working
			 */
		
		}
		if(action.equals("Special Event: NewAssignment"))
		{
			ArrayList <String >choices = new ArrayList();
			for(int k=0; k<Pawn.getRoles().size(); k++)
			{
				choices.add("New role: " + Pawn.getRoles().get(k));
			}
			ScrollingBackground.drawTopGUI("New Assignment: Choose a new role", choices);
			System.out.println("HIT");
			/*
			 * Working
			 */
		}
		
		if(action.equals("Special Event: RapidVaccineDeployment"))
		{
			Infection.setRapidVaccine(true);
			ScrollingBackground.drawTimedMessageGUI("Vaccine Deployment: Choose 5 adjacent cubes");
			
			/*
			 * Fix
			 */
			
		}
		
		if(action.equals("Special Event: RemoteTreatment"))
		{
			Infection.setRemoteTreatment(true);
			ScrollingBackground.drawTimedMessageGUI("Remote Treatment: Choose two cubes!");
			/*
			 * Working
			 */
		}
		
		if(action.equals("Special Event: ResilientPopulation"))
		{
			ArrayList <String> toRet = new ArrayList();
			for(int k=0; k<InfectionDeck.getInfectionDeck().size();k++)
			{
				toRet.add("Remove -- " + InfectionDeck.getInfectionDeck().get(k).getID());
			}
			ScrollingBackground.drawExtraLargeTopGUI("Resilient Population: Choose a card to remove", toRet);
			/*
			 * Working (at least graphically)
			 */
		}
		if(action.equals("Special Event: SpecialOrders"))
		{
			ArrayList<String>toRet = new ArrayList();
			for(int k=0; k< Pawn.getPawns().size(); k++)
			{
				if(!Pawn.getPawns().get(k).equals(Pawn.whoseTurn()))
					toRet.add("Special Order: " + Pawn.getPawns().get(k).getRole());
			}
			ScrollingBackground.drawTopGUI("Special Order: Choose a player", toRet);
			/*
			 * Working
			 */
		}
		

		if(action.equals("Special Event: OneQuietNight"))
		{
			
			Infection.setQuietNight(true);
			/*
			 * Working
			 */
			
		}
		if(action.equals("Special Event: ReExaminedResearch"))
		{
			
			ArrayList<String>choices = new ArrayList();
			for(int k=0; k<PlayerDeck.getDiscardDeck().size(); k++)
			{
				if(!PlayerDeck.getDiscardDeck().get(k).getID().equals("Epidemic"))	
					choices.add("Take "+PlayerDeck.getDiscardDeck().get(k).getID());
			}
			ScrollingBackground.drawTopGUI("Choose a city to take", choices);
			Infection.setPop(true);
			/*
			 * working
			 */
		}
		
		
		
	
		/*
		 * Secondary choices
		 * Special Events
		 */
		if(action.substring(0,4).equals("Take"))
		{
			i.reexaminedResearch(Pawn.whoseTurn(), action.substring(5));
		}
		/*
		 * Secondary command Forecast
		 */
		if(temp.equals("Put down "))
		{
	
			for(int k=0; k<topSix.size();k++)
			{
				if(action.substring(9).equals(topSix.get(k).getID()))
				{
					System.out.println("You have placed down " + topSix.get(k).getID());
					topSix.get(k).resetInfectionCard();
					Card.getInfectionDiscarding().add(topSix.get(k));
					topSix.get(k).setDiscard(true);
					topSix.remove(k);
					
				}
				
			}
			ArrayList<String>toRet=new ArrayList();
			for(int k=0; k<topSix.size();k++)
			{
				toRet.add("Put down " + topSix.get(k).getID());
			}
			if(topSix.size()>0)
			{
				ScrollingBackground.drawTopGUI("Choose forecasted card to put back down", toRet);
			}
			
			

				
		}
		
	
		
		
		/*
		 * Secondary new role
		 */
		
		if(temp.equals("New role:"))
		{
			int index=0;
			for(int k=0; k<Pawn.getPawns().size();k++)
			{
				if(Pawn.getPawns().get(k).equals(Pawn.whoseTurn()))
					index=k;
			}
			Pawn.getRoles().add(Pawn.whoseTurn().getRole());
			Pawn.whoseTurn().setRole(action.substring(10), index, true, 
					Pawn.getPawns().get(index).getMoves(), Pawn.getPawns().get(index).getMyHand(),
					Pawn.getPawns().get(index).getLocation());
			System.out.println("Hit: " +action);
			
		}
		/*
		Special order
		*/
		if(temp.equals("Special O"))
		{
			Infection.setSpecialOrders(true);
			Infection.setStoredPlayer(Pawn.whoseTurn());
			int moves = Infection.getStoredPlayer().getMoves();
			Pawn.whoseTurn().setTurn(false);
			for(int k=0; k<Pawn.getPawns().size(); k++)
			{
				if(Pawn.getPawns().get(k).getRole().equals(action.substring(15)))
				{
					System.out.println("You are playing as "+ Pawn.getPawns().get(k).getRole());
					Infection.setSpecialPlayer(Pawn.getPawns().get(k));
					Pawn.getPawns().get(k).setTurn(true);
					Pawn.getPawns().get(k).setMoves(moves);
				}
			}
		}
		
		/*
		 * Secondary with Resilient population
		 * 
		 */
		if(temp.equals("Remove --"))
		{
			Card c = new Card(City.getCities().get(0));
			for (int k=0; k<InfectionDeck.getInfectionDeck().size();k++)
			{
				if(InfectionDeck.getInfectionDeck().get(k).getID().equals(action.substring(10)))
				{
					c=InfectionDeck.getInfectionDeck().get(k);
				}
			}
			InfectionDeck.getInfectionDeck().remove(c);
			ScrollingBackground.drawTimedMessageGUI("You have removed "+ c.getID());
				
		}
		
		
		
		
		
		
		
		if(temp.equals("Discard: "))
		{
			Pawn p = new Pawn();
			int playerIndex=0;
			for (int k =0; k<Pawn.getPawns().size();k++)
				if(Pawn.getPawns().get(k).getNeed())
				{
					p = Pawn.getPawns().get(k);
					playerIndex = k;
				}
			int index=0;
			for(int k=0; k<p.getMyHand().size();k++)
				if(p.getMyHand().get(k).getID().equals(action.substring(9)))
					index=k;
				
			i.discardCard(p, index);
			p.checkDiscard(playerIndex);
		}
		/*
		 * Secondary airlift
		 */
		if(temp.equals("Control: "))
		{
			for(int k=0; k<Pawn.getPawns().size();k++)
			{
				if(Pawn.getPawns().get(k).getRole().equals(action.substring(9)))
					Infection.setAirliftPlayer(Pawn.getPawns().get(k));
			}
			ScrollingBackground.drawTimedMessageGUI("Choose a city to move to");
		}
		
		/*
		 * Dispatch Special Move: 
		 * 
		 */
		
		if(temp.equals("Dispatch "))
		{
			Pawn.whoseTurn().addMove();
			Pawn pawn1 = new Pawn();
			Pawn pawn2 = new Pawn();
			int commaSpot = action.indexOf(",");
			String p1 = action.substring(32, commaSpot);
			String p2 = action.substring(commaSpot+2);
			System.out.println(action.substring(32, commaSpot) + " " +
					action.substring(commaSpot+2));
			for(int k=0; k<Pawn.getPawns().size();k++)
			{
				if(Pawn.getPawns().get(k).getRole().equals(p1))
					pawn1=Pawn.getPawns().get(k);
				if(Pawn.getPawns().get(k).getRole().equals(p2))
					pawn2=Pawn.getPawns().get(k);
			}
			pawn1.setLocation(pawn2.getLocation());
		}
	
	

		
		
		
		/*
		 * Commands on click
		 */
		
		if(action.equals("Research Center"))
		{
			i.buildResearch(myPawn, myPawn.getLocation());
	
			
		}
		/*if(action.equals("Discard"))
		{
			i.discardCard(myPawn, myInt);
			
		}*/
		if(action.equals("Direct Flight"))
		{
			City c = new City();
			for(int m=0; m<City.getCities().size();m++)
			{
				if(City.getCities().get(m).getName().equals(Pawn.whoseTurn().getHandNames().get(myInt)))
				{
					c=City.getCities().get(m);
				}
			}
			
			i.directFlightToCity(Pawn.whoseTurn(), c);
			
			//Fix
		}
	
		
		
		/*
		 * When there are multiple color cubes
		 */
		
		if(action.equals("Remove blue cube"))
		{
			i.removeBlueCube(Pawn.whoseTurn().getLocation());
		}
		if(action.equals("Remove red cube"))
		{
			i.removeRedCube(Pawn.whoseTurn().getLocation());
		}
		if(action.equals("Remove yellow cube"))
		{
			i.removeYellowCube(Pawn.whoseTurn().getLocation());
		}
		if(action.equals("Remove black cube"))
		{
			i.removeBlackCube(Pawn.whoseTurn().getLocation());
		}
		if(action.equals("Chartered Flight"))
		{
			ScrollingBackground.drawTimedMessageGUI("Choose a city");
			charteringFlight=true;
		}
		
		
		
		
		
		try
		{
			temp = action.substring(0,15);
		}
		catch(Exception e){}
		
		/*
		 * Cure
		 */
		if(temp.equals("Discard towards"))
		{
			for(int k=0; k<Pawn.whoseTurn().getMyHand().size(); k++)
			{
				if(Pawn.whoseTurn().getMyHand().get(k).getID().equals(action.substring(22)))
				{
					Infection.removeCureDiscard("Discard towards cure: "+ Pawn.whoseTurn().getMyHand().get(k).getID());
					i.discardCard(Pawn.whoseTurn(), k);
					Infection.addCuredCard();
				}
			}
			if(Infection.getCuredCard()<Pawn.whoseTurn().getNumToCure())
			{
				ScrollingBackground.drawTopGUI("Choose discard", Infection.getCureDiscard());
			}
			else
			{
				Infection.setCuredCard(0);
				Infection.getCureDiscard().clear();
			}
		}
		
		if(temp.equals("Special Event: "))
		{
			
			if(!contingencyTemp)	
				i.specialEvent(myPawn, myInt);
			else
			{
				contingencyTemp=false;
				System.out.println("Contigency Planner played the card: "+
						Pawn.whoseTurn().getMyHand().remove(Pawn.whoseTurn().getMyHand().size()-1).getID());
				Pawn.whoseTurn().addMove();
			}
			
		}
		if(temp.equals("Trade card with"))
		{
			i.tradeCard(myPawn, myInt, action.substring(16));//substring = name of person you are giving card to
				
		}
		if(temp.equals("Steal card from"))
		{
			i.stealCard(myPawn, myInt);
		
		}
		if(temp.equals("Epidemiologist "))
		{
			int colonSpot = action.indexOf(":");
			int fromSpot = action.indexOf("from");
			String card = action.substring(colonSpot+7, fromSpot-1);
			String takenFrom = action.substring(fromSpot + 5);
			System.out.println(card);
			System.out.println(takenFrom);
			Pawn pawn = new Pawn();
			int index = 0;
			for(int k=0; k<Pawn.getPawns().size(); k++)
			{
				if(Pawn.getPawns().get(k).getRole().equals(takenFrom))
				{
					pawn = Pawn.getPawns().get(k);
					for(int m=0; m<Pawn.getPawns().get(k).getMyHand().size(); m++)
					{
						if(Pawn.getPawns().get(k).getMyHand().get(m).getID().equals(card))
						{
							index=m;
						}
					}
				}
			}
			Pawn.whoseTurn().setMoves(Pawn.whoseTurn().getMoves()-1);
			if(Pawn.whoseTurn().getMoves()+1<4)
				Pawn.whoseTurn().setSpecialMovesTaken(1);
			i.stealCard(pawn, index);
		}
		
		if(temp.equals("No cost: build "))
		{
			Pawn.whoseTurn().getLocation().setHasResearchCenter(true);
			Pawn.whoseTurn().addMove();
		}
		if(temp.equals("Fly anywhere: D"))
		{
			Pawn.whoseTurn().setSpecialMovesTaken(1);
			ArrayList<String> options = new ArrayList();
			for(int k=0; k<Pawn.whoseTurn().getMyHand().size();k++)
			{
				options.add("Operations discard: " + Pawn.whoseTurn().getMyHand().get(k).getID());
			}
			ScrollingBackground.drawTopGUI("Choose a discard", options);
		}
		if(temp.equals("Operations disc"))
		{
			Infection.setOperationsFlight(true);
			for(int k=0; k<Pawn.whoseTurn().getMyHand().size();k++)
			{
				if(Pawn.whoseTurn().getMyHand().get(k).getID().equals(action.substring(20)))
				{
					i.discardCard(Pawn.whoseTurn(), k);
				}
			}
			ScrollingBackground.drawTimedMessageGUI("Choose your city");
		}
		if(temp.equals("Troubleshooter "))
		{
			ArrayList<String> reveal = new ArrayList();
			for(int k=0; k<Infection.getInfectionRate(); k++)
			{
				try
				{
					reveal.add(""+InfectionDeck.getInfectionDeck().get(InfectionDeck.getInfectionDeck().size()-1-k).getID());
				}
				catch(Exception e){}
			}
			ScrollingBackground.drawStartGUI("Infection Forecast", reveal);
		}
		
		/*
		 * Field Operative
		 */
		if(temp.equals("Store black cub"))
		{
			Infection.addStoredBlack(1);
			Infection.removeBlackCube(Pawn.whoseTurn().getLocation());
		}
		if (temp.equals("Store blue cube"))
			
		{
			Infection.removeBlueCube(Pawn.whoseTurn().getLocation());
			Infection.addStoredBlue(1);
		}
		if(temp.equals("Store yellow cu"))
		{
			Infection.removeYellowCube(Pawn.whoseTurn().getLocation());
			Infection.addStoredYellow(1);
		}
		if(temp.equals("Store red cube "))
		{
			Infection.removeRedCube(Pawn.whoseTurn().getLocation());
			Infection.addStoredRed(1);
		}
		
		/*
		 * Field Operative--
		 * Cure Special Move
		 */
		
		if (temp.equals("Play three red "))
		{

			Infection.addCuredCard();
			Infection.addCuredCard();
			Infection.addStoredRed(-3);
			if(Infection.getCuredCard()<5)
			{
				ScrollingBackground.drawTopGUI("Choose discard", Infection.getCureDiscard());
			}
			else
			{
				Infection.setCuredCard(0);
				Infection.getCureDiscard().clear();
			}
	
		}
		if (temp.equals("Play three blac"))
		{

			Infection.addCuredCard();
			Infection.addCuredCard();
			Infection.addStoredBlack(-3);
			if(Infection.getCuredCard()<5)
			{
				ScrollingBackground.drawTopGUI("Choose discard", Infection.getCureDiscard());
			}
			else
			{
				Infection.setCuredCard(0);
				Infection.getCureDiscard().clear();
			}
	
		}
		if (temp.equals("Play three blue"))
		{

			Infection.addCuredCard();
			Infection.addCuredCard();
			Infection.addStoredBlue(-3);
			if(Infection.getCuredCard()<5)
			{
				ScrollingBackground.drawTopGUI("Choose discard", Infection.getCureDiscard());
			}
			else
			{
				Infection.setCuredCard(0);
				Infection.getCureDiscard().clear();
			}
	
		}
		if (temp.equals("Play three yell"))
		{

			Infection.addCuredCard();
			Infection.addCuredCard();
			Infection.addStoredYellow(-3);
			if(Infection.getCuredCard()<5)
			{
				ScrollingBackground.drawTopGUI("Choose discard", Infection.getCureDiscard());
			}
			else
			{
				Infection.setCuredCard(0);
				Infection.getCureDiscard().clear();
			}
	
		}
		/*
		 * Role special powers
		 */
		
		
		
		if(temp.equals("Archivist move:"))
		{
			i.archivist(Pawn.whoseTurn(), action.substring(24));
		}
		
		
		/*
		 * discard last chance move
		 */
		
		
		/*for(int i1=0; i1<Pawn.getPawns().size();i1++)
		{
			if(Pawn.getPawns().get(i1).getTurn()&&Pawn.getPawns().get(i1).getMoves()==4)
			{
				Infection in=new Infection();
				in.cycle(i1);
			}
		}*/
		
		
		/*
		 * FIX FLASHING ERROR AT START
		 */
		
	}
}