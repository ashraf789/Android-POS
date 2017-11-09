/**
 * Copyright 2006 - 2008 BLUEBAMBOO International Inc.
 *           All rights reserved.
 *
 *
 * BLUEBAMBOO PROPRIETARY/CONFIDENTIAL.
 *
 */

/**
 * File name:            Tracer.java
 *
 * Originally developed: Commands.Wu
 *
 * Create date :         2006-10-23
 *
 * Description:          This Tracer Tools.
 *
 * Version:              0.1
 *
 * Contributors:
 *
 * Modifications:
 * name          version           reasons
 *
 */
package net.londatiga.android.bluebamboo.util;


public class Tracer
{

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

  public static String dump(byte abyte0[])
  {
    if(abyte0 == null || abyte0.length == 0)
      return "";
    else
    	return dump(abyte0, 0, abyte0.length, true);
  }
  public static String dump(byte abyte0[], int beginIndex, int endIndex, boolean spaceFlag)
  {
	  return dump(abyte0, beginIndex, endIndex, spaceFlag, true, true, 0);
  }
  public static String dump(byte abyte0[], int beginIndex, int endIndex, boolean spaceFlag, boolean asciiFlag, boolean lineNumberFlag, int linenumber)
  {
    if(abyte0 == null || abyte0.length == 0)
      return "";

    String outMsg = "";
    int totalLine = (endIndex - beginIndex) / 16;
    int lineNumber, q;
    int offset = beginIndex;
    byte byte0;
    StringBuffer stringbuffer = new StringBuffer(6 + (spaceFlag?48:32));
    StringBuffer asciibuffer = new StringBuffer();
    String printString;

    if (linenumber < 0)
      linenumber = 0;
    else
      linenumber = linenumber % 10000;

    for(int i = 0; i <= totalLine; i++, linenumber++)
    {
      if (offset < endIndex) {
        stringbuffer.delete(0, stringbuffer.length());
        asciibuffer.delete(0, asciibuffer.length());
        if (lineNumberFlag) {
          stringbuffer.append("0000: ");
          lineNumber = linenumber;
          for(byte0 = 3; byte0 >=0; byte0--){
            q = (lineNumber * 52429) >>> (16+3);
            stringbuffer.setCharAt(byte0, toHexChar(lineNumber - ((q << 3) + (q << 1)))); // toHexChar(lineNumber-(q*10))
            lineNumber = q;
            if (0 == lineNumber) break;
          }
        }
        for(int j = 0; j < 16; j++, offset++)
        {
          if (offset < endIndex) {
            byte0 = abyte0[offset];
            stringbuffer.append(toHexChar(byte0 >> 4));
            stringbuffer.append(toHexChar(byte0));
            if (spaceFlag)
              stringbuffer.append(' ');
            if (asciiFlag) {
              if (byte0 >= 0x20 && byte0 <= 0x7E)
                asciibuffer.append((char)byte0);
              else
                asciibuffer.append('.');
            }
          } else {
            stringbuffer.append(' ');
            stringbuffer.append(' ');
            if (spaceFlag)
              stringbuffer.append(' ');
          }
        }
        if (asciiFlag)
          printString = stringbuffer.toString() + "; " + asciibuffer.toString();
        else
          printString = stringbuffer.toString();
//        printLine(printString);
        outMsg =  outMsg + printString + "\n";
      } else {
        break;
      }
    }
    printString = null;
    stringbuffer = asciibuffer = null;
    return outMsg;
  }



}
