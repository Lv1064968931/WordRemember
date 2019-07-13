package com.example.nuc.wordremember.Application;

import android.app.Application;

import com.example.nuc.wordremember.ViewItem.ExploreItem;
import com.example.nuc.wordremember.ViewItem.WordItem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 声明一个全局变量来实现两个活动之间调用同一个变量
 */

public class ChangewordApp extends Application {


    public static int ChangewordCount = 0;
    public static int ClockingCount = 0;

    public static List<WordItem> strangewordList = new ArrayList<>();
    public static List<ExploreItem> loveessayList = new ArrayList<>();

}
