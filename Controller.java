package tictactoe.panda.org;

import animatefx.animation.ZoomIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {

    @FXML
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    @FXML
    private Pane gamePane, winnerPane;
    @FXML
    private Text winnerText;

    int playerTurn = 0;

    ArrayList<Button> buttons;


    public void initialize() {
        buttons = new ArrayList<>(Arrays.asList(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9));

        buttons.forEach(button -> {
            setupButton(button);
            button.setFocusTraversable(false);
        });
    }

    public void resetButton(Button button) {
        button.setDisable(false);
        button.setText("");
    }

    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            checkIfGameIsOver();
        });
    }

    public void setPlayerSymbol(Button button) {
        if(playerTurn % 2 == 0) {
            button.setText("X");
            button.setTextFill(Color.BLUE);
            playerTurn = 1;
        } else {
            button.setText("O");
            button.setTextFill(Color.ORANGE);
            playerTurn = 0;
        }
    }

    public void checkIfGameIsOver() {
        for(int a = 0; a < 8; a++) {
            String line = switch(a) {
                case(0) -> btn1.getText() + btn2.getText() + btn3.getText();
                case(1) -> btn4.getText() + btn5.getText() + btn6.getText();
                case(2) -> btn7.getText() + btn8.getText() + btn9.getText();
                case(3) -> btn1.getText() + btn5.getText() + btn9.getText();
                case(4) -> btn3.getText() + btn5.getText() + btn7.getText();
                case(5) -> btn1.getText() + btn4.getText() + btn7.getText();
                case(6) -> btn2.getText() + btn5.getText() + btn8.getText();
                case(7) -> btn3.getText() + btn6.getText() + btn9.getText();
                default -> null;
            };

            // X Winner
            if(line.equals("XXX")) {
                winnerPane.toFront();
                new ZoomIn(winnerPane).setSpeed(10).play();
                winnerText.setText("X Won!");
            }

            // O Winner
            else if(line.equals("OOO")) {
                winnerPane.toFront();
                new ZoomIn(winnerPane).setSpeed(10).play();
                winnerText.setText("O Won!");
            }
        }
    }

    @FXML
    void pvpButtonClicked(ActionEvent event) {
        gamePane.toFront();
        new ZoomIn(gamePane).setSpeed(10).play();
    }

    @FXML
    void exitButtonClicked(ActionEvent event) {
        boolean result = AlertBox.yesPopup("TicTacToe", "Are you sure you want to exit?", "Yes", "Cancel");
        if(result) { System.exit(0); }
    }

    @FXML
    void yesButtonClicked(ActionEvent event) {
        gamePane.toFront();
        new ZoomIn(gamePane).setSpeed(10).play();
        buttons.forEach(this::resetButton);
    }
}
