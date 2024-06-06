package mdk.mutils.lang;

import java.io.*;
public class SimpleLang extends AbstractLang {
    public SimpleLang() {
        super();
    }

    @Override
    public ILang load(Reader reader2) {
        try (BufferedReader reader = new BufferedReader(reader2)) {
            boolean end = true;
            boolean commit = false;
            String line;
            String prefix = "";
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("<")) {
                    if (!line.contains(">")) {
                        commit = true;
                    }
                } else
                if (commit) {
                    if (line.contains(">")) {
                        commit = false;
                    }
                } else
                if (line.startsWith("#{") && line.endsWith("}")) {
                    prefix = line.substring(2, line.length() - 1);
                    end = false;
                } else if (line.startsWith("#end")) {
                    end = true;
                } else if (line.startsWith("//")) {

                } else if (line.contains("=")) {
                    String[] parts = line.split("=", 2);
                    String key;
                    if (!end) {
                        key = prefix + parts[0].trim();
                    }
                    else {
                        key = parts[0].trim();
                    }
                    put(key, parts[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;
    }
}
