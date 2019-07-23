package com.ibm.notifier;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Application {

	private static final int FIFTEEN_MINUTES = 900000;
	private static final int THIRTY_SECONDS = 30000;
	private static String dir;
	private static boolean recursive;
	private static String networkUrl;

	static void usage() {
		System.err.println("usage: java -jar notifier [-r] dir url");
		System.exit(-1);
	}

	public static void main(String[] args) throws IOException {
		// parse arguments
		if (args.length == 0 || args.length > 3) {
			// usage();
			args = new String[] { "-r", WatchDir.DEFAULT_WATCH_PATH };
		}
		int dirArg = 0;
		if (args[0].equals("-r")) {
			if (args.length < 2)
				usage();
			recursive = true;
			dirArg++;
		}
		dir = args[dirArg];
		networkUrl = args[dirArg + 1];

		initialize();

	}

	private static void initialize() {
		final Path path = Paths.get(dir);


		// register directory and process its events
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					new WatchDir(path, recursive).processEvents();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}).start();



	}

}
