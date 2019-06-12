package com.viscum.pay.model.response.wxpay.risk;

import com.viscum.pay.model.response.BaseResponse;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p>
 * 获取微信公钥
 * <p>
 *
 * @author fenglei
 * @since 2019-05-13
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class WxGetpublickeyResponse extends BaseResponse {
    /**
     * 微信支付分配的商户号
     */
    @XmlElement(name = "mch_id")
    private String mchId;
    /**
     * RSA 公钥
     */
    @XmlElement(name = "pub_key")
    private String pubKey;

}
