package springjooq.dao;

import java.util.Random;
import java.util.UUID;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.databene.contiperf.timer.RandomTimer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.jooq.dao.generated.tables.pojos.UserDetails;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-config.xml" })
public class ServiceTest {
	@Rule
	public ContiPerfRule contiPerfRule=new ContiPerfRule();

	private static Random random;

	static {
		random = new Random();
	}

	@Autowired
	private PerformanceServiceBean performanceServiceBean;

	@PerfTest(invocations=1000000,threads=100,rampUp=1000)
	@Test
	public void test() {
		System.out.println("Thread ID = " + Thread.currentThread().getId());
		UserDetails details = new UserDetails();
		details.setAddress("Test Address");
		details.setAge(random.nextInt(100));
		details.setUserName("makky-"+UUID.randomUUID().getLeastSignificantBits());
		performanceServiceBean.addUser(details);
	}
}
