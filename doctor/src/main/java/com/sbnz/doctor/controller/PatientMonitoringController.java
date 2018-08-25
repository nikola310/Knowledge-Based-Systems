package com.sbnz.doctor.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.util.HtmlUtils;

import com.sbnz.doctor.dto.Greeting;
import com.sbnz.doctor.dto.HelloMessage;
import com.sbnz.doctor.monitoring.MonitorMessage;

/**
 * @author Nikola
 *
 */
@Controller
public class PatientMonitoringController {

	//@Autowired
	//private PatientServiceInterface patientService;
	
	static List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
	
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {
		Thread.sleep(1000); // simulated delay
		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	}
	
	@MessageMapping("/start")
	@SendTo("/patient/monitor")
	public Greeting startSimulation(long patientId) throws Exception {
		Thread.sleep(1000); // simulated delay
		
		//PatientDTO patient = patientService.Read(patientId);
		
		//PatientModel pseudoPatient = new PatientModel(patient);
		
		// get kie session
		
		
		// insert object
		
		
		
		
		return null;
	}
	
	@SendTo("/patient/monitor")
	private MonitorMessage sendMessage() throws Exception{
		return null;
	}
	
}
