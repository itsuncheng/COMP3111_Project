package sample;

import java.lang.Math;
import java.util.ArrayList;

public class LaserTower extends BasicTower{
	
	private static int _attackPower = 5;
	private static int _range = 630;  
	private static int _build_cost = 7;
	private static int _upgrade_cost = 3;
	
	private static int shootCost = 2;
	public static String _imagePath = "./src/main/resources/laserTower.png";
	
	public LaserTower(int _x, int _y) {
		super(_x, _y, _attackPower, _range, _build_cost, _upgrade_cost, _imagePath);
	}
	
	public boolean isInRange(Monster m) {
		
		return true;//Math.abs(y - m.getY()) < 3 ;//* MyController.GRID_HEIGHT;			
	}
	
	public boolean isInRange(int _x, int _y) {
		
		return true;//Math.abs(y - _y) < 3 ;//* MyController.GRID_HEIGHT;			
	}
	
	public void shoot(Monster m,Arena a) {   //this is done by the following step 
		
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
					 a.monsters.get(i).setHp(a.monsters.get(i).getHp() - this.attackPower);
					 a.removeMoney(shootCost);
				 }
			 
			 }
		}
			
	}
	
	public String getTowerType() {
		return "Laser Tower";
	}
	
	public static int getUpgradeCost() {
		return upgrade_cost;
	}
}
