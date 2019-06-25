/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import View.Tabuleiro;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author satoshi
 */
public class Timer extends Thread{
    public long time = 0;
    public Tabuleiro view;

    public Timer(Tabuleiro view) {
        this.view = view;
        this.time = 0;
        start();
    }
    
    
    public void run() {
        SimpleDateFormat ft = new SimpleDateFormat ("mm:ss");
        while(true) {
            try {
                //System.out.println(ft.format(time));
                view.getTimerLabel().setText(ft.format(time));
                time += 1000;
                this.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
