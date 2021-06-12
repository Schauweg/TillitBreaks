package schauweg.tillitbreaks;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.api.ClientModInitializer;
import schauweg.tillitbreaks.config.TIBConfigManager;

public class Main implements ClientModInitializer {

    public static final String MOD_ID = "tillitbreaks";
    public static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();

    @Override
    public void onInitializeClient() {
        TIBConfigManager.initializeConfig();
    }
}
