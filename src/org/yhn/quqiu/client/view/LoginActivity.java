package org.yhn.quqiu.client.view;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.yhn.quqiu.client.model.ManageClientConServer;
import org.yhn.quqiu.client.model.YQClient;
import org.yhn.quqiu.common.User;
import org.yhn.quqiu.common.YQMessage;
import org.yhn.quqiu.common.YQMessageType;
import org.yhn.yq.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	public static String userInfo;
	EditText accountEt,passwordEt;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.activity_login);
	    
	    accountEt=(EditText) findViewById(R.id.et_account);
	    passwordEt=(EditText) findViewById(R.id.et_password);
	    Button btnLogin=(Button) findViewById(R.id.btn_login);
	    btnLogin.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				if(accountEt.getText().equals("") || passwordEt.getText().equals("")){
					Toast.makeText(LoginActivity.this, "�˺Ż����벻��Ϊ�գ�", Toast.LENGTH_SHORT).show();
				}else{
					login(Integer.parseInt(accountEt.getText().toString()), passwordEt.getText().toString());
				}
			}
	    });
	    findViewById(R.id.btn_register).setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
			}
	    });
	    ManageActivity.addActiviy("LoginActivity", this);
	}
	
	void login(int a, String p){
		User user=new User();
		user.setAccount(a);
		user.setPassword(p);
		user.setOperation("login");
		boolean b=new YQClient(this).sendLoginInfo(user);
		//��½�ɹ�
		if(b){
			try {
				//����һ��Ҫ�󷵻����ߺ��ѵ������Message
				ObjectOutputStream oos = new ObjectOutputStream	(
						ManageClientConServer.getClientConServerThread(user.getAccount()).getS().getOutputStream());
				YQMessage m=new YQMessage();
				m.setType(YQMessageType.GET_ONLINE_FRIENDS);
				m.setSender(user.getAccount());
				oos.writeObject(m);
			} catch (IOException e) {
				e.printStackTrace();
			}
			//ת��������
			startActivity(new Intent(this, MainActivity.class));
		}else {
			Toast.makeText(this, "��½ʧ�ܣ���������Ϊʲô��", Toast.LENGTH_SHORT).show();
		}
	}
}
