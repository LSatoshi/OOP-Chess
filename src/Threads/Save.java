/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import Model.ModelTabuleiro;
import Model.Peca;
import java.util.ArrayList;

/**
 *
 * @author satoshi
 */
public class Save implements java.io.Serializable {
    public ArrayList<Peca> pecasPretas;
    public ArrayList<Peca> pecasBrancas;
    public long time;

    public Save(ArrayList<Peca> pecasPretas, ArrayList<Peca> pecasBrancas, long time) {
        this.pecasPretas = pecasPretas;
        this.pecasBrancas = pecasBrancas;
        this.time = time;
    }
    
    public Save(ModelTabuleiro model, Timer time) {
        this.pecasBrancas = model.pecasBrancas;
        this.pecasPretas = model.pecasPretas;
        this.time = time.time;
    }
    
    public void load(ModelTabuleiro model, Timer time) {
        model.pecasBrancas = this.pecasBrancas;
        model.pecasPretas = this.pecasPretas;
        time.time = this.time;
    }
    
}
