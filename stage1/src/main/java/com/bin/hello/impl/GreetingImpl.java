package com.bin.hello.impl;

import com.bin.hello.IGreeting;

public class GreetingImpl implements IGreeting {
    final String m_name;

    public GreetingImpl(String name) {
        m_name = name;
    }

    public void sayHello() {
        System.out.println("Hello, " + m_name + "!");
    }
}