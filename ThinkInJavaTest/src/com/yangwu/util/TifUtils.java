package com.yangwu.util;

import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.RenderedOp;

import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.JPEGEncodeParam;
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
			
			separateTif("D:\\img\\test.tif","D:\\img");
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

	public static void separateTif(String tifFile,String destPath) throws IOException {
		FileSeekableStream ss = new FileSeekableStream(tifFile);
		
		TIFFEncodeParam encodeParam = null;
		
		TIFFEncodeParam param = new TIFFEncodeParam();
		
		TIFFEncodeParam param1 = new TIFFEncodeParam();
		
		ImageDecoder dec = ImageCodec.createImageDecoder("tiff", ss, encodeParam);
		
		int count = dec.getNumPages();
		
		System.out.println("**************Page Count : " + count + "**********************");
		
		
//		param.setCompression(TIFFEncodeParam.COMPRESSION_GROUP4);
		param.setCompression(TIFFEncodeParam.COMPRESSION_DEFLATE);
		param.setLittleEndian(false);
		
		for(int i = 0;i<count;i++) {
			RenderedImage page = dec.decodeAsRenderedImage(i);
			
			File file = new File(destPath + File.separator + "img" + i + ".tif" );
			
			ParameterBlock pb = new ParameterBlock();
			
			pb.addSource(page);
			
			pb.add(file.toString());
			
			pb.add("tiff");
			
			pb.add(param1);
			
			RenderedOp renderedOp = JAI.create("filestore", pb);
			renderedOp.dispose();
		}
		
	}
}
