package com.example.wallbox4share;

import java.io.Serializable;

public class Wallbox implements Serializable {
    private Long id;
    private Long owner_id;
    private Double latitude;
    private Double longitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Wallbox(Long owner_id, Double latitude, Double longitude) {
        this.owner_id = owner_id;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
