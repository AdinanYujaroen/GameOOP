/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author MSI
 */
public class GameOver extends JFrame{
    private int statePlayer = 0;
    public GameOver(){
        while(statePlayer == 0){
            setSize(400,650);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            getContentPane().setLayout(new BorderLayout());
        
            GridLayout g = new GridLayout(0,2);
            g.setHgap(20);
         
            JPanel jp = new JPanel();
            jp.setLayout(g);
         
            JButton player1 = new JButton("Start 1 Player ");
            player1.addActionListener(new ActionListener(){
            
                public void actionPerformed(ActionEvent ae1) {
                    statePlayer =1;
                    System.out.println("OUT OUT OUT");
                }
            });
            JButton player2 = new JButton("Start 2 Player ");
            player2.addActionListener(new ActionListener(){
            
                public void actionPerformed(ActionEvent ae2) {
                    statePlayer = 2;
                    System.out.println("IN IN IN");
                }
            });
            
            jp.add(player1);
            jp.add(player2);
            JLabel jb1 = new JLabel("Wellcome to เกมตะลุยอวกาศ");
            jb1.setSize(20,100);
            getContentPane().add(jb1, BorderLayout.PAGE_START);
          
             getContentPane().add(jp, BorderLayout.PAGE_END);
                
             setVisible(true);
        }
        
    }
}
