package Hash;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;
import Utility.CryptoTools;

public class RSA_512SHA2 {
	public static void main(String[] args) throws Exception
	{
		BigInteger n = new BigInteger("94587468335128982981605019776781234618384857805657005686084562260910788622013722070926491690843853690071248130134427832324966728582532832363221542231787068203763027067400082835394459857525017707284768411819006776211493735326500782954621660256501187035611332577696332459049538105669711385995976912007767106063");
		BigInteger e = new BigInteger("74327");
		BigInteger d = new BigInteger("7289370196881601766768920490284861650464951706793000236386405648425161747775298344104658393385359209126267833888223695609366844098655240542152017354442883676634193191857568369042999854440242050353181703706753485749165295123694487676952198090537385200990850805837963871485320168470788328336240930212290450023");
		byte[] pt = ("Meet me at 5 pm tomorrow").getBytes();
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte[] hash = md.digest(pt);
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(n, e);
		RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(n, d);
		PublicKey pub = keyFactory.generatePublic(pubSpec);
		PrivateKey priv = keyFactory.generatePrivate(privSpec);
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		
		cipher.init(Cipher.ENCRYPT_MODE, priv);
		byte[] ct = cipher.doFinal(hash);
		System.out.println(CryptoTools.bytesToHex(ct));		//Test Case: 6E35DFF59080D683C34C548F8A5E0B9077DBB709F96D7FBDEEF90A5134C7F6AE06A49C57A81C834CBA773A0A441DDFB91D50BADF03E86C56A713E9B37589DC7C099734CC4BC77A3CAAC929300AB92E2FAB468A57B4A70F782A6058D3795723DA25C42B4CA48E13DEE91D2823653E1A54BD23D8AE25C7AD3C64DF4BCC2CB8C474
	}
}
