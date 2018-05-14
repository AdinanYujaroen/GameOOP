
package MyGame;


import java.awt.Color;
import java.awt.Graphics2D;

public class SpaceShip extends Sprite{

	int step = 50;
	private int stateBullet = 0;
        private boolean stateSkill = false;
        private int numOfBullet = 100;
        
	public SpaceShip(int x, int y, int width, int height,int maxHp,int maxMp) {
		super(x, y, width, height,maxHp,maxMp);
                
		
	}

	@Override
	public void draw(Graphics2D g) {//วาดยานเรา สีเขียว ตำแหน่งx y ขนาด w*d
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}

	public void move(int direction){//เอาไว้เปลี่ยนตำแหน่งแกน x 
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;
	}

    @Override
    public void decreaseHp(int hp) {
        curHp -= hp;//เพราะมันเข้าเช็คเร็วไปทำให้มาเข้า if intersect 2 ครั้ง
        if(curHp <= 0){
            this.curHp = 0;
            //System.out.println("in decreaseHp < = 0");
        }
           
    }

    @Override
    public void increaseHp(int hp) {
       curHp += hp;
       if(curHp >= this.maxHp){
           this.curHp = this.maxHp;
          // System.out.println("in decreaseHp > max");
       }
           
    }

    @Override
    public void decreaseMp(int mp) {
        curMp -= mp;
        if(curMp <= 0)
           curMp = 0;
           
    }

    @Override
    public void increaseMp(int mp) {
       this.curMp += mp;
       if(this.curMp >= this.maxMp){
            this.curMp = this.maxMp;
            //System.out.println("in increaseMp > max----------------------"+curMp+maxMp+maxHp);
            this.stateSkill = true;
        }
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    public void increaseMaxHp(int hp){
        maxHp += hp;
    }

    public boolean getStateSkill(){
        return this.stateSkill;
    }
    public void clearMp(){
        curMp = 0;
        stateSkill = false;
    }

    public void decreaseNumOfBullet(){
        this.numOfBullet--;
    }
    public int getNumOfBullet(){
        return numOfBullet;
    }
    public void increaseNumOfBullet(int num){
        this.numOfBullet += num;
    }
    public void decreaseStateBullet(){
        this.stateBullet--;
        if(this.stateBullet < 0 )
            this.stateBullet = 0;
    }
    public void increaseStateBullet(){
        this.stateBullet ++;
        if(this.stateBullet >= 2)
            this.stateBullet = 2;
        
    }
    public int getStateBullet(){
        return this.stateBullet;
    }
}
