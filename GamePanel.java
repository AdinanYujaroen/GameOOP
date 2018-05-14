
package MyGame;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;	
	Graphics2D big;
	ArrayList<Sprite> spaces = new ArrayList<Sprite>();
        ArrayList<Bullet> bullets = new ArrayList<Bullet>();
        private SpaceShip v ;
       // Item item = new Item(0,0);
	public GamePanel(SpaceShip v) {
		bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);//8-bit RGBA color 
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.BLACK);
                this.v = v;
	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0,400, 600);//กำหนดการclear ค่า บนกระดาน บน ซ้าย ขวา ล่าง
		
		big.setColor(Color.WHITE);		
		big.drawString(String.format("score:%d", reporter.getScore()), 300, 20);//เขียนคะแนน
                big.drawString(String.format("HP:%d", v.getHp()), 200, 20);//เขียนคะแนน
                big.drawString(String.format("MP:%d", v.getMp()), 150, 20);//เขียนคะแนน
                big.drawString(String.format("Bullet:%d", v.getNumOfBullet()), 80, 20);//เขียนคะแนน
		for(Sprite s : spaces){
			s.draw(big);//วาดยานเราและศัตรู
		}
		for(Bullet b : bullets){
                        b.draw(big);
                }
                //item.draw(big);
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
                g2d.setColor(Color.YELLOW);
                //System.out.println("555");

	}

}
