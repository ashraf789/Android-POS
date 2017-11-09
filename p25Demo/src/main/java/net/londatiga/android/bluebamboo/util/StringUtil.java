package net.londatiga.android.bluebamboo.util;

/**
 * Copyright 2006 - 2008 BLUEBAMBOO International Inc. 
 *           All rights reserved.
 *
 *
 * BLUEBAMBOO PROPRIETARY/CONFIDENTIAL.
 *
 */

/** 
 * File name:            StringUtil.java
 * 
 * Originally developed: Commands.Wu
 *
 * Create date :         2006-10-23
 * 
 * Description:          This String convert process.
 * 
 * Version:              0.1
 * 
 * Contributors:         
 * 
 * Modifications: 
 * name          version           reasons
 * 
 */



public class StringUtil
{
  public static final int LCD_WIDTH = 16;

  public static final String specialSaveChars = "=: \t\r\n\f#!";

  /** A table of hex digits */
  public static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6',
      '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

  /**
   * Convert a nibble to a hex character
   * 
   * @param nibble
   *          the nibble to convert.
   */
  public static char toHexChar(int nibble)
  {
    return hexDigit[(nibble & 0xF)];
  }

  public static int getSaveConvertLength(String theString) 
  {
    return saveConvert(theString, null, 0, true, true, true);
  }
  public static int saveConvert(String theString, byte[] dst, int offset) 
  {
    return saveConvert(theString, dst, offset, true, true, false);
  }
  /*
   * Converts unicodes to encoded &#92;uxxxx and writes out any of the
   * characters in specialSaveChars with a preceding slash
   *
   * @param theString
   *            the String needing convert. 
   * @param dst
   *            Save of the result 
   * @param offset
   *            the offset of result 
   * @param escapeSpace
   *            if <code>true</code>, escape Space 
   * @param lengthFlag
   *            Whether add one byte of length in the result. 
   *            <code>true</code> add one byte of length in the result
   * @param getLengthFlag
   *            Calculate the length of result, if <code>true</code>, theString length that return. 
   * @return  if getLengthFlag = false, return offset of the result.
   *          if getLengthFlag = true, the length of the sequence of characters represented by this
   *          object.
   */
  public static int saveConvert(String theString, byte[] dst, int offset, boolean escapeSpace, boolean lengthFlag, boolean getLengthFlag )
  {
    if (false == getLengthFlag
        && (null == dst || dst.length < (offset + (lengthFlag ? 1 : 0))
            || dst.length < 1 || offset < 0))
      return -1;
    if (null == theString)
      theString = "";
    int length = theString.length();

    StringBuffer outBuffer = new StringBuffer(length * 2);

    for (int x = 0; x < length; x++)
    {
      char aChar = theString.charAt(x);
      switch (aChar)
      {
      case ' ':
        if (x == 0 || escapeSpace)
          outBuffer.append('\\');

        outBuffer.append(' ');
        break;
      case '\\':
        outBuffer.append('\\');
        outBuffer.append('\\');
        break;
      case '\t':
        outBuffer.append('\\');
        outBuffer.append('t');
        break;
      case '\n':
        outBuffer.append('\\');
        outBuffer.append('n');
        break;
      case '\r':
        outBuffer.append('\\');
        outBuffer.append('r');
        break;
      case '\f':
        outBuffer.append('\\');
        outBuffer.append('f');
        break;
      default:
        if ((aChar < 0x0020) || (aChar > 0x007e))
        {
          outBuffer.append('\\');
          outBuffer.append('u');
          outBuffer.append(toHexChar((aChar >> 12) & 0xF));
          outBuffer.append(toHexChar((aChar >> 8) & 0xF));
          outBuffer.append(toHexChar((aChar >> 4) & 0xF));
          outBuffer.append(toHexChar(aChar & 0xF));
        } else
        {
          if (specialSaveChars.indexOf(aChar) != -1)
            outBuffer.append('\\');
          outBuffer.append(aChar);
        }
      }
    }
    length = outBuffer.length();
    if (length > 255)
      length = 255;
    if (!getLengthFlag)
    {
      if (dst.length >= offset + length + (lengthFlag ? 1 : 0))
      {
        if (lengthFlag)
        {
          dst[offset] = (byte) (length & 0xFF);
          offset++;
        }
        for (int i = 0; i < length; i++)
        {
          dst[offset] = (byte) outBuffer.charAt(i);
          offset++;
        }
        length = offset;
      } else
      {
        length = -1;
      }
    } else
    {
      if (lengthFlag)
        length = length + 1;
    }

    outBuffer = null;

    return length;
  }


  /*
   * Converts encoded &#92;uxxxx to unicode chars and changes special saved
   * chars to their original forms
   *
   * @param s
   *            the  byte arrary needing convert. 
   * @param offset
   *            the offset of byte arrary 
   * @param lengthFlag
   *            Whether add one byte of length in the result. 
   *            <code>true</code> add one byte of length in the result
   * @return  the convert result of the byte arrary. 
   */
  public static String loadConvert(byte[] s, int offset, boolean lengthFlag)
    throws IllegalArgumentException
  {
    if (null == s || (offset + (lengthFlag ? 1 : 0)) > s.length)
      throw new IllegalArgumentException("invalid byte arrary");

    char aChar;
    int len = (s.length - offset);

    if (lengthFlag)
    {
      len = s[offset] & 0xFF;
      offset++;
    }

    StringBuffer outBuffer = new StringBuffer(len);

    for (int x = offset; x < (offset + len);)
    {
      aChar = (char) s[x++];
      if (aChar == '\\')
      {
        aChar = (char) s[x++];
        if (aChar == 'u')
        {
          // Read the xxxx
          int value = 0;
          for (int i = 0; i < 4; i++)
          {
            aChar = (char) s[x++];
            switch (aChar)
            {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
              value = (value << 4) + aChar - '0';
              break;
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
              value = (value << 4) + 10 + aChar - 'a';
              break;
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
              value = (value << 4) + 10 + aChar - 'A';
              break;
            default:
              throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
            }
          }
          outBuffer.append((char) value);
        } else
        {
          if (aChar == 't')
            aChar = '\t';
          else
            if (aChar == 'r')
              aChar = '\r';
            else
              if (aChar == 'n')
                aChar = '\n';
              else
                if (aChar == 'f')
                  aChar = '\f';
          outBuffer.append(aChar);
        }
      } else
        outBuffer.append(aChar);
    }
    return outBuffer.toString();
  }

  public static String returnString(String str)
  {
    if (null == str)
        return "";
    else
      return str;
  }

  public static String returnString(int intValue)
  {
    if (intValue < 0)
      return "";
    else
      return "" + intValue;
  }

  public static String returnString(short shortValue)
  {
     return returnString((int)shortValue);
  }

  public static String returnString(byte byteValue)
  {
     return returnString((int)byteValue);
  }

  /**
  * Method trim space
  *
  * @param The string to be format.
  *
  */
  public static String trimSpace(String oldString) 
  {
    if (null == oldString)
      return null;
    if (0 == oldString.length())
      return "";
      
    StringBuffer sbuf = new StringBuffer();
    int oldLen = oldString.length();
    for(int i = 0; i < oldLen; i++)
    {
      if (' ' != oldString.charAt(i))
        sbuf.append(oldString.charAt(i));
    }     
    String returnString = sbuf.toString();
    sbuf = null;
    return returnString;
  }

  /**
  * Method convert byte[] to String
  *
  * @param The string to be format.
  *
  */
  public static String toString(byte[] buffer)
  {
    if(null == buffer)
      return null;
    else
      return new String(buffer);
  }

  /**
   * Format buffer into the designated width and height, for example: 
   * bufferString = "123456789012345678901234567890"
   * width = 16 , height = 0
   * String[] = {
   * {"1234567890123456"},
   * {"78901234567890"},
   * }
   *
   * @param The string to be format.
   *
   */
  public static String[] buffer2Message(String bufferString, int width, int height) 
  {
    int buffLen;
    int i = 0;
    int h, w;
    if (null == bufferString)
      buffLen = 0;
    else
      buffLen = bufferString.length();
      
    if (height < 1 && width > 0)
    {
      if (0 == (buffLen % width))
        h = buffLen / width;
      else
        h = (buffLen / width) + 1;
      w = width;
    } else
    {
      if (height > 0 && width < 1)
      {
        if (0 == (buffLen % height))
          w = buffLen / height;
        else
          w = (buffLen / height) + 1;
        h = height;
      } else
      {
        if (height > 0 && width > 0)
        {
          h = height;
          w = width;
        } else
        {
          return null;
        }
      }
    }
      
    String[] buff = new String[h];
    
    for (i = 0; i < h; i++)
    {
      if ((w * (i + 1)) < buffLen)
        buff[i] = bufferString.substring(w * i, w * (i+1));
      else
        if ((w * (i + 1)) >= buffLen && (w * i) < buffLen)
          buff[i] = bufferString.substring(w * i, buffLen);
        else
          buff[i] = "";
    }
    
    return buff;
  }

  /**
  * Method Format string
  *
  * @param The string to be format.
  *
  */
  public static String[] buffer2Message(String bufferString)
  {
    return buffer2Message(bufferString, LCD_WIDTH, 3);
  }

  /**
  * Method fill string
  *
  * @param The string to be format.
  *
  */
  public static String fillString(String formatString, int length, char fillChar, boolean leftFillFlag)
  {
    if (null == formatString)
    {
      formatString = "";
    }
    int strLen = formatString.length();
    if (strLen >= length)
    {
      if (true == leftFillFlag)  // left fill 
        return formatString.substring(strLen - length, strLen);
      else
        return formatString.substring(0, length);
    } else
    {
      StringBuffer sbuf = new StringBuffer();
      int fillLen = length - formatString.length();
      for (int i = 0; i < fillLen; i++)
      { 
        sbuf.append(fillChar);
      }
      
      if (true == leftFillFlag)  // left fill 
      {
        sbuf.append(formatString);
      } else
      {
        sbuf.insert(0, formatString);
      }
      String returnString = sbuf.toString();
      sbuf = null;
      return returnString;
    }
  }

  /**
  * Method fill string
  *
  * @param The string to be format.
  *
  */
  public static String fillSpace(String formatString, int length)
  {
    return fillString(formatString, length, ' ', false);
  }

  /**
  * Method Format string
  *
  * @param The string to be format.
  *
  */
  public static String formatLine(String formatString, boolean leftFillFlag)
  {
    return fillString(formatString, LCD_WIDTH, ' ', leftFillFlag);
  }


  private static final char[] space8 = { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
  /**
  * Method fill space , converted String length to LCD_WIDTH
  *
  * @param The string to be format.
  *
  */
  public static String fillShowSpace(String formatString)
  {
    if(null == formatString)
      return "";
      
    if (formatString.length() <= LCD_WIDTH)
    {
      int len = 8 - (formatString.length() / 2);
      StringBuffer sbuf = new StringBuffer();
      sbuf.append(space8, 0, len);
      sbuf.append(formatString);
      sbuf.append(space8, 0, len);
      sbuf.setLength(LCD_WIDTH);
      
      String returnString = sbuf.toString();
      sbuf = null;
      return returnString;
    } else
    {
      return formatString.substring(0, LCD_WIDTH);
    }
  }

  /**
  * Method Format string
  *
  * @param The string to be format.
  *
  */
  public static String fillZero(String formatString, int length)
  {
    return fillString(formatString, length, '0', true);
  }

  /**
       * @param s source string (with Hex representation)
       * @return byte array
       */
  public static byte[] hexStringToBytes (String s) 
  {
    if (null == s)
      return null;
      
    return hexStringToBytes (s, 0, s.length());
  }
  /**
   * @param   hexString   source string (with Hex representation)
   * @param   offset      starting offset
   * @param   count       the length
   * @return  byte array
   */
  public static byte[] hexStringToBytes(String hexString, int offset, int count) 
  {
    if (null == hexString || offset < 0 || count < 2 || (offset + count) > hexString.length())
      return null;

    byte[] buffer =  new byte[count >> 1];
    int stringLength = offset + count;
    int byteIndex = 0;
    for(int i = offset; i < stringLength; i++)
    {
      char ch = hexString.charAt(i);
      if (ch == ' ')
        continue;
      byte hex = isHexChar(ch);
      if (hex < 0)
        return null;
      int shift = (byteIndex%2 == 1) ? 0 : 4;
      buffer[byteIndex>>1] |= hex << shift;
      byteIndex++;
    }
    byteIndex = byteIndex>>1;
    if (byteIndex > 0) {
      if (byteIndex < buffer.length) {
        byte[] newBuff = new byte[byteIndex];
        System.arraycopy(buffer, 0, newBuff, 0, byteIndex);
        buffer = null;
        return newBuff;
      }
    } else {
      buffer = null;
    }
    return buffer;
  }
  
  private static void appendHex(StringBuffer stringbuffer, byte byte0)
  {
    stringbuffer.append(toHexChar(byte0 >> 4));
    stringbuffer.append(toHexChar(byte0));
  }

  public static String toHexString(byte abyte0[], int beginIndex, int endIndex, boolean spaceFlag)
  {
    if(null == abyte0)
      return null;
    if(0 == abyte0.length)
      return "";
    StringBuffer sbuf = new StringBuffer();
    appendHex(sbuf, abyte0[beginIndex]);
    for(int i = (beginIndex + 1); i < endIndex; i++)
    {
      if (spaceFlag)
        sbuf.append(' ');
      appendHex(sbuf, abyte0[i]);
    }
    String returnString = sbuf.toString();
    sbuf = null;
    return returnString;
  }

  public static String toHexString(byte abyte0[], int beginIndex, int endIndex)
  {
    if(null == abyte0)
      return null;
    return toHexString(abyte0, beginIndex, endIndex, true);
  }

  public static String toHexString(byte abyte0[], boolean spaceFlag)
  {
    if(null == abyte0)
      return null;
    return toHexString(abyte0, 0, abyte0.length, spaceFlag);
  }

  /**
  * Method convert byte[] to HexString
  *
  * @param The string to be format.
  *
  */
  public static String toHexString(byte abyte0[])
  {
    if(null == abyte0)
      return null;
    return toHexString(abyte0, 0, abyte0.length, true);
  }
  public static String toHexString(char achar0)
  {
    return toHexString((byte)achar0);
  }
  public static String toHexString(byte abyte0)
  {
    StringBuffer sbuf = new StringBuffer();
    appendHex(sbuf, abyte0);
    
    String returnString = sbuf.toString();
    sbuf = null;
    return returnString;
  }

  /**
   * Return true if the string is HexChars(1234567890abcdefABCDEF).
   *
   */  
  public static byte isHexChar(char ch)
  {   
    if ('a' <= ch && ch <= 'f')
      return (byte)(ch - 'a' + 10);
    if ('A' <= ch && ch <= 'F')
      return (byte)(ch - 'A' + 10);
    if ('0' <= ch && ch <= '9')
      return (byte)(ch - '0');

    return -1;
  }
 /**
  * Method Check String 
  *
  * @param The string to be format.
  * 
  * @param checkSpaceFlag=false: skip the space.
  *
  */  
  public static boolean isHexChar(String hexString, boolean checkSpaceFlag) 
  {
    if (null == hexString || 0 == hexString.length())
      return false;

    int hexLen = hexString.length();
    int hexCharCount = 0;
    char ch;
    for(int i = 0; i < hexLen; i++)
    {
      ch = hexString.charAt(i);
      if (ch == ' ') {
        if (checkSpaceFlag) return false;
      } else {
        if (isHexChar(ch) < 0)
          return false;
        else
          hexCharCount++;
      }
    }
    
    if (hexCharCount % 2 != 0)
      return false;
    
    return true;
  }
  /**
   * Method Check String 
   *
   * @param The string to be format.
   *
   */  
  public static boolean isHexChar(String hexString) 
  {
    return isHexChar(hexString, true);
  }
  
  /**
   * Return true if the string is alphanum.
   * <code>{letter digit }</code>
   * 
   **/
  public static boolean isLetterNumeric ( String s ) {
      int i = 0, len = s.length();
      while ( i < len && ( Character.isLowerCase(s.charAt(i)) ||
       Character.isUpperCase(s.charAt(i) ) ||
        Character.isDigit(s.charAt(i)) )){
          i++;
      }
      return ( i >= len );
  }
  
  public static String byteToString(byte[] str) {
	  StringBuilder sb = new StringBuilder();
		
	  for (int i = 0; i < str.length; i ++) {
		  sb.append(StringUtil.toHexString(str[i]));
		  sb.append(" ");
		}
	
	  return sb.toString();
  }
}
