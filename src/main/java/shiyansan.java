import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class shiyansan {
    // 选择明文和密钥
    static byte[] plaintext = { 0x40, 0x5C, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 }; // 明文
    static byte[] key = {
            (byte) 0x01, (byte) 0x23, (byte) 0x45, (byte) 0x67,
            (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF
    }; // 密钥

    // 选择输入差分
    static byte[] inputDiff = { (byte) 0x40, (byte) 0x5C, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };

    // DES算法实现
    private static byte[] des(byte[] input, byte[] key, int mode) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // 创建密钥Spec对象
        SecretKeySpec keySpec = new SecretKeySpec(key, "DES");
        // 创建 Cipher 对象
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        // 初始化 Cipher 对象
        cipher.init(mode, keySpec);
        // 执行加密或解密操作
        return cipher.doFinal(input);
    }

    // 计算信噪比
    private static double calculateSNR(byte[] plaintext, byte[] ciphertext, byte[] inputDiff) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        // 计算预期的差分
        byte[] expectedDiff = des(inputDiff, key, Cipher.ENCRYPT_MODE);
        // 计算实际的差分
        byte[] actualDiff = new byte[inputDiff.length];
        for (int i = 0; i < inputDiff.length; i++) {
            actualDiff[i] = (byte) (plaintext[i] ^ ciphertext[i]);
        }
        // 计算汉明权重
        int hammingWeight = 0;
        for (int i = 0; i < inputDiff.length; i++) {
            if (expectedDiff[i]!= actualDiff[i]) {
                hammingWeight++;
            }
        }
        // 计算信噪比
        return (double) hammingWeight / inputDiff.length;
    }

    // 过滤密文
    private static boolean filterCiphertext(byte[] ciphertext, double threshold) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        // 计算信噪比
        double snr = calculateSNR(plaintext, ciphertext, inputDiff);
        // 判断信噪比是否大于阈值
        return snr > threshold;
    }

    // 计数器计算
    private static int[] countKeyBits(byte[][] ciphertexts) {
        // 创建计数器数组
        int[] keyCounts = new int[64]; // 64位密钥
        // 遍历每个密文
        for (byte[] ciphertext : ciphertexts) {
            // 遍历每个比特位
            for (int i = 0; i < 64; i++) {
                // 计算比特位的值
                keyCounts[i] += (ciphertext[i / 8] >> (i % 8)) & 1;
            }
        }
        return keyCounts;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // 加密明文
        byte[] ciphertext = des(plaintext, key, Cipher.ENCRYPT_MODE);

        // 计算信噪比
        double snr = calculateSNR(plaintext, ciphertext, inputDiff);
        System.out.println("SNR: " + snr);

        // 过滤密文
        if (filterCiphertext(ciphertext, 0.2)) { // 设置阈值
            // 密文通过过滤，进行计数器计算
            byte[][] ciphertexts = { ciphertext };
            int[] keyCounts = countKeyBits(ciphertexts);
            System.out.println("key:");
            for (int i = 0; i < keyCounts.length; i++) {
                System.out.print(keyCounts[i]);
            }
        } else {
            System.out.println("Ciphertext filtered out.");
        }
    }
}