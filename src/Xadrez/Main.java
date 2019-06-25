/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Xadrez;

import Controler.TabuleiroController;
import Model.ModelTabuleiro;
import Threads.Autosave;
import Threads.Timer;
import View.Tabuleiro;
import java.io.*;

/**
 *
 * @author LSatoshi
 */
public class Main {

  /**
   * @param args the command line arguments
   */
public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
        /*Object obj = new Object ();    
        try {
            FileInputStream f_in = new FileInputStream ("chess.sav");
            ObjectInputStream obj_in = new ObjectInputStream (f_in);
            obj = obj_in.readObject ();
            //ModelTabuleiro model = (ModelTabuleiro) obj;
        }
        catch (Exception e) {
            System.out.println (e.toString ());
            System.exit (1);
        }*/
        //ModelTabuleiro model = (ModelTabuleiro) obj;
        ModelTabuleiro model = new ModelTabuleiro();  
        // cria o View (Janela do Tabuleiro)
        Tabuleiro viewTabuleiro = new Tabuleiro(model);  
        // Cria o Controller do Tabuleiro.... todos eventos tratados aqui..
        TabuleiroController tabuleiroController = new TabuleiroController(); 
        // define o tratamento de eventos dos atributos do view para o controller
        viewTabuleiro.addController(tabuleiroController);
        // associa o view ao seu repectivo controller
        tabuleiroController.addView(viewTabuleiro);
        tabuleiroController.addModel(model);
        Timer timer = new Timer(viewTabuleiro);
        Autosave save = new Autosave(model, 0);
        // finalmente, executa o view.....
        tabuleiroController.runTabuleiro();
      }
    });
  }
}
