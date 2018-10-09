package googletests.listeners;

import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import googletests.helpers.Log;

public class TestListener extends TestListenerAdapter{
	private static Logger log = Log.getLogData(EventListener.class.getName());
	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("[Configuration] - Test started");
		log.info("Test started");
		System.out.println("-----------------------------------------------------------------------------");
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("-----------------------------------------------------------------------------");
		System.out.printf("[Results] - Test %s PASSED \n" ,result.getName());
		log.info("Test " + result.getName() + " PASSED");
		System.out.printf("[Information] - Priority of this test is %d \n" ,result.getMethod().getPriority());
		log.info("Priority of this test is " + result.getMethod().getPriority());
		System.out.println("-----------------------------------------------------------------------------");
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("-----------------------------------------------------------------------------");
		System.out.printf("[Results] - Test %s SKIPPED \n" ,result.getName());
		log.info("Test " + result.getName() + " SKIPPED");
		System.out.printf("[Information] - Priority of this test is %d \n" ,result.getMethod().getPriority());
		log.info("Priority of this test is " + result.getMethod().getPriority());
		System.out.println("-----------------------------------------------------------------------------");
	}
}
