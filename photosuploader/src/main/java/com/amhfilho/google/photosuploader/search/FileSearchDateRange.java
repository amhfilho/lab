package com.amhfilho.google.photosuploader.search;

import java.time.LocalDate;

public class FileSearchDateRange {
    private LocalDate start;
    private LocalDate end;

    public FileSearchDateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }
}
