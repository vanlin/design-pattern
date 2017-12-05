// Copyright (c) 2015‐2017 LIANHEBAOLI. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.2 : 2017-12-04, wanlin.liu, creation
// ============================================================================
package me.vanlin.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * author wanlin.liu
 *
 * @version 2.2.0
 */
public class Student implements Observer {
    private Observable ob;
    private String name;

    public Student(String name,Observable ob) {
        this.ob = ob;
        this.name=name;
        ob.addObserver(this);
    }

    public void update(Observable o, Object arg) {
        Teacher t=(Teacher)o;
        System.out.println(name+"得到作业信息:"+t.getInfo());
    }
}
