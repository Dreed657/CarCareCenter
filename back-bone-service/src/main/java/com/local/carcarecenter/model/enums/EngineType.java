package com.local.carcarecenter.model.enums;

public enum EngineType {

    PETROL("Petrol"),
    DIESEL("Diesel"),
    ELECTRIC("Electric");

    private String _type = null;

    EngineType(String type) {
        this._type = type;
    }

    public String toString() {
        return this._type;
    }
}
