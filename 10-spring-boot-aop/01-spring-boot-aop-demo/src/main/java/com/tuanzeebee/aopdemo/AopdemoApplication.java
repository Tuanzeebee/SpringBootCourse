package com.tuanzeebee.aopdemo;

import com.tuanzeebee.aopdemo.dao.AccountDAO;
import com.tuanzeebee.aopdemo.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO){
		return runner ->{
			demoTheBeforeAdvice(theAccountDAO,theMembershipDAO);
		};
	}

	private void demoTheBeforeAdvice(AccountDAO theAccountDAO,MembershipDAO theMembershipDAO) {

		//call the business method
		Account myAccount= new Account();
		theAccountDAO.addAccount(myAccount,true);
		theAccountDAO.doWork();

		//call the business method
		theMembershipDAO.addSillyMember();
		theMembershipDAO.goToSleep();
		//do it again!
//		System.out.println("\n let's call it again");
//
//		//call the business method again
//		theAccountDAO.addAccount();
//		theMembershipDAO.addAccount();
	}

}
