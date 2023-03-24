package nl.novi.techiteasycontroller.Model;

public class Television {
    public Long id;
    public String brand;
    public String type;

    public Television(Long id, String brand, String type) {
        this.id = id;
        this.brand = brand;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
