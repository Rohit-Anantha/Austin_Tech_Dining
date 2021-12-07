package ATD;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirestoreTester {
    public static void main(String[] args)
            throws IOException, InterruptedException, ExecutionException {
        FileInputStream serviceAccount = new FileInputStream(
                "austintechdining-7943a-firebase-adminsdk-jefgw-99c8b42090.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);


        FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
                .setProjectId("austintechdining-7943a").setCredentials(credentials).build();
        Firestore firestore = firestoreOptions.getService();

        System.out.println();

        DocumentReference docRef = firestore.collection("Nutrition").document("tanya");
        // Add document data with id "alovelace" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put("first", "tanya");
        data.put("last", "Som");
        data.put("born", 1815);
        // asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
        // ...
        // result.get() blocks on response
        System.out.println("Update time : " + result.get().getUpdateTime());
    }
}
