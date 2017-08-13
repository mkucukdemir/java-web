/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smallfe.clerk.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/**
 * Write short comment related with created class
 * @author mkucukdemir
 */
public class DigestUtil {
    public static String getSHA256(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(message.getBytes());
            
            byte byteData[] = md.digest();
            
            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DigestUtil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    private static String decryptionAlphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    
    public static String encryptEGM(String plainText){
        StringBuilder cypherText = new StringBuilder();
        try {
            plainText = StringUtils.rightPad(plainText, ((plainText.length() - 1) / 3 + 1) * 3, (char)0);
        } catch (NullPointerException ex) {
            Logger.getLogger(DigestUtil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        for (int i = 0; i < plainText.length(); i+=3) {
            byte[] partitions = plainText.substring(i, i+3).getBytes(Charset.forName("iso-8859-9"));
            int c1 = (int)(partitions[0]);
            if(c1<0) c1 += 256;
            int c2 = (int)(partitions[1]);
            if(c2<0) c2 += 256;
            int c3 = (int)(partitions[2]);
            if(c3<0) c3 += 256;

            int k1 = (c1) / 4;
            int k2 = (c1) % 4;
            int k3 = (c2) / 16;
            int k4 = (c2) % 16;
            int k5 = (c3) / 64;
            int k6 = (c3) % 64;

            char ec1 = decryptionAlphabet.charAt(k1);
            char ec2 = decryptionAlphabet.charAt(k3 + k2 * 16);
            char ec3 = decryptionAlphabet.charAt(k5 + k4 * 4);
            char ec4 = decryptionAlphabet.charAt(k6);
            
            cypherText.append(ec1);
            cypherText.append(ec2);
            if (c2 != 0) cypherText.append(ec3);
            if (c3 != 0) cypherText.append(ec4);
        }
        return cypherText.toString();
    }
    public static String decryptEGM(String cypherText){
        StringBuilder plainText = new StringBuilder();
        try {
            cypherText = StringUtils.rightPad(cypherText, ((cypherText.length() - 1) / 4 + 1) * 4, (char)0);
        } catch (NullPointerException ex) {
            Logger.getLogger(DigestUtil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        for (int i = 0; i < cypherText.length(); i+=4) {
            String partition = cypherText.substring(i, i+4);
            int c1 = decryptionAlphabet.indexOf(partition.charAt(0));
            int c2 = decryptionAlphabet.indexOf(partition.charAt(1)) + (partition.charAt(0) == '/' ? 64 : 0);
            int c3 = decryptionAlphabet.indexOf(partition.charAt(2)) + (partition.charAt(1) == '/' ? 64 : 0);
            int c4 = decryptionAlphabet.indexOf(partition.charAt(3)) + (partition.charAt(2) == '/' ? 64 : 0);
            
            int r1 = (c2 * 16) / 255;
            int r2 = (c3 * 64) / 255;
            
            char dc1 = (char)(c1 * 4 + r1);
            char dc2 = (char)((c2 * 16 + r2) % 256);
            int r3 = Math.min(254,(c3 - dc2 % 16 * 4) % 64 * 64 + c4);
            char dc3 = c4 != -1 ? (char)r3 : (char)0;
            plainText.append((dc1));
            if (c3 != -1) plainText.append((dc2));
            if (c4 != -1) plainText.append((dc3));
        }
        return plainText.toString().toUpperCase().replace('Ý', '#').replace('Þ', '$').replace('Ð', '£').replace('ý', 'I').replace('ä', 'Ç');
    }
}
