package com.Impl;

import com.Impl.DTOs.*;
import com.google.gson.Gson;
import com.sun.jdi.connect.spi.ClosedConnectionException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestClient {
    private final HttpClient http = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    private final String protocol = "http://";
    private final String url;

    public RestClient(String address, String port) {
        this.url = protocol + address + ":" + port;
    }

    // GET http://localhost:8080/actuator/health
    public boolean healthCheck() throws IOException, InterruptedException {
        var request = HttpRequest
                .newBuilder(URI.create(url + "/actuator/health"))
                .GET().build();

        var response = http.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.body().contains("UP")) {
            System.out.println("Connection established!");
            return true;
        } else {
            throw new ClosedConnectionException("Cannot connect to the server!");
        }
    }

    // GET http://localhost:8080/api/cars/{id}/
    public CarDTO getCar(Long id) throws IOException, InterruptedException {
        var request = HttpRequest
                .newBuilder(URI.create(url + "/api/cars/" + id + "/"))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        var response = http.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        var CarDTO = gson.fromJson(response.body(), CarDTO.class);

        System.out.printf("Car with id: %s was with code: %s\n", CarDTO.getId(), response.statusCode());
        return CarDTO;
    }

    // POST http://localhost:8080/api/cars/
    public CarDTO createCar(CarInputModel model) throws IOException, InterruptedException {
        var carJson = gson.toJson(model);

        var request = HttpRequest
                .newBuilder(URI.create(url + "/api/cars/"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(carJson))
                .build();

        var response = http.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        var carDto = gson.fromJson(response.body(), CarDTO.class);

        System.out.printf("Car with id: %s was with code: %s\n", carDto.getId(), response.statusCode());
        return carDto;
    }

    // GET http://localhost:8080/api/repairs/{id}/
    public RepairDTO getRepair(Long id) throws IOException, InterruptedException {
        var request = HttpRequest
                .newBuilder(URI.create(url + "/api/repairs/" + id + "/"))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        var response = http.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        var repairDto = gson.fromJson(response.body(), RepairDTO.class);

        System.out.printf("Repair with id: %s was with code: %s\n", repairDto.getId(), response.statusCode());
        return repairDto;
    }

    // POST http://localhost:8080/api/repairs/
    public RepairDTO createRepair(RepairInputModel model) throws IOException, InterruptedException {
        var repairJson = gson.toJson(model);

        var request = HttpRequest
                .newBuilder(URI.create(url + "/api/repairs/"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(repairJson))
                .build();

        var response = http.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        var repairDto = gson.fromJson(response.body(), RepairDTO.class);

        System.out.printf("Repair with id: %s was with code: %s\n", repairDto.getId(), response.statusCode());
        return repairDto;
    }

    // POST http://localhost:8080/api/items/
    public ItemDTO createItem(ItemInputModel model) throws IOException, InterruptedException {
        var itemJson = gson.toJson(model);

        var request = HttpRequest
                .newBuilder(URI.create(url + "/api/items/"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(itemJson))
                .build();

        var response = http.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        var itemDto = gson.fromJson(response.body(), ItemDTO.class);

        System.out.printf("Item with id: %s was with code: %s\n", itemDto.getId(), response.statusCode());
        return itemDto;
    }
}
