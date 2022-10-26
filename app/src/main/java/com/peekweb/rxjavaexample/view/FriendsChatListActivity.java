package com.peekweb.rxjavaexample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.peekweb.rxjavaexample.R;
import com.peekweb.rxjavaexample.api.AndroidUserAPI;
import com.peekweb.rxjavaexample.api.RetrofitAPI;
import com.peekweb.rxjavaexample.databinding.ActivityFrindsChatListActivitryBinding;
import com.peekweb.rxjavaexample.model.ChatFriendListResponse;
import com.peekweb.rxjavaexample.viewModel.ChatListViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class FriendsChatListActivity extends AppCompatActivity {

    private ActivityFrindsChatListActivitryBinding chatListActivityBinding;
    private ChatListViewModel chatListViewModel;
    private DisposableObserver mGetChatList;
    private RecyclerView rl_chatlist;
    private ChatListAdapter chatListAdapter;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();

     /*   chatListActivityBinding.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/


        setUpView();
    }
    private void initDataBinding() {
        chatListActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_frinds_chat_list_activitry);
        chatListViewModel = new ChatListViewModel(this);
        chatListActivityBinding.setChatListViewModel(chatListViewModel);
    }

    private void setUpView() {
        setSupportActionBar(chatListActivityBinding.toolbar);

        rl_chatlist = chatListActivityBinding.rlchatlist;
        rl_chatlist.setLayoutManager(new LinearLayoutManager(this));
        chatListAdapter = new ChatListAdapter();
        chatListAdapter.showList(chatListViewModel.getDataList());
        rl_chatlist.setAdapter(chatListAdapter);
    }

    private void mGetChatList() {
        mGetChatList = new DisposableObserver<ChatFriendListResponse>() {
            @Override
            public void onNext(ChatFriendListResponse data) {
                if (data != null && data.getData().size() > 0) {
                    chatListViewModel.updateFriendsChatList(data.getData());
                    chatListActivityBinding.setChatListViewModel(chatListViewModel);
                    updateList();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Android_error", e.getMessage());
            }
            @Override
            public void onComplete() {
                Log.d("Android complete","done");
            }
        };
        chatListViewModel.getFriendsChatList(mGetChatList);


    }

    private void updateList() {
        chatListAdapter.showList(chatListViewModel.getDataList());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGetChatList();
    }
}