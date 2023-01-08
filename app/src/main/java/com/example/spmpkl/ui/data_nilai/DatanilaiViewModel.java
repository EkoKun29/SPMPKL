package com.example.spmpkl.ui.data_nilai;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DatanilaiViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DatanilaiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}