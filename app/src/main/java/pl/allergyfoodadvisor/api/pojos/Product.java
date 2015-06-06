package pl.allergyfoodadvisor.api.pojos;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {

    public String _id;
    public String name;
    public String producer;
    public String description;
    public List<Allergen> allergens;
    public List<Object> history;

}
