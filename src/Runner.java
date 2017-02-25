import java.awt.Component;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Runner extends JFrame 
{
	ArrayList<String> nums = new ArrayList();
	ArrayList<String> levels = new ArrayList();
	
	public Runner() 
	{
		
		super("Pandemic");
		City.populate();
		CardImage.populatePictures();
		Cube.findCubes();
		City.makeNeighbors();
		PlayerDeck.populateSpecials();
		setSize(535*Background.getResize(), 400*Background.getResize());
		setLocation(180,100);
		ScrollingBackground back = new ScrollingBackground();
		((Component)back).setFocusable(true);
		getContentPane().add(back);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		populateNums();
		populateLevels();
		Pawn.populateRoles();
		ScrollingBackground.drawTopGUI("Choose the number of players", nums);
		ScrollingBackground.drawTopGUI("Choose your level", levels);
	
		
		
	}
	
	public static void main(String[] args)
	{
		new Runner();
	}
	
	public void populateNums()
	{
		for(int i=2; i<6; i++)
		{
			nums.add(""+i +" Players");
		}
		 nums.add("Return to level selection");
	}
	
	public void populateLevels()
	{
		levels.add("Introductory: 4");
		levels.add("Standard: 5");
		levels.add("Heroic: 6");
	}
	

	
	


}