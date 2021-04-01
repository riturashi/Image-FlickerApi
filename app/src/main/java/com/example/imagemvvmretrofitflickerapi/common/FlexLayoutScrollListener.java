package com.example.imagemvvmretrofitflickerapi.common;


import com.google.android.flexbox.FlexboxLayoutManager;

public abstract class FlexLayoutScrollListener extends PaginationScrollListener {

    public FlexLayoutScrollListener(FlexboxLayoutManager layoutManager) {
        super(layoutManager);
    }

    @Override
    public int getFirstVisibleItemPosition() {
        return ((FlexboxLayoutManager) mLayoutManager).findFirstVisibleItemPosition();
    }
}
