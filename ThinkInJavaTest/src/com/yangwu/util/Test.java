package com.yangwu.util;
/** 

��õ�ǰ�������·�������һ�� 
 
*/  
  
   
  
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;  
  
   
  
/** 
 
* 
 
java.version          Java ����ʱ�����汾 
 
java.vendor         Java ����ʱ������Ӧ�� 
 
java.vendor.url         Java ��Ӧ�̵� URL 
 
java.vm.specification.version         Java ������淶�汾 
 
java.vm.specification.vendor         Java ������淶��Ӧ�� 
 
java.vm.specification.name         Java ������淶���� 
 
java.vm.version         Java �����ʵ�ְ汾 
 
java.vm.vendor         Java �����ʵ�ֹ�Ӧ�� 
 
java.vm.name         Java �����ʵ������ 
 
java.specification.version         Java ����ʱ�����淶�汾 
 
java.specification.vendor         Java ����ʱ�����淶��Ӧ�� 
 
java.specification.name         Java ����ʱ�����淶���� 
 
os.name         ����ϵͳ������ 
 
os.arch         ����ϵͳ�ļܹ� 
 
os.version         ����ϵͳ�İ汾 
 
file.separator         �ļ��ָ������� UNIX ϵͳ���ǡ� / ���� 
 
path.separator         ·���ָ������� UNIX ϵͳ���ǡ� : ���� 
 
line.separator         �зָ������� UNIX ϵͳ���ǡ� /n ���� 
 
  
 
java.home         Java ��װĿ¼ 
 
java.class.version         Java ���ʽ�汾�� 
 
java.class.path         Java ��·�� 
 
java.library.path          ���ؿ�ʱ������·���б� 
 
java.io.tmpdir         Ĭ�ϵ���ʱ�ļ�·�� 
 
java.compiler         Ҫʹ�õ� JIT ������������ 
 
java.ext.dirs         һ��������չĿ¼��·�� 
 
user.name         �û����˻����� 
 
user.home         �û�����Ŀ¼ 
 
user.dir 
 
*/  
  
public class Test {  
  
        public static void main(String[] args) throws MalformedURLException, URISyntaxException {  
  
                System.out.println("java.home : "+System.getProperty("java.home"));  
  
                System.out.println("java.class.version : "+System.getProperty("java.class.version"));  
  
                System.out.println("java.class.path : "+System.getProperty("java.class.path"));  
  
                System.out.println("java.library.path : "+System.getProperty("java.library.path"));  
  
                System.out.println("java.io.tmpdir : "+System.getProperty("java.io.tmpdir"));  
  
                System.out.println("java.compiler : "+System.getProperty("java.compiler"));  
  
                System.out.println("java.ext.dirs : "+System.getProperty("java.ext.dirs"));  
  
                System.out.println("user.name : "+System.getProperty("user.name"));  
  
                System.out.println("user.home : "+System.getProperty("user.home"));  
  
                System.out.println("user.dir : "+System.getProperty("user.dir"));  
  
                System.out.println("===================");  
  
                System.out.println("package: "+Test.class.getPackage().getName());  
  
                System.out.println("package: "+Test.class.getPackage().toString());  
  
                System.out.println("=========================");  
  
                String packName = Test.class.getPackage().getName();  
  
                /*URL packurl = new URL(packName); 
 
                System.out.println(packurl.getPath());*/  
  
                URI packuri = new URI(packName);  
  
                System.out.println(packuri.getPath());  
  
                //System.out.println(packuri.toURL().getPath());  
  
                System.out.println(packName.replaceAll("//.", "/"));  
  
                System.out.println(System.getProperty("user.dir")+"/"+(Test.class.getPackage().getName()).replaceAll("//.", "/")+"/");  
  
        }  
  
}   