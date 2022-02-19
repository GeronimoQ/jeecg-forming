package org.jeecg.modules.demo.geronimo;


import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.oConvertUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication
public class FormPlatformApplication {

	public static void main(String[] args) throws UnknownHostException {
		ConfigurableApplicationContext application = SpringApplication.run(FormPlatformApplication.class, args);
		ConfigurableEnvironment environment = application.getEnvironment();
		String ip = InetAddress.getLocalHost().getHostAddress();
		String port = environment.getProperty("server.port");
		String path = oConvertUtils.getString(environment.getProperty("server.servlet.context-path"));
		log.info("\n----------------------------------------------------------\n\t" +
				"Application FormPlatform is running! Access URLs:\n\t" +
				"Local: \t\thttp://localhost:" + port + path + "/\n\t" +
				"External: \thttp://" + ip + ":" + port + path + "/\n\t" +
				"Swagger文档: \thttp://" + ip + ":" + port + path + "/doc.html\n" +
				"-------------------BY GERONIMO----------------------------------");
	}

}
