package com.viscum.pay.service;

import com.viscum.pay.base.AliConstant;
import com.viscum.pay.exception.PayException;
import com.viscum.pay.base.Standard;
import com.viscum.pay.config.AliPayConfig;
import com.viscum.pay.model.request.alipay.*;
import com.viscum.pay.model.response.alipay.*;
import com.viscum.pay.util.AliPayUtil;
import com.viscum.pay.util.HttpUtil;
import com.viscum.pay.util.JsonParser;
import com.viscum.pay.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * 支付宝支付相关接口
 */
@Slf4j
@Service
public class AliPayService {
    private AliPayConfig aliPayConfig;
    public String url = AliConstant.ALI_SERVER_URL; // 请求地址

    public void setAliPayConfig(AliPayConfig aliPayConfig) {
        this.aliPayConfig = aliPayConfig;
    }

    /**
     * 生成下单地址
     *
     * @param request
     * @return
     * @throws PayException
     * @throws IOException
     */
    public AliTradeAppPayResponse createOrder(AliTradePayRequest request) throws PayException {
        String method = AliConstant.UNIFIED_ORDER;// 接口名称
        String version = "1.0";
        AliCommonParam aliCommonParam = generate(request, method, version);
        String aliSign = AliPayUtil.getAliSign(aliCommonParam, aliPayConfig);
        aliCommonParam.setSign(aliSign);
        String params = AliPayUtil.destUrl(aliCommonParam, aliPayConfig);
        log.info("===========url===========" + url);
        AliTradeAppPayResponse response = new AliTradeAppPayResponse();
//        response.setOrderStr(url);
        response.setParams(params);
        return response;
    }

    /**
     * 查询订单信息
     *
     * @param request
     * @return
     * @throws IOException
     * @throws PayException
     */
    public AliTradeQueryResponse queryOrder(AliTradePayQueryRequest request) throws PayException {
        if (StringUtils.isEmpty(request.getOutTradeNo()) && StringUtils.isEmpty(request.getTradeNo())) {
            throw new PayException("商户订单号,和支付宝交易号不能同时为空");
        }
        String method = AliConstant.TRADE_QUERY;
        String version = "1.0";
        return call(generate(request, method, version), AliTradeQueryResponse.class);
    }

    /**
     * 退款接口
     *
     * @param request
     * @return
     * @throws IOException
     * @throws PayException
     */
    public AliTradeRefundResponse refundOrder(AliTradeRefundRequest request) throws PayException {
        if (StringUtils.isEmpty(request.getOutTradeNo()) && StringUtils.isEmpty(request.getTradeNo())) {
            throw new PayException("支付宝交易号，和商户订单号不能同时为空");
        }
        String method = AliConstant.TRADE_REFUND;
        String version = "1.0";
        return call(generate(request, method, version), AliTradeRefundResponse.class);
    }

    /**
     * 退款查证接口
     *
     * @param request
     * @return
     * @throws PayException
     */
    public AliTradeRefundQueryResponse refundQuery(AliTradeRefundQueryRequest request) throws PayException {
        if (StringUtils.isEmpty(request.getTradeNo()) && StringUtils.isEmpty(request.getOutTradeNo())) {
            throw new PayException("商户订单号,和支付宝交易号不能同时为空");
        }
        String method = AliConstant.TRADE_REFUND_QUERY;
        String version = "1.0";
        return call(generate(request, method, version), AliTradeRefundQueryResponse.class);
    }

    /**
     * 单笔转账到支付宝账户
     *
     * @param request
     * @return
     * @throws PayException
     */
    public AliTransferResponse toAccountTransfer(AliTransferRequest request) throws PayException {
        if (new BigDecimal(request.getAmount()).compareTo(new BigDecimal("50000")) >= 0 && StringUtils.isEmpty(request.getRemark())) {
            throw new PayException("转账金额大于50000元，必须输入备注");
        }
        String method = AliConstant.ACCOUNT_TRANSFER;
        String version = "1.0";
        return call(generate(request, method, version), AliTransferResponse.class);
    }

    /**
     * 查询单笔转账
     *
     * @param request
     * @return
     * @throws PayException
     */
    public AliTransferQueryResponse transferQuery(AliTransferQueryRequest request) throws PayException {
        if (StringUtils.isEmpty(request.getOrderId()) && StringUtils.isEmpty(request.getOutBizNo())) {
            throw new PayException("支付宝转账单据号和商户转账唯一订单号不能同时为空");
        }
        String method = AliConstant.TRADE_TRANS_QUERY;
        String version = "1.0";
        return call(generate(request, method, version), AliTransferQueryResponse.class);
    }

    /**
     * 账单下载
     *
     * @param request
     * @return
     * @throws IOException
     * @throws PayException
     */
    public AliBillDownloadUrlQueryResponse billDownloadUrlQuery(AliBillDownloadUrlQueryRequest request) throws PayException {
        String method = AliConstant.BILL_DOWNLOAD;
        String version = "1.0";
        return call(generate(request, method, version), AliBillDownloadUrlQueryResponse.class);
    }

    /**
     * 同步验签
     *
     * @param json
     * @return
     */
    public Boolean syncVerifySign(JSONObject json, String method) throws PayException {
        if (!json.containsKey("sign")) {
            throw new PayException("返回报文中不含签名");
        }
        String sign = json.get("sign").toString();
        log.info("支付宝传来的签名：" + sign);
        json.remove("sign");
        String content = json.get(method.replaceAll("\\.", "\\_") + "_response").toString();
        log.info("待验证返回签名内容：" + content);
        try {
            boolean flag = AliPayUtil.rsaCheck(content, sign, aliPayConfig.getAliPublicKey(), Standard.ENCODING_UTF8, aliPayConfig.getSignType().toString());
            return flag;
        } catch (PayException e) {
            log.info("验签出错", e);
            throw new PayException(Standard.RET_FAIL, e.getErrMsg());
        }
    }

    /**
     * 异步验签
     *
     * @param response
     * @return
     */
    public Boolean asyncVerifySign(JSONObject response) throws PayException {
        return AliPayUtil.rsaCheckV1(response, aliPayConfig.getAliPublicKey(), Standard.ENCODING_UTF8, aliPayConfig.getSignType().toString());
    }


    /**
     * 统一调用支付宝接口
     *
     * @param aliCommonParam
     * @return
     * @throws PayException
     */
    private <T> T call(AliCommonParam aliCommonParam, Class<T> responseClazz) throws PayException {
        String aliSign = AliPayUtil.getAliSign(aliCommonParam, aliPayConfig);
        aliCommonParam.setSign(aliSign);
        String params = AliPayUtil.destUrl(aliCommonParam, aliPayConfig);
        log.info("===========params===========" + params);
        String responseStr = "";
        responseStr = HttpUtil.callPostStr(url, params, "form", null, null);


        // 返回结果验签
        if (!syncVerifySign(JSONObject.fromObject(responseStr), aliCommonParam.getMethod())) {
            throw new PayException("返回报文签名不正确，疑似篡改！");
        }
        String content = JSONObject.fromObject(responseStr).get(aliCommonParam.getMethod().replaceAll("\\.", "\\_") + "_response").toString();
        try {
            return JsonParser.JSONToModel(content, responseClazz);
        } catch (IOException e) {
            throw new PayException(Standard.RET_FAIL, "");
        }

    }

    // 生成支付宝报文实体类
    private AliCommonParam generate(Object request, String method, String version) throws PayException {
        String biz_content = "";
        try {
            biz_content = JsonParser.modelToJSON(request).replaceAll("\r\n", "").replaceAll("\\s+", "");
        } catch (IOException e) {
            throw new PayException(Standard.FEILD_ERRCODE, "转换报文出错");
        }
        return new AliCommonParam(aliPayConfig.getAppId(), aliPayConfig.getNotifyUrl(), method, version, biz_content);
    }

}
