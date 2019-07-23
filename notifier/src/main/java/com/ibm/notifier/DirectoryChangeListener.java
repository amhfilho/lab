package com.ibm.notifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.WatchEvent;

public class DirectoryChangeListener implements ChangeListener {
    private static final Logger logger = LoggerFactory.getLogger(DirectoryChangeListener.class.getName());
    @Override
    public void processChange(WatchEvent event) {

        logger.info("kind().name(): {}",event.kind().name());
        logger.info("context: {}",event.context().toString());
    }
}
