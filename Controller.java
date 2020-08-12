package tictactoepackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Random;

import static java.lang.Math.abs;

public class Controller {

    @FXML
    private Rectangle gameField;

    @FXML
    private Text field00;

    @FXML
    private Text field01;

    @FXML
    private Text field02;

    @FXML
    private Text field10;

    @FXML
    private Text field11;

    @FXML
    private Text field12;

    @FXML
    private Text field20;

    @FXML
    private Text field21;

    @FXML
    private Text field22;

    @FXML
    private Text aboutAuthor;

    @FXML
    private TextField playerSign;

    @FXML
    private TextField cpuSign;

    @FXML
    private Button playButton;

    private int bestX;
    private int bestY;
    private int bestScore;
    private int score;
    private Text[][] field;
    private int x, y;
    String player = "o";
    String opponent = "x";
    static Random rand = new Random();

    /**
     * function created to display author's watermark
     *
     * @param event
     */
    @FXML
    void printWatermark(ActionEvent event) {
        aboutAuthor.setText("Copyright by Tomasz Kulnianin");
    }

    /**
     * handles initializing game field and picks randomly first CPU move
     */

    @FXML
    void initializeFields() {
        if (playerSign.getText() != null)
            player = playerSign.getText().substring(0, 1);
        if (cpuSign.getText() != null)
            opponent = cpuSign.getText().substring(0, 1);

        field = new Text[3][3];
        field[0][0] = field00;
        field[1][0] = field01;
        field[2][0] = field02;

        field[0][1] = field10;
        field[1][1] = field11;
        field[2][1] = field12;

        field[0][2] = field20;
        field[1][2] = field21;
        field[2][2] = field22;
        //picks random spot
        field[abs(rand.nextInt(3))][abs(rand.nextInt(3))].setText(opponent);

    }

    /**
     * in order to use only one event listener instead of 9 I created transparent rectangle and this function calcuales which field was clicked
     *
     * @param event
     */

    @FXML
    void clickOnRectangle(MouseEvent event) {
        x = ((int) event.getSceneX() - 54) / 100;
        y = ((int) event.getSceneY() - 58) / 100;

        if (x == 3)
            x--;
        if (y == 3)
            y--;

        if (field[x][y].getText().equals("") && block(player) == false && block(opponent) == false) {
            field[x][y].setText(player);
            winTheGame();
            System.out.println(lookForAWinner(field));
        }


    }

    /**
     * resets game field
     *
     * @param event
     */
    @FXML
    void resetGame(ActionEvent event) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                field[i][j].setText("");
        initializeFields();

    }

    @FXML
    private MenuItem reset;


    /**
     * function checks if there's a winner. If so it blocks game field and both CPU and human won't be able to pick a field
     *
     * @param playerName
     * @return boolean value if to block or not to block
     */
    boolean block(String playerName) {
        int sum = 0;
        if ((field[0][0].getText()).equals(playerName))
            if ((field[1][1].getText()).equals(playerName))
                if ((field[2][2].getText()).equals(playerName)) {
                    return true;
                }

        if ((field[0][2].getText()).equals(playerName))
            if ((field[1][1].getText()).equals(playerName))
                if ((field[2][0].getText()).equals(playerName))
                    return true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                if ((field[i][j].getText()).equals(playerName))
                    sum++;
            if (sum == 3)
                return true;
            else sum = 0;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                if ((field[j][i].getText()).equals(playerName))
                    sum++;
            if (sum == 3)
                return true;
            else sum = 0;
        }
        return false;
    }

    /**
     * fucntion that looks for a winner. Uses given convention cpu loss -1, still playing 0, 1 draw, 2 cpu win
     *
     * @param field game field
     * @return score of given result
     */

    int lookForAWinner(Text[][] field) //cpu loss -1, still playing 0, 1 draw, 2 cpu win
    {
        if ((field[0][0].getText()).equals(player))
            if ((field[1][1].getText()).equals(player))
                if ((field[2][2].getText()).equals(player))
                    return -1;


        if ((field[0][2].getText()).equals(player))
            if ((field[1][1].getText()).equals(player))
                if ((field[2][0].getText()).equals(player))
                    return -1;


        for (int i = 0; i < 3; i++)
            if ((field[i][2].getText()).equals(player))
                if ((field[i][1].getText()).equals(player))
                    if ((field[i][0].getText()).equals(player))
                        return -1;

        for (int i = 0; i < 3; i++)
            if ((field[0][i].getText()).equals(player))
                if ((field[1][i].getText()).equals(player))
                    if ((field[2][i].getText()).equals(player))
                        return -1;

        if ((field[0][0].getText()).equals(opponent))
            if ((field[1][1].getText()).equals(opponent))
                if ((field[2][2].getText()).equals(opponent))
                    return 2;


        if ((field[0][2].getText()).equals(opponent))
            if ((field[1][1].getText()).equals(opponent))
                if ((field[2][0].getText()).equals(opponent))
                    return 2;


        for (int i = 0; i < 3; i++)
            if ((field[i][2].getText()).equals(opponent))
                if ((field[i][1].getText()).equals(opponent))
                    if ((field[i][0].getText()).equals(opponent))
                        return 2;

        for (int i = 0; i < 3; i++)
            if ((field[0][i].getText()).equals(opponent))
                if ((field[1][i].getText()).equals(opponent))
                    if ((field[2][i].getText()).equals(opponent))
                        return 2;


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j].getText().equals(""))
                    return 0;
                else continue;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j].getText().equals(""))
                    continue;
                else return 1;
            }
        }
        return 0;
    }

    /**
     * funciton that handels looking for the best possible move
     */

    void winTheGame() {
        bestScore = -1000;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (field[i][j].getText().equals("")) {
                    field[i][j].setText(opponent);
                    score = miniMax(field, 0, false);
                    if (score > bestScore) {
                        bestScore = score;
                        bestX = i;
                        bestY = j;
                    }
                    field[i][j].setText("");
                }
        field[bestX][bestY].setText(opponent);
    }

    /**
     * currently not used, switches players. Necessary for second human player. Could be usable in the future
     */
    void switchPlayers() {
        String help = player;
        player = opponent;
        opponent = help;
    }

    /**
     * heart of the game, hadnels minimax algorithm
     *
     * @param field      game field
     * @param depth      depth of given tree
     * @param toMaximize is the current player maximizer?
     * @return result of given path
     */
    int miniMax(Text[][] field, int depth, boolean toMaximize) {
        int result = lookForAWinner(field);
        if (result != 0)
            return result;
        if (toMaximize == true) {
            int bestScore = -1000;
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (field[i][j].getText().equals("")) {
                        field[i][j].setText(opponent);
                        score = miniMax(field, depth + 1, false);
                        field[i][j].setText("");
                        if (score > bestScore)
                            bestScore = score;
                    }
            return bestScore;
        } else {
            int bestScore = 1000;
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (field[i][j].getText().equals("")) {
                        field[i][j].setText(player);
                        score = miniMax(field, depth + 1, true);
                        field[i][j].setText("");
                        if (score < bestScore)
                            bestScore = score;
                    }
            return bestScore;
        }
    }

}
