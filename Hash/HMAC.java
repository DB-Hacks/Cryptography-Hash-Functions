package Hash;

import java.security.MessageDigest;
import Utility.CryptoTools;

public class HMAC {
	public static void main(String[] args) throws Exception
	{
		byte[] ky = ("This is an ultra-secret key").getBytes();
		byte[] m = ("Mainly cloudy with 40 percent chance of showers").getBytes();
		
		MessageDigest md = MessageDigest.getInstance("SHA1");
		
		int block = 64;
		
		if(ky.length > block) {
			ky = md.digest(ky);
		}
		if(ky.length < block) {
			int pad = block - ky.length;
			for(int i = 0; i<pad; i++) {
				ky = CryptoTools.hexToBytes(CryptoTools.bytesToHex(ky) + CryptoTools.bytesToHex(("0").getBytes()));
			}
		}
		
		byte[] ipad = new byte[block];
		byte[] opad = new byte[block];
		for (int i = 0; i < block; i++) {
			ipad[i] = 0x36;
			opad[i] = 0x5C;
		}

		byte[] kIpadXorResult = new byte[block];
		for (int i = 0; i < block; i++) {
			kIpadXorResult[i] = (byte) (ky[i] ^ ipad[i]);
		}
		
		byte[] kOpadXorResult = new byte[block];
		for (int i = 0; i < block; i++) {
			kOpadXorResult[i] = (byte) (ky[i] ^ opad[i]);
		}
		byte[] hash1 = md.digest(CryptoTools.hexToBytes(CryptoTools.bytesToHex(ipad) + CryptoTools.bytesToHex(m)));
		byte[] hash2 = md.digest(CryptoTools.hexToBytes(CryptoTools.bytesToHex(opad) + CryptoTools.bytesToHex(hash1)));
		
		System.out.println("HMAC = " + CryptoTools.bytesToHex(hash2));		//Test Case: 928844A5052EFBC4EFC61FB6C3755019BD7D6492
	}
}
