/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyGame;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level; 
import java.util.logging.Logger;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();//แค่เอาไว้เช็คการชน	
        private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
        private ArrayList<Item> items = new ArrayList<Item>();//สร้างไว้เช็คเวลาพ้นจอ ให้มันลบ
	private SpaceShip v;	
	private Timer timer;
	private int x;
        private int count=0;
	private long score = 0;
	private double difficulty = 0.1;
	private int player;
	public GameEngine(GamePanel gp, SpaceShip v,int player) {
		this.gp = gp;
		this.v = v;		
		this.player = player;
		gp.spaces.add(v);
		
		timer = new Timer(50, new ActionListener() {//หน่วงเวลาการเขียนข้อมูลใหม่
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
                                count++;
			}
		});
		timer.setRepeats(true);//มาครั้งเดียว
		
	}
	//มีความเชื่อมโยงกับ object ของ enemyด้วยการ ส่งค่าไปเมื่อสร้าง ศัตรูใหม่เพิ่ม
	public void start(){
		timer.start();
	}
	
	private void generateEnemy(){
                x=(int)(Math.random()*8)*50;
                //System.out.println(x);
               if(count >= 400 ){
                   Item i1 = new Item(this.x,30);
                    gp.spaces.add(i1);
                    items.add(i1);
                    count =1;
                    this.difficulty +=0.1;
                }else{
                    Enemy e = new Enemy(this.x, 30);
                    gp.spaces.add(e);
                    enemies.add(e);//เพิ่มเพื่อเอาไว้เช็คการชน โดยจะใส่ทุกๆข้าศึกลงไป 
                }
                
		
	}
	private void generateBullet(){
            if(v.getNumOfBullet() > 0){//มีการจำกัดจำนวนกระสุน ไว้ที่ 
                Bullet b1 = new Bullet(this.v.getX(),this.v.getY(),v.getStateBullet());//ส่งค่า สถานะของกระสุนไปด้วย 
                bullets.add(b1);
                gp.bullets.add(b1);
                v.decreaseNumOfBullet();
            }
            
        }
	private void process(){
              
		if(Math.random() < difficulty){//กำหนดจำนวน ศัตรู
			generateEnemy();//ยิ่งค่า difficulty มากขึ้น โอกาสที่จะสร้างศัตรูก้เพิ่มมากขึ้น 
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();//ใช้ interator เพื่อจะสามารถลบตัวที่กำลงดูยุได้
		while(e_iter.hasNext()){//คือเพื่อเช็คค่าทุกค่าที่อยู๋ใน enemies//เช็คว่ามีตัวถัดไปไหม
			Enemy e = e_iter.next();//โดยจะนำค่าตำแหน่งที่กำลังประมวลผลเกบไว้ที่ e_iter
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.spaces.remove(e);
				score += 10;
			}
		}
                Iterator<Bullet> b_iter = bullets.iterator();
		while(b_iter.hasNext()){//คือเพื่อเช็คค่าทุกค่าที่อยู๋ใน enemies//เช็คว่ามีตัวถัดไปไหม
			Bullet b = b_iter.next();//โดยจะนำค่าตำแหน่งที่กำลังประมวลผลเกบไว้ที่ e_iter
			b.proceed(0);
			
			if(!b.isAlive()){
				b_iter.remove();
                                gp.bullets.remove(b);
                                
			}
		}
                Iterator<Item> I_iter = items.iterator();//ใช้ interator เพื่อจะสามารถลบตัวที่กำลงดูยุได้
		while(I_iter.hasNext()){//คือเพื่อเช็คค่าทุกค่าที่อยู๋ใน enemies//เช็คว่ามีตัวถัดไปไหม
			Item i = I_iter.next();//โดยจะนำค่าตำแหน่งที่กำลังประมวลผลเกบไว้ที่ e_iter
			i.move(1);
			
			if(!i.isAlive()){
				I_iter.remove();
				gp.spaces.remove(i);
				
			}
		}
		
		gp.updateGameUI(this);//ส่งไป gamePanel เพื่ออัพเดตค่า 
		
		Rectangle2D.Double vr = v.getRectangle();//เอาค่าของยานเราออกมา
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			if(v.getHp() <= 0)
                            die();
                        er = e.getRectangle();//รับค่าศัตรู
			if(er.intersects(vr)){//เช็คว่าค่ามัน ทับกกันไหม 
                            e.setAlive();//เมื่อชนแล้วก้ให้มันตายๆไปสะจะได้ไม่เช็คซ้ำสองรอบ 
                                if(v.getStateBullet() != 0)
                                    v.decreaseStateBullet();
                                else{
                                    if(v.getHp() <= 0)
                                        die();
                                    else{
                                        v.decreaseHp(e.getHp());
                                        v.increaseMp(e.getHp());
                                    }
                                }
				return;
			}
                        for(Bullet b:gp.bullets){
                            if(er.intersects(b.getRectangle())){
                                e.decreaseHp(b.getPower());
                                if(e.getHp() <= 0)
                                    e.setAlive();//เมื่อชนแล้วก้ให้มันตายๆไปสะจะได้ไม่เช็คซ้ำสองรอบ
                                b.setAlive(); 
                                //return;
                            }
                        }
		}
                for(Item i: items){//เอาไว้เช็คการชนกันของ ยานเรากับไอเทม
                    er = i.getRectangle();
                    if(er.intersects(vr)){
                        i.setAlive();//เมื่อชนแล้วก้ให้มันตายๆไปสะจะได้ไม่เช็คซ้ำสองรอบ
                        if(i.getItem() == 1){
                            v.increaseHp(50);//เพิ่ม 50
  
                        }else if(i.getItem() == 2){
                            v.increaseMp(50);//เพิ่ม 50

                        }else if(i.getItem() == 3){
                            v.increaseStateBullet();

                        }else if(i.getItem() == 4){
                            v.increaseNumOfBullet(50);//เพิ่ม 50

                        }
                    }
                    
                }
                
	}
	
	public void die(){
		timer.stop();
	}
	
	void controlVehicle(KeyEvent e) {//ตัวรับคีย์ที่กด 
            
            if(player == 1){
                switch (e.getKeyCode()) {
                case KeyEvent.VK_A:
			v.move(-1);
			break;
		case KeyEvent.VK_D:
			v.move(1);
			break;
                case KeyEvent.VK_W:
                        generateBullet();
			break;
                case KeyEvent.VK_S:
                        if(v.getStateSkill()){
                            clearAll();
                            v.clearMp();
                        }
			break;
                case KeyEvent.VK_R://เอาไว้เพิ่มข้าศึก
			difficulty += 0.1;
			break;
		}
            }
            if(player == 2){
                switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move(-1);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(1);
			break;
		 case KeyEvent.VK_ALT://เอาไว้เพิ่มข้าศึก
			difficulty += 0.1;
			break;
                case KeyEvent.VK_UP:
                        generateBullet();
			break;
                case KeyEvent.VK_DOWN:
                        if(v.getStateSkill()){
                            clearAll();
                            v.clearMp();
                        }
			break;
                }
            }
		
	}
        public void clearAll(){
            Iterator<Enemy> e_iter = enemies.iterator();//ใช้ interator เพื่อจะสามารถลบตัวที่กำลงดูยุได้
		while(e_iter.hasNext()){//คือเพื่อเช็คค่าทุกค่าที่อยู๋ใน enemies//เช็คว่ามีตัวถัดไปไหม
			Enemy e = e_iter.next();//โดยจะนำค่าตำแหน่งที่กำลังประมวลผลเกบไว้ที่ e_iter
				e_iter.remove();
				gp.spaces.remove(e);
				score += 10;
			
		}
        }
	public long getScore(){
		return score;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {//ทำงานเมื่อกด คีย์บอร์ด
		controlVehicle(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {//ทำเมื่อปล่อย
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {//
		//do nothing		
	}

    
}
