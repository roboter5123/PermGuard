package com.roboter5123.permguard.listener;

import net.kyori.adventure.text.TextComponent;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Collection;
import java.util.Optional;
import java.util.logging.Logger;

import static net.kyori.adventure.text.Component.text;

public class JoinListener implements Listener {

    private final Logger logger;

    private final LuckPerms luckPerms;

    private final TextComponent kickText;

    public JoinListener(LuckPerms luckPerms, Logger logger) {
        this.logger = logger;
        this.luckPerms = luckPerms;
        this.kickText = text().content("You are not permitted on this server").build();
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        User luckPermsUser = luckPerms.getPlayerAdapter(Player.class).getUser(player);

        QueryOptions queryOptions = luckPermsUser.getQueryOptions();
        Collection<Node> nodes = luckPermsUser.resolveInheritedNodes(queryOptions);
        Optional<Node> joinPermissionOptional = nodes.stream().filter(node -> "permguard.join".equals(node.getKey())).findFirst();

        if (!joinPermissionOptional.isPresent()) {
            logger.warning("Permission not found for player: " + player.getName());
            player.kick(this.kickText);
            return;
        }

        Node joinPermission = joinPermissionOptional.get();
        if (!joinPermission.getValue()) {
            player.kick(this.kickText);
        }
    }
}
