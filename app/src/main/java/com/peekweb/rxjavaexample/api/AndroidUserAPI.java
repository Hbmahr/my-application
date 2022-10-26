package com.peekweb.rxjavaexample.api;



import com.peekweb.rxjavaexample.model.ChatFriendListResponse;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface AndroidUserAPI {



   @Headers({"projectid: 63183b5bb110c06cb4822451"})
    @GET("friends/chatUserList/632088181ef7ace35eff6ece")
    Observable<ChatFriendListResponse> getFriendsChats();




}