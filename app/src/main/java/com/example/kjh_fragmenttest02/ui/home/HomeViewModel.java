package com.example.kjh_fragmenttest02.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        Log.d(getClass().getName(), "HomeViewModel()");
        mText = new MutableLiveData<>();
        mText.setValue("1번째 Fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}