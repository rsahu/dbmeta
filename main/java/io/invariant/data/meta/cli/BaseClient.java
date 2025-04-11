package io.invariant.data.meta.cli;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

        import java.io.IOException;

public abstract class BaseClient {

    private static final Logger log = LoggerFactory.getLogger (BaseClient.class);

    protected String buildServiceURI(String path) throws IOException {
        return ""; //Config.buildBaseServiceURI () + path;
    }

}