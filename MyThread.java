
package MyGame;


public class MyThread extends Thread{
    private SpaceShip s;
    private GamePanel gp;
    private GameEngine ge;
    public MyThread(SpaceShip s,GamePanel gp,GameEngine ge){
        this.s = s;
        this.gp = gp;
        this.ge = ge;
        
    }
    
    @Override
    public void run(){
        ge.start();
        
    }
}
