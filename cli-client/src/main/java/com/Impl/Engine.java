package com.Impl;

import java.io.IOException;
import java.util.Arrays;

public class Engine {

    private final RestClient client;

    public Engine(RestClient client) {
        this.client = client;
    }

    public void Run(String command) throws IOException, InterruptedException {
        var args = command.trim().split(" ");

        var name = args[0].trim();

        switch (name) {
            case "add":
                System.out.println("Add command!");
                break;
            case "print":
                var comArgs = Arrays.copyOfRange(args, 2, args.length);
                var separated = comArgs[0].split("=");
                switch (args[1]) {
                    case "repair":
                        var repair = this.client.getRepair(Long.parseLong(separated[1]));
                        System.out.println(repair.toString());
                        break;
                    case "car":
                        var car = this.client.getCar(Long.parseLong(separated[1]));
                        System.out.println(car.toString());
                        break;
                }
                break;
            case "export":
                System.out.println("Export command!");
                break;
        }
    }
}
