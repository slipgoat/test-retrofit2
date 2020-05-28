package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:api.properties"})
public interface ApiConfig extends Config {
    @Key("api.protocol")
    String protocol();

    @Key("api.host")
    String host();

    @Key("api.timeout.read.sec")
    Integer readTimeoutSec();
}
