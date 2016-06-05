package com.jun.mail;

import java.security.MessageDigest;

public class MD5Util {
	private static byte[] encode2bytes(String source){
		byte[] result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(source.getBytes("utf-8"));
			result=md.digest();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public static String encode2hex(String source){
		byte[] data= encode2bytes(source);
		StringBuffer hexString = new StringBuffer();
		for(int i=0;i<data.length;i++){
			String hex = Integer.toHexString(0xff&data[i]);
			
			if(hex.length()==1){
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}
	
	public static boolean validate(String unknown,String okHex){
		return okHex.equals(encode2hex(unknown));
	}
}
