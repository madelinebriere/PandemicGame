import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;


public class ScrollingBackground extends Canvas implements Runnable
{
	
	//Two copies of the background image to scroll
	private Background backOne;
	private Background backTwo;
	
	private static int ex;
	private static int why;
	
	private static int mouseMoveX;
	private static int mouseMoveY;
	
	private BufferedImage back;
	
	private boolean left, stopped=true;
	private Graphics g;
	private static boolean startMenu=true;
	private static boolean playerNames=false;
	
	private static boolean researchCenter=false;
	private static boolean cure=false;
	
	private static Image arch =Toolkit.getDefaultToolkit().getImage("ArchivistPawn.png");
	

	private static ArrayList<ButtonBox> popups = new ArrayList();
	
	public static ArrayList<ButtonBox> getPopups (){return popups;}
	
	public static boolean donut;
	public static boolean getDonut(){return donut;}
	
	public static boolean cat;
	public static boolean getCat(){return cat;}
	

	
	 
	
	
	public ScrollingBackground()
	{
		addMouseListener(new CubeListener());
		addMouseMotionListener(new MoveListener());
		addKeyListener(new Keys());
		
		backOne = new Background();
		backTwo = new Background(backOne.getImageWidth()*Background.getResize(), 0);
		
		new Thread(this).start();
		setVisible(true);
		
		
	}
	
	
	@Override
	public void run() 
	{
		try {
			while (true)
			{
				Thread.currentThread().sleep(5);
				repaint();
			}
		}
		catch(Exception e) {}
	}
	
	@Override
	public void update(Graphics window) 
	{
		paint(window);
	}
	
	public void paint(Graphics window) 
	{
		
		Graphics2D twoD = (Graphics2D)window;
		
		if(back == null)
			back = (BufferedImage)(createImage(getWidth(), getHeight()));
		
		//Create a buffer to draw to
		Graphics buffer = back.createGraphics();
		
		backOne.draw(buffer);
			
		//Draw the image onto the window
		twoD.drawImage(back, null, 0, 0);
		
		
		
	}
	
	public void checkHover()
	{
		/*
		 * Cities
		 */
		for(int i=0; i<City.getCities().size(); i++)
		{
			int cityX = (int) City.getCities().get(i).getCenter().getX();
			int cityY = (int) City.getCities().get(i).getCenter().getY();
			
			int k = (mouseMoveX-cityX)*(mouseMoveX-cityX);
            int z = (mouseMoveY-cityY)*(mouseMoveY-cityY);
            
            double m = Math.sqrt(k + z);
		
            if (m <= City.getCities().get(i).getDiameter())
            {
            	City.getCities().get(i).setHover(true);
            	
            }
            else
            	City.getCities().get(i).setHover(false);
		}
		
		/*
		 * Player Cards
		 */
		{
			for(int i=0; i<Pawn.getPawns().size();i++)
			{
				Pawn pawn= Pawn.getPawns().get(i);
				for(int k=0; k< pawn.getHandNames().size(); k++)
				{
					Card c = pawn.getMyHand().get(k);
					int x = 55+k*45;
					int y =10+55*i;
					Rectangle r = new Rectangle (x, y, 35, 50); //Rectangle where card is
					if(r.contains(mouseMoveX, mouseMoveY))
					{
						c.setHover(true);
						for(int m=0; m<City.getCities().size();m++)
						{
							if(City.getCities().get(m).equals(c))
								City.getCities().get(m).setHover(true);
						}
					}
					else
						c.setHover(false);
				}
				Rectangle r = new Rectangle(10,10+i*55,35,50);
				if(r.contains(mouseMoveX, mouseMoveY))
				{
					pawn.setRoleHover(true);
				}
				else
					pawn.setRoleHover(false);
				
			}
			
		}
		
	
	}
	
	public String toString2(ArrayList<Card> cards)
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
	public static void drawEndTurnGUI()
	{
		ArrayList<String>toRet = new ArrayList();
		toRet.add("Yes, end my turn");
		/*for(int k=0; k<Pawn.whoseTurn().getMyHand().size();k++)
		{
			if(Pawn.whoseTurn().getMyHand().get(k).getType().equals("SpecialEvent"))
			{
				toRet.add("Last Chance Special Event: "+ Pawn.whoseTurn().getMyHand().get(k).getID());
			}
		}*/
		ButtonBox frm = new ButtonBox(toRet, "End your turn?", true);
	    frm.setSize(300,150);  
	    frm.setAlwaysOnTop(true);
	    frm.setLocation(500,300);
	    frm.setVisible( true );
	    popups.add(frm);
	}
	
	public static void drawVoluntaryEndTurnGUI()
	{
		ArrayList<String>toRet = new ArrayList();
		toRet.add("Yes, end my turn");
		toRet.add("No, do not end my turn");
		ButtonBox frm = new ButtonBox(toRet, "End your turn?");
	    frm.setSize(300,150);  
	    frm.setLocation(500,300);
	    frm.setVisible( true );
	    popups.add(frm);
	}
	
	public static  void drawGUI(ArrayList<String> options, Pawn pawn, int k) // k=index of card in hand clicked
	{
		ButtonBox frm = new ButtonBox(options, pawn, k);
	    frm.setSize(300,150);  
	    frm.setLocation(500,300);
	    frm.setVisible( true );
	    popups.add(frm);
	}

	
	public static  void drawStartGUI(String title, ArrayList<String> options)
	{
		ButtonBox frm = new ButtonBox(options, title);
	    frm.setSize(300,150);  
	    frm.setLocation(500,300);
	    frm.setVisible( true );
	    popups.add(frm);
	}
	
	public static  void drawTopGUI(String title, ArrayList<String> options)
	{
		ButtonBox frm = new ButtonBox(options, title, true);
		frm.setAlwaysOnTop(true);
		frm.setPerm(true);
		int ySize=200;
		if(options.size()>=10)
			ySize=300;
		if(options.size()>=20)
			ySize=400;
		if(options.size()>=30)
			ySize=500;
	    frm.setSize(300,ySize);  
	    frm.setLocation(500,300);
	    frm.setVisible(true);
	    popups.add(frm);
	}
	
	public static  void drawLargeTopGUI(String title, ArrayList<String> options)
	{
		ButtonBox frm = new ButtonBox(options, title, true);
		frm.setAlwaysOnTop(true);
		frm.setPerm(true);
	    frm.setSize(300,350);  
	    frm.setLocation(500,300);
	    frm.setVisible(true);
	    popups.add(frm);
	}
	
	public static  void drawExtraLargeTopGUI(String title, ArrayList<String> options)
	{
		ButtonBox frm = new ButtonBox(options, title, true);
		frm.setAlwaysOnTop(true);
		frm.setPerm(true);
	    frm.setSize(600,600);  
	    frm.setLocation(400,300);
	    frm.setVisible(true);
	    popups.add(frm);
	}
	
	/*public static  void drawPermGUI(String title, ArrayList<String> options)
	{
		ButtonBox frm = new ButtonBox(options, title);
		frm.setPerm(true);
	    frm.setSize(400,300);  
	    frm.setLocation(300,300);
	    frm.setVisible( true );
	    popups.add(frm);
	}*/
	
	public static  void drawLargeStartGUI(String title, ArrayList<String> options)
	{
		ButtonBox frm = new ButtonBox(options, title);
	    frm.setSize(400,300);  
	    frm.setLocation(500,300);
	    frm.setVisible( true );
	    popups.add(frm);
	}
	
	public static void drawInstructions()
	{
		ButtonBox frm = new ButtonBox();
		frm.setAlwaysOnTop(true);
		frm.setPerm(true);
	    frm.setSize(1200,500);  
	    frm.setLocation(100,100);
	    frm.setVisible( true );
	    popups.add(frm);
	}
	
	public static void drawStats()
	{
		ArrayList<String>options = new ArrayList();
		int red=0;
		int blue=0;
		int yellow=0;
		int black=0;
		for(int i=0; i<City.getCities().size();i++)
		{
			red+=City.getCities().get(i).getRedCubes();
			blue+=City.getCities().get(i).getBlueCubes();
			black+=City.getCities().get(i).getBlackCubes();
			yellow+=City.getCities().get(i).getYellowCubes();
			
		}
		options.add("Number of red cubes on board: "+ red);
		options.add("Number of blue cubes on board: "+blue);
		options.add("Number of yellow cubes on board: "+yellow);
		options.add("Number of black cubes on board: "+black);
		options.add("Number of player cards left: "+PlayerDeck.getPlayerDeck().size());
		options.add("Click to look at infection discard deck");
		ButtonBox frm = new ButtonBox(options, "Current stats");
	    frm.setSize(600,500);  
	    frm.setLocation(400,100);
	    frm.setVisible( true);
	    popups.add(frm);
	}
	
	public static  void drawRoleGUI(String title, ArrayList<String> options)
	{
		ArrayList<String> roleChoice = new ArrayList(options);
		roleChoice.add("See player details");
		ButtonBox frm = new ButtonBox(roleChoice, title, true);
		frm.setAlwaysOnTop(true);
		frm.setPerm(true);
		int ySize=200;
		if(options.size()>=10)
			ySize=300;
		if(options.size()>=20)
			ySize=400;
		if(options.size()>=30)
			ySize=500;
	    frm.setSize(300,ySize);  
	    frm.setLocation(500,300);
	    frm.setVisible(true);
	    popups.add(frm);
	}
	
	public static void drawWinTimedGUI(String winOrLose)
	{
		ButtonBox frm = new ButtonBox(winOrLose);
	    frm.setSize(300,150);  
	    frm.setLocation(500,300);
	    frm.setVisible( true );
		try
		{
			Thread thread = new Thread();
			thread.sleep(1000);
		}
		catch(Exception e){}
		System.exit(0);
		
	}
	
	public static void drawLoseTimedGUI(String winOrLose)
	{
		ButtonBox frm = new ButtonBox(winOrLose);
	    frm.setSize(300,150);  
	    frm.setLocation(500,300);
	    frm.setVisible( true );

		try
		{
			Thread thread = new Thread();
			thread.sleep(1000);
		}
		catch(Exception e){}
		System.exit(0);
		
	}
	
	public static void drawTimedMessageGUI(String message)
	{
		ButtonBox frm = new ButtonBox(message);
	    frm.setSize(300,150);  
	    frm.setLocation(500,300);
	    frm.setVisible( true );
		try
		{
			Thread thread = new Thread();
			thread.sleep(1000);
		}
		catch(Exception e){}
		frm.setVisible(false);
		
	}
	public static void resetPopups()
	{
		boolean pop=false;
		for(int i=0; i<popups.size();i++)
		{
			if(!popups.get(i).getPerm())	
			{
				popups.get(i).setVisible(false);
			}
			else if(popups.get(i).isVisible())
			{
				pop=true;
			}
		}
		Infection.setPop(pop);
	}
	
	/*public static void renewPermPops()
	{
		for(int i=0; i<popups.size();i++)
		{
			if(popups.get(i).getPerm())	
			{
				popups.get(i).setVisible(true);
				Infection.setPop(true);
				boolean temp=false;
				for(int k=0; k<Pawn.getPawns().size();k++)
					if(Pawn.getPawns().get(k).getNeed())
						temp=true;
				popups.get(i).setPerm(temp);
			}
		}
	}*/

	private class MoveListener implements MouseMotionListener
	{

		
		public void mouseDragged(MouseEvent e) 
		{
			
			
		}

		
		public void mouseMoved(MouseEvent e)
		{
			mouseMoveX=e.getX();
			mouseMoveY=e.getY();
			checkHover();
		}
		
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
	
	private class CubeListener implements MouseListener 
	//implements leads to interface, given class with nothing but method headers
	{
		
		
		
		public void mouseClicked(MouseEvent eve) 
		{
			
			
		}
		public void mouseEntered(MouseEvent eve) 
		{
			
		}

		public void mouseExited(MouseEvent eve) 
		{
			
			
		}

		
	
		public void mousePressed(MouseEvent eve) 
		{
			
			ex=eve.getX();
			why=eve.getY();
			Infection in =new Infection();
			resetPopups();
			if(!Infection.getPop()) //if there is nothing else going on
				in.movePawns(ex, why);
			else
				for(int i=0; i<popups.size(); i++)
					if(popups.get(i).getPerm())
						popups.get(i).toFront(); // bring permanent popups to the focus
			//renewPermPops();
			/*System.out.println("Infection Deck: " +toString2(InfectionDeck.getInfectionDeck()));
			System.out.println("Infection Discard: " + toString2(InfectionDeck.getInfectionDiscardDeck()));
			System.out.println("Player Deck: " + toString2(PlayerDeck.getPlayerDeck()));
			System.out.println("Player Discard: " + toString2(PlayerDeck.getDiscardDeck()));
			repaint();*/
			
			
			
			
		}

		public void mouseReleased(MouseEvent eve) 
		{
						
		}
	
	}
	
	private class Keys implements KeyListener
	{

		
		public void keyPressed(KeyEvent arg0) 
		{
			if(arg0.getKeyCode() == KeyEvent.VK_D)
			{
				if(donut)
					donut=false;
				else
					donut=true;
			}
			if(arg0.getKeyCode() == KeyEvent.VK_C)
			{
				if(cat)
					cat=false;
				else
					cat=true;
			}
			
		}

		
		public void keyReleased(KeyEvent arg0) 
		{
			
			
		}

		
		public void keyTyped(KeyEvent arg0) 
		{
			
			
		}
		
	}
	
	
	
	
	
	public static int getEx() {return ex;}
	public static int getWhy() {return why;}
	public static boolean getStartMenu() {return startMenu;}
	public static boolean getPlayerNames() {return playerNames;}
	public static boolean getResearchCenter() {return researchCenter;}
	public static void setResearchCenter(boolean c) {researchCenter=c;}
	public static boolean getCure() {return cure;}
	public static void setCure(boolean c) {cure=c;}
}