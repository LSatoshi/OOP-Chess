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
public class Rainha  extends Peca{

    public Rainha(Cor cor, int x, int y)  {
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
            g.drawImage(pecasImg, x0, y0-40, x1, y1, 658, 72, 860, 445, null);
        } else {
            g.drawImage(pecasImg, x0, y0-40, x1, y1, 658, 570, 860, 935, null);
        }
    }
    
    @Override
    public String toString() {
        if(this.cor == Peca.Cor.PRETO){
            return "Rainha Preta";
        } else {
            return "Rainha Branca";
        }
    }
    
    @Override
    public boolean validMove(int coord_x, int coord_y) {
        int x = (coord_x-60)/60;
        int y = (coord_y-60)/60;
        if(this.getX() == x ^ this.getY() == y)return(true);
        if(Math.abs(this.getX()-x) == Math.abs(this.getY()-y))return(true);
        return(false);
    }
}
