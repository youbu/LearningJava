package com.yangwu.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.TIFFEncodeParam;

public class Pdf2TiffUtil {
	private static final float DPI = 160;
	static {
        System.setProperty("com.sun.media.jai.disableMediaLib", "true");
    }
	/**
	 * ����������ȡpdf��ת��Ϊtiff��д�������.<br/>
	 * �ο��б�
	 * <ol>
	 * <li><a href=
	 * "http://www.coderanch.com/t/497492/java/java/Convert-PDF-files-Tiff-files"
	 * >Convert PDF files to Tiff files</a></li>
	 * <li><a href=
	 * "http://www.oracle.com/technetwork/cn/java/javaee/downloads/readme-1-1-2-137176.html"
	 * >Java(TM) Advanced Imaging API README</a></li>
	 * </ol>
	 * 
	 * @param is
	 *            ���������ṩpfg����.
	 * @param os
	 *            �����.
	 */
	public static void pdf2Tiff(InputStream is, OutputStream os) {
		PDDocument doc = null;
		try {
			doc = PDDocument.load(is);
			int pageCount = doc.getNumberOfPages();
			PDFRenderer renderer = new PDFRenderer(doc); // ����PDDocument���󴴽�pdf��Ⱦ��

			List<PlanarImage> piList = new ArrayList<PlanarImage>(pageCount - 1);
			for (int i = 0 + 1; i < pageCount; i++) {
				BufferedImage image = renderer.renderImageWithDPI(i, DPI, ImageType.RGB);
				PlanarImage pimg = JAI.create("mosaic", image);
				piList.add(pimg);
			}

			TIFFEncodeParam param = new TIFFEncodeParam();// ����tiff���������
			param.setCompression(TIFFEncodeParam.COMPRESSION_DEFLATE);// ѹ������
			param.setExtraImages(piList.iterator());// ����ͼƬ�ĵ�����

			BufferedImage fimg = renderer.renderImageWithDPI(0, DPI, ImageType.RGB);
			PlanarImage fpi = JAI.create("mosaic", fimg); // ͨ��JAI��create()����ʵ����jai��ͼƬ����

			ImageEncoder enc = ImageCodec.createImageEncoder("tiff", os, param);
			enc.encode(fpi);// ָ����һ�����б����jaiͼƬ����,�������д�뵽���
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (doc != null)
					doc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	public static void main(String[] args) throws IOException {
		String pdfFile = "E:\\test\\test.pdf";
		String tifFile = "E:\\test\\test.tif";
		FileInputStream inputStream = new FileInputStream(new File(pdfFile));
		OutputStream os = new FileOutputStream(new File(tifFile));
		
		pdf2Tiff(inputStream, os);
		TifUtils.separateTif(tifFile, "E://test//img");
	}
	
}