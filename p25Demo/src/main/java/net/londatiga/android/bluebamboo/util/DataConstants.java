/*
 * Copyright ?2007-2011 Blue Bamboo Ltd. 
 * 
 */
package net.londatiga.android.bluebamboo.util;

/**
 * This class contains all kinds of constant type values.
 * 
 * @author : Nelson
 */
public interface DataConstants
{
	public static final String NEW_LINE = "\n";
	public static final int CREDIT_SWIPE_ACTION = 0;
	public static final String DOT = ".";
	public static final String SPACE = " ";

	/****  Alignment type***********************/
	public static final int CENTER_ALIGN 		   = 0;
	public static final int RIGHT_ALIGN 		   = 1;
	public static final int LEFT_ALIGN 		       = 2;

	/****  Main Menu Item  **************************/
	public static final int SWIPE_OPTION_VALUE 		= 0;
	public static final int PRINT_OPTION_VALUE 		= 1;
	public static final int PIN_OPTION_VALUE 		= 2;
	public static final int IMAGE_OPTION_VALUE 		= 3;
	public static final int SMARTCARD_OPTION_VALUE  = 4;
	public static final int BAR_CODE_VALUE 			= 5;
	
	/*******  device type ***********************/
//	public static final int DEVICE_P25M = 0;
//	public static final int DEVICE_H50 = 1;
//	public static final int DEVICE_HISTORY = 2;
//	public static final int DEVICE_MANUAL = 3;
	
	/*******  Font Type ***********************/
	public static final int FONT_SMALL_FONT 		= 0;//12*24
	public static final int FONT_BIG_FONT 			= 1;//16*32
	public static final int FONT_DOUBLE_SMALL_FONT  = 2;
	public static final int FONT_DOUBLE_BIG_FONT    = 3;
	public static final int FONT_UNDERLINE_SMALL_FONT = 4; 
	public static final int FONT_BIG_REVERSE_FONT 	  = 5;
	
	/*******  language type ***********************/
    public static final int ENGLISH 				= 0;
    public static final int CHINESE 				= 1;
    
	
	public static final int RECEIPT_WIDTH = 24;
	
	public static String RMS_CURRENT_DEVICE_NAME = "currentDeviceName";
	public static String RMS_DEVICE_LIST_KEY = "deviceNameList";
	public static String RMS_PRINTER_INFO = "printerInfo";
	
	public static final int TYPE_BLUETOOTH_DEVICE = 2;
	
	public static final int DEVICE_P25M = 0;
	public static final int DEVICE_H50 = 1;
	public static final int DEVICE_HISTORY = 2;
	public static final int DEVICE_MANUAL = 3;
	public static final int DEVICE_NONE = 4;
}
