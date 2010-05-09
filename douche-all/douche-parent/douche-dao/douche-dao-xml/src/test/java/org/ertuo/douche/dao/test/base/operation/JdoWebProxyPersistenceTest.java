package org.ertuo.douche.dao.test.base.operation;

import java.util.Date;

import javax.jdo.PersistenceManager;

import org.ertuo.douche.dao.domain.WebProxyDo;
import org.ertuo.douche.dao.opration.Repository;
import org.ertuo.douche.dao.opration.jdo.impl.ProxyCnDao;
import org.ertuo.douche.dao.test.base.BaseGoogleAppEngineTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.inject.Provider;

/**
 * This is a test of the JdoGreetingRepository and JdoGreetingQueries classes in
 * isolation, without invoking Wicket.
 */
public class JdoWebProxyPersistenceTest extends BaseGoogleAppEngineTest {
	private PersistenceManager pm;
	private Repository<WebProxyDo> greetingRepo;

	// private GreetingQueries greetingQueries;

	@BeforeMethod
	public void setUp() {
		pm = persistenceManagerFactory.getPersistenceManager();

		final Provider<PersistenceManager> pmProvider = new Provider<PersistenceManager>() {
			// @Override
			public PersistenceManager get() {
				return pm;
			}
		};

		greetingRepo = new ProxyCnDao();
	}

	@AfterMethod
	public void tearDown() {
		pm.close();
	}

	@Test
	public void testPersistWithAuthenticatedUser() {
		WebProxyDo webProxy = new WebProxyDo();
		webProxy.setCheckDate(new Date());
		webProxy.setPort(80);
		webProxy.setUrl("http://www.alipay2.net");
		webProxy.setUseDate(new Date());

		greetingRepo.persist(webProxy);
		WebProxyDo webProxyDo = greetingRepo.get(webProxy.getId());
		System.out.println("∂‘œÛ£∫" + webProxyDo);
		// assertNotNull(greeting.getId());
		// assertNotNull(greetingRepo.get(greeting.getId()));
	}

	/*
	 * @Test public void testPersistWithAnonymousUser() { final String content =
	 * "Hello, World!"; final Date date = new Date(); Greeting greeting = new
	 * Greeting(null, content, date);
	 * 
	 * greetingRepo.persist(greeting);
	 * 
	 * assertNotNull(greeting.getId());
	 * assertNotNull(greetingRepo.get(greeting.getId())); }
	 * 
	 * @Test public void testDelete() { final String content = "Hello, World!";
	 * final Date date = new Date(); final Greeting greeting = new
	 * Greeting(null, content, date);
	 * 
	 * greetingRepo.runInTransaction(new Runnable() { public void run() {
	 * greetingRepo.persist(greeting); } });
	 * 
	 * Long id = greeting.getId(); assertNotNull(greetingRepo.get(id));
	 * 
	 * greetingRepo.runInTransaction(new Runnable() { public void run() {
	 * greetingRepo.delete(greeting); } });
	 * 
	 * assertNull(greetingRepo.get(id)); }
	 * 
	 * @Test public void testLatestGreetingsQuery() { final Calendar calendar =
	 * Calendar.getInstance(); final String content = "%d: Hello, World!"; final
	 * Date baseDate = calendar.getTime(); final int total = 7; final int max =
	 * 4; List<Date> dates = new ArrayList<Date>(); for (int i = 0; i < total;
	 * i++) { calendar.setTime(baseDate); calendar.add(Calendar.MINUTE, i); Date
	 * date = calendar.getTime(); dates.add(date); Greeting greeting = new
	 * Greeting(null, String.format(content, i), date);
	 * greetingRepo.persist(greeting); }
	 * 
	 * // The latest greetings are returned in descending date order.
	 * List<Greeting> greetings = greetingQueries.latest(max);
	 * 
	 * assertEquals(greetings.size(), max); Collections.reverse(greetings);
	 * dates = dates.subList(total - max, total); for (int i = 0; i < max; i++)
	 * { Greeting greeting = greetings.get(i);
	 * assertEquals(greeting.getContent(), String.format(content, total - max +
	 * i)); assertEquals(greeting.getDate(), dates.get(i)); } }
	 */
}
