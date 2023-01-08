package com.example.spmpkl.ui_dosen.nilai_dosen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NilaiViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public NilaiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}