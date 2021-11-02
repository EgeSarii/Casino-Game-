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
public class Coin extends SubGame {
    //Attributes
    private boolean selectedFace;
    private boolean resultedFace;
    private final Random flipCoin;
    
    
    //Constructor
    public Coin () {
        super();
        this.selectedFace = true;
        this.resultedFace = true;
        this.flipCoin = new Random(); 
    }
    
    public Coin (int currentCurrency, int bet,int totBet, int winAmount, int noGames, int noWins) {
        super (currentCurrency, bet, totBet, winAmount,noGames, noWins);
        this.selectedFace = true;
        this.resultedFace = true;
        this.flipCoin = new Random();
    }
    
    //Getters and Setters
    public boolean getSelectedFace () {
        return selectedFace;
    }
    
    public void setSelectedFace ( boolean outcomeDecision) {
        selectedFace = outcomeDecision;
    }
    
    public boolean getResultedFace() {
        return resultedFace;
    }
    
    public void setResultedFace (boolean outcomeResult) {
        resultedFace = outcomeResult;
    }
    
    //Methods
    public boolean flipCheck() {
        return (resultedFace == selectedFace);
    }
    
    public void setData () {
        if (flipCheck()) {
            reward.setValue(this.getBetAmount() * 2);
            currency.setValue(this.getReward() + this.currency.getValue());
            totWinAmount += (this.getReward()/2);
            super.totAmountBet += super.getBetAmount();
            numberOfWins += 1;
            updateData();
        }
        else {
            reward.setValue(this.getBetAmount() );
            currency.setValue( this.currency.getValue());
            super.totAmountBet += super.getBetAmount();
            updateData();
        }
        
    }
    
    public String faceToString (){
        if (resultedFace )
            return "HEAD";
        else{
            return "TAIL";
        }
    }
    
    public boolean randomCoin (boolean c) {
    return c ^ (flipCoin.nextInt() % 32 >= 17);
    }
        
    public String outcomeString() {          

        if ( this.flipCheck() ){
            return  "Congratulations! You guessed it right! You win.";
        }
        else {
            return "How unlucky, you guessed it wrong. You loose your bet." ;   
        } 
    }

    @Override
    public String displayStat() {
        String stat = "";
        
        stat += "The total amount of currency you have bet is: " + totAmountBet + "\n"
              + "The total amount of currency you have received back is:  " + (totAmountBet + totWinAmount) + "\n\n"
              + "In all loosing games, you have lost the currency amount of: " + (totWinAmount - totAmountBet) + "\n"
              + "In all winning games, you have won the currency amount of: " + totWinAmount + "\n\n"
              + "Your actual profit is: " + (totWinAmount + (totWinAmount - totAmountBet)) +"\n\n"   
              + "The total number of games you have played is: " + numberOfGames + "\n"
              + "The total amount of wins you have is: " + numberOfWins ;
        return stat;
    }
    
    @Override
    public String displayAlgorithm() {
        
        return " In the coin game the algorithm was desgined to help you guessing! \n"
                + "The formula used to return your choice is: \n\n"
                + "yourChoice ^ (flipCoin.nextInt() % 32 >= 17); \n\n"
                + "Because of this, your selected face has probability 17/32, so ~53%.\n"
                + "Making you win is a technique used by casinos to make players more confident\n"
                + "and increase the probability of bigger bets.";
         }
    
    @Override
    public double incrementBet() {
        betAmount.setValue(betAmount.getValue()+ 1) ;
        return betAmount.getValue();
    }
    


    @Override
    public void roll() {
        resultedFace = randomCoin (selectedFace);
    }  
}
