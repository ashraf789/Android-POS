package net.londatiga.android.bluebamboo.util;

/*
 * Copyright ?2007-2011 Blue Bamboo Ltd. 
 * 
 */
/*
 * Copyright ?2007-2011 Blue Bamboo Ltd. 
 * 
 */

/**
 * This class hold all common controlling variables
 * 
 * @author <B>Nelson</B>
 * 
 */
public class AppParameters
{
	public static String VERSION = "";

	public static String hardwareVersion = "G3";
	public static String softwareVersion = "Android";
	public static String printerSN = "13701886760";
	
	/** Specify application in debug mode or not.*/
	public static boolean isDebugMode = false;

	/** Specify whether application executing first time or not */
	public static boolean isFirstTime = true;

	/** Keep track of status blutooth detected device.*/ 
	public static boolean hasBluetoothDetected = false;//false;
	
	/**
	 * Basic Constructor
	 */
	public AppParameters()
	{

	}
	
	
}
