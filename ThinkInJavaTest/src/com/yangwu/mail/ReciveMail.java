package com.yangwu.mail;

import com.sun.mail.pop3.POP3Message;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class ReciveMail {

    private String host;

    private int port;

    private String address;

    private String type;

    private String password;

    private  String filepath;

   public static  void main(String[] args){
//       ReciveMail  mail = new ReciveMail("pop.qq.com",995,"549047879@qq.com",
//               "pop3","xapupucxxxsrbgai","");
       ReciveMail mail = new ReciveMail("pop.126.com",995,"testbaoli","pop3","sakura","D:\\img\\file");
       mail.receiveMail();
//       ReciveMail.deleteMail();
   }

    public ReciveMail(String host,int port,String address,String type,String password,String filepath){
        this.host = host;
        this.port = port;
        this.address = address;
        this.type = type;
        this.password = password;
        this.filepath = filepath;
    }

    /**
     * ��ȡsession�Ự�ķ���
     *
     * @return
     * @throws Exception
     */
    private Session getSessionMail() throws Exception {
        Properties properties = System.getProperties();
//        properties.put("mail.smtp.host", host);
//        properties.put("mail.smtp.auth", true);
        properties.setProperty("mail.pop3.host", host);
        properties.setProperty("mail.pop3.port", String.valueOf(port));
        // SSL��ȫ���Ӳ���
        properties.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.pop3.socketFactory.fallback", "true");
        properties.setProperty("mail.pop3.socketFactory.port", String.valueOf(port));
        Session sessionMail = Session.getDefaultInstance(properties, null);
        return sessionMail;
    }

    /**
     * �����ʼ�
     *
     * @param //������û���������
     * @return ��
     */
    public void receiveMail() {
        Store store = null;
        Folder folder = null;
        int messageCount = 0;
        URLName urln = null;
        try {
            //�����û���������
//            urln = new URLName(type, host, port, null, userName, passWord);
//            store = getSessionMail().getStore(urln);
            store = getSessionMail().getStore(type);
            store.connect(host,address,password);
            //��������ڵ��ʼ���Folder������"ֻ��"��
            folder = store.getFolder("INBOX");//���ռ���
            folder.open(Folder.READ_WRITE);
            //����ʼ���Folder�ڵ������ʼ�����
            messageCount = folder.getMessageCount();// ��ȡ�����ʼ�����
            //��ȡ���ʼ�����
            System.out.println("============>>�ʼ�������" + messageCount);
            if (messageCount > 0) {
                Message[] messages = folder.getMessages(1, messageCount);//��ȡ�����һ���ʼ�
                for (int i = 0; i < messages.length; i++) {
                    String content = getMailContent((Part) messages[i]);//��ȡ����
                    if (isContainAttach((Part) messages[i])) {
                        saveAttachMent((Part) messages[i], filepath);
                    }
                    System.out.println("=====================>>��ʼ��ʾ�ʼ�����<<=====================");
                    System.out.println("������: " + getFrom(messages[i]));
                    System.out.println("����: " + getSubject(messages[i]));
                    System.out.println("����: " + content);
                    System.out.println("����ʱ��: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(((MimeMessage) messages[i]).getSentDate()));
                    System.out.println("�Ƿ��и���: " + (isContainAttach((Part) messages[i]) ? "�и���" : "�޸���"));
                    System.out.println("=====================>>������ʾ�ʼ�����<<=====================");
                    ((POP3Message) messages[i]).invalidate(true);
//                    messages[i].setFlag(Flags.Flag.DELETED, true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
    }

    /**
     * ��÷����˵ĵ�ַ
     *
     * @param message��Message
     * @return �����˵ĵ�ַ
     */
    private String getFrom(Message message) throws Exception {
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
    private String getSubject(Message message) throws Exception {
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
    private String getMailContent(Part part) throws Exception {
        StringBuffer bodytext = new StringBuffer();//����ʼ�����
        //�ж��ʼ�����,��ͬ���Ͳ�����ͬ
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
//        System.out.println(bodytext.toString());
        return bodytext.toString();
    }

    /**
     * �жϴ��ʼ��Ƿ��������
     *
     * @param part��Part
     * @return �Ƿ��������
     */
    private boolean isContainAttach(Part part) throws Exception {
        boolean attachflag = false;
        if (part.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) part.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                BodyPart mpart = mp.getBodyPart(i);
                String disposition = mpart.getDisposition();
                if ((disposition != null) && ((disposition.equals(Part.ATTACHMENT)) || (disposition.equals(Part.INLINE))))
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
    private void saveAttachMent(Part part, String filePath) throws Exception {
        String fileName = "";
        //���渽��������������
        if (part.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) part.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                BodyPart mpart = mp.getBodyPart(i);
                String disposition = mpart.getDisposition();
                if ((disposition != null) && ((disposition.equals(Part.ATTACHMENT)) || (disposition.equals(Part.INLINE)))) {
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

    public static void deleteMail(Message message) throws MessagingException {
        message.setFlag(Flags.Flag.DELETED, true);
    }
}
