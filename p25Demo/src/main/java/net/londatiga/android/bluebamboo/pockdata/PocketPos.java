package net.londatiga.android.bluebamboo.pockdata;

import android.util.Log;

import net.londatiga.android.bluebamboo.util.NumberUtil;
import net.londatiga.android.bluebamboo.util.StringUtil;


/**
 * Copyright 2006 - 2008 BLUEBAMBOO International Inc. 
 *           All rights reserved.
 *
 *
 * BLUEBAMBOO PROPRIETARY/CONFIDENTIAL.
 *
 */

/** 
 * File name:            PocketPos.java
 * 
 * Originally developed: Commands.Wu
 *
 * Create date :         2006-10-23
 * 
 * Description:          
 * 
 * Version:              0.1
 * 
 * Contributors:         
 * 
 * Modifications: 
 * name          version           reasons
 * 
 */

public class PocketPos
{
  // the frame type
  public static final byte FRAME_ACK = 0x06;
  public static final byte FRAME_NACK = 0x15;
  public static final byte FRAME_ENQ = 0x05;
  
  
  
  public static final byte FRAME_EOT = 0x04;
  public static final byte FRAME_ETX = 0x03;
 
  public static final byte FRAME_PHONE = 0x50;
  public static final byte FRAME_RSP = 0x52;
  public static final byte FRAME_TOF_PRINT = 0x44;
  public static final byte FRAME_TOF_PWD = 0x4D;
  public static final byte FRAME_TOF_PROCESSDATA = 0x50;
  public static final byte FRAME_MSR= 0x48;
  
  
  public static final int TAG_OUTPUT_PHONE = 161; //a1
  public static final int TAG_LABEL_PHONE = 162;//a2
  public static final int TAG_ITEMS_PHONE = 163;//a3
  
  public static final int TAG_INPUT_B1 = 177;//b1;
  public static final int TAG_INPUT_B2 = 178;//b2
  public static final int TAG_INPUT_B3 = 179;//b3
  public static final int TAG_INPUT_B4 = 180; //b4

  public static final int BYTES_OF_DATA_LENGTH = 4;

  public static final byte NO_CRYPTO = (byte) 0x00;

  // the command type
  public final static int FRAME_REQUEST = 1;
  public final static int FRAME_RESPONSE = 2;

  public final static byte FONT_SMALL = 1;//������
  public final static byte FONT_BIG = (byte)0xC0; //0x08 = Double-height mode  selected 0x10 = Double-width mode selected
  public final static byte FONT_DEFAULT = 0;
  public static byte fontSize = FONT_DEFAULT;
  
  
  /*
  Bit Off/On Value Function
  0 Select 32 dot font (24
  char/per line)
  eg. 1B 21 00
  0
  -- 1 Select 24 dot font (36
  char/per line)
  eg. 1B 21 01
  1 -- -- undefined
  2 -- -- undefined
  3 -- -- undefined
  off 0 Double-height mode not
  selected
  4
  on 1 Double-height mode
  selected
  off 0 Double-width mode not
  selected
  5
  on 1 Double-width mode selected
  6 -- -- undefined
  off 0 Underline mode not
  selected
  7
  on 1 Underline mode selected
*/
  
  public final static byte LANGUAGE_ENGLISH = 0;
  public final static byte LANGUAGE_FRANCE = 1;
  public final static byte LANGUAGE_GERMANY = 2;
  public final static byte LANGUAGE_UK = 3;
  public final static byte LANGUAGE_DENMARK1 = 4;
  public final static byte LANGUAGE_SWEDEN = 5;
  public final static byte LANGUAGE_ITALY = 6;
  public final static byte LANGUAGE_SPAIN1 = 7;
  public final static byte LANGUAGE_NORWAY = 8;
  public final static byte LANGUAGE_DENMARK2 = 9;
  public final static byte LANGUAGE_SPAIN2 = 10;
  public final static byte LANGUAGE_PORTUGUESE = 11;
  public final static byte LANGUAGE_CHINESE = 48;

  
  public final static int TYPE_IMAGE = 2;
  public final static int TYPE_IDLE_TIME = 3;
  public final static int TYPE_POWEROFF_TIME = 4;
  public final static int TYPE_ALIGN_LEFT = 5;
  public final static int TYPE_ALIGN_CENTER = 6;
  public final static int TYPE_ALIGN_RIGHT = 7;
  public final static int TYPE_ALIGN_FONTMULTIPLE = 8;
  
  // add decode and encode
  public final static byte B_SOF = (byte) 0xc0;
  public final static byte B_EOF = (byte) 0xc1;
  public final static byte B_TRANSFER = (byte) 0x7D;
  public final static byte B_XOR = (byte) 0x20;
  public final static byte B_ESC = (byte) 0x1B;
  
  private static int dataID = 0;

  private static int intToAscii(long number, byte[] buf, int offset, int length) 
  {
    if (buf == null || offset < 0 || length < 1 || buf.length < (offset + length))
      return -1;
    length = offset + length;
    int charPos = length -1;
    int index = 0;
    
    if (number > 0) {
      number = -number;
    }
    for(; charPos >= offset; charPos--){
      if (0 != number){
        index = (int)(-(number % 10));
        number = number / 10;
      } else {
        index = 0;
      }
      buf[charPos] = (byte)('0' + index);
    }

    return charPos;
  }
  private static int asciiToInt(byte[] buf, int offset, int length)
  {
    if (buf == null || offset < 0 
        || length < 1 || length > 10 
        || buf.length < (offset + length))
      return -1; 
    int charPos = offset + length -1;
    int result = 0;
    int j = 1;

    for (int i = 0; i < length; i++) {
      if (buf[charPos] < '0' || '9' < buf[charPos])
        return -1;
      result = result + (buf[charPos] - (byte)0x30) * j;
      j = (j * 10);
      charPos--;
    }
    return result;
  }
  
  private static byte[] concatByteArray(byte[] a, byte[] b,byte c[], byte[] d)
  {
	 
	
	  
    if (a == null && b == null && c == null && d == null)
      return null;

    int aL = (a == null?0:a.length);
    int bL = (b ==null?0:b.length);
    int cL = (c == null?0:c.length);
    int dL = (d == null?0:d.length);
    
    
    Log.i("a length", aL + "");
    Log.i("b length", bL + "");
    Log.i("c length", cL + "");
    Log.i("d length", dL + "");
    
//    if (bL == 0)
//      return a;
    
    int len = aL + bL + cL + dL;
    byte[] result = new byte[len];
    
    Log.i("len",len+"");
    
    
    if (a != null)
      System.arraycopy(a, 0, result, 0, aL);
    if (b != null)
      System.arraycopy(b, 0, result, aL, bL);
    if(c != null)
    	System.arraycopy(c, 0, result, aL+bL, cL);
    if(d != null)
    	System.arraycopy(d, 0, result, aL+bL+cL, dL);
    
    Log.i("result ", StringUtil.toHexString(result));
  
    return result;
  }  
  
  private static byte[] concatByteArray(byte[] a, byte[] b)
  {
    if (a == null && b == null )
      return null;

    int aL = (a == null?0:a.length);
    int bL = (b ==null?0:b.length);
  
    if (bL == 0)
      return a;
    int len = aL + bL ;
    byte[] result = new byte[len];
    
    if (a != null)
      System.arraycopy(a, 0, result, 0, aL);
    if (b != null)
      System.arraycopy(b, 0, result, aL, bL);
   
    return result;
  }  

  /**
   * encode the byte[] to char[] in UTF-8
   * @param buf
   * @param cbuf
   * @return      the cbuf valid length
   */
  private static String bytes2charsUTF8(byte[] buf, int offset, int length, boolean bigEndian)
  {
    int len=0;
    int bufLength = offset + length;
    if (bufLength > buf.length) 
      bufLength = buf.length;
    for(int i=offset;i<bufLength;i++){
      if(((buf[i])& 0x80)==0x00 
          || ((buf[i])& 0xc0)==0xc0 )
      {
        len++;
      }
    }
    char[] cbuf=new char[len];
    int cpos=0;
    int pos=offset;
    byte b1,b2;
    b1=0;b2=0;
    while(pos<(offset + length)){
        if((buf[pos] & 0x80)==0x00){ //U-00000000 - U-0000007F:  0xxxxxxx
            b1=0;
            b2=buf[pos];
            pos++;
        }else if((buf[pos] & 0xe0)==0xc0){ //U-00000080 - U-000007FF:  110xxxxx 10xxxxxx
            if((buf[pos+1] & 0x80)==0x80){
                b1=(byte)(((buf[pos] & 0x1f)>>2) & 0xff);
                b2=(byte)(((buf[pos] & 0x03)<<6) | (buf[pos+1] & 0x3f) & 0xff);
                pos+=2;
            }else{
                /* invalid format, use ? instead 
                 * -- 2006-3-29 13:55:32 */
                b1=0x00;
                b2=0x3f;
                pos+=1;
            }
        }else if((buf[pos] & 0xf0)==0xe0){ //U-00000800 - U-0000FFFF:  1110xxxx 10xxxxxx 10xxxxxx
            if(((buf[pos+1] & 0x80)==0x80) && ((buf[pos+2] & 0x80)==0x80)){
                b1=(byte)((((buf[pos] & 0x0f)<<4) | ((buf[pos+1] & 0x3f)>>2) ) & 0xff);
                b2=(byte)(((buf[pos+1] & 0x03)<<6) | (buf[pos+2] & 0x3f) & 0xff);
                pos+=3;
            }else if((buf[pos+1] & 0x80)==0x80){
                /* invalid format, use ? instead 
                 * -- 2006-3-29 13:55:32 */
                b1=0x00;
                b2=0x3f;
                pos+=2;
            }else{
                /* invalid format, use ? instead 
                 * -- 2006-3-29 13:55:32 */
                b1=0x00;
                b2=0x3f;
                pos+=1;
            }
        }else{
            b1=0;
            b2=0;
            pos++;
            continue;
        }
        if(bigEndian){
            cbuf[cpos]=(char)(((b1 & 0xff)<<8 | (b2 & 0xff)) & 0xffff);
        }else{
            cbuf[cpos]=(char)(((b2 & 0xff)<<8 | (b1 & 0xff)) & 0xffff);
        }
        cpos++;
    }
    
    String str=new String(cbuf,0,len);
    cbuf=null;
    return str;
  }

  private static byte[] char2ByteUTF8(String input, int offset, int length)
  {
    byte[] output = new byte[length * 3];

    int i = offset;
    int inEnd = offset + length;
    int outLength = 0;

    char inputChar;
    
    while (i < inEnd) {
        inputChar = input.charAt(i);
        if (inputChar < 0x80) {
          output[outLength++] = (byte) inputChar;
        } else if (inputChar < 0x800) {
          output[outLength++] = (byte) (0xc0 | ((inputChar >> 6) & 0x1f));
          output[outLength++] = (byte) (0x80 | (inputChar & 0x3f));
        } else {
          output[outLength++] = (byte) (0xe0 | ((inputChar >> 12)) & 0x0f);
          output[outLength++] = (byte) (0x80 | ((inputChar >> 6) & 0x3f));
          output[outLength++] = (byte) (0x80 | (inputChar & 0x3f));
        }
        i ++;
    }
    byte[] ret=new byte[outLength];
    System.arraycopy(output, 0, ret, 0, outLength);
    return ret;    
  }
  private static byte getDataID()
  {
    dataID++;
    if (dataID >= 10)
      dataID = 0;
    return (byte)('0' + dataID);
  }
  
  private static int calcCheckSumAndSpecialBytes(byte[] buf, int offset, int len, byte[] checkSum, int checkSumOffset)
  {
    byte even = 0x00;
    byte odd = 0x00;
    boolean isEven = true;
    int cnt = 0;
    int length = len + offset;
    for (int i = offset; i < length; i++, cnt++)
    {
      if (isEven) {
        even = (byte) (even ^ buf[offset + i]);
      } else {
        odd = (byte) (odd ^ buf[offset + i]);
      }
      isEven = !isEven;
      if (buf[i] == B_SOF || buf[i] == B_EOF || buf[i] == B_TRANSFER)
      {
        cnt++;
      }
    }
    if (even == B_SOF || even == B_EOF || even == B_TRANSFER)
      cnt++;
    if (odd == B_SOF || odd == B_EOF || odd == B_TRANSFER)
      cnt++;
    cnt += 2;
    checkSum[checkSumOffset] = even;
    checkSumOffset++;
    checkSum[checkSumOffset] = odd;
    return cnt;
  }

  private static int convertSpecialBytes(byte[] srcBuff, int srcOffset, int srcLen, byte[] destBuff, int destOffset)
  {
    for (int j = srcOffset; j < srcLen; j++, destOffset++)
    {
      if (srcBuff[j] == B_SOF || srcBuff[j] == B_EOF || srcBuff[j] == B_TRANSFER)
      {
        destBuff[destOffset] = B_TRANSFER;
        destOffset++;
        destBuff[destOffset] = (byte) ((srcBuff[j] ^ B_XOR) & 0xff);
      } else {
        destBuff[destOffset] = srcBuff[j];
      }
    }
    return destOffset;
  }
  public static byte[] FramePack(byte typeFrame, byte[] srcBuff, int srcOffset, int srcLen)
  {
    if (typeFrame == FRAME_NACK || typeFrame == FRAME_ACK 
        || typeFrame == FRAME_ENQ || typeFrame == FRAME_EOT) {
      return new byte[]{B_SOF, B_SOF, B_SOF, typeFrame, B_EOF, B_EOF, B_EOF};
    }
    if (typeFrame == FRAME_ETX) {
      return new byte[]{B_SOF, B_SOF, B_SOF, typeFrame, getDataID(), B_EOF, B_EOF, B_EOF};
    }
    if (typeFrame == FRAME_PHONE 
        || typeFrame == FRAME_RSP || typeFrame == FRAME_TOF_PRINT || typeFrame == FRAME_TOF_PWD || typeFrame == FRAME_TOF_PROCESSDATA
        
        ) {
      if(srcOffset < 0 || srcLen < 1 || (srcOffset + srcLen) > srcBuff.length)
        return null;
      byte[] checkSum = new byte[2];
      int countSpecialBytes = calcCheckSumAndSpecialBytes(srcBuff, srcOffset, srcLen, checkSum, 0);
      //byte[] buffer = new byte[(1+1+1) + 1 + 1 + BYTES_OF_DATA_LENGTH + countSpecialBytes  + (1+1+1)];
      byte[] buffer = new byte[(1) + 1 + 1 + BYTES_OF_DATA_LENGTH + countSpecialBytes  + (1)]; //For P25
      // |C0H(SOF) | (TOF) | DATA_ID | DATA Length | DATA | CHECK_SUM |C1H(EOF)|
      int offset = 0;
      buffer[offset] = B_SOF;
      offset++;
//      buffer[offset] = B_SOF;
//      offset++;
//      buffer[offset] = B_SOF;
//      offset++;
      buffer[offset] = typeFrame;
      offset++;
      buffer[offset] = getDataID();
      offset++;
      intToAscii(srcLen, buffer, offset, BYTES_OF_DATA_LENGTH);
      offset += BYTES_OF_DATA_LENGTH;
      
      offset = convertSpecialBytes(srcBuff, srcOffset, srcLen, buffer, offset);

      offset = convertSpecialBytes(checkSum, 0, checkSum.length, buffer, offset);

//      buffer[offset] = B_EOF;
//      offset++;
//      buffer[offset] = B_EOF;
//      offset++;
      buffer[offset] = B_EOF;
      offset++;
      return buffer;
    }
    
    return null;
  }

  public static byte[] FrameUnpack(byte[] srcBuff, int srcOffset, int srcLen)
  {
    if(srcOffset < 0 || srcLen < 1 || (srcOffset + srcLen) > srcBuff.length)
      return null;

    int offset,length;

    length = srcOffset + srcLen;
    for(offset = srcOffset; offset<length; offset++)
    {
      if(srcBuff[offset] == B_SOF  && srcBuff[offset+1] != B_SOF)
        break;
    }

    if((offset + 2) > length) //  2 = 1+ 1 = (TOF) + C1H(EOF)
      return null;
    
    offset++; // C0H(SOF)
    byte typeFrame = srcBuff[offset]; // (TOF)
    if ((typeFrame == FRAME_NACK || typeFrame == FRAME_ACK 
        || typeFrame == FRAME_ENQ || typeFrame == FRAME_EOT )
      && srcBuff[offset + 1] == B_EOF){
      return new byte[]{typeFrame};
    }
    
    if (typeFrame == FRAME_ETX && (offset + 2) <= length  //  2 = 1+ 1 = DATA_ID + C1H(EOF)
        && srcBuff[offset + 2] == B_EOF) {
      dataID = srcBuff[offset + 1];
      return new byte[]{typeFrame};
    }
   
    if ((typeFrame == FRAME_PHONE 
        || typeFrame == FRAME_RSP
        || typeFrame == FRAME_MSR
        || typeFrame == FRAME_TOF_PWD
        || typeFrame == FRAME_TOF_PROCESSDATA
        )
        && (offset + 4 + BYTES_OF_DATA_LENGTH) <= length ) {//  4 = 1+2+1 = DATA_ID+CHECK_SUM+C1H(EOF)
      offset++; // (TOF)
      dataID = srcBuff[offset];
      offset++; // DATA_ID
      
      int dataLength = -1;
      try
      {
        dataLength = asciiToInt(srcBuff, offset, BYTES_OF_DATA_LENGTH);
      } catch (Exception e)
      {
        dataLength = -1;
      }
      offset = offset + BYTES_OF_DATA_LENGTH;
      if (dataLength >= 0 && (offset + dataLength) <= length) {
        byte[] buffer = new byte[1 + dataLength]; 
        buffer[0] = typeFrame;
        byte even = 0x00;
        byte odd = 0x00;
        boolean isEven = true;
        for (int i = 1; i <= dataLength; i++, offset++)
        {
          if (srcBuff[offset] == B_TRANSFER)
          {
            offset++;
            buffer[i] = (byte)((srcBuff[offset] ^ B_XOR) & 0xff);
          } else
          {
            buffer[i] = srcBuff[offset];
          }
          if (isEven) { // calcCheckSum
            even = (byte) (even ^  buffer[i]);
          } else {
            odd = (byte) (odd ^  buffer[i]);
          }
          isEven = !isEven;
        }
        if ((offset + 3) <= length ) {//  3 = 2+1 = CHECK_SUM+C1H(EOF) {
          byte checkSum = srcBuff[offset];
          if (checkSum == B_TRANSFER) {
            offset++;
            checkSum = (byte)((srcBuff[offset] ^ B_XOR) & 0xff);
          }
          if (even != checkSum)
            return null;
          offset++;
          checkSum = srcBuff[offset];
          if (srcBuff[offset] == B_TRANSFER) {
            offset++;
            checkSum = (byte)((srcBuff[offset] ^ B_XOR) & 0xff);
          }
          if (odd != checkSum)
            return null;
          offset++;
          if (offset <= length && srcBuff[offset]  == B_EOF) {
            return buffer;
          }
        }
      }
    }
    return null;
  }

  public static byte[] buildMSRData(byte[] track1, byte[] track2, byte[] track3) 
  {
    int length = 0;
    if (track1 != null && track1.length > 0) {
      length = length + 1 + 4 + track1.length;
    }
    if (track2 != null && track2.length > 0) {
      length = length + 1 + 4 + track2.length;
    }
    if (track3 != null && track3.length > 0) {
      length = length + 1 + 4 + track3.length;
    }
    if (length > 0) {
      byte[] buffer = new byte[length];
      length = 0;
      if (track1 != null && track1.length > 0) {
        buffer[length] = (byte)'1';
        length++;
        intToAscii(track1.length, buffer, length, 4);
        length += 4;
        System.arraycopy(track1, 0, buffer, length, track1.length);
        length += track1.length;
      }
      if (track2 != null && track2.length > 0) {
        buffer[length] = (byte)'2';
        length++;
        intToAscii(track2.length, buffer, length, 4);
        length += 4;
        System.arraycopy(track2, 0, buffer, length, track2.length);
        length += track2.length;
      }
      if (track3 != null && track3.length > 0) {
        buffer[length] = (byte)'3';
        length++;
        intToAscii(track3.length, buffer, length, 4);
        length += 4;
        System.arraycopy(track3, 0, buffer, length, track3.length);
        length += track3.length;
      }
      return buffer;
    } else {
      return null;
    }
  }
  
  public static String parsePrintData(byte[] data, int offset, int length)
  {
    if (data == null || offset < 0 || length < 0 || (offset + length) > data.length)
      return null;
    if (length < 2)
      return null;
    int i = offset;
    int end = offset + length;
    
    byte language = LANGUAGE_ENGLISH;
    fontSize = FONT_DEFAULT;
    String msg = "";
  
    for(; i < end; i ++)
    {
      if (B_ESC != data[i])
      {
        break;
      }
      i ++;
      byte format = data[i];
      i ++;
      byte value = data[i];
      if (format == (byte)0x52) // Language Character Select
      {
        language = value;
      }
      else if (format == (byte)0x21) // Font Size Select
      {
        if (value == 0x00)  // Big font
          fontSize = FONT_BIG;
        else if (value == 0x01)  // Small font
          fontSize = FONT_SMALL;
      }
    }
    if (language == LANGUAGE_CHINESE)
    {
      msg = bytes2charsUTF8(data, i, end - i, true);
    } else {
      msg = new String(data, i, end - i);
    }
    return msg;
  }
  public static byte[] convertPrintData(String str, int offset, int length, byte languageSet, byte fontSet,byte align,byte linespace)
  {
    byte[] buffer = null;
    if (languageSet == LANGUAGE_CHINESE)
    {
      buffer = char2ByteUTF8(str, offset, length);
    } else {
      buffer = new byte[length];
      System.arraycopy(str.getBytes(), offset, buffer, 0, length);
    }
    
    byte[] lang = null;
//    if (languageSet != LANGUAGE_ENGLISH)
//    {
    	lang = new byte[]{B_ESC, (byte)0x4B, (byte)0x31,B_ESC, (byte)0x52, languageSet};
//    }
    byte[] font = null;
    byte[] fontalign = null;
    byte[] fontlinespace = null;
  //  if (fontSet != FONT_DEFAULT)
   // {
    
   font = new byte[]{B_ESC, (byte)0x21, fontSet};
   fontalign = new byte[]{B_ESC,(byte)0x61,align};
   fontlinespace = new byte[]{B_ESC,(byte)0x33,linespace};
  
    //}
    byte[] formate = concatByteArray(lang, font, fontalign, fontlinespace);
    Log.i("formate value = ", StringUtil.toHexString(formate));
    return concatByteArray(formate, buffer);
  }

 
  /*
  The tag field (T) consists of one or more consecutive bytes. It indicates a class,
  a type, and a number (see Table 35). The tag field of the data objects 
  described in this specification is coded on one or two bytes. 
  *The length field (L) consists of one or more consecutive bytes. It indicates the 
  length of the following field. The length field of the data objects described in 
  this specification is coded on one or two bytes. 
  *The value field (V) indicates the value of the data object. If L = '00', the value 
  field is not present. 
 */
  public static short[] TLVParse(byte[] data, int offset)
  {
    if (data != null && offset >= 0 && data.length > (offset + 2))
    {
      int i = offset;
      short tag = 0;
      short valueOffset = 0;
      short valueLength = 0;
//      Tag Field Structure (First Byte) BER-TLV,the first byte of 
//      the tag field of a BER-TLV data object: 
//      b8_|b7_|b6_|b5_|b4_|b3_|b2_|b1_|  Meaning 
//      _0_|_0_|___|___|___|___|___|___|Universal class 
//      _0_|_1_|___|___|___|___|___|___|Application class 
//      _1_|_0_|___|___|___|___|___|___|Context-specific class 
//      _1_|_1_|___|___|___|___|___|___|Private class 
//      ___|___|_0_|___|___|___|___|___|Primitive data object 
//      ___|___|_1_|___|___|___|___|___|Constructed data object 
//      ___|___|___|_1_|_1_|_1_|_1_|_1_|See subsequent bytes 
//      ___|___|___|Any other value <31|  Tag number 
//
//      Tag Field Structure (Subsequent Bytes) BER-TLV defines the coding 
//      rules of the subsequent bytes of a BER-TLV tag when tag numbers �� 31 
//      are used (that is, bits b5 - b1 of the first byte equal '11111'). 
//      b8_|b7_|b6_|b5_|b4_|b3_|b2_|b1_| Meaning 
//      _1_|___|___|___|___|___|___|___|Another byte follows  
//      _0_|___|___|___|___|___|___|___|Last tag byte 
//      ___|_____Any value > 0_________|(Part of) tag number 
      // TAG������������bit����16������������1��2������������
      // ��tag������������������������������������������������
      // ����������������������������bit��������������������
      // 5��bit����11111������������tag������������������9F33����
      // ����������������������95����
      if ((data[i] & 0x1F) == 0x1F) {
        tag = NumberUtil.byte2ToShort(data, i);
        i ++;
      } else {
        tag = (short)(data[i] & 0xFF);
      }
      i ++;
//    When bit b8 of the most significant byte of the length field is set to 0, the length 
//    field consists of only one byte. Bits b7 to b1 code the number of bytes of the value
//    field. The length field is within the range 1 to 127. 
//    When bit b8 of the most significant byte of the length field is set to 1, the 
//    subsequent bits b7 to b1 of the most significant byte code the number of 
//    subsequent bytes in the length field. The subsequent bytes code an integer 
//    representing the number of bytes in the value field. Two bytes are necessary to 
//    express up to 255 bytes in the value field. 
      //������������L����������������bit����1��3������������������������������
      //a)��L��������������������bit������bit8����0��������L����������������
      //  ��������7��bit������bit7��bit1����������������������������������
      //  ����������������������������������������������3������������������
      //  ����������������00000011����������������������������1��127����������
      //  ������L����������������������
      //b)��L��������������������bit������bit8����1��������L��������������
      //  ��������������������������������������������7��bit������bit7��bit1��
      //  ������������������������������������10000010������L����������������
      //  ������������������������������������������������������������������
      //  ��L��������1000 0001 1111 1111��������������������255��������������
      //  ������������������127��255����������������L����������������������
      int ll = 1;
      if ((data[i] & 0x80) == 0x80) {
        ll = data[i] & 0x7F;
        i ++;
      }
      if (ll == 2){
        valueLength = NumberUtil.byte2ToShort(data, i);
        i ++;
      } else {
        valueLength = (short)(data[i] & 0xFF);
      }
      i ++;
      valueOffset = (short)i;
      short[] taginfo = new short[3];
      taginfo[0] = tag;
      taginfo[1] = valueOffset;
      taginfo[2] = valueLength;
      return taginfo;
    }
    return null;
  }

  public static int TLVAppend(short tag, byte[] src, int src_offset, byte[] dst, int dst_offset, int length)
  {
    int i = dst_offset;
    byte[] buffer = NumberUtil.shortToByte2(tag);
    if ((buffer[0] & 0x1F) == 0x1F) {
      System.arraycopy(buffer, 0, dst, i, 2);
      i ++;
    } else {
      dst[i] = buffer[1];
    }
    i ++;
    if (length <= 0x7F)
    {
      dst[i] = (byte)(length & 0xFF);
    }
    else if (length <= 0xFF)
    {
      dst[i] = (byte)0x81;
      i ++;
      dst[i] = (byte)(length & 0xFF);
    }
    else
    {
      dst[i] = (byte)0x82;
      i ++;
      dst[i] = (byte)(length/256);
      i ++;
      dst[i] = (byte)(length%256);
    }
    i ++;
    System.arraycopy(src, src_offset, dst, i, length);
    i = i + length;

    return i;
  }
//EMV Process End

}
