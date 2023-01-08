package com.example.spmpkl.ui_mahasiswa.upload;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MahasiswaMasukViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MahasiswaMasukViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is masuk fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}