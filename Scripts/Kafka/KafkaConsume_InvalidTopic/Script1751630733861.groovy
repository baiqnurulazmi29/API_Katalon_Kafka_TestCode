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
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.ConsumerRecord
import java.util.Properties
import java.util.Arrays

// === [1] Konfigurasi Kafka Consumer ===
Properties props = new Properties()
props.put("bootstrap.servers", "localhost:9092") // Kafka broker lokal
props.put("group.id", "test-group")              
props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
props.put("auto.offset.reset", "earliest")      

KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)

// === [2] Subscribe ke topic ===
consumer.subscribe(Arrays.asList("test-invalid-topic"))

println "Listening for messages on topic: test-topic ..."

// === [3] Poll pesan selama 5 detik ===
ConsumerRecords<String, String> records = consumer.poll(5000)

if (records.isEmpty()) {
	println "Tidak ada pesan karena topik salah - Expected"
	assert true
}  else {
    println "Ada pesan diterima di topik yang seharusnya tidak ada"
    for (ConsumerRecord<String, String> record : records) {
        println "Value: " + record.value()
    }
    assert false
}

consumer.close()

