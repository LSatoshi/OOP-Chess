/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

import Model.Peca;

/**
 *
 * @author satoshi
 */
public class PieceNotFound extends Exception{
    Peca p;
    
    public PieceNotFound(Peca p) {
        this.p = p;
    }
    
    public PieceNotFound() {
        this.p = null;
    }
}
