package net.londatiga.android.bluebamboo.pockdata;

public class PostDefine {
    // public static byte ENQ = 0x05;
    // public static byte EOT = 0x04;

    public static char[] digits = {
                                      '0', '1', '2', '3', '4', '5',
                                      '6', '7', '8', '9', 'a', 'b',
                                      'c', 'd', 'e', 'f', 'g', 'h',
                                      'i', 'j', 'k', 'l', 'm', 'n',
                                      'o', 'p', 'q', 'r', 's', 't',
                                      'u', 'v', 'w', 'x', 'y', 'z'
                                  };

    // STX LEN PATH TYPE ID CONT ETX LRC
    public static byte STX = 0x02;
    public static byte ETX = 0x03;
    public static byte FS = 0x1C;

    // public static byte TEST = 0x99;

    public static byte PATH_1 = 0x01; // ������-��POS(����������)
    public static byte PATH_2 = 0x02; // ������<- POS(����������)
    public static byte PATH_3 = 0x03; // ������-��POS(����������)
    public static byte PATH_4 = 0x03; // ������<- POS(����������)
    public static byte PATH_5 = 0x03; // ������<- POS(������������)
    public static byte PATH_6 = 0x03; // ������-> POS(����������)

    public static byte TYPE_1 = 0x01; // ������������
    public static byte TYPE_2 = 0x02; // IC������
    public static byte TYPE_3 = 0x03; // ��������

    public static byte REQUEST = 0x04; // ����������������
    public static byte RESPONSE = 0x05; // ����������������

    // public static string Id = "000001";//6������


    public static byte ACK = 0x06;
    public static byte NAK = 0x15;

    public static byte SUCCEED = 0x60;

    

    // ����99
    public static byte[] TEST = new byte[] { (byte)digits[9], (byte)digits[9] };
    // ����01
    public static byte[] CONSUMER = new byte[] { (byte)digits[0], (byte)digits[1] };
    // ��������02
    public static byte[] REVOKE = new byte[] { (byte)digits[0], (byte)digits[2] };
    // ����03
    public static byte[] RETURN = new byte[] { (byte)digits[0], (byte)digits[3] };
    // ������04
    public static byte[] BALANCE = new byte[] { (byte)digits[0], (byte)digits[4] };

    // ����51
    public static byte[] REGIST = new byte[] { (byte)digits[5], (byte)digits[1] };
    // ����52
    public static byte[] SETTLE = new byte[] { (byte)digits[5], (byte)digits[2] };
    // ��������53
    public static byte[] DOWN_PUBLIC_KEY = new byte[] { (byte)digits[5], (byte)digits[3] };
    // AID��������54
    public static byte[] AID = new byte[] { (byte)digits[5], (byte)digits[4] };

    // ����������
    public static byte[] PRE_AUTHORIZED = new byte[] { (byte)digits[2], (byte)digits[1] };
    // ����������
    public static byte[] ADD_PRE_AUTHORIZED = new byte[] { (byte)digits[2], (byte)digits[2] };
    // ��������������
    public static byte[] COMPETE_PRE_AUTHORIZED_ONLINE = new byte[] { (byte)digits[2], (byte)digits[3] };
    // ��������������
    public static byte[] COMPETE_PRE_AUTHORIZED_OffLINE = new byte[] { (byte)digits[2], (byte)digits[4] };
    // ����������
    public static byte[] PRE_AUTHORIZED_REVOKE = new byte[] { (byte)digits[2], (byte)digits[5] };
    // ��������������
    public static byte[] COMPETE_PRE_AUTHORIZED_REVOKE = new byte[] { (byte)digits[2], (byte)digits[6] };
    // ��������Off balance
    public static byte[] OFF_BALANCE = new byte[] { (byte)digits[2], (byte)digits[7] };
    // ��������
    public static byte[] OffLINE_ADJUST = new byte[] { (byte)digits[2], (byte)digits[8] };


    // ��������������61
    public static byte[] PRINT = new byte[] { (byte)digits[6], (byte)digits[1] };
    // ��������������62
    public static byte[] GET_SETTLE = new byte[] { (byte)digits[6], (byte)digits[2] };
    


    // ��������
    public static String CODE_0 = "00";
    // ������������
    public static String CODE_Y4 = "Y4";
    // ����������
    public static String CODE_A3 = "A3";
    // ������,����������
    public static String CODE_A4 = "A4";
    // ������������
    public static String CODE_A5 = "A5";
    // ��������
    public static String CODE_A6 = "A6";
    // ��������
    public static String CODE_XX = "XX";


    public static byte[] START = new byte[] { (byte)0xC0, (byte)0xC0, (byte)0xC0 };
    public static byte[] END = new byte[] { (byte)0xC1, (byte)0xC1, (byte)0xC1 };

    public static byte FRAME_QUERY_REVERSAL = 0x60;
    public static byte FRAME_QUERY_ADVICE = 0x61;
    public static byte FRAME_START_FINANCIAL_TRANS = 0x62;
    public static byte FRAME_QUERY_TRANS_WITH_STAN = 0x63;
    public static byte FRAME_PRINT_TRANS_WITH_STAN = 0x64;
    public static byte FRAME_VOID_TRANS_WITH_STAN = 0x65;
    public static byte FRAME_PACK_ISO8583 = 0x66;
    public static byte FRAME_UNPACK_ISO8583 = 0x67;
    public static byte FRAME_READ_MSR = 0x68;
    public static byte FRAME_ENTER_PIN = 0x69;
    public static byte FRAME_END_FINANCIAL_TRANS = 0x6F;

    public static byte TRAN_SALE = 1;
    public static byte TRAN_VOID = 2;
    public static byte TRAN_REFUND = 3;
    public static byte TRAN_AUTH = 4;
    public static byte TRAN_ADD_AUTH = 5;
    public static byte TRAN_CANCEL = 6;
    public static byte TRAN_AUTH_SETTLEMENT = 7;
    public static byte TRAN_AUTH_COMPLETE = 8;
    public static byte TRAN_VOID_COMPLETE = 9;
    public static byte TRAN_OFFLINE = 10;
    public static byte TRAN_ADJUST = 11;
    public static byte TRAN_BATCH = 12;
    public static byte TRAN_UPLOAD = 13;
    public static byte TRAN_BALANCE = 14;
    public static byte TRAN_LOGIN = 15;
    public static byte TRAN_LOGOUT = 16;
    public static byte TRAN_DOWNPARAM = 17;
    public static byte TRAN_TESTING = 18;
    public static byte TRAN_UPSTATUS = 19;
    public static byte TRAN_VOID_SALE = 20;
    public static byte TRAN_VOID_OFFLINE = 21;
    public static byte TRAN_ADJUST_SALE = 22;
    public static byte TRAN_ADJUST_OFFLINE = 23;
    public static byte TRAN_BATCH_END = 24;
    public static byte TRAN_ECASH = 25;
}
