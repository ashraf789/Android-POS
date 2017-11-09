package net.londatiga.android.bluebamboo.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * File Operation Class
 * @author guhx
 *
 */
public class FileOperation {
	
	private Context ct;
	public static String Folder_pub = "public";
	public static String Folder_images = "images";
	public static String Folder_text = "text";
	public static String Folder_icon = "icons";
	
	
	
	public FileOperation(){
		
	}
	
	public FileOperation(Context ct){
		this.ct = ct;
		//makeDir();
	}
	
	
	/**
	 * make folder
	 */
	@SuppressWarnings("deprecation")
	public void makeDir(){
		try{
			File fl = ct.getDir(Folder_pub, Context.MODE_WORLD_WRITEABLE);
			if(!fl.exists()){
				fl.mkdir();
			}
			
			File fl1 = ct.getDir(Folder_images, Context.MODE_WORLD_WRITEABLE);
			if(!fl1.exists()){
				fl1.mkdir();
			}
			
			File fl2 = ct.getDir(Folder_text, Context.MODE_WORLD_WRITEABLE);
			if(!fl2.exists()){
				fl2.mkdir();
			}
			
			File fl3 = ct.getDir(Folder_icon, Context.MODE_WORLD_WRITEABLE);
			if(!fl3.exists()){
				fl3.mkdir();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * copy file
	 * @param src
	 * @param desc
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public boolean copyFile(int resourceId,String folder){
		try{
			if(resourceId != 0){
				
				InputStream src = ct.getResources().openRawResource(resourceId); //��������raw������������������������������
				String filename = ct.getResources().getResourceEntryName(resourceId);//get file name;
				
				filename = filename+".png";
				Log.i("copy file file name====>",filename);
		        byte b[] = new byte[src.available()];
		        int temp = 0;
		        int file = 0;

		        while((temp=src.read())!=-1){    //get file length   
			        b[file]=(byte)temp;   
			        file ++;
			    }
		        
		        src.close();   
				File fl = ct.getFileStreamPath( filename );
				Log.i("File path",fl.getAbsolutePath());
				if(!fl.exists()){
					FileOutputStream stream = ct.openFileOutput( filename, Context.MODE_WORLD_WRITEABLE);
					stream.write(b);
					stream.flush();
					stream.close();
					b=null;
				}else{
					//already have file
					Log.i("already have file!","already have file");
				}
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings("deprecation")
	public boolean copyXmlFile(int resourceId,String folder){
		try{
			if(resourceId != 0){
				
				InputStream src = ct.getResources().openRawResource(resourceId); //��������raw������������������������������
				String filename = ct.getResources().getResourceEntryName(resourceId);//get file name;
				
				filename = filename+".xml";
				Log.i("copy file file name====>",filename);
		        byte b[] = new byte[src.available()];
		        int temp = 0;
		        int file = 0;

		        while((temp=src.read())!=-1){    //get file length   
			        b[file]=(byte)temp;   
			        file ++;
			    }
		        
		        src.close();   
				File fl = ct.getFileStreamPath( filename );
				if(!fl.exists()){
					FileOutputStream stream = ct.openFileOutput( filename, Context.MODE_WORLD_WRITEABLE);
					stream.write(b);
					stream.flush();
					stream.close();
					b=null;
				}else{
					//already have file
					Log.i("already have file!","already have file");
				}
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings("deprecation")
	public boolean copyHtmlFile(int resourceId,String folder){
		try{
			if(resourceId != 0){
				
				InputStream src = ct.getResources().openRawResource(resourceId); //��������raw������������������������������
				String filename = ct.getResources().getResourceEntryName(resourceId);//get file name;
				
				filename = filename+".htm";
				Log.i("copy file file name====>",filename);
		        byte b[] = new byte[src.available()];
		        int temp = 0;
		        int file = 0;

		        while((temp=src.read())!=-1){    //get file length   
			        b[file]=(byte)temp;   
			        file ++;
			    }
		        
		        src.close();   
				File fl = ct.getFileStreamPath( filename );
				if(!fl.exists()){
					FileOutputStream stream = ct.openFileOutput( filename, Context.MODE_WORLD_WRITEABLE);
					stream.write(b);
					stream.flush();
					stream.close();
					b=null;
				}else{
					//already have file
					Log.i("already have file!","already have file");
				}
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings("deprecation")
	public boolean copyCssFile(int resourceId,String folder){
		try{
			if(resourceId != 0){
				
				InputStream src = ct.getResources().openRawResource(resourceId); //��������raw������������������������������
				String filename = ct.getResources().getResourceEntryName(resourceId);//get file name;
				
				filename = filename+".css";
				Log.i("copy file file name====>",filename);
		        byte b[] = new byte[src.available()];
		        int temp = 0;
		        int file = 0;

		        while((temp=src.read())!=-1){    //get file length   
			        b[file]=(byte)temp;   
			        file ++;
			    }
		        
		        src.close();   
				File fl = ct.getFileStreamPath( filename );
				if(!fl.exists()){
					FileOutputStream stream = ct.openFileOutput( filename, Context.MODE_WORLD_WRITEABLE);
					stream.write(b);
					stream.flush();
					stream.close();
					b=null;
				}else{
					//already have file
					Log.i("already have file!","already have file");
				}
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	/**
	 * Load local image file
	 * @param filename
	 * @return
	 */
	
	public Bitmap getLocalImageFile(String filename){
		if(filename != null){
			try{
				FileInputStream in =ct.openFileInput(filename);
				Bitmap bm = BitmapFactory.decodeStream(in);
				in.close();
				in = null;
				return bm;
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}else{
			return null;
		}
	}
	
	/**
	 * delete file
	 * @param desc
	 * @return
	 */
	
	public boolean deleteFile(String filename){
		try{
			if(filename != null && filename.length() > 0 ){
				File fl = ct.getFileStreamPath( filename );
				if(fl.exists()){
					fl.delete();
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//write file to phone
	
	@SuppressWarnings("deprecation")
	public boolean writeFiletoPhone(String filename,String content){
		try{
			if(filename != null && content != null){
				File fl = ct.getFileStreamPath( filename );
				if(!fl.exists()){
					byte[] b = content.getBytes();
					FileOutputStream stream = ct.openFileOutput( filename, Context.MODE_WORLD_WRITEABLE);
					stream.write(b);
					stream.flush();
					stream.close();
					b=null;
					return true;
				}else{
					//already have file
					Log.i("write file error!","already have file");
					//delete and rewrite
					fl.delete();
					byte[] b = content.getBytes();
					FileOutputStream stream = ct.openFileOutput( filename, Context.MODE_WORLD_WRITEABLE);
					stream.write(b);
					stream.flush();
					stream.close();
					b=null;
					return true;
				}
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings("deprecation")
	public boolean writeHtmltoPhone(String filename,String content){
		try{
			if(filename != null && content != null){
				File fl = ct.getFileStreamPath( filename );
				if(!fl.exists()){
					byte[] b = content.getBytes();
					FileOutputStream stream = ct.openFileOutput( filename, Context.MODE_WORLD_WRITEABLE);
					stream.write(b);
					stream.flush();
					stream.close();
					b=null;
					return true;
				}else{
					//already have file
					//Log.i("write file error!","write file error");
					fl.delete();
					byte[] b = content.getBytes();
					FileOutputStream stream = ct.openFileOutput( filename, Context.MODE_WORLD_WRITEABLE);
					stream.write(b);
					stream.flush();
					stream.close();
					b=null;
					return true;
				}
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//got local file size
	public long getLocalFileSize(String filename){
		try{
			if(filename != null){
				File fl = ct.getFileStreamPath( filename );
				if(fl.exists()){
					return fl.length();
				}else{
					//already have file
					Log.i("get file size error!","get file size error");
					return 0;
				}
			}else{
				return 0;
			}
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	
	/**
	 * compare with local xml file and confirm which data should be update
	 * @param version
	 * @return
	 */
	public boolean needToUpdate(String resourcename,String version){
		
		
		
		return true;
		
	}
	
	//compare local file and remote files' size
	public boolean comparefileSize(String localfilename,String remoteurl){
		long localfile = 0;
		long remotefile = 0;
		
		long lastmodifylocal = 0;
		long lastmodifyremote = 0;
		try{
			if(localfilename != null && remoteurl != null){
				  File fl = ct.getFileStreamPath( localfilename );
				  if(fl.exists()){
					  localfile = fl.length();
					  lastmodifylocal = fl.lastModified();
				  }else{
					  localfile = 0;
					  return false;
				  }
				  
				  URLConnection connect = null;
				  URL newUrl=new URL(remoteurl);
				  connect=newUrl.openConnection();
				  connect.setRequestProperty ("Content-type","application/x-www-form-urlencoded");
				  
				  connect.connect();
				  
				 
				
				  remotefile = connect.getContentLength();
				  lastmodifyremote = connect.getLastModified();
				  Log.i("local file size",localfile+"");
				  Log.i("remote file size",remotefile + "");
				  
				  Log.i("last modify local ",lastmodifylocal+"");
				  Log.i("last modify remote ",lastmodifyremote + "");
				  
				  if(localfile == remotefile){
					  return true;
				  }else{
					  return false;
				  }
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * DownLoad file
	 * @param url
	 * @return
	 */
	@SuppressWarnings({ "unused", "deprecation" })
	public boolean downLoadFile(String url,String localfilename){
		int   fileSize;
		int   downLoadFileSize;
	try{
    	URL myURL = new URL(url);
    	URLConnection conn = myURL.openConnection();
    	conn.connect();
    	InputStream is = conn.getInputStream();
	    fileSize = conn.getContentLength();
	    
	    if (fileSize <= 0) throw new RuntimeException("File size is 0 ");
	    if (is == null) throw new RuntimeException("stream is null");
	   // FileOutputStream fos = new FileOutputStream(path + filename);
	    FileOutputStream fos = ct.openFileOutput(localfilename, Context.MODE_WORLD_WRITEABLE);
	    byte buf[] = new byte[1024];
	    downLoadFileSize = 0;
	    //sendMsg(0);
	    do
	      {
	        int numread = is.read(buf);
	        if (numread == -1)
	        {
	          break;
	        }
	        fos.write(buf, 0, numread);
	        downLoadFileSize += numread;
	      } while (true);
	    try
	      {
	        is.close();
	      } catch (Exception ex)
	      {
	        Log.e("tag", "error: " + ex.getMessage(), ex);
	      }
	}catch(Exception e){
		e.printStackTrace();
		return false;
	}
		return true;
	}
	
	public static byte[] getBytesFromAssets(Context ctx,String file_name) throws IOException {
		
	    AssetManager assetManager = ctx.getAssets();
	    ByteArrayOutputStream outputStream = null;
	    InputStream inputStream = null;
	    try {
	        inputStream = assetManager.open(file_name);
	        outputStream = new ByteArrayOutputStream();
	        byte buf[] = new byte[1024];
	        int len;
	        try {
	            while ((len = inputStream.read(buf)) != -1) {
	                outputStream.write(buf, 0, len);
	            }
	            outputStream.close();
	            inputStream.close();
	        } catch (IOException e) {
	        	throw e;
	        }
	    } catch (IOException e) {
	    	throw e;
	    }
	    
	    return outputStream.toByteArray();

	}
}
