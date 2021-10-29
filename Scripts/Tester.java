import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;

public class Tester {
    public static void main(String[] args) throws MalformedURLException, IOException {
        MenuParser mParser = new MenuParser();

        DiningURL dURL = new DiningURL("Kins Dining", "Lunch");
        HashMap<String, HashMap<String, String>> hash =
                mParser.getNutritionalValuesForAllFoods(dURL.getCompleteURL());
        // System.out.println(hash);
        System.out.println(hash.get("Breadstick").get("Calories"));
    }
}
