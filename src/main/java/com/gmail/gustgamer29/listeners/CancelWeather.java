package com.gmail.gustgamer29.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.weather.WeatherEvent;

public class CancelWeather implements Listener {

    @EventHandler
    public void onWeatherChangeEvent(WeatherChangeEvent event){
        event.setCancelled(true);
    }
}
