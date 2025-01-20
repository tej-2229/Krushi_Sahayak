package com.c2w.View;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
 import com.google.cloud.storage.BlobId;
 import com.google.cloud.storage.BlobInfo;
 import com.google.cloud.storage.Storage;
 import com.google.cloud.storage.StorageOptions;
 import java.io.InputStream;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
 import java.nio.file.Files;
 import java.nio.file.Path;
 import java.nio.file.Paths;
 import java.util.Arrays;
public class ImageUploader {

    private static final String BUCKET_NAME = "krushi-sahayak-5c2db.appspot.com"; // Ensure this is correct


    public static String uploadImage(String localFilePath,String uploadFileName,String type) {
    try {
    FileInputStream serviceAccount = new FileInputStream("farming\\src\\main\\resources\\krushi.json");
   
    Storage storage = StorageOptions.newBuilder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).build().getService();
    Path path = Paths.get(localFilePath);
    byte[] bytes = Files.readAllBytes(path);
    String folderPath = type + "/" + uploadFileName;
   
    BlobId blobId = BlobId.of(BUCKET_NAME,folderPath);
    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").setAcl(Arrays.asList(Acl.of(Acl.User.ofAllUsers(),Acl.Role.READER))).build(); // Make file publicly accessible
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
    }
   
   
