package demoqa.project.configurations.scenario;

import demoqa.project.enums.ObjectKey;
import java.util.HashMap;
import java.util.Map;


public class ScenarioContext {

    private static ScenarioContext instance;
    private Map<ObjectKey, Object> data;

    private ScenarioContext() {
        data = new HashMap<>();
    }

    public static ScenarioContext getInstance() {
        if (instance == null) {
            instance = new ScenarioContext();
        }
        return instance;
    }

    public void saveData(ObjectKey key, Object value) {
        data.put(key, value);
    }

    public void clearData() {
        data.clear();
    }

    public <T> T getData(ObjectKey key) {
        return (T) data.get(key);
    }
}
