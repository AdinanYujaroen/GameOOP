
package MyGame;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
        private Thread t = new Thread();
	public static void main(String[] args){
                
                
                Wellcome w = new Wellcome();
                    
                if(w.getStatePlayer() == 1){
                    //GridLayout g = new GridLayout(0,2);
                    //g.setHgap(5);
                 JFrame frame = new JFrame("Space War");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(410, 650);
                frame.getContentPane().setLayout(new BorderLayout());
                //JPanel jp = new JPanel();
                //jp.setLayout(g);
                
                SpaceShip v1 = new SpaceShip(150, 550, 50, 20,100,200);//ตัวกำหนดขนาดของยานเรา
                GamePanel gp1 = new GamePanel(v1);//เอาไว้วาดข้อมูลลงหน้าต่าง
                GameEngine engine1 = new GameEngine(gp1, v1,1);
                
                //jp.add(gp1);
                 MyThread t1 = new MyThread(v1,gp1,engine1);
                 
                 frame.addKeyListener(engine1);
                 t1.start();
                 frame.getContentPane().add(gp1, BorderLayout.CENTER);
                
		frame.setVisible(true);
                }else {
                    GridLayout g = new GridLayout(0,2);
                    g.setHgap(5);
                    JFrame frame = new JFrame("Space War");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(805, 650);
                frame.getContentPane().setLayout(new BorderLayout());
                JPanel jp = new JPanel();
                jp.setLayout(g);
                
                SpaceShip v1 = new SpaceShip(150, 550, 50, 20,100,200);//ตัวกำหนดขนาดของยานเรา
                GamePanel gp1 = new GamePanel(v1);//เอาไว้วาดข้อมูลลงหน้าต่าง
                GameEngine engine1 = new GameEngine(gp1, v1,1);
                
                jp.add(gp1);
                 MyThread t1 = new MyThread(v1,gp1,engine1);
                
                SpaceShip v2 = new SpaceShip(150, 550, 50, 20,100,200);//ตัวกำหนดขนาดของยานเรา
		GamePanel gp2 = new GamePanel(v2);//เอาไว้วาดข้อมูลลงหน้าต่าง
		GameEngine engine2 = new GameEngine(gp2, v2,2);
                MyThread t2 = new MyThread(v2,gp2,engine2);
                
                jp.add(gp2);
		frame.addKeyListener(engine1);
                frame.addKeyListener(engine2);
                
		t1.start();
                t2.start();
                
                frame.getContentPane().add(jp, BorderLayout.CENTER);
                
		frame.setVisible(true);
                }
            //Wellcome w2 = new Wellcome();
		/*engine1.start();
                engine2.start();*/
	}
     
}
