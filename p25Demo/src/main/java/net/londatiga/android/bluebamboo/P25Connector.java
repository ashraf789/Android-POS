package net.londatiga.android.bluebamboo;

import net.londatiga.android.bluebamboo.util.StringUtil;

import java.io.IOException;
import java.io.OutputStream;

import java.net.URL;
import java.util.UUID;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import android.os.AsyncTask;
import android.util.Log;

/**
 * P25 printer connection class.
 * 
 * @author Lorensius W. L. T <lorenz@londatiga.net>
 *
 */
public class P25Connector {
	private BluetoothSocket mSocket;	
	private OutputStream mOutputStream;
	
	private ConnectTask mConnectTask;
	private P25ConnectionListener mListener;
	
	private boolean mIsConnecting = false;
	
	private static final String TAG = "P25";	
	private static final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
	
	public P25Connector(P25ConnectionListener listener) {
		mListener = listener;
	}
	
	public boolean isConnecting() {
		return mIsConnecting;
	}
	
	public boolean isConnected() {
		return (mSocket == null) ? false : true;
	}
	
	public void connect(BluetoothDevice device) throws P25ConnectionException {
		if (mIsConnecting && mConnectTask != null) {
			throw new P25ConnectionException("Connection in progress");
		}
		
		if (mSocket != null) {
			throw new P25ConnectionException("Socket already connected");
		}
		
		(mConnectTask = new ConnectTask(device)).execute();
	}
	
	public void disconnect() throws P25ConnectionException {
		if (mSocket == null) {
			throw new P25ConnectionException("Socket is not connected");
		}
		
		try {
			mSocket.close();
			
			mSocket = null;
			
			mListener.onDisconnected();
		} catch (IOException e) {
			throw new P25ConnectionException(e.getMessage());
		}
	}
	
	public void cancel() throws P25ConnectionException {
		if (mIsConnecting && mConnectTask != null) {
			mConnectTask.cancel(true);
		} else {
			throw new P25ConnectionException("No connection is in progress");
		}
	}
	
	public void sendData(byte[] msg) throws P25ConnectionException {
		if (mSocket == null) {
			throw new P25ConnectionException("Socket is not connected, try to call connect() first");
		}
			
		try {
			mOutputStream.write(msg);
			mOutputStream.flush();

			Log.i(TAG, StringUtil.byteToString(msg));					
		} catch(Exception e) {
			throw new P25ConnectionException(e.getMessage());
		}
	}
	
	public interface P25ConnectionListener {
		public abstract void onStartConnecting();
		public abstract void onConnectionCancelled();
		public abstract void onConnectionSuccess();
		public abstract void onConnectionFailed(String error);
		public abstract void onDisconnected();
	}
	
	public class ConnectTask extends AsyncTask<URL, Integer, Long> {
		BluetoothDevice device;
		String error = "";
		
		public ConnectTask(BluetoothDevice device) {
			this.device = device;
		}
		
		protected void onCancelled() {
			mIsConnecting = false;
			
			mListener.onConnectionCancelled();
		}
		
    	protected void onPreExecute() {
    		mListener.onStartConnecting();
    		
    		mIsConnecting = true;
    	}
    
        protected Long doInBackground(URL... urls) {         
            long result = 0;
            
            try {
            	mSocket			= device.createRfcommSocketToServiceRecord(UUID.fromString(SPP_UUID));
            	
            	mSocket.connect(); 
            	
            	mOutputStream	= mSocket.getOutputStream();
            	
            	result = 1;
            } catch (IOException e) { 
            	e.printStackTrace();
            	
            	error = e.getMessage();
            }
            
            return result;
        }

        protected void onProgressUpdate(Integer... progress) {              	
        }

        protected void onPostExecute(Long result) {        	
        	mIsConnecting = false;
        	
        	if (mSocket != null && result == 1) {
        		mListener.onConnectionSuccess();
        	} else {
        		mListener.onConnectionFailed("Connection failed " + error);
        	}
        }
    }
}