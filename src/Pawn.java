import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.io.File;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Pawn
{
	private City location;
	private Color color;
	private int moves;
	private int movesPerTurn;
	private String role;
	private Image pawnImage;
	private static int numPlayers=0;
	private static ArrayList<Pawn> players = new ArrayList();
	private static Image arch =Toolkit.getDefaultToolkit().getImage("ArchivistPawn.png");
	private static Image contain =Toolkit.getDefaultToolkit().getImage("ContainmentSpecialistPawn.png");
	private static Image contigency =Toolkit.getDefaultToolkit().getImage("ContingencyPlannerPawn.png");
	private static Image dispatch = Toolkit.getDefaultToolkit().getImage("DispatcherPawn.png");
	private static Image epidem = Toolkit.getDefaultToolkit().getImage("EpidemiologistPawn.png");
	private static Image field = Toolkit.getDefaultToolkit().getImage("FieldOperativePawn.png");
	private static Image gen = Toolkit.getDefaultToolkit().getImage("GeneralistPawn.png");
	private static Image medic = Toolkit.getDefaultToolkit().getImage("MedicPawn.png");
	private static Image op = Toolkit.getDefaultToolkit().getImage("OperationsExpertPawn.png");
	private static Image quar = Toolkit.getDefaultToolkit().getImage("QuarentineSpecialistPawn.png");
	private static Image research = Toolkit.getDefaultToolkit().getImage("ResearcherPawn.png");
	private static Image scientist = Toolkit.getDefaultToolkit().getImage("ScientistPawn.png");
	private static Image trouble = Toolkit.getDefaultToolkit().getImage("TroubleShooterPawn.png");
	private static Image archCard =Toolkit.getDefaultToolkit().getImage("RoleArchivist.png");
	private static Image containCard =Toolkit.getDefaultToolkit().getImage("RoleContainmentSpecialist.png");
	private static Image contigencyCard =Toolkit.getDefaultToolkit().getImage("RoleContingencyPlanner.png");
	private static Image dispatchCard = Toolkit.getDefaultToolkit().getImage("RoleDispatcher.png");
	private static Image epidemCard = Toolkit.getDefaultToolkit().getImage("RoleEpidemiologist.png");
	private static Image fieldCard = Toolkit.getDefaultToolkit().getImage("RoleFieldOperative.png");
	private static Image genCard = Toolkit.getDefaultToolkit().getImage("RoleGeneralist.png");
	private static Image medicCard = Toolkit.getDefaultToolkit().getImage("RoleMedic.png");
	private static Image opCard = Toolkit.getDefaultToolkit().getImage("RoleOperationsExpert.png");
	private static Image quarCard = Toolkit.getDefaultToolkit().getImage("RoleQuarantineSpecialist.png");
	private static Image researchCard = Toolkit.getDefaultToolkit().getImage("RoleResearcher.png");
	private static Image scientistCard = Toolkit.getDefaultToolkit().getImage("RoleScientist.png");
	private static Image troubleCard = Toolkit.getDefaultToolkit().getImage("RoleTroubleShooter.png");
	private static Image donutPic =Toolkit.getDefaultToolkit().getImage("Donut.png");
	private static Image catPic =Toolkit.getDefaultToolkit().getImage("cat.png");
	
	private ArrayList <Card> myHand = new ArrayList();
	private boolean myTurn;
	private static ArrayList<String> roles = new ArrayList();
	private static ArrayList<String> chosenRoles=new ArrayList();
	private Image roleImage;
	
	private static ArrayList<Image> roleImages = new ArrayList();
	public static ArrayList<Image> getRoleImages() {return roleImages;}
	
	private boolean roleHover;
	private int discardNum=7;
	private boolean needToDiscard=false;
	
	public void setNeed(boolean c){needToDiscard = c;}
	public boolean getNeed() {return needToDiscard;}
	
	private boolean specialMove;
	public void setSpecialMove(boolean c){specialMove=c;}
	public boolean getSpecialMove(){return specialMove;}

	private int specialMovesTaken;
	public int getSpecialMovesTaken(){return specialMovesTaken;}
	public void setSpecialMovesTaken(int c){specialMovesTaken=c;}
	
	private int numToCure=5;
	public void setNumToCure(int c){numToCure=c;}
	public int getNumToCure(){return numToCure;}
	
	public int getDiscardNum(){return discardNum;}
	public void setDiscardNum(int c){discardNum=c;}
	public boolean getRoleHover(){return roleHover;}
	public void setRoleHover(boolean c){roleHover=c;}
	
	public Pawn()
	{
		location=new City();
		role = "";
		moves=0;
		movesPerTurn=4;
		myTurn = true;
	}
	
	public Pawn(City l, String n, Image pawn, Image roleCard, boolean turn)
	{
		location=l;
		role=n;
		pawnImage=pawn;
		myTurn = turn;
		roleImage=roleCard;
		movesPerTurn=4;
		
	}
	
	public void checkDiscard(int j)
	{
			int numCards = this.getMyHand().size();
			if(numCards> this.getDiscardNum()) 
			{
				this.setNeed(true);
				Infection i = new Infection();
				i.resetClick();
				ArrayList <String > toRet = new ArrayList();
				for(int k =0; k<players.get(j).getMyHand().size();k++)
				{
					toRet.add("Discard: " + players.get(j).getMyHand().get(k).getID());
				}
				ScrollingBackground.drawLargeTopGUI("Discard options for " + Pawn.getPawns().get(j).getRole(), toRet);
				Infection.setPop(true);
				/*
				 * 
				 * Fix mandatory discard
				 * */
				/*
				 * Call pop-up menu here?
				 */
			}
			else 
			{this.setNeed(false);}
		
	}
	
	public boolean equals(Pawn pawn)
	{
		if(this.getRole().equals(pawn.getRole()))
			return true;
		else
			return false;
	}

	
	public boolean inCity(City city)
	{
	
		if(city.getName().equals(this.getLocation().getName()))
			return true;
		else
			return false;
	}
	
	public boolean inCity(Card card)
	{
		if(card.getID().equals(this.getLocation().getName()))
			return true;
		else
			return false;
	}
	
	public static Pawn whoseTurn()
	{
		Pawn toRet = new Pawn();
		for(int i=0; i<players.size();i ++)
		{
			if (players.get(i).getTurn())
				toRet= players.get(i);
		}
		return toRet;
	}
	
	
	public static void populateRoles()
	{
		
		roleImages.add(archCard);
		roleImages.add(containCard);
		roleImages.add(epidemCard);
		roleImages.add(fieldCard);
		roleImages.add(genCard);
		roleImages.add(troubleCard);
		roleImages.add(medicCard);
		roleImages.add(researchCard);
		roleImages.add(scientistCard);
		roleImages.add(quarCard);
		roleImages.add(contigencyCard);
		roleImages.add(opCard);
		roleImages.add(dispatchCard);
		
		roles.add("Archivist"); //done
		roles.add("Containment Specialist"); //done
		roles.add("Epidemiologist"); // done
		roles.add("Field Operative"); //done
		roles.add("Generalist"); //done
		roles.add("Troubleshooter"); //done
		//roles.add("Bio-Terrorist");
		roles.add("Medic"); //done
		roles.add("Researcher"); //done
		roles.add("Scientist"); //done
		roles.add("Quarantine Specialist"); //done
		roles.add("Contingency Planner"); //done
		roles.add("Operations Expert"); // done
		roles.add("Dispatcher"); //done
		
	}

	
	public static void drawPawns(Graphics g, int shift)
	{
		for(int i=0; i<players.size(); i++)
		{
			int x=(int) players.get(i).getLocation().getCenter().getX()-10+shift;
			int y=(int) players.get(i).getLocation().getCenter().getY()-20;
			if(players.get(i).getLocation().getHover())
				y-=10+i*10;
			/*if(players.get(i).getLocation().getHover() && ScrollingBackground.getCat())
				g.drawString("MEOW", x-15, y-20);*/
			if((players.get(i).getTurn() && Infection.getAirliftPlayer()==null)|| (Infection.getAirliftPlayer()!=null && players.get(i).equals(Infection.getAirliftPlayer()))
					&&!ScrollingBackground.getDonut()&&!ScrollingBackground.getCat())
				g.drawImage(players.get(i).getImage(), x-10,y-10, 45, 45, null); //draw larger if your turn
			else if(!ScrollingBackground.getDonut()&&!ScrollingBackground.getCat())
				g.drawImage(players.get(i).getImage(), x,y, 26, 26, null);//shift for each added player
			/*if(players.get(i).getTurn()&&ScrollingBackground.getDonut())
				g.drawImage(donutPic, x-10,y-10, 45, 45, null); //draw larger if your turn
			else if(ScrollingBackground.getDonut())
				g.drawImage(donutPic, x,y, 26, 26, null);//shift for each added player
			if(players.get(i).getTurn()&&ScrollingBackground.getCat())
				g.drawImage(catPic, x-25,y-20, 60, 60, null); //draw larger if your turn
			else if(ScrollingBackground.getCat())
				g.drawImage(catPic, x-15,y-15, 40, 40, null);//shift for each added player*/
		}
		drawCardHand(g,shift); // draw the hands of each pawn
	}

	public static void drawNumMoves(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect(935, 150, 75, 25);
		g.setColor(Color.WHITE);
		g.drawRect(935, 150, 75, 25);
		g.drawString("Moves: "+ (Pawn.whoseTurn().getMovesPerTurn() - Pawn.whoseTurn().getMoves()), 943, 170);
	}
	
	public static void drawCardHand(Graphics g, int shift)
	{
		int whyPos=10+(numPlayers-1)*55;
		for(int i=numPlayers-1; i>=0;i--)
		{
				for(int j=players.get(i).getMyHand().size(); j>=0; j--)
				{
					if(j==0 && players.get(i).getRoleHover()) //draw role card
					{
						g.drawImage(players.get(i).roleImage, 
								10+j*45, whyPos, 190, 240, null);
						g.setColor(Color.PINK);
						if(players.get(i).getTurn())
						{
							g.drawRect(5+j*45, whyPos-5, 200, 248);
						}
						if(players.get(i).getRole().equals("Contingency Planner")&&Infection.getPlannerStoredCard()!=null)
						{
							g.drawImage(Infection.getPlannerStoredCard().getCardImage().getCurrentImage(), 15+j*45,whyPos+5, 40,60, null);
						}
						if(players.get(i).getRole().equals("Field Operative"))
						{
							int ex = 20+j*45;
							int why = whyPos+30;
							Cube cube = new Cube(15, ex, why, Color.RED);
							int num = Infection.getStoredRed();
							cube.drawCubes(g,cube, num);
							
							if(num!=0)
								why+=30;
							num=Infection.getStoredBlue();
							cube=new Cube(15,ex,why, Color.BLUE);
							cube.drawCubes(g,cube, num);
							
							if(num!=0)
								why+=30;
							num=Infection.getStoredYellow();
							cube=new Cube(15,ex,why, Color.YELLOW);
							cube.drawCubes(g,cube, num);
							
							if(num!=0)
								why+=30;
							num=Infection.getStoredBlack();
							cube=new Cube(15,ex,why, Color.BLACK);
							cube.drawCubes(g,cube, num);
						}
					}
					else if(j==0)
					{
						g.drawImage(players.get(i).roleImage, 
								10+j*45, whyPos, 35, 50, null);
						g.setColor(Color.PINK);
						if(players.get(i).getTurn())
						{
							g.drawRect(5+j*45, whyPos-5, 45, 58);
		
						}
					}
					else if(players.get(i).getMyHand().get(j-1).getHover())//draw other cards
					{
						g.drawImage(players.get(i).getMyHand().get(j-1).getCardImage().getCurrentImage(), 
						5+j*45, whyPos-5, 80, 110, null);
					}
					else
					{
						g.drawImage(players.get(i).getMyHand().get(j-1).getCardImage().getCurrentImage(), 
						10+j*45, whyPos, 35, 50, null);
						
					}
				}
				whyPos-=55;// move down for new player
			
		}
	}
	
	public static String toString3(ArrayList<Card> cards)
	{
		int counter = 0;
		String toRet = "";
		for(int i=0; i<cards.size()-1;i++)
		{
			toRet += cards.get(i).getID() + ", ";
		}
		try
		{
			toRet+= cards.get(cards.size()-1).getID();
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return toRet;
	}
	
	public static ArrayList<Pawn> getPawns() {return players;}
	public void setLocation(City l) {location=l;}
	public void addMove() {moves++;}
	public int getMoves() {return moves;}
	public City getLocation() {return location;}
	public void  setTurn(boolean turn) {myTurn = turn;}
	public void setMoves (int m) {moves=m;}
	public boolean getTurn () {return myTurn;}
	public Image getImage() {return pawnImage;}
	public ArrayList<Card> getMyHand() {return myHand;}
	public ArrayList<String> getHandNames() 
	{
		ArrayList s = new ArrayList<String>();
		for(int i=0; i<myHand.size();i++)
		{
			s.add(myHand.get(i).getID());
		}
		return s;
	}
	public static int getNumPlayers() {return numPlayers;}
	public static void setNumPlayers(int x) 
	{
		/*
		 * Setting players to determined roles depending on number needed
		 */
		numPlayers=x;
		/*if(numPlayers>=1)
		{
			players.add(new Pawn(City.getCities().get(2), "Archivist", arch, archCard, true));
			roles.remove("Archivist");
		}
		if(numPlayers>=2)
		{
			players.add(new Pawn(City.getCities().get(2), "Field Operative", op, opCard, false));
			roles.remove("Field Operative");
		}
		if(numPlayers>=3)
		{
			players.add(new Pawn(City.getCities().get(2), "Epidemiologist", epidem, epidemCard, false));
			roles.remove("Epidemiologist");
		}
		if(numPlayers>=4)
		{
			players.add(new Pawn(City.getCities().get(2), "Containment Specialist", contain, containCard, false));
			roles.remove("Containment Specialist");
		}
		if(numPlayers>=5)
		{
			players.add(new Pawn(City.getCities().get(2), "Generalist", gen, genCard, false));
			roles.remove("Generalist");
		}*/
	}
	
	public void setRole(String string, int index, boolean myTurn, int prevMoves, ArrayList<Card> myHand, City city)
	{
		if(string.equals("Archivist"))
		{
			players.set(index, new Pawn(city, "Archivist", arch, archCard, myTurn));
			players.get(index).setDiscardNum(8);
			roles.remove("Archivist");
			/*
			 * done
			 */
		}
		if(string.equals("Field Operative"))
		{
			players.set(index, new Pawn(City.getCities().get(2), "Field Operative", field, fieldCard, myTurn));
			roles.remove("Field Operative");
		}
		if(string.equals("Epidemiologist"))
		{
			players.set(index, new Pawn(city, "Epidemiologist", epidem, epidemCard, myTurn));
			roles.remove("Epidemiologist");
		}
		if(string.equals("Containment Specialist"))
		{
			players.set(index, new Pawn(city, "Containment Specialist", contain, containCard, myTurn));
			roles.remove("Containment Specialist");
			/*
			 * done
			 */
		}
		if(string.equals("Generalist"))
		{
			players.set(index, new Pawn(city, "Generalist", gen, genCard, myTurn));
			roles.remove("Generalist");
			players.get(index).setMovesPerTurn(5);
			/*
			 * done
			 */
		}
		if(string.equals("Contingency Planner"))
		{
			players.set(index, new Pawn(city, "Contingency Planner", contigency, contigencyCard, myTurn));
			roles.remove("Contingency Planner");
		}
		if(string.equals("Dispatcher"))
		{
			players.set(index, new Pawn(city, "Dispatcher", dispatch, dispatchCard, myTurn));
			roles.remove("Dispatcher");
		}
		if(string.equals("Medic"))
		{
			players.set(index, new Pawn(city, "Medic", medic, medicCard, myTurn));
			roles.remove("Medic");
		}
		if(string.equals("Operations Expert"))
		{
			players.set(index, new Pawn(city, "Operations Expert", op, opCard, myTurn));
			roles.remove("Operations Expert");
		}
		if(string.equals("Quarantine Specialist"))
		{
			players.set(index,new Pawn(city, "Quarantine Specialist", quar, quarCard, myTurn));
			roles.remove("Quarantine Specialist");
		}
		if(string.equals("Researcher"))
		{
			players.set(index, new Pawn(city, "Researcher", research, researchCard, myTurn));
			roles.remove("Researcher");
		}
		if(string.equals("Scientist"))
		{
			players.set(index, new Pawn(city, "Scientist", scientist, scientistCard, myTurn));
			roles.remove("Scientist");
			players.get(index).setNumToCure(4);
		}
		if(string.equals("Troubleshooter"))
		{
			players.set(index, new Pawn(city, "Troubleshooter", trouble, troubleCard, myTurn));
			roles.remove("Troubleshooter");
		}
		try
		{
			players.get(index).getMyHand().addAll(myHand);
		}
		catch (Exception e){}
		players.get(index).setMoves(prevMoves);
	}
	
	public static void addNewPlayer(String string)
	{
		boolean myTurn=false;
		if(players.size()==0)
			myTurn = true;
		players.add(new Pawn());
		players.get(players.size()-1).setRole(string, players.size()-1, myTurn, 0, null, City.getCities().get(2));
		
		/*if(string.equals("Archivist"))
		{
			players.add(new Pawn(City.getCities().get(2), "Archivist", arch, archCard, myTurn));
			players.get(players.size()-1).setDiscardNum(8);
			roles.remove("Archivist");
			/*
			 * done
			 
		}
		if(string.equals("Field Operative"))
		{
			players.add(new Pawn(City.getCities().get(2), "Field Operative", field, fieldCard, myTurn));
			roles.remove("Field Operative");
		}
		if(string.equals("Epidemiologist"))
		{
			players.add(new Pawn(City.getCities().get(2), "Epidemiologist", epidem, epidemCard, myTurn));
			roles.remove("Epidemiologist");
		}
		if(string.equals("Containment Specialist"))
		{
			players.add(new Pawn(City.getCities().get(2), "Containment Specialist", contain, containCard, myTurn));
			roles.remove("Containment Specialist");
			/*
			 * done
			 
		}
		if(string.equals("Generalist"))
		{
			players.add(new Pawn(City.getCities().get(2), "Generalist", gen, genCard, myTurn));
			roles.remove("Generalist");
			players.get(players.size()-1).setMovesPerTurn(5);
			/*
			 * done
			 
		}
		if(string.equals("Contingency Planner"))
		{
			players.add(new Pawn(City.getCities().get(2), "Contingency Planner", contigency, contigencyCard, myTurn));
			roles.remove("Contingency Planner");
		}
		if(string.equals("Dispatcher"))
		{
			players.add(new Pawn(City.getCities().get(2), "Dispatcher", dispatch, dispatchCard, myTurn));
			roles.remove("Dispatcher");
		}
		if(string.equals("Medic"))
		{
			players.add(new Pawn(City.getCities().get(2), "Medic", medic, medicCard, myTurn));
			roles.remove("Medic");
		}
		if(string.equals("Operations Expert"))
		{
			players.add(new Pawn(City.getCities().get(2), "Operations Expert", op, opCard, myTurn));
			roles.remove("Operations Expert");
		}
		if(string.equals("Quarantine Specialist"))
		{
			players.add(new Pawn(City.getCities().get(2), "Quarantine Specialist", quar, quarCard, myTurn));
			roles.remove("Quarantine Specialist");
		}
		if(string.equals("Researcher"))
		{
			players.add(new Pawn(City.getCities().get(2), "Researcher", research, researchCard, myTurn));
			roles.remove("Researcher");
		}
		if(string.equals("Scientist"))
		{
			players.add(new Pawn(City.getCities().get(2), "Scientist", scientist, scientistCard, myTurn));
			roles.remove("Scientist");
		}
		if(string.equals("Troubleshooter"))
		{
			players.add(new Pawn(City.getCities().get(2), "Troubleshooter", trouble, troubleCard, myTurn));
			roles.remove("Troubleshooter");
		}*/
		/*
		 * Add special moves
		 */
	}
	
	public String getRole() {return role;}
	public static ArrayList<String> getRoles() {return roles;}
	public void setMovesPerTurn(int c){movesPerTurn=c;}
	public int getMovesPerTurn(){return movesPerTurn;}
	public void addMovesPerTurn(int k){movesPerTurn+=k;}
	
		
	
	

	
	
}
