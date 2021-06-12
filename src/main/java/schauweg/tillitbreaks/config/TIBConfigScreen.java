package schauweg.tillitbreaks.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import schauweg.tillitbreaks.Main;

import java.util.function.Function;

public class TIBConfigScreen {

    public static Screen getScreen(Screen parent) {
        TIBConfig config = TIBConfigManager.getConfig();

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(new TranslatableText(Main.MOD_ID + ".config.menu"));

        ConfigCategory general = builder.getOrCreateCategory(new TranslatableText(Main.MOD_ID + ".config.general"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText(Main.MOD_ID + ".config.option.alwaysshowbar"), config.isShowDurabilityIfBarFull())
                .setSaveConsumer(newValue -> config.setShowDurabilityIfBarFull(newValue))
                .setDefaultValue(false)
                .setYesNoTextSupplier(getYesNoSupplier("tillitbreaks.config.enabled", "tillitbreaks.config.disabled"))
                .build());

        general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText(Main.MOD_ID + ".config.option.showbar"), config.isShowDurabilityBar())
                .setSaveConsumer(newValue -> config.setShowDurabilityBar(newValue))
                .setDefaultValue(true)
                .setYesNoTextSupplier(getYesNoSupplier("tillitbreaks.config.enabled", "tillitbreaks.config.disabled"))
                .build()
        );

        general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText(Main.MOD_ID + ".config.option.showdurablilitynumber"), config.isShowDurabilityNumber())
                .setSaveConsumer(newValue -> config.setShowDurabilityNumber(newValue))
                .setDefaultValue(true)
                .setYesNoTextSupplier(getYesNoSupplier("tillitbreaks.config.enabled", "tillitbreaks.config.disabled"))
                .build()
        );

        general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText(Main.MOD_ID + ".config.option.colordurability"), config.isColorDurabilityNumber())
                .setSaveConsumer(newValue -> config.setColorDurabilityNumber(newValue))
                .setDefaultValue(true)
                .setYesNoTextSupplier(getYesNoSupplier("tillitbreaks.config.enabled", "tillitbreaks.config.disabled"))
                .build()
        );

        general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText(Main.MOD_ID + ".config.option.showarrowcount"), config.isShowArrowCount())
                .setSaveConsumer(newValue -> config.setShowArrowCount(newValue))
                .setDefaultValue(true)
                .setYesNoTextSupplier(getYesNoSupplier("tillitbreaks.config.enabled", "tillitbreaks.config.disabled"))
                .build()
        );

        general.addEntry(entryBuilder.startIntSlider(new TranslatableText(Main.MOD_ID + ".config.option.textsize"), config.getTextSize(), 50, 130)
                .setSaveConsumer(newValue -> config.setTextSize(newValue))
                .setDefaultValue(100)
                .build()
        );

        builder.setSavingRunnable(() -> {
            TIBConfigManager.save();
        });

        return builder.build();
    }

    private static Function<Boolean, Text> getYesNoSupplier(String keyYes, String keyNo) {
        return x -> {
            if (x)
                return new TranslatableText(keyYes);
            else
                return new TranslatableText(keyNo);
        };
    }


}
