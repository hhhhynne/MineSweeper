package projekt;



import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class MineSweeperController {
    @FXML
    private GridPane grid;

    private gameBoard gameboard;

    private int count;

    private Files files = new Files();

    @FXML
    private Button newGame;

    @FXML
    private Text seier;

    @FXML
    private Text winners;

    @FXML
    private TextField navn;

    @FXML
    private Button enter;

    public MineSweeperController() {
        //gameboard.generateNewBoard();
        gameboard = new gameBoard();
        gameboard.fillBoard();
        gameboard.makeBombs();
        gameboard.getBombsNearby();
        gameboard.safeNeighbours();
        gameboard.showgameBoard();

    }
    @FXML
    private void initialize() { //her plasseres stackpanes med knapp og text i hver rute på griden. teksten gis av hva slags spot det er.
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                StackPane stackPane = new StackPane();
                stackPane.setStyle("-fx-background-color: white; -fx-border-color: black;");
                stackPane.setPrefSize(50, 50);
                grid.add(stackPane, col, row);
                Button button = new Button();
                button.setStyle("-fx-background-color: lightgrey; -fx-border-color: black;");
                button.setPrefSize(50, 50);
                button.setText("");
                Text text = new Text(gameboard.getBoard()[row+1][col+1].getStringSpot());
                stackPane.getChildren().addAll(text, button);
                button.setOnAction(event -> {openButton(event);});
            }
        }
        winners.setText(files.getwrite());
        
    }
    
                        
    @FXML
    private void openButton(ActionEvent event) { //denne metoden bestemmer hva som skjer når man trykker på en knapp. Knappen fjernes, og det sjekkes om spilleren har vunnet eller tapt.
        Button button = (Button) event.getSource();
        StackPane stackPane = (StackPane) button.getParent();
        stackPane.getChildren().remove(button);
        int x = grid.getRowIndex(stackPane);
        int y = grid.getColumnIndex(stackPane);
        if (gameboard.checklose(x, y) == true) {
            seier.setText("Du tapte!"); 
            openAll();
        }
        if (checkVictory() == true) {
            seier.setText("Du vant!");
            openAll();
            navn.setVisible(true);
            enter.setVisible(true);


        }
        openNeighbour1(gameboard.getBoard()[x+1][y+1]);
        
    }
    @FXML
    private void openAll() { //åpner alle spotsa i griden
        for (Node node: grid.getChildren()) {
            if (node instanceof StackPane) {
                StackPane stack = (StackPane) node;
                stack.getChildren().removeIf(nod -> nod instanceof Button);

            }
            
        }
    }

    @FXML
    private void handlenewGame(ActionEvent event) { //lager et nytt brett når man trykker på new game
        gameboard = gameboard.generateNewBoard();
        openAll();
        grid.getChildren().removeAll();
        seier.setText(null);
        navn.setVisible(false);
        enter.setVisible(false);

        initialize();
    }

    private boolean checkVictory() { //sjekker om spillet er vunnet
        ObservableList<Node> noder = grid.getChildren();
        count = (int) noder.stream().filter(node -> node instanceof StackPane && ((StackPane)node).getChildren().size() == 2).count();
        if (count == gameboard.getMaxBomb()) {
            return true;
        }
        else {
            return false;
        }
    }

    @FXML
    private void handleenter(ActionEvent event) { //lagrer navnet på spilleren som var vunnet
        String input = navn.getText();
        if (!input.equals("") && !input.equals("Skriv navnet ditt her")) {
            files.write(input);
        }
        navn.setText("");

    }

    private void openNeighbour1(Spot spot) { //denne er ganske komplisert. Metoden åpner alle naboene til spotten, hvis det er en safespot. Hvis naboene ogsp er en safespot, åpnes alle deres naboer også osv. 
        if (spot instanceof safeSpot) {
            if (!((safeSpot)spot).getNeighbours().isEmpty()) {

                    for (Spot safe : ((safeSpot)spot).getNeighbours()) {
                        int x = safe.getXval()-1;
                        int y = safe.getYval()-1;

                        for (Node node : grid.getChildren()) {
                            Integer rowIndex = GridPane.getRowIndex(node);
                            Integer columnIndex = GridPane.getColumnIndex(node);

                            if (rowIndex != null && columnIndex != null && rowIndex == x && columnIndex == y) {
                                if (node instanceof StackPane) {
                                    ((StackPane) node).getChildren().removeIf(n -> n instanceof Button);

                                    if (safe instanceof safeSpot && !((safeSpot) safe).isOpen()) {
                                        ((safeSpot) safe).setOpen(true); 
                                        openNeighbour1(safe);
                                    }
                                }
                            }
                        }
                    }
            }
       }
    }
}
