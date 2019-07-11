package com.amhfilho.google;

import java.util.Set;

public class FileSearchFilter {
    protected String directory;
    protected Set<String> extensions;
    protected String contains;
    protected FileSearchSizeRange sizeRange;
    protected FileSearchDateRange dateRange;

    protected FileSearchFilter(String directory){
        this.directory = directory;
    }

    public static FileSearchFilterBuilder start(String directory){
        return new FileSearchFilterBuilder(directory);
    }

    public String getDirectory() {
        return directory;
    }

    public Set<String> getExtensions() {
        return extensions;
    }

    public String getContains() {
        return contains;
    }

    public FileSearchSizeRange getSizeRange() {
        return sizeRange;
    }

    public FileSearchDateRange getDateRange() {
        return dateRange;
    }
}
