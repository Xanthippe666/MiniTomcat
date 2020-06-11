import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.OutputStream;
import java.nio.charset.Charset;



public class HttpServletResponse {
	private OutputStream oos;
	
	
	public HttpServletResponse(OutputStream oos) {
		this.oos = oos;
	}


	public void redirect(String pageUrl) throws IOException{
		byte[] bs=new byte[1024];
		FileInputStream fis=null;

		File f=new File(pageUrl);
		if(!f.exists()){

			out404();

			return;

		}else{
			
			
			//Read content from file
			fis = new FileInputStream(f);
			StringBuffer sb=new StringBuffer();
			int length=-1;
			while((length=fis.read(bs, 0, bs.length))!=-1){
				sb.append(new String(bs, 0, length, "UTF-8"));
			}
			
			String responseHead="HTTP/1.1 404 Not Found\r\nContent-Type: text/html;"
					+ "charset=utf-8\r\nContent=Length: " + sb.toString().getBytes().length
					+ "\r\n\r\n";
			
			out(responseHead, sb.toString());
			
		}
		
		
		
	}


	private void out404() {
		System.out.println("out404");
		
		String responseBody="<!DOCTYPE html><h1>Ã»ÓÐÒ³Ãæ</h1>";
		String responseHead="HTTP/1.1 404 Not Found\r\nContent-Type: text/html;"
				+ "charset=utf-8\r\nContent=Length: " + responseBody.getBytes().length
				+ "\r\n\r\n";
		
		try{
			out(responseHead, responseBody);
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private void out(String responseHead,String responseBody) throws IOException{
		oos.write(responseHead.getBytes());
		oos.write(responseBody.getBytes(Charset.forName("UTF-8")));
		oos.flush();
	}

}
