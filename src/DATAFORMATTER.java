import java.io.UnsupportedEncodingException;

public class DATAFORMATTER {
	
	private double temp, hi, lo;
	private String local, weather;
	
	
	public DATAFORMATTER(double temp, double hi, double lo, String local, String weather)
	{
		this.temp = temp;
		this.hi = hi;
		this.lo = lo;
		this.local = local;
		this.weather = weather;
	}
	
	public String toString()
	{
		return String.format("Currently the weather is %s in %s. The current temperature is %.1fF, with a high of %.1fF, and a low of %.1fF.", local, weather, temp, hi, lo);
	}
	
	// getters
	public double getHi()
	{
		return hi;
		
	}
	
	public double getLo()
	{
		return lo;
	}
	
	public double getTemp()
	{
		return temp;
	}
	
	public String getLocal()
	{
		return local;
	}
	
	public String getWeather()
	{
		return weather;
	}
	
	// setters
	
	public void setHi(double hi)
	{
		this.hi = hi;
	}
	
	public void setLo(double lo)
	{
		this.lo = lo;
	}
	
	public void setTemp(double temp)
	{
		this.temp = temp;
	}
	
	public void setLocal(String local)
	{
		this.local = local;
	}
	
	public void setWeather(String weather)
	{
		this.weather = weather;
	}

}
