package com.ibm.notifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;

public class DirectoryWatcher {
    private String directory;
    private static final Logger logger = LoggerFactory.getLogger(DirectoryWatcher.class.getName());

    public DirectoryWatcher(String directory){
        this.directory = directory;
    }

    public void watch(ChangeListener listener) throws IOException, InterruptedException {
        logger.info("Starting watching {}", directory);
        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            Path path = initDirectory();
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                                        StandardWatchEventKinds.ENTRY_MODIFY);
            WatchKey key;
            while ((key = watchService.take()) != null) {
                logger.info("key: {}", key.toString());
                for (WatchEvent<?> event : key.pollEvents()) {
                    listener.processChange(event);
                }
                key.reset();
            }
        }
    }

    private Path initDirectory() {
        Path path = Paths.get(directory);
        if(!Files.isDirectory(path)){
            throw new IllegalArgumentException("Not a directory");
        }
        return path;
    }


}
