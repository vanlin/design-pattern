// Copyright (c) 2015‐2017 LIANHEBAOLI. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.2 : 2017-12-04, wanlin.liu, creation
// ============================================================================
package me.vanlin.observer;

import org.junit.Test;

/**
 * author wanlin.liu
 *
 * @version 2.2.0
 */
public class TeacherTest {
    @Test
    public void testTeacher() {
        Teacher teacher = new Teacher();

        Student student1 = new Student("张三", teacher);
        teacher.addObserver(student1);
        Student student2 = new Student("李四", teacher);
        teacher.addObserver(student2);

        teacher.setHomework("家庭作业1");
        teacher.setHomework("家庭作业2");
        teacher.setHomework("家庭作业3");
    }
}
