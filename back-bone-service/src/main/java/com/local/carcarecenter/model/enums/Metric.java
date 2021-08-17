package com.local.carcarecenter.model.enums;

public enum Metric {

    LITER("Liter"),
    UNIT("Unit");

    private String _Metric = null;

    Metric(String metric) {
        this._Metric = metric;
    }

    public String toString() {
        return this._Metric;
    }
}
