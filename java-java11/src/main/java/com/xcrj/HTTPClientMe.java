package com.xcrj;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

public class HTTPClientMe{
    public static void main(String[] args) throws Exception{
        test2();
    }

    /**
     * 同步方法
     * httpClient.send()将阻塞，直到获取到响应
     * @throws Exception
     */
    public static void test1() throws Exception{
        // http client
        HttpClient httpClient=HttpClient.newHttpClient();
        // http request
        HttpRequest httpRequest=HttpRequest.newBuilder(URI.create("https://www.baidu.com/home")).build();
        // http response body handler
        BodyHandler<String> responseBodyHandler=BodyHandlers.ofString();
        // http response
        HttpResponse<String> httpResponse=httpClient.send(httpRequest,responseBodyHandler);
        // http response body
        String body=httpResponse.body();
        //
        System.out.println(body);
    }

    /**
     * 异步方法
     * httpClient.sendAsync()不阻塞，可以处理其他任务
     * @throws Exception
     */
    public static void test2() throws Exception{
        // http client
        HttpClient httpClient=HttpClient.newHttpClient();
        // http request
        HttpRequest httpRequest=HttpRequest.newBuilder(URI.create("https://www.baidu.com/home")).build();
        // http response body handler
        BodyHandler<String> responseBodyHandler=BodyHandlers.ofString();
        // CompletableFuture
        CompletableFuture<HttpResponse<String>> completableFuture=httpClient.sendAsync(httpRequest,responseBodyHandler);
        // http response
        // get()会阻塞调用线程, 应该使用其他线程去get()
        HttpResponse<String> httpResponse=completableFuture.get();
        // http response body
        String body=httpResponse.body();
        //
        System.out.println(body);
    }
}