/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyGame;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import static javax.swing.Spring.height;
import static javax.swing.Spring.width;

/**
 *
 * @author MSI
 */
public class Bullet {
    public static final int Y_TO_FADE = 400;//
    public static final int Y_TO_DIE = 600;//เอาไว้เข็คว่าชนขอบหรือยัง
    private int x;
    private int y;
    public static final int width = 5;
    public static final int height = 10;
    private boolean alive;
    private int stateBullet;
    private int power ;

    public Bullet(int x,int y,int stateBullet){
        this.x = x;
        this.y = y;
        this.stateBullet = stateBullet;
        alive = true;
    }
    
    public void draw(Graphics2D g) {
        proceed(-10);
        if(y < Y_TO_FADE){
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            
        }else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
                        
		}
        if(this.stateBullet == 0){
            g.setColor(Color.GREEN);
            g.fillRect(x+23, y, width, height);
            this.power = 10;
        }else if(this.stateBullet == 1){
            g.setColor(Color.GREEN);
            g.fillRect(x, y, width, height);
            g.setColor(Color.GREEN);
            g.fillRect(x+45, y, width, height);
            this.power =20;
        }else if(this.stateBullet == 2){
            g.setColor(Color.GREEN);
            g.fillRect(x+23, y, width, height);
            g.setColor(Color.GREEN);
            g.fillRect(x, y, width, height);
            g.setColor(Color.GREEN);
            g.fillRect(x+45, y, width, height);
            this.power = 30;
        }
		
    }
    public void proceed(int step){
		y += step;//เอาไว้เพิ่มความเร็วของข้าศึก
		if(y > Y_TO_DIE || y < 0){
			alive = false;
		}
                
	}
    public void setAlive(){
        alive = false;
    }
    public boolean isAlive(){
        return alive;
    }
    public Rectangle2D.Double getRectangle() {
		return new Rectangle2D.Double(x, y, width, height);//วาด 4 เหลี่ยม
	}
        
    public int getPower(){
        return power;
    }
}
