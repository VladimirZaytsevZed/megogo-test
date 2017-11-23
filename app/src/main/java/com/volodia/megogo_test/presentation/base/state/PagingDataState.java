package com.volodia.megogo_test.presentation.base.state;

/**
 * Created by Volodia on 22.11.2017.
 */

public class PagingDataState {

    private DataState dataState = DataState.NONE;
    private int page;

    public DataState getDataState() {
        return dataState;
    }

    public int getPage() {
        return page;
    }

    public void setDataState(DataState dataState) {
        this.dataState = dataState;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PagingDataState that = (PagingDataState) o;

        if (page != that.page) return false;
        return dataState == that.dataState;
    }

    @Override
    public int hashCode() {
        int result = dataState.hashCode();
        result = 31 * result + page;
        return result;
    }


    public boolean isInitState() {
        return page == 0 && dataState == DataState.NONE;
    }

    public void clear() {
        page = 0;
        dataState = DataState.NONE;
    }

    @Override
    public String toString() {
        return "PagingDataState{" +
                "dataState=" + dataState +
                ", page=" + page +
                '}';
    }
}
