import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Cube 
{
	private int size;
	private Color color;
	private boolean removable;
	private double alpha = 30;
	private int cubeX, cubeY;

	public Cube(int s, int x, int y, Color c)
	{
		size = s;
		cubeX = x;
		cubeY = y;
		color = c;
		removable = false;
	}
	
	public Cube()
	{
		size = 10;
		cubeX = 0;
		cubeY = 0;
		color = Color.BLUE;
		removable = false;
	}
	
	public int getSize() {return size;}
	public Color getColor() {return color;}
	public int getCubeX() {return cubeX;}
	public int getCubeY() {return cubeY;}
	public boolean getRemovable() {return removable;}
	
	public void setSize(int s) {size = s;}
	public void setColor(Color c) {color = c;}
	public void setCubeX(int newX) {cubeX = newX;}
	public void setCubeY(int newY) {cubeY = newY;}
	public void setRemovable(boolean val) {removable = val;}

	
	public void drawOneCube(Graphics g, Cube cube)
	{
		int x = cube.getCubeX();
		int y = cube.getCubeY();
		int s = cube.getSize(); 
		double a = Math.toRadians(alpha);
		
	//	Graphics2D g2 = (Graphics2D) g;
	//	g2.setStroke(new BasicStroke(strokeWidth));
	
		int[] x1 = {x, x + (int)(s*Math.cos(a)), x + (int)(s*Math.cos(a)), x};
		int[] y1 = {y, y - (int)(s*Math.sin(a)), y - (int)(s*Math.sin(a)) + s, y + s};
		int[] x2 = {x, x + (int)(s*Math.cos(a)), x , x - (int)(s*Math.cos(a))};
		int[] y2 = {y, y - (int)(s*Math.sin(a)), y - (int)(2*s*Math.sin(a)), y - (int)(s*Math.sin(a))};
		int[] x3 = {x, x - (int)(s*Math.cos(a)), x - (int)(s*Math.cos(a)), x};
		int[] y3 = {y, y - (int)(s*Math.sin(a)), y - (int)(s*Math.sin(a)) + s, y + s}; 
		g.setColor(cube.getColor());
		g.fillPolygon(x1, y1, 4);
		g.fillPolygon(x2, y2, 4);
		g.fillPolygon(x3, y3, 4);
		if(cube.getColor().equals(Color.yellow))
			g.setColor(Color.black);
		else
			g.setColor(Color.white);
		g.drawPolygon(x1, y1, 4);
		g.drawPolygon(x2, y2, 4);
		g.drawPolygon(x3, y3, 4);
	}	
/*	
	public boolean isTransparent(Cube cube)
	{
		int x = (int)cube.getPoint().getX();
		int y = (int)cube.getPoint().getY();
		targetRadius = cube.getSize()  * 85 /100;
		return (((mouseX - x)*(mouseX - x) + (mouseY - y)*(mouseY - y)) < targetRadius*targetRadius && cube.getRemovable());
	} 
*/	
	public void drawCubes(Graphics g, Cube cube, int numCubes) 
	{
		int x = cube.getCubeX();
		int y = cube.getCubeY();
		cube.setSize(cube.getSize()*Background.getResize()/2);
		int s = cube.getSize();
		double a = Math.toRadians(alpha);
		
		
		if(numCubes == 1)
		{
			cube.setRemovable(true);
	//		if(!isTransparent(cube))
				drawOneCube(g, cube);
	//		else
	//			drawOneCube(g, new Cube(cube.getSize(), cube.getPoint(), 
	//					new Color(cube.getColor().getRed(), cube.getColor().getGreen(), cube.getColor().getBlue(), 180)));
		}
		if(numCubes == 2 )
		{
			cube.setCubeX(getCubeX()-4);
			cube.setCubeY(getCubeY()-2);
			x = cube.getCubeX();
			y = cube.getCubeY();
			cube.setRemovable(false);
			drawOneCube(g, cube);
			Cube secondCube = new Cube(cube.getSize(), x + (int)(s*Math.cos(a)), y + (int)(s*Math.sin(a)), cube.getColor());
			drawOneCube(g, secondCube);
		}
		if(numCubes == 3 /*&& !threat*/)
		{
			cube.setCubeX(getCubeX()-8);
			cube.setCubeY(getCubeY()-4);
			x = cube.getCubeX();
			y = cube.getCubeY();
			drawOneCube(g, cube);
			drawOneCube(g, new Cube(cube.getSize(), x + (int)(2*s*Math.cos(a)) -1/*fudge*/, y, cube.getColor()));
			drawOneCube(g, new Cube(cube.getSize(), x + (int)(s*Math.cos(a)), y + (int)(s*Math.sin(a)), cube.getColor()));  
		}
	/*	if(numCubes == 3 && threat)
		{
			drawOneCube(g, cube); //1
			drawOneCube(g, new Cube(cube.getSize(), new Point(x + (int)(s*Math.cos(a)), y + (int)(s*Math.sin(a))), cube.getColor())); //2
			if(!glowtrue) 
				drawOneCube(g, new Cube(cube.getSize(), new Point(x, y - (int)(2*s*Math.sin(a))), cube.getColor())); //3
			else
			{
				Color col = cube.getColor();
				drawOneCube(g, new Cube(cube.getSize(), new Point(x, y - (int)(2*s*Math.sin(a))), new Color(col.getRed(), col.getGreen(), col.getBlue(), 180))); //3
			}
		} */
		if(numCubes>3)
		{
			drawCubes(g, cube, 3);
			drawCubes(g, new Cube(cube.getSize(), x+30, y, color), numCubes-3);
		}
	}
	
	public static void findCubes() //draws start cubes
	{
		ArrayList <Card> cards = InfectionDeck.getStartInfectionDeck();
		Infection infection = new Infection();
		for(int i=0; i<3;i++)
		{
			Card card = InfectionDeck.infectTop(Card.getInfectionDiscarding(), 
					InfectionDeck.getInfectionDeck());
			String name = card.getID();
			//InfectionDeck.discardCard(card);
			card.setDiscard(true);
			//card.getCardImage().setX(760);
			for(int j=0; j <City.getCities().size();j++)
			{
				if(City.getCities().get(j).getName().equals(name))
				{
					City.getCities().get(j).addCubes(3, City.getCities().get(j).getColor());
					
					
				}
			}
		}
		
		for(int i=0; i<3;i++)
		{
			Card card = InfectionDeck.infectTop(Card.getInfectionDiscarding(), 
					InfectionDeck.getInfectionDeck());
			String name = card.getID();
			//card.getCardImage().setX(760);
			card.setDiscard(true);
			for(int j=0; j <City.getCities().size();j++)
			{
				if(City.getCities().get(j).getName().equals(name))
				{
					City.getCities().get(j).addCubes(2, City.getCities().get(j).getColor());
				}
			}
		}
		
		for(int i=0; i<3;i++)
		{
			Card card = InfectionDeck.infectTop(Card.getInfectionDiscarding(), 
					InfectionDeck.getInfectionDeck());
			String name = card.getID();
			//card.getCardImage().setX(760);
			card.setDiscard(true);
			for(int j=0; j <City.getCities().size();j++)
			{
				if(City.getCities().get(j).getName().equals(name))
				{
					City.getCities().get(j).addCubes(1, City.getCities().get(j).getColor());
				}
			}
		}
		
		
	}
	
	public static void placeCubes(Graphics g, int shift) //draws cubes each round
	{
		for (int i=0; i<City.getCities().size(); i++)
		{
			if(City.getCities().get(i).getRedCubes()>0)
			{
				int ex = (int) City.getCities().get(i).getCenter().getX()+shift+5;
				int why = (int) City.getCities().get(i).getCenter().getY();
				Cube cube = new Cube(15, ex, why, Color.RED);
				int num = City.getCities().get(i).getRedCubes();
				cube.drawCubes(g,cube, num);
			}
			if(City.getCities().get(i).getBlackCubes()>0)
			{
				int ex = (int) City.getCities().get(i).getCenter().getX()+shift-5;
				int why = (int) City.getCities().get(i).getCenter().getY();
				Cube cube = new Cube(15, ex, why, Color.BLACK);
				int num = City.getCities().get(i).getBlackCubes();
				cube.drawCubes(g,cube, num);
			}
			if(City.getCities().get(i).getYellowCubes()>0)
			{
				int ex = (int) City.getCities().get(i).getCenter().getX()+shift;
				int why = (int) City.getCities().get(i).getCenter().getY()+5;
				Cube cube = new Cube(15, ex, why, Color.YELLOW);
				int num = City.getCities().get(i).getYellowCubes();
				cube.drawCubes(g,cube, num);
			}
			if(City.getCities().get(i).getBlueCubes()>0)
			{
				int ex = (int) City.getCities().get(i).getCenter().getX()+shift;
				int why = (int) City.getCities().get(i).getCenter().getY()-5;
				Cube cube = new Cube(15, ex, why, Color.BLUE);
				int num = City.getCities().get(i).getBlueCubes();
				cube.drawCubes(g,cube, num);
			}
		}
	}
}
