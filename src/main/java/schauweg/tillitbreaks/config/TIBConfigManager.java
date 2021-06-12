package schauweg.tillitbreaks.config;

import net.fabricmc.loader.api.FabricLoader;

import java.io.*;

import static schauweg.tillitbreaks.Main.GSON;
import static schauweg.tillitbreaks.Main.MOD_ID;

public class TIBConfigManager {

    private static File file;
    private static TIBConfig config;

    private static void prepareConfigFile() {
        if (file != null) {
            return;
        }
        file = FabricLoader.getInstance().getConfigDir().resolve(MOD_ID+".json").toFile();
    }

    public static TIBConfig initializeConfig() {
        if (config != null) {
            return config;
        }

        config = new TIBConfig();
        load();

        return config;
    }

    private static void load() {
        prepareConfigFile();

        try {
            if (!file.exists()) {
                save();
            }
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));

                TIBConfig parsed = GSON.fromJson(br, TIBConfig.class);
                if (parsed != null) {
                    config = parsed;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        prepareConfigFile();

        String jsonString = GSON.toJson(config);

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TIBConfig getConfig() {
        if (config == null) {
            config = new TIBConfig();
        }
        return config;
    }
}
