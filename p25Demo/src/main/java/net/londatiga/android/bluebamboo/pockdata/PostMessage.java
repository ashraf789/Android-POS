package net.londatiga.android.bluebamboo.pockdata;

public class PostMessage {

	public class TimeOutType {
		public static final int TEST = 20;
		public static final int PATH = 180;
		public static final int ACKNAK = 50;
		public static final int OTHER = 40;
	}

	public enum ReturnType {
		NONE, ACK, NAK, PATH, // ��������
		Integrity
		// ��������������
	}

	private ReturnType returnType;
	private String errorInfo;
	private byte[] content;

	public ReturnType getReturnType() {
		return returnType;
	}

	public void setReturnType(ReturnType returnType) {
		this.returnType = returnType;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

}
