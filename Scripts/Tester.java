import java.io.IOException;
import java.net.MalformedURLException;

public class Tester {
    public static void main(String[] args) throws MalformedURLException, IOException {
        MenuParser mParser = new MenuParser();

        DiningURL dURL = new DiningURL("Kins Dining", "Lunch");
        System.out.println(mParser.getNutritionalValuesForAllFoods(dURL.getCompleteURL()).keySet());
    }
}
