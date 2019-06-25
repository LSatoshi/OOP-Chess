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
public class Rei  extends Peca{

    public Rei(Cor cor, int x, int y)  {
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
            g.drawImage(pecasImg, x0-5, y0-70, x1+5, y1, 108, 6, 365, 473, null);
        } else {
            g.drawImage(pecasImg, x0-5, y0-70, x1+5, y1, 108, 507, 365, 974, null);
        }
    }
    
    @Override
    public String toString() {
        if(this.cor == Peca.Cor.PRETO){
            return "Rei Preto";
        } else {
            return "Rei Branco";
        }
    }
    
    @Override
    public boolean validMove(int coord_x, int coord_y) {
        int x = (coord_x-60)/60;
        int y = (coord_y-60)/60;
        if(Math.abs(this.getX()-x) <= 1 && Math.abs(this.getY()-y) <= 1)return(true);
        return(false);
    }
}
