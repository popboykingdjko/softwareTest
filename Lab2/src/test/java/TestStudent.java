package test.java;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
*@author popboy
*@createDate 创建时间：2019年3月31日下午6:32:46
**/



import main.java.Check;

/**
*@author popboy
*@createDate 创建时间：2019年4月1日上午11:54:18
**/

public class TestStudent {
	private Check result =  new Check();
	private  Map<String, String> test = new HashMap<String, String>();
	
	@Before
	public void setUp() throws Exception {
		test = result.read("/Users/popboy/Documents/课程资料/大三下/软件测试/软件测试名单.xlsx");
	}
	
	@Test
	public void testStudent() throws Exception{
		
		assertEquals(true, result.check(test));
	}
}
