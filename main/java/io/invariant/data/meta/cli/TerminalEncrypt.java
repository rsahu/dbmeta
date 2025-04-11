package io.invariant.data.meta.cli;

import java.io.IOException;

import io.invariant.data.meta.config.AppConf;
import io.invariant.data.meta.crypt.CryptoUtils;
import io.invariant.data.meta.constants.AppConstants;
import io.invariant.data.meta.util.ConfigLoader;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.reader.impl.DefaultParser;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class TerminalEncrypt {

    public static void main(String[] args) throws IOException {
        Terminal terminal = TerminalBuilder.builder().build();
        DefaultParser parser = new DefaultParser();
        LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .parser(parser)
                .build();
        while (true) {
            try {
                String line = reader.readLine("Password to encrypt: ");
                System.out.println(line);
                ConfigLoader configLoader = new ConfigLoader();
                AppConf appConf = configLoader.getAppConfig();
                String salt = appConf.getRelay().getApiKey();
                String encryptedPassword = CryptoUtils.encrypt(line, AppConstants.SECRET, salt);
                System.out.println(encryptedPassword);

                System.exit(0);
            } catch (UserInterruptException e) {
                // Ignore
            } catch (EndOfFileException e) {
                return;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}