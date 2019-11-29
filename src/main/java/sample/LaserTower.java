package sample;

import java.lang.Math;
import java.util.ArrayList;

public class LaserTower extends BasicTower{
	
	private static int _attackPower = 5;
	private static int _range = 630;  
	private static int _build_cost = 7;
	private static int _upgrade_cost = 3;
	
	private int shootCost;
	public static String _imagePath = "./src/main/resources/laserTower.png";
	
	public LaserTower(int _x, int _y) {
		super(_x, _y, _attackPower, _range, _build_cost, _upgrade_cost, _imagePath);
		shootCost = 2;
	}
	
	public boolean isInRange(Monster m) {
		
		return true;//Math.abs(y - m.getY()) < 3 ;//* MyController.GRID_HEIGHT;			
	}
	
	public boolean isInRange(int _x, int _y) {
		
		return true;//Math.abs(y - _y) < 3 ;//* MyController.GRID_HEIGHT;			
	}
	
	public void shoot(Monster m,Arena a) {   //this is done by the following step 
		
		if (isShot != true) {
			double currentX = m.getX();
			double currentY = m.getY();
			
			double slope = ((double)y - currentY)/((double)x - currentX); //1)finding the slope between tower and the current monster m
			
			for (int i=0; i<a.monsters.size(); ++i) {
				 double targetX = a.monsters.get(i).getX();  //2)getting x and y of each monsters in the list 
				 double targetY = a.monsters.get(i).getY();  
				 
				//3)using slope properties,getting x and y coordinate which form the shortest distance between target monster and the line drawn by tower and current monster
				 double tempX = (slope*targetY + slope*slope*x - slope*y + targetX)/(1 + slope*slope); 
				 double tempY = slope*(tempX-x) + y;
				 
				 double distance = Math.sqrt(Math.pow(targetX - tempX, 2)+Math.pow(targetY - tempY, 2));
				 
				 if (distance <= 3) {
					 boolean xIsInRange;
					 boolean yIsInRange;
				 
					 if (currentX >= x)
						 xIsInRange = targetX >= x;
					 else 
						 xIsInRange = targetX < x;
				 
					 if (currentY >= y)
						 yIsInRange = targetY >= y;
					 else 
						 yIsInRange = targetY < y;
				 
					 if (xIsInRange && yIsInRange ) {
						 if (a.getMoney() >= shootCost) {
							 a.removeMoney(shootCost);
							 a.monsters.get(i).setHp(a.monsters.get(i).getHp() - this.attackPower);
						 }
					 }
				 
				 }
			}
		}
	}
	
	public void upgrade() {
		attackPower += 1;
		shootCost = (int) (attackPower/_attackPower);
	}
	
	public String getTowerType() {
		return "Laser Tower";
	}

	
	public void setShootCost(int _shootCost) {
		shootCost = _shootCost;
	}
	
	public int getShootCost() {
		return shootCost;
	}
	
	public static int getDefaultBuildCost() {
		return _build_cost;
	}
	
	
	public static int getDefaultUpgradeCost() {
		return _upgrade_cost;
	}
}
