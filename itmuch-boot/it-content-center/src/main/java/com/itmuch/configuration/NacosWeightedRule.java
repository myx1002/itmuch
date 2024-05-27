package com.itmuch.configuration;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.discovery.NacosDiscoveryClient;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class NacosWeightedRule extends AbstractLoadBalancerRule {

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
        // 读取配置文件，并初始化NacosWeightedRule
    }

    @Override
    public Server choose(Object o) {
        BaseLoadBalancer loadBalancer = (BaseLoadBalancer) this.getLoadBalancer();

        // 想要请求的微服务名称
        String name = loadBalancer.getName();
        try {
            Instance instance = nacosDiscoveryProperties.namingServiceInstance().selectOneHealthyInstance(name);
            log.info("选中的instance = {}", instance);
            return new NacosServer(instance);
        } catch (NacosException e) {
            log.info(e.getMessage());
            return null;
        }
    }
}
