package config;

import java.util.HashMap;
import java.util.Map;

public class DIContainer {
    private static final DIContainer instance = new DIContainer();
    private static final Map<Class<?>, Object> beans = new HashMap<>();

    private DIContainer() {}

    public static DIContainer getInstance() {
        return instance;
    }

    public <T> void registerBean(Class<T> clazz, T b) {
        beans.put(clazz, b);
    }

    public <T> T getBean(Class<T> clazz) {
        return (T) beans.get(clazz);
    }
}
