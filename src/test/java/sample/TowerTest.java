package sample;

import static org.junit.Assert.*;

import org.junit.Test;

public class TowerTest {

	@Test
	public void test() {
		
		BasicTower basic = new BasicTower(360,0);
		assertEquals(basic.getAttackPower(), 3);
		assertEquals(basic.getRange(), 65);
		assertEquals(basic.getTowerType(), "Basic Tower");
		assertEquals(basic.getX(), 360);
		assertEquals(basic.getY(), 0);
		assertEquals(basic.getBuildCost(), 2);
		assertEquals(basic.getUpgradeCost(), 1);
		
		BasicTower ice = new IceTower(360, 0);
		assertEquals(ice.getAttackPower(), 1);
		assertEquals(ice.getRange(), 65);
		assertEquals(ice.getTowerType(), "Ice Tower");
		assertEquals(ice.getX(), 360);
		assertEquals(ice.getY(), 0);
		assertEquals(ice.getBuildCost(), 4);
		assertEquals(ice.getUpgradeCost(), 5);

		BasicTower catapult = new Catapult(360, 0);
		assertEquals(catapult.getAttackPower(), 5);
		assertEquals(catapult.getRange(), 150);
		assertEquals(catapult.getTowerType(), "Catapult");
		assertEquals(catapult.getX(), 360);
		assertEquals(catapult.getY(), 0);
		assertEquals(catapult.getBuildCost(), 5);
		assertEquals(catapult.getUpgradeCost(), 3);
		assertEquals(((Catapult)catapult).getLowRange(), 50);
		
		BasicTower laser = new LaserTower(360,0);
		assertEquals(laser.getAttackPower(), 5);
		assertEquals(laser.getRange(), 630);
		assertEquals(laser.getTowerType(), "Laser Tower");
		assertEquals(laser.getX(), 360);
		assertEquals(laser.getY(), 0);
		assertEquals(laser.getBuildCost(), 7);
		assertEquals(laser.getUpgradeCost(), 3);
		
		basic.upgrade();
		ice.upgrade();
		catapult.upgrade();
		laser.upgrade();
	}
	
	@Test
	public void basicTowerTest() {
		
		boolean [][] isGreen = new boolean [12][12];
		Arena arena = new Arena(12, 12, isGreen);
		
		Monster fox = new Fox();
		arena.addMonster(fox);
		
		BasicTower basic = new BasicTower(40,0);
		basic.isInRange(fox);
		basic.isInRange(fox.getX(), fox.getY());
		basic.shoot(fox, arena);
		
		fox.setY(440);
		basic.isInRange(fox);
		basic.isInRange(fox.getX(), fox.getY());
	}
	
	@Test
	public void iceTowerTest() {
		
		boolean [][] isGreen = new boolean [12][12];
		Arena arena = new Arena(12, 12, isGreen);
		
		Monster fox = new Fox();
		arena.addMonster(fox);
		
		BasicTower ice = new IceTower(40,0);
		ice.shoot(fox, arena);
		ice.shoot(fox, arena);
				
	}
	
	@Test
	public void catapultTest() {
		
		boolean [][] isGreen = new boolean [12][12];
		Arena arena = new Arena(12, 12, isGreen);
		
		Monster fox = new Fox();
		Monster penguin = new Penguin();
		arena.addMonster(fox);
		arena.addMonster(penguin);
		
		BasicTower catapult = new Catapult(360,0);
		
		catapult.isInRange(fox);
		catapult.isInRange(fox.getX(), fox.getY());
		
		fox.setX(320);
		catapult.isInRange(fox);
		catapult.isInRange(fox.getX(), fox.getY());
		
		fox.setX(280);
		catapult.isInRange(fox);
		catapult.isInRange(fox.getX(), fox.getY());
		
		catapult.shoot(fox, arena);
		catapult.shoot(fox, arena);
		
		Catapult negative = new Catapult(360, 0, 5, 150, 5, 3, 50, -1, 0, "");
		negative.upgrade();
	}
	
	@Test
	public void laserTowerTest() {
		
		boolean [][] isGreen = new boolean [12][12];
		Arena arena = new Arena(12, 12, isGreen);
		
		Monster fox = new Fox();
		Monster penguin = new Penguin();
		Monster unicorn = new Unicorn();
		arena.addMonster(fox);
		arena.addMonster(penguin);
		arena.addMonster(unicorn);
		
		LaserTower laser = new LaserTower(280,240);
		
		laser.isInRange(fox);
		laser.isInRange(fox.getX(), fox.getY());
		
		fox.setX(320);
		fox.setY(280);
		penguin.setX(240);
		penguin.setY(200);
		laser.shoot(fox, arena);
		laser.shoot(penguin, arena);
		
		fox.setX(240);
		fox.setY(280);
		penguin.setX(320);
		penguin.setY(200);
		laser.shoot(fox, arena);
		laser.shoot(penguin, arena);
				
	}
	
	@Test 
	public void towerImageViewTest() {
		
		BasicTower basic = new BasicTower(360,0);
		TowerImageView basicTIV = new TowerImageView(basic);
		BasicTower test = basicTIV.getTower();
		
		BasicTower ice = new IceTower(360, 40);
		ice.imageView = null;
		TowerImageView iceTIV = new TowerImageView(ice);
		
	}
	
}
