package hash;

import java.security.MessageDigest;

import util.CryptoTools;

public class HMAC {
	public static void main(String[] args) throws Exception
	{
		byte[] ky = ("This is an ultra-secret key").getBytes();
		byte[] m = ("Mainly cloudy with 40 percent chance of showers").getBytes();
		
		MessageDigest md = MessageDigest.getInstance("SHA1");
		
		int block = 64;
		int output = 20;
		
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
//		for (int i = 0; i < block; i++) {
//			ipad[i] = 0x5C;
//			opad[i] = 0x36;
//		}
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
		
		System.out.println(CryptoTools.bytesToHex(hash2));
		
		//regular:  928844A5052EFBC4EFC61FB6C3755019BD7D6492
		//reversed: 21845A48484E3311414EE8472724275BBC4EC534
	}
}
