package maze;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MazeUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private Cell[][] cellList;
	
	public MazeUI(Cell[][] cellList) {
		this.cellList = cellList;
		this.setBackground(Color.white);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(40 + 50 * this.cellList[0].length, 40 + 50 * this.cellList.length);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int height = this.cellList.length * 50;
		int width = this.cellList[0].length * 50;
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2f));	
		Font ubuntuFont = new Font("Ubuntu", Font.BOLD, 12);
		g2.setFont(ubuntuFont);
		
		g2.drawString("Start", 15, 15);
		g2.drawString("Goal", width, 33 + height);
		
		// Draw outline
		g2.draw(new Line2D.Double(20, 20, 20 + width, 20));
		g2.draw(new Line2D.Double(20, 20 + height, 20 + width, 20 + height));
		g2.draw(new Line2D.Double(20, 20, 20, 20 + height));
		g2.draw(new Line2D.Double(20 + width, 20, 20 + width, 20 + height));
		
		for(int i = 0; i < this.cellList.length; i++) {
			for(int j = 0; j < this.cellList[0].length; j++) {		 
				 for(Wall w : cellList[i][j].walls) {
					 if(!w.open) {
						 int northSouth = w.parent_one.location.first - w.parent_two.location.first;
						 int eastWest = w.parent_one.location.second - w.parent_two.location.second;
						 // Check if north, south, east, west
						 if(northSouth == 1) {
							 g2.draw(new Line2D.Double(20 + j * 50, 20 + i * 50, 20 + (j + 1) * 50, 20 + i * 50));
						 } else if(northSouth == -1) {
							 g2.draw(new Line2D.Double(20 + j * 50, 20 + (i + 1) * 50, 20 + (j + 1) * 50, 20 + (i + 1) * 50));
						 } else if(eastWest == -1) {
							 g2.draw(new Line2D.Double(20 + (j + 1) * 50, 20 + i * 50, 20 + (j + 1) * 50, 20 + (i + 1) * 50));
						 } else if(eastWest == 1) {
							 g2.draw(new Line2D.Double(20 + j * 50, 20 + i * 50, 20 + j * 50, 20 + (i + 1) * 50));
						 } else {
							 System.out.println("MazeUI error: Wall is invalid.");
							 System.exit(ERROR);
						 }
					 }
				 }
			}
		}
	}
	
	public void run() {
		JFrame frame = new JFrame("Maze");	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		frame.setLayout(new BorderLayout());
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	
}
