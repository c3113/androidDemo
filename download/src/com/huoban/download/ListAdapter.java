package com.huoban.download;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huoban.download.network.DownLoadHttpClient;
import com.huoban.download.network.DownLoadHttpClient.OnDownloadListener;
import com.huoban.download.network.Picture;
 /**hilary Create on @2013-11-7. */
public class ListAdapter extends BaseAdapter {

	private ArrayList<Picture> list = new ArrayList<Picture>();
	
	private Context context;
	
	private HashMap<String, ImageView> map = new HashMap<String, ImageView>();
	
	public ListAdapter(Context context, ArrayList<Picture> list){
		this.context = context;
		this.list = list;
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(context).inflate(R.layout.listview_item, null);
		ImageView image = (ImageView) convertView.findViewById(R.id.picture);
		TextView name = (TextView) convertView.findViewById(R.id.name);
		Picture bean = list.get(position);
		
		name.setText(bean.name);
		image.setTag(bean.url);
		image.setImageResource(R.drawable.pho);
		
		DownLoadHttpClient downLoadHttpClient = new DownLoadHttpClient();
		String path = "/sdcard/test/" + bean.name + ".jpg";
		Drawable drawable = Drawable.createFromPath(path);
		if(drawable != null){
			image.setImageDrawable(drawable);
//			map.remove(bean.url);
		} else {
			if(!map.containsKey(bean.url)){
				map.put(bean.url, image);
				downLoadHttpClient.setFilePath(path);
				downLoadHttpClient.setmUrl(bean.url);
				downLoadHttpClient.setOnDownloadListener(new OnDownloadListener() {
					
					@Override
					public void onProgress(int downloadSize, int totalSize) {
						
					}
					
					@Override
					public void downloadSucceed(final String url, String path) {
						Message msg = new Message();
						msg.obj = new String[]{url, path};
						handler.sendMessage(msg);
					}
					
					@Override
					public void downloadFileError(Exception e) {
						
					}
				});
				new Thread(downLoadHttpClient).start();
			}
		}
		
		return convertView;
	}

	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			String[] array = (String[])msg.obj;
			Drawable drawable = Drawable.createFromPath(array[1]);
			if(drawable != null && map.get(array[0]) != null){
				map.get(array[0]).setImageDrawable(Drawable.createFromPath(array[1]));
				notifyDataSetChanged();
			} else if(map.get(array[0]) != null){
				map.get(array[0]).setImageResource(R.drawable.pho);
			}
		}
		
	};
}
