package hash;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

public class RSA_Hash {
	public static void main(String[] args) throws Exception
	{
		BigInteger nA = new BigInteger ("171024704183616109700818066925197841516671277");
		BigInteger eA = new BigInteger ("1571");
		BigInteger pB = new BigInteger ("98763457697834568934613");
		BigInteger qB = new BigInteger ("8495789457893457345793");
		BigInteger eB = new BigInteger ("87697");
		
		BigInteger nB = pB.multiply(qB);
		BigInteger phiB = pB.subtract(BigInteger.ONE).multiply(pB.subtract(BigInteger.ONE));
		BigInteger dB = eB.modInverse(phiB);
		
		BigInteger m = new BigInteger ("418726553997094258577980055061305150940547956");
		BigInteger s = new BigInteger ("749142649641548101520133634736865752883277237");
		
//		byte[] ct = m.toByteArray();
		BigInteger bk = m.modPow(dB, nB);
		bk = bk.modPow(eA, nA);
//		KeyFactory keyFactory = KeyFactory.getInstance("RSA");		
//		RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(nB, eB);
//		RSAPublicKeySpec pubSpecA = new RSAPublicKeySpec(nA, eA);
//		RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(nB, dB);
//		PublicKey pub = keyFactory.generatePublic(pubSpec);
//		PublicKey pubA = keyFactory.generatePublic(pubSpecA);
//		PrivateKey priv = keyFactory.generatePrivate(privSpec);
//		Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
//		cipher.init(Cipher.DECRYPT_MODE, priv);
//		byte[] pt = cipher.doFinal(ct);
//		cipher.init(Cipher.DECRYPT_MODE, pubA);
//		byte[] bk = cipher.doFinal(pt);
		
//		System.out.println(new String(bk));
		System.out.println(new String(bk.toByteArray()));
	}
}
