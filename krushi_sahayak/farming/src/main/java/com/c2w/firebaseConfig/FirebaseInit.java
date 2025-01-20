package com.c2w.firebaseConfig;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;

      public class FirebaseInit {
            private static final String BUCKET_NAME = "krushi-sahayak-5c2db.appspot.com";
            private static final String FIRESTORE_PROJECT_ID = "krushi-sahayak-5c2db";
            private static Firestore db;
            //private static final String BUCKET_NAME = "krushi-sahayak-5c2db.appspot.com"; 

            public static void initializeFirebase() throws IOException {
                  System.out.println("intialize");
            
                  FileInputStream serviceAccount = new

                  FileInputStream(
                              "farming\\src\\main\\resources\\krushi.json");
                  FirebaseOptions options = new FirebaseOptions.Builder()
                              .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                              .setDatabaseUrl("https://krushi-sahayak-5c2db-default-rtdb.firebaseio.com/")
                              .build();

                  FirebaseApp.initializeApp(options);
                  db = FirestoreClient.getFirestore();
            }

            public void createRec(String username, String info) throws InterruptedException,
                        ExecutionException {

                  Map<String, Object> teamData = new HashMap<>();
                  teamData.put(username, info);

                  ApiFuture<WriteResult> future =

                              db.collection("cropInfo").document("cereal crops").set(teamData);

                  //System.out.println("Update time : " + future.get().getUpdateTime());
            }

            public static String readRec(String collection, String document, String username) throws InterruptedException,
                        ExecutionException {

                  DocumentReference docRef =

                              db.collection(collection).document(document);

                  ApiFuture<DocumentSnapshot> snapShot = docRef.get();
                  DocumentSnapshot docSnap = snapShot.get();
                  if (docSnap.exists()) {
                        String str = docSnap.get(username).toString();
                        return str;
                  } else {
                        System.out.println("Document Not Found");
                  }
                  return " ";

            }

            public static void updateRec(String collection, String document, String username, String info) {
                  Map<String, Object> updateData = new HashMap<>();
                  updateData.put(username, info);
                  ApiFuture<WriteResult> future = db.collection(collection)
                              .document(document).set(updateData, SetOptions.merge());

            }

            public void addData(String collection, String document, Map<String, Object> data) throws ExecutionException, InterruptedException {
                  System.out.println(document+" "+collection);
                  DocumentReference docRef = db.collection(collection).document(document);
                  ApiFuture<WriteResult> result = docRef.set(data);
                  result.get();
            }
      
            public DocumentSnapshot getData(String collection, String document) throws ExecutionException, InterruptedException {
                  try {
                  DocumentReference docRef = db.collection(collection).document(document);
                  ApiFuture<DocumentSnapshot> future = docRef.get();
                  return future.get();
                  } catch (Exception e) {
                  e.printStackTrace();
                  throw e;
                  }
            }
      
            public boolean authenticateUser(String username, String password) throws ExecutionException, InterruptedException {
                  DocumentSnapshot document = db.collection("users").document(username).get().get();
                  
                  if (document.exists()) {
                      String storedPassword = document.getString("password");
                      return password.equals(storedPassword);
                  }
                  return false;
            }

            public void fetchDocumentKeys(String collectionName, String documentId, TextArea textArea) throws ExecutionException, InterruptedException {
                  ApiFuture<DocumentSnapshot> future = db.collection(collectionName).document(documentId).get();
                  DocumentSnapshot document = future.get();
                  if (document.exists()) {
                        Map<String, Object> data = document.getData();
                        if (data != null) {
                              data.forEach((key,value) -> textArea.appendText(value + "\n"));
                        }
                  } else {
                        textArea.setText("No such document!");
                  }
           }
   
            public static String uploadImage(String localFilePath,String uploadFileName,String type) {
                  try {
                        FileInputStream serviceAccount = new FileInputStream(
                                    "farming\\src\\main\\resources\\krushi.json");

                        Storage storage = StorageOptions.newBuilder()
                                    .setCredentials(GoogleCredentials.fromStream(serviceAccount)).build().getService();
                        Path path = Paths.get(localFilePath);
                        byte[] bytes = Files.readAllBytes(path);
                        String folderPath = type + "/" + uploadFileName;

                        BlobId blobId = BlobId.of(BUCKET_NAME, folderPath);
                        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg")
                                    .setAcl(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))).build(); // Make
                                                                                                                    // file
                                                                                                                    // publicly
                                                                                                                    // accessible
                        storage.create(blobInfo, bytes);

                        return "https://storage.googleapis.com/" +
                                    BUCKET_NAME + type + uploadFileName;
                  } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                  }
            }
            public static Image fetchImageFromFirebase(String remoteImagePath) throws IOException {
                  Storage storage = StorageOptions.getDefaultInstance().getService();
                  com.google.cloud.storage.Blob blob = storage.get(BlobId.of(BUCKET_NAME, remoteImagePath));

                  if (blob != null) {
                        byte[] content = blob.getContent();
                        InputStream inputStream = new ByteArrayInputStream(content);
                        return new Image(inputStream);
                  } else {
                        throw new IOException("Failed to fetch image from Firebase Storage.");
                  }
            }

            public static String uploadImages(String username, String localFilePath, String uploadFileName, String description) {

                  try {
                        FileInputStream serviceAccount = new FileInputStream(
                              "farming\\src\\main\\resources\\krushi.json");

                        Storage storage = StorageOptions.newBuilder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
                              .build().getService();

                        Path path = Paths.get(localFilePath);
                        byte[] bytes = Files.readAllBytes(path);

                        BlobId blobId = BlobId.of(BUCKET_NAME, uploadFileName);
                        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg")
                              .setAcl(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))).build();

                        storage.create(blobInfo, bytes);

                        String imageUrl = "https://storage.googleapis.com/" + BUCKET_NAME + "/" + uploadFileName;

                        savePostToFirestore(username, imageUrl, description);

                        return imageUrl;

                  } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                  }
            }

            private static void savePostToFirestore( String username, String imageUrl, String description) throws IOException {
                  FirestoreOptions firestoreOptions = FirestoreOptions.newBuilder()
                        .setCredentials(GoogleCredentials.fromStream(new FileInputStream(
                                    "farming\\src\\main\\resources\\krushi.json")))
                        .setProjectId(FIRESTORE_PROJECT_ID)
                        .build();
                  Firestore db = firestoreOptions.getService();

                  Map<String, String> post = new HashMap<>();
                  post.put("usernameTextField",username);
                  post.put("imageUrl", imageUrl);
                  post.put("description", description);

                  ApiFuture<com.google.cloud.firestore.WriteResult> future = db.collection("posts").document().set(post,
                        SetOptions.merge());
                  try {
                        future.get();
                  } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                  }
            }

            public static Firestore getFirestore() {
                  if (db == null) {
                      try {
                          FirestoreOptions firestoreOptions = FirestoreOptions.newBuilder()
                                  .setCredentials(GoogleCredentials.fromStream(new FileInputStream("farming\\src\\main\\resources\\krushi.json")))
                                  .setProjectId("krushi-sahayak-5c2db")
                                  .build();
                          db = firestoreOptions.getService();
                      } catch (IOException e) {
                          e.printStackTrace();
                      }
                  }
                  return db;
              }
}
   



      