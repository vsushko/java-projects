package vsushko.crypto;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class LinkToMD5ToBase64 {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String password = "some string";

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        System.out.println(myHash);

        String originalInput = "www.google.ru/find?q=generate+unique+strings+using+base64&oq=generate+unique+strings" +
                "+using+base64&aqs=chrome..69i57.10446j0j1&client=ubuntu&sourceid=chrome&ie=UTF-8";
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        System.out.println(encodedString);

        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String decodedString = new String(decodedBytes);
        System.out.println(decodedString);
    }
}
