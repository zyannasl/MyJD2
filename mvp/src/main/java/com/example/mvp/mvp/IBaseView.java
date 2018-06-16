package com.example.mvp.mvp;

public interface IBaseView {
    void showLoading();
    void hideLoading();
    void serverFail(String msg);
}
