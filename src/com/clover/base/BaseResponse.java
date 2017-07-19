package com.clover.base;

import java.io.Serializable;

/**
 * 返回结果封装
 * @author zhangdq
 * @time 2017-7-19 上午10:33:34
 * @Email qiang900714@126.com
 */
public class BaseResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String resultCode;

	private String resultMessage;

	private Object result;

	public BaseResponse() {

	}

	public BaseResponse(String resultCode, String resultMessage) {
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
