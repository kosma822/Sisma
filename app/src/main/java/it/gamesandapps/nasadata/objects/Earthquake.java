package it.gamesandapps.nasadata.objects;


public class Earthquake {

    private String type;
    private Properties properties;
    private Geometry geometry;
    private String id;

    /**
     * No args constructor for use in serialization
     *
     */
    public Earthquake() {
    }

    /**
     *
     * @param id
     * @param properties
     * @param type
     * @param geometry
     */
    public Earthquake(String type, Properties properties, Geometry geometry, String id) {
        super();
        this.type = type;
        this.properties = properties;
        this.geometry = geometry;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Earthquake withType(String type) {
        this.type = type;
        return this;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Earthquake withProperties(Properties properties) {
        this.properties = properties;
        return this;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Earthquake withGeometry(Geometry geometry) {
        this.geometry = geometry;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Earthquake withId(String id) {
        this.id = id;
        return this;
    }

}
