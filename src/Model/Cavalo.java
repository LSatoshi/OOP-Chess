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
public class Cavalo  extends Peca{

    public Cavalo(Cor cor, int x, int y)  {
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
            g.drawImage(pecasImg, x0-20, y0-30, x1+5, y1, 2049, 89, 2384, 410, null);
        } else {
            g.drawImage(pecasImg, x0-20, y0-30, x1+5, y1, 2049, 597, 2384, 915, null);
        }
    }
    
    @Override
    public String toString() {
        if(this.cor == Peca.Cor.PRETO){
            return "Cavalo Preto";
        } else {
            return "Cavalo Branco";
        }
    }
    
    @Override
    public boolean validMove(int coord_x, int coord_y) {
        int moves[][] = {{-1,-2},{1,-2},{2,-1},{2,1},{1,2},{-1,2},{-2,1},{-2,-1}};
        int x = (coord_x-60)/60;
        int y = (coord_y-60)/60;
        for(int i = 0; i < 8; i++) {
            if(this.getX() + moves[i][0] == x && this.getY() + moves[i][1] == y) return(true);
        }
        return(false);
    }
}
