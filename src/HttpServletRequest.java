import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class HttpServletRequest {
	private String uri;
	private String method;
	private String protocolVersion;
	private Map<String,String> parameter=new HashMap<String,String>();
	private InputStream iis;
	
	public HttpServletRequest( InputStream iis) throws IOException{

		this.iis=iis;

		parse();

	}

	//解析协议的方法   GET /company/index.html HTTP/1.1
	private void parse() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(iis));
		String line=null;

		int i=0;
		if((line=br.readLine())!=null){

			if(i==0){	

				parseCommandLine(line);

			}

			i++;

		}
	}

	private void parseCommandLine(String line) {
		if(line!=null&& !"".equals(line)){
			System.out.println(line);
			
			String [] strs=line.split(" ");

			method=strs[0]; //A restful request
			
			protocolVersion=strs[2];
			if("GET".equals(method)){
				doGet(strs[1]);
			}
			
		}
	}

	//     /a/abc.php?asdf=123&fdsa=321
	//     uri = /a/abc.php
	//     parameter will be mapped with asdf=123, fdsa=321
	private void doGet(String string) {
		if(string.contains("?")){
			String[] strs=string.split("?");
			uri=strs[0];
			String[] ps = strs[1].split("&");
			for(String s:ps){

				String[] pp=s.split("=");

				parameter.put(pp[0], pp[1]);

			}
		}
		else{

			uri=string;

		}
	}
	
	
	//Public methods
	public String getUri(){
		return uri;
	}
	
	public String getMethod(){
		return method;
	}
	
	public String getProtocolVersion(){
		return protocolVersion;
	}
	
}
