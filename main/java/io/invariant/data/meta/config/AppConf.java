package io.invariant.data.meta.config;

import java.util.List;

/**
 * Application Config
 */
public class AppConf {

    private RelayConf relay;
    private GeneratorConf generator;
    private List<DBConf> dataSources;

    public RelayConf getRelay() {
        return relay;
    }

    public void setRelay(RelayConf relay) {
        this.relay = relay;
    }

    public GeneratorConf getGenerator() {
        return generator;
    }

    public void setGenerator(GeneratorConf generator) {
        this.generator = generator;
    }

    public List<DBConf> getDataSources() {
        return dataSources;
    }

    public void setDataSources(List<DBConf> dataSources) {
        this.dataSources = dataSources;
    }

    @Override
    public String toString() {
        return "AppConf{" +
                "\nrelay=" + relay +
                ", \ngenerator=" + generator +
                ", \ndataSources=" + dataSources +
                '}';
    }
}
