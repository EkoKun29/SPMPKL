package com.example.spmpkl.ui.rekap_presensi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RekapViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public RekapViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}