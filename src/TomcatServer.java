

import java.io.IOException;
import java.net.ServerSocket;

import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;

public class TomcatServer {
	public static void main(String[] args){
		int port = Integer.parseInt((String) WebProperties.getInstance().get("port"));
		ServerSocket ss=null;
		try {

			ss = new ServerSocket(port);

		} catch (IOException e) {

		

			e.printStackTrace();

		}
		
		//Keep on providing service
		while(true){
			System.out.println("waiting");
			Socket s=null;
			try {

				s=ss.accept();

			} catch (IOException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}
			
			System.out.println(LocalDate.now().toString() + ' ' + LocalTime.now()+"\t服务器启动,监听"+ss.getLocalPort()+"端口");
			
			HttpSessionTask hst=new HttpSessionTask(s);
			new Thread(hst).start();
			
		}
		
	}
}
