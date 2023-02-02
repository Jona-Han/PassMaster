package encryption;

import model.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Encryption {
    public static void encryptAccount(Account account) throws InvalidAlgorithmParameterException,
            NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException,
            NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String username = account.getUsername();
        String password = account.getPassword();
        SecretKey key = EncryptionUtil.generateSecretKey(password, account.getSalt());
        IvParameterSpec ivSpec = new IvParameterSpec(account.getIV());
        account.setUsername(EncryptionUtil.encrypt(username, key, ivSpec));
        account.setPassword(EncryptionUtil.encrypt(password, key, ivSpec));
    }

    public static String decryptUsername(Account account) throws InvalidAlgorithmParameterException,
            NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException,
            InvalidKeyException {
        String username = account.getUsername();
        String password = account.getPassword();
        SecretKey key = EncryptionUtil.generateSecretKey(password, account.getSalt());
        IvParameterSpec ivSpec = new IvParameterSpec(account.getIV());
        return EncryptionUtil.decrypt(username, key, ivSpec);

    }

    public static String decryptPassword(Account account) throws InvalidAlgorithmParameterException,
            NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException,
            InvalidKeyException {
        String username = account.getUsername();
        String password = account.getPassword();
        SecretKey key = EncryptionUtil.generateSecretKey(password, account.getSalt());
        IvParameterSpec ivSpec = new IvParameterSpec(account.getIV());
        return EncryptionUtil.decrypt(password, key, ivSpec);
    }
}
