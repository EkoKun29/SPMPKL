package com.example.spmpkl.ui_mahasiswa.rekap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MahasiswaRekapViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MahasiswaRekapViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}