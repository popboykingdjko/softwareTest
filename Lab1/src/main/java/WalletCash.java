package main.java;


/**
*@author popboy
*@createDate 创建时间：2019年3月19日上午11:03:32
**/

public class WalletCash {

	int[] list1 = {50,20,5,5,1,1,1};//从大到小输入现有的金钱种类
    
    public int cash(int num) {
		int remain = 0;//剩余钱数
		for(int i = 0;i <list1.length;i++) {
			remain += list1[i];
		}//计算目前资产总额
		if(num > remain) {
			return -1;
		}//目标比总额还多，返回异常
		else if(num < 0){
			return -1;
		}//给出样例为负时，返回异常
		for(int i = 0;i <list1.length;i++) {
			if(num >= list1[i]) {
				num -= list1[i];
			}
		}//循环测试，从大到小依次给出支付排列方式
		if(num == 0) {
			return 1;
		}//若能成功给出目标数值，结果为0，返回1
		else {
			return -1;
		}//若不能成功给出目标数值，结果不为0，返回-1
	}
}
