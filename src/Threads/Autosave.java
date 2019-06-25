/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import Model.ModelTabuleiro;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author satoshi
 */
public class Autosave extends Thread{
    public Save save;


    public Autosave(ModelTabuleiro model, Timer time) {
        this.save.pecasBrancas = model.pecasBrancas;
        this.save.pecasPretas = model.pecasPretas;
        //this.save.time = time.time;
        start();
    }
    
    public Autosave(Save save) {
        this.save = save;
        start();
    }
    
    @Override
    public void run() {
        while(true) {
            this.save();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }      
    }
    
    void save() {
        Path path = Paths.get("chess.sav");
        if(Files.exists(path) == false) {
            File file = new File("chess.sav");
        }
        try {
            FileOutputStream f_out = new FileOutputStream ("chess.sav");
            ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
            obj_out.writeObject (this.save);
        } catch (Exception e) {
            System.out.println (e.toString ());
            System.exit (1);
        }
    }
}
