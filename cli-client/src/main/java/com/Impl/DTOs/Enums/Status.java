package com.Impl.DTOs.Enums;

public enum Status {

    FINISHED("Finished"),
    PROGRESS("In-Progress");

    private String _status = null;

    Status(String status) {
        this._status = status;
    }

    public String toString() {
        return this._status;
    }
}
