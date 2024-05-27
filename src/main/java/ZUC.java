import java.util.Arrays;

public class ZUC {
    private static final int[] T = new int[256];
    private static final int[] S = new int[256];
    private static int LFSR_S0;
    private static int LFSR_S1;
    private static int LFSR_S2;
    private static int LFSR_S3;
    private static int LFSR_S4;
    private static int LFSR_S5;
    private static int LFSR_S6;
    private static int LFSR_S7;
    private static int LFSR_S8;
    private static int LFSR_S9;
    private static int LFSR_S10;
    private static int LFSR_S11;
    private static int LFSR_S12;
    private static int LFSR_S13;
    private static int LFSR_S14;
    private static int LFSR_S15;
    private static int LFSR_S16;
    private static int LFSR_S17;
    private static int LFSR_S18;
    private static int LFSR_S19;
    private static int LFSR_S20;
    private static int LFSR_S21;
    private static int LFSR_S22;
    private static int LFSR_S23;
    private static int LFSR_S24;
    private static int LFSR_S25;
    private static int LFSR_S26;
    private static int LFSR_S27;
    private static int LFSR_S28;
    private static int LFSR_S29;
    private static int LFSR_S30;
    private static int LFSR_S31;
    private static int LFSR_S32;
    private static int LFSR_S33;
    private static int LFSR_S34;
    private static int LFSR_S35;
    private static int LFSR_S36;
    private static int LFSR_S37;
    private static int LFSR_S38;
    private static int LFSR_S39;

    private static void ZUC_Init() {
        // 初始化T表和S盒
        for (int i = 0; i < 256; i++) {
            T[i] = 0;
            S[i] = 0;
        }

        // 初始化LFSR寄存器
        LFSR_S0 = 0x01;
        LFSR_S1 = 0x01;
        LFSR_S2 = 0x01;
        LFSR_S3 = 0x01;
        LFSR_S4 = 0x01;
        LFSR_S5 = 0x01;
        LFSR_S6 = 0x01;
        LFSR_S7 = 0x01;
        LFSR_S8 = 0x01;
        LFSR_S9 = 0x01;
        LFSR_S10 = 0x01;
        LFSR_S11 = 0x01;
        LFSR_S12 = 0x01;
        LFSR_S13 = 0x01;
        LFSR_S14 = 0x01;
        LFSR_S15 = 0x01;
        LFSR_S16 = 0x01;
        LFSR_S17 = 0x01;
        LFSR_S18 = 0x01;
        LFSR_S19 = 0x01;
        LFSR_S20 = 0x01;
        LFSR_S21 = 0x01;
        LFSR_S22 = 0x01;
        LFSR_S23 = 0x01;
        LFSR_S24 = 0x01;
        LFSR_S25 = 0x01;
        LFSR_S26 = 0x01;
        LFSR_S27 = 0x01;
        LFSR_S28 = 0x01;
        LFSR_S29 = 0x01;
        LFSR_S30 = 0x01;
        LFSR_S31 = 0x01;
        LFSR_S32 = 0x01;
        LFSR_S33 = 0x01;
        LFSR_S34 = 0x01;
        LFSR_S35 = 0x01;
        LFSR_S36 = 0x01;
        LFSR_S37 = 0x01;
        LFSR_S38 = 0x01;
        LFSR_S39 = 0x01;
    }

    private static void ZUC_KeySet(byte[] key, byte[] iv) {
        int[] LFSR = new int[16];
        for (int i = 0; i < 16; i++) {
            LFSR[i] = 0;
        }

        for (int i = 0; i < 16; i++) {
            LFSR[i] = (key[i] & 0xFF) | ((iv[i] & 0xFF) << 8);
        }

        for (int i = 0; i < 32; i++) {
            ZUC_LFSRWithInitMode(LFSR);
        }

        for (int i = 0; i < 32; i++) {
            ZUC_BitReorganization(LFSR);
        }
    }

    private static void ZUC_LFSRWithInitMode(int[] LFSR) {
        int b0 = (LFSR[0] << 13) ^ LFSR[0];
        int b1 = (LFSR[2] << 3) ^ LFSR[2];
        int b2 = (LFSR[15] << 27) ^ LFSR[15];
        LFSR[0] = LFSR[1];
        LFSR[1] = LFSR[2];
        LFSR[2] = LFSR[3];
        LFSR[3] = LFSR[4];
        LFSR[4] = LFSR[5];
        LFSR[5] = LFSR[6];
        LFSR[6] = LFSR[7];
        LFSR[7] = LFSR[8];
        LFSR[8] = LFSR[9];
        LFSR[9] = LFSR[10];
        LFSR[10] = LFSR[11];
        LFSR[11] = LFSR[12];
        LFSR[12] = LFSR[13];
        LFSR[13] = LFSR[14];
        LFSR[14] = LFSR[15];
        LFSR[15] = b0 ^ b1 ^ b2;
    }

    private static void ZUC_BitReorganization(int[] LFSR) {
        int B0 = ((LFSR[15] & 0x7FFF8000) << 1) | (LFSR[14] & 0xFFFF);
        int B1 = ((LFSR[11] & 0xFFFF) << 16) | ((LFSR[9] & 0xFFFF) << 1) | (LFSR[7] & 0xFFFF);
        int B2 = ((LFSR[5] & 0xFFFF) << 16) | ((LFSR[3] & 0xFFFF) << 1) | (LFSR[1] & 0xFFFF);
        int B3 = ((LFSR[12] & 0xFFFF) << 16) | ((LFSR[10] & 0xFFFF) << 1) | (LFSR[8] & 0xFFFF);
        int B4 = ((LFSR[6] & 0xFFFF) << 16) | ((LFSR[4] & 0xFFFF) << 1) | (LFSR[2] & 0xFFFF);
        int B5 = ((LFSR[13] & 0xFFFF) << 16) | ((LFSR[15] & 0xFFFF) << 1) | (LFSR[0] & 0xFFFF);

        S[0] = B0;
        S[1] = B1;
        S[2] = B2;
        S[3] = B3;
        S[4] = B4;
        S[5] = B5;

        for (int i = 0; i < 256; i++) {
            int j = i % 6;
            int k = i / 6;
            T[i] = (S[j] >>> k) & 1;
        }
    }

    private static int ZUC_GetBit() {
        int z = T[0] ^ T[1] ^ T[2] ^ T[3] ^ T[4] ^ T[5] ^ T[6] ^ T[7] ^ T[8] ^ T[9] ^ T[10] ^ T[11] ^ T[12] ^ T[13] ^ T[14] ^ T[15] ^ T[16] ^ T[17] ^ T[18] ^ T[19] ^ T[20] ^ T[21] ^ T[22] ^ T[23] ^ T[24] ^ T[25] ^ T[26] ^ T[27] ^ T[28] ^ T[29] ^ T[30] ^ T[31] ^ T[32] ^ T[33] ^ T[34] ^ T[35] ^ T[36] ^ T[37] ^ T[38] ^ T[39] ^ T[40] ^ T[41] ^ T[42] ^ T[43] ^ T[44] ^ T[45] ^ T[46] ^ T[47] ^ T[48] ^ T[49] ^ T[50] ^ T[51] ^ T[52] ^ T[53] ^ T[54] ^ T[55] ^ T[56] ^ T[57] ^ T[58] ^ T[59] ^ T[60] ^ T[61] ^ T[62] ^ T[63];

        int z0 = T[15] ^ T[31] ^ T[45] ^ T[59];
        int z1 = T[2] ^ T[16] ^ T[32] ^ T[46] ^ T[60];
        int z2 = T[3] ^ T[17] ^ T[33] ^ T[47] ^ T[61];
        int z3 = T[4] ^ T[18] ^ T[34] ^ T[48] ^ T[62];
        int z4 = T[5] ^ T[19] ^ T[35] ^ T[49] ^ T[63];
        int z5 = T[0] ^ T[6] ^ T[20] ^ T[36] ^ T[50];
        int z6 = T[1] ^ T[7] ^ T[21] ^ T[37] ^ T[51];
        int z7 = T[8] ^ T[22] ^ T[38] ^ T[52];
        int z8 = T[9] ^ T[23] ^ T[39] ^ T[53];
        int z9 = T[10] ^ T[24] ^ T[40] ^ T[54];
        int z10 = T[11] ^ T[25] ^ T[41] ^ T[55];
        int z11 = T[12] ^ T[26] ^ T[42] ^ T[56];
        int z12 = T[13] ^ T[27] ^ T[43] ^ T[57];
        int z13 = T[14] ^ T[28] ^ T[44] ^ T[58];

        for (int i = 63; i > 0; i--) {
            T[i] = T[i - 1];
        }
        T[0] = z;

        return (z13 << 13) | (z12 << 12) | (z11 << 11) | (z10 << 10) | (z9 << 9) | (z8 << 8) | (z7 << 7) | (z6 << 6) | (z5 << 5) | (z4 << 4) | (z3 << 3) | (z2 << 2) | (z1 << 1) | z0;
    }

    public static byte[] ZUC_GenKeyStream(byte[] key, byte[] iv, int length) {
        ZUC_Init();
        ZUC_KeySet(key, iv);

        byte[] keyStream = new byte[length];
        for (int i = 0; i < length; i++) {
            int z = ZUC_GetBit();
            keyStream[i] = (byte) z;
        }

        return keyStream;
    }

    public static void main(String[] args) {
        byte[] key = new byte[] {0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef};
        byte[] iv = new byte[] {0x12, 0x34, 0x56, 0x78, (byte) 0x9a, (byte) 0xbc, (byte) 0xde, (byte) 0xf0};
        byte[] plaintext = "Hello, ZUC!".getBytes();

        // 加密
        byte[] keyStream = ZUC.ZUC_GenKeyStream(key, iv, plaintext.length);
        byte[] ciphertext = new byte[plaintext.length];
        for (int i = 0; i < plaintext.length; i++) {
            ciphertext[i] = (byte) (plaintext[i] ^ keyStream[i]);
        }

        System.out.println("Plaintext: " + new String(plaintext));
        System.out.println("Ciphertext: " + Arrays.toString(ciphertext));

        // 解密
        byte[] decryptedKeyStream = ZUC.ZUC_GenKeyStream(key, iv, ciphertext.length);
        byte[] decryptedPlaintext = new byte[ciphertext.length];
        for (int i = 0; i < ciphertext.length; i++) {
            decryptedPlaintext[i] = (byte) (ciphertext[i] ^ decryptedKeyStream[i]);
        }

        System.out.println("Decrypted plaintext: " + new String(decryptedPlaintext));
    }
}