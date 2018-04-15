package com.lpgxp.chapter;

import com.lpgxp.chapter.util.Events;
import com.lpgxp.chapter.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
public class ChapterApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ChapterApplication.class, args);
	}

	@Autowired
	private StateMachine<Status,Events> stateMachine;


	@Override
	public void run(String... args) throws Exception {
		stateMachine.start();
		stateMachine.sendEvent(Events.PAY);
		stateMachine.sendEvent(Events.RECEIVE);
	}
}
