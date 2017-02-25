import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Popup implements ActionListener
{
	JTextArea output;
	JScrollPane scrollPane;
	//ImageIcon researchCenterIcon = new ImageIcon("ResearchCenter.png");
	int reWidth = 30;
	int reHeight = 30;
	
	public Container createContentPane()
	{
		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.setOpaque(true);

		//create a scrolled text area
		output = new JTextArea(5, 30);
		output.setEditable(false);
		scrollPane = new JScrollPane(output);
		
		//add the text area to the content pane
		contentPane.add(scrollPane, BorderLayout.CENTER);
		return contentPane;
	}
	
	public void createPopupMenu(String[] names)
	{
		JPopupMenu popup = new JPopupMenu();
		
		for(int i=0; i<names.length; i++)
		{
		
		JMenuItem menuItem;
		String name = names[i];
		
		menuItem = new JMenuItem(name);
		menuItem.addActionListener(this);
		popup.add(menuItem);
		
		}
	
		MouseListener popupListener = new PopupListener(popup);
		output.addMouseListener(popupListener);
	}
	
	/*
	 * Resizing code
	 */

	public ImageIcon resizeImage(ImageIcon original)
	{
		Image img = original.getImage();
		BufferedImage rescaled = new BufferedImage(reWidth, reHeight, 
				BufferedImage.TYPE_INT_ARGB);
		Graphics g = rescaled.createGraphics(); // I didn't know that you could create a Graphics object in this manner
		g.drawImage(img, 0, 0, reWidth, reHeight, null); 
		ImageIcon newIcon = new ImageIcon(rescaled);
		System.out.println(newIcon.getIconHeight());
		return newIcon;
	}
	class PopupListener extends MouseAdapter
	{
		JPopupMenu popup;
		
		PopupListener(JPopupMenu popupMenu)
		{
			popup = popupMenu;
		}
		
		public void mousePressed(MouseEvent e)
		{
			maybeShowPopup(e);
		}
		
		public void mouseReleased(MouseEvent e)
		{
			maybeShowPopup(e);
		}
		
		private void maybeShowPopup(MouseEvent e)
		{
			if(e.isPopupTrigger())
			{
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	/* 
	 * Create and show the GUI.  For thread safety, this 
	 * method should be invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI()
	{
		//create and set up the window
		JFrame frame = new JFrame("PopupMenuDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//create the contentPane
		Popup demo = new Popup();
		frame.setContentPane(demo.createContentPane());
		
		//create and setup the popup menu
		String [] hi = {"Hi there", "What's up"};
		demo.createPopupMenu(hi);
		
		//display the window
		frame.setSize(600, 600);
		frame.setVisible(true);
	}
	
	
	public static void main(String[] args)
	{
		//schedule a job for the event-dispatching thread
		//creating and showing this application's GUI
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndShowGUI();
			}
		});
	}
}
