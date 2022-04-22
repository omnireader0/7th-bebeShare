package bebeShare.config.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.StorageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.io.FileInputStream;
import java.io.IOException;

@Configuration
@Slf4j
public class FirebaseConfig {
    @Bean
    public FirebaseApp firebaseApp() throws IOException{
        log.info("Initializing Firebase.");
        FileInputStream serviceAccount = new FileInputStream("C:\\Users\\ltwy0\\OneDrive\\바탕 화면\\새 폴더\\7th-bebeShare\\src\\main\\java\\bebeShare\\config\\firebase\\firebase.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("bebeshare-image.appspot.com")
                .build();

                FirebaseApp app = FirebaseApp.initializeApp(options);
                log.info("FirebaseApp initialized" + app.getName());
                return app;
    }

    @Bean
    public FirebaseAuth firebaseAuth() throws IOException{
        return FirebaseAuth.getInstance(firebaseApp());
    }

    @Bean
    public Bucket bucket() throws IOException{
        return StorageClient.getInstance(firebaseApp()).bucket();
    }
}
