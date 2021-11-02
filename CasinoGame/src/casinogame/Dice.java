package casinogame;

import java.util.Random;

import static java.lang.Math.abs;

public class Dice extends SubGame{

    //Attributes
    private int selectedFace;
    private int resultedFace;
    private int lossAmount;
    private final Random rollDice;
    
    enum Result { WIN, NO_W_L, LOST }
    Dice.Result myResult;

    //Constructors
    public Dice()
    {
        super();
        this.selectedFace = 1;
        this.resultedFace = 1;
        this.lossAmount = 0;
        this.rollDice = new Random();

    }

    public Dice(int currentCurrency, int bet,int totBet, int winAmount, int noGames, int noWins, int loss, int refund)
    {
        super(currentCurrency,
                bet,
                totBet,
                winAmount,
                noGames,
                noWins);
        this.selectedFace = 1;
        this.resultedFace = 1;
        this.lossAmount = loss;
        this.rollDice = new Random();

    }

    //Getters and Setters
    public int getSelectedFace () {
        return selectedFace;
    }

    public void setSelectedFace ( int outcomeDecision) {
        selectedFace = outcomeDecision;
    }

    public int getResultedFace() {
        return resultedFace;
    }

    public void setResultedFace (int outcomeResult) {
        resultedFace = outcomeResult;
    }
    
    public void setLoss(double loss){
        this.lossAmount = (int) loss;
    }


    public void setData () {
        this.setReward(returnDie());
        currency.setValue(this.getReward() + this.currency.getValue());
        super.totAmountBet += this.getBetAmount();
        //super.totWinAmount += this.getReward();
       //this.numberOfWins += 1;
        updateData();

    }

    public int randomDie () {
        return 6 - (abs((rollDice.nextInt() % 6) % 6));
        // returns a random number from {1, 2, …, 6}.
        // there is a very slightly lower chance of rolling 1 or 2,
        // as nextInt()’s pool of 2^32 is not divisible by 6.
        // The four remaining options are mapped to the higher numbers to be nice to the player.
    }
    
    
    
    public double returnDie () {
        // i = input bet, c = choice, o = output result
        if (resultedFace == selectedFace) {
            myResult = Dice.Result.WIN;
            totWinAmount += (selectedFace* getBetAmount());
            numberOfWins += 1;
            return (selectedFace* getBetAmount());
        }
        else if (resultedFace> selectedFace) {
            
            myResult = Dice.Result.NO_W_L;
            totWinAmount += getBetAmount();
            return getBetAmount();
        }
        else
            myResult = Dice.Result.LOST;
            lossAmount += getBetAmount();
            return 0;
    }

    public String faceToString (){

        return ("" + resultedFace);
    }
    
    @Override
    public String displayStat() {        
        return "The total amount of currency you have bet is: " + totAmountBet + "\n"
              + "The total amount of currency you have received back is:  " + (totWinAmount) + "\n\n"
              + "In all loosing games, you have lost the currency amount of: -" + lossAmount + "\n\n"
              + "Your actual profit is: " + (totWinAmount - totAmountBet) +"\n\n"   
              + "The total number of games you have played is: " + numberOfGames + "\n"
              + "The total amount of wins you have is: " + numberOfWins ;
         
    }
    
    @Override
    public String displayAlgorithm() {
        return " In the dice game the algorithm was desgined to help you guessing from face 3 to 6! \n"
                + "The formula used to determine a roll is: \n\n"
                + "6 - (abs((rollDice.nextInt() % 6) % 6)); \n\n"
                + "there is a very slightly lower chance of rolling 1 or 2. \n"
                + "nextInt()’s pool of 2^32 is not divisible by 6.\n"
                + "By making the results with higher rewards more likely, casinos increase your \n"
                + "chances of betting on those and their probability to keep the bet.";
         
    }
    
    @Override
    public double incrementBet() {
        betAmount.setValue(betAmount.getValue()+ 3) ;
        return betAmount.getValue();
    }
    @Override
    public void roll() {
        //int outcome = rollDice.nextInt(5) +1;
        resultedFace = randomDie();
    }

    
    public String outcomeString() {
        String message = "";

        if ( myResult == Result.WIN ){
            return message += "Congratulations! You guessed it right! You win.";
        }
        else if (myResult == Result.NO_W_L) {
            return message += "Still bigger, my friend." ;
        }
        else
        {
            return  message +="You lost! So sorry!";
        }
    }
    
        
    public String stringLoss(){
        return "" + lossAmount + "\n";
    }
}