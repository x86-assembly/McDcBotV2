package net.codemania.Chat;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;


public class MessageGenerator {

    public static Component generateChatMessage(
            Player player,
            Component message,
            String service,
            String serviceDescription,
            TextColor servicePrimaryColor,
            TextColor serviceSecondaryColor
    ) {
        String playerName = player.getName();
        Scoreboard scoreboard = player.getScoreboard();
        Team playerTeam = scoreboard.getEntryTeam(playerName);


        Component teamTag;
        TextColor teamColor;

        if (playerTeam != null) {
            teamColor = playerTeam.color();
            teamTag = generateTag(playerTeam.getName(), teamColor, playerTeam.displayName().color(teamColor));

        } else {
            teamColor = TextColor.color(Color.BLUE.asRGB());
            teamTag = generateTag("P", teamColor, Component.text("Player").color(teamColor));
        }


        return Component.empty()
                .append(generateServiceTag(service, serviceDescription, servicePrimaryColor, serviceSecondaryColor))
                .append(teamTag)
                .append(
                        Component.text(playerName).color(TextColor.color(Color.ORANGE.asRGB()))
                )
                .append(
                        Component.text(": ").color(TextColor.color(Color.SILVER.asRGB()))
                )
                .append(message);

    }

    private static Component generateTag(
            Component badgeText,
            TextColor badgeColor,
            Component description
    ) {
        return Component.empty()
                .append(Component.text("["))
                .append(badgeText)
                .append(Component.text("] "))
                .hoverEvent(HoverEvent.showText(description))
                .color(badgeColor)
                ;
    } private static Component generateTag(
            String badgeText,
            TextColor badgeColor,
            Component description) {
        return generateTag(Component.text(badgeText), badgeColor, description);
    }


    private static Component generateServiceTag(
            String service,
            String serviceDescription,
            TextColor servicePrimaryColor,
            TextColor serviceSecondaryColor) {
        return generateTag(service, servicePrimaryColor, Component.text(serviceDescription).color(serviceSecondaryColor));
    }

}
