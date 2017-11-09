package net.londatiga.android.bluebamboo;

import net.londatiga.android.bluebamboo.R;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

import android.content.Context;
import android.content.DialogInterface;

import android.view.LayoutInflater;
import android.view.View;

import android.widget.EditText;

/**
 * Input text dialog.
 * 
 * @author Lorensius W. L. T <lorenz@londatiga.net>
 *
 */
public class InputTextDialog {
	private OnOkClickListener mListener;
	private AlertDialog mDialog;
	
	public InputTextDialog(Context context, OnOkClickListener listener) {
		mListener = listener;
		
		LayoutInflater inflater = LayoutInflater.from(context);
		View view				= inflater.inflate(R.layout.dialog_input, null);
		final EditText textEt	= (EditText) view.findViewById(R.id.editText);
		
		Builder builder 		= new AlertDialog.Builder(context);

		builder.setTitle("Input Text");
		builder.setView(view)  	  	   	  	    
	  	    .setPositiveButton("Print", new DialogInterface.OnClickListener() {
	  	  		@Override
	  	  		public void onClick(DialogInterface dialog, int which) {
	  	  			String text = textEt.getText().toString();
	  	  					
	  	  			if (mListener != null && !text.equals("")) {
	  	  				mListener.onPrintClick(text);
	  	  			}
	  	  		}
	  	    });
	  	  	
		mDialog = builder.create();
	}
	
	public void show() {
    	mDialog.show();
    }
	
	public interface OnOkClickListener {
		public abstract void onPrintClick(String text);
	}
}