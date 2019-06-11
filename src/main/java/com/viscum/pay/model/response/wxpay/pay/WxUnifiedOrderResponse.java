package com.viscum.pay.model.response.wxpay.pay;

import com.viscum.pay.model.response.BaseResponse;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 微信预下单参数
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class WxUnifiedOrderResponse extends BaseResponse {
    /**
     * 返回状态码
     */
    @XmlElement(name = "return_code")
    private String returnCode;
    /**
     * 返回信息
     */
    @XmlElement(name = "return_msg")
    private String returnMsg;
    /**
     * 应用APPID
     */
    @XmlElement(name = "appid")
    private String appId;
    /**
     * 商户号
     */
    @XmlElement(name = "mch_id")
    private String mchId;
    /**
     * 设备号
     */
    @XmlElement(name = "device_info")
    private String deviceInfo;
    /**
     * 随机字符串
     */
    @XmlElement(name = "nonce_str")
    private String nonceStr;
    /**
     * 签名
     */
    private String sign;
    /**
     * 业务结果
     */
    @XmlElement(name = "result_code")
    private String resultCode;
    /**
     * 错误代码
     */
    @XmlElement(name = "err_code")
    private String errCode;
    /**
     * 错误代码描述
     */
    @XmlElement(name = "err_code_des")
    private String errCodeDes;
    /**
     * 交易类型
     */
    @XmlElement(name = "trade_type")
    private String tradeType;
    /**
     * 预支付交易会话标识
     */
    @XmlElement(name = "prepay_id")
    private String prepayId;
    /**
     * mweb_url为拉起微信支付收银台的中间页面，可通过访问该url来拉起微信客户端，完成支付,mweb_url的有效期为5分钟。
     */
    @XmlElement(name = "mweb_url")
    private String mwebUrl;

}
