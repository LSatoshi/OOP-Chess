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
    ModelTabuleiro model;
    long time;

    public Autosave(ModelTabuleiro model, long time) {
        this.model = model;
        this.time = time;
        start();
    }
    
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
            obj_out.writeObject (this.model);
            obj_out.writeObject (this.time);
        } catch (Exception e) {
            System.out.println (e.toString ());
            System.exit (1);
        }
    }
}
