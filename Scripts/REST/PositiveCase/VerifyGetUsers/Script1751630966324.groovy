import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import groovy.json.JsonSlurper


def response = WS.sendRequest(findTestObject('API/Positive Case/GETUsers'))


WS.verifyResponseStatusCode(response, 200)

per_page_value = WS.getElementText(response, 'per_page')
data_count = WS.getElementsCount(response, 'data')

WS.verifyElementPropertyValue(response, 'page', 2)
WS.verifyElementPropertyValue(response, 'per_page', 6)
WS.verifyElementPropertyValue(response, 'data[0].id', 7)
WS.verifyElementPropertyValue(response, 'data[0].email', 'michael.lawson@reqres.in')
WS.verifyElementPropertyValue(response, 'data[0].first_name', 'Michael')
WS.verifyElementPropertyValue(response, 'data[0].last_name', 'Lawson')
WS.verifyElementPropertyValue(response, 'data[0].avatar', 'https://reqres.in/img/faces/7-image.jpg')

assert per_page_value == data_count.toString()

def json = new JsonSlurper().parseText(response.getResponseText())

json.data.each { data ->
	println "ID        : ${data.id}"
	println "Email     : ${data.email}"
	println "First Name: ${data.first_name}"
	println "Last Name : ${data.last_name}"
	println "Avatar    : ${data.avatar}"
	println "-----------------------------"
}