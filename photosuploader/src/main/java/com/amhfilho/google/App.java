package com.amhfilho.google;

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
        Set<String> acceptableExtensions = Arrays.asList("jpg","jpeg","mpg","mpeg","png","gif","mov","jsp")
                        .stream()
                        .collect(Collectors.toSet());

        long start = System.currentTimeMillis();
        System.out.println("Starting process...");

        FileSearchFilter filter = FileSearchFilter
                .start("c:/Users/Mult-e/projects")
                .withExtensions(acceptableExtensions)
                .withSizeRange(FileSearchSizeRange.lessOrEqualThan(500))
                .build();
        List<Path> files = new DefaultFileSearcher().searchFileNames(filter);

        files.forEach(x-> System.out.println(String.format("Name: %s - Size: %s",x.toString(),x.toFile().length())));

        System.out.println(String.format("Found %d files. Process completed in %s seconds", files.size(),(System.currentTimeMillis()-start)/1000));

    }
}
