package com.company.systems;

import com.company.scenes.DrawScene;

public class InnsettingSystem {
    private DrawScene drawScene;
    public InnsettingSystem(DrawScene drawScene) {
        this.drawScene = drawScene;
    }

    /**
     * Sjekker om stringen inneholder kun alfabet bokstaver
     * @param s String
     * @return true hvis det ikke er tall eller symboler, false hvis det inneholder tall eller symboler.
     */
    public boolean sjekkForSymboler(String s){
        if(s.matches("^[-a-zA-Z]+")){
            return true;
        }else return false;
    }

    /**
     * Sjekker om nummer er lik eller mindre en 999.
     * @param n int
     * @return true hvis den er mindre eller lik 999 eller false hvis ikke.
     */
    public boolean sjekkNummer(int n){
        return n <= 999;
    }

    public boolean sjekkOmNummerEksisterer(int i, String s) {
        if (!drawScene.getTree().search(i)) {
            return true;
        } else {
            drawScene.showErrorMessage(s);
            return false;
        }
    }
    public boolean sjekkOmCharEksisterer(char input, String s){
        if(!drawScene.getTree().search(input)) {
            return true;
        } else {
            drawScene.showErrorMessage(s);
            return false;
        }
    }

}
