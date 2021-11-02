/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinogame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author colin
 */
public class savingAndLoading {
    private boolean saveOrLoad;
    private Stage stage;
    private File save1;
    private File save2;
    private File save3;
    private File saveEmpty;
    private Coin coin ;
    private Dice dice ;
    private SuitBlock suitblock;
    private FiveDice fivedice;
    private gameJavaFx cur;
    
    
    public savingAndLoading(Stage stage, Coin coin, Dice dice,SuitBlock suitblock, FiveDice fivedice, gameJavaFx cur){
        this.saveOrLoad = true;
        this.stage = stage;
        this.save1 = new File("file1.txt");
        this.save2 = new File("file2.txt");
        this.save3 = new File("file3.txt");
        this.saveEmpty = new File("fileEmpty");
        this.coin = coin;
        this.dice = dice;
        this.suitblock = suitblock;
        this.fivedice = fivedice;
        this.cur = cur;
    }
    
    public void FX(){

        RadioButton saving = new RadioButton("saving");
        RadioButton loading = new RadioButton("loading");
        HBox savingButtons = new HBox(saving,loading);
        savingButtons.setAlignment(Pos.CENTER);
        
        ToggleGroup group = new ToggleGroup();
        saving.setToggleGroup(group);
        loading.setToggleGroup(group);
        
        Button file1 = new Button("save1");
        Button file2 = new Button("save2");
        Button file3 = new Button("save3");
        VBox buttons = new VBox(file1,file2,file3);
        buttons.setAlignment(Pos.CENTER);
        
        VBox layout = new VBox(savingButtons, buttons);
        handlers(saving,loading,file1,file2,file3); 
                Scene secondScene = new Scene(layout, 230, 100);
                
                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("Saving and Loading");
                newWindow.setScene(secondScene);
 
                // Set position of second window, related to primary window.
                newWindow.setX(stage.getX() + 200);
                newWindow.setY(stage.getY() + 100);
                newWindow.show();
    }
    public void handlers(RadioButton saving , RadioButton loading, Button file1,Button file2, Button file3){
        saving.setOnAction((ActionEvent)-> {
            saveOrLoad = true;
        });
        loading.setOnAction((ActionEvent)-> {
            saveOrLoad = false;
        });
        file1.setOnAction((ActionEvent) -> {
            if(saveOrLoad){
              saving(1);  
            }else load(1);
        });
        file2.setOnAction((ActionEvent) -> {
            if(saveOrLoad){
              saving(2);  
            }else load(2);            
        });
        file3.setOnAction((ActionEvent) -> {
            if(saveOrLoad){
              saving(3);  
            }else load(3);            
        });
    }

    
    public void loadhelp2(){
        
    }
    public void load(int file){
        try {
            Scanner myReader = new Scanner(fileNumber(file));
            int reading = 0;
            int currentGame = 0;
            while (myReader.hasNextInt()) {
                switch (reading){
                    case 0 : suitblock.setLoss(myReader.nextInt()); 
                    case 1 : fivedice.setLoss(myReader.nextInt());
                    case 2 : fivedice.setTries(myReader.nextInt());
                    case 3 : dice.setLoss(myReader.nextInt());
                    case 4 : coin.setBetAmount(myReader.nextInt());
                    case 5 : coin.setCurrency(myReader.nextInt());
                    case 6 : coin.setTotAmountBet(myReader.nextInt());
                    case 7 : coin.setTotWinAmount(myReader.nextInt());
                    case 8 : coin.setNoGames(myReader.nextInt());
                    case 9 : coin.setNoWins(myReader.nextInt());
                    case 10 : dice.setBetAmount(myReader.nextInt());
                    case 11 : dice.setCurrency(myReader.nextInt());
                    case 12 : dice.setTotAmountBet(myReader.nextInt());
                    case 13 : dice.setTotWinAmount(myReader.nextInt());
                    case 14 : dice.setNoGames(myReader.nextInt());
                    case 15 : dice.setNoWins(myReader.nextInt());
                    case 16 : fivedice.setBetAmount(myReader.nextInt());
                    case 17 : fivedice.setCurrency(myReader.nextInt());
                    case 18 : fivedice.setTotAmountBet(myReader.nextInt());
                    case 19 : fivedice.setTotWinAmount(myReader.nextInt());
                    case 20 : fivedice.setNoGames(myReader.nextInt());
                    case 21 : fivedice.setNoWins(myReader.nextInt());
                    case 22 : suitblock.setBetAmount(myReader.nextInt());
                    case 23 : suitblock.setCurrency(myReader.nextInt());
                    case 24 : suitblock.setTotAmountBet(myReader.nextInt());
                    case 25 : suitblock.setTotWinAmount(myReader.nextInt());
                    case 26 : suitblock.setNoGames(myReader.nextInt());
                    case 27 : suitblock.setNoWins(myReader.nextInt());
                    case 28 : currentGame = myReader.nextInt();
                    default : break;
                }
                reading++;
                
            }
            cur.callCurGame(currentGame);
            myReader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(savingAndLoading.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("null")
    public void saving(int file){
  
            FileWriter mywriter = null;
        try {
            mywriter = new FileWriter(fileNumber(file));
            mywriter.write(suitblock.stringLoss());
            mywriter.write(fivedice.stringLoss());
            mywriter.write(dice.stringLoss());
            mywriter.write(coin.toString());
            mywriter.write(dice.toString());
            mywriter.write(fivedice.toString());
            mywriter.write(suitblock.toString());
            mywriter.write(cur.getcurGame());
            mywriter.close();
        } catch (IOException ex) {
            Logger.getLogger(savingAndLoading.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                mywriter.close();
            } catch (IOException ex) {
                Logger.getLogger(savingAndLoading.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    public File fileNumber(int file){
        switch(file){
            case 1 : return save1;
            case 2 : return save2;
            case 3 : return save3;
            default: return saveEmpty;
        }
    }
}
