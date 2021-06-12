package schauweg.tillitbreaks.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import schauweg.tillitbreaks.config.TIBConfigScreen;

public class TIBModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return TIBConfigScreen::getScreen;
    }

}
