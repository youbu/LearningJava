package com.yangwu.mail;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class ReciveMail2 {

	private Folder folder;

	private Store store;

	/**
	 * ��ȡsession�Ự�ķ���
	 *
	 * @return
	 * @throws Exception
	 */
	public void InitStore(String host, int port, String address, String type, String password) throws Exception {
		Properties properties = System.getProperties();
		properties.setProperty("mail.pop3.host", host);
		properties.setProperty("mail.pop3.port", String.valueOf(port));
		// SSL��ȫ���Ӳ���
		properties.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.pop3.socketFactory.fallback", "true");
		properties.setProperty("mail.pop3.socketFactory.port", String.valueOf(port));
		Session sessionMail = Session.getDefaultInstance(properties, null);
		store = sessionMail.getStore(type);
		store.connect(host, address, password);
	}

	/**
	 * �����ʼ�
	 *
	 * @param //������û���������
	 * @return ��
	 * @throws MessagingException
	 */
	public Message[] receiveMail() throws MessagingException {

		// ��������ڵ��ʼ���Folder����
		folder = store.getFolder("INBOX");// ���ռ���
		folder.open(Folder.READ_WRITE);
		return folder.getMessages();
	}

	/**
	 * ��÷����˵ĵ�ַ
	 *
	 * @param message��Message
	 * @return �����˵ĵ�ַ
	 */
	public String getFrom(Message message) throws Exception {
		InternetAddress[] address = (InternetAddress[]) ((MimeMessage) message).getFrom();
		String from = address[0].getAddress();
		if (from == null) {
			from = "";
		}
		return from;
	}

	/**
	 * ����ʼ�����
	 *
	 * @param message��Message
	 * @return �ʼ�����
	 */
	public String getSubject(Message message) throws Exception {
		String subject = "";
		if (((MimeMessage) message).getSubject() != null) {
			subject = MimeUtility.decodeText(((MimeMessage) message).getSubject());// ���ʼ��������
		}
		return subject;
	}

	/**
	 * ��ȡ�ʼ�����
	 *
	 * @param part��Part
	 */
	public String getMailContent(Part part) throws Exception {
		StringBuffer bodytext = new StringBuffer();// ����ʼ�����
		// �ж��ʼ�����,��ͬ���Ͳ�����ͬ
		if (part.isMimeType("text/plain")) {
			bodytext.append((String) part.getContent());
		} else if (part.isMimeType("text/html")) {
			bodytext.append((String) part.getContent());
		} else if (part.isMimeType("multipart/*")) {
			Multipart multipart = (Multipart) part.getContent();
			int counts = multipart.getCount();
			for (int i = 0; i < counts; i++) {
				bodytext.append(getMailContent(multipart.getBodyPart(i)));
			}
		} else if (part.isMimeType("message/rfc822")) {
			getMailContent((Part) part.getContent());
		} else {
		}
		// System.out.println(bodytext.toString());
		return bodytext.toString();
	}

	/**
	 * �жϴ��ʼ��Ƿ��������
	 *
	 * @param part��Part
	 * @return �Ƿ��������
	 */
	public boolean isContainAttach(Part part) throws Exception {
		boolean attachflag = false;
		if (part.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) part.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				BodyPart mpart = mp.getBodyPart(i);
				String disposition = mpart.getDisposition();
				if ((disposition != null)
						&& ((disposition.equals(Part.ATTACHMENT)) || (disposition.equals(Part.INLINE))))
					attachflag = true;
				else if (mpart.isMimeType("multipart/*")) {
					attachflag = isContainAttach((Part) mpart);
				} else {
					String contype = mpart.getContentType();
					if (contype.toLowerCase().indexOf("application") != -1)
						attachflag = true;
					if (contype.toLowerCase().indexOf("name") != -1)
						attachflag = true;
				}
			}
		} else if (part.isMimeType("message/rfc822")) {
			attachflag = isContainAttach((Part) part.getContent());
		}
		return attachflag;
	}

	/**
	 * ���渽��
	 *
	 * @param part��Part
	 * @param filePath���ʼ��������·��
	 */
	public void saveAttachMent(Part part, String filePath) throws Exception {
		String fileName = "";
		// ���渽��������������
		if (part.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) part.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				BodyPart mpart = mp.getBodyPart(i);
				String disposition = mpart.getDisposition();
				if ((disposition != null)
						&& ((disposition.equals(Part.ATTACHMENT)) || (disposition.equals(Part.INLINE)))) {
					fileName = mpart.getFileName();
					if (fileName != null) {
						fileName = MimeUtility.decodeText(fileName);
						saveFile(fileName, mpart.getInputStream(), filePath);
					}
				} else if (mpart.isMimeType("multipart/*")) {
					saveAttachMent(mpart, filePath);
				} else {
					fileName = mpart.getFileName();
					if (fileName != null) {
						fileName = MimeUtility.decodeText(fileName);
						saveFile(fileName, mpart.getInputStream(), filePath);
					}
				}
			}
		} else if (part.isMimeType("message/rfc822")) {
			saveAttachMent((Part) part.getContent(), filePath);
		}

	}

	/**
	 * ���渽����ָ��Ŀ¼��
	 *
	 * @param fileName����������
	 * @param in���ļ�������
	 * @param filePath���ʼ�������Ż�·��
	 */
	private void saveFile(String fileName, InputStream in, String filePath) throws Exception {
		File storefile = new File(filePath);
		if (!storefile.exists()) {
			storefile.mkdirs();
		}
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(filePath + "\\" + fileName));
			bis = new BufferedInputStream(in);
			int c;
			while ((c = bis.read()) != -1) {
				bos.write(c);
				bos.flush();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (bos != null) {
				bos.close();
			}
			if (bis != null) {
				bis.close();
			}
		}
	}

	public void deleteMail(Message message) throws MessagingException {
		message.setFlag(Flags.Flag.DELETED, true);
	}

	public void close() throws MessagingException {
		if (folder != null && folder.isOpen()) {
			try {
				folder.close(true);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		if (store.isConnected()) {
			try {
				store.close();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// ReciveMail mail = new ReciveMail("pop.qq.com",995,"549047879@qq.com",
		// "pop3","xapupucxxxsrbgai","");
		// ReciveMail2 mail = new ReciveMail2("pop.126.com", 995, "testbaoli", "pop3",
		// "sakura", "D:\\img\\file");
		// mail.receiveMail();
		// ReciveMail.deleteMail();
		ReciveMail2 mail2 = new ReciveMail2();
		mail2.InitStore("pop.126.com", 995, "testbaoli", "pop3", "sakura");
		Message[] messages = mail2.receiveMail();
		if (messages.length == 0) {
			System.out.println("No Mail !");
			return;
		}
		System.out.println(mail2.getFrom(messages[0]));
		mail2.deleteMail(messages[0]);
		mail2.close();
	}

}
