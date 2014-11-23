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
		//登陆成功
		if(b){
			//转到登陆界面
			startActivity(new Intent(this, LoginActivity.class));
		}else {
			Toast.makeText(this, "加载失败了，但是我们努力了…（%>_<%）…尝试重新加载中…", Toast.LENGTH_SHORT).show();
		}
	}
}
