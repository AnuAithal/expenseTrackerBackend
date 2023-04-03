package com.example.expenseTracker.expenseTracker.config;

//import com.google.api.client.util.Value;
import org.springframework.beans.factory.annotation.Value;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

   //"${jdbc.url}"
    @Value("${firebase.file}")
    private String firebasePrivateKeyFile;


    @Bean
    public void initFirebase() throws IOException
    {
        System.out.println(firebasePrivateKeyFile);
        InputStream firebaseFile =new ClassPathResource(firebasePrivateKeyFile).getInputStream();
        FirebaseOptions firebaseOptions=FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(firebaseFile))
                .build();
        if((FirebaseApp.getApps().isEmpty()))
        {
            FirebaseApp.initializeApp(firebaseOptions);
        }
    }
}
