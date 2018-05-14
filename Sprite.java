
package MyGame;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public abstract class Sprite {
	int x;
	int y;
	int width;
	int height;
        int maxHp;
        int maxMp;
        int curHp;
        int curMp;
        boolean alive;
	
	public Sprite(int x, int y, int width, int height,int maxHp,int maxMp) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
                this.maxHp = maxHp;
                this.maxMp = maxMp;
                this.curHp = maxHp;
                this.curMp = 0;
                this.alive = true;
                
                
	}

	abstract public void draw(Graphics2D g);//import Graphics2D
	
	public Double getRectangle() {
		return new Rectangle2D.Double(x, y, width, height);//วาด 4 เหลี่ยม
	}
        
        abstract public void move(int direction);
        abstract public void decreaseHp(int hp);
        abstract public void increaseHp(int hp);
        abstract public void decreaseMp(int mp);
        abstract public void increaseMp(int mp);
        public boolean isAlive (){
            return alive;
        }
        public int getHp(){
            return curHp;
        }
        public int getMp(){
            return curMp;
        }
        public int getX(){
            return x;
        }
        public int getY(){
            return y;
        }
}
