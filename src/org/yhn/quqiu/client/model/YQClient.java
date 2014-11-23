package org.yhn.quqiu.client.model;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.yhn.quqiu.client.view.LoginActivity;
import org.yhn.quqiu.client.view.MainActivity;
import org.yhn.quqiu.common.User;
import org.yhn.quqiu.common.YQMessage;
import org.yhn.quqiu.common.YQMessageType;

import android.content.Context;
import android.util.Log;

public class YQClient {
	private Context context;
	public Socket s;
	public YQClient(Context context){
		this.context=context;
	}
	public boolean sendLoginInfo(Object obj){
		boolean b=false;
		try {
			s=new Socket();
			try{
				s.connect(new InetSocketAddress("10.0.2.2",5469),2000);
			}catch(SocketTimeoutException e){
				//���ӷ�������ʱ
				return false;
			}
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(obj);
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			YQMessage ms=(YQMessage)ois.readObject();
			if(ms.getType().equals(YQMessageType.SUCCESS)){
				//������Ϣ
				MainActivity.myInfo=ms.getContent();
				//����һ�����˺źͷ������������ӵ��߳�
				ClientConServerThread ccst=new ClientConServerThread(context,s);
				//������ͨ���߳�
				ccst.start();
				//���뵽��������
				ManageClientConServer.addClientConServerThread(((User)obj).getAccount(), ccst);
				b=true;
			}else if(ms.getType().equals(YQMessageType.FAIL)){
				b=false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public boolean sendRegisterInfo(Object obj){
		boolean b=false;
		try {
			s=new Socket();
			try{
				s.connect(new InetSocketAddress("10.0.2.2",5469),2000);
			}catch(SocketTimeoutException e){
				//���ӷ�������ʱ
				return false;
			}
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(obj);
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			YQMessage ms=(YQMessage)ois.readObject();
			if(ms.getType().equals(YQMessageType.SUCCESS)){
				b=true;
			}else if(ms.getType().equals(YQMessageType.FAIL)){
				b=false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return b;
	}
}