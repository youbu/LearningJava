package com.yangwu.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.yangwu.entity.FileInfo;

public class BigDatasort {

	private static final String PREFIX = "big_data_";
	private static final int SPLIT_SIZE = 100000;
	private static final String PATH = "D:\\BigFile";
	private static final String SUFFIX = ".txt";
	private static final String FINAL_FILE = "D:\\MergeFile.txt";

	public static void main(String[] args) {
		BigDatasort sort = new BigDatasort();
		try {
			sort.splitFile("aa.txt");
			sort.mergeFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int fileNum = 0;

	private void splitFile(String fname) throws FileNotFoundException, IOException {
		int count = 0;

		List<Integer> list = new ArrayList<Integer>();

		String data;
		try (BufferedReader br = new BufferedReader(new FileReader(new File(fname)))) {
			do {
				data = br.readLine();
				if (data != null) {
					list.add(Integer.valueOf(data));
					count++;
					if (count >= SPLIT_SIZE) {
						System.out.println("====ÎÄ¼þ·Ö¸î=====");
						writeFile(PATH, list);
						fileNum++;
						count = 0;
						list.clear();
					}
				}else if (list != null){
					writeFile(PATH, list);
				}
			} while (data != null);
		}
	}

	private void writeFile(String path2, List<Integer> list) throws IOException {
		File folder = new File(path2);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		String fileName = path2 + "\\" + PREFIX + fileNum + SUFFIX;
		
		Collections.sort(list);
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
			for(Integer num : list) {
				bw.write(num + "\r\n");
			}
			bw.flush();
		}
	}

	private void mergeFile() throws IOException {
		File[] files = new File(PATH).listFiles();
		List<FileInfo>fileList = new ArrayList<FileInfo>();
		for(File file : files){
			BufferedReader br = new BufferedReader(new FileReader(file));
			FileInfo fileinfo = new FileInfo();
			fileinfo.setReader(br);
			fileinfo.setFileName(file.getName());
			fileinfo.readNext();
			fileList.add(fileinfo);
		}
		
//		Collections.sort(fileList, new Comparator<FileInfo>() {
//			@Override
//			public int compare(FileInfo o1, FileInfo o2) {
//				if(o1.getCurrentNum() != o2.getCurrentNum()) {
//					return o1.getCurrentNum() - o2.getCurrentNum();
//				}else {
//					return o1.getFileName().compareTo(o2.getFileName());
//				}				
//			}
//		});
		Collections.sort(fileList, (o1,o2) -> {
			if(o1.getCurrentNum() != o2.getCurrentNum()) {
				return o1.getCurrentNum() - o2.getCurrentNum();
			}else {
				return o1.getFileName().compareTo(o2.getFileName());
			}
		});
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(FINAL_FILE))){
			while(!fileList.isEmpty()) {
				FileInfo fi = fileList.get(0);
				System.out.println("========" + fi.getCurrentNum() + " ,  " + fi.getFileName() + "========");
				bw.write(fi.getCurrentNum() + "\r\n");
				fi.readNext();
				
//				Collections.sort(fileList, new Comparator<FileInfo>() {
//					@Override
//					public int compare(FileInfo o1, FileInfo o2) {
//						if(o1.getCurrentNum() != o2.getCurrentNum()) {
//							return o1.getCurrentNum() - o2.getCurrentNum();
//						}else {
//							return o1.getFileName().compareTo(o2.getFileName());
//						}				
//					}
//				});
				Collections.sort(fileList, (o1,o2) -> {
					if(o1.getCurrentNum() != o2.getCurrentNum()) {
						return o1.getCurrentNum() - o2.getCurrentNum();
					}else {
						return o1.getFileName().compareTo(o2.getFileName());
					}
				});
				
				if(fi.getCurrentNum() == -1) {
					fileList.remove(fi);
				}
			}
		}		
	}
}
