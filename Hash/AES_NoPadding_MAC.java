package hash;

import java.security.Key;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class AES_NoPadding_MAC {
	public static void main(String[] args) throws Exception
	{
		byte[] pt = ("No one can make you feel inferior without your consent.").getBytes();
		byte[] ky = ("structureRepubli").getBytes();
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] hash = md.digest(pt);
		Key secret = new SecretKeySpec(ky, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		byte[] ct = cipher.doFinal(hash);
		
		System.out.println("MAC = " + CryptoTools.bytesToHex(ct));
		
		//C1B5344759C4F2DD0D415A8064CA5DA8
	}
}
