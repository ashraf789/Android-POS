package net.londatiga.android.bluebamboo;

import net.londatiga.android.bluebamboo.pockdata.PocketPos;

import net.londatiga.android.bluebamboo.util.DateUtil;
import net.londatiga.android.bluebamboo.util.FontDefine;
import net.londatiga.android.bluebamboo.util.Printer;

import android.app.Activity;
import android.app.ProgressDialog;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;

/**
 * Demo Blue Bamboo P25 Thermal Printer.
 *
 * @author Lorensius W. L. T <lorenz@londatiga.net>
 *
 */
public class MainActivityPrinter extends Activity {

	private Button mConnectBtn;
	private Button mEnableBtn;
	private Button btnPrint;
	private Button btnCancel;
	private Spinner mDeviceSp;

	private ProgressDialog mProgressDlg;
	private ProgressDialog mConnectingDlg;

	private BluetoothAdapter mBluetoothAdapter;

	private P25Connector mConnector;

	private ArrayList<BluetoothDevice> mDeviceList = new ArrayList<BluetoothDevice>();

	private String data = "";
	private PrintSellInfo printINfo;
	private TextView sellerNameTv,sellerPhoneTv,sellerEmailTv,customerNameTv,customerPhoneTv,customerEmialTv,
			invoiceTv,totalAmountTv,dueTv,productsName,productQuantityTv,productPriceTv,totalBillTv,  discountTv, payableTv,depositTv,currentDueTv;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_printer);
		initializeAll();//initialize all variable
		try {
			printINfo = (PrintSellInfo) getIntent().getSerializableExtra("printData");
			data = printINfo.getProductsName();

			setWedgedData();
		}catch (Exception e){

		}

		if (mBluetoothAdapter == null) {
			showUnsupported();
		} else {
			if (!mBluetoothAdapter.isEnabled()) {
				showDisabled();
			} else {
				showEnabled();

				Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

				if (pairedDevices != null) {
					mDeviceList.addAll(pairedDevices);

					updateDeviceList();
				}
			}

			mProgressDlg 	= new ProgressDialog(this);

			mProgressDlg.setMessage("Scanning...");
			mProgressDlg.setCancelable(false);
			mProgressDlg.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();

					mBluetoothAdapter.cancelDiscovery();
				}
			});

			mConnectingDlg 	= new ProgressDialog(this);

			mConnectingDlg.setMessage("Connecting...");
			mConnectingDlg.setCancelable(false);

			mConnector 		= new P25Connector(new P25Connector.P25ConnectionListener() {

				@Override
				public void onStartConnecting() {
					mConnectingDlg.show();
				}

				@Override
				public void onConnectionSuccess() {
					mConnectingDlg.dismiss();

					showConnected();
				}

				@Override
				public void onConnectionFailed(String error) {
					mConnectingDlg.dismiss();
				}

				@Override
				public void onConnectionCancelled() {
					mConnectingDlg.dismiss();
				}

				@Override
				public void onDisconnected() {
					showDisonnected();
				}
			});

			//enable bluetooth
			mEnableBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

					startActivityForResult(intent, 1000);
				}
			});

			//connect/disconnect
			mConnectBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					connect();
				}
			});
			btnPrint.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					printMessage();
				}
			});

			btnCancel.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					finish();
				}
			});
		}



		IntentFilter filter = new IntentFilter();

		filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
		filter.addAction(BluetoothDevice.ACTION_FOUND);
		filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
		filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);

		registerReceiver(mReceiver, filter);
	}

	private void setWedgedData() {
		sellerNameTv.setText(printINfo.getSellerNameTv());
		sellerPhoneTv.setText(printINfo.getSellerPhoneTv());
		sellerEmailTv.setText(printINfo.getSellerEmailTv());
		customerNameTv.setText(printINfo.getCustomerNameTv());
		customerPhoneTv.setText(printINfo.getCustomerPhoneTv());
		customerEmialTv.setText(printINfo.getCustomerEmialTv());
		invoiceTv.setText(printINfo.getInvoiceTv());
		totalAmountTv.setText("Paid: "+printINfo.getDepositTv()+" Tk");
		dueTv.setText("Due: "+printINfo.getDueTv()+" Tk");
		productsName.setText(printINfo.getProductsName());
		productQuantityTv.setText(printINfo.getProductQuantityTv());
		productPriceTv.setText(printINfo.getProductPriceTv());
		totalBillTv.setText("Total Bill: "+printINfo.getTotalBillTv()+" Tk");
		discountTv.setText("Discount : "+printINfo.getDiscount()+" Tk");
		payableTv.setText("Payable : "+printINfo.getPayableTv()+" Tk");
		depositTv.setText("Deposit: "+printINfo.getDepositTv()+" Tk");
		currentDueTv.setText("DUE: "+printINfo.getCurrentDueTv()+" Tk");
	}

	public void initializeAll(){
		mConnectBtn			= (Button) findViewById(R.id.btn_connect);
		mEnableBtn			= (Button) findViewById(R.id.btn_enable);
		btnPrint 		= (Button) findViewById(R.id.btnPrint);
		btnCancel 	= (Button) findViewById(R.id.btnCancel);
		mDeviceSp 			= (Spinner) findViewById(R.id.sp_device);




		sellerNameTv = (TextView) findViewById(R.id.sellerNameTv);
		sellerPhoneTv = (TextView) findViewById(R.id.sellerPhoneTv);
		sellerEmailTv = (TextView) findViewById(R.id.sellerEmailTv);
		customerNameTv = (TextView) findViewById(R.id.customerNameTv);
		customerPhoneTv = (TextView) findViewById(R.id.customerPhoneTv);
		customerEmialTv = (TextView) findViewById(R.id.customerEmialTv);
		invoiceTv = (TextView) findViewById(R.id.invoiceTv);
		totalAmountTv = (TextView) findViewById(R.id.totalAmountTv);
		dueTv = (TextView) findViewById(R.id.dueTv);
		productsName = (TextView) findViewById(R.id.productsName);
		productQuantityTv = (TextView) findViewById(R.id.productQuantityTv);
		productPriceTv = (TextView) findViewById(R.id.productPriceTv);
		totalBillTv = (TextView) findViewById(R.id.totalBillTv);
		discountTv = (TextView) findViewById(R.id.discountTv);
		payableTv = (TextView) findViewById(R.id.payableTv);
		depositTv = (TextView) findViewById(R.id.depositTv);
		currentDueTv = (TextView) findViewById(R.id.currentDueTv);

		printINfo = new PrintSellInfo();
		mBluetoothAdapter	= BluetoothAdapter.getDefaultAdapter();
	}

	public void printMessage(){

		String titleStr	= "******Seller*****" + "\n"+
				printINfo.getSellerNameTv() + "\n"+
				printINfo.getSellerEmailTv() + "\n"+
				printINfo.getSellerPhoneTv() + "\n\n"+
				"******Customer*****" + "\n"+
				printINfo.getCustomerNameTv() + "\n"+
				printINfo.getCustomerEmialTv() + "\n"+
				printINfo.getCustomerPhoneTv() + "\n\n";

		String subTitle = "Invoice no: "+printINfo.getInvoiceTv()+"     Paid: "+printINfo.getDepositTv()+
				" Tk"+"  Due: "+printINfo.getDueTv()+" Tk";
		StringBuilder contentSb	= new StringBuilder();

		contentSb.append(printINfo.getProductQuantityTv()+" "+printINfo.getProductsName() +"     "+ printINfo.getProductPriceTv()+"\n\n\n");

		StringBuilder content2Sb = new StringBuilder();

		content2Sb.append("Total Bill : "+printINfo.getTotalBillTv()+" Tk "+"   "+"Discount : "+printINfo.getDiscount()+" Tk\n");

		content2Sb.append("Payable : "+printINfo.getPayableTv()+" Tk "+"   "+"Deposit : "+printINfo.getDepositTv()+" Tk\n\n");

		String message = "DUE: "+printINfo.getCurrentDueTv()+" Tk \n\n";


		long milis		= System.currentTimeMillis();
		String date		= DateUtil.timeMilisToString(milis, "dd-MM-yy / HH:mm")  + "\n\n";

		byte[] titleByte	= Printer.printfont(titleStr, FontDefine.FONT_24PX,FontDefine.Align_CENTER,
				(byte)0x1A, PocketPos.LANGUAGE_ENGLISH);
		byte[] subTitleByte	= Printer.printfont(subTitle, FontDefine.FONT_24PX,FontDefine.Align_CENTER,
				(byte)0x1A, PocketPos.LANGUAGE_ENGLISH);


		byte[] content1Byte	= Printer.printfont(contentSb.toString(), FontDefine.FONT_24PX,FontDefine.Align_LEFT,
				(byte)0x1A, PocketPos.LANGUAGE_ENGLISH);

		byte[] content2Byte	= Printer.printfont(content2Sb.toString(), FontDefine.FONT_24PX,FontDefine.Align_LEFT,
				(byte)0x1A, PocketPos.LANGUAGE_ENGLISH);

		byte[] messageByte	= Printer.printfont(message, FontDefine.FONT_24PX,FontDefine.Align_CENTER,  (byte)0x1A,
				PocketPos.LANGUAGE_ENGLISH);


		byte[] dateByte		= Printer.printfont(date, FontDefine.FONT_24PX,FontDefine.Align_LEFT, (byte)0x1A,
				PocketPos.LANGUAGE_ENGLISH);

		byte[] totalByte	= new byte[titleByte.length +subTitleByte.length + content1Byte.length + content2Byte.length +messageByte.length +
				dateByte.length];


		int offset = 0;
		System.arraycopy(titleByte, 0, totalByte, offset, titleByte.length);
		offset += titleByte.length;

		System.arraycopy(subTitleByte, 0, totalByte, offset, subTitleByte.length);
		offset += subTitleByte.length;

		System.arraycopy(content1Byte, 0, totalByte, offset, content1Byte.length);
		offset += content1Byte.length;

		System.arraycopy(content2Byte, 0, totalByte, offset, content2Byte.length);
		offset += content2Byte.length;

		System.arraycopy(messageByte, 0, totalByte, offset, messageByte.length);
		offset += messageByte.length;

		System.arraycopy(dateByte, 0, totalByte, offset, dateByte.length);

		byte[] sendData = PocketPos.FramePack(PocketPos.FRAME_TOF_PRINT, totalByte, 0, totalByte.length);

		sendData(sendData);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_scan) {
			mBluetoothAdapter.startDiscovery();
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onPause() {
		if (mBluetoothAdapter != null) {
			if (mBluetoothAdapter.isDiscovering()) {
				mBluetoothAdapter.cancelDiscovery();
			}
		}

		if (mConnector != null) {
			try {
				mConnector.disconnect();
			} catch (P25ConnectionException e) {
				e.printStackTrace();
			}
		}

		super.onPause();
	}

	@Override
	public void onDestroy() {
		unregisterReceiver(mReceiver);

		super.onDestroy();
	}

	private String[] getArray(ArrayList<BluetoothDevice> data) {
		String[] list = new String[0];

		if (data == null) return list;

		int size	= data.size();
		list		= new String[size];

		for (int i = 0; i < size; i++) {
			list[i] = data.get(i).getName();
		}

		return list;
	}

	private void showToast(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
	}

	private void updateDeviceList() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.simple_spinner_item, getArray(mDeviceList));

		adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);

		mDeviceSp.setAdapter(adapter);
		mDeviceSp.setSelection(0);
	}

	private void showDisabled() {
		showToast("Bluetooth disabled");

		mEnableBtn.setVisibility(View.VISIBLE);
		mConnectBtn.setVisibility(View.GONE);
		mDeviceSp.setVisibility(View.GONE);
	}

	private void showEnabled() {
		showToast("Bluetooth enabled");

		mEnableBtn.setVisibility(View.GONE);
		mConnectBtn.setVisibility(View.VISIBLE);
		mDeviceSp.setVisibility(View.VISIBLE);
	}

	private void showUnsupported() {
		showToast("Bluetooth is unsupported by this device");

		mConnectBtn.setEnabled(false);
		btnPrint.setEnabled(false);
		mDeviceSp.setEnabled(false);
	}

	private void showConnected() {
		showToast("Connected");

		mConnectBtn.setText("Disconnect");

		btnPrint.setEnabled(true);

		mDeviceSp.setEnabled(false);
	}

	private void showDisonnected() {
		showToast("Disconnected");

		mConnectBtn.setText("Connect");

		btnPrint.setEnabled(false);
		mDeviceSp.setEnabled(true);
	}

	private void connect() {
		if (mDeviceList == null || mDeviceList.size() == 0) {
			return;
		}

		BluetoothDevice device = mDeviceList.get(mDeviceSp.getSelectedItemPosition());

		if (device.getBondState() == BluetoothDevice.BOND_NONE) {
			try {
				createBond(device);
			} catch (Exception e) {
				showToast("Failed to pair device");

				return;
			}
		}

		try {
			if (!mConnector.isConnected()) {
				mConnector.connect(device);
			} else {
				mConnector.disconnect();

				showDisonnected();
			}
		} catch (P25ConnectionException e) {
			e.printStackTrace();
		}
	}

	private void createBond(BluetoothDevice device) throws Exception {

		try {
			Class<?> cl 	= Class.forName("android.bluetooth.BluetoothDevice");
			Class<?>[] par 	= {};

			Method method 	= cl.getMethod("createBond", par);

			method.invoke(device);

		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	private void sendData(byte[] bytes) {
		try {
			mConnector.sendData(bytes);
		} catch (P25ConnectionException e) {
			e.printStackTrace();
		}
	}


	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();

			if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
				final int state 	= intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);

				if (state == BluetoothAdapter.STATE_ON) {
					showEnabled();
				} else if (state == BluetoothAdapter.STATE_OFF) {
					showDisabled();
				}
			} else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
				mDeviceList = new ArrayList<BluetoothDevice>();

				mProgressDlg.show();
			} else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
				mProgressDlg.dismiss();

				updateDeviceList();
			} else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
				BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

				mDeviceList.add(device);

				showToast("Found device " + device.getName());
			} else if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
				final int state = intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, BluetoothDevice.ERROR);

				if (state == BluetoothDevice.BOND_BONDED) {
					showToast("Paired");

					connect();
				}
			}
		}
	};

}