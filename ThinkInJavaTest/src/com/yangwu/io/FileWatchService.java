package com.yangwu.io;

import static org.hamcrest.CoreMatchers.containsString;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class FileWatchService {

	public static void main(String[] args) throws IOException, InterruptedException {
		

		WatchService watchService = FileSystems.getDefault().newWatchService();
		
		Path path = new File(System.getProperty("user.dir")).toPath();
		
		path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

		
		
		while (true) {
			WatchKey watchKey = watchService.take();
			if (watchKey == null) {
				continue;
			}

			for(WatchEvent<?> event : watchKey.pollEvents()) {
				if (event.kind().equals(StandardWatchEventKinds.ENTRY_CREATE)) {
					System.out.println("新建文件：" + event.context());
				}
			}
			watchKey.reset();
		}
		
	}

}
