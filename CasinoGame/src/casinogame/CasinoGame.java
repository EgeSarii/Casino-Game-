/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinogame;

import javafx.application.Application;
import javafx.stage.*;




/**
 *
 * @author matteo
 */
public class CasinoGame extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch (args);
    }

    @Override
    public void start(Stage stage) { 
        gameJavaFx game = new gameJavaFx(stage);
        game.buttonHandlers();
        game.coinGame(); 
        //game.displayStats();
        stage.show();     
    }
    
}
