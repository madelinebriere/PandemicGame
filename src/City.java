import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;


public class City 
{
	private Point center;
	private Color color;
	private int diameter;
	private boolean isReal;
	private String myName;
	
	private int numRed;
	private int numBlue;
	private int numBlack;
	private int numYellow;
	
	private static ArrayList<City> cities = new ArrayList() ;
	private ArrayList<City> neighbors = new ArrayList();
	private ArrayList<City> crossNeighbors = new ArrayList();
	
	private boolean hasResearchCenter;
	
	private static Image red =Toolkit.getDefaultToolkit().getImage("RedCity.png");
	private static Image yellow =Toolkit.getDefaultToolkit().getImage("YellowCity.png");
	private static Image black =Toolkit.getDefaultToolkit().getImage("BlackCity.png");
	private static Image blue =Toolkit.getDefaultToolkit().getImage("BlueCity.png");
	private static Image researchCenter =Toolkit.getDefaultToolkit().getImage("ResearcherCenter.png");
	private boolean hover=false;
	
	public void setRedCubes(int n){numRed = n;}
	public void setYellowCubes(int n){numYellow = n;}
	public void setBlueCubes(int n){numBlue = n;}
	public void setBlackCubes(int n){numBlack = n;}
	
	public int getRedCubes(){return numRed;}
	public int getBlueCubes() {return numBlue;}
	public int getYellowCubes(){return numYellow;}
	public int getBlackCubes(){return numBlack;}
	
	public City(){}
	
	public City(Point p, Color c, int d, boolean real, String name)
	{
		color=c;
		center=p;
		diameter=d;
		isReal=real;
		myName=name;
	}
	
	public boolean equals(Card card)
	{
		if(this.getName().equals(card.getID()))
			return true;
		else
			return false;
	}
	
	public static City cityClicked(int ex, int why)
	{
		City c = null;
		for(int j=0; j<City.getCities().size();j++)//cycle through cities
		{
			if(clickedCity(City.getCities().get(j), ex, why))
				c = City.getCities().get(j);
		}
		return c;
	}
	
	public boolean hasPlayer(String role)
	{
		boolean toRet=false;
		for(int i=0; i< Pawn.getPawns().size(); i++)
		{
			if(Pawn.getPawns().get(i).getRole().equals(role) && Pawn.getPawns().get(i).inCity(this))
				toRet=true;
		}
		return toRet;
	}
	
	public boolean cityQuarantined()
	{
		boolean toRet=false;
		Pawn quarantine = new Pawn();
		boolean quarAction = false;
		for(int i=0; i<Pawn.getPawns().size();i++)
		{
			if(Pawn.getPawns().get(i).getRole().equals("Quarantine Specialist"))
			{
				quarantine = Pawn.getPawns().get(i);
				quarAction=true;
			}
		}
		for(int i=0; i<this.getNeighbors().size();i++)
		{
			if(quarAction && getNeighbors().get(i).equals(quarantine.getLocation()))
			{
				toRet=true;
			}
		}
		if(quarantine.getLocation().equals(this))
			toRet=true;
		for(int i=0; i<this.getCrossNeighbors().size();i++)
		{
			if(quarAction && getCrossNeighbors().get(i).equals(quarantine.getLocation()))
			{
				toRet=true;
			}
		}
		return toRet;
	}
	public boolean cityCleared(Color color)
	{
		boolean toRet=true;
		
		if(this.getColor().equals(Color.RED) && color.equals(Color.RED) && numRed>0)
			toRet=false;
		if(this.getColor().equals(Color.BLUE) && color.equals(Color.BLUE) && numBlue>0)
			toRet=false;
		if(this.getColor().equals(Color.BLACK) && color.equals(Color.BLACK) && numBlack>0)
			toRet=false;
		if(this.getColor().equals(Color.YELLOW) && color.equals(Color.YELLOW) && numYellow>0)
			toRet=false;
		
		for(int i=0; i<this.getNeighbors().size();i++)
		{
			if(this.getNeighbors().get(i).getColor().equals(Color.RED) && color.equals(Color.RED) && this.getNeighbors().get(i).numRed>0)
				toRet=false;
			if(this.getNeighbors().get(i).getColor().equals(Color.BLUE) && color.equals(Color.BLUE) && this.getNeighbors().get(i).numBlue>0)
				toRet=false;
			if(this.getNeighbors().get(i).getColor().equals(Color.BLACK) && color.equals(Color.BLACK) && this.getNeighbors().get(i).numBlack>0)
				toRet=false;
			if(this.getNeighbors().get(i).getColor().equals(Color.YELLOW) && color.equals(Color.YELLOW) && this.getNeighbors().get(i).numYellow>0)
				toRet=false;
		}
		
		for(int i=0; i<this.getCrossNeighbors().size();i++)
		{
			if(this.getCrossNeighbors().get(i).getColor().equals(Color.RED) && color.equals(Color.RED) && this.getCrossNeighbors().get(i).numRed>0)
				toRet=false;
			if(this.getCrossNeighbors().get(i).getColor().equals(Color.BLUE) && color.equals(Color.BLUE) && this.getCrossNeighbors().get(i).numBlue>0)
				toRet=false;
			if(this.getCrossNeighbors().get(i).getColor().equals(Color.BLACK) && color.equals(Color.BLACK) && this.getCrossNeighbors().get(i).numBlack>0)
				toRet=false;
			if(this.getCrossNeighbors().get(i).getColor().equals(Color.YELLOW) && color.equals(Color.YELLOW) && this.getCrossNeighbors().get(i).numYellow>0)
				toRet=false;
		}
		
		return toRet;
	}
	
	public static void populate()
	{
	
		cities.add(new City(new Point(88,326), Color. BLUE, 26, true, "San Francisco")); //0, San Francisco
		cities.add(new City(new Point(169,294), Color. BLUE, 26, true, "Chicago")); //1, Chicago
		cities.add(new City(new Point(209,347), Color. BLUE, 26, true, "Atlanta")); //Atlanta
		cities.add(new City(new Point(237,294), Color. BLUE, 26, true,"Toronto")); // 3, Toronto
		cities.add(new City(new Point(286,347), Color. BLUE, 26, true, "Washington")); //Washington
		cities.add(new City(new Point(306,293), Color. BLUE, 26, true, "New York")); //New York
		cities.add(new City(new Point(421,244), Color. BLUE, 26, true, "London")); //London
		cities.add(new City(new Point(417,326), Color. BLUE, 26, true, "Madrid")); //Madrid
		cities.add(new City(new Point(477,286), Color. BLUE, 26, true, "Paris")); //Paris
		cities.add(new City(new Point(497,226), Color. BLUE, 26, true, "Essen")); // Essen
		cities.add(new City(new Point(534,274), Color. BLUE, 26, true, "Milan")); //Milan
		cities.add(new City(new Point(570,215), Color. BLUE, 26, true, "St. Petersburg")); //St. Petersburg
		
		cities.add(new City(new Point(103,384), Color. YELLOW, 26, true, "Los Angeles")); //12, Los Angeles
		cities.add(new City(new Point(170,407), Color. YELLOW, 26, true, "Mexico City")); //13, Mexico City
		cities.add(new City(new Point(263,395), Color. YELLOW, 26, true, "Miami")); //Miami
		cities.add(new City(new Point(253,455), Color. YELLOW, 26, true, "Bogota")); //Bogota
		cities.add(new City(new Point(215,527), Color. YELLOW, 26, true, "Lima")); //Lima
		cities.add(new City(new Point(240,607), Color. YELLOW, 26, true, "Santiago")); //Santiago
		cities.add(new City(new Point(309,596), Color. YELLOW, 26, true, "Buenos Aires")); //Buenos Aires
		cities.add(new City(new Point(347,539), Color. YELLOW, 26, true, "Sao Paulo")); //Sao Paulo
		cities.add(new City(new Point(480,444), Color. YELLOW, 26, true, "Lagos")); //Lagos
		cities.add(new City(new Point(518,496), Color. YELLOW, 26, true, "Kinshasa")); //Kinshasa
		cities.add(new City(new Point(554,558), Color. YELLOW, 26, true, "Johannesburg")); //Johannesburg
		cities.add(new City(new Point(569,441), Color. YELLOW, 26, true, "Khartoum")); //Khartoum
		
		cities.add(new City(new Point(483,363), Color. BLACK, 26, true, "Algiers")); //24, Algiers
		cities.add(new City(new Point(562,315), Color. BLACK, 26, true, "Istanbul")); //25, Istanbul
		cities.add(new City(new Point(619,253), Color. BLACK, 26, true, "Moscow")); //26, Moscow
		cities.add(new City(new Point(664,302), Color. BLACK, 26, true, "Tehran")); //Tehran
		cities.add(new City(new Point(614,345), Color. BLACK, 26, true, "Baghdad")); //Baghdad
		cities.add(new City(new Point(622,409), Color. BLACK, 26, true, "Riyadh")); //Riyadh
		cities.add(new City(new Point(668,362), Color. BLACK, 26, true, "Karachi")); //Karachi
		cities.add(new City(new Point(731,340), Color. BLACK, 26, true, "Delhi")); // Delhi
		cities.add(new City(new Point(683,432), Color. BLACK, 26, true, "Mumbai")); //Mumbai
		cities.add(new City(new Point(738,463), Color. BLACK, 26, true, "Chennai")); //Chennai
		cities.add(new City(new Point(784,366), Color. BLACK, 26, true, "Kolkata")); //Kolkata
		cities.add(new City(new Point(545,382), Color. BLACK, 26, true, "Cairo")); //Cairo
		
		cities.add(new City(new Point(818,278), Color. RED, 26, true, "Beijing")); //36, Beijing
		cities.add(new City(new Point(895,279), Color. RED, 26, true, "Seoul")); //37, Seoul
		cities.add(new City(new Point(958,316), Color. RED, 26, true, "Tokyo")); //Tokyo
		cities.add(new City(new Point(829,339), Color. RED, 26, true, "Shanghai")); //Shanghai
		cities.add(new City(new Point(953,367), Color. RED, 26, true, "Osaka")); //Osaka
		cities.add(new City(new Point(835,393), Color. RED, 26, true, "Hong Kong")); //Hong Kong
		cities.add(new City(new Point(907,392), Color. RED, 26, true, "Taipei")); //Taipei
		cities.add(new City(new Point(911,459), Color. RED, 26, true, "Manila")); //Manila
		cities.add(new City(new Point(847,461), Color. RED, 26, true, "Ho Chi Minh City")); //Ho Chi Minh City
		cities.add(new City(new Point(791,432), Color. RED, 26, true, "Bangkok")); //Bangkok
		cities.add(new City(new Point(797,501), Color. RED, 26, true, "Jakarta")); //Jakarta
		cities.add(new City(new Point(967,585), Color. RED, 26, true, "Sydney")); //Sydney
		
		cities.add(new City(new Point(0, 373), Color.BLACK, 0, false, "Fake"));
		cities.add(new City(new Point(0, 320), Color.BLACK, 0, false, "Fake"));
		cities.add(new City(new Point(0, 498), Color.BLACK, 0, false, "Fake"));
		cities.add(new City(new Point(1052, 320), Color.BLACK, 0, false, "Fake"));
		cities.add(new City(new Point(1052, 373), Color.BLACK, 0, false, "Fake"));
		cities.add(new City(new Point(1052, 498), Color.BLACK, 0, false, "Fake"));
		
		cities.get(2).setHasResearchCenter(true);
		
		Background b = new Background();
		
		/*for(int i=0; i<cities.size();i++)
		{
			int x = (int) cities.get(i).getCenter().getX();
			int y = (int) cities.get(i).getCenter().getY();
			cities.get(i).setCenter(new Point(x*b.getResize()/2, y*b.getResize()/2));
		}*/
	}
	
	public static void drawCities(Graphics window, int shift)
	{
		
		for(int i=0; i<cities.size(); i++)
		{
			if((cities).get(i).getIsReal())
			{
				int x=(int) cities.get(i).center.getX()-26*Background.getResize()/4+shift;
				int y=(int) cities.get(i).center.getY()-26*Background.getResize()/4;
				int width=cities.get(i).diameter*Background.getResize()/2;
				int height = width;

				
				if(cities.get(i).hover==true)
				{
					x-=2;
					y-=2;
					width+=5;
					height+=5;
				}
				
				if(cities.get(i).getColor().equals(Color.BLUE))
					window.drawImage(blue, x, y, width, height,null);
				if(cities.get(i).getColor().equals(Color.BLACK))
					window.drawImage(black, x, y, width, height,null);
				if(cities.get(i).getColor().equals(Color.RED))
					window.drawImage(red, x, y, width, height,null);
				if(cities.get(i).getColor().equals(Color.YELLOW))
					window.drawImage(yellow, x, y, width, height,null);
				window.setColor(cities.get(i).color);
				if(cities.get(i).hover==true)
				{
					String name =cities.get(i).getName();
					int counter=0;
					for(int j=0; j<name.length(); j++)
						if(name.charAt(j)==' ')
							counter++;
					if(counter<2)
						window.drawString(name, x, y);
					else
					{
						window.drawString(name+"", (x-30), y);
					}
				}
				//window.drawString("" +i,(x-12)*Background.getResize()/2,y*Background.getResize()/2);
			}
			
			
		}
		
	}
	
	public static void drawLines(Graphics window, int shift)
	{
		Graphics g2d = window.create();
		g2d.setColor(Color.WHITE);
		((Graphics2D) g2d).setStroke(new BasicStroke(2));
		for(int i=0; i<cities.size(); i++)
			for(int j=0; j<cities.get(i).getNeighbors().size(); j++)
			{
				City neighbor = cities.get(i).getNeighbors().get(j);
				g2d.drawLine((int)cities.get(i).getCenter().getX()+shift, (int)cities.get(i).getCenter().getY(), 
						(int)neighbor.getCenter().getX()+shift, (int)neighbor.getCenter().getY());
				
			}
	}
	
	public static void drawResearchCenters(Graphics window, int shift)
	{
		for(int i=0; i<cities.size(); i++)
		{
			if(cities.get(i).getHasResearchCenter())
				window.drawImage(researchCenter, (int)cities.get(i).getCenter().getX()-20, 
						(int)cities.get(i).getCenter().getY()-25, 50, 50, null);
		}
	}
	
	
	public Color getColor() {return color;}
	public Point getCenter() {return center;}
	public String getName() {return myName;}
	public void setCenter(Point c) {center=c;}
	public int getDiameter() {return diameter;}
	public static ArrayList<City> getCities() {return cities;}
	//public int getNumCubes() {return numCubes;}
	public boolean getHover() {return hover;}
	public void setHover(boolean b) {hover=b;}
	public static Image getBlue(){return blue;}
	public static Image getYellow(){return yellow;}
	public static Image getRed() {return red;}
	public static Image getBlack() {return black;}
	
	public void setNumCubes(int n, Color c) 
	{
		if(c.equals(Color.YELLOW))
			numYellow=n; 
		if(c.equals(Color.BLACK))
			numBlack=n;
		if(c.equals(Color.RED))
			numRed=n;
		if(c.equals(Color.BLUE))
			numBlue=n;
		System.out.println(this.getName() + " set to number of " + c + " cubes: " + n);
	}
	
	public ArrayList <City> getNeighbors() {return neighbors;}
	public ArrayList <City> getCrossNeighbors() {return crossNeighbors;}
	public boolean getIsReal(){return isReal;}
	public void setHasResearchCenter(boolean c) {hasResearchCenter=c;}
	public boolean getHasResearchCenter() {return hasResearchCenter;}
	public boolean hasHadRecentOutbreak;
	
	public void addCubes(int add, Color c)
	{
		if(c.equals(Color.RED))	
		{
			if(this.numRed+add>=0 &&this.numRed+add<=3) 
			{
				this.numRed=this.numRed+add;
				if(add>0&&add!=1)
					System.out.println(this.getName() + " gained " + add + " cubes");
				else if(add>0)
					System.out.println(this.getName() + " gained " + add + " cube");
			}
			else if(numRed+add>=0)
			{
				System.out.println("Outbreak from: " + this.getName());
				Infection.increaseOutbreak();
				this.hasHadRecentOutbreak = true;
				for(int i=0; i< getNeighbors().size(); i++)
				{
					if(getNeighbors().get(i).getIsReal() &&
							!getNeighbors().get(i).hasHadRecentOutbreak)
					{
						//getNeighbors().get(i).hasHadRecentOutbreak=true;
						getNeighbors().get(i).addCubes(1, Color.RED);
						//getNeighbors().get(i).hasHadRecentOutbreak=false;
					}
				}
				for(int i=0; i< getCrossNeighbors().size(); i++)
				{
					if(getCrossNeighbors().get(i).getIsReal() &&
							!getCrossNeighbors().get(i).hasHadRecentOutbreak)
					{
						//getCrossNeighbors().get(i).hasHadRecentOutbreak=true;
						getCrossNeighbors().get(i).addCubes(1, Color.RED);
						//getCrossNeighbors().get(i).hasHadRecentOutbreak=false;
					}
				}
				this.hasHadRecentOutbreak = false;
			}
		}
		
		if(c.equals(Color.BLUE))	
		{
			if(this.numBlue+add>=0 &&this.numBlue+add<=3) 
			{
				this.numBlue=this.numBlue+add;
				if(add>0&&add!=1)
					System.out.println(this.getName() + " gained " + add + " cubes");
				else if(add>0)
					System.out.println(this.getName() + " gained " + add + " cube");
			}
			else if(numBlue+add>=0)
			{
				System.out.println("Outbreak from: " + this.getName());
				Infection.increaseOutbreak();
				this.hasHadRecentOutbreak = true;
				for(int i=0; i< getNeighbors().size(); i++)
				{
					if(getNeighbors().get(i).getIsReal() &&
							!getNeighbors().get(i).hasHadRecentOutbreak)
					{
						//getNeighbors().get(i).hasHadRecentOutbreak=true;
						getNeighbors().get(i).addCubes(1, Color.BLUE);
						//getNeighbors().get(i).hasHadRecentOutbreak=false;
					}
				}
				for(int i=0; i< getCrossNeighbors().size(); i++)
				{
					if(getCrossNeighbors().get(i).getIsReal() &&
							!getCrossNeighbors().get(i).hasHadRecentOutbreak)
					{
						//getCrossNeighbors().get(i).hasHadRecentOutbreak=true;
						getCrossNeighbors().get(i).addCubes(1, Color.BLUE);
						//getCrossNeighbors().get(i).hasHadRecentOutbreak=false;
					}
				}
				this.hasHadRecentOutbreak = false;
			}
		}
		
		if(c.equals(Color.YELLOW))	
		{
			if(this.numYellow+add>=0 &&this.numYellow+add<=3) 
			{
				this.numYellow=this.numYellow+add;
				if(add>0&&add!=1)
					System.out.println(this.getName() + " gained " + add + " cubes");
				else if(add>0)
					System.out.println(this.getName() + " gained " + add + " cube");
			}
			else if(numYellow+add>=0)
			{
				System.out.println("Outbreak from: " + this.getName());
				Infection.increaseOutbreak();
				this.hasHadRecentOutbreak = true;
				for(int i=0; i< getNeighbors().size(); i++)
				{
					if(getNeighbors().get(i).getIsReal() &&
							!getNeighbors().get(i).hasHadRecentOutbreak)
					{
						//getNeighbors().get(i).hasHadRecentOutbreak=true;
						getNeighbors().get(i).addCubes(1, Color.YELLOW);
						//getNeighbors().get(i).hasHadRecentOutbreak=false;
					}
				}
				for(int i=0; i< getCrossNeighbors().size(); i++)
				{
					if(getCrossNeighbors().get(i).getIsReal() &&
							!getCrossNeighbors().get(i).hasHadRecentOutbreak)
					{
						//getCrossNeighbors().get(i).hasHadRecentOutbreak=true;
						getCrossNeighbors().get(i).addCubes(1, Color.YELLOW);
						//getCrossNeighbors().get(i).hasHadRecentOutbreak=false;
					}
				}
				this.hasHadRecentOutbreak = false;
			}
		}
		
		if(c.equals(Color.BLACK))
		{
			if(this.numBlack+add>=0 &&this.numBlack+add<=3) 
			{
				this.numBlack=this.numBlack+add;
				if(add>0&&add!=1)
					System.out.println(this.getName() + " gained " + add + " cubes");
				else if(add>0)
					System.out.println(this.getName() + " gained " + add + " cube");
			}
			else if(numBlack+add>=0)
			{
				System.out.println("Outbreak from: " + this.getName());
				Infection.increaseOutbreak();
				this.hasHadRecentOutbreak = true;
				for(int i=0; i< getNeighbors().size(); i++)
				{
					if(getNeighbors().get(i).getIsReal() &&
							!getNeighbors().get(i).hasHadRecentOutbreak)
					{
						//getNeighbors().get(i).hasHadRecentOutbreak=true;
						getNeighbors().get(i).addCubes(1, Color.BLACK);
						//getNeighbors().get(i).hasHadRecentOutbreak=false;
					}
				}
				for(int i=0; i< getCrossNeighbors().size(); i++)
				{
					if(getCrossNeighbors().get(i).getIsReal() &&
							!getCrossNeighbors().get(i).hasHadRecentOutbreak)
					{
						//getCrossNeighbors().get(i).hasHadRecentOutbreak=true;
						getCrossNeighbors().get(i).addCubes(1, Color.BLACK);
						//getCrossNeighbors().get(i).hasHadRecentOutbreak=false;
					}
				}
				this.hasHadRecentOutbreak = false;
			}
		}
		
		for (int i=0; i<City.getCities().size();i++) //check if you have used all cubes
		{
			int blue=0, yellow=0, red=0, black=0;
			for(int j=0; j<City.getCities().get(i).getBlueCubes(); j++)
				blue++;
			for(int j=0; j<City.getCities().get(i).getRedCubes(); j++)
					red++;
			for(int j=0; j<City.getCities().get(i).getBlackCubes(); j++)
					black++;
			for(int j=0; j<City.getCities().get(i).getYellowCubes(); j++)
					yellow++;
			blue+=Infection.getStoredBlue();
			red+=Infection.getStoredRed();
			yellow+=Infection.getStoredYellow();
			black+=Infection.getStoredBlack();
			if(yellow > 24 || blue > 24|| red>24 || black > 24)
			{
				System.out.println("YOU LOSE: RAN OUT OF CUBES");
				ScrollingBackground.drawLoseTimedGUI("You lose: Ran out of cubes");
				//System.exit(0);
			}
		}
	}

	
	public static void makeNeighbors() 
	{
		cities.get(0).neighbors.add(cities.get(1));
		cities.get(0).neighbors.add(cities.get(12));
		cities.get(0).neighbors.add(cities.get(48));
		cities.get(0).neighbors.add(cities.get(49));
		cities.get(0).crossNeighbors.add(cities.get(38));
		cities.get(0).crossNeighbors.add(cities.get(43));
		
		cities.get(1).neighbors.add(cities.get(0));
		cities.get(1).neighbors.add(cities.get(12));
		cities.get(1).neighbors.add(cities.get(13));
		cities.get(1).neighbors.add(cities.get(2));
		cities.get(1).neighbors.add(cities.get(3));
		
		cities.get(2).neighbors.add(cities.get(1));
		cities.get(2).neighbors.add(cities.get(4));
		cities.get(2).neighbors.add(cities.get(14));
		
		cities.get(3).neighbors.add(cities.get(1));
		cities.get(3).neighbors.add(cities.get(4));
		cities.get(3).neighbors.add(cities.get(5));
		
		cities.get(4).neighbors.add(cities.get(2));
		cities.get(4).neighbors.add(cities.get(3));
		cities.get(4).neighbors.add(cities.get(5));
		cities.get(4).neighbors.add(cities.get(14));
		
		cities.get(5).neighbors.add(cities.get(3));
		cities.get(5).neighbors.add(cities.get(4));
		cities.get(5).neighbors.add(cities.get(6));
		cities.get(5).neighbors.add(cities.get(7));
		
		cities.get(6).neighbors.add(cities.get(5));
		cities.get(6).neighbors.add(cities.get(7));
		cities.get(6).neighbors.add(cities.get(8));
		cities.get(6).neighbors.add(cities.get(9));
		
		cities.get(7).neighbors.add(cities.get(5));
		cities.get(7).neighbors.add(cities.get(6));
		cities.get(7).neighbors.add(cities.get(8));
		cities.get(7).neighbors.add(cities.get(19));
		cities.get(7).neighbors.add(cities.get(24));
		
		cities.get(8).neighbors.add(cities.get(6));
		cities.get(8).neighbors.add(cities.get(7));
		cities.get(8).neighbors.add(cities.get(9));
		cities.get(8).neighbors.add(cities.get(10));
		cities.get(8).neighbors.add(cities.get(24));
		
		cities.get(9).neighbors.add(cities.get(6));
		cities.get(9).neighbors.add(cities.get(8));
		cities.get(9).neighbors.add(cities.get(10));
		cities.get(9).neighbors.add(cities.get(11));
		
		cities.get(10).neighbors.add(cities.get(8));
		cities.get(10).neighbors.add(cities.get(9));
		cities.get(10).neighbors.add(cities.get(25));
		
		cities.get(11).neighbors.add(cities.get(9));
		cities.get(11).neighbors.add(cities.get(25));
		cities.get(11).neighbors.add(cities.get(26));
		
		cities.get(12).neighbors.add(cities.get(50));
		cities.get(12).neighbors.add(cities.get(0));
		cities.get(12).neighbors.add(cities.get(1));
		cities.get(12).neighbors.add(cities.get(13));
		cities.get(12).crossNeighbors.add(cities.get(47));
		
		cities.get(13).neighbors.add(cities.get(12));
		cities.get(13).neighbors.add(cities.get(1));
		cities.get(13).neighbors.add(cities.get(14));
		cities.get(13).neighbors.add(cities.get(15));
		cities.get(13).neighbors.add(cities.get(16));
		
		cities.get(14).neighbors.add(cities.get(2));
		cities.get(14).neighbors.add(cities.get(4));
		cities.get(14).neighbors.add(cities.get(13));
		cities.get(14).neighbors.add(cities.get(15));
		
		cities.get(15).neighbors.add(cities.get(13));
		cities.get(15).neighbors.add(cities.get(14));
		cities.get(15).neighbors.add(cities.get(16));
		cities.get(15).neighbors.add(cities.get(18));
		cities.get(15).neighbors.add(cities.get(19));
		
		cities.get(16).neighbors.add(cities.get(13));
		cities.get(16).neighbors.add(cities.get(15));
		cities.get(16).neighbors.add(cities.get(17));
		
		cities.get(17).neighbors.add(cities.get(16));
		
		cities.get(18).neighbors.add(cities.get(15));
		cities.get(18).neighbors.add(cities.get(19));
		
		cities.get(19).neighbors.add(cities.get(15));
		cities.get(19).neighbors.add(cities.get(18));
		cities.get(19).neighbors.add(cities.get(7));
		cities.get(19).neighbors.add(cities.get(20));
		
		cities.get(20).neighbors.add(cities.get(19));
		cities.get(20).neighbors.add(cities.get(21));
		cities.get(20).neighbors.add(cities.get(23));
		
		cities.get(21).neighbors.add(cities.get(20));
		cities.get(21).neighbors.add(cities.get(23));
		cities.get(21).neighbors.add(cities.get(22));
		
		cities.get(22).neighbors.add(cities.get(21));
		cities.get(22).neighbors.add(cities.get(23));
		
		cities.get(23).neighbors.add(cities.get(20));
		cities.get(23).neighbors.add(cities.get(21));
		cities.get(23).neighbors.add(cities.get(22));
		cities.get(23).neighbors.add(cities.get(35));
		
		cities.get(24).neighbors.add(cities.get(7));
		cities.get(24).neighbors.add(cities.get(8));
		cities.get(24).neighbors.add(cities.get(25));
		cities.get(24).neighbors.add(cities.get(35));
		
		cities.get(25).neighbors.add(cities.get(10));
		cities.get(25).neighbors.add(cities.get(11));
		cities.get(25).neighbors.add(cities.get(24));
		cities.get(25).neighbors.add(cities.get(26));
		cities.get(25).neighbors.add(cities.get(35));
		cities.get(25).neighbors.add(cities.get(28));
		
		cities.get(26).neighbors.add(cities.get(11));
		cities.get(26).neighbors.add(cities.get(25));
		cities.get(26).neighbors.add(cities.get(27));
		
		cities.get(27).neighbors.add(cities.get(26));
		cities.get(27).neighbors.add(cities.get(28));
		cities.get(27).neighbors.add(cities.get(30));
		cities.get(27).neighbors.add(cities.get(31));
		
		cities.get(28).neighbors.add(cities.get(25));
		cities.get(28).neighbors.add(cities.get(27));
		cities.get(28).neighbors.add(cities.get(35));
		cities.get(28).neighbors.add(cities.get(30));
		cities.get(28).neighbors.add(cities.get(29));
		
		cities.get(29).neighbors.add(cities.get(35));
		cities.get(29).neighbors.add(cities.get(28));
		cities.get(29).neighbors.add(cities.get(30));
		
		cities.get(30).neighbors.add(cities.get(27));
		cities.get(30).neighbors.add(cities.get(28));
		cities.get(30).neighbors.add(cities.get(29));
		cities.get(30).neighbors.add(cities.get(31));
		cities.get(30).neighbors.add(cities.get(32));
		
		cities.get(31).neighbors.add(cities.get(27));
		cities.get(31).neighbors.add(cities.get(30));
		cities.get(31).neighbors.add(cities.get(32));
		cities.get(31).neighbors.add(cities.get(33));
		cities.get(31).neighbors.add(cities.get(34));
		
		cities.get(32).neighbors.add(cities.get(30));
		cities.get(32).neighbors.add(cities.get(31));
		cities.get(32).neighbors.add(cities.get(33));
		
		cities.get(33).neighbors.add(cities.get(31));
		cities.get(33).neighbors.add(cities.get(32));
		cities.get(33).neighbors.add(cities.get(34));
		cities.get(33).neighbors.add(cities.get(45));
		cities.get(33).neighbors.add(cities.get(46));
		
		cities.get(34).neighbors.add(cities.get(31));
		cities.get(34).neighbors.add(cities.get(33));
		cities.get(34).neighbors.add(cities.get(41));
		cities.get(34).neighbors.add(cities.get(45));
		
		cities.get(35).neighbors.add(cities.get(23));
		cities.get(35).neighbors.add(cities.get(24));
		cities.get(35).neighbors.add(cities.get(25));
		cities.get(35).neighbors.add(cities.get(28));
		cities.get(35).neighbors.add(cities.get(29));
		
		cities.get(36).neighbors.add(cities.get(37));
		cities.get(36).neighbors.add(cities.get(39));
		
		cities.get(37).neighbors.add(cities.get(36));
		cities.get(37).neighbors.add(cities.get(38));
		cities.get(37).neighbors.add(cities.get(39));
		
		cities.get(38).neighbors.add(cities.get(37));
		cities.get(38).neighbors.add(cities.get(39));
		cities.get(38).neighbors.add(cities.get(40));
		cities.get(38).neighbors.add(cities.get(51));
		cities.get(38).crossNeighbors.add(cities.get(0));
		
		cities.get(39).neighbors.add(cities.get(36));
		cities.get(39).neighbors.add(cities.get(37));
		cities.get(39).neighbors.add(cities.get(38));
		cities.get(39).neighbors.add(cities.get(41));
		cities.get(39).neighbors.add(cities.get(42));
		
		cities.get(40).neighbors.add(cities.get(38));
		cities.get(40).neighbors.add(cities.get(42));
		
		cities.get(41).neighbors.add(cities.get(34));
		cities.get(41).neighbors.add(cities.get(39));
		cities.get(41).neighbors.add(cities.get(42));
		cities.get(41).neighbors.add(cities.get(43));
		cities.get(41).neighbors.add(cities.get(44));
		cities.get(41).neighbors.add(cities.get(45));
		
		cities.get(42).neighbors.add(cities.get(39));
		cities.get(42).neighbors.add(cities.get(40));
		cities.get(42).neighbors.add(cities.get(41));
		cities.get(42).neighbors.add(cities.get(42));
		cities.get(42).neighbors.add(cities.get(43));
		
		cities.get(43).neighbors.add(cities.get(41));
		cities.get(43).neighbors.add(cities.get(42));
		cities.get(43).neighbors.add(cities.get(44));
		cities.get(43).neighbors.add(cities.get(47));
		cities.get(43).neighbors.add(cities.get(52));
		cities.get(43).crossNeighbors.add(cities.get(0));
		
		cities.get(44).neighbors.add(cities.get(41));
		cities.get(44).neighbors.add(cities.get(43));
		cities.get(44).neighbors.add(cities.get(45));
		cities.get(44).neighbors.add(cities.get(46));
		
		cities.get(45).neighbors.add(cities.get(34));
		cities.get(45).neighbors.add(cities.get(33));
		cities.get(45).neighbors.add(cities.get(41));
		cities.get(45).neighbors.add(cities.get(44));
		cities.get(45).neighbors.add(cities.get(46));
		
		cities.get(46).neighbors.add(cities.get(33));
		cities.get(46).neighbors.add(cities.get(44));
		cities.get(46).neighbors.add(cities.get(45));
		cities.get(46).neighbors.add(cities.get(47));
		
		cities.get(47).neighbors.add(cities.get(43));
		cities.get(47).neighbors.add(cities.get(46));
		cities.get(47).neighbors.add(cities.get(53));
		cities.get(47).crossNeighbors.add(cities.get(12));
		
		
	}
	
	public static boolean clickedCity(City city, int xPos, int yPos)
	{
		boolean toRet=false;
		int cityX = (int) city.getCenter().getX();
		int cityY = (int) city.getCenter().getY();
		int k = (xPos-cityX)*(xPos-cityX);
        int z = (yPos-cityY)*(yPos-cityY);
        double m = Math.sqrt(k + z);
		if(city.getIsReal() && m <= city.getDiameter())
		{
			toRet=true;
		}
		return toRet;
	}
	
/*	public static boolean inCity(City city, int xPos, int yPos)
	{
		if(city.getIsReal())
		{
			int cityX = (int) city.getCenter().getX();
			int cityY = (int) city.getCenter().getY();
			
			int k = (xPos-cityX)*(xPos-cityX);
            int z = (yPos-cityY)*(yPos-cityY);
            double m = Math.sqrt(k + z);
       
            if (m <= city.getDiameter())
            	return true;
            else
            	return false;
		}
		else
			return false;
	}*/

}
