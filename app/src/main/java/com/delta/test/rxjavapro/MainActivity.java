package com.delta.test.rxjavapro;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    Disposable disposable;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //基本使用
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        });
        Observer<Integer>observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Integer value) {
                Log.i(TAG, "onNext: "+value);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
            }
        };
        observable.subscribe(observer);

        //链式调用
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.i(TAG, "subscribe: emit"+1);
                e.onNext(1);
                Log.i(TAG, "subscribe: emit"+2);
                e.onNext(2);
                Log.i(TAG, "subscribe: emit"+3);
                e.onNext(3);
                Log.i(TAG, "subscribe: emit"+4);
                e.onNext(4);
                Log.i(TAG, "subscribe: emit"+5);
                e.onNext(5);
                e.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe: ");
                disposable = d;
            }

            @Override
            public void onNext(Integer value) {
                Log.i(TAG, "onNext: "+value);
                if(value == 3){
                    disposable.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
            }
        });

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.i(TAG, "subscribe: emit"+1);
                e.onNext(1);
                Log.i(TAG, "subscribe: emit"+2);
                e.onNext(2);
                Log.i(TAG, "subscribe: emit"+3);
                e.onNext(3);
                Log.i(TAG, "subscribe: emit"+4);
                e.onNext(4);
                Log.i(TAG, "subscribe: emit"+5);
                e.onNext(5);
                e.onComplete();
            }
            //onNext
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

            }
            //onError
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
            //onComplete
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
            //onSubscribe
        }, new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {

            }
        });
    }
}
