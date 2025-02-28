package com.tuanzeebee.aopdemo.dao;

import com.tuanzeebee.aopdemo.Account;

public interface AccountDAO {

    void addAccount(Account theAccount,boolean vipFlag);

    boolean doWork();
}
