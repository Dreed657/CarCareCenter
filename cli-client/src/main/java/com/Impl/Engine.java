package com.Impl;

import java.io.IOException;

public class Engine {

    private final RestClient client;

    public Engine(RestClient client) {
        this.client = client;
    }

    public void Run() throws IOException, InterruptedException {
        var response = client.getCar(2L);
    }
}
