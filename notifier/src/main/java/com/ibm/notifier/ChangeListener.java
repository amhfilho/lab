package com.ibm.notifier;

import java.nio.file.WatchEvent;

public interface ChangeListener {
    void processChange(WatchEvent event);
}
