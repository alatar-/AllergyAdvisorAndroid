package pl.allergyfoodadvisor.api.pojos;

import java.io.Serializable;
import java.util.List;

public class Allergen implements Serializable {

    public String _id;
    public String name;
    public List<String> negative_votes;
    public List<String> positive_votes;
}
