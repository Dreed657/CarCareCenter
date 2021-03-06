package com.Impl;

import com.Impl.DTOs.*;
import com.google.gson.Gson;
import com.sun.jdi.connect.spi.ClosedConnectionException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RestClient {
    static Logger log = Logger.getLogger(RestClient.class.getName());

    private final HttpClient http = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    private final String protocol = "http://";
    private final String url;

    public RestClient(String address, String port) {
        log.setLevel(Level.SEVERE);
        this.url = protocol + address + ":" + port;
    }

    // GET http://localhost:8080/actuator/health
    public boolean healthCheck() throws IOException, InterruptedException {
        var request = HttpRequest
                .newBuilder(URI.create(url + "/actuator/health"))
                .GET().build();

        var response = http.send(request, HttpResponse.BodyHandlers.ofString());
        log.info(response.body());

        if (response.body().contains("UP")) {
            log.severe("Connection established!");
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
        this.handleNotFound(response);
        log.info(response.body());

        var CarDTO = this.gson.fromJson(response.body(), CarDTO.class);

        log.info(String.format("Car with id: %s was with code: %s\n", CarDTO.getId(), response.statusCode()));
        return CarDTO;
    }

    // POST http://localhost:8080/api/cars/
    public CarDTO createCar(CarInputModel model) throws IOException, InterruptedException {
        var carJson = this.gson.toJson(model);

        var request = HttpRequest
                .newBuilder(URI.create(url + "/api/cars/"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(carJson))
                .build();

        var response = http.send(request, HttpResponse.BodyHandlers.ofString());
        log.info(response.body());

        var carDto = this.gson.fromJson(response.body(), CarDTO.class);

        log.info(String.format("Car with id: %s was with code: %s\n", carDto.getId(), response.statusCode()));
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
        this.handleNotFound(response);
        log.info(response.body());

        var repairDto = this.gson.fromJson(response.body(), RepairDTO.class);

        log.info(String.format("Repair with id: %s was with code: %s\n", repairDto.getId(), response.statusCode()));
        return repairDto;
    }

    // POST http://localhost:8080/api/repairs/
    public RepairDTO createRepair(RepairInputModel model) throws IOException, InterruptedException {
        var repairJson = this.gson.toJson(model);

        var request = HttpRequest
                .newBuilder(URI.create(url + "/api/repairs/"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(repairJson))
                .build();

        var response = http.send(request, HttpResponse.BodyHandlers.ofString());
        log.info(response.body());

        var repairDto = this.gson.fromJson(response.body(), RepairDTO.class);

        log.info(String.format("Repair with id: %s was with code: %s\n", repairDto.getId(), response.statusCode()));
        return repairDto;
    }

    // POST http://localhost:8080/api/items/
    public ItemDTO createItem(ItemInputModel model) throws IOException, InterruptedException {
        var itemJson = this.gson.toJson(model);

        var request = HttpRequest
                .newBuilder(URI.create(url + "/api/items/"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(itemJson))
                .build();

        var response = http.send(request, HttpResponse.BodyHandlers.ofString());
        log.info(response.body());

        var itemDto = this.gson.fromJson(response.body(), ItemDTO.class);

        log.info(String.format("Item with id: %s was with code: %s\n", itemDto.getId(), response.statusCode()));
        return itemDto;
    }

    private void handleNotFound(HttpResponse response) {
        if (response.statusCode() == 404) {
            var body = this.gson.fromJson(String.valueOf(response.body()), ErrorResponse.class);
            throw new IllegalArgumentException(body.getDetails()[0]);
        }
    }
}
