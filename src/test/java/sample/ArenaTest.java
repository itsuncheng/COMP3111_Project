package sample;
import static org.junit.Assert.*;
import org.junit.Test;


public class ArenaTest {
	
	@Test
	public void test() {
		boolean [][] isGreen = {{true,false},{true,true},{false,false,false}};
		Arena a1 = new Arena(2,3,isGreen);
		assertEquals(a1.isValidArena, false);
		
		boolean [][] isGreen2 = {{true,false},
							{true,true},
							{false,false}};
		
		
		 
		a1 = new Arena(3,3,isGreen2);
		assertEquals(a1.isValidArena, false);
		
		a1 = new Arena(2,1,isGreen2);
		assertEquals(a1.isValidArena, false);
		
		a1 = new Arena(2,3,isGreen2);
		assertEquals(a1.isValidArena, true);
		
		assertEquals(a1.isGreenGrid(1,1), true);
		assertEquals(a1.isGreenGrid(2,1), false);
		
		Fox fox = new Fox();
		a1.addMonster(fox);
		assertEquals(fox,a1.monsters.get(0));
		
		
		a1.removeMonster(fox);
		a1.removeMonster(fox);
		
		a1.setMoney(-1);
		a1.setMoney(5);
		assertEquals(a1.getMoney(),5);
		
		a1.addMoney(-5);
		a1.addMoney(2);
		assertEquals(a1.getMoney(),7);
		
		a1.removeMoney(8);
		assertEquals(a1.getMoney(),7);
		
		a1.removeMoney(-1);
		assertEquals(a1.getMoney(),7);
		
		a1.removeMoney(6);
		assertEquals(a1.getMoney(),1);
	
		
	}

}

