/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.ModelTabuleiro;
import Exceptions.*;
import Model.Peca;
import View.Tabuleiro;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author LSatoshi
 */
public class TabuleiroController implements  MouseListener, MouseMotionListener{

  private Tabuleiro view;
  private ModelTabuleiro model;
  private Peca atual = null;
  private int turno = 0;

  
    public void addView (Tabuleiro view){
        this.view = view;
    }
    
    public void addModel (ModelTabuleiro model){
        this.model = model;
    }
    
    /*
      USe este metodo para iniciar o seu VIEW... neste caso, antes de motra-lo
    na tela, o posicionamos no centro dela....
    */
    public void runTabuleiro() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - view.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - view.getHeight()) / 2);
        view.setLocation(x, y);
        view.getPlayerLabel().setText("Turno do jogador Branco");
        
        view.setVisible(true);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();//pega as coordenadas do mouse
        int y = e.getY();
        
        
    }

    @Override
    /*Funcao que controla toda interacao entre usuario e programa
    * para jogabilidade
    */
    public void mousePressed(MouseEvent e) {
        int x = e.getX();//pega as coordenadas do mouse
        int y = e.getY();
        //se nao possuir nenhuma peca selecionada
        if(atual == null) {
            try {
                atual = this.model.findPeca((x-60)/60,(y-60)/60);//seleciona uma peca na posicao do click
                //turno controla a vez do jogador
                //se a peca selecionada for valida e da cor do jogador
                model.verifyPlayerColour(turno, atual);
                view.getMessage().setText("Peca selecionada (" + atual.toString() + ")");
            } catch (PieceNotFound pnf) {//msg de erro caso a casa esteja vazia
                view.getMessage().setText("Nenhuma peca selecionada");    
            } catch (WrongColour ex) {  //msg de erro caso a peça seja da cor errada
                view.getMessage().setText("Peca invalida");
                atual = null;
            }
            view.getClickLabel().setText("Quadrante: ["+(x-60)/60+","+(y-60)/60+"]");
        }
        //se ja selecionou uma peca no primeiro click
        else {
            /*chama as funcoes validPosition, validMove e clearPath 
            *para verificar se a jogada eh valida
            */
            try {
                this.model.validPosition(x,y);
                if(atual.validMove(x,y) == false) throw new InvalidMove();
                this.model.pathClear(atual, x, y);
                this.model.findPeca((x-60)/60,(y-60)/60);//verifica se ha peca
                //se houver uma peca no local, verifica se é aliada ou inimiga
                try {//tenta "comer" a peca inimiga
                    Peca aux = this.model.findPeca((x-60)/60,(y-60)/60);
                    this.model.removePeca(aux, turno);
                    atual.setQuadrante((x-60)/60,(y-60)/60);
                    view.getMessage().setText("Peca movida para : x = "+atual.getX()+" y = "+atual.getY());
                    view.repaint();
                    atual = null;
                    turno++;
                } catch(InvalidMove IM) {//se for uma peca da mesma cor que a atual, da movimento invalida
                    view.getMessage().setText("Movimento invalido");
                }
            } catch(InvalidMove IM) {//se falhou no teste de movimento valido, da erro e atual recebe nulo
                view.getMessage().setText("Movimento invalido");
                atual = null;
            } catch(PieceNotFound pnf) {//se não encontrou uma peca, realiza o movimento normalmente
                atual.setQuadrante((x-60)/60,(y-60)/60);
                view.getMessage().setText("Peca movida para : x = "+atual.getX()+" y = "+atual.getY());
                view.repaint();
                atual = null;
                turno++;
            }
            //se executou um movimento valido, passa de turno
            //utiliza a variavel turno para mostrar ao usuario o turno  do jogador
            if(turno%2 == 0) {
                view.getPlayerLabel().setText("Turno do jogador Branco");
            } else view.getPlayerLabel().setText("Turno do jogador Preto");
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
     }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();//pega as coordenadas do mouse
        int y = e.getY();
        view.getCoordenadaLabel().setText("Quadrante: ["+(x-60)/60+","+(y-60)/60+"]");
        view.getMouseCoord().setLocation(x, y);
        view.repaint();
    }

}
