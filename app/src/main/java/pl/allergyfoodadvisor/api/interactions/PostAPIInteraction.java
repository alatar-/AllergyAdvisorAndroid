package pl.allergyfoodadvisor.api.interactions;

import java.util.HashMap;
import java.util.Map;

public abstract class PostAPIInteraction extends APIInteraction {
    protected Map<String, Object> mRequestBody;

    public PostAPIInteraction() {
        this.mRequestBody = new HashMap<String, Object>();
    }

    public void putRequestField(String name, Object value) {
        this.mRequestBody.put(name, value);
    }
}