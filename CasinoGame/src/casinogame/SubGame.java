/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinogame;

import javafx.beans.property.*;

/**
 *
 * @author matteo
 */
public abstract class SubGame {
    //Property to display during game.
    protected IntegerProperty currency;     //Currency available.
    protected IntegerProperty betAmount;   //Amount to put in the next bet.
    protected IntegerProperty reward ;     //Winning reward.
    //Statistics data.
    protected int totAmountBet;        //Total amount of currency bet in all games.
    protected int totWinAmount;        //Total amount of currency win in all games.
    protected int numberOfGames;          //Number of played games.
    protected int numberOfWins;           //Number of wins.
    
    //Constructor for starting game.
    public SubGame () {
        this.currency = new SimpleIntegerProperty(100);
        this.betAmount = new SimpleIntegerProperty(0);
        this.reward = new SimpleIntegerProperty(0);
        this.totAmountBet = 0;
        this.totWinAmount = 0;
        this.numberOfGames = 0;
        this.numberOfWins = 0;
    }
    
    //Constructor for loading game.
    public SubGame (int currentCurrency, int bet, int totBet, int winAmount, int noGames, int noWins) {
        this.currency = new SimpleIntegerProperty (currentCurrency);
        this.betAmount = new SimpleIntegerProperty (bet);
        this.reward = new SimpleIntegerProperty (0);
        this.totAmountBet = totBet;
        this.totWinAmount = winAmount;
        this.numberOfGames = noGames;
        this.numberOfWins = noWins;
    }
    
    //Setters and getters.
    public void setCurrency (double current) {
        currency.setValue( current);
    }
    
    public double getCurrency() {
        return currency.getValue();
    }
    
    public IntegerProperty getCurrencyProperty () {
        return currency;
    }
    
    public void setBetAmount (double current) {
        betAmount.setValue(current);
    }
    
    public double getBetAmount() {
        return betAmount.getValue();
    }
    
    public IntegerProperty getBetAmountProperty () {
        return betAmount;
    }
   
    public void setReward (double current) {
        reward.setValue(current);
    }
    
    public double getReward() {
        return reward.getValue();
    }
    
    public IntegerProperty getRewardProperty () {
        return reward;
    }
    
    public void setTotAmountBet (int current) {
        totAmountBet = current;
    }
    
    public double getTotAmountBet() {
        return totAmountBet;
    }
    
    public void setTotWinAmount (int current) {
        totWinAmount = current;
    }
    
    public double getTotWinAmount() {
        return totWinAmount;
    }
        
    public void setNoOfGames() {
        numberOfGames++;
    }
    
    public void setNoGames(int noGames){
        this.numberOfGames = noGames;
    }
    
    public int getNoOfGames () {
        return numberOfGames;
    }
    
    public void SetNoOfWins() {
        numberOfWins++;
    }
    
    public void setNoWins(int noWins){
        numberOfWins = noWins;
    }
    
    public int getNoWins () {
        return numberOfWins;
    }
    
    public void updateData () {
        numberOfGames += 1;
    }
    
    @Override
    public String toString(){
       return "" + betAmount.getValue() + "\n" 
              + currency.getValue() + "\n"
              + totAmountBet + "\n"
              + totWinAmount + "\n"
              + numberOfGames + "\n"
              + numberOfWins+ "\n";
               
               
    }
    
    //Abstarct methods for sub-games.
    public abstract double incrementBet();  
    
    public abstract void roll();
    
    public abstract String displayStat();
    
    public abstract String displayAlgorithm();
    
    
}
