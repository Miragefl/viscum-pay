package com.viscum.pay.model.request.alipay.transfer;

import com.viscum.pay.base.AliConstant;
import com.viscum.pay.enums.PayeeType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.model.request.alipay.AliRequest;
import com.viscum.pay.model.response.alipay.transfer.AlipayFundTransToaccountTransferResponse;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 单笔转账
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlipayTundTransToaccountTransferRequest implements AliRequest<AlipayFundTransToaccountTransferResponse> {
    /**
     * 商户转账唯一订单号。发起转账来源方定义的转账单据ID，用于将转账回执通知给来源方。
     * 不同来源方给出的ID可以重复，同一个来源方必须保证其ID的唯一性。
     * 只支持半角英文、数字，及“-”、“_”。
     */
    @JsonProperty("out_biz_no")
    private String outBizNo;
    /**
     * 收款方账户类型。可取值：
     * 1、ALIPAY_USERID：支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。
     * 2、ALIPAY_LOGONID：支付宝登录号，支持邮箱和手机号格式。
     */
    @JsonProperty("payee_type")
    private PayeeType payeeType;
    /**
     * 收款方账户。与payee_type配合使用。付款方和收款方不能是同一个账户。
     */
    @JsonProperty("payee_account")
    private String payeeAccount;
    /**
     * 转账金额，单位：元。
     * 只支持2位小数，小数点前最大支持13位，金额必须大于等于0.1元。
     * 最大转账金额以实际签约的限额为准。
     */
    private String amount;
    /**
     * 付款方姓名（最长支持100个英文/50个汉字）。显示在收款方的账单详情页。如果该字段不传，则默认显示付款方的支付宝认证姓名或单位名称。
     */
    @JsonProperty("payer_show_name")
    private String payerShowName;
    /**
     * 收款方真实姓名（最长支持100个英文/50个汉字）。
     * 如果本参数不为空，则会校验该账户在支付宝登记的实名是否与收款方真实姓名一致。
     */
    @JsonProperty("payee_real_name")
    private String payeeRealName;
    /**
     * 转账备注（支持200个英文/100个汉字）。
     * 当付款方为企业账户，且转账金额达到（大于等于）50000元，remark不能为空。收款方可见，会展示在收款用户的收支详情中。
     */
    private String remark;

    public AlipayTundTransToaccountTransferRequest(@NotBlank String outBizNo, PayeeType payeeType, @NotBlank String payeeAccount, @Pattern(regexp = "(?!^0*(\\.0{1,2})?$)^\\d{1,13}(\\.\\d{1,2})?$", message = "请输入正确的金额") String amount, String remark) {
        this.outBizNo = outBizNo;
        this.payeeType = payeeType;
        this.payeeAccount = payeeAccount;
        this.amount = amount;
        this.remark = remark;
    }

    public AlipayTundTransToaccountTransferRequest(@NotBlank String outBizNo, PayeeType payeeType, @NotBlank String payeeAccount, @Pattern(regexp = "(?!^0*(\\.0{1,2})?$)^\\d{1,13}(\\.\\d{1,2})?$", message = "请输入正确的金额") String amount, String remark, String payerShowName, String payeeRealName) {
        this.outBizNo = outBizNo;
        this.payeeType = payeeType;
        this.payeeAccount = payeeAccount;
        this.amount = amount;
        this.payerShowName = payerShowName;
        this.payeeRealName = payeeRealName;
        this.remark = remark;
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String getMethod() {
        return AliConstant.TRANSFER_TOACCOUNT;
    }

    @Override
    public Class<AlipayFundTransToaccountTransferResponse> getResponseClass() {
        return AlipayFundTransToaccountTransferResponse.class;
    }
}
