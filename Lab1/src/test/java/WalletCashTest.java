package test.java;


/**
*@author popboy
*@createDate 创建时间：2019年3月19日上午11:05:01
**/

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.java.WalletCash;

public class WalletCashTest {
    WalletCash c = null;
    int[] num = {-20,0,6,9,21,49,52,53,54};//录入测试用例
    int[] equal = {-1,1,1,-1,1,-1,1,1,-1};//录入期望结果
    @Before
    public void testBeforeClass(){
        c = new WalletCash();//定义好测试前所需的类
    }
    @Test
    public void testcash(){
    	for(int i = 0;i < num.length;i++) {
        int result = c.cash(num[i]);
        Assert.assertEquals(result,equal[i]);
    	}
        //输入测试用例以及期望，进行测试
    }
}
