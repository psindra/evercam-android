package io.evercam.unirest.http.utils;

import io.evercam.unirest.http.options.Option;
import io.evercam.unirest.http.options.Options;

import local.org.apache.http.client.HttpClient;
import local.org.apache.http.impl.nio.client.CloseableHttpAsyncClient;

public class ClientFactory {

	public static HttpClient getHttpClient() {
		return (HttpClient) Options.getOption(Option.HTTPCLIENT);
	}

	public static CloseableHttpAsyncClient getAsyncHttpClient() {
		return (CloseableHttpAsyncClient) Options.getOption(Option.ASYNCHTTPCLIENT);
	}

}
