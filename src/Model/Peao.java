/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Graphics2D;

/**
 *
 * @author jbatista
 */
public class Peao  extends Peca{

    public Peao(Cor cor, int x, int y)  {
        super(cor, x, y);
    }

    @Override
    public void draw(Graphics2D g) {
        int squareWidth = (g.getClip().getBounds().width-120)/ 8;
        int squareHeight = (g.getClip().getBounds().height-120)/ 8;
        
        int x0 = quadrante.x * squareWidth + 65;
        int y0 = quadrante.y * squareHeight + 60;
        int x1 = x0 + squareWidth;
        int y1 = y0 + squareHeight;
        
        if(this.cor == Peca.Cor.PRETO){
            g.drawImage(pecasImg, x0, y0-20, x1, y1, 2642, 105, 2870, 413, null);//320, 20, 360, 60//393
        } else {
            g.drawImage(pecasImg, x0, y0-20, x1, y1, 2642, 602, 2870, 910, null);//320, 72, 360, 112//890
        }
    }
    
    @Override
    public String toString() {
        if(this.cor == Peca.Cor.PRETO){
            return "Peao Preto";
        } else {
            return "Peao Branco";
        }
    }
    
    @Override
    public boolean validMove(int coord_x, int coord_y) {
        int x = (coord_x-60)/60;
        int y = (coord_y-60)/60;
        if(this.cor == Peca.Cor.PRETO){
            if(this.getY() == 1) {
               if(this.getX() == x && y == 3) return(true);
            }
            if(this.getY() == y-1) {
                if(this.getX() == x) return(true);
                else if(this.getX() == x-1 || this.getX() == x+1) {
                    /*if(model.findPeca(x,y) != null)*/ return(true);
                }
            }
        } else {
            if(this.getY() == 6) {
               if(this.getX() == x && y == 4) return(true);
            }
            if(this.getY() == y+1) {
                if(this.getX() == x) return(true);
                else if(this.getX() == x-1 || this.getX() == x+1) {
                    /*if(model.findPeca(x,y) != null)*/ return(true);
                }
            }
        }
        return(false);
    }
}
