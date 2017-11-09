package net.londatiga.android.bluebamboo.pockdata;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class SmartPost {
	// ��MIS-POS Client����������������������������������������
	// ������������ID,������������������������������
	// ������������������EMV POS��������ID��������
	private static byte[] id = new byte[] { 0, 0, 0, 0, 0, 1 }; // "000001";//6������
	private static String business_en = ""; // ���������� LLVAR60_2
	private static String business_zh = ""; // ���������� LLVAR60
	private static String businessNo = "123456789012345"; // ������ AN15
	private static String terminalNo = "12345678"; // ������ AN8
	
	/**
	 * ����
	 * @param cmd ��������������CMD��
	 * @param path ������������
	 * @param amount ��������N12
	 * @param certificateNo ������������n6(��������)
	 * @return
	 */
	public static byte[] InitMessage(byte[] cmd, byte path, BigDecimal amount,
			String certificateNo) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			baos.write(PostDefine.START);
			baos.write(PostDefine.STX);
			
			ByteArrayOutputStream lsContent = new ByteArrayOutputStream();
			lsContent.write(path);
			lsContent.write(PostDefine.TYPE_1);
			lsContent.write(id);
			lsContent.write(cmd);
			lsContent.write(PostDefine.FS);
			lsContent.write(businessNo.getBytes());
			lsContent.write(PostDefine.FS);
			lsContent.write(terminalNo.getBytes());
			lsContent.write(PostDefine.FS);
			lsContent.write(business_zh.getBytes());
			lsContent.write(PostDefine.FS);
			lsContent.write(business_en.getBytes());

			if (amount.intValue() > -1) {
				lsContent.write(PostDefine.FS);
			//	int value = (int) (amount.intValue() * 100);
				int value = (int) amount.intValue();
				NumberFormat numberFormat = new DecimalFormat("#000000000000");
				lsContent.write(numberFormat.format(value).getBytes());

				// ��������,������������
				if (certificateNo != null) {
					lsContent.write(PostDefine.FS);
					lsContent.write(certificateNo.getBytes());
				}
			}
			int len = lsContent.size();
			baos.write(Function.toHex2Len(len));
			baos.write(lsContent.toByteArray());
			baos.write(PostDefine.ETX);
			
			byte[] buffer = baos.toByteArray();
			byte[] LRCContent = new byte[buffer.length-4];
			System.arraycopy(buffer, 4, LRCContent, 0, buffer.length-4);
			byte LRC = Function.Enteryparity(LRCContent);
			
			baos.write(LRC);
			baos.write(PostDefine.END);
			lsContent.close();
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return baos.toByteArray();
	}
	
	/**
	 * ��������
	 * @param cmd AN
	 * @param code ACK/NAK����
	 * @param tip ����
	 * @param path ������������
	 * @return
	 */
	public static byte[] InitMessage(byte[] cmd, String code, String tip, byte path) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			baos.write(PostDefine.START);
			baos.write(PostDefine.STX);
			
			ByteArrayOutputStream lsContent = new ByteArrayOutputStream();
			lsContent.write(path); // PostDefine.PATH_1
			lsContent.write(PostDefine.TYPE_1);
			lsContent.write(id);
			lsContent.write(cmd);
			if (code != null) {
				lsContent.write(PostDefine.FS);
				lsContent.write(code.getBytes());
				if (tip != null) {
					lsContent.write(PostDefine.FS);
					lsContent.write(tip.getBytes());
				}
			}
			int len = lsContent.size();
			baos.write(Function.toHex2Len(len));
			baos.write(lsContent.toByteArray());
			baos.write(PostDefine.ETX);
			
			byte[] buffer = baos.toByteArray();
			byte[] LRCContent = new byte[buffer.length-4];
			System.arraycopy(buffer, 4, LRCContent, 0, buffer.length-4);
			byte LRC = Function.Enteryparity(LRCContent);
			
			baos.write(LRC);
			baos.write(PostDefine.END);
			lsContent.close();
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return baos.toByteArray();
	}
	
	/**
	 * 
	 * @param cmd ��������������CMD��
	 * @param code ACK/NAK����
	 * @return
	 */
	public static byte[] InitMessage(byte cmd, String code) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			baos.write(PostDefine.START);
			baos.write(cmd);
			if (code != null) {
				baos.write(PostDefine.FS);
				baos.write(code.getBytes());
			}
			baos.write(PostDefine.END);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return baos.toByteArray();
	}
	
	/**
	 * ������������������
	 * @param content 
	 * @return
	 */
	public static byte[] InitMessage(byte[] content) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			baos.write(PostDefine.START);
			baos.write(PostDefine.STX);
			int len = content.length + 8;//��path��type��id��������8����
			baos.write(Function.toHex2Len(len));
			baos.write(PostDefine.PATH_4);
			baos.write(PostDefine.TYPE_1); //
			baos.write(id);
			baos.write(content);
			baos.write(PostDefine.ETX);

			byte[] buffer = baos.toByteArray();
			byte[] LRCContent = new byte[buffer.length-4];
			System.arraycopy(buffer, 4, LRCContent, 0, buffer.length-4);
			byte LRC = Function.Enteryparity(LRCContent);
			
			baos.write(LRC);
			baos.write(PostDefine.END);
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baos.toByteArray();
	}
}