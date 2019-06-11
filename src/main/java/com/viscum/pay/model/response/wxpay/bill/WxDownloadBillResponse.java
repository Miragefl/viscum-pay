package com.viscum.pay.model.response.wxpay.bill;

import com.viscum.pay.model.response.BaseResponse;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 下载账单返回参数 失败时返回
 */
@Data
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WxDownloadBillResponse extends BaseResponse {
    /**
     * 返回状态码
     */
    @XmlElement(name = "return_code")
    private String returnCode;
    /**
     * 错误码描述
     */
    @XmlElement(name = "return_message")
    private String returnMessage;
    /**
     * 错误码
     */
    @XmlElement(name = "error_code")
    private String errorCode;

}
