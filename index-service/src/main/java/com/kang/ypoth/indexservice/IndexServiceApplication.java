package com.kang.ypoth.indexservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/*import io.fabric8.kubernetes.client.KubernetesClient;
//import io.fabric8.spring.cloud.kubernetes.config.ConfigMapConfigProperties;
//import io.fabric8.spring.cloud.kubernetes.config.ConfigMapPropertySourceLocator;
//import io.fabric8.spring.cloud.kubernetes.config.SecretsConfigProperties;
//import io.fabric8.spring.cloud.kubernetes.config.SecretsPropertySourceLocator;*/
import io.dekorate.kubernetes.annotation.Env;
import io.dekorate.openshift.annotation.OpenshiftApplication;
import io.dekorate.option.annotation.JvmOptions;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient

@OpenshiftApplication(replicas = 1, expose = true, envVars = {
        @Env(name="indexservice", configmap = "indexservice")
})
@JvmOptions(xms = 128, xmx = 256, heapDumpOnOutOfMemoryError = true)
//@EnableSwagger2
//@EnableAutoConfiguration
public class IndexServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IndexServiceApplication.class, args);
	}
	

	
 }
	
	


