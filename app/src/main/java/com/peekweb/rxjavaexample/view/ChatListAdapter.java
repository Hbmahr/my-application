package com.peekweb.rxjavaexample.view;

import android.content.Context;
import android.os.Build;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.peekweb.rxjavaexample.R;
import com.peekweb.rxjavaexample.databinding.ChatListLayoutCardBinding;
import com.peekweb.rxjavaexample.model.ChatFriendList;
import com.peekweb.rxjavaexample.util.ConnectionClass;
import com.peekweb.rxjavaexample.util.Constatnts;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.OkHttpClient;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.MyViewHolder> {


    private List<ChatFriendList> mList;
    private LayoutInflater mInflater;
    public static final int TEXT_MESSAGE =0;
    public static final int iMAGE_MESSAGE =1;
    public static final int FILE_MESSAGE =2;
    public static final int CALL_MESSAGE =3;
    public static final int LINK_MESSAGE =4;
    public static final int VIDEO_MESSAGE =5;
    public static final int AUDIO_MESSAGE =6;
    public static final int LOCATION_MESSAGE =7;
    public static final int STICKER_MESSAGE =8;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.getContext());
        }

        ChatListLayoutCardBinding binding
                = DataBindingUtil.inflate(mInflater, R.layout.chat_list_layout_card, parent, false);
        return new MyViewHolder(binding);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.binding.setChatFriendList(mList.get(position));
        String URL = "https://chat.chatto.jp:20000/profile/"+mList.get(position).getProfileImage();
        imageLoader(URL,holder.itemView.getContext(),holder.binding.userImage);


        switch (mList.get(position).getMessageType()){

            case AUDIO_MESSAGE:{
                holder.binding.lastMessage.setText("Voice Message");
                holder.binding.messageIcon.setVisibility(View.VISIBLE);
                holder.binding.messageIcon.setBackgroundResource(R.drawable.ic_mice);
                break;
            }
            default:{
                Log.d("yes","");
            }


        }
        if(mList.get(position).getOnlineStatus()==1){
            holder.binding.onlineStatus.setBackgroundTintList(holder.itemView.getContext().getResources().getColorStateList(R.color.online_status_color));
        }




    }
    public void imageLoader(String URL, Context context, ImageView userImage){
        ConnectionClass loadImageClass=new ConnectionClass();

        if (URL != null && !URL.isEmpty()) {
            OkHttpClient picassoClient = loadImageClass.getUnsafeOkHttpClient();
            Picasso picasso = new Picasso.Builder(context).downloader(new OkHttp3Downloader(picassoClient)).build();
            picasso.setLoggingEnabled(true);
            picasso.load(URL)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.dumy)
                    .into(userImage);
        }
    }

    @Override
    public int getItemCount() {
        if (mList != null && mList.size() > 0)
            return mList.size();
        else
            return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ChatListLayoutCardBinding binding;
        public MyViewHolder(ChatListLayoutCardBinding binding) {
            super(binding.getRoot());
            this.binding=binding;

        }
    }

    public void showList(List<ChatFriendList> noteList) {
        this.mList = noteList;

        Log.d("testList",""+noteList.size());


        notifyDataSetChanged();
    }
}