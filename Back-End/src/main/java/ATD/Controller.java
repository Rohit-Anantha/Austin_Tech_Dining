package ATD;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;

public class Controller {

    static FileInputStream serviceAccount;
    static GoogleCredentials credentials;
    static FirestoreOptions firestoreOptions;
    static Firestore firestore;

    public Controller() throws IOException {
        serviceAccount = new FileInputStream(
                "austintechdining-7943a-firebase-adminsdk-jefgw-99c8b42090.json");
        credentials = GoogleCredentials.fromStream(serviceAccount);
        firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
                .setProjectId("austintechdining-7943a").setCredentials(credentials).build();
        firestore = firestoreOptions.getService();
        firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
                .setProjectId("austintechdining-7943a").setCredentials(credentials).build();
    }

    public static void main(String[] args)
            throws IOException, InterruptedException, ExecutionException {
        // create a new menu parser
        MenuParser menuParser = new MenuParser();
        Controller c = new Controller();
        Controller.writeAllFoodFromLocation(menuParser, "Jester", "Dinner");
        //Controller.createJSforLocationAndMeal(menuParser, "FAST Line at J2", "Lunch");
    }

    public static void createJSforLocationAndMeal(MenuParser menuParser, String location,
            String meal) throws IOException {
        DiningURL diningURL = new DiningURL(location, meal);
        HashMap<String, HashMap<String, String>> nutritionAndFood =
                menuParser.getNutritionalValuesForAllFoods(diningURL.getCompleteURL());


        // create a new file

        location = location.replaceAll("\\s+", "");

        String fileName = location + meal + ".js";
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write("const " + location + meal + " = [");

        // write the food, an id, and caloric information to the file

        int id = 0;
        Iterator<String> it = nutritionAndFood.keySet().iterator();
        while (it.hasNext()) {
            String food = it.next();
            fileWriter.write("{");
            fileWriter.write("id: \"" + id + "\",");
            fileWriter.write("name: \"" + makeStringSafe(food) + "\",");
            fileWriter.write(
                    "calories: " + "\"" + nutritionAndFood.get(food).get("Calories") + "\"" + ",");
            fileWriter.write("}");
            id++;
            fileWriter.write("\n");
            if (it.hasNext()) {
                fileWriter.write(",");
            }
        }
        fileWriter.write("]");
        fileWriter.write("\n");
        fileWriter.write("export default " + location + meal + ";");
        fileWriter.close();
        System.out.println("Done");
    }

    private static String makeStringSafe(String string) {
        final String[] metaCharacters = {"\\", "^", "$", "{", "}", "[", "]", "(", ")", ".", "*",
                "+", "?", "|", "<", ">", "-", "&", "%", "\""};

        for (int i = 0; i < metaCharacters.length; i++) {
            if (string.contains(metaCharacters[i])) {
                string = string.replace(metaCharacters[i], "\\" + metaCharacters[i]);
            }
        }
        return string;
    }

    /**
     * This method takes a location (Jester, Kins, etc) and a meal (breakfast, lunch, or dinner) and
     * writes all the foods for that meal to the database
     * 
     * @param menuParser
     * @param location
     * @param meal
     * @throws IOException
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void writeAllFoodFromLocation(MenuParser menuParser, String location, String meal)
            throws IOException, InterruptedException, ExecutionException {
        DiningURL diningURL = new DiningURL(location, meal);
        System.out.println(diningURL.getCompleteURL());
        System.out.println();
        HashMap<String, HashMap<String, String>> nutritionalValuesForAllFoods =
                menuParser.getNutritionalValuesForAllFoods(diningURL.getCompleteURL());
        System.out.println("Got foods/nutritions");
        // add all the foods to the database
        for (String food : nutritionalValuesForAllFoods.keySet()) {
            // get the nutritional values for each food
            Map<String, String> nutritionalValues = nutritionalValuesForAllFoods.get(food);
            // add the food to the database with the nutritional values
            DocumentReference docRef = firestore.collection("Nutrition")
                    .document(URLEncoder.encode(food, StandardCharsets.UTF_8));
            ApiFuture<WriteResult> result = docRef.set(nutritionalValues);
            System.out.println("Update time : " + result.get().getUpdateTime());
        }
    }

    /**
     * gets the nutritionalvalue of a food from the database
     * 
     * @param food
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void getFoodNutrition(String food)
            throws InterruptedException, ExecutionException {
        // get the nutritional values for the food
        // add the food to the database with the nutritional values
        // get the nutritional values for a specific food

        food = URLEncoder.encode(food, StandardCharsets.UTF_8);
        DocumentReference docRef = firestore.collection("Nutrition").document(food);
        // asynchronously retrieve the document
        ApiFuture<DocumentSnapshot> future = docRef.get();
        // ...
        // future.get() blocks on response
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            System.out.println("ID: " + document.getId());
            System.out.println("calories: " + document.getString("Calories"));
        } else {
            System.out.println("No such document!");
        }

    }
}
