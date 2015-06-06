package pl.allergyfoodadvisor.api.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {

    public String _id;
    public String name;
    public String producer;
    public String description;
    public List<Object> allergens;
    public List<Object> history;

}
