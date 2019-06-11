package com.viscum.pay.base;

import org.springframework.stereotype.Component;


/**
 * @author qiangl
 *
 */
@Component
public class Standard {
	
	/**
	 * 成功
	 */
	public static final String RET_SUCC="000000";
	/**
	 * 失败
	 */
	public static final String RET_FAIL="999999";
	/**
	 * 查询数据不存在
	 */
	public static final String NOT_FIND_MSG="查询数据不存在";
	/**
	 * 字段-errCode
	 */
	public static final String FEILD_ERRCODE="errCode";
	/**
	 * 字段-errMsg
	 */
	public static final String FEILD_ERRMSG="errMsg";

	public static final String FORMAT_JSON = "json";
	public static final String FORMAT_XML = "xml";
	public static final String FORMAT_FORM = "form";

	/**
	 * 默认配置的是UTF-8
	 */
	public static final String ENCODING_UTF8 = "UTF-8";
	public static final String ENCODING_GBK = "GBK";

	public static final String HTTP_GET = "GET";
	public static final String HTTP_POST = "POST";





}

