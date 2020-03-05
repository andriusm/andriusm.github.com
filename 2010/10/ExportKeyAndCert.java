import java.io.*;
import java.security.*;
import java.security.cert.Certificate;

public class ExportKeyAndCert {

    private static class Base64 {
        static final byte[] encodeData;
        static final String charSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

        static {
            encodeData = new byte[64];
            for (int i = 0; i < 64; i++) {
                byte c = (byte) charSet.charAt(i);
                encodeData[i] = c;
            }
        }

        private Base64() {}

        public static String encode(byte[] src) {
            int length = src.length;
            byte[] dst = new byte[(length + 2) / 3 * 4 + length / 57];
            int x = 0, len = 0, old = 0, state = 0, dstIndex = 0;
            
            for (int srcIndex = 0; srcIndex < length; srcIndex++) {
                x = src[srcIndex];
                switch (++state) {
                    case 1:
                        dst[dstIndex++] = encodeData[(x >> 2) & 0x3f];
                        break;
                    case 2:
                        dst[dstIndex++] = encodeData[((old << 4) & 0x30) | ((x >> 4) & 0xf)];
                        break;
                    case 3:
                        dst[dstIndex++] = encodeData[((old << 2) & 0x3C) | ((x >> 6) & 0x3)];
                        dst[dstIndex++] = encodeData[x & 0x3F];
                        state = 0;
                        break;
                }
                old = x;
                if (++len >= 57) {
                    dst[dstIndex++] = (byte) '\n';
                    len = 0;
                }
            }
            switch (state) {
                case 1:
                    dst[dstIndex++] = encodeData[(old << 4) & 0x30];
                    dst[dstIndex++] = (byte) '=';
                    dst[dstIndex++] = (byte) '=';
                    break;
                case 2:
                    dst[dstIndex++] = encodeData[(old << 2) & 0x3c];
                    dst[dstIndex++] = (byte) '=';
                    break;
            }
            return new String(dst);
        }
    }

    static public void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java ExportKeyAndCert keyStore keyAlias");
            System.exit(0);
        }
        try {
            String keyStore = args[0];
            char[] storePass = System.console().readPassword("Enter keystore password: ");
            String keyAlias = args[1];
            char[] keyPass = System.console().readPassword("Enter key password: ");

            KeyStore ks = KeyStore.getInstance("jks");
            ks.load(new FileInputStream(keyStore), storePass);

            Certificate cert = ks.getCertificate(keyAlias);
            String b64 = Base64.encode(cert.getEncoded());

            PrintWriter fout = new PrintWriter("public.cer");
            fout.println("-----BEGIN CERTIFICATE-----");
            fout.println(b64);
            fout.println("-----END CERTIFICATE-----");
            fout.close();

            Key key = ks.getKey(keyAlias, keyPass);
            b64 = Base64.encode(key.getEncoded());

            fout = new PrintWriter("private.key");
            fout.println("-----BEGIN PRIVATE KEY-----");
            fout.println(b64);
            fout.println("-----END PRIVATE KEY-----");
            fout.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}