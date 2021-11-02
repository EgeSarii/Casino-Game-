/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinogame;

import java.util.Random;

/**
 *
 * @author matteo
 */
public class SuitBlock extends SubGame {
    private final CardSuits [] block1;
    private int                suitChoice;
    private boolean            switcher;
    private int                result;
    private int                 loss;
    private Random              r;

    public SuitBlock (CardSuits [] suit) {
        super();
        this.block1 = suit;
        this.switcher = false;
        this.result = 0;
        this.loss = 0;
        this.r = new Random();
    }
  
    public SuitBlock (int currentCurrency, int bet,int totBet, int winAmount,
                      int noGames, int noWins, int loss, CardSuits [] suit) {
        super (currentCurrency, bet, totBet, winAmount,noGames, noWins);
        this.block1 = suit;
        this.result = 0;
        this.loss = loss;
        this.r = new Random();
    }
    
    public void setSelectedSuit (String selection) {
        if ( selection.equalsIgnoreCase("♣")) {
            suitChoice = 0;
        }
        else if (selection.equalsIgnoreCase("♥")) {
            suitChoice = 1;
        }
        else if (selection.equalsIgnoreCase("♠")) {
             suitChoice = 2;
         }
        else {
             suitChoice = 3;
         }
    }
    
    public String getSelectedSuit() {
        switch (suitChoice){
            case 0  : return "♣";
            case 1  : return "♥";
            case 2  : return "♠";
            default : return "♦";
        }
    }
    
    public void setResult( int res) {
        result = res;
    }
    
    public int getResult() {
        return result;
    }
    
    
    public void setSwitcher( boolean switcher) {
        this.switcher = switcher;
    }
    public void setLoss (int loss){
        this.loss = loss;
    }
    
    public void randomSuit (int c) {
        result = (c + (r.nextInt() % 64 / 33) + 2 * (r.nextInt() % 64 / 33)) % 4;
    
    }
    
    
    public boolean checkFlip() {
        if (switcher) {
           if (suitChoice == result) {
               reward.setValue( betAmount.getValue() * 3 );
               currency.setValue(currency.getValue() + reward.getValue());
               return true;
           }
           else if ( (suitChoice - result) % 2 == 0 ){
               reward.setValue(betAmount.getValue());
               currency.setValue(currency.getValue() + reward.getValue());
               return true;
           }
           else {
            reward.setValue(betAmount.getValue()); //* -1
            //this.currency.setValue(this.currency.getValue());
            loss += betAmount.getValue();
            return false;
            }
        }
        else if (suitChoice == result) {
               reward.setValue( betAmount.getValue() * 4 );
               currency.setValue(currency.getValue() + reward.getValue());
               return true;
        }
        else {
            //this.reward.setValue(this.betAmount.getValue());
            //this.currency.setValue(this.currency.getValue());
            loss += betAmount.getValue();
            return false;
        }
    }
    
    

    public void setData() {
        if (checkFlip()) {
            totWinAmount += getReward();
            numberOfWins += 1;
        }
        totAmountBet += getBetAmount();
        updateData();
    }
    
    public String outcomeString(){
        switch (result) {
            case 0  :  return "♣"; 
            case 1  :  return "♥"; 
            case 2  :  return  "♠"; 
            default :  return "♦"; 
        }     
        
    }
    
    @Override
    public String displayStat() {
        
        
        return "The total amount of currency you have bet is: " + totAmountBet + "\n"
              + "The total amount of currency you have received back is:  " + (totWinAmount) + "\n"
              + "In all loosing games, you have lost the currency amount of: -" + loss + "\n"
              + "Your actual profit is: " + (totWinAmount - totAmountBet) +"\n"   
              + "The total number of games you have played is: " + numberOfGames + "\n"
              + "The total amount of wins you have is: " + numberOfWins ;
         
    }
    
    @Override
    public String displayAlgorithm() {
        return  " In the suit game the algorithm was designed to encourage the bigger risk! \n"
                + "The formula used to return your choice is: \n\n"
                + "(yourChoice + (r.nextInt() % 64 / 33) + 2 * (r.nextInt() % 64 / 33)) % 4; \n\n"
                + "This returns the chosen suit’s colour with a 33/64 chance\n"
                + "and the chosen suit with a (33/64)² chance.";

    }

    
    @Override
    public double incrementBet() {
         betAmount.setValue(betAmount.getValue() + 2);
         return betAmount.getValue();
    }

    @Override
    public void roll() {
        randomSuit (suitChoice);
        setData();
    }
    
            
    public String stringLoss(){
    return "" + loss + "\n";
    }
}

    
    
