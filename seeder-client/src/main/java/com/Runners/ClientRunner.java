package com.Runners;

import com.RestClient;

import java.io.IOException;

public class ClientRunner implements Runnable {
    private final int repairs;
    private final int items;

    public ClientRunner(int repairs, int items) {
        this.repairs = repairs;
        this.items = items;
    }

    @Override
    public void run() {
        var client = new RestClient();

        try {
            client.createCar();

            for (int i = 0; i < this.repairs; i++) {
                client.createRepair();
            }

            for (int i = 0; i < this.items; i++) {
                client.createItem();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
