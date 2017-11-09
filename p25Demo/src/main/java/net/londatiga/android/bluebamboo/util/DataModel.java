/*
 * Copyright ?2007-2011 Blue Bamboo Ltd. 
 * 
 */
package net.londatiga.android.bluebamboo.util;


/**
 *This Class is used to store data which seperates from UI and can 
 *flow between different screens 
 *
 * @author : Nelson
 */
public class DataModel
{
	public static String accountNum = "";

	public static String amount = "";

	public static String printContent = "";

	public static String debitPin = "";

	public static String keySerialNumber = "";

	public static String smartCardData = "";

	public static String balance = "";

	public static String trackData = "";

	public static String hardwareVersion = "";
	public static String softwareVersion = "";
	public static String printerSN = "";
	
	public static int language = DataConstants.ENGLISH;

	public static int deviceType = DataConstants.DEVICE_P25M;

	public static String deviceName = "";
	
	public static String searchName = "";

	public static int fontSize;

	public static String platformInfo ="";
	
	public static String deviceInfo ="";
	
	public DataModel()
	{
	}

	public static void resetModel()
	{

	}
}
