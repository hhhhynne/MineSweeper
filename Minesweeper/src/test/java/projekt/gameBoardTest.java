package projekt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class gameBoardTest {
    private gameBoard brett;

    @BeforeEach
    public void setUp() {
        brett = new gameBoard();
        brett.fillBoard();
    }

    @Test
    @DisplayName("Sjekk at brettet blir laget ordentlig")
    public void testBoard() {
        Spot[][] spots = brett.getBoard();
        assertNotNull(spots);
        assertEquals(11, spots.length);
        assertEquals(11, spots[0].length);
    }

    @Test
    @DisplayName("Sjekk antall bomber")
    public void testBombs() {
        brett.makeBombs();
        Spot[][] spots = brett.getBoard();
        int bombCount = 0;
        for (Spot[] row : spots) {
            for (Spot spot : row) {
                if (spot instanceof bomb) {
                    bombCount++;
                }
            }
        }
        assertEquals(brett.getMaxBomb(), bombCount);


    }

    



    
}
