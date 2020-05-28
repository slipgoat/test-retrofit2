package config;

import org.aeonbits.owner.ConfigFactory;

public class Configuration {
    public static ApiConfig getApi() {
        return ConfigFactory.create(ApiConfig.class);
    }
}
