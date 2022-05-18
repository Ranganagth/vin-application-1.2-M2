package com.example.M2.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service
public class M2Consumer {

	@Autowired
	M2Producer producer;
	
	//Consume from K1 KAFKA
	@KafkaListener(topics="k1-topic", groupId="mygroup1")

	public int consumeFromTopic(String message) {
		System.out.println("Consumed message from k1-topic "+message);
		String msg=message.substring(0,20);
		int speed1 = Integer.parseInt(message.substring(17,20));
		
		if(msg.length() != 20 || speed1<0) {
			return 0;
		}
		
		String vin = msg.substring(0,17);
		String speed = msg.substring(17,20);
		String time= message.substring(20);
		
		boolean isvinaalphaNumeric;
		boolean isvinnNumeric;
		boolean isNumeric;
		
		char verify = 'n';
		char alert = 'n';
		
		String vina = vin.substring(0,11);
		String vinn = vin.substring(11,17);
		isvinaalphaNumeric = vina.matches("^[a-zA-Z0-9]*$");
		isvinnNumeric = vinn.matches("^[0-9]*$");
		isNumeric = speed.matches("^[0-9]*$");
		
		if(isvinaalphaNumeric && isNumeric && isvinnNumeric) {
			if(Integer.parseInt(speed)>100) {
				alert = 'y';
				verify = 'y';
				String VinAndSpeedAndTime = vin+verify+speed+alert+time;
				producer.publishToTopic(VinAndSpeedAndTime);
			}
			else {
				verify = 'y';
				String VinAndSpeedAndTime = vin+verify+speed+alert+time;
				producer.publishToTopic(VinAndSpeedAndTime);
			}
			return 1;
		}
		else {
			if(Integer.parseInt(speed)>100){
				alert = 'y';
				verify = 'n';
				String VinAndSpeedAndTime = vin+verify+speed+alert+time;
				producer.publishToTopic(VinAndSpeedAndTime);
			}
			else {
				alert = 'n';
				verify = 'n';
				String VinAndSpeedAndTime = vin+verify+speed+alert+time;
				producer.publishToTopic(VinAndSpeedAndTime);
				
			}
		}
    return 0;
}

}
