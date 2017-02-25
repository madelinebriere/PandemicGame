import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;



public class Background 
{
	private BufferedImage image, imageOriginal;
	private int x,y;
	private static int resize=2;
	static boolean  startDone=false;
	//private static double fluff=resize/2.2727272727;
	private static Image loser =Toolkit.getDefaultToolkit().getImage("Over.png");
	
	private static Image arch =Toolkit.getDefaultToolkit().getImage("ArchivistPawn.png");
	
	
	public static void setStartDone(boolean c){startDone=c;}
	
	public static boolean drawRoles;
	public static void setDrawRoles(boolean c){drawRoles=c;};
	
	public Background()
	{
		this(0,0);
	}
	
	public Background(int x, int y)
	{
		this.x=x;
		this.y=y;
		
		//Try to open the image file background
		try
		{
			image=ImageIO.read(new File("background.png"));
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		
		
	}
	
	
	/**
	 * Method that draws the image onto the Graphics object passed
	 * @param window
	 */
	
	
	
	public void draw(Graphics window)
	{
		window.drawImage(image, getX(),getY(), (int)(resize*image.getWidth()), (int)(resize*image.getHeight()), null);
		Infection.drawOutbreak(window, x);
		City.drawLines(window, x);
		City.drawCities(window, x);
		City.drawResearchCenters(window,x);
		Cube.placeCubes(window, x);
		Infection.drawCures(window, x);
		Infection.drawSpecialMove(window,x);
		Infection.drawInstruction(window, x);
		Infection.drawCurrentStats(window, x);
		Infection.drawEndTurn(window,x);
		if(drawRoles == true)
			Infection.drawRoles(window, x);
		if(Pawn.getPawns().size()==Pawn.getNumPlayers() && startDone)
		{
			drawRoles=false;
			Card.drawCards2(window, x);
			Pawn.drawPawns(window,x);
			Pawn.drawNumMoves(window);
		}
		if(Infection.getLost())
		{
			for(int i=0; i<ScrollingBackground.getPopups().size();i++)
			{
				ScrollingBackground.getPopups().get(i).setVisible(false);
			}
			ScrollingBackground.drawLoseTimedGUI("You lose");
			try
			{
				Thread thread = new Thread();
				thread.sleep(1000);
			}
			catch(Exception e){}
			System.exit(0);
			
		}
		
	}
	
	
	

	
	public void setX(int x) {this.x=x;}
	public int getX() {return this.x;}
	public int getY() {return this.y;}
	public int getImageWidth() {return image.getWidth();}
	public static int getResize() {return resize;}

	public static boolean getStartDone() {
		// TODO Auto-generated method stub
		return startDone;
	}
	
	
	

	
	
}
