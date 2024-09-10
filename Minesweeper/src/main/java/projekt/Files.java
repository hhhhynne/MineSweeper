package projekt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Files {

    public void write(String string){
     
        try {
            FileWriter writer = new FileWriter("src/main/java/projekt/MineSweepertext.txt", true);
            writer.append(string + "\n");
            writer.close();

        }
        catch (IOException error) {
            System.err.println("Error writing to file: " + error.getMessage());
            error.printStackTrace();

        }
    }

    public String getwrite() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/projekt/MineSweepertext.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
                
            }           
            // sletter siste nye line separator
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            reader.close();

            
            

        }
        catch (IOException error) {
            error.printStackTrace();
        }
        return stringBuilder.toString();
        
    }

    public void clearFileContent() { //sletter innholdet i filen
        try {
            FileWriter writer = new FileWriter("src/main/java/projekt/MineSweepertext.txt", false);
            writer.write("");
            writer.close();
        } catch (IOException error) {
            System.err.println("Error clearing file content: " + error.getMessage());
            error.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Files test = new Files();
        test.getwrite();

    }
    
}


