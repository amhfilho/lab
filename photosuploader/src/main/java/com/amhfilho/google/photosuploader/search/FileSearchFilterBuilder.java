package com.amhfilho.google.photosuploader.search;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class FileSearchFilterBuilder {
    private String directory;
    private Set<String> extensions = new HashSet<>();
    private String contains;
    private FileSearchSizeRange sizeRange;
    private FileSearchDateRange dateRange;

    protected FileSearchFilterBuilder(String directory){
        this.directory = directory;
    }

    public FileSearchFilterBuilder withExtensions(Set<String> extensions){
        this.extensions = extensions;
        return this;
    }

    public FileSearchFilterBuilder withExtension(String extension){
        this.extensions.add(extension);
        return this;
    }

    public FileSearchFilterBuilder contains(String contains){
        this.contains = contains;
        return this;
    }

    public FileSearchFilterBuilder withSizeRange(double minBytes, double maxBytes){
        this.sizeRange = new FileSearchSizeRange(minBytes, maxBytes);
        return this;
    }

    public FileSearchFilterBuilder withSizeRange(FileSearchSizeRange range){
        this.sizeRange = range;
        return this;
    }

    public FileSearchFilterBuilder dateRange(LocalDate start, LocalDate end){
        this.dateRange = new FileSearchDateRange(start,end);
        return this;
    }

    public FileSearchFilter build(){
        FileSearchFilter fileSearchFilter = new FileSearchFilter(this.directory);
        fileSearchFilter.contains = this.contains;
        fileSearchFilter.extensions = this.extensions;
        fileSearchFilter.dateRange = this.dateRange;
        fileSearchFilter.sizeRange = this.sizeRange;
        return fileSearchFilter;
    }
}
