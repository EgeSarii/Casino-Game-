/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinogame;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author colin
 */
public class gameJavaFx {
    private int game;
    private Stage stage;
    private Button switchToDice = new Button ("NEXT GAME");
    private Button switchToSuit = new Button ("NEXT GAME");
    private Button switchToFive = new Button ("NEXT GAME");
    private Button switchToData = new Button ("VIEW RESULTS & STATISTICS");
    private Button saving = new Button("Saving and Loading");
    private Scene scene1 = new Scene(new BorderPane(),640,480);
    private Scene scene2 = new Scene (new BorderPane(), 640,480);
    private Scene scene3 = new Scene (new BorderPane(), 640,480);
    private Scene scene4 = new Scene (new BorderPane(), 640,480);
    private Scene scene5 = new Scene (new BorderPane(), 640,480);
    private Scene trans1 = new Scene (new BorderPane(), 640,480);
    private Scene trans2 = new Scene (new BorderPane(), 640,480);
    private Scene trans3 = new Scene (new BorderPane(), 640,480);
    private Scene trans4 = new Scene (new BorderPane(), 640,480);
    private Image img0;
    private BackgroundSize bSize;
    private Coin coin = new Coin();
    private Dice dice = new Dice();
    private CardSuits [] suits = CardSuits.values();
    private SuitBlock suitblock = new SuitBlock( suits );
    private FiveDice fivedice = new FiveDice();
        
    
    public gameJavaFx(Stage stage){
       this.stage = stage; 
       this.game = 0;
       this.img0 =  new Image(getClass().getResourceAsStream("/Images/casino-background.png"));
       this.bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
    }
    public gameJavaFx(Stage stage,int game){
        this.stage = stage;
        this.game = game;
        this.img0 =  new Image(getClass().getResourceAsStream("/Images/casino-background.png"));
        this.bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);

    }
    
    //getters and setters
    public Scene getCurrentScene(){
        switch(game){
            case 0 : return scene1;
            case 1 : return scene2;
            case 2 : return scene3;
            case 3 : return scene4;
            case 4 : return scene5;
        }
        return scene1;
    }
    public String getcurGame(){
        return  "" + game +  "\n";
    }
    
    // helper functions
    public boolean goToNextGame(int cur, int gameNum, int numberPlayed){
        if(coin.currency.getValue() >= cur || numberPlayed>= gameNum){
                return game<4;
        }
        return false;
    }
    
    public void callCurGame(int game){
     switch(game){
            case 0 : coinGame(); break;
            case 1 : diceGame(); break;
            case 2 : suitBlockGame(); break;
            case 3 : fiveDiceGame(); break;
            case 4 : displayStats(); break;
            default : displayStats();
        }
    }
    
    //functions
    public void coinGame(){
         Label coinGreetings = new Label (" Pick heads/tails, chose an amount and click Roll.");
                                                           
        coinGreetings.setFont(new Font("Piazzola",15) );
        coinGreetings.setTextFill(Color.WHITE);
        
        Button coinIncrement = new Button("Increment +1");
        
        Button coinRoll = new Button("Roll");
        
        RadioButton coinChoiceHead = new RadioButton("");
        RadioButton coinChoiceTail = new RadioButton("");
        
        Label coinInfo = new Label("");
        coinInfo.setTextFill(Color.GOLD);
        
        Label coinCur_info = new Label("");
        
        Label coinTxt1 = new Label("Your currency is:" + coin.getCurrency() );
        coinTxt1.setTextFill(Color.WHITE);
        Label coinTxt2 = new Label("Your bet is: " );
        coinTxt2.setTextFill(Color.WHITE);
        Label coinTxt3 = new Label("The result is: ");
        coinTxt3.setTextFill(Color.WHITE);
        Label coinTxt4 = new Label();
        coinTxt4.setTextFill(Color.GOLD);
        coinTxt4.setFont( new Font("Arial", 17));
        
        Label head = new Label ("Head");
        VBox coinPic1 = new VBox (head, coinChoiceHead); 
        Label tail = new Label ("Tail");
        VBox coinPic2 = new VBox (tail, coinChoiceTail);
        
        
        /**********************
         * IMAGES OF COIN GAME
        ***********************/
        // Background
        ImageView viewbackground = new ImageView(img0);
        viewbackground.setPreserveRatio(true);
        //Head icon in RadioButton CoinChoiceHead.
        Image img1 = new Image(getClass().getResourceAsStream("/Images/casino-coin-0.png"));
        ImageView viewHead = new ImageView(img1);
        viewHead.setFitHeight(50);
        viewHead.setPreserveRatio(true);
        coinChoiceHead.setGraphic(viewHead);
        //Head icon in RadioButton CoinChoiceTail.
        Image img2 = new Image(getClass().getResourceAsStream("/Images/casino-coin-1.png"));
        ImageView viewTail = new ImageView(img2);
        viewTail.setFitHeight(50);
        viewTail.setPreserveRatio(true);
        coinChoiceTail.setGraphic(viewTail);
        
        //  HANDLERS ONLY USED IN COIN GAME.
        //Handler CoinIncrement button.
        coinIncrement.setOnAction((ActionEvent event) -> {
            coinInfo.textProperty().bind(Bindings.concat( coin.getBetAmountProperty() ));
            coin.incrementBet();
            coin.setCurrency(coin.getCurrency() - 1);
            coinCur_info.textProperty().bind(Bindings.concat(coin.getCurrencyProperty()));
            coinTxt1.setText("Your currency is:" + coinCur_info.getText());
            
        });
        
        //Handler coinChoiceHead button.
        coinChoiceHead.setOnAction ( (ActionEvent event) -> {
            coin.setSelectedFace (true);
        });
        
        //Handler coinChoiceTail button.
        coinChoiceTail.setOnAction ( (ActionEvent event) -> {
            coin.setSelectedFace (false);
        });
        
        //Setting radio buttons.
        ToggleGroup sizeGroup = new ToggleGroup();
        coinChoiceHead.setToggleGroup(sizeGroup);
        coinChoiceTail.setToggleGroup(sizeGroup);
        
        //Creating Horizontal pane for radio buttons.
        HBox pane1 = new HBox(coinPic1, coinPic2);
        pane1.setSpacing(20);
        pane1.setAlignment(Pos.CENTER);
        
        //Creating pane for coinIncrement and binded label.
        HBox pane2 = new HBox (coinIncrement, coinInfo);
        
        pane2.setAlignment(Pos.CENTER);
        
        //Creating pane containing layout of game.
        VBox pane3 = new VBox(pane1, pane2, coinRoll);
        pane3.setSpacing(35);
        pane3.setAlignment(Pos.CENTER);
        
        //Creating pane for information.
        HBox pane4 = new HBox(coinTxt1, coinTxt2, coinTxt3);
        pane4.setSpacing(50);
        pane4.setAlignment(Pos.CENTER);
        VBox paneAlign = new VBox (pane4, coinTxt4);
        paneAlign.setAlignment(Pos.CENTER);
        paneAlign.setSpacing (25);
        //Creating pane for saving button
        // Creating last pane to contain all previous panes.
        VBox pane5 = new VBox(paneAlign, pane3);
        VBox.setMargin(paneAlign, new Insets (75));
        pane5.setSpacing(25);
        pane5.setAlignment(Pos.CENTER);
        
        VBox pane15 = new VBox (coinGreetings,saving);
        pane15.setAlignment(Pos.CENTER);
        
        
        BorderPane coinPane = new BorderPane (pane5, pane15, null, null, null);
                coinPane.setBackground(new Background(new BackgroundImage(
                img0,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));
        scene1.setRoot(coinPane);
        stage.setScene(scene1);
        //Creating scenes for sub-game 1.
            coinRoll.setOnAction((ActionEvent event) -> {
            coin.roll();
            coin.setData();
            
            coinTxt1.setText("Your currency is: " + coin.getCurrency() );
            coinTxt2.setText("Your bet is: " + coinInfo.getText());
            coinTxt3.setText("The result is: " + coin.faceToString());
            coinTxt3.setTextFill(Color.WHITE);
            coinTxt4.setText(coin.outcomeString());
            
            coinInfo.textProperty().unbind();
            coinInfo.setText("" + 0);
            coin.setBetAmount(0);
           
            if (goToNextGame(145,10,coin.getNoOfGames() )){
                StackPane coinToSuit = new StackPane(switchToSuit);
                coinToSuit.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                trans1.setRoot(coinToSuit);
                stage.setScene(trans1);
            }
            else if (game >4 && coin.getNoOfGames() <=10){
                Label goodBye = new Label ("YOU HAVE REACHED THE END OF THE GAME!");
                goodBye.setFont(new Font ("Arial", 25));
                goodBye.setTextFill(Color.DARKBLUE);
                Label finalCurrency = new Label ("Starting at 100, your final currency amount is "
                                                + fivedice.currency.getValue());
                finalCurrency.setFont(new Font ("Arial", 20));
                finalCurrency.setTextFill(Color.DARKBLUE);
                
                StackPane finish = new StackPane(goodBye);
                VBox end = new VBox(finalCurrency, switchToData);
                end.setSpacing(25);
                end.setAlignment(Pos.CENTER);
                BorderPane endPlay = new BorderPane (end, finish, null, null, null);
                endPlay.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                trans4.setRoot(endPlay);
                stage.setScene(trans4);
                
            }
        });
            }
    
    
    public void suitBlockGame(){
        Label blockGreetings = new Label ("Select the mode, the outcome and the amount you wish to bet.\n"
                + "With \"3+1\" you get x3 the bet if you win, x1 if the color matches.\n"
                + "With \"4\" you get x4 the bet only if you win.");
        blockGreetings.setTextFill(Color.WHITE);
        
        Button blockIncrement = new Button("Increment +2");
        
        Button blockRoll = new Button("Roll");
        
        RadioButton blockChoiceClub = new RadioButton();
        RadioButton blockChoiceHeart = new RadioButton();
        RadioButton blockChoiceSpade = new RadioButton();
        RadioButton blockChoiceDiamond = new RadioButton();
        
        RadioButton mode3 = new RadioButton("3+1");
        mode3.setTextFill(Color.WHITE);
        RadioButton mode4 = new RadioButton("4");
        mode4.setTextFill(Color.WHITE);
        
        Label blockCur_info = new Label("");
        blockCur_info.setTextFill(Color.GOLD);
        Label blockInfo = new Label("");
        blockInfo.setTextFill(Color.GOLD);
        
        Label blockTxt1 = new Label("Your currency is:" + coin.currency.getValue() );
        blockTxt1.setTextFill(Color.WHITE);
        Label blockTxt2 = new Label("Your bet is: " );
        blockTxt2.setTextFill(Color.WHITE);
        Label blockTxt3 = new Label("Result: ");
        blockTxt3.setTextFill(Color.GOLD);
        blockTxt3.setFont(new Font("Piazzola",25) );

        /****************************
         * IMAGES FOR SUIT BLOCK GAME
        *****************************/
        
        ImageView viewbackground = new ImageView(img0);
        viewbackground.setPreserveRatio(true);
        Image img9 = new Image(getClass().getResourceAsStream("/Images/casino-suit-0.png"));
        ImageView viewSuit1 = new ImageView(img9);
        viewSuit1.setFitHeight(50);
        viewSuit1.setPreserveRatio(true);
        blockChoiceClub.setGraphic(viewSuit1);
        
        Image img10 = new Image(getClass().getResourceAsStream("/Images/casino-suit-1.png"));
        ImageView viewSuit2 = new ImageView(img10);
        viewSuit2.setFitHeight(50);
        viewSuit2.setPreserveRatio(true);
        blockChoiceHeart.setGraphic(viewSuit2);
        
        Image img11 = new Image(getClass().getResourceAsStream("/Images/casino-suit-2.png"));
        ImageView viewSuit3 = new ImageView(img11);
        viewSuit3.setFitHeight(50);
        viewSuit3.setPreserveRatio(true);
        blockChoiceSpade.setGraphic(viewSuit3);
        
        Image img12 = new Image(getClass().getResourceAsStream("/Images/casino-suit-3.png"));
        ImageView viewSuit4 = new ImageView(img12);
        viewSuit4.setFitHeight(50);
        viewSuit4.setPreserveRatio(true);
        blockChoiceDiamond.setGraphic(viewSuit4);
          
            //HANDLERS NEEDED ONLY IN SUITBLOCK GAME.
        //Handler mode3.
        mode3.setOnAction((ActionEvent event) -> { 
            suitblock.setSwitcher(true);
        });
        
        //Handler mode4.
        mode4.setOnAction((ActionEvent event) -> { 
            suitblock.setSwitcher(false);
        });
        
        //Handler blockIncrement button.
        blockIncrement.setOnAction((ActionEvent event) -> {
            blockInfo.textProperty().bind(Bindings.concat( suitblock.getBetAmountProperty() ));
            suitblock.incrementBet();
            suitblock.setCurrency(suitblock.getCurrency() - 2);
            blockCur_info.textProperty().bind(Bindings.concat(suitblock.getCurrencyProperty()));
            blockTxt1.setText("Your currency is:" + blockCur_info.getText());
        });
       
        //Handler blockChoiceClub.
        blockChoiceClub.setOnAction ( (ActionEvent event) -> {
            suitblock.setSelectedSuit ( "♣" );    //"♣♥♠♦"
        });
        
        //Handler blockChoiceHeart.
        blockChoiceHeart.setOnAction ( (ActionEvent event) -> {
            suitblock.setSelectedSuit ( "♥" );
        });
        
        //Handler blockChoiceSpade.
        blockChoiceSpade.setOnAction ( (ActionEvent event) -> {
            suitblock.setSelectedSuit ( "♠" );
        });
        
        //Handler blockChoiceDiamond.
        blockChoiceDiamond.setOnAction ( (ActionEvent event) -> {
            suitblock.setSelectedSuit ( "♦" );
        });
        
        //Setting radio buttons.
        //Suits radio buttons.
        ToggleGroup sizeGroup1 = new ToggleGroup();
        blockChoiceClub.setToggleGroup(sizeGroup1);
        blockChoiceHeart.setToggleGroup(sizeGroup1);
        blockChoiceSpade.setToggleGroup(sizeGroup1);
        blockChoiceDiamond.setToggleGroup(sizeGroup1);
        //Modes radio buttons.
        ToggleGroup sizeGroup3 = new ToggleGroup();
        mode3.setToggleGroup(sizeGroup3);
        mode4.setToggleGroup(sizeGroup3);
        
        //Creating Horizontal panes for radio buttons.
        HBox pane7 = new HBox( blockChoiceClub,  blockChoiceHeart,  blockChoiceSpade,  blockChoiceDiamond);
        HBox pane13 = new HBox (mode3, mode4);
        pane13.setSpacing(100);
        pane13.setAlignment(Pos.CENTER); 
        pane7.setSpacing(20);
        pane7.setAlignment(Pos.CENTER);
      
        VBox pane14 = new VBox(pane13,pane7);
        pane14.setSpacing(20);
        pane14.setAlignment(Pos.CENTER);
        
        //Creating pane for increment and binded label.
        HBox pane8 = new HBox (blockIncrement, blockInfo);
        pane8.setSpacing(8);
        pane8.setAlignment(Pos.CENTER);

        //Creating pane containing layout of game.
        VBox pane18 = new VBox(pane14, pane8, blockRoll);
        pane18.setSpacing (25);
        pane18.setAlignment(Pos.CENTER);
       // VBox pane9 = new VBox (pane14,pane18);
        //pane9.setSpacing(35);
        //pane9.setAlignment(Pos.CENTER);
        
        //Creating pane for information to display.
        HBox pane10 = new HBox (blockTxt1, blockTxt2);
        VBox pane12 = new VBox(pane10, blockTxt3);
        pane10.setSpacing(100);
        pane10.setAlignment(Pos.CENTER);
        pane12.setSpacing(10);
        pane12.setAlignment(Pos.CENTER);
                // Creating last pane to contain all previous panes.
        VBox pane11 = new VBox (pane12, pane18);
        pane11.setSpacing(68);
        pane11.setAlignment(Pos.CENTER);
        StackPane pane16 = new StackPane (blockGreetings);
        VBox withSaving = new VBox(pane16, saving);
        withSaving.setAlignment(Pos.CENTER);
        StackPane.setMargin(blockGreetings, new Insets(10));
        BorderPane blockPane = new BorderPane (pane11, withSaving, null, null, null);
        blockPane.setBackground(new Background(new BackgroundImage(
                img0,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));
        
       
        //Creating scenes for games. 
        scene3.setRoot(blockPane);
        stage.setScene(scene3);
        
                blockRoll.setOnAction( (ActionEvent event) -> {
            suitblock.roll();
            
            blockTxt1.setText("Your currency is: " + suitblock.currency.getValue());
            blockTxt2.setText("Your bet is: " + blockInfo.getText());
            blockTxt3.setText("Result:" + suitblock.outcomeString());
            blockInfo.textProperty().unbind();
            blockInfo.setText("" + 0);
            suitblock.setBetAmount(0);
            
            if (goToNextGame(235,20,suitblock.getNoOfGames())){
                StackPane suitToDice = new StackPane(switchToDice);
                suitToDice.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                trans3.setRoot(suitToDice);
                stage.setScene(trans3);
            }
            else if (game >4){
                Label goodBye = new Label ("YOU HAVE REACHED THE END OF THE GAME!");
                goodBye.setFont(new Font ("Arial", 25));
                goodBye.setTextFill(Color.DARKBLUE);
                Label finalCurrency = new Label ("Starting at 100, your final currency amount is "
                                                + fivedice.currency.getValue());
                finalCurrency.setFont(new Font ("Arial", 20));
                finalCurrency.setTextFill(Color.DARKBLUE);
                
                StackPane finish = new StackPane(goodBye);
                VBox end = new VBox(finalCurrency, switchToData);
                end.setSpacing(25);
                end.setAlignment(Pos.CENTER);
                BorderPane endPlay = new BorderPane (end, finish, null, null, null);
                endPlay.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                trans4.setRoot(endPlay);
                stage.setScene(trans4);
                
            }
                        else if (game >4){
                Label goodBye = new Label ("YOU HAVE REACHED THE END OF THE GAME!");
                goodBye.setFont(new Font ("Arial", 25));
                goodBye.setTextFill(Color.DARKBLUE);
                Label finalCurrency = new Label ("Starting at 100, your final currency amount is "
                                                + fivedice.currency.getValue());
                finalCurrency.setFont(new Font ("Arial", 20));
                finalCurrency.setTextFill(Color.DARKBLUE);
                
                StackPane finish = new StackPane(goodBye);
                VBox end = new VBox(finalCurrency, switchToData);
                end.setSpacing(25);
                end.setAlignment(Pos.CENTER);
                BorderPane endPlay = new BorderPane (end, finish, null, null, null);
                endPlay.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                trans4.setRoot(endPlay);
                stage.setScene(trans4);
                
            }
        }); 
    }
    
    public void diceGame(){
         // Control components used for GUI of single dice game.
        Button diceIncrement = new Button("Increment +3");
        
        Button diceRoll = new Button("Roll");
        
        RadioButton diceChoice_1 = new RadioButton();
        RadioButton diceChoice_2 = new RadioButton();
        RadioButton diceChoice_3 = new RadioButton();
        RadioButton diceChoice_4 = new RadioButton();
        RadioButton diceChoice_5 = new RadioButton();
        RadioButton diceChoice_6 = new RadioButton();
        
        Label diceInfo = new Label("");
        diceInfo.setTextFill(Color.GOLD);
        
        Label diceGreetings = new Label ("Pick the dice outcome and increment the bet!");
        diceGreetings.setTextFill(Color.WHITE);
        
        Label diceCur_info = new Label("");
        Label diceTxt = new Label("");
        diceTxt.setTextFill(Color.GOLD);
        diceTxt.setFont (new Font ("Arial", 18));
        Label diceTxt1 = new Label ("Your currency is:" + suitblock.currency.getValue() );
        diceTxt1.setTextFill(Color.WHITE);
        Label diceTxt2 = new Label("Your bet is: " );
        diceTxt2.setTextFill(Color.WHITE);
        Label diceTxt3 = new Label("The result is: ");
        diceTxt3.setTextFill(Color.WHITE);
        
        /*********************
         * IMAGES OF DICE GAME
        **********************/
        
        Image img3 = new Image(getClass().getResourceAsStream("/Images/casino-dice-1.png"));
        ImageView viewDice1 = new ImageView(img3);
        viewDice1.setFitHeight(50);
        viewDice1.setPreserveRatio(true);
        diceChoice_1.setGraphic(viewDice1);
        
        Image img4 = new Image(getClass().getResourceAsStream("/Images/casino-dice-2.png"));
        ImageView viewDice2 = new ImageView(img4);
        viewDice2.setFitHeight(50);
        viewDice2.setPreserveRatio(true);
        diceChoice_2.setGraphic(viewDice2);
        
        Image img5 = new Image(getClass().getResourceAsStream("/Images/casino-dice-3.png"));
        ImageView viewDice3 = new ImageView(img5);
        viewDice3.setFitHeight(50);
        viewDice3.setPreserveRatio(true);
        diceChoice_3.setGraphic(viewDice3);
        
        Image img6 = new Image(getClass().getResourceAsStream("/Images/casino-dice-4.png"));
        ImageView viewDice4 = new ImageView(img6);
        viewDice4.setFitHeight(50);
        viewDice4.setPreserveRatio(true);
        diceChoice_4.setGraphic(viewDice4);
        
        Image img7 = new Image(getClass().getResourceAsStream("/Images/casino-dice-5.png"));
        ImageView viewDice5 = new ImageView(img7);
        viewDice5.setFitHeight(50);
        viewDice5.setPreserveRatio(true);
        diceChoice_5.setGraphic(viewDice5);
        
        Image img8 = new Image(getClass().getResourceAsStream("/Images/casino-dice-6.png"));
        ImageView viewDice6 = new ImageView(img8);
        viewDice6.setFitHeight(50);
        viewDice6.setPreserveRatio(true);
        diceChoice_6.setGraphic(viewDice6);
        
        //  HANDLERS ONLY USED IN SINGLE DICE GAME.
        //Handler diceIncrement button.
        diceIncrement.setOnAction((ActionEvent event) -> {
            dice.setBetAmount(dice.getBetAmount() + 3);
            dice.setCurrency(dice.getCurrency() -3);
            diceCur_info.textProperty().bind(Bindings.concat(dice.getCurrencyProperty()));
            diceTxt1.setText("Your currency is:" + diceCur_info.getText());
            diceInfo.textProperty().bind(Bindings.concat(dice.getBetAmountProperty()));
        });

        //Handler diceChoice_1 button.
        diceChoice_1.setOnAction ( (ActionEvent event) -> {
            dice.setSelectedFace (1);
        });

        //Handler diceChoice_2 button.
        diceChoice_2.setOnAction ( (ActionEvent event) -> {
            dice.setSelectedFace (2);
        });
        //Handler diceChoice_3 button.
        diceChoice_3.setOnAction ( (ActionEvent event) -> {
            dice.setSelectedFace (3);
        });

        //Handler diceChoice_4 button.
        diceChoice_4.setOnAction ( (ActionEvent event) -> {
            dice.setSelectedFace (4);
        });
        //Handler diceChoice_5 button.
        diceChoice_5.setOnAction ( (ActionEvent event) -> {
            dice.setSelectedFace (5);
        });

        //Handler diceChoice_6 button.
        diceChoice_6.setOnAction ( (ActionEvent event) -> {
            dice.setSelectedFace (6);
        });
        
        ImageView viewbackground = new ImageView(img0);
        viewbackground.setPreserveRatio(true);
        //Setting radio buttons.
        ToggleGroup sizeGroup2 = new ToggleGroup();
        diceChoice_1.setToggleGroup(sizeGroup2);
        diceChoice_2.setToggleGroup(sizeGroup2);
        diceChoice_3.setToggleGroup(sizeGroup2);
        diceChoice_4.setToggleGroup(sizeGroup2);
        diceChoice_5.setToggleGroup(sizeGroup2);
        diceChoice_6.setToggleGroup(sizeGroup2);
        
        //Creating pane for radio buttons.
        HBox pane20 = new HBox(diceChoice_1, diceChoice_2, diceChoice_3, diceChoice_4, diceChoice_5, diceChoice_6);
        
        pane20.setSpacing(20);
        pane20.setAlignment(Pos.CENTER);
        
        //Crating pane for greetings.
        StackPane pane21 = new StackPane(diceGreetings);
        StackPane.setMargin(diceGreetings, new Insets(10));
        VBox withSaving = new VBox (pane21,saving);
        withSaving.setAlignment(Pos.CENTER);
        
        //Creating pane for bet and its binded label.
        HBox pane22 = new HBox (diceIncrement, diceInfo);
        pane22.setSpacing(8);
        pane22.setAlignment(Pos.CENTER);
        
        //Creating a pane containing game actions.
        VBox pane28 = new VBox(pane20, pane22, diceRoll);
        pane28.setSpacing (30);
        pane28.setAlignment(Pos.CENTER);
        //VBox pane23 = new VBox(pane20, pane28);
        //pane23.setSpacing(75);
        //pane23.setAlignment(Pos.CENTER);

        //Creating a pane for information.
        HBox pane24 = new HBox(diceTxt1, diceTxt2,diceTxt3);
        pane24.setSpacing(100);
        pane24.setAlignment(Pos.CENTER);
        
        //Cresting pane for result label.
        VBox pane25 = new VBox(pane24, diceTxt);
        pane25.setSpacing(35);
        pane25.setAlignment(Pos.CENTER);
        
        //Setting up the layout.
        VBox pane26 = new VBox(pane25, pane28);
        pane26.setAlignment(Pos.CENTER);
        pane26.setSpacing(125);
        
       // VBox pane27 = new VBox(pane24, pane26,pane28);
        //pane27.setAlignment(Pos.CENTER);
        //pane27.setSpacing(25);
        BorderPane dicePane = new BorderPane (pane26, withSaving, null, null, null);
        dicePane.setBackground(new Background(new BackgroundImage(
                img0,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));
        
        //Creating scene for sub-game 2.
        scene2.setRoot(dicePane);
        stage.setScene(scene2);
        diceRoll.setOnAction((ActionEvent event) -> {
            dice.roll();
            dice.setData();
            diceTxt.setText(dice.outcomeString());
            diceTxt1.setText("Your currency is:" + diceCur_info.getText());

            diceTxt2.setText("Your bet is: " + diceInfo.getText());

            diceTxt3.setText("The result is: " + dice.faceToString());
            diceInfo.textProperty().unbind();
            diceCur_info.textProperty().unbind();
            diceInfo.setText("" +0);
            dice.setBetAmount(0);
            
            if (goToNextGame(195,15,dice.getNoOfGames())){
                StackPane diceToFive = new StackPane(switchToFive);
                diceToFive.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                
                trans2.setRoot(diceToFive);
                stage.setScene(trans2);
            }
            else if (game >4){
                Label goodBye = new Label ("YOU HAVE REACHED THE END OF THE GAME!");
                goodBye.setFont(new Font ("Arial", 25));
                goodBye.setTextFill(Color.DARKBLUE);
                Label finalCurrency = new Label ("Starting at 100, your final currency amount is "
                                                + fivedice.currency.getValue());
                finalCurrency.setFont(new Font ("Arial", 20));
                finalCurrency.setTextFill(Color.DARKBLUE);
                
                StackPane finish = new StackPane(goodBye);
                VBox end = new VBox(finalCurrency, switchToData);
                end.setSpacing(25);
                end.setAlignment(Pos.CENTER);
                BorderPane endPlay = new BorderPane (end, finish, null, null, null);
                endPlay.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                trans4.setRoot(endPlay);
                stage.setScene(trans4);
                
            }
        });

    }
    
    public void fiveDiceGame(){
        Button fiveIncrement = new Button("Increment +5.");
        
        Button fiveRoll      = new Button("Roll All");
        
        Button fiveRoll1     = new Button();
        fiveRoll1.setBackground((new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY))));
        
        Button fiveRoll2     = new Button();
        fiveRoll2.setBackground((new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY))));
        
        Button fiveRoll3     = new Button();
        fiveRoll3.setBackground((new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY))));    
        
        Button fiveRoll4     = new Button();
        fiveRoll4.setBackground((new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY))));
        
        Button fiveRoll5     = new Button();
        fiveRoll5.setBackground((new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY))));
        
        Label fiveGreetings  = new Label ("Roll once and you can choose dices to roll separately, increment the bet!\n" +
                                          "The result differs based on combinations");
        fiveGreetings.setTextFill(Color.WHITE);
        
        Label fiveCur_info = new Label("");
        Label fiveInfo     = new Label("");
        fiveInfo.setTextFill(Color.GOLD);
        
        Label fiveTxt      = new Label("");
        fiveTxt.setTextFill(Color.GOLD);
        fiveTxt.setFont(new Font("Arial", 20));
        Label fiveTxt1     = new Label("Your currency is:"+ dice.getCurrency());
        fiveTxt1.setTextFill(Color.WHITE);
        Label fiveTxt2     = new Label("Your bet is: " );
        fiveTxt2.setTextFill(Color.WHITE);
        Label fiveTxt3     = new Label("The result is: ");
        fiveTxt3.setTextFill(Color.WHITE);
        
        /****************************
         * IMAGES FOR FIVE DICE GAMES
         *****************************/
        
        Image img13 = new Image(getClass().getResourceAsStream("/Images/casino-dice-1.png"));
        ImageView viewFive1 = new ImageView(img13);
        viewFive1.setFitHeight(50);
        viewFive1.setPreserveRatio(true);
        fiveRoll1.setGraphic(viewFive1);
        
        Image img14 = new Image(getClass().getResourceAsStream("/Images/casino-dice-2.png"));
        ImageView viewFive2 = new ImageView(img14);
        viewFive2.setFitHeight(50);
        viewFive2.setPreserveRatio(true);
        fiveRoll2.setGraphic(viewFive2);
        
         Image img15 = new Image(getClass().getResourceAsStream("/Images/casino-dice-3.png"));
        ImageView viewFive3 = new ImageView(img15);
        viewFive3.setFitHeight(50);
        viewFive3.setPreserveRatio(true);
        fiveRoll3.setGraphic(viewFive3);
        
        Image img16 = new Image(getClass().getResourceAsStream("/Images/casino-dice-4.png"));
        ImageView viewFive4 = new ImageView(img16);
        viewFive4.setFitHeight(50);
        viewFive4.setPreserveRatio(true);
        fiveRoll4.setGraphic(viewFive4);
        
        Image img17 = new Image(getClass().getResourceAsStream("/Images/casino-dice-5.png"));
        ImageView viewFive5 = new ImageView(img17);
        viewFive5.setFitHeight(50);
        viewFive5.setPreserveRatio(true);
        fiveRoll5.setGraphic(viewFive5);
        
        Label roll1Txt = new Label ("Roll dice 1");
        roll1Txt.setTextFill(Color.WHITE);
        VBox roll1Layout = new VBox (roll1Txt, fiveRoll1);
        
        Label roll2Txt = new Label ("Roll dice 2");
        roll2Txt.setTextFill(Color.WHITE);
        VBox roll2Layout = new VBox (roll2Txt, fiveRoll2);
        
        Label roll3Txt = new Label ("Roll dice 3");
        roll3Txt.setTextFill(Color.WHITE);
        VBox roll3Layout = new VBox (roll3Txt, fiveRoll3);
        
        Label roll4Txt = new Label ("Roll dice 4");
        roll4Txt.setTextFill(Color.WHITE);
        VBox roll4Layout = new VBox (roll4Txt, fiveRoll4);
        
        Label roll5Txt = new Label ("Roll dice 5");
        roll5Txt.setTextFill(Color.WHITE);
        VBox roll5Layout = new VBox (roll5Txt, fiveRoll5);
        
        HBox pane30 = new HBox(roll1Layout, roll2Layout, roll3Layout, roll4Layout, roll5Layout);
        pane30.setSpacing(20);
        pane30.setAlignment(Pos.CENTER);


        HBox pane31 = new HBox (fiveIncrement, fiveInfo);
        pane31.setSpacing(8);
        pane31.setAlignment(Pos.CENTER);
        fiveInfo.textProperty().bind(Bindings.concat( fivedice.getBetAmountProperty() ));

        VBox pane32 = new VBox(pane30, pane31, fiveRoll);
        pane32.setSpacing(25);
        pane32.setAlignment(Pos.CENTER);

        HBox pane33 = new HBox(fiveTxt1, fiveTxt2, fiveTxt3);
        pane33.setSpacing(100);
        pane33.setAlignment(Pos.CENTER);

        HBox pane34 = new HBox(fiveTxt);
        pane34.setSpacing(10);
        pane34.setAlignment(Pos.CENTER);

        VBox pane35 = new VBox( pane33, pane34, pane32);
        pane35.setSpacing(25);
        pane35.setAlignment(Pos.CENTER);
        
        StackPane pane36 = new StackPane(fiveGreetings);
        VBox withSaving = new VBox (pane36, saving);
        withSaving.setAlignment(Pos.CENTER);
        StackPane.setMargin(fiveGreetings, new Insets(10));
        BorderPane fivePane = new BorderPane (pane35, withSaving, null, null, null);
        fivePane.setBackground(new Background(new BackgroundImage(
                img0,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));
         //Handlers.
         
         scene4.setRoot(fivePane);
        stage.setScene(scene4);
        //Handler increment button.
        
        fiveIncrement.setOnAction((ActionEvent event) -> {
            fivedice.setBetAmount(fivedice.getBetAmount() + 5);
            fivedice.setCurrency(fivedice.getCurrency() -5);
            fiveCur_info.textProperty().bind(Bindings.concat(fivedice.getCurrencyProperty()));
            fiveTxt1.setText("Your currency is:" + fiveCur_info.getText());
            fiveInfo.textProperty().bind(Bindings.concat(fivedice.getBetAmountProperty()));
        });

        fiveRoll1.setOnAction((ActionEvent event) -> {
            if ( fivedice.getTries() < 3) {
                Dice dice1 = new Dice();
                dice1.roll();
                fivedice.setResultedFaces(dice1,0);
                fivedice.setData();
                fiveTxt.setText(fivedice.outcomeString());
                fiveTxt1.setText("Your currency is:" + fiveCur_info.getText());
                fiveTxt2.setText("Your bet is: " + fiveInfo.getText());
                fiveTxt3.setText("The result is: " + fivedice.faceToString());
                fiveInfo.textProperty().unbind();
                fiveCur_info.textProperty().unbind();
                fiveInfo.setText("" +0);
                fivedice.setBetAmount(0);
                fivedice.setTries( fivedice.getTries() + 1);
            }
            else {
                fivedice.showAlert();
            }
             
        });
        
        fiveRoll2.setOnAction((ActionEvent event) -> {
            if ( fivedice.getTries() < 3) {
                Dice dice2 = new Dice();
                dice2.roll();
                fivedice.setResultedFaces(dice2,1 );
                fivedice.setData();
                fiveTxt.setText(fivedice.outcomeString());
                fiveTxt1.setText("Your currency is:" + fiveCur_info.getText());
                fiveTxt2.setText("Your bet is: " + fiveInfo.getText());
                fiveTxt3.setText("The result is: " + fivedice.faceToString());
                fiveInfo.textProperty().unbind();
                fiveCur_info.textProperty().unbind();
                fiveInfo.setText("" +0);
                fivedice.setBetAmount(0);
                fivedice.setTries( fivedice.getTries() + 1);
            }
            else {
                fivedice.showAlert();
            }   
        });
        
        fiveRoll3.setOnAction((ActionEvent event) -> {
            if ( fivedice.getTries() < 3) {
                Dice dice3 = new Dice();
                dice3.roll();
                fivedice.setResultedFaces(dice3,2 );
                fivedice.setData();
                fiveTxt.setText(fivedice.outcomeString());
                fiveTxt1.setText("Your currency is:" + fiveCur_info.getText());
                fiveTxt2.setText("Your bet is: " + fiveInfo.getText());
                fiveTxt3.setText("The result is: " + fivedice.faceToString());
                fiveInfo.textProperty().unbind();
                fiveCur_info.textProperty().unbind();
                fiveInfo.setText("" +0);
                fivedice.setBetAmount(0);
                fivedice.setTries( fivedice.getTries() + 1);
            }
            else {
                fivedice.showAlert();
            }
        });
        
        fiveRoll4.setOnAction((ActionEvent event) -> {
            if ( fivedice.getTries() < 3) {
                Dice dice4 = new Dice();
                dice4.roll();
                fivedice.setResultedFaces(dice4, 3);
                fivedice.setData();
                fiveTxt.setText(fivedice.outcomeString());
                fiveTxt1.setText("Your currency is:" + fiveCur_info.getText());
                fiveTxt2.setText("Your bet is: " + fiveInfo.getText());
                fiveTxt3.setText("The result is: " + fivedice.faceToString());
                fiveInfo.textProperty().unbind();
                fiveCur_info.textProperty().unbind();
                fiveInfo.setText("" +0);
                fivedice.setBetAmount(0);
                fivedice.setTries( fivedice.getTries() + 1);
            }
            else {
                fivedice.showAlert();
            }
        });
        
        fiveRoll5.setOnAction((ActionEvent event) -> {
            if ( fivedice.getTries() < 3) {
                Dice dice5 = new Dice();
                dice5.roll();
                fivedice.setResultedFaces(dice5, 4);
                fivedice.setData();
                fiveTxt.setText(fivedice.outcomeString());
                fiveTxt1.setText("Your currency is:" + fiveCur_info.getText());
                fiveTxt2.setText("Your bet is: " + fiveInfo.getText());
                fiveTxt3.setText("The result is: " + fivedice.faceToString());
                fiveInfo.textProperty().unbind();
                fiveCur_info.textProperty().unbind();
                fiveInfo.setText("" +0);
                fivedice.setBetAmount(0);
                fivedice.setTries( fivedice.getTries() + 1);
            }
            else {
                fivedice.showAlert();
            }
        });
       
        fiveRoll.setOnAction((ActionEvent event) -> {
            fivedice.setTries(0);
            fivedice.roll();
            fivedice.setData();
            
            fiveTxt.setText(fivedice.outcomeString());

            fiveTxt1.setText("Your currency is:" + fiveCur_info.getText());

            fiveTxt2.setText("Your bet is: " + fiveInfo.getText());
            fiveTxt3.setText("The result is: " + fivedice.faceToString());

            fiveInfo.textProperty().unbind();
            fiveCur_info.textProperty().unbind();
            fiveInfo.setText("" +0);
            fivedice.setBetAmount(0);
            
            if (goToNextGame(235,15,fivedice.getNoOfGames())){
                Label goodBye = new Label ("THE END OF GAME!");
                goodBye.setFont(new Font ("Arial", 25));
                goodBye.setTextFill(Color.DARKBLUE);
                Label finalCurrency = new Label ("Starting at 100, your final currency amount is "
                                                + fivedice.currency.getValue());
                finalCurrency.setFont(new Font ("Arial", 20));
                finalCurrency.setTextFill(Color.DARKBLUE);
                
                StackPane finish = new StackPane(goodBye);
                VBox end = new VBox(finalCurrency, switchToData);
                end.setSpacing(25);
                end.setAlignment(Pos.CENTER);
                BorderPane endPlay = new BorderPane (end, finish, null, null, null);
                endPlay.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                trans4.setRoot(endPlay);
                stage.setScene(trans4);
            }   else if (game >4){
                Label goodBye = new Label ("YOU HAVE REACHED THE END OF THE GAME!");
                goodBye.setFont(new Font ("Arial", 25));
                goodBye.setTextFill(Color.DARKBLUE);
                Label finalCurrency = new Label ("Starting at 100, your final currency amount is "
                                                + fivedice.currency.getValue());
                finalCurrency.setFont(new Font ("Arial", 20));
                finalCurrency.setTextFill(Color.DARKBLUE);
                
                StackPane finish = new StackPane(goodBye);
                VBox end = new VBox(finalCurrency, switchToData);
                end.setSpacing(25);
                end.setAlignment(Pos.CENTER);
                BorderPane endPlay = new BorderPane (end, finish, null, null, null);
                endPlay.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                trans4.setRoot(endPlay);
                stage.setScene(trans4);
                
            }
            
        }); 
 
    }
    
    public void displayStats(){
                Button backButton = new Button ("BACK TO STAT PAGE");
        Button endButton = new Button ("ESC");
        stage.setScene(scene5);
        /***********************
         * IMAGES FOR STATISTICS
         ************************/
        Image img20 = new Image(getClass().getResourceAsStream("/Images/StatBackground.png"));
        ImageView viewbackgroundStat = new ImageView(img20);
        viewbackgroundStat.setPreserveRatio(true);
        
        Image img21 = new Image(getClass().getResourceAsStream("/Images/coinSpinning.png"));
        ImageView viewbackgroundCoin = new ImageView(img21);
        viewbackgroundCoin.setPreserveRatio(true);
        
        Image img22 = new Image(getClass().getResourceAsStream("/Images/singleDice.png"));
        ImageView viewbackgroundDice = new ImageView(img22);
        viewbackgroundDice.setPreserveRatio(true);
        
        Image img23 = new Image(getClass().getResourceAsStream("/Images/suit.png"));
        ImageView viewbackgroundSuit = new ImageView(img23);
        viewbackgroundSuit.setPreserveRatio(true);
        
        Image img24 = new Image(getClass().getResourceAsStream("/Images/five.png"));
        ImageView viewbackgroundFive = new ImageView(img24);
        viewbackgroundFive.setPreserveRatio(true);
        
        HBox layout = new HBox (backButton);
        layout.setSpacing(50);
        layout.setAlignment(Pos.CENTER);
        
        HBox.setMargin(backButton, new Insets(10) );
        
        StackPane endOfGames = new StackPane (endButton);
        StackPane.setMargin(endButton, new Insets (10));
        
        Button statCoin = new Button ("COIN STATISTICS");
        Button statDice = new Button ("DICE STATISTICS");
        Button statSuit = new Button ("SUITBLOCK STATISTICS");
        Button statFive = new Button ("FIVE DICE STATISTICS");
        Button test = new Button("testing");
        
        VBox statChoice = new VBox (statCoin, statDice, statSuit, statFive,saving);
        statChoice.setSpacing(35);
        statChoice.setAlignment(Pos.CENTER);
        
        Label statGreetings = new Label("WELCOME TO THE STATS!" + "\n" 
                             + "You can now select a game you played and see results and statistics." +"\n"
                             + "As you will see, the outcome result is always biased on your choice!");
        
        StackPane statPane = new StackPane(statGreetings);
        StackPane.setMargin(statGreetings, new Insets(10));
        BorderPane stat = new BorderPane (statChoice, statPane, null, endOfGames, null);
        stat.setBackground(new Background(new BackgroundImage(
                img20,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));
        
        
        scene5.setRoot(stat);
        
        //Handlers for statisticsb buttons.
        test.setOnAction((actionEvent)-> {
            game = 4;
           coinGame(); 
           // and reducing the games played back to 0; 
        });
        statCoin.setOnAction (( ActionEvent event) -> {
            Label coinDescription1 = new Label (coin.displayStat());
            Label coinDescription2 = new Label (coin.displayAlgorithm());
            
            
            VBox coinDescription = new VBox (coinDescription1, coinDescription2);
            coinDescription.setSpacing(15);
            coinDescription.setAlignment(Pos.CENTER);
            BorderPane statCoinDisplay = new BorderPane(coinDescription, null, null, layout, null);
            statCoinDisplay.setBackground(new Background(new BackgroundImage(
                img21,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));
            
            
            Scene scene6 = new Scene(statCoinDisplay,640,480);
            
            stage.setScene(scene6);
            
        });

        statDice.setOnAction (( ActionEvent event) -> {
            Label diceDescription1 = new Label (dice.displayStat());
            Label diceDescription2 = new Label (dice.displayAlgorithm());
            
            
            VBox diceDescription = new VBox (diceDescription1, diceDescription2);
            diceDescription.setSpacing(15);
            diceDescription.setAlignment(Pos.CENTER);
            BorderPane statDiceDisplay = new BorderPane(diceDescription, null, null, layout, null);
            statDiceDisplay.setBackground(new Background(new BackgroundImage(
                img22,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));
            
            
            Scene scene6 = new Scene(statDiceDisplay,640,480);
            
            stage.setScene(scene6);
            
        });
        
        statSuit.setOnAction (( ActionEvent event) -> {
            Label suitDescription1 = new Label (suitblock.displayStat());
            Label suitDescription2 = new Label (suitblock.displayAlgorithm());
            
            
            VBox suitDescription = new VBox (suitDescription1, suitDescription2);
            suitDescription.setSpacing(15);
            suitDescription.setAlignment(Pos.CENTER);
            BorderPane statSuitDisplay = new BorderPane(suitDescription, null, null, layout, null);
            statSuitDisplay.setBackground(new Background(new BackgroundImage(
                img23,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));
            
            
            Scene scene6 = new Scene(statSuitDisplay,640,480);
            
            stage.setScene(scene6);
            
        });
        
        statFive.setOnAction (( ActionEvent event) -> {
            Label fiveDescription1 = new Label (fivedice.displayStat());
            Label fiveDescription2 = new Label (fivedice.displayAlgorithm());
            
            
            VBox fiveDescription = new VBox (fiveDescription1, fiveDescription2);
            fiveDescription.setSpacing(15);
            fiveDescription.setAlignment(Pos.CENTER);
            BorderPane statFiveDisplay = new BorderPane(fiveDescription, null, null, layout, null);
            statFiveDisplay.setBackground(new Background(new BackgroundImage(
                img24,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));
            
            
            Scene scene6 = new Scene(statFiveDisplay,640,480);
            
            stage.setScene(scene6);
            
        });
        
        //Handlers for button.
        endButton.setOnAction (( ActionEvent event) -> {
            stage.close();
        });
        
        backButton.setOnAction (( ActionEvent event) -> {
            stage.setScene(scene5);
        });
        
    }
           
    public void buttonHandlers(){
        switchToDice.setOnAction((ActionEvent event) -> {
            dice.setCurrency(suitblock.currency.getValue());
            //diceTxt1.setText("Your currency is:" + dice.currency.getValue());       
            diceGame();
            game++;
        });
        
        //From Dice game to Suitblock Game.
        switchToSuit.setOnAction((ActionEvent event) -> {
            suitblock.setCurrency(coin.currency.getValue());
            //blockTxt1.setText("Your currency is:" + suitblock.currency.getValue());
            suitBlockGame();
            game++;

        });
        
        //From Suitblock game to Five Dice Game.
        switchToFive.setOnAction((ActionEvent event) -> {
            fivedice.setCurrency(dice.currency.getValue());
          //  fiveTxt1.setText("Your currency is:" + fivedice.currency.getValue());
            fiveDiceGame();
            game++;

        });
        
        switchToData.setOnAction((ActionEvent event) -> {
            displayStats();
            game++;

        });
        
       saving.setOnAction((ActionEvent event) -> {          
           savingAndLoading saves = new savingAndLoading(stage,coin,dice,suitblock,fivedice,this);
           saves.FX(); 
        });
        switchToDice.setPadding(new Insets(20) );
        switchToSuit.setPadding(new Insets(20) );
        switchToFive.setPadding(new Insets(20) );
        switchToData.setPadding(new Insets(20) );
        
        }    
    }

    
    

