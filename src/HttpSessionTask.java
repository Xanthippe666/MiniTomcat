
import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;

import java.net.Socket;

public class HttpSessionTask implements Runnable {
	private Socket s;
	private InputStream iis;
	private OutputStream oos;
	private boolean flag = true;
	
	public HttpSessionTask(Socket s){
		this.s = s;
		try{
			iis = s.getInputStream();
			oos = s.getOutputStream();
			
			
		}catch(IOException e){
			e.printStackTrace();
			flag = false;
		}
	}
	
	
	
	

	private void out500(Exception e) {

		//拼接协议输出错误信息

		//略

		

	}





	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(flag==false){

			return;

		}
		
		try {
		HttpServletRequest request=new HttpServletRequest(iis);
		String uri=request.getUri();
		
		String filepath=WebProperties.getInstance().getProperty("path")+uri;
		filepath = filepath.replace('/', '\\');
		System.out.println("redirect to filepath ->" + filepath);
		HttpServletResponse response=new HttpServletResponse(oos);
		response.redirect(filepath);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			if(this.s!=null){
				try {

					s.close();

				} catch (IOException e) {

					

					e.printStackTrace();

				}
			}
		}
	}
}
