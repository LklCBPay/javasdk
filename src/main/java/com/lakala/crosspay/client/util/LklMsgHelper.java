package com.lakala.crosspay.client.util;

import com.google.gson.Gson;
import com.lakala.crosspay.client.entities.LklCrossPayEncryptReq;
import com.lakala.crosspay.client.entities.LklCrossPayEncryptRes;
import com.lakala.crosspay.client.entities.LklCrossPaySuperReq;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 切面；用于发送消息前加密
 * </p>
 * Created by jiang on 16/4/20.
 */
public class LklMsgHelper implements MethodInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LklMsgHelper.class);


    public Object invoke(MethodInvocation invocation) throws Throwable {
        logger.debug("enter lkl advisor");
        LklCrossPayEncryptRes encryptRes = null;
        Class returnClazz = invocation.getMethod().getReturnType();
        Object req1 = invocation.getArguments()[0];
        LklCrossPaySuperReq req2 = (LklCrossPaySuperReq) invocation.getArguments()[1];
        Gson json = new Gson();
        try {
            LklCrossPayEncryptReq req = LklMsgUtil.encryptMsg(req1, req2);
            //执行业务方法，返回加密的响应参数（对象）
            encryptRes = (LklCrossPayEncryptRes) invocation.proceed();
        } catch (Exception e) {
            logger.error("invoke lkl remote method error", e);
        }

        return "1231";

    }
}
