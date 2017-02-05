package it.gamesandapps.nasadata.objects;

import java.util.List;

public class Geometry {

    private String type;
    private List<Double> coordinates = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Geometry() {
    }

    /**
     *
     * @param type
     * @param coordinates
     */
    public Geometry(String type, List<Double> coordinates) {
        super();
        this.type = type;
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Geometry withType(String type) {
        this.type = type;
        return this;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public Geometry withCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
        return this;
    }

}