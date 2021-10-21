import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class MenuParser {

    private static HashMap<String, ArrayList<String>> locationsAndFood;

    public MenuParser() throws MalformedURLException, IOException {
        Scanner scanner = new Scanner(new File("Scripts/DiningURLs.txt"));
        String baseURL = scanner.nextLine();
        while (scanner.hasNextLine()) {
            String overallURL = baseURL + scanner.nextLine();
            ArrayList<String> currentFoods = getFoodsFromScanner(getScannerFromWeb(overallURL));
            locationsAndFood.putIfAbsent("LOCATION: ", currentFoods);
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
        System.out.println(locationsAndFood);
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

}
