package com.github.moonkin63.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/**
 * Created by Moonkin63 on 20.04.2015.
 */
public class FileUtils {

    /**
     * Calculates MD5 hash of specified file
     *
     * @param file
     * @return MD5 hash of specified file of empty string in case of NoSuchAlgorithmException or
     * IOException
     * @throws FileNotFoundException
     */
    public static String getMD5(File file) throws FileNotFoundException {
        try {
            FileInputStream fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] dataBytes = new byte[1024];

            int nread = 0;
            while ((nread = fis.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nread);
            }
            byte[] mdbytes = md.digest();

            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < mdbytes.length; i++) {
                sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Calculates paths to folder that contains jar file
     *
     * @return
     */
    public static String getCurrentJARPath() {
        String path = FileUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String currentPath = new File(path).getAbsolutePath();
        String patter = Pattern.quote(File.separator);
        String[] pathParts = currentPath.split(patter);
        StringBuffer buffer = new StringBuffer("");
        for (int i = 0; i < pathParts.length - 1; i++) {
            buffer.append(pathParts[i] + File.separator);
        }
        return buffer.toString();
    }
}
