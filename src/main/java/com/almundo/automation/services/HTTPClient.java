package com.almundo.automation.services;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.almundo.automation.utils.EncryptKey;
import com.almundo.automation.utils.PropertyReader;

public class HTTPClient {
	
	private static final String PROP_FILE = "conf/conf.properties";
	private static final String UOW_KEY = "glbUserName";
	private static final String API_KEY = "API-Key";
	private static final String HOST = "host";
	private static final String PORT = "port";
	private static final String USE_PROXY = "enableProxy";
	
	private HttpEntity<String> addAlmundoHeaders() {
		PropertyReader propertyReader = new PropertyReader();
		HttpHeaders headers = new HttpHeaders();
		
		String uow = propertyReader.getPropertiesValues(UOW_KEY, PROP_FILE);
		String encryptedKey = propertyReader.getPropertiesValues(API_KEY, PROP_FILE);
		String apiKey = EncryptKey.decryptText(encryptedKey);
		
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		 
		headers.set("X-UOW", uow);
	 	headers.set("X-ApiKey", apiKey);
	 	return new HttpEntity<String>(headers);
	}
	
	private ClientHttpRequestFactory getProxyConfiguration() {
		PropertyReader propertyReader = new PropertyReader();
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		String host = propertyReader.getPropertiesValues(HOST, PROP_FILE);
		String portSt = propertyReader.getPropertiesValues(PORT, PROP_FILE);
		int port = -1;
		try {
			port = Integer.parseInt(portSt);
        } catch (NumberFormatException e) {
            System.out.println("Unable to parse the proxy port");
        }

        InetSocketAddress address = new InetSocketAddress(host,port);
        Proxy proxy = new Proxy(Proxy.Type.HTTP,address);
        factory.setProxy(proxy);
        return factory;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> get(URI url, Class<?> type) {
		PropertyReader propertyReader = new PropertyReader();
		RestTemplate template;
		boolean flag = propertyReader.getBooleanFromProperties(USE_PROXY, PROP_FILE);
		if (flag) {
			template = new RestTemplate(getProxyConfiguration());
		} else {
			template = new RestTemplate();
		}
		HttpEntity<String> entity = this.addAlmundoHeaders();
		return (ResponseEntity<Object>) template.exchange(url, HttpMethod.GET, entity, type);	
	}
	
}