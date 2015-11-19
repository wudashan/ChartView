package com.example.administrator.myapplication.charts;

import android.content.Context;
import android.view.View;

/**
 * Created by wudashan on 2015/11/19 0019.
 */
public class RefreshLoadingView extends View{


    private static LoadingViewListener DEFAULT = new LoadingViewListener() {
        @Override
        public void hiddenAutoLoading(View paramView) {
        }

        @Override
        public void showAutoLoading(View paramView) {
        }
    };

    private LoadingViewListener loadingViewListener = DEFAULT;

    public RefreshLoadingView(Context paramContext){
        super(paramContext);
    }

    public LoadingViewListener getLoadingViewListener(){
        if (this.loadingViewListener == null){
            return DEFAULT;
        }
        return this.loadingViewListener;
    }


    public interface LoadingViewListener{
        void hiddenAutoLoading(View paramView);
        void showAutoLoading(View paramView);
    }
}
