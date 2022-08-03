package com.kitezeng.springbootmall;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootApplication
public class SpringbootMallApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(SpringbootMallApplication.class, args);
//        FileInputStream serviceAccount =
//                new FileInputStream("./springboot-mall-firebase-adminsdk-cjn0f-aad2c519fc.json");
//
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .build();
//
//        FirebaseApp.initializeApp(options);

//        ClassPathResource serviceAccount = new ClassPathResource("springboot-mall-firebase-adminsdk-cjn0f-aad2c519fc.json");
//
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
//                .setStorageBucket("springboot-mall.appspot.com")
//                .build();
//
//        FirebaseApp.initializeApp(options);
    }

}
