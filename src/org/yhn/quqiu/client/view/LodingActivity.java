package org.yhn.quqiu.client.view;


import org.yhn.yq.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class LodingActivity extends Activity {
	public static String userInfo;
	EditText accountEt,passwordEt;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.activity_loding);
	    loding();
	}
	void loding(){

		boolean b=false;
		//��½�ɹ�
		if(b){
			//ת����½����
			startActivity(new Intent(this, LoginActivity.class));
		}else {
			Toast.makeText(this, "����ʧ���ˣ���������Ŭ���ˡ���%>_<%�����������¼����С�", Toast.LENGTH_SHORT).show();
		}
	}
}
