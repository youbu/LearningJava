package com.yangwu.util;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;

import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.TIFFEncodeParam;

public class TifUtils {

	public static void main(String[] args) {
		List<String>fileList = new ArrayList<>();
		fileList.add("D:\\img\\1.jpg");
		fileList.add("D:\\img\\2.jpg");
		fileList.add("D:\\img\\3.jpg");
		fileList.add("D:\\img\\4.jpg");
		
		try {
			combineToTif(fileList, "D:\\img", "test.tif");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void combineToTif(List<String> fileList, String toPath, String fileName) throws IOException {
		if (fileList == null || fileList.isEmpty()) {
			return;
		}

		File[] jpgs = new File[fileList.size()];

		for (int i = 0; i < fileList.size(); i++) {
			jpgs[i] = new File(fileList.get(i));
		}

		List pages = new ArrayList<>();

		FileSeekableStream[] streams = new FileSeekableStream[jpgs.length];

		for (int i = 0; i < streams.length; i++) {
			streams[i] = new FileSeekableStream(jpgs[i].getCanonicalPath());
		}

		ParameterBlock pb = new ParameterBlock();

		PlanarImage firstPage = JAI.create("stream", streams[0]);

		for (int i = 1; i < jpgs.length; i++) {
			PlanarImage page = JAI.create("stream", streams[i]);
			pages.add(page);
		}

		TIFFEncodeParam param = new TIFFEncodeParam();
		
		File f = new File(toPath);
		if (!f.exists()) {
			f.mkdirs();
		}
		
		OutputStream os = new FileOutputStream(toPath + File.separator + fileName);
		
		ImageEncoder enc = ImageCodec.createImageEncoder("tiff", os, param);
		param.setExtraImages(pages.iterator());
		
		enc.encode(firstPage);
		
		for (FileSeekableStream fileSeekableStream : streams) {
			fileSeekableStream.close();
		}
		
		os.close();
		
	}
}
