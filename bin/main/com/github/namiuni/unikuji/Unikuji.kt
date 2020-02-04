package com.github.namiuni.unikuji

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

class Unikuji : JavaPlugin() {
    override fun onEnable() {
        logger.info("うにくじ有効にした！")
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        when(command.name) {
            "unikuji" -> {
                sender.sendMessage("うにくじの結果は！")
                val fortune = listOf("大吉", "吉", "中吉", "小吉", "末吉", "凶", "大凶", "雲丹吉")
                val result = fortune.random()
                var count = 0
                object : BukkitRunnable() {
                    override fun run() {
                        if(count++ <=2) {
                            sender.sendMessage(".")
                        } else {
                            if(result == "雲丹吉") {
                                sender.sendMessage("${ChatColor.GOLD}${result}!")
                                if(sender is Player) {
                                    val uni = ItemStack(Material.PUFFERFISH)
                                    val meta = uni.itemMeta
                                    meta.setDisplayName("${ChatColor.GOLD}${ChatColor.BOLD}極上の雲丹")
                                    uni.itemMeta = meta
                                    sender.inventory.addItem(uni)
                                    sender.sendMessage("${meta.displayName}${ChatColor.RESET}をゲットした!!")
                                }
                            } else sender.sendMessage("${ChatColor.GREEN}${result}!")
                            cancel()
                        }
                    }
                }.runTaskTimer(this, 25, 25)
                return true
            }
        }
        return false
    }

    override fun onDisable() {
        logger.info("うにくじ無効にした！")
    }
}