package com.ibm.notifier;

import org.junit.Test;

import java.io.IOException;

public class DirectoryWatcherTest {
    @Test
    public void shouldInvokeListenerWhenDirectoryChanges() throws IOException, InterruptedException {
        DirectoryWatcher watcher = new DirectoryWatcher("C:\\Users\\Mut-e\\antonio");
        ChangeListener listener = new DirectoryChangeListener();
        watcher.watch(listener);

    }
}
