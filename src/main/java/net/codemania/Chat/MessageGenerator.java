package net.codemania.Chat;

import net.codemania.Service;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.List;
import java.util.Random;


public class MessageGenerator {



    public static Component generateChatMessage(
            Player player,
            Component message,
            Service service
    ) {



        return Component.empty()
                .append(
                        generateName(
                                player,
                                service
                        )
                )
                .append(Component.text(": ", NamedTextColor.GRAY))
                .append(message);

    }

    public static Component generateName(Player player,
                                          Service service) {
        TextColor nameColor = NamedTextColor.GOLD;

        String playerName = player.getName();
        Team playerTeam = player.getScoreboard().getEntryTeam(playerName);
        TextColor defaultColor = NamedTextColor.BLUE;


        Component teamTag;
        TextColor teamColor;
        
        if (playerTeam != null) {
            try {
                teamColor = playerTeam.color();
            } catch (Exception e) {
                teamColor = defaultColor;
            }
            teamTag = generateBadge(playerTeam.getName(), teamColor, playerTeam.displayName().color(teamColor));

        } else {
            teamColor = TextColor.color(defaultColor);
            teamTag = generateBadge("P", teamColor, Component.text("Player").color(teamColor));
        }


        return Component.empty()
                .append(
                        generateServiceBadge(service))
                .append(teamTag)
                .append(player.displayName().color(nameColor))
                ;
    }


    private static Component generateBadge(
            String badgeText,
            TextColor badgeColor,
            Component description) {
        return Component.empty()
                .append(Component.text("["))
                .append(Component.text(badgeText))
                .append(Component.text("] "))
                .hoverEvent(HoverEvent.showText(description))
                .color(badgeColor)
                ;

    }


    public static Component generateServiceBadge(Service service) {
        return generateBadge(
                service.getName(),
                service.getPrimaryColor(),
                Component.text(service.getDescription()).color(service.getSecondaryColor())
        );
    }


    public static List<List<String>> joinMessages;
    public static List<List<String>> leaveMessages;

    private static final Random random = new Random();
    public static Component generateJoinMessage(Player player) {

        int randomIndex = random.nextInt(joinMessages.size());
        List<String> randomMessage = joinMessages.get(randomIndex);


        Component playerComponent = generateName(player, Service.Minecraft());


        return  Component.text(randomMessage.get(0)).color(NamedTextColor.YELLOW)
                .append(playerComponent)
                .append(Component.text(" " + randomMessage.get(1), NamedTextColor.YELLOW));
    }

    public static Component generateLeaveMessage(Player player) {

        int randomIndex = random.nextInt(leaveMessages.size());
        List<String> randomMessage = leaveMessages.get(randomIndex);


        Component playerComponent = generateName(player, Service.Minecraft());


        return  Component.text(randomMessage.get(0)).color(NamedTextColor.YELLOW)
                .append(playerComponent)
                .append(Component.text(" " + randomMessage.get(1), NamedTextColor.YELLOW));
    }

}
