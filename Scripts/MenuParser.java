import java.io.*;
import java.net.*;
import java.util.*;

public class MenuParser {

    private HashMap<String, ArrayList<String>> locationsAndFood;
    private HashMap<String, HashMap<String, String>> nutritionAndFood;

    public MenuParser() throws IOException {
        locationsAndFood = new HashMap<>();
        Scanner listOfPlaces = new Scanner(new File("..\\ListOfPlaces.txt"));
        Scanner diningURLs = new Scanner(new File("..\\DiningURLs.txt"));
        String baseURL = diningURLs.nextLine();
        while (diningURLs.hasNextLine()) {
            String secondHalfURL = diningURLs.nextLine();
            ArrayList<String> currentFoods = getFoodsFromScanner(getScannerFromWeb(baseURL + secondHalfURL));
            locationsAndFood.putIfAbsent(listOfPlaces.nextLine(), currentFoods);
        }
        Scanner longMenus = new Scanner(new File("LongMenuURLs.txt"));
        nutritionAndFood = new HashMap<>();
        // while (longMenus.hasNextLine()) {
        // nutritionAndFood.putAll(getNutritionalValuesForAllFoods(longMenus.nextLine()));
        // }
    }

    private void generateCurrentLongMenuURLs() {
        // get the date and the different locations and generate all the different long
        // menu urls
        GregorianCalendar currentDate = new GregorianCalendar();
        System.out.println(currentDate.getTime());
        System.out.println(currentDate.get(Calendar.DAY_OF_MONTH));
        System.out.println(currentDate.get(Calendar.MONTH) + 1);
        System.out.println(currentDate.get(Calendar.YEAR));
    }

    /**
     * Main method mostly used for testing.
     * 
     * @param args
     * @throws MalformedURLException
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        MenuParser mParser = new MenuParser();

        // if (args.length == 0) {

        // Scanner scanner = new Scanner(System.in);
        // boolean done = false;
        // String currentLocation = "";
        // while (!done) {
        // // set current location
        // currentLocation = askUserForLocation(scanner);
        // if (currentLocation.equals("")) {
        // done = true;
        // } else if (currentLocation.equals("Invalid Location")) {
        // System.out.println("Must be between 0 - 5");
        // } else {
        // mParser.getMenuOptions(currentLocation);
        // }
        // }
        // scanner.close();
        // }
        mParser.generateCurrentLongMenuURLs();

    }

    private void getMenuOptions(String currentLocation) {
        ArrayList<String> foods = locationsAndFood.get(currentLocation);
        for (String string : foods) {
            System.out.println(string);
            System.out.println(nutritionAndFood.get(string));
        }
    }

    // prompt user for input
    private static String askUserForLocation(Scanner scanner) {
        System.out.println("Which Location do you want the list of food from?");
        System.out.println("1. Jester Dining\n" + "2. Fresh and Simple Tastes (FAST) Line at J2 Dining\n"
                + "3. Kins Dining\n" + "4. Cypress Bend Cafe\n" + "5. Jesta's Pizza");
        System.out.println("Type 0 to exit");
        int location = scanner.nextInt();
        switch (location) {
        case 0:
            return "";
        case 1:
            return ("Jester Dining");
        case 2:
            return ("Fresh and Simple Tastes (FAST) Line at J2 Dining");
        case 3:
            return ("Kins Dining");
        case 4:
            return ("Cypress Bend Cafe");
        case 5:
            return ("Jesta's Pizza");
        default:
            System.out.println("Invalid Location");
            return ("Invalid Location");
        }
    }

    /**
     * Create a list of all the nutritional values relating to all the foods
     * 
     * @param URL - the url to find the nutrional values on --> in the longmenu.aspx
     * @return - a hashmap that corresponds String names of food to a HashMap of
     *         nutritionalValues and their String values.
     * @throws MalformedURLException
     * @throws IOException
     */
    public HashMap<String, HashMap<String, String>> getNutritionalValuesForAllFoods(String URL) throws IOException {
        ArrayList<String> urlEndingsOfFoodsFromLongMenu = getURLEndingsOfFoodsFromLongMenu(URL);
        HashMap<String, HashMap<String, String>> overallHashMap = new HashMap<>();
        for (int i = 0; i < urlEndingsOfFoodsFromLongMenu.size(); i++) {
            String ending = urlEndingsOfFoodsFromLongMenu.get(i);
            String beginning = "http://hf-food.austin.utexas.edu/foodpro/";
            Scanner tempScanner = getScannerFromWeb(beginning + ending);
            String currentFood = "";
            HashMap<String, String> nutritionalValues;
            while (tempScanner.hasNextLine()) {
                String line = tempScanner.nextLine();
                if (line.contains("labelrecipe")) {
                    // this line contains a new food
                    line = line.substring(line.indexOf("labelrecipe") + 13);
                    line = line.substring(0, line.indexOf("<"));
                    // start a new food
                    currentFood = line;
                    nutritionalValues = new HashMap<>();
                    overallHashMap.put(currentFood, nutritionalValues);
                }
                if (line.contains("nutfactstopnutrient")) {
                    line = line.substring(line.indexOf("nutfactstopnutrient") + 21);
                    // add all the different nutritional items and their values
                    if (Character.isAlphabetic(line.charAt(0))) {
                        String nutritionalValue = line.substring(0, line.indexOf("&"));
                        line = line.substring(line.indexOf("&"));
                        String value = line.substring(line.indexOf(";") + 1, line.indexOf("<"));
                        overallHashMap.get(currentFood).put(nutritionalValue, value);
                    }
                }
            }
        }
        return overallHashMap;
    }

    /**
     * Given a url in the form of a string, return a scanner based on that URL
     * 
     * @param urlParam
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public Scanner getScannerFromWeb(String urlParam) throws IOException {
        String url = urlParam;
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        return new Scanner(con.getInputStream());
    }

    // the location where the substring should start and end

    private static final int SHORT_MENU_RECIPIES_START = 87;
    private static final String AND_SYMBOL = "&";

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

    /**
     * get all url endings based on a long menu link
     * 
     * @param URL
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public ArrayList<String> getURLEndingsOfFoodsFromLongMenu(String URL) throws IOException {

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

    public enum DINING_LOCATIONS {
        KINS_DINING("03"), JESTER_DINING("12"), JESTER_CITY_MARKET("05"), KINS_MARKET("14"), FAST_LINE_AT_J2("27"),
        JESTA_PIZZA("26"), CYPRESS_BEND("08");

        public final String URLNumber;

        private DINING_LOCATIONS(String URL) {
            URLNumber = URL;
        }
    }

}
