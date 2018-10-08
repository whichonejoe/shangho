package com.shangho.utils.status;

public interface APIStatus {
	public final static int SUCCESS = 0;

	public final static int HTTP_METHOD_FAIL = 101;

	public final static int ILLEGAL_ARGUMENT = 102;

	public final static int LOG_NOT_SETTING = 103;

	public final static int FAIL_AUTHORIZATION = 104;

	public final static int FAIL_AUTHENTICATION = 105;

	public final static int DATA_DOES_NOT_EXIST = 106;

	public final static int DATA_DOES_EXIST = 107;

	public final static int FAIL_OPERATE_PERMISSION = 108;

	public final static int IP_NOT_ALLOWED = 109;

	public final static int NETWORK_FAIL = 110;

	public final static int GENERAL_ERROR = 9999;

	public final static String MSG_SUCCESS = "success";
}
