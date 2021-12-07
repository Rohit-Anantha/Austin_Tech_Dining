package ATD;

import java.util.*;


/**
 * This class represents a URL that is used to access the "longmenu.aspx" that contains the links
 * for the different foods for UT Austin's dining halls.
 */
public class DiningURL {
    long date;
    String url;
    String name;
    String location;
    int MONTH;
    int DAY_OF_MONTH;
    int YEAR;

    String urlStart = "http://hf-food.austin.utexas.edu/foodpro/longmenu.aspx";
    String urlName = "?sName=I&";
    String locationNum = "locationNum=";
    String locationName = "&locationName=I&naFlag=1&WeeksMenus=This+Week%27s+Menus&dt";
    String dateURL = "date=";
    String AND_SYMBOL = "&";
    String PERCENT_TWO_F = "%2f";
    String mealString = "mealName=";
    String mealName;

    /**
     * Constructor for DiningURL
     * 
     * @param location 03 = Kins Dining <br>
     *        12 = Jester <br>
     *        05 = Jester City Market <br>
     *        14 = Kin's Market <br>
     *        27 = FAST Line at J2 <br>
     *        26 = Jesta Pizza <br>
     *        08 = Cypress Bend
     * @param mealName Breakfast, Lunch, Dinner, Lunch%2fDinner
     * 
     */
    public DiningURL(String location, String mealName) {
        this.location = location;
        setDate();
        this.mealName = mealName;
        this.url = getCompleteURL();

    }

    /**
     * This constructor sets the date to the current date.
     */
    public DiningURL() {
        setDate();
    }

    /**
     * This method creates a url version of the current date.
     */
    public String createDateString() {
        return MONTH + PERCENT_TWO_F + DAY_OF_MONTH + PERCENT_TWO_F + YEAR + AND_SYMBOL;
    }

    /**
     * This method returns a clickable url.
     * 
     * @return
     */
    public String getCompleteURL() {
        return urlStart + urlName + locationNum + getLocationNum() + locationName + dateURL
                + createDateString() + mealString + mealName;
    }

    // based on the location, get the location number
    /**
     * 03 = Kins Dining <br>
     * 12 = Jester <br>
     * 05 = Jester City Market <br>
     * 14 = Kin's Market <br>
     * 27 = FAST Line at J2 <br>
     * 26 = Jesta Pizza <br>
     * 08 = Cypress Bend
     */
    public String getLocationNum() {
        if (location.equals("Kins Dining")) {
            return "03";
        }
        if (location.equals("Jester")) {
            return "12";
        }
        if (location.equals("Jester City Market")) {
            return "05";
        }
        if (location.equals("Kin's Market")) {
            return "14";
        }
        if (location.equals("FAST Line at J2")) {
            return "27";
        }
        if (location.equals("Jesta Pizza")) {
            return "26";
        }
        if (location.equals("Cypress Bend")) {
            return "08";
        }
        return "";
    }

    /**
     * sets the date
     */
    public void setDate() {
        GregorianCalendar currentDate = new GregorianCalendar();
        this.MONTH = currentDate.get(Calendar.MONTH) + 1;
        this.DAY_OF_MONTH = currentDate.get(Calendar.DAY_OF_MONTH);
        this.YEAR = currentDate.get(Calendar.YEAR);
    }
}
