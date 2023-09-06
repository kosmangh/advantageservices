package com.sabonay.advantageservices.utils;

import java.io.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.jar.JarFile;

public class Settings {

    private static Settings uniqueInstance = null;
    public Properties props;
    public static Map mapp = new HashMap();

    private static final String configFileName = "supermartservices.properties";
    public static final String configPath = "/usr/app/merchantapi/";

    private Settings(String s) {
        props = null;
        //logger = //logger.getLogger(getClass().getName());
        props = new Properties();
        //myLoad(s);
        LoadPropertyFile();
    }

    private synchronized void LoadPropertyFile() {
        try {
            props.load(Settings.class.getClassLoader().getResourceAsStream(configFileName));
        } catch (Exception ex) {

        }

    }

    public static boolean SaveFileContent(String filename2, String msgx) {
        boolean success = false;
        try {
            File outFile = new File(filename2);
            success = outFile.createNewFile();
            if (success) {
                BufferedWriter out = new BufferedWriter(new FileWriter(filename2));
                out.write(msgx);
                out.close();

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return success;

    }

    public static boolean WriteLog(String msgx) {
        boolean success = false;
        try {
            System.out.println(msgx);
            ////logger.debug(msgx) ;
            //File outFile = new File("C:\\OmniFlexLog.txt");
            String osx = System.getProperty("os.name").toLowerCase();
            String filename2 = "";
            System.out.println("OSSSSSXXX::" + osx);
            if (osx.indexOf("win") >= 0) {
                filename2 = "C:\\Merchantlogs.txt";
            } else {
                filename2 = "Merchantlogs.txt"; // "/usr/app/MuleISOServer/MuleISOServer/log";
            }               //BufferedWriter out = new BufferedWriter(new FileWriter(filename2,true));
            //out.write(msgx);
            //out.close();

            PrintWriter pw = null;
            File outFile = new File(filename2);
            System.out.println(msgx);
            if (outFile.exists()) {
                pw = new PrintWriter(new FileWriter(filename2, true));
            } else {
                pw = new PrintWriter(new FileWriter(filename2));
            }
            pw.println(msgx);
            pw.flush();

            //FileOutputStream fos = new FileOutputStream(filename2, true);
            //fos.write(msgx.getBytes());
            //fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return success;

    }

    public static boolean SaveImageToFile(byte[] b) {
        boolean result = false;
        try {
            Calendar calD = Calendar.getInstance();
            Random rand = new Random();
            String filename2 = "C://" + calD.getTimeInMillis() + "_" + rand.nextInt(10000) + ".XML";
            System.out.println("File Name Incoming Response FCC: " + filename2);
            FileOutputStream fos = new FileOutputStream(filename2);
            fos.write(b);
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            Settings.WriteLog(ex.getClass() + " " + ex.getMessage());
        }
        return result;

    }

    public static String ReadFileContent(String resultFile) {
        String response = "";
        try {
            File ff = new File(resultFile);
            BufferedReader in = null;
            if (ff.isFile()) {
                in = new BufferedReader(new FileReader(ff));
                String tmp = "";
                while ((tmp = in.readLine()) != null) {
                    response = response + tmp;
                }
                in.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;
    }

    public static String filename(String fullPath) { // gets filename without extension
        int dot = fullPath.lastIndexOf(".");
        int sep = fullPath.lastIndexOf("/");
        return fullPath.substring(sep + 1, dot);
    }

    private synchronized void myLoad(String s) {
        try {
            String s1 = configFileName;
            //s = s1;
            s = null;
            /*if (s != null && !s.equals("")) {
         //logger.debug("file name not empty:" + s);
         String s2 = "D:\\" + s;
         //logger.debug("config file name:" + s2);
         FileInputStream fileinputstream = new FileInputStream(new File(s2));
         props.load(fileinputstream);
         fileinputstream.close();
         } else*/
            //logger.debug("file name empty");

            String osx = System.getProperty("os.name").toLowerCase();
            String filename2 = "";
            System.out.println("OSSSSSXXX::" + osx + "  " + configFileName);
            if (osx.indexOf("win") < 0) {
                System.out.println("LOADDDDDOSSSSSXXX::" + osx + "  " + configFileName);
                FileInputStream fileinputstream1 = new FileInputStream(new File(configFileName));
                props.load(fileinputstream1);
                fileinputstream1.close();
            } else {
                String s3 = getClass().getClassLoader().getResource("com/ecobank/util/" + configFileName).toString().replaceAll("%20", " ").replaceAll("file:/", "/");
                if (s3.charAt(2) == ':') {
                    s3 = s3.substring(1);
                }
                if (s3.startsWith("jar:")) {
                    //logger.debug("jar file");
                    int i = s3.indexOf("!/");
                    s3 = s3.substring(4, i);
                    //logger.debug("file name:" + s3);
                    JarFile jarfile = new JarFile(s3);
                    props.load(jarfile.getInputStream(jarfile.getEntry("com/ecobank/util/" + configFileName)));

                    //  jarfile.close();
                } else {
                    //logger.debug("config file name:" + s3);
                    FileInputStream fileinputstream1 = new FileInputStream(new File(s3));
                    props.load(fileinputstream1);
                    fileinputstream1.close();
                }
            }
        } catch (IOException ioexception) {
            //logger.debug("failed load properties due to : " + ioexception);
        }
    }

    public static synchronized Settings getInstance(String s) {
        if (uniqueInstance == null) {
            uniqueInstance = new Settings(s);
        }
        return uniqueInstance;
    }

    public Properties getPropertyFile() {
        return props; //.getProperty(s);
    }

    public synchronized String getProperty(String s) {
        return props.getProperty(s);
    }

    public synchronized void setProperty(String s, String s1) {
        try {
            if (s1 == null) {
                //logger.debug("Key=" + s + ", Value=" + s1);
                s1 = "Empty";
            }
            props.setProperty(s, s1);
            FileOutputStream fileoutputstream = new FileOutputStream(configFileName);
            props.store(fileoutputstream, "Settings");
        } catch (IOException ioexception) {
            //logger.debug("failed load properties due to : " + ioexception);
        }
    }

    public static String[] tokenize(String input, String delim) {
        Vector v = new Vector();
        StringTokenizer t;
        System.out.println("...TOKENIZE::" + input + "    " + delim);
        if (delim.equals("default")) {
            t = new StringTokenizer(input);
        } else {
            t = new StringTokenizer(input, delim);
        }
        for (; t.hasMoreTokens(); v.addElement(t.nextToken()));
        String cmd[] = new String[v.size()];
        for (int i = 0; i < cmd.length; i++) {
            cmd[i] = (String) v.elementAt(i);
            System.out.println("...TOKENIZE CMD::" + cmd[i]);
        }

        return cmd;
    }

    public static void SetSSLCertSetting() {
        String sslClientFile = Settings.getInstance("").getProperty("JDK_SSLFILE");
        String sslClientPwd = Settings.getInstance("").getProperty("JDK_SSLPWD");

        System.setProperty("javax.net.ssl.trustStore", sslClientFile);
        System.setProperty("javax.net.ssl.trustStorePassword", sslClientPwd);

        System.setProperty("javax.net.ssl.keyStore", sslClientFile);
        System.setProperty("javax.net.ssl.keyStorePassword", sslClientPwd);
        //System.setProperty("javax.net.debug", "all");
        System.setProperty("https.protocols", "TLSv1.2"); //,TLSv1,SSLv3");    	

    }
}
