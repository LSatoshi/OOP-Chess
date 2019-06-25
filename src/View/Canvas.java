/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author jbatista
 */
public class Canvas extends JPanel{
    
    private ArrayList<Observer> observers;
    protected String boardPath = "img/stable.png";
    protected static BufferedImage boardImg = null; 
    

    public Canvas() {
        super();
        observers = new ArrayList<Observer>();
        if(boardImg == null){
            try {
                boardImg = ImageIO.read(new File(boardPath));
            }  catch (IOException ex) {
       
            }
        }
    }
    
    public void registerObserver(Observer ob){
        this.observers.add(ob);
    }
    
    private void desenhaTabuleiro(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    // 64 é o numedo de quadrantes de um tabuleiro de xadrez
        for (int i = 0; i < 8; i++) {
          for (int j = 0; j < 8; j++) {        
            if ((j+i) % 2 == 0) g2d.setColor(Color.BLACK);
            else g2d.setColor(Color.WHITE);
            g2d.fillRect(j * 60, i*60, 60, 60);
          }
        }
    }
    
    private void drawBoard(Graphics2D g){
        Color cor1 = new Color(243, 235, 215);
        Color cor2 = new Color(163, 119, 84);
        g.setBackground(cor1);
        g.setColor(cor2);
                 
        float maxWidth=this.getWidth()-120;
        float maxHeight=this.getHeight()-120;
        //System.out.println(maxWidth + "  " + maxHeight);
        float boardSize = (maxWidth < maxHeight) ? maxWidth : maxHeight;
        g.drawImage(boardImg, 0, 0, 600, 600, 0, 0, 680, 680, null);
        int spotSize = Math.round(boardSize/8.0f);
        for(int i = 0; i<8; ++i){
            for(int j = 0; j<8; ++j){
                //varia a cor do quadrante
                if(g.getColor() == cor1) g.setColor(cor2);
                else g.setColor(cor1);

                //Desenha o tabuleiro
                g.fillRect(i*spotSize+60,j*spotSize+60,spotSize, spotSize);
            }

            if(g.getColor() == cor1) g.setColor(cor2);
            else g.setColor(cor1);
        }
    }
    
    
    
    @Override //sobrescrita do metodo paintComponent da classe JPanel
    protected void paintComponent(Graphics g) {
        //System.out.println("repintando");
        super.paintComponent(g);
        //desenhaTabuleiro(g);
        Graphics2D g2 = (Graphics2D)g;
        this.drawBoard(g2);
        // Depois de desenhar o tabuleiro, desenha o resto.
        // temos 2 observers registrados: o Tabuleiro 
        for(Observer ob : observers){
            ob.update(null, g2);
        }
    }
    
}
