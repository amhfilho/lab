package com.amhfilho.google;

import java.nio.file.Path;
import java.util.List;

public interface FileSearcher {
    List<Path> searchFileNames(FileSearchFilter filter);
}
