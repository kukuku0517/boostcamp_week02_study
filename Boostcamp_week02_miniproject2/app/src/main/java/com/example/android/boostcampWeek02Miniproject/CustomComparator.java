package com.example.android.boostcampWeek02Miniproject;

import java.util.Comparator;

/**
 * Created by samsung on 2017-07-13.
 */

class CustomComparator implements Comparator<CardItem> {

    private int sortType;

    public CustomComparator(int sortType) {
        this.sortType = sortType;
    }

    @Override
    public int compare(CardItem o1, CardItem o2) {
        long obj1 = 0;
        long obj2 = 0;
        switch (sortType) {
            case 0:
                obj1 = o1.getDistance();
                obj2 = o2.getDistance();
                break;
            case 1:
                obj1 = o1.getLikes();
                obj2 = o2.getLikes();
                break;
            case 2:
                obj1 = o1.getDate().getTime();
                obj2 = o2.getDate().getTime();
                break;
        }
        if (obj1 > obj2)
            return 1;
        else if (obj1 < obj2)
            return -1;
        return 0;
    }
}