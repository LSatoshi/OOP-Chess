/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Xadrez;

import Controler.TabuleiroController;
import Model.ModelTabuleiro;
import Threads.*;
import View.Tabuleiro;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        @Override
        public void run() {
        Object obj = new Object ();
        Path path = Paths.get("chess.sav");
        Save sav = null;
        if(Files.exists(path) == true) {
            try {   
                FileInputStream f_in = new FileInputStream ("chess.sav");
                ObjectInputStream obj_in = new ObjectInputStream (f_in);
                obj = obj_in.readObject ();
                sav = (Save) obj;
            }
            catch (Exception e) {
                System.out.println (e.toString ());
            }
        }
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
        if(sav != null) {
            sav.load(model,timer);
        }
        Save save = new Save(model, timer);
        Autosave autosave = new Autosave(save);
        // finalmente, executa o view.....
        tabuleiroController.runTabuleiro();
      }
    });
  }
}
