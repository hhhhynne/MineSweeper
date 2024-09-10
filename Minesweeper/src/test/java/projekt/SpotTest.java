package projekt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpotTest {
    private Spot spot;

    @BeforeEach
    public void setUp() {
        spot = new Spot(1, 1);
    }

    @Test
    @DisplayName("Sjekk posisjon")
    public void checkPosition() {
        assertEquals(1, spot.getXval());
        assertEquals(1, spot.getYval());
    }

    @Test
    @DisplayName("Sjekk bomber")
    public void chechAddBomb() {
        assertEquals(0, spot.getBombsNearby());
        spot.addBomb();
        assertEquals(1, spot.getBombsNearby());

    }

    @Test
    @DisplayName("Sjekk setStringSpot funksjonen")
    public void testString() {
        assertEquals("x", spot.getStringSpot());
        spot.setStringSpot("B");
        assertEquals("B", spot.getStringSpot());
    }

    
}
