/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinogame;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author matteo
 */
public enum CardSuits {
    CLUB (0),
    HEART (1), 
    SPADE (2), 
    DIAMOND (3); 
    
    private int value;
    private static Map map = new HashMap<>();
    
    
    private CardSuits (int value) {
        this.value = value;
    }
   
    static {
        for (CardSuits suit : CardSuits.values()) {
            map.put(suit.value, suit);
        }
    }
    
    public static CardSuits valueOf(int index) {
        return (CardSuits) map.get(index);
    }

    public int getValue() {
        return value;
    }
    
    int randomSuit (int c) {
        Random r = new Random();
        return (c + (r.nextInt() % 64 / 33) + 2 * (r.nextInt() % 64 / 33)) % 4;
    }
    
    public String roll(int suitChoice) {
        int indexResult = randomSuit(suitChoice);
        
        switch (indexResult){
            case 0  : return "♣";
            case 1  : return "♥";
            case 2  : return "♠";
            default : return "♦";
        }
    }
    
}
