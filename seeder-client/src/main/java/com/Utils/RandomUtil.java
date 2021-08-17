package com.Utils;

import com.DTOs.*;
import com.Enums.EngineType;
import com.Enums.Metric;
import com.Enums.Status;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.Objects;
import java.util.Random;

public class RandomUtil {
    private final Random rand = new Random();
    private String[] carParts = new String[]{};
    private String[] carManufacturers = new String[]{};

    // CAR CONSTRAINTS
    private final Integer MIN_CAR_YEAR = 1900;
    private final Integer MAX_CAR_YEAR = Calendar.getInstance().get(Calendar.YEAR);

    // REPAIR CONSTRAINTS
    private final Integer MAX_REPAIR_MILEAGE = 200000;

    // ITEM CONSTRAINTS
    private final Integer MAX_ITEM_QUANTITY = 25;
    private final Integer MAX_ITEM_PRICE = 500;


    public RandomUtil() {
        var gson = new Gson();
        var classLoader = RandomUtil.class.getClassLoader();

        var carPartsFile = "carpars-1629200793641.txt";
        var carManufacturers = "car-manufacturers.txt";

        try {
            var carPartsFileSrc = new File(Objects.requireNonNull(classLoader.getResource(carPartsFile)).getFile());
            String carPartsContent = new String(Files.readAllBytes(carPartsFileSrc.toPath()));
            this.carParts = gson.fromJson(carPartsContent, String[].class);

            var carManufacturersFileSrc = new File(Objects.requireNonNull(classLoader.getResource(carManufacturers)).getFile());
            String carManufacturersContent = new String(Files.readAllBytes(carManufacturersFileSrc.toPath()));
            this.carManufacturers = gson.fromJson(carManufacturersContent, String[].class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public CarInputModel generateCar() {
        var car = new CarInputModel();

        car.setVin(generateString(17));
        car.setYear((int) Math.floor(Math.random() * (MAX_CAR_YEAR - MIN_CAR_YEAR + 1) + MIN_CAR_YEAR));
        car.setType(EngineType.values()[rand.nextInt(EngineType.values().length)]);
        car.setModel(generateString(10));
        car.setManufacturer(carManufacturers[rand.nextInt(carManufacturers.length)]);

        return car;
    }

    public RepairInputModel generateRepair(Long carId) {
        var repair = new RepairInputModel();

        repair.setMileage(Long.parseLong(String.valueOf(rand.nextInt(MAX_REPAIR_MILEAGE))));
        repair.setStatus(Status.values()[rand.nextInt(Status.values().length)]);
        repair.setCarId(carId);

        return repair;
    }

    public ItemInputModel generateItem(Long repairId) {
        var item = new ItemInputModel();

        item.setDescription(carParts[rand.nextInt(carParts.length)]);
        item.setMetric(Metric.values()[rand.nextInt(Metric.values().length)]);
        item.setPrice(new BigDecimal(rand.nextInt(MAX_ITEM_PRICE)));
        item.setQuantity(rand.nextInt(MAX_ITEM_QUANTITY));
        item.setRepairId(repairId);

        return item;
    }

    public String generateString(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
