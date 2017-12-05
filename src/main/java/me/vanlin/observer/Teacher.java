// Copyright (c) 2015‐2017 LIANHEBAOLI. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.2 : 2017-12-04, wanlin.liu, creation
// ============================================================================
package me.vanlin.observer;


import java.util.Observable;

/**
 * author wanlin.liu
 *
 * @version 2.2.0
 */
public class Teacher extends Observable {
    //布置作业的状态信息字符串
    private String info;
    public void setHomework(String info) {

        this.info=info;
        System.out.println("布置的作业是"+info);

        setChanged();
        notifyObservers();
    }
    public String getInfo() {
        return info;
    }
}
