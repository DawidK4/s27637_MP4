package staticattributeconstraint;

public class Product {
    private String name;

    public Product(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("Name must not be null or empty!");
        }

        if (name.length() > 30) {
            throw new IllegalArgumentException("Name length must not exceed 30 characters!");
        }

        this.name = name;
    }
}
