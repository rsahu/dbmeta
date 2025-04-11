package io.invariant.data.relay.cli;

import io.invariant.data.relay.config.AppConf;
import io.invariant.data.relay.crypt.CryptoUtils;
import io.invariant.data.relay.constants.AppConstants;
import io.invariant.data.relay.util.ConfigLoader;

public class EncryptPassword {

    /**
     * java -cp invariant-relay.jar  io.invariant.data.relay.cli.EncryptPassword password-to-encrypt
     */
    public static void main(String[] args) throws Exception {

        ConfigLoader configLoader = new ConfigLoader();
        AppConf appConf = configLoader.getAppConfig();
        String salt = appConf.getRelay().getApiKey();

        String originalPassword = args[0];
        String encryptedPassword = CryptoUtils.encrypt (originalPassword, AppConstants.SECRET, salt);
        System.out.println (encryptedPassword);
    }
}

