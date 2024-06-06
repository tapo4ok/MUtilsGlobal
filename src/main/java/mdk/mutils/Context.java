package mdk.mutils;

import lombok.Getter;

import java.io.File;
import java.util.logging.Logger;

@Getter
public class Context {
    private final Logger logger;
    private final File datadir;

    public Context(Logger logger, File data) {
        this.datadir = data;
        this.logger = logger;
    }
}
