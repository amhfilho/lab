package com.amhfilho.google.photosuploader.persistence;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.nio.file.Path;

@Entity
@Getter
public class TargetFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private UploadStatus uploadStatus;

    protected TargetFile(){}

    public TargetFile(String name) {
        this.name = name;
        this.uploadStatus = UploadStatus.PENDING;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TargetFile that = (TargetFile) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "TargetFile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uploadStatus=" + uploadStatus +
                '}';
    }

    public static TargetFile from(Path path){
        return new TargetFile(path.toString());
    }
}
