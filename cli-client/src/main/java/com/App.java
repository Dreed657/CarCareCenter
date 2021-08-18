package com;

import com.Impl.Engine;
import com.Impl.RestClient;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        var scanner = new Scanner(System.in);

        // FOR PROD XD
        //System.out.print("Server address: ");
        //var address = scanner.nextLine();

        //System.out.print("Server port: ");
        //var port = scanner.nextLine();

        // FOR DEVELOPMENT
        var address = "localhost";
        var port = "8080";

        var client = new RestClient(address, port);
        client.healthCheck();

        var engine = new Engine(client);

        try {
            var command = scanner.nextLine();

            while (!command.toLowerCase(Locale.ROOT).equals("exit")) {
                try {
                    engine.Run(command);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }

                command = scanner.nextLine();
            }

            System.out.println("Thank you!");
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
