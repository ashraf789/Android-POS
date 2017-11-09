package net.londatiga.android.bluebamboo.pockdata;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import net.londatiga.android.bluebamboo.pockdata.PostMessage.ReturnType;


public class Function {
	/**
	 * ������������
	 * @param databyte
	 * @return
	 */
	public static byte Enteryparity(byte[] databyte) {
		byte byteOne = databyte[0];
		byte intTwo = databyte[1];
		byte intResult = (byte) (byteOne ^ intTwo);

		for (int i = 2; i < databyte.length; i++) {
			intResult = (byte) (intResult ^ databyte[i]);
		}
		return intResult;
	}

	public static byte[] readInputStreamToByte(InputStream in)
			throws IOException {
		byte[] bytes = new byte[0];
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		byte[] cache = new byte[1024 * 4];
		int read = -1;
		while ((read = in.read(cache)) != -1) {
			out.write(cache, 0, read);
		}
		bytes = out.toByteArray();
		out.close();
		return bytes;
	}

	public static String hexString(byte[] b) {
		StringBuffer d = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			char hi = Character.forDigit((b[i] >> 4) & 0x0F, 16);
			char lo = Character.forDigit(b[i] & 0x0F, 16);
			d.append(Character.toUpperCase(hi));
			d.append(Character.toUpperCase(lo));
		}
		return d.toString();
	}

	public static byte[] hex2byte (byte[] b, int offset, int len) {
        byte[] d = new byte[len];
        for (int i=0; i<len*2; i++) {
            int shift = i%2 == 1 ? 0 : 4;
            d[i>>1] |= Character.digit((char) b[offset+i], 16) << shift;
        }
        return d;
    }

	public static byte[] hex2byte (String s) {
        if (s.length() % 2 == 0) {
            return hex2byte (s.getBytes(), 0, s.length() >> 1);
        } else {
        	return hex2byte("0"+s);
        }
    }

	public static byte[] toHex2Len(int i) {
		byte[] buffer = new byte[2];
		byte[] b = hex2byte(Integer.toHexString(i));
		if (b.length < 2) {
			buffer[0] = 0x00;
			buffer[1] = b[0];
		} else {
			System.arraycopy(b, 0, buffer, 0, 2);
		}
		return buffer;
	}
	
	public static String CheckCode(String code) {
		String content = "";
		if (code.indexOf(PostDefine.CODE_0) > -1) {
			content = "��������";
		} else if (code.indexOf(PostDefine.CODE_Y4) > -1) {
			content = "������������";
		} else if (code.indexOf(PostDefine.CODE_A3) > -1) {
			content = "����������";
		} else if (code.indexOf(PostDefine.CODE_A4) > -1) {
			content = "������,����������";
		} else if (code.indexOf(PostDefine.CODE_A5) > -1) {
			content = "������������";
		} else if (code.indexOf(PostDefine.CODE_A6) > -1) {
			content = "��������";
		} else if (code.indexOf(PostDefine.CODE_XX) > -1) {
			content = "��������";
		}
		return content;
	}

	public static PostMessage DealACKNACK(byte[] inBuffer) {
		PostMessage postMsg = new PostMessage();
		try {
			if (inBuffer != null && inBuffer.length > 2) {
				int start = 0;
				for (int i = start; i < 3; i++) {
					if (inBuffer[i] == PostDefine.START[0]) {
						start++;
					} else {
						break;
					}
				}
				int end = 0;
				for (int i = inBuffer.length - 1; i >= inBuffer.length - 3; i--) {
					if (inBuffer[i] == PostDefine.END[0]) {
						end++;
					} else {
						break;
					}
				}
				int index = start;
				if (inBuffer[index] == PostDefine.ACK) {
					postMsg.setReturnType(ReturnType.ACK);
					postMsg.setErrorInfo(CheckCode(inBuffer.toString()
							.substring(index + 1, index + 3)));
				} else if (inBuffer[index] == PostDefine.NAK) {
					postMsg.setReturnType(ReturnType.NAK);
					postMsg.setErrorInfo(CheckCode(inBuffer.toString()
							.substring(index + 1, index + 3)));
				} else {
					index += 11;
					if (inBuffer.length > index) {
						if (inBuffer[index] == PostDefine.SUCCEED) // 0x60
						{
							postMsg.setReturnType(ReturnType.PATH);
							int len = inBuffer.length - (11 + 2 + start + end);

							// ����������
							byte[] header = new byte[] { inBuffer[start + 1],
									inBuffer[start + 2] };
							int msgLen = Integer.parseInt(Function
									.hexString(header), 16);
							if (msgLen == len + 8) {
								byte[] bytesContent = new byte[len];
								int j = 0;
								end = start + 11 + len;
								for (int i = start + 11; i < end; i++) {
									bytesContent[j] = inBuffer[i];
									j++;
								}

								postMsg.setContent(bytesContent);

							} else {
								postMsg.setReturnType(ReturnType.Integrity);
							}
						}
					} else {
						postMsg.setReturnType(ReturnType.NONE);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("����������������  " + "\r\n");
		}
		return postMsg;
	}
}
