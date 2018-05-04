package com.gmail.gustgamer29.listeners;

import org.bukkit.Location;
import org.bukkit.entity.*;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

public class ShootWithBowListener implements Listener{

    @EventHandler
    public void onShootWithBow(EntityShootBowEvent event){
        Entity entity = event.getEntity();
        if(!(entity instanceof Player)){
            return;
        }
        if(!(event.getProjectile() instanceof Arrow)){
            return;
        }
        Arrow arrow = (Arrow) event.getProjectile();
        Player player = (Player) entity;
        if(player.isOnline()){
           player.setVelocity(arrow.getVelocity());
//            Fireball fireball = player.getWorld().spawn(arrow.getLocation().clone().add(2, -1, 2), Fireball.class);
//            fireball.setVelocity(arrow.getVelocity());

        }
    }

    @EventHandler
    public void onArrowHitBlock(ProjectileHitEvent ev){
        Entity ent = ev.getEntity();
        ProjectileSource shooter = ev.getEntity().getShooter();
        //System.out.println(ent.getType().toString());
        if(!(ent instanceof Arrow)) {
            return;
        }
        if(!(shooter instanceof Player)){
            lightinigEntity((Entity) ev.getEntity().getShooter());
            return;
        }
        Arrow arrow = (Arrow) ent;
        Player player = (Player) shooter;
        Location l = ent.getLocation();
        l.getWorld().strikeLightning(l);
        player.teleport(l);
        arrow.remove();
    }

    private void lightinigEntity(Entity ent){
        if(ent == null) return;
        Location loc = ent.getLocation();
        ent.getWorld().strikeLightning(loc);
        ent.setVelocity(new Vector(ent.getVelocity().getX(), ent.getVelocity().getY() + 2, ent.getVelocity().getZ()));
        }

}
