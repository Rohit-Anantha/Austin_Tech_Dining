package Austin_Tech_Dining;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The goal of this class is to parse a given CSV file into a different storage
 * format.
 */
public class CSVParser {
    public static void main(String[] args) throws IOException {
        Scanner menu = new Scanner(new File("oct13j2menu.csv"));
        File currentListOfItems = new File("currentListOfItems.txt");
        currentListOfItems.setWritable(true);
        FileWriter fWriter = new FileWriter(currentListOfItems);
        FoodSections currentFoodSection = FoodSections.BREAKFAST;
        menu.nextLine();
        while (menu.hasNextLine()) {
            String[] currentLine = menu.nextLine().split(",");
            System.out.println(Arrays.toString(currentLine));
            if (currentLine[0].equals("\"\"")) {
                if (currentLine[1].equals("\"\"")) {
                    currentFoodSection = FoodSections.DINNER;
                } else {
                    currentFoodSection = FoodSections.LUNCH;
                }
            }
            if (currentFoodSection == FoodSections.BREAKFAST) {
                fWriter.write("BREAKFAST: " + currentLine[0] + "\n");
            }
            if (currentFoodSection == FoodSections.LUNCH) {
                fWriter.write("LUNCH: " + currentLine[1] + "\n");
            }
            if (currentFoodSection == FoodSections.DINNER) {
                fWriter.write("DINNER: " + currentLine[2] + "\n");
            }
        }
        fWriter.close();
    }

    private enum FoodSections {
        BREAKFAST, LUNCH, DINNER
    }
}
