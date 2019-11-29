package sample;

import static org.junit.Assert.*;

import org.junit.Test;

public class MonsterTest {

	@Test
	public void test() {
		
		Monster m1 = new Fox();
		assertEquals(m1.getHp(), 5);
		assertEquals(m1.getSpeed(), 6);
		assertEquals(m1.getX(), 0);
		assertEquals(m1.getY(), 0);
		assertEquals(m1.getMonsterType(), "Fox");
		assertEquals(m1.getIsIced(), false);
		assertEquals(m1.getIceTime(), 0);
		
		Monster m2 = new Unicorn();
		assertEquals(m2.getHp(), 8);
		assertEquals(m2.getSpeed(), 1);
		assertEquals(m2.getX(), 0);
		assertEquals(m2.getY(), 0);
		assertEquals(m2.getMonsterType(), "Unicorn");
		assertEquals(m2.getIsIced(), false);
		assertEquals(m2.getIceTime(), 0);
		
		Monster m3 = new Penguin();
		assertEquals(m3.getHp(), 6);
		assertEquals(m3.getSpeed(), 3);
		assertEquals(m3.getX(), 0);
		assertEquals(m3.getY(), 0);
		assertEquals(m3.getMonsterType(), "Penguin");
		assertEquals(m3.getIsIced(), false);
		assertEquals(m3.getIceTime(), 0);
		
		m2.setX(10);
		assertEquals(m2.getX(), 10);
		
		m2.setY(10);
		assertEquals(m2.getY(), 10);
		
		m3.setImageView(null);
		m3.setX(10);
		assertEquals(m3.getX(), 10);
		
		m3.setImageView("");
		m3.setY(10);
		assertEquals(m3.getY(), 10);
		
		m3.imageView = null;
		m3.setX(20);
		m3.setY(20);
	}
	
	@Test
	public void penguinTest() {
		Monster m3 = new Penguin();
		m3.setHp(2);
		m3.replenishHp();
		
		m3.setHp(100);
		m3.replenishHp();
		
	}
	
	@Test
	public void monsterImageViewTest() {
		Monster m3 = new Penguin();
		
		MonsterImageView mIV3 = new MonsterImageView(m3);
		Monster storedMonster = mIV3.getMonster();
		
		storedMonster.setIsIced(false);
		storedMonster.setIceTime(0);
		
		//move per frame
		while(storedMonster.getIsMoving()) {
			mIV3.moveOneGrid();
			
		}
		mIV3.stateEndOfEachFrame();
		
		storedMonster.setIsIced(false);
		storedMonster.setIceTime(3);
		while(storedMonster.getIsMoving()) {
			mIV3.moveOneGrid();
			
		}
		mIV3.stateEndOfEachFrame();
		
		storedMonster.setIsIced(true);
		storedMonster.setIceTime(0);
		while(storedMonster.getIsMoving()) {
			mIV3.moveOneGrid();
			
		}
		mIV3.stateEndOfEachFrame();
		
		storedMonster.setIsIced(true);
		storedMonster.setIceTime(1);
		while(storedMonster.getIsMoving()) {
			mIV3.moveOneGrid();
			
		}
		mIV3.stateEndOfEachFrame();
		
		
		mIV3.setImageView(10,10);
		
		
		
		Monster m2 = new Unicorn();
		m2.imageView = null;
		MonsterImageView mIV2 = new MonsterImageView(m2);
		while(m2.getIsMoving()) {
			mIV2.moveOneGrid();
			
		}
		mIV2.stateEndOfEachFrame();
		mIV2.setImageView(10,10);
		
		// if ((x == maxX-2*stepX && y == 0)) 
		Monster m1 = new Fox();
		m1.setX(480-2*40);
		m1.setY(0);
		MonsterImageView mIV1 = new MonsterImageView(m1);
		while(m1.getIsMoving()) {
			mIV1.moveOneGrid();
			
		}
		mIV1.stateEndOfEachFrame();
		while(m1.getIsMoving()) {
			mIV1.moveOneGrid();
			
		}
		mIV1.stateEndOfEachFrame();
		while(m1.getIsMoving()) {
			mIV1.moveOneGrid();
			
		}
		mIV1.stateEndOfEachFrame();
		while(m1.getIsMoving()) {
			mIV1.moveOneGrid();
			
		}
		mIV1.stateEndOfEachFrame();
		
		m1.setX(10);
		m1.setY(10);
		while(m1.getIsMoving()) {
			mIV1.moveOneGrid();
			
		}
		mIV1.stateEndOfEachFrame();
		
		m1.setX(480-2*40);
		m1.setY(10);
		while(m1.getIsMoving()) {
			mIV1.moveOneGrid();
			
		}
		mIV1.stateEndOfEachFrame();
		
		m1.setX(10);
		m1.setY(0);
		while(m1.getIsMoving()) {
			mIV1.moveOneGrid();
			
		}
		mIV1.stateEndOfEachFrame();
		
	}
	

}
