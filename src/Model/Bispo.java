/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Graphics2D;

/**
 *
 * @author LSatoshi
 */
public class Bispo  extends Peca{

    public Bispo(Cor cor, int x, int y)  {
        super(cor, x, y);
    }

    @Override
    public void draw(Graphics2D g) {
        int squareWidth = 60;
        //(g.getClip().getBounds().width-120)/ 8;
        int squareHeight = 60;
        //(g.getClip().getBounds().height-120)/ 8;
        
        int x0 = quadrante.x * squareWidth + 60;
        int y0 = quadrante.y * squareHeight + 60;
        int x1 = x0 + squareWidth;
        int y1 = y0 + squareHeight;
        
        if(this.cor == Peca.Cor.PRETO){
            g.drawImage(pecasImg, x0, y0-40, x1, y1, 1616, 59, 1866, 437, null);
        } else {
            g.drawImage(pecasImg, x0, y0-40, x1, y1, 1616, 558, 1866, 957, null);
        }
    }
    
    @Override
    public String toString() {
        if(this.cor == Peca.Cor.PRETO){
            return "Bispo Preto";
        } else {
            return "Bispo Branco";
        }
    }
    
    @Override
    public boolean validMove(int coord_x, int coord_y) {
        int x = (coord_x-60)/60;
        int y = (coord_y-60)/60;
        if(Math.abs(this.getX()-x) == Math.abs(this.getY()-y))return(true);
        return(false);
    }
}
