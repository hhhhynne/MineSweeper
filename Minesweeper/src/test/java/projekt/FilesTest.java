package projekt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FilesTest {
    private Files files;
    
    @Test
    @DisplayName("Sjekker at den skriver og henter riktig fra fil")
    public void testFile() {
        files = new Files();
        files.write("Test");
        assertEquals("Test", files.getwrite());
        files.clearFileContent();
    }    
}
