package com;

import com.DTOs.CarDTO;
import com.DTOs.ItemDTO;
import com.DTOs.RepairDTO;
import com.Utils.RandomUtil;
import com.google.gson.Gson;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@NoArgsConstructor
public class RestClient {
    private final HttpClient http = HttpClient.newHttpClient();
    private final RandomUtil randUtil = new RandomUtil();
    private final Gson gson = new Gson();

    private final String domain = "http://localhost:";
    private final String port = "8080";
    private final String url = domain + port;

    private Long carId;
    private final List<Long> repairsIds = new ArrayList<>();

    // POST http://localhost:8080/api/cars/
    public CarDTO createCar() throws IOException, InterruptedException {
        var car = randUtil.generateCar();
        var carJson = gson.toJson(car);

        var request = HttpRequest
                .newBuilder(URI.create(url + "/api/cars/"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(carJson))
                .build();

        var response = http.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        var carDto = gson.fromJson(response.body(), CarDTO.class);
        this.carId = carDto.getId();

        System.out.printf("Car with id: %s was with code: %s\n", carDto.getId(), response.statusCode());
        return carDto;
    }

    // POST http://localhost:8080/api/repairs/
    public RepairDTO createRepair() throws IOException, InterruptedException {
        var repair = randUtil.generateRepair(this.carId);
        var repairJson = gson.toJson(repair);

        var request = HttpRequest
                .newBuilder(URI.create(url + "/api/repairs/"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(repairJson))
                .build();

        var response = http.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        var repairDto = gson.fromJson(response.body(), RepairDTO.class);
        this.repairsIds.add(repairDto.getId());

        System.out.printf("Repair with id: %s was with code: %s\n", repairDto.getId(), response.statusCode());
        return repairDto;
    }

    // POST http://localhost:8080/api/items/
    public ItemDTO createItem() throws IOException, InterruptedException {
        var rand = new Random();
        var item = randUtil.generateItem(this.repairsIds.get(rand.nextInt(this.repairsIds.size())));
        var itemJson = gson.toJson(item);

        var request = HttpRequest
                .newBuilder(URI.create(url + "/api/items/"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(itemJson))
                .build();

        var response = http.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        var itemDto = gson.fromJson(response.body(), ItemDTO.class);
        this.repairsIds.add(itemDto.getId());

        System.out.printf("Item with id: %s was with code: %s\n", itemDto.getId(), response.statusCode());
        return itemDto;
    }
}
