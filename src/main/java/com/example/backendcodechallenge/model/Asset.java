package com.example.backendcodechallenge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Asset {

    @Id
    @GeneratedValue
    private UUID uuid = UUID.randomUUID();
    private String type;
    private String title;
    private String label;
    private String url;

    //constructor overload
    public Asset() {
    }

    public Asset(String type, String title, String label, String url) {
        this.type = type;
        this.title = title;
        this.label = label;
        this.url = url;
    }

    @Override
    public String toString() {
        return String.format("UUID: (%), type: %, title: %, label: %, url: %",
                uuid, type, title, label, url);
    }

    //getters and setters
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID  uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
