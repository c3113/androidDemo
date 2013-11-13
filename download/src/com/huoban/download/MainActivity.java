package com.huoban.download;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.huoban.download.network.Picture;

/**
 * hilary create on 2013-11-11.
 * 
 * 列表形势加载网络图片资源
 */
public class MainActivity extends Activity {

	private ListView listView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final ArrayList<Picture> list = new ArrayList<Picture>();
		Picture p = new Picture("amy", "http://f2.huoban.com/100073/user_avatar/100618/96");
		 p = new Picture("毕惠子", "http://f2.huoban.com/100073/user_avatar/100304/96");
		 list.add(p);
		 p = new Picture("丛瑜帅", "http://f2.huoban.com/100073/user_avatar/100292/96");
		 list.add(p);
		 p = new Picture("崔建宁", "http://f2.huoban.com/100073/user_avatar/100438/96");
		 list.add(p);
		 p = new Picture("池骋", "http://f2.huoban.com/100073/user_avatar/b30042be818189da276e109a3048148b/96");
		 list.add(p);
		 p = new Picture("freeman", "http://f2.huoban.com/100073/user_avatar/eee596475499a43fec095c1e8354e80d/96");
		 list.add(p);
		 p = new Picture("付建宇", "http://f2.huoban.com/100073/user_avatar/101068/96");
		 list.add(p);
		 p = new Picture("浮伟", "http://f2.huoban.com/100073/user_avatar/100720/96");
		 list.add(p);
		 p = new Picture("胡言帅", "http://f2.huoban.com/100073/user_avatar/7b62ee535c52bb27137199c58b44ec12/96");
		 list.add(p);
		 p = new Picture("李坤宇", "http://f2.huoban.com/100073/user_avatar/101019/96");
		 list.add(p);
		 p = new Picture("罗国文", "http://f2.huoban.com/100073/user_avatar/100489/96");
		 list.add(p);
		 p = new Picture("罗平", "http://f2.huoban.com/100073/user_avatar/100375/96");
		 list.add(p);
		 p = new Picture("聂心原", "http://f2.huoban.com/100073/user_avatar/100528/96");
		 list.add(p);
		 p = new Picture("涂亚", "http://f2.huoban.com/100073/user_avatar/100270/96");
		 list.add(p);
		 p = new Picture("吴杨", "http://f2.huoban.com/100073/user_avatar/100236/96");
		 list.add(p);
		 p = new Picture("王斌", "http://f2.huoban.com/100073/user_avatar/100209/96");
		 list.add(p);
		 p = new Picture("邢建迪", "http://f2.huoban.com/100073/user_avatar/100083/96");
		 list.add(p);
		 p = new Picture("余志民", "http://f2.huoban.com/100073/user_avatar/101046/96");
		 list.add(p);
		 p = new Picture("姚刚", "http://f2.huoban.com/100073/user_avatar/101002/96");
		 list.add(p);
		 p = new Picture("杨利", "http://f2.huoban.com/100073/user_avatar/100348/96");
		 list.add(p);
		 p = new Picture("袁兆江", "http://f2.huoban.com/100073/user_avatar/100195/96");
		 list.add(p);
		 p = new Picture("周贺", "http://f2.huoban.com/100073/user_avatar/100401/96");
		 list.add(p);
		 p = new Picture("张建伟", "http://f2.huoban.com/100073/user_avatar/100450/96");
		 list.add(p);
		 p = new Picture("陈凯", "http://f2.huoban.com/100073/user_avatar/6d5dc4b3bdb30be7511bd903411b63be/96");
		 list.add(p);
		 p = new Picture("测试", "http://f2.huoban.com/100073/user_avatar/51ef62c83fa80ed8ee4fe296c53f2689/96");
		 list.add(p);
		 p = new Picture("于雷", "http://f2.huoban.com/100073/user_avatar/100506/96");
		 list.add(p);
		 p = new Picture("高倩", "http://f2.huoban.com/100073/user_avatar/137aff470895150fe2bc89075cc18946/96");
		 list.add(p);
		 p = new Picture("丛姗", "http://f2.huoban.com/100073/user_avatar/81ed4f1addae4f7b34f5dc0780df8a0e/96");
		 list.add(p);
		 p = new Picture("戴志康", "http://f2.huoban.com/100073/user_avatar/ec063febe1d18803cc55d6922b471014/96");
		 list.add(p);
		 p = new Picture("陈洋", "http://f2.huoban.com/100073/user_avatar/7d20a5f30676daf35b503575e417a741/96");
		 list.add(p);
		 listView = (ListView) findViewById(R.id.listView);
		 listView.setAdapter(new ListAdapter(this, list));
		
	}

}
