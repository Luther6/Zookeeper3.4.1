package com.luther.operation;

import org.apache.kerby.util.Base64;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Sha1 {
    public static int hello(String s1){

        return 1;
    }

    public static void main(String[] args) throws Exception {
 /*       String userNameAndPassword = "anacr:qwe123";
        MessageDigest sha1 = MessageDigest.getInstance("SHA1");
        byte[] digest = sha1.digest(userNameAndPassword.getBytes());
        Base64 base64 = new Base64();
        String s = base64.encodeToString(digest);
        System.out.println(s);
        System.out.println(1 << 0);*/
        int a = 0;
        try {
            if(hello("1")==1) {
                throw new Exception("hello");
            }
        }catch (IOException e){
            System.out.println("----");
        }
    }
}
