package io.invariant.data.relay.cli;

import io.invariant.data.relay.config.AppConf;
import io.invariant.data.relay.crypt.CryptoUtils;
import io.invariant.data.relay.constants.AppConstants;
import io.invariant.data.relay.util.ConfigLoader;

public class DecryptPassword {

    public static void main(String[] args) throws Exception {
        ConfigLoader configLoader = new ConfigLoader();
        AppConf appConf = configLoader.getAppConfig();
        String salt = appConf.getRelay().getApiKey();

        String encryptedPassword = args[0];
        String originalPassword = CryptoUtils.decrypt(encryptedPassword, AppConstants.SECRET, salt);
        System.out.println(originalPassword);
    }

}
