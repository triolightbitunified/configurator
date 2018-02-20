package com.bitunified.ledconfig.configuration.configs;

public class Configuration {

    private String code;
    private String name;
    private String description;
    private Integer max;

    public Configuration(String code, String name, String description, Integer max){
        this.code=code;
        this.name=name;
        this.description=description;
        this.max=max;
    }

    public Integer getMax() {
        return max;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
