
import java.io.IOException;

import java.io.InputStream;

import java.util.Properties;

public class WebProperties extends Properties {
	private static WebProperties myProperties;
	private WebProperties() {
		InputStream iis=WebProperties.class.getClassLoader().getResourceAsStream( "web.properties");
		try {

			this.load(   iis );

		} catch (IOException e) {

			

			e.printStackTrace();

		}
	}
	
	public synchronized static WebProperties getInstance(){
		if( myProperties==null){

			 myProperties=new WebProperties();

		}
		return myProperties;
	}
		
		
}
