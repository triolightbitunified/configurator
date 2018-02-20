package com.bitunified.server.google;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.FileList;

import javax.servlet.ServletContext;
import java.io.*;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.List;


public class Download {
    /**
     * Application name.
     */
    private static final String APPLICATION_NAME =
            "My Project";

    /**
     * Directory to store user credentials for this application.
     */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.home"), ".credentials/drive-java-quickstart");

    public static java.io.File PKCS8KEY = new java.io.File(
            System.getProperty("java.io.tmpdir"), "pkcs8_key");
    /**
     * Global instance of the {@link FileDataStoreFactory}.
     */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY =
            JacksonFactory.getDefaultInstance();

    /**
     * Global instance of the HTTP transport.
     */
    private static HttpTransport HTTP_TRANSPORT;

    /**
     * Global instance of the scopes required by this quickstart.
     * <p>
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/drive-java-quickstart
     */
    private static final List<String> SCOPES =
            Arrays.asList(DriveScopes.DRIVE_METADATA_READONLY);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    private final ServletContext context;

    public Download(ServletContext context) {
        this.context = context;
    }


    private PrivateKey stringToPK() throws Exception {

        BufferedInputStream bis;
        try {
            bis = new BufferedInputStream(new FileInputStream(PKCS8KEY));
        } catch (FileNotFoundException e) {
            throw new Exception("Could not locate keyfile at ", e);
        }
        byte[] privKeyBytes = new byte[(int) PKCS8KEY.length()];
        bis.read(privKeyBytes);
        bis.close();
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        KeySpec ks = new PKCS8EncodedKeySpec(privKeyBytes);
        RSAPrivateKey privKey = (RSAPrivateKey) keyFactory.generatePrivate(ks);
        return privKey;
    }

    public Credential authorize() {

        try {
            return auth4();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Credential auth4() throws Exception {
        PrivateKey privateKey = stringToPK();
        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(HTTP_TRANSPORT)
                .setJsonFactory(JSON_FACTORY)
                .setServiceAccountId("config@iron-burner-185915.iam.gserviceaccount.com")
                .setServiceAccountScopes(Arrays.asList(DriveScopes.DRIVE))
                //.setServiceAccountUser("106604968324764351739")
                .setServiceAccountPrivateKey(privateKey)
                .build();
        return credential;
    }


    /**
     * Build and return an authorized Drive client service.
     *
     * @return an authorized Drive client service
     * @throws IOException
     */
    public Drive getDriveService() throws IOException {
        Credential credential = authorize();
        return new Drive.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }


    public ByteArrayOutputStream getParserDataFile(String fileNameStart) throws IOException {
        // Build a new authorized API client service.
        Drive service = getDriveService();


//        Permission userP = new Permission()
//                .setType("user")
//                .setRole("writer")
//
//                .setValue("triolightbitunified@gmail.com");
//
//        service.permissions().insert("1RpBiSblZk8SgUOxr0rxFkaNkgQ5gfItm",userP).execute();

//        com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();
//        fileMetadata.setTitle("Configurator");
//        fileMetadata.setMimeType("application/vnd.google-apps.folder");

//        com.google.api.services.drive.model.File file = service.files().insert(fileMetadata)
//                .setFields("id")
//
//                .execute();
//        System.out.println("Folder ID: " + file.getId());
//        PermissionId permissionId = service.permissions().getIdForEmail("triolightbitunified@gmail.com").execute();
//                Permission userPermission = new Permission()
//                .setType("anyone")
//                .setRole("writer")
//                .setEmailAddress("triolightbitunified@gmail.com");
//        service.permissions().insert("1RpBiSblZk8SgUOxr0rxFkaNkgQ5gfItm",userPermission).execute();
//
//        service.permissions().update("1RpBiSblZk8SgUOxr0rxFkaNkgQ5gfItm",permissionId.getId(),userPermission).execute();


        // Print the names and IDs for up to 10 files.
        FileList result = service.files().list()
                .setMaxResults(10)
                .execute();
        List<com.google.api.services.drive.model.File> files = result.getItems();
        if (files == null || files.size() == 0) {
            System.out.println("No files found.");
        } else {
            System.out.println("Files:");
            for (com.google.api.services.drive.model.File f : files) {
                if (f.getOriginalFilename() != null && f.getOriginalFilename().startsWith(fileNameStart)) {
                    System.out.printf("%s (%s)\n", f.getTitle(), f.getId());
                    return downloadFile(service, f);

                }
                System.out.printf("%s (%s)\n", f.getTitle(), f.getId());
            }
        }
        return null;
    }

    private ByteArrayOutputStream downloadFile(Drive service, com.google.api.services.drive.model.File f) throws IOException {

        String fileId = f.getId();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        service.files().get(fileId)
                .executeMediaAndDownloadTo(outputStream);
        return outputStream;
    }

}