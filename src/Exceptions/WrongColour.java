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
public class WrongColour extends Exception{
    Peca p;
    int turno;

    public WrongColour(Peca p, int turno) {
        this.p = p;
        this.turno = turno;
    }

    public WrongColour() {
        this.p = null;
        this.turno = -1;
    }

}
