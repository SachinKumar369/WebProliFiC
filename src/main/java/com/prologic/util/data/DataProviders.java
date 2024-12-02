package com.prologic.util.data;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;


public class DataProviders {



	@DataProvider(name="suiteDataProvider")
	public static Object[][] getDataSuite(Method m){

//		String dataDir = System.getProperty("user.dir")+File.separator+"data"+File.separator+"DataSuite.xlsx";
//
//		ExcelReader excel = new ExcelReader(dataDir);
//
//		return Utility.getData(m.getName(), excel);

		return new Object[0][];
	}


	@DataProvider(name="suiteADataProvider")
	public static Object[][] getDataSuiteA(Method m){
//		init();
//		ExcelReader excel = new ExcelReader(prop.getProperty("xlsFileLocation")+"\\"+Constants.FIRST_SUITE+".xlsx");
//
//		return Utility.getData(m.getName(), excel);

		return new Object[0][];
	}
	
	@DataProvider(name="suiteBDataProvider")
	public static Object[][] getDataSuiteB(Method m){
//		init();
//		ExcelReader excel = new ExcelReader(prop.getProperty("xlsFileLocation")+"\\"+Constants.SECOND_SUITE+".xlsx");
//
//		return Utility.getData(m.getName(), excel);

		return new Object[0][];
	}
	
	@DataProvider(name="suiteCDataProvider")
	public static Object[][] getDataSuiteC(Method m){
//		init();
//		ExcelReader excel = new ExcelReader(prop.getProperty("xlsFileLocation")+"\\"+Constants.THIRD_SUITE+".xlsx");
//
//		return Utility.getData(m.getName(), excel);

		return new Object[0][];
	}
	
	
	

}
