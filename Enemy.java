/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyGame;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy extends Sprite{
	public static final int Y_TO_FADE = 400;//
	public static final int Y_TO_DIE = 600;//เอาไว้เข็คว่าชนขอบหรือยัง
	
	private int step = 12;
	
	public Enemy(int x, int y) {
		super(x, y, 50, 10,30,0);
		
	}

	@Override
	public void draw(Graphics2D g) {
		if(y < Y_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
                
		
	}

	public void proceed(){
		y += step;//เอาไว้เพิ่มความเร็วของข้าศึก
		if(y > Y_TO_DIE){
			alive = false;
		}
                if(curHp <= 0){
                    alive = false;
                }
	}
	public void setAlive(){
            alive = false;
        }


    @Override
    public void move(int direction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void decreaseHp(int hp) {
       curHp -= hp;
       if(curHp <= 0)
           curHp = 0;
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