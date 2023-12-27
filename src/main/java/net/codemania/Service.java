package net.codemania;

import net.codemania.Chat.MessageGenerator;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

public class Service {
    private String name;
    private String description;
    private TextColor primaryColor;
    private TextColor secondaryColor;

    private Service(String name, String description, TextColor primaryColor, TextColor secondaryColor) {
        this.name = name;
        this.description = description;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
    }

    public static Service newService(String name, String description, TextColor primaryColor, TextColor secondaryColor) {
        return new Service(name, description, primaryColor, secondaryColor);
    }

    public static Service Minecraft() {
        return new Service("M", "This message was sent from a player in Minecraft", NamedTextColor.GREEN, NamedTextColor.GREEN);
    }

    public static Service Discord() {
        return new Service("D", "This message was sent from a player on Discord", NamedTextColor.BLUE, NamedTextColor.BLUE);
    }

    // Getters for fields
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public TextColor getPrimaryColor() {
        return primaryColor;
    }

    public TextColor getSecondaryColor() {
        return secondaryColor;
    }

}
