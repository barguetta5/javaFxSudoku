package sample;

//import com.sun.org.apache.bcel.internal.generic.PUSH;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class sudokuController {

    @FXML
    private GridPane gridPan;
    private TextField[][] txt;
    private final int SIZE = 9;
    private int count = 0;

    public void initialize() {
        txt = new TextField[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                txt[i][j] = new TextField();
                txt[i][j].setPrefSize(gridPan.getPrefWidth() / SIZE,
                        gridPan.getPrefWidth() / SIZE);
                txt[i][j].setText("");
//                txt[i][j].setStyle(" -fx-border-color: black;");
                gridPan.add(txt[i][j], count % SIZE, count / SIZE);
                txt[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        if (checkNumber()) {
                            alert.setTitle("WRONG NUMBER");
                            alert.setContentText("Please right number between 0-9");
                        }
                        if (!checkRow(event)) {
                            JOptionPane.showMessageDialog(null, "The number illegal in the row");
                            //clearCell(event);
                        }
                        else if (!checkColumn(event)) {
                            JOptionPane.showMessageDialog(null, "The number illegal in the column");
                            //clearCell(event);
                        }
                        else if (!checkGrid(event)) {
                            JOptionPane.showMessageDialog(null, "The number illegal in the grid");
                            //clearCell(event);
                        }
                    }
                });
                count++;
            }

        }
        dyeGridPan();
    }
    public void clearCell(ActionEvent event)
    {
        for (int i = 0 ; i <SIZE;i++)
        {
            for (int j = 0;j<SIZE;j++)
            {
                if (txt[i][j] == event.getSource())
                {
                    txt[i][j].clear();
                }
            }
        }
    }
    void dyeGridPan()
    {
        for (int i = 0;i<9;i++)
        {
            for (int j = 0;j<9;j++)
            {
                if (i<3||i>5) {
                    if (j < 3 || j > 5){
                        txt[i][j].setStyle("-fx-background-color: lightgray; -fx-text-fill: black;-fx-border-color: black;");}
                    else
                        txt[i][j].setStyle("-fx-border-color: black;");}

                else if (i>2&&i<6){
                    if (j>2&&j<6){
                        txt[i][j].setStyle("-fx-background-color: lightgray; -fx-text-fill: black;-fx-border-color: black;");}
                    else
                        txt[i][j].setStyle("-fx-border-color: black;");}
        }
        }
    }
    boolean checkNumber() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (!txt[row][col].getText().equals("")) {
                    int num1;
                        try {
                             num1 = Integer.parseInt(txt[row][col].getText());
                        }
                        catch (NumberFormatException ex) {
                            txt[row][col].clear();
                            return true;
                        }
                         if (num1<1&&num1>9) {
                            txt[row][col].clear();
                            return true; }
                }
            }
        }
        return false;
    }
    boolean checkRow(ActionEvent event)
    {
        for(int row = 0; row < 9; row++)
            for(int col = 0; col < 8; col++)
                for(int col2 = col + 1; col2 < 9; col2++)
                    if (txt[row][col].getText().equals(txt[row][col2].getText())&&
                            txt[row][col].getText().length()>0) {
                            if (txt[row][col] == event.getSource()&&!event.getEventType().equals(new Button()))
                        {
                            txt[row][col].setText("");
                        }
                        else
                            txt[row][col2].setText("");
                        //txt[row][col2].clear();
                        return false;
                    }
       return true;
    }
    boolean checkColumn(ActionEvent event)
    {
        for(int col = 0; col < 9; col++)
            for(int row = 0; row < 8; row++)
                for(int row2 = row + 1; row2 < 9; row2++)
                if (txt[row][col].getText().equals(txt[row2][col].getText())&&
                        txt[row][col].getText().length()>0) {
                    if (txt[row][col] == event.getSource()&&!event.getEventType().equals(new Button()))
                    {
                        txt[row][col].setText("");
                    }
                    else
                        txt[row2][col].setText("");
                    //txt[row2][col].clear();
                    return false;
                }
        return true;
    }
    void coloress()
    {

    }
    boolean checkGrid(ActionEvent event)
    {
        for(int row = 0; row < 9; row += 3)
            for(int col = 0; col < 9; col += 3)
                // row, col is start of the 3 by 3 grid
                for(int pos = 0; pos < 8; pos++)
                    for(int pos2 = pos + 1; pos2 < 9; pos2++)
                        if (txt[row + pos%3][col + pos/3].getText().equals(txt[row + pos2%3][col + pos2/3].getText()) &&
                                txt[row + pos%3][col + pos/3].getText().length()>0){
                            if (txt[row + pos%3][col + pos/3] == event.getSource()/*&&!event.getEventType().equals(new Button())*/)
                            {
                                txt[row + pos%3][col + pos/3].setText("");
                            }
                            else if (txt[row + pos2%3][col + pos2/3] == event.getSource()/*&&!event.getEventType().equals(new Button())*/)
                            {
                                txt[row + pos2%3][col + pos2/3].setText("");
                            }
                            else{
                                txt[row + pos2 % 3][col + pos2 / 3].setText("");
                                System.out.println("get in");
                            }
                            return false;
                        }
        return true;
    }

    @FXML
    void clearPress(ActionEvent event) {
        for (int i = 0;i<SIZE;i++)
            for (int j=0;j<SIZE;j++)
                txt[i][j].setText("");

    }
    @FXML
    void setPress(ActionEvent event) {
        clearPress(event);
        dyeGridPan();
        Random rn = new Random();
        int randRow;
        int randCol;
        int randNum;
        String s;
        for (int i =0;i < 15;i++)
        {
            randRow = rn.nextInt(9);
            randCol = rn.nextInt(9);
            s=String.valueOf(rn.nextInt(10));
            txt[randRow][randCol].setText(s);
            txt[randRow][randCol].setStyle("-fx-border-color: red ;");
            checkColumn(event);
            checkGrid(event);
            checkRow(event);
        }

    }

/*
* 1)need to finish the set button
* 2)need to find how event give me the current row and column
* 3)set numbers in the board in the red color
* */
}
