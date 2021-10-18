package Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherEvent implements Listener
{
	@EventHandler
	public void onChange(WeatherChangeEvent e)
	{
		boolean rain = e.toWeatherState();
		
		if(rain)
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onThunder(ThunderChangeEvent e)
	{
		boolean thunder = e.toThunderState();
		
		if(thunder)
		{
			e.setCancelled(true);
		}
	}
}
