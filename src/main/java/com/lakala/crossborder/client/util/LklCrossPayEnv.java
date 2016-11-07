package com.lakala.crossborder.client.util;

import com.lakala.crossborder.client.enums.LklEnv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 拉卡拉跨境支付环境信息
 * </p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
@Component
public class LklCrossPayEnv {

    @Autowired
    @Qualifier("yamlMap")
    private Map<String, Object> yamlMap;

    private static Map<String, Object> YML_MAP;

    private static final Map<LklEnv, LklCrossPayClientConfig> ENV_CONFIG = new ConcurrentHashMap<LklEnv, LklCrossPayClientConfig>();

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
//        YML_MAP = ctx.getBean("yamlMap", Map.class);
        YML_MAP = (Map<String, Object>) yamlMap.get("yamlMap");
    }


    /**
     * 获取当前环境的配置信息
     *
     * @return
     */
    public static LklCrossPayClientConfig getEnvConfig() {
        Iterator<Map.Entry<LklEnv, LklCrossPayClientConfig>> it = ENV_CONFIG.entrySet().iterator();
        LklCrossPayClientConfig config = null;
        if (it.hasNext()) {
            config = it.next().getValue();
        }
        return config;

    }

    /**
     * 注册拉卡拉支付平台环境,一个应用实例只能注册一个环境
     *
     * @param env        拉卡拉环境枚举 {@link LklEnv}
     * @param merId      该环境对应的商户号
     * @param privateKey 该环境对应的私钥
     * @param publicKey  该环境对应的公钥
     */
    public synchronized void registerEnv(LklEnv env, String merId, String privateKey, String publicKey) {
        String envValue = env.getEnv();
        //一个实例只允许注册一个环境
        if (!ENV_CONFIG.isEmpty()) {
            return;
        } else {
            LklCrossPayClientConfig config = new LklCrossPayClientConfig();
            Map<String, String> envObj = (LinkedHashMap<String, String>) YML_MAP.get(envValue);
            config.setPort(String.valueOf(envObj.get("port")));
            config.setPrivateKey(privateKey);
            config.setPublicKey(publicKey);
            config.setServer(String.valueOf(envObj.get("server")));
            config.setMerId(merId);
            ENV_CONFIG.put(env, config);

        }
    }
}
