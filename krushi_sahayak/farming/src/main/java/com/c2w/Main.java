package com.c2w;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.c2w.firebaseConfig.FirebaseInit;
import com.c2w.initialize.InitializeApp;

import javafx.application.Application;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        FirebaseInit.initializeFirebase();
        System.out.println("Hello world!");
        Application.launch(InitializeApp.class,args);
      
    }
}
