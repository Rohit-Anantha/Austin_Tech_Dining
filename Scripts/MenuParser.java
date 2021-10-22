import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class MenuParser {

    private HashMap<String, ArrayList<String>> locationsAndFood;

    public MenuParser() throws MalformedURLException, IOException {
        locationsAndFood = new HashMap<>();
        Scanner scanLocations = new Scanner(new File("..\\ListOfPlaces.txt"));
        Scanner scanner = new Scanner(new File("..\\DiningURLs.txt"));
        String baseURL = scanner.nextLine();
        while (scanner.hasNextLine()) {
            String secondHalfURL = scanner.nextLine();
            ArrayList<String> currentFoods = getFoodsFromScanner(getScannerFromWeb(baseURL + secondHalfURL));
            locationsAndFood.putIfAbsent(scanLocations.nextLine(), currentFoods);

        }
    }

    /**
     * Main method mostly used for testing.
     * 
     * @param args
     * @throws MalformedURLException
     * @throws IOException
     */
    public static void main(String[] args) throws MalformedURLException, IOException {
        MenuParser mParser = new MenuParser();

        if (args.length == 0) {

            /*
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Which Location do you want the list of food from?");

                System.out.println("Jester Dining\n" + "Fresh and Simple Tastes (FAST) Line at J2 Dining\n"
                        + "Kins Dining\n" + "Cypress Bend Cafe\n" + "Jesta's Pizza");
                String location = scanner.nextLine();
                if (location.equals("")) {
                    break;
                }
                System.out.println(mParser.getFoodForLocation(location));
            }
            */

            String URL = "http://hf-food.austin.utexas.edu/foodpro/longmenu.aspx?sName=University%2bHousing%2band%2bDining&locationNum=12&locationName=Jester%2b2nd%2bFloor%2b(J2)%2bDining&naFlag=1&WeeksMenus=This+Week%27s+Menus&dtdate=10%2f21%2f2021&mealName=Breakfast";
            ArrayList<String> urlEndingsOfFoodsFromLongMenu = mParser.getURLEndingsOfFoodsFromLongMenu(URL);
            for (int i = 0; i < urlEndingsOfFoodsFromLongMenu.size(); i++) {
                String ending = urlEndingsOfFoodsFromLongMenu.get(i);
                String beginning = "http://hf-food.austin.utexas.edu/foodpro/";
                Scanner tempScanner = mParser.getScannerFromWeb(beginning + ending);
                while (tempScanner.hasNextLine()) {
                    String line = tempScanner.nextLine();
                        if(line.contains("labelrecipe"))
                        {
                            System.out.println();
                            System.out.println();
                            line = line.substring(line.indexOf("labelrecipe") + 13);
                            line = line.substring(0 , line.indexOf("<"));
                            System.out.println(line);
                            System.out.println();
                            System.out.println();
                        }
                        if (line.contains("nutfactstopnutrient")) {
                            line = line.substring(line.indexOf("nutfactstopnutrient") + 21);
                            line = line.substring(0, line.indexOf("nbsp"));
                            System.out.println(line);
                            System.out.println();
                        }
            }
            }

        } else {
            System.out.println(mParser.getFoodForLocation(args[0]));
        }

    }

    /**
     * Given a url in the form of a string, return a scanner based on that URL
     * 
     * @param urlParam
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public Scanner getScannerFromWeb(String urlParam) throws MalformedURLException, IOException {
        String url = urlParam;
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        Scanner scan = new Scanner(con.getInputStream());
        return scan;
    }

    // the location where the substring should start and end
    final static int SHORT_MENU_RECIPIES_START = 87;
    final static String AND_SYMBOL = "&";

    /**
     * Given a Scanner, return a list of just the foods from the web page.
     * 
     * @param scanner
     * @return
     */
    public ArrayList<String> getFoodsFromScanner(Scanner scanner) {
        ArrayList<String> foodObjects = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains("shortmenurecipes")) {

                foodObjects.add(line.substring(SHORT_MENU_RECIPIES_START, line.indexOf(AND_SYMBOL)));
            }
        }
        return foodObjects;
    }

    /**
     * Return a string of the food at a place for example: <br>
     * Jester Dining <br>
     * Fresh and Simple Tastes (FAST) Line at J2 Dining <br>
     * Kins Dining <br>
     * Cypress Bend Cafe <br>
     * Jesta's Pizza <br>
     * 
     * @param locationString
     * @return
     */
    public String getFoodForLocation(String locationString) {
        ArrayList<String> foods = locationsAndFood.get(locationString);
        StringBuilder sBuilder = new StringBuilder();
        for (String string : foods) {
            sBuilder.append(string);
            sBuilder.append("\n");
        }
        return sBuilder.toString();
    }

    public ArrayList<String> getURLEndingsOfFoodsFromLongMenu(String URL) throws MalformedURLException, IOException {

        Scanner scan = getScannerFromWeb(URL);
        ArrayList<String> URLEndings = new ArrayList<>();
        while (scan.hasNextLine()) {
            String currentLine = scan.nextLine();
            if (currentLine.contains("longmenucoldispname")) {
                currentLine = currentLine.substring(currentLine.indexOf("a href=") + 8);
                currentLine = currentLine.substring(0, currentLine.indexOf("'"));
                URLEndings.add(currentLine);
            }
        }
        return URLEndings;
    }

}
