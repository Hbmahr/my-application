package com.peekweb.rxjavaexample.viewModel;


import android.content.Context;
import android.util.Log;

import com.peekweb.rxjavaexample.api.AndroidUserAPI;
import com.peekweb.rxjavaexample.model.ChatFriendList;
import com.peekweb.rxjavaexample.api.RetrofitAPI;
import com.peekweb.rxjavaexample.model.ChatFriendListResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class ChatListViewModel extends Observable {


    private Context mContext;
    private List<ChatFriendList> dataFriendList;
    CompositeDisposable compositeDisposable=new CompositeDisposable();


    public ChatListViewModel(Context mContext) {
        this.mContext = mContext;
        this.dataFriendList = new ArrayList<ChatFriendList>();
    }

    public void getFriendsChatList(Observer mObserver) {

       /* new RetrofitAPI().getInstance().create(AndroidUserAPI.class)
                .getFriendsChats("63183b5bb110c06cb4822451")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(mObserver);*/
        Retrofit retrofit = RetrofitAPI.getInstance();
        AndroidUserAPI myAPI = retrofit.create(AndroidUserAPI.class);
        myAPI.getFriendsChats()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);

    }

    public void updateFriendsChatList(List<ChatFriendList> list) {
        dataFriendList = list;
    }

    public List<ChatFriendList> getDataList() {
        return dataFriendList;
    }


    public boolean hasData(){
        if(getDataList()!=null && getDataList().size()>0)
            return true;
        return false;
    }




}