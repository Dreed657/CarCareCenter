package com;

import com.Runners.ClientRunner;

import java.util.Random;
import java.util.concurrent.Executors;

public class App {

    public static void main(String[] args) {

        var MIN = 0;
        var MAX_CLIENTS = 5;
        var MAX_REPAIRS = 5;
        var MAX_ITEMS = 10;

        var pool = Executors.newFixedThreadPool(MAX_CLIENTS);

        for (int i = 0; i < MAX_CLIENTS; i++) {
            var random = new Random();
            var repairs = random.nextInt(MAX_REPAIRS - MIN) + MIN;
            var items = random.nextInt(MAX_ITEMS - MIN) + MIN;
            var runner = new ClientRunner(repairs, items);

            pool.submit(runner);
        }

        pool.shutdown();
    }
}

