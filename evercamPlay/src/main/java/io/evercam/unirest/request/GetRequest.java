/*
The MIT License

Copyright (c) 2013 Mashape (http://mashape.com)

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package io.evercam.unirest.request;

import io.evercam.unirest.http.HttpMethod;
import io.evercam.unirest.request.HttpRequest;

import java.util.Map;

public class GetRequest extends HttpRequest {

	public GetRequest(HttpMethod method, String url) {
		super(method, url);
	}

	public io.evercam.unirest.request.GetRequest routeParam(String name, String value) {
		super.routeParam(name, value);
		return this;
	}

	@Override
	public io.evercam.unirest.request.GetRequest header(String name, String value) {
		return (io.evercam.unirest.request.GetRequest) super.header(name, value);
	}

	@Override
	public io.evercam.unirest.request.GetRequest headers(Map<String, String> headers) {
		return (io.evercam.unirest.request.GetRequest) super.headers(headers);
	}

	@Override
	public io.evercam.unirest.request.GetRequest basicAuth(String username, String password) {
		super.basicAuth(username, password);
		return this;
	}
}
