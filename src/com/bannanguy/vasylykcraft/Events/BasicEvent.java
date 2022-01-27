package com.bannanguy.vasylykcraft.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.gsit.api.GSitAPI;
import me.gsit.api.events.PlayerGetUpCrawlEvent;

public class BasicEvent implements Listener {
	private Plugin GSitUpdate;
	private GSitAPI gsitapi = new GSitAPI();
	private boolean is_shift_pressed = false;
	private boolean can_crawl = true;
	
	public BasicEvent(Plugin plugin) {
		GSitUpdate = plugin;
	}
	
	@EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent event) {
		if (!event.isSneaking()) return; // if shift now
		if (!can_crawl) return;
		
		Player player = event.getPlayer();
		if (!is_shift_pressed) {
			is_shift_pressed = true; // first pressing
			
			new BukkitRunnable() {
    	      @Override
    	      public void run() {
    	    	  is_shift_pressed = false; // reset flag pressed
    	      }
    	    }.runTaskLater(GSitUpdate, (long)10); // one second
    	    
    	    return;
		}
    	
		// second pressing
    	if (!gsitapi.isPlayerCrawling(player)) {
    		gsitapi.startPlayerCrawl(player);
    		can_crawl = false; // block crawl again
    	}
    }
	
	@EventHandler
    public void onPlayerGetUpCrawl(PlayerGetUpCrawlEvent event) {
		new BukkitRunnable() {
    	      @Override
    	      public void run() {
    	    		can_crawl = true; // allow crawl with timer	    		
    	      }
    	    }.runTaskLater(GSitUpdate, (long)50); // five seconds
	}
}
