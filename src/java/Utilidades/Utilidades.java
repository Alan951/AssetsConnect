/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author jorge
 */
public class Utilidades {
     public static String cifrarDatos(String dato){
        byte[] digest = null;
        byte[] buffer = dato.getBytes();
        
        try{
            MessageDigest mensajeDigest = MessageDigest.getInstance("MD5");
            mensajeDigest.reset();
            mensajeDigest.update(buffer);
            digest = mensajeDigest.digest();
        }catch(NoSuchAlgorithmException nsae){
            nsae.printStackTrace();
        }
        
        return aHexadecimal(digest);
    }
    
    //Este metodo es necesario para cifrarDatos
    public static String aHexadecimal(byte[] digest){
        String hash = "";
        for(byte aux : digest){
            int b = aux & 0xff;
            if(Integer.toHexString(b).length() == 1){
                hash += "0";
            } 
            hash += Integer.toHexString(b);
        }
        return hash;
    }
}
