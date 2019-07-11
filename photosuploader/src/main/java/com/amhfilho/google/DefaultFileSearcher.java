package com.amhfilho.google;

import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DefaultFileSearcher implements FileSearcher {
    @Override
    public List<Path> searchFileNames(FileSearchFilter filter) {
        List<Path> results = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(Paths.get(filter.getDirectory()))) {
            results = walk.filter(Files::isRegularFile)
                    .filter(f->filter.getExtensions()!=null?filter.getExtensions().contains(FilenameUtils.getExtension(f.toString())):true)
                    .filter(f->filter.getContains()!=null?f.toString().contains(filter.getContains()):true)
                    .filter(f->filter.getSizeRange()!=null?f.toFile().length() >= filter.getSizeRange().getMinBytes() && f.toFile().length() <= filter.getSizeRange().getMaxBytes():true)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }
}
