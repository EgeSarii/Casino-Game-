package casinogame;

import java.util.Arrays;
import java.util.Random;
import static java.lang.Math.abs;
import javafx.scene.control.Alert;

public class FiveDice extends SubGame{

    //Attributes
    private Dice[] resultedFaces = new Dice[5];


    enum Result {
        FIVE_KIND, FOUR_KIND, FULL_HOUSE, THREE_KIND,
        TWO_PAIRS,BIG_FLUSH, SMALL_FLUSH, JUNK }
    FiveDice.Result myResult;
    private int lossAmount;
    private int tries;

    //Constructors
    public FiveDice()
    {
        super();
         this.resultedFaces = new Dice[]{new Dice(), new Dice(), new Dice(), new Dice(), new Dice()};
         this.lossAmount = 0;
         this.tries = 0;

    }

    public FiveDice(int currentCurrency, int bet,int totBet, int winAmount, int noGames, int noWins, int tries, int loss) {
        super(currentCurrency,
                bet,
                totBet,
                winAmount,
                noGames,
                noWins);
        this.resultedFaces = new Dice[]{new Dice(), new Dice(),new Dice(),new Dice(),new Dice()};
        this.tries= tries;
        this.lossAmount = loss;
    }

    //Getters and Setters

    public Dice getResultedFaces(int index) {
        return resultedFaces[index];
    }

    public void setResultedFaces (Dice outcomeResult, int index) {
        resultedFaces[index] = outcomeResult;
    }
    
    public int getTries() {
        return tries;
    }
    
    public void setTries( int neW) {
        tries = neW;
    }
    public void setLoss(int nextInt) {
        this.lossAmount = nextInt;
            }
    
    public void setData () {

        setReward(returnFiveDice());
        currency.setValue(getReward() + currency.getValue());
        totWinAmount += getReward();
        totAmountBet += this.getBetAmount();
        updateData();
    }
    
    public Dice[] randomFiveDice () {
        Dice[] Dices = new Dice[5];
        for(int i = 0; i< 5; i++)
        {
            Dice dice = new Dice();
            dice.roll();
            Dices[i] = dice;
        }
        return Dices;
    }


    public double returnFiveDice () {
        if (5 != resultedFaces.length)
            throw new IllegalArgumentException("o.length must be exactly 5");

        int[] h = {0, 0, 0, 0, 0, 0}; // histogram
        for (int k = 0; k < 5; k++) // k < 5 because o.length is 5
        {
            h[((this.resultedFaces[k]).getResultedFace()) -1 ]++;
        }
        int[] hh = {0, 0, 0, 0, 0}; // histogram of histogram
        for (int k = 0; k < 6; k++) // k < 6 because h.length is 6
        {
            if (h[k] >= 1) {
                hh[h[k] - 1]++;
            }


        }

        if (Arrays.equals(hh, new int[]{0, 0, 0, 0, 1})) // five of a kind
        {
            this.myResult = Result.FIVE_KIND;
            numberOfWins += 1;
            return (4 * getBetAmount());
        } else if (Arrays.equals(hh, new int[]{1, 0, 0, 1, 0})) // four of a kind
        {
            this.myResult = Result.FOUR_KIND;
            numberOfWins += 1;
            return (2 * getBetAmount());
        } else if (Arrays.equals(hh, new int[]{0, 1, 1, 0, 0})) // full house
        {
            this.myResult = Result.FULL_HOUSE;
            numberOfWins += 1;
            return (1.2 * getBetAmount());
        } else if (Arrays.equals(hh, new int[]{2, 0, 1, 0, 0})) // three of a kind
        {
            this.myResult = Result.THREE_KIND;
            numberOfWins += 1;
            return (0.6 * getBetAmount());
        } else if (Arrays.equals(hh, new int[]{3, 2, 0, 0, 0})) // two pairs
        {
            this.myResult = Result.TWO_PAIRS;
            numberOfWins += 1;
            return (0.4 * getBetAmount());
        } else if (Arrays.equals(hh, new int[]{5, 0, 0, 0, 0}) ||
                Arrays.equals(hh, new int[]{3, 1, 0, 0, 0})) {

            if ((h[0] != 0 && h[1] != 0 && h[2] != 0 && h[3] != 0 && h[4] != 0 ) ||
                    (h[1] != 0 && h[2] != 0 && h[3] != 0 && h[4] != 0 && h[5] != 0)) // big flush, five in a row
            {
                this.myResult = Result.BIG_FLUSH;
                numberOfWins += 1;
                return (2 * getBetAmount());

            } else if ((h[0] != 0 && h[1] != 0 && h[2] != 0 && h[3] != 0 ) ||
                    (h[1]!= 0 && h[2] != 0 && h[3] != 0 && h[4] != 0) ||
                    (h[2] != 0 && h[3] != 0 && h[4] != 0 && h[5] != 0)) // smol flush, four in a row
            {
                this.myResult = Result.SMALL_FLUSH;
                numberOfWins += 1;
                return (1 * getBetAmount());
            } else // junk
                lossAmount += getBetAmount();
                this.myResult = Result.JUNK;

        }
        return 0;
    }
    
    public void showAlert(){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText("You can not spin a single dice more than three times!\n" +"Roll all again!" );
        a.show();
    }

    public String faceToString (){

        return ("" + resultedFaces[0].faceToString() + " ,"
        + resultedFaces[1].faceToString() + " ,"
        + resultedFaces[2].faceToString() + " ,"
        + resultedFaces[3].faceToString() + " ,"
        + resultedFaces[4].faceToString());
    }
    
    @Override
    public String displayStat() {        
        return "The total amount of currency you have bet is: " + totAmountBet + "\n\n"
              + "The total amount of currency you have received back is:  " + (totWinAmount) + "\n\n"
              + "In all loosing games, you have lost the currency amount of: -" + lossAmount + "\n\n"
              + "Your actual profit is: " + (totWinAmount - totAmountBet) +"\n\n"   
              + "The total number of games you have played is: " + numberOfGames + "\n\n"
              + "The total amount of wins you have is: " + numberOfWins ;         
    }
    
    @Override
    public String displayAlgorithm() {
        return " In the dice game the algorithm was desgined to help you guessing from face 3 to 6! \n"
                + "The formula used to determine a roll is: \n\n"
                + "6 - (abs((rollDice.nextInt() % 6) % 6)); \n\n"
                + "This formula is the same one of single dices, but it is used five times. \n";
                         
    }

    @Override
    public double incrementBet() {
        betAmount.setValue(betAmount.getValue()+ 5) ;
        return betAmount.getValue();
    }

    @Override
    public void roll() {
        //int outcome = rollDice.nextInt(5) +1;
        Dice outcome[] = randomFiveDice();
        for(int i = 0; i<5; i++) {
            resultedFaces[i] = outcome[i];
        }
    }

        public String outcomeString() {
        

        if ( null == this.myResult ){
            return  "Junk";
        }
        else switch (this.myResult) {
            case FIVE_KIND:
                return  "Five Kind";
            case FOUR_KIND:
                return  "Four Kind" ;
            case FULL_HOUSE:
                return  "Full House" ;
            case THREE_KIND:
                return  "Three Kind" ;
            case BIG_FLUSH:
                return  "Big Flush" ;
            case SMALL_FLUSH:
                return  "Small flush" ;
            case TWO_PAIRS:
                return  "Two Pairs" ;
            default:
                return  "Junk";
        }
    }
    
        public String stringLoss(){
            return "" + lossAmount + "\n" +
                  tries + "\n";
        }
}