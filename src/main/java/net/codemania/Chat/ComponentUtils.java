package net.codemania.Chat;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class ComponentUtils {

    private static String processString(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        return input.replaceAll("§[a-z]", "").replaceAll("§§", "§");
    }

    public static String componentToPlaintext(Component component) {

        return processString(
                LegacyComponentSerializer
                        .legacySection()
                        .serialize(component)
        );

    }


}
