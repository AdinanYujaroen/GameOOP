/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyGame;

import static MyGame.Bullet.Y_TO_DIE;
import static MyGame.Bullet.Y_TO_FADE;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;


public class Item extends Sprite{

    private final int step = 12;
    private int item;
    public Item(int x, int y) {
        super(x, y, 50,10,0,0);
        generateItem();
    }

    @Override
    public void draw(Graphics2D g) {
        if(y < Y_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}
        
	if(item == 1){//เพิ่มเลือก
            g.setColor(Color.GREEN);
            g.fillRect(x, y, width, height);
        }else if(item ==2){//เพิ่ม MP
            g.setColor(Color.BLUE);
            g.fillRect(x, y, width, height);
        }else if(item == 3){//เพิ่ม stateBullet
            g.setColor(Color.ORANGE);
            g.fillRect(x, y, width, height);
        }else if(item == 4){//เพิ่ม num of bullet
            g.setColor(Color.GRAY);
            g.fillRect(x, y, width, height);
        }else {
            g.setColor(Color.WHITE);
            g.fillRect(x, y, width, height);
        }	
        
                
    }

    @Override
    public void move(int direction) {
        y += (step*direction);
        if(y > Y_TO_DIE){
			alive = false;
		}
       
    }
    public void generateItem(){
        int x = (int)(Math.random()*10);
        if(x >=0 && x<=2)
            item = 1;
        else if(x ==3 || x == 4)
            item = 2;
        else if(x == 5)
            item = 3;
        else if(x == 6 || x == 7)
            item = 4;
        else 
            item = 5;
        
    }
    public int getItem(){
        return item;
    }
    public void setAlive(){
        alive = false;
    }
    @Override
    public void decreaseHp(int hp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void increaseHp(int hp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void decreaseMp(int mp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void increaseMp(int mp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
