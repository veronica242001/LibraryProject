package models;

import java.util.Objects;

public class Publisher {
    private String name;
    private City city;
    private int id;
    private int idCity;

    public Publisher(String name, City city) {
        this.name = name;
        this.city = city;
    }
    public Publisher(int id, String name, City city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Publisher: " +
                "name='" + name + '\'' +
                ", city=" + city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return Objects.equals(name, publisher.name) && Objects.equals(city, publisher.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city);
    }
}
