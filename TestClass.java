import org.junit.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class TestClass {

	@Test
	public void playerInitTest01() {
		Player p = new Player("test");
		assertEquals("test", p.getName());
		assertTrue(p.isHuman());
	}

	@Test
	public void playerGetTideLevelTest01(){
		Player p = new Player("test");
		assertEquals(0, p.getTideLevel());
	}

	@Test
	public void playerSetTideLevelTest01(){
		Player p = new Player("test");
		p.setTideLevel(1);
		assertEquals(1, p.getTideLevel());
	}

	@Test
	public void playerAddPlayCardTest01(){
		Player p = new Player("test");
		Weather w = new Weather(13);
		p.addCard(w);
		assertEquals(w, p.playCard(0));
	}

	@Test
	public void playerGetCardCountTest01(){
		Player p = new Player("test");
		for (int i = 0; i < 5; i++) {
			p.addCard(new Weather((i * 1346278 + 43298479) % 60));
		}
		assertEquals(5, p.getCardCount());
	}

	@Test
	public void playerPlayRandomCardTest01() {
		Player p = new Player();
		Weather[] w = {new Weather(13), new Weather(23), new Weather(44)};
		p.addCard(w[0]);
		p.addCard(w[1]);
		p.addCard(w[2]);
		Weather rand = p.playRandomCard();
		assertTrue(rand == w[0] || rand == w[1] || rand == w[2]);
	}

	@Test
	public void playerCalcLifePreserversTest01(){
		Player p = new Player("test");
		p.addCard(new Weather(13));
		p.addCard(new Weather(23));
		p.addCard(new Weather(24));
		p.addCard(new Weather(53));
		p.calcLifePreservers();
//		System.out.println(p.getLifePreservers());
		assertEquals(1,p.getLifePreservers());
	}

	@Test
	public void playerDecreaseLifePreserversTest01(){
		Player p = new Player("test");
		p.addCard(new Weather(13));
		p.addCard(new Weather(23));
		p.addCard(new Weather(24));
		p.addCard(new Weather(26));
		p.addCard(new Weather(53));
		p.calcLifePreservers();
		p.decreaseLifePreservers();
		assertEquals(1, p.getLifePreservers());
	}

	@Test
	public void playerIsEliminatedTest01(){
		Player p = new Player("test");
		Weather[] w = new Weather[10];

		for (int i = 0; i < 10; i++) {
			w[i] = new Weather((i * 3243 + 6757) % 60);
			p.addCard(w[i]);
		}
		p.calcLifePreservers();
		for (int i = 0; i < 12; i++) {
			p.decreaseLifePreservers();
		}
		assertTrue(p.isEliminated());
	}

	@Test
	public void playerScoreTest01(){
		Player p = new Player("test");

		p.setScore(3);
		assertEquals(3, p.getScore());
	}

	@Test
	public void weatherInitTest01(){
		Weather w = new Weather(13);
		assertNotNull(w);
	}

	@Test
	public void weatherLifePreserverTest01(){
		assertEquals(0, new Weather(12).getLifePreserver());
		assertEquals(1, new Weather(18).getLifePreserver());
		assertEquals(2, new Weather(34).getLifePreserver());
	}

	@Test
	public void weatherGetValueTest01(){
		Weather[] w = new Weather[60];
		for (int i = 0; i < 60; i++) {
			w[i] = new Weather(i + 1);
		}

		for (int i = 0; i < 60; i++) {
			assertEquals(i + 1, w[i].getValue());
		}
	}

	@Test
	public void tableInitTest01(){
		Table t = new Table(4);
		assertNotNull(t);
	}

	@Test
	public void tableTideTest01(){
		Table t = new Table(4);
		try{
			Field tideField = t.getClass().getDeclaredField("tide");
			tideField.setAccessible(true);
			int[] tide = (int[])tideField.get(t);

			assertEquals(tide.length, 24);

			int[] count = new int[12];
			for (int i = 0; i < 24; i++) {
				assertTrue(tide[i] >= 1 && tide[i] <= 12);
				count[tide[i] - 1]++;
			}
			for (int i = 0; i < 12; i++) {
				assertEquals(2, count[i]);
			}
		}
		catch(Exception e){
			fail("Exception thrown");
		}
	}

	@Test
	public void tableDrawTest01(){
		Table t = new Table(4);
		// call t.draw() by reflection
		try{
			Field tideField = t.getClass().getDeclaredField("tide");
			tideField.setAccessible(true);
			int[] tide = (int[])tideField.get(t);
			int trueTide = tide[0];

			Method method = t.getClass().getDeclaredMethod("draw");
			method.setAccessible(true);
			int gotTide = (int)method.invoke(t);
			assertEquals(trueTide, gotTide);
		}
		catch(Exception e){
			fail("Exception thrown");
		}
	}

	@Test
	public void tableFindIndexWithBiggestCardTest01(){
		Table t = new Table(4);
		// call t.findIndexWithBiggestCard() by reflection
		try{
			Method findIndexWithBiggestCard = t.getClass().getDeclaredMethod("findIndexWithBiggestCard", Weather[].class);
			findIndexWithBiggestCard.setAccessible(true);
			Weather[] w = {
					new Weather(54),
					new Weather(34),
					new Weather(59),
					new Weather(12)
			};
			int index = (int)findIndexWithBiggestCard.invoke(t, (Object)w);
			assertEquals(index, 2);
		}
		catch(Exception e){
			fail("Exception thrown");
		}
	}

	@Test
	public void tableFindIndexWithSecondBiggestCardTest01(){
		Table t = new Table(4);
		// call t.findIndexWithSecondBiggestCard() by reflection
		try{
			Method findIndexWithSecondBiggestCard = t.getClass().getDeclaredMethod("findIndexWithSecondBiggestCard", Weather[].class);
			findIndexWithSecondBiggestCard.setAccessible(true);
			Weather[] w = {
					new Weather(54),
					new Weather(34),
					new Weather(57),
					new Weather(12)
			};
			int index = (int)findIndexWithSecondBiggestCard.invoke(t, (Object)w);
			assertEquals(index, 0);
		}
		catch(Exception e){
			fail("Exception thrown");
		}
	}

}