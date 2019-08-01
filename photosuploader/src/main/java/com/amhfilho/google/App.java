package com.amhfilho.google;

import com.amhfilho.google.photosuploader.persistence.DefaultTargetFileRepository;
import com.amhfilho.google.photosuploader.persistence.TargetFile;
import com.amhfilho.google.photosuploader.persistence.TargetFileRepository;
import com.amhfilho.google.photosuploader.search.DefaultFileSearcher;
import com.amhfilho.google.photosuploader.search.FileSearchFilter;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.api.gax.rpc.ApiException;
import com.google.photos.library.v1.PhotosLibraryClient;
import com.google.photos.library.v1.PhotosLibrarySettings;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        new App().test();
//        System.out.println("Connecting to DB...");
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("photosuploader");
//        EntityManager em = emf.createEntityManager();
//
//        List<TargetFile> files = em.createQuery("select f from TargetFile f",TargetFile.class).getResultList();
//        files.forEach(System.out::println);
//
//        System.out.println("connected to DB");
//
//        em.close();
//        emf.close();

    }

    public void testGoogle(){
        // Set up the Photos Library Client that interacts with the API
        PhotosLibrarySettings settings =
                PhotosLibrarySettings.newBuilder()
                        .setCredentialsProvider(
                                FixedCredentialsProvider.create(/* Add credentials here. */))
                        .build();

        try (PhotosLibraryClient photosLibraryClient =
                     PhotosLibraryClient.initialize(settings)) {

            // Create a new Album  with at title
            //Album createdAlbum = photosLibraryClient.createAlbum("My Album");

            // Get some properties from the album, such as its ID and product URL
            //String id = album.getId();
            //String url = album.getProductUrl();

        } catch (ApiException | IOException e) {
            // Error during album creation
        }
    }



    public void test(){
        Set<String> acceptableExtensions = Arrays.asList("jpg","jpeg","mpg","mpeg","png","gif","mov","jsp")
                .stream()
                .collect(Collectors.toSet());

        long start = System.currentTimeMillis();
        System.out.println("Starting process...");

        FileSearchFilter filter = FileSearchFilter
                .start("c:/Users/AntonioMarioHenrique/Pictures")
                .withExtensions(acceptableExtensions)
                //.withSizeRange(FileSearchSizeRange.lessOrEqualThan(500))
                .build();
        //List<Path> files = new DefaultFileSearcher().searchFileNames(filter);

        System.out.println("Connecting to DB...");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("photosuploader");
        EntityManager em = emf.createEntityManager();

        TargetFileRepository repository = new DefaultTargetFileRepository(em);
        em.getTransaction().begin();
        int deleted = repository.deleteAll();
        em.getTransaction().commit();
        System.out.println(String.format("Deleted %d records",deleted));
        List<TargetFile> targetFiles = repository.findAll();
        targetFiles.forEach(System.out::println);


//        em.getTransaction().begin();
//
//        files.forEach(x-> {
//            System.out.println(String.format("Name: %s - Size: %s - Parent: %s",x.toString(),x.toFile().length(),x.getParent().getFileName().toString()));
//            em.persist(TargetFile.from(x));
//        });
//        em.getTransaction().commit();

        System.out.println("Closing DB");
        emf.close();
        //System.out.println(String.format("Found %d files. Process completed in %s seconds", files.size(),(System.currentTimeMillis()-start)/1000));


    }
}
