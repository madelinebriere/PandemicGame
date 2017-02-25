import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.imageio.ImageIO;


public class CardImage 
{
	private int xPos, yPos, width, height;
	private Image image;
	private Image image2;
	private Image currentImage;
	private static Image playerBack;
	private static Image infectionBack;

	
	public CardImage(int x, int y, int w, int h, String imageName, boolean infection)
	{
		xPos=x;
		yPos=y;
		width=w;
		height=h;
		try
		{
			currentImage=ImageIO.read(new File(imageName));
			
		}
		catch (Exception e)
		{
			System.out.println(e + imageName);
		}
	
	}
	
	public CardImage(int x, int y, int w, int h, String imageName)
	{
		xPos=x;
		yPos=y;
		width=w;
		height=h;
		try
		{
			currentImage=ImageIO.read(new File(imageName));
			//image=Toolkit.getDefaultToolkit().getImage(imageName);
		}
		catch (Exception e)
		{
			System.out.println(e + imageName);
		}
		
	}
	
	public static void populatePictures()
	{
		try
		{
			playerBack=ImageIO.read(new File("PlayerBack.png"));
			infectionBack=ImageIO.read(new File("InfectionBack.png"));
			
		}
		catch (Exception e){}
	}
	
	public void setX(int x) {xPos=x;}
	public void setY(int y) {yPos=y;}
	public int getX() {return xPos;}
	public int getY() {return yPos;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public Image getImage(){return image;}
	public void setImage(Image i) {image = i;}
	public Image getImage2(){return image2;}
	public void setImage2(Image i) {image2 = i;}
	public Image getCurrentImage(){return currentImage;}
	public void setCurrentImage(Image i){currentImage=i;}
	public static Image getPlayerBack(){return playerBack;}
	public static Image getInfectionBack(){return infectionBack;}
}
