import java.io.IOException;
import java.net.MalformedURLException;

public class Tester {
    public static void main(String[] args) throws MalformedURLException, IOException {
        MenuParser mParser = new MenuParser();
        String URL = "http://hf-food.austin.utexas.edu/foodpro/longmenu.aspx?sName=University%2bHousing%2band%2bDining&locationNum=12&locationName=Jester%2b2nd%2bFloor%2b(J2)%2bDining&naFlag=1&WeeksMenus=This+Week%27s+Menus&dtdate=10%2f21%2f2021&mealName=Breakfast";
        System.out.println(mParser.getNutritionalValuesForAllFoods(URL).get("Gala Apple"));
    }
}
