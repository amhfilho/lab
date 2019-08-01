package com.amhfilho.google.photosuploader.persistence;

import java.util.List;

public interface TargetFileRepository {
    List<TargetFile> findAll();
    void save(TargetFile targetFile);
    int deleteAll();
}
