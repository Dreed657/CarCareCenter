package com;

import com.Impl.Engine;
import com.Impl.RestClient;

import java.io.IOException;
import java.net.ConnectException;
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
            engine.Run();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
