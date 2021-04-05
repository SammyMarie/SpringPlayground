package com.sammy.entity.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Region {
    Central_Coast("Central Coast"),
    Southern_California("Southern California"),
    Northern_California("Northern California"),
    Napa_Sonoma_Counties("Napa/Sonoma Counties"),
    Varies("Varies");

    private final String label;

    Region(String label) {
        this.label = label;
    }

    public static Region findByLabel(String byLabel){
        return switch (byLabel) {
            case "Central Coast" -> Region.Central_Coast;
            case "Southern California" -> Region.Southern_California;
            case "Northern California" -> Region.Northern_California;
            case "Napa/Sonoma Counties" -> Region.Napa_Sonoma_Counties;
            case "Varies" -> Region.Varies;
            default -> throw new IllegalArgumentException("Label [" + byLabel + "] not supported.");
        };
        /*return Arrays.stream(Region.values())
                     .filter(region -> region.label.equalsIgnoreCase(byLabel))
                     .findFirst()
                     .orElse(null);*/

    }
}
