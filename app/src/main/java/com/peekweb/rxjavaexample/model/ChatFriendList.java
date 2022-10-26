package com.peekweb.rxjavaexample.model;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class ChatFriendList implements Serializable {
    public ChatFriendList() {
    }

    @SerializedName("messageType")
    @Expose
    private Integer messageType;
    @SerializedName("senderId")
    @Expose
    private String senderId;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("messageCounter")
    @Expose
    private Integer messageCounter;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;
    @SerializedName("onlineStatus")
    @Expose
    private Integer onlineStatus;
    @SerializedName("receipt_status")
    @Expose
    private Integer receiptStatus;
    @SerializedName("unread")
    @Expose
    private Integer unread;
    @SerializedName("ismute")
    @Expose
    private Integer ismute;


    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getCreatedAt() {


        createdAt=getDateAndTime(createdAt);
        return createdAt;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getDateAndTime(String createdAt) {
        String time=Instant.parse(createdAt)                // Parse this String in standard ISO 8601 format as a `Instant`, a point on the timeline in UTC. The `Z` means UTC.
                .atOffset(ZoneOffset.UTC)                                // Change from `Instant` to the more flexible `OffsetDateTime`.
                .format(                                                   // Generate a String representing the value of this `OffsetDateTime` object.
                        DateTimeFormatter.ofPattern("dd-MM-YYYY")   // Specify a formatting pattern as desired.
                );
        DateFormat dateFormat = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        }
        String currentDate = dateFormat.format(new Date()).toString();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterday=dateFormat.format(cal.getTime());

        if(time.equals(currentDate))
        {
            time="today";
        }
        else if(time.equals(yesterday)){
            time="yesterday";
        }
        return time;
    }

    public void setCreatedAt(String createdAt) {

        this.createdAt = createdAt;
    }

    public String getMessageCounter() {


        String counter=messageCounter.toString();
        return counter;
    }

    public void setMessageCounter(Integer messageCounter) {
        this.messageCounter = messageCounter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public Integer getReceiptStatus() {
        return receiptStatus;
    }

    public void setReceiptStatus(Integer receiptStatus) {
        this.receiptStatus = receiptStatus;
    }

    public Integer getUnread() {
        return unread;
    }

    public void setUnread(Integer unread) {
        this.unread = unread;
    }

    public Integer getIsmute() {
        return ismute;
    }

    public void setIsmute(Integer ismute) {
        this.ismute = ismute;
    }



}
