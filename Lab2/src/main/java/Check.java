package main.java;


import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
*@author popboy
*@createDate 创建时间：2019年3月31日下午6:32:46
**/

import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Check {
	public  Map<String, String> read(String file) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		//创建输入流
        FileInputStream fis = new FileInputStream(new File(file));
        //通过构造函数传参
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        //获取工作表
        XSSFSheet sheet = workbook.getSheetAt(0);
        //获取所有行数
        int realRowCount = sheet.getPhysicalNumberOfRows();
        String key = "";
        String value = "";
        /*Cell cell = Row.getCell(index);//取出cell
        String value = cell.toString;//cell内容
        BigDecimal bigDecimal = new BigDecimal(value);//创建BigDecimal对象，把科学计数转成数字
        String res = bigDecimal.toPlainString();//转成最终要的数字字符串*/
        for(int i = 2; i < realRowCount; i++) {
        	for(int j = 1; j < 4; j++) {
        		if(j == 1) {
        			key += new BigDecimal(sheet.getRow(i).getCell(j).toString()).toPlainString();
        		}else if(j == 2) {
        			key += sheet.getRow(i).getCell(j).toString();
        		}else {
        			value = sheet.getRow(i).getCell(j).toString();
        		}
        	}
        	System.out.println(key + value);
        	map.put(key, value);
        	key = "";
        	value = "";
        }
        workbook.close();
        fis.close();
		return map;
	}
	public boolean check(Map<String, String> temp) {
		WebDriver driver;
		String baseUrl;
		String driverPath =  "/Users/popboy/Documents/课程资料/大三下/软件测试/geckodriver";
		System.setProperty("webdriver.gecko.driver", driverPath);
		driver = new FirefoxDriver();
		baseUrl = "http://121.193.130.195:8800";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl + "/");
		for(String key :  temp.keySet()) {
			driver.findElement(By.name("id")).clear();
			driver.findElement(By.name("id")).sendKeys(key.substring(0, 10));
			driver.findElement(By.name("password")).clear();
			driver.findElement(By.name("password")).sendKeys(key.substring(4, 10));
			driver.findElement(By.id("btn_login")).click();
			
			System.out.print(key.substring(10, key.length()));
			if(driver.findElement(By.id("student-id")).getText().equals(key.substring(0, 10))) {
				//System.out.println(driver.findElement(By.id("student-id")).getText());
				if(driver.findElement(By.id("student-name")).getText().equals(key.substring(10, key.length()))){
					//System.out.println(driver.findElement(By.id("student-git")).getText());
					//System.out.println(temp.get(key));
					if(driver.findElement(By.id("student-git")).getText().equals(temp.get(key))) {
						System.out.println("信息完全匹配");
					}else {
						System.out.println("git地址匹配不上");
					}
				}else {
					System.out.println("姓名匹配不上");
				}
			}else {
				System.out.println("学号匹配不上");
				return false;
			}
			
			driver.findElement(By.id("btn_logout")).click();
			driver.findElement(By.id("btn_return")).click();
			
		}
		return true;
	}
//	public static void main(String [] args) throws Exception {
//		Map<String, String> result = read("/Users/popboy/Documents/课程资料/大三下/软件测试/软件测试名单.xlsx");
//		for(String key:result.keySet()) {
//			System.out.println(key);
//			System.out.println(result.get(key).length());
//			if(result.get(key).equals("")) {
//				System.out.println("no");
//			}else {
//				System.out.println(result.get(key));
//			}
//		}
//		check(result);
//	}
}
  
 