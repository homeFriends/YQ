package org.yhn.quqiu.client.view;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yhn.yq.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class GroupAcitivity extends Activity{
	private List<Map<String,String>> data = new ArrayList<Map<String,String>>();  
    
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_group);
		
		ListView listView = (ListView) findViewById(R.id.lv_group);  
        Map<String, String> map = new HashMap<String, String>();  
        map.put("nick","Ц�ıȱ��׻�������");  
        map.put("info","��������ÿһ�죬ҮҮ �п���~");  
        data.add(map);  
        Map<String, String> map2 = new HashMap<String, String>();  
        map2.put("nick","ߣ��ߣ");
        map2.put("info", "ߣ�Ѽ���أ�");  
        data.add(map2);  
        SimpleAdapter adapter=new SimpleAdapter(  
                this,  
                data,
                R.layout.group_listview_item,
                new String[]{"nick","info"},
                new int[]{R.id.nick_group,R.id.info_group});  
        listView.setAdapter(adapter);
	}
}
