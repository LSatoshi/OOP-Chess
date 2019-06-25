/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author jbatista
 */
public abstract class Peca implements java.io.Serializable{
    
    /*Illustrated Chess Pieces and Board Pack - free asset
    *credits to : Joszs
    *https://joszs.itch.io/chess-pack
    */
    protected String imgPath = "img/chess-pieces.png";
    protected static BufferedImage pecasImg = null;    
    protected Cor cor;
    protected Point quadrante;
    
    public enum Cor{
        PRETO,
        BRANCO
    }
    
    public Peca(Cor cor, int x, int y)  {
        this.cor = cor;
        this.quadrante = new Point(x,y);
        if(pecasImg == null){
            try {
                pecasImg = ImageIO.read(new File(imgPath));
            } catch (IOException ex) {
                Logger.getLogger(Peca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean inSquare(int x, int y){
        if(x == quadrante.x && y == quadrante.y) return true;
        else return false;
    }
    
    
    public int getX() {
        return(quadrante.x);
    }
    
    public int getY() {
        return(quadrante.y);
    }
    
    public Cor getCor() {
        return(cor);
    } 
    
    public void setQuadrante(int x, int y){
        quadrante.setLocation(x, y);
    }
    
    public abstract void draw(Graphics2D g);
    
    //funcao abstrata validMove para ser sobrescrita
    public abstract boolean validMove(int x, int y);
}
