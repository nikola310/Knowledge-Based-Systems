package com.sbnz.doctor.controller;

import java.util.concurrent.TimeUnit;

import org.drools.core.time.SessionPseudoClock;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import com.sbnz.doctor.dto.WebsocketMessage;
import com.sbnz.doctor.interfaces.services.PatientServiceInterface;
import com.sbnz.doctor.monitoring.Heartbeat;
import com.sbnz.doctor.monitoring.ChangeOxygenLevel;
import com.sbnz.doctor.monitoring.PatientModel;
import com.sbnz.doctor.monitoring.UrinationAmount;

/**
 * @author Nikola
 *
 */
@Controller
public class PatientMonitoringController {

	@Autowired
	private PatientServiceInterface patientService;

	@Autowired
	private KieContainer container;

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/monitoring")
	@SendToUser("/topic/notifications")
	public void greeting(SimpMessageHeaderAccessor headerAccessor) throws Exception {
		SimpMessageHeaderAccessor ha = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
		ha.setSessionId(headerAccessor.getSessionId());
		ha.setLeaveMutable(true);

		KieSession sesija = container.newKieSession("monitoringSession");
		PatientModel pm = new PatientModel(patientService.Read(1));
		sesija.insert(pm);
		SessionPseudoClock clock = sesija.getSessionClock();
		for (int i = 0; i < 40; i++) {
			sesija.insert(new Heartbeat(pm.getPatientId()));
		}
		clock.advanceTime(9, TimeUnit.SECONDS);
		sesija.getAgenda().getAgendaGroup("Heartbeat").setFocus();
		int fired = sesija.fireAllRules();
		if (fired > 0) {
			WebsocketMessage reply = new WebsocketMessage("Patient has heart problems!");
			simpMessagingTemplate.convertAndSendToUser(headerAccessor.getSessionId(), "/topic/notifications", reply,
					ha.getMessageHeaders());
		}

		Thread.sleep(5000);

		clock.advanceTime(10, TimeUnit.SECONDS);
		sesija.insert(new UrinationAmount(pm.getPatientId(), 25.0));
		clock.advanceTime(8, TimeUnit.HOURS);
		sesija.insert(new UrinationAmount(pm.getPatientId(), 10.0));
		clock.advanceTime(3, TimeUnit.HOURS);
		sesija.insert(new UrinationAmount(pm.getPatientId(), 25.0));
		clock.advanceTime(1, TimeUnit.HOURS);
		for (int i = 0; i < 15; i++) {
			sesija.insert(new Heartbeat(pm.getPatientId()));
		}
		sesija.getAgenda().getAgendaGroup("Dialysis").setFocus();
		fired = sesija.fireAllRules();
		System.out.println(clock.getCurrentTime());
		if (fired > 0) {
			WebsocketMessage reply = new WebsocketMessage("Patient needs dialysis!");
			simpMessagingTemplate.convertAndSendToUser(headerAccessor.getSessionId(), "/topic/notifications", reply,
					ha.getMessageHeaders());
		}

		Thread.sleep(5000);

		sesija.insert(new ChangeOxygenLevel(pm.getPatientId(), -10));
		clock.advanceTime(5, TimeUnit.MINUTES);
		sesija.insert(new ChangeOxygenLevel(pm.getPatientId(), 10));
		clock.advanceTime(5, TimeUnit.MINUTES);
		sesija.insert(new ChangeOxygenLevel(pm.getPatientId(), -50));
		clock.advanceTime(5, TimeUnit.MINUTES);
		sesija.insert(new ChangeOxygenLevel(pm.getPatientId(), -10));
		clock.advanceTime(5, TimeUnit.MINUTES);
		clock.advanceTime(10, TimeUnit.MINUTES);
		sesija.getAgenda().getAgendaGroup("Oxygen").setFocus();
		fired = sesija.fireAllRules();
		if (fired > 0) {
			WebsocketMessage reply = new WebsocketMessage("Patient has oxygen problems!");
			simpMessagingTemplate.convertAndSendToUser(headerAccessor.getSessionId(), "/topic/notifications", reply,
					ha.getMessageHeaders());
		}
	}

}