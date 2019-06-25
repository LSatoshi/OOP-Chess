/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Exceptions.*;
import View.Tabuleiro;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author jbatista
 * ESTA CLASSE contem o MODELO DE DADOS da sua aplicação !!!!!
 * Coloque nela APENAS os dados: acesso a um banco de dados, queries de SQL, suas pecas de xadrez, 
 * etc..
 */
public class ModelTabuleiro implements Observer, java.io.Serializable{

    private final ArrayList<Peca> pecasPretas;
    private final ArrayList<Peca> pecasBrancas;

    public ModelTabuleiro()  {
        this.pecasPretas = new ArrayList<Peca>();
        this.pecasBrancas  = new ArrayList<Peca>();
        
        init();
    }

    public ModelTabuleiro(ModelTabuleiro m) {
        this.pecasPretas = m.pecasPretas;
        this.pecasBrancas = m.pecasBrancas;
        
        init();
    }
    
    
    
    private void init() {
        
        //inicializa time branco
        for(int i = 0; i < 8; i++) {//inicializa peoes brancos
            pecasBrancas.add(new Peao(Peca.Cor.BRANCO,i,6));
        }
        pecasBrancas.add(new Torre(Peca.Cor.BRANCO,0,7));
        pecasBrancas.add(new Torre(Peca.Cor.BRANCO,7,7));
        pecasBrancas.add(new Cavalo(Peca.Cor.BRANCO,1,7));
        pecasBrancas.add(new Cavalo(Peca.Cor.BRANCO,6,7));
        pecasBrancas.add(new Bispo(Peca.Cor.BRANCO,2,7));
        pecasBrancas.add(new Bispo(Peca.Cor.BRANCO,5,7));
        pecasBrancas.add(new Rainha(Peca.Cor.BRANCO,3,7));
        pecasBrancas.add(new Rei(Peca.Cor.BRANCO,4,7));

        //inicializa time preto
        pecasPretas.add(new Torre(Peca.Cor.PRETO,0,0));
        pecasPretas.add(new Torre(Peca.Cor.PRETO,7,0));
        pecasPretas.add(new Cavalo(Peca.Cor.PRETO,1,0));
        pecasPretas.add(new Cavalo(Peca.Cor.PRETO,6,0));
        pecasPretas.add(new Bispo(Peca.Cor.PRETO,2,0));
        pecasPretas.add(new Bispo(Peca.Cor.PRETO,5,0));
        pecasPretas.add(new Rainha(Peca.Cor.PRETO,3,0));
        pecasPretas.add(new Rei(Peca.Cor.PRETO,4,0));
        for(int i = 0; i < 8; i++) {
            pecasPretas.add(new Peao(Peca.Cor.PRETO,i,1));
        }
        

        
    }
    
    public Peca findPeca(int x, int y) throws PieceNotFound{
        Peca peca = null;
        
        //desenha pecas Brancas
        for(Peca p : pecasBrancas){
            if(p.inSquare(x,y)){
                return p;
            }
        }
        
        //desenha pecas pretas
        for(Peca p : pecasPretas){
            if(p.inSquare(x,y)){
                return p;
            }
        }
        
        throw new PieceNotFound(peca);
    }
    
    Peca FindPeca(int x, int y){
        Peca peca = null;
        
        //desenha pecas Brancas
        for(Peca p : pecasBrancas){
            if(p.inSquare(x,y)){
                return p;
            }
        }
        
        //desenha pecas pretas
        for(Peca p : pecasPretas){
            if(p.inSquare(x,y)){
                return p;
            }
        }
        
        return peca;
    }

    public void verifyPlayerColour(int turno, Peca p) throws WrongColour{
        if((turno%2 != 0 && p.getCor() == Peca.Cor.BRANCO) || (turno%2 == 0 && p.getCor() == Peca.Cor.PRETO)) {
            throw new WrongColour();
        }
        return;
    }
    
    /*Funcao que remove uma peca da lista de pecas*/
    public void removePeca(Peca rem, int turno) throws InvalidMove{
        if(turno%2 != 0) {
            for(Peca p : pecasBrancas){
                if(p == rem){
                    pecasBrancas.remove(rem);
                    return;
                }
            }
        }
        else {
            for(Peca p : pecasPretas){
                if(p == rem){
                    pecasPretas.remove(rem);
                    return;
                }
            }
        }
        throw new InvalidMove();
    }
    
    public void draw(Graphics2D g){
        //desenha pecas Brancas
        Peca frente;
        for(Peca p : pecasBrancas){
            frente = FindPeca(p.quadrante.x,p.quadrante.y + 1); //marcador da peca da frente
            p.draw(g);
            while(frente != null){//por as pecas terem um efeito 3d, repinta as pecas da frente
                frente.draw(g);
                frente = FindPeca(frente.quadrante.x,frente.quadrante.y + 1);
            }
        }
        
        //desenha pecas pretas
        for(Peca p : pecasPretas){
            frente = FindPeca(p.quadrante.x,p.quadrante.y + 1);
            p.draw(g);
            while(frente != null){
                frente.draw(g);
                frente = FindPeca(frente.quadrante.x,frente.quadrante.y + 1);
            }
        }
    }
    
    
    @Override
    public void update(Observable o, Object arg) {
        draw((Graphics2D) arg);
    }
    
    /*funcao que verifica se uma posicao eh valida dentro do tabuleiro*/
    public boolean validPosition(int coord_x, int coord_y) throws InvalidMove{
        int x = (coord_x-60)/60;
        int y = (coord_y-60)/60;
        if(coord_x <= 540 && coord_x >= 60) {
            if(coord_y <= 540 && coord_y >= 60)return(true);
        }
        throw new InvalidMove();
    }
    
    /*funcao que verifica se o caminho entre a posicao inicial e final da peca esta livre*/
    public boolean pathClear(Peca p, int coord_x, int coord_y) throws InvalidMove{
        //se for um cavalo retorna true
        if(p.getClass().getName().equals("Model.Cavalo")) return(true);
        int x = (coord_x-60)/60;
        int y = (coord_y-60)/60;
        //caso contrario, verifica os movimentos possiveis
        //no caso, movimentos em diagonal ou em cruz
        if(x != p.getX() && y != p.getY()) {//verifica as diagonais
            if(x > p.getX()){
                if(y > p.getY()){
                    for(int i = 1; x-i > p.getX(); i++) {//direita baixo
                        if(FindPeca(p.getX()+i,p.getY()+i) != null) throw new InvalidMove();
                    }
                }
                if(y < p.getY()){
                    for(int i = 1; x-i > p.getX(); i++) {//direita cima
                        if(FindPeca(p.getX()+i,p.getY()-i) != null) throw new InvalidMove();
                    }
                }              
            }
            if(x < p.getX()){
                if(y > p.getY()){
                    for(int i = 1; x+i < p.getX(); i++) {//esquerda baixo
                        if(FindPeca(p.getX()-i,p.getY()+i) != null) throw new InvalidMove();
                    }
                }
                if(y < p.getY()){
                    for(int i = 1; x+i < p.getX(); i++) {//esquerda cima
                        if(FindPeca(p.getX()-i,p.getY()-i) != null) throw new InvalidMove();
                    }
                }              
            }
        }
        else if (x != p.getX() ^ y != p.getY()) {//verifica em cruz
            if(x > p.getX()){
                for(int i = 1; x-i > p.getX(); i++) {//baixo
                        if(FindPeca(p.getX()+i,p.getY()) != null) throw new InvalidMove();
                }
            }
            if(x < p.getX()){
                for(int i = 1; x+i < p.getX(); i++) {//cima
                        if(FindPeca(p.getX()-i,p.getY()) != null) throw new InvalidMove();
                }
            }
            if(y > p.getY()){
                for(int i = 1; y-i > p.getY(); i++) {//direita
                        if(FindPeca(p.getX(),p.getY()+i) != null) throw new InvalidMove();
                }
            }
            if(y < p.getY()){
                for(int i = 1; y+i < p.getY(); i++) {//direita
                        if(FindPeca(p.getX(),p.getY()-i) != null) throw new InvalidMove();
                }
            }
        }
        return(true);
    }


   
    
}
