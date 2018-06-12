package com.example.surcae_laptop.pictoria;

public class GridManager {
    private static final GridManager ourInstance = new GridManager();

    public static GridManager getInstance() {
        return ourInstance;
    }
    public boolean isSearchStart = false;
    private String[] url = new String[10];
    private GridAdapter gridAdapter = null;
    private GridManager() {
    }

    public void SetGridAdapter(GridAdapter _param){
        gridAdapter = _param;
    }

    public GridAdapter getGridAdapter() {
        return gridAdapter;
    }

    public void setURLStore(String[] _param){
        url = _param;
    }
    public String getUrlWithPos(int pos){
        return url[pos];
    }
    public boolean CheckAllLoaded(){
        for(int i = 0; i < url.length; ++i){
            if(url[i].isEmpty())
                return false;
        }
        return true;
    }
}
