import java.awt.Color;
import java.util.Scanner;
import java.math.*;

public class DuckHunting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EZ.initialize(1550,800); 
		EZText title = EZ.addText(EZ.getWindowWidth()/2, EZ.getWindowHeight() / 2, "Duck Hunting", Color.BLACK, 70);
		EZText score = EZ.addText(EZ.getWindowWidth() - 400, EZ.getWindowHeight() /8, "SCORE:", Color.BLACK, 50);
		EZText number = EZ.addText(EZ.getWindowWidth() - 200, EZ.getWindowHeight() /8, "0", Color.BLACK, 50);
		EZText time = EZ.addText(EZ.getWindowWidth() - 400, EZ.getWindowHeight() /5, "TIME:", Color.BLACK, 30);
		EZText actualTime = EZ.addText(EZ.getWindowWidth() - 200, EZ.getWindowHeight() /5, "20.00", Color.BLACK, 30);
		//title.setFont("Courier New");
		//score.setFont("Courier New");
		number.setFont("Courier New");
		//time.setFont("Courier New");
		actualTime.setFont("Courier New");
		Scanner s = new Scanner(System.in);
		//System.out.println(rect.getHeight());

		
		
		EZRectangle [] recArrayTopRow;
		recArrayTopRow = makeRectangles(8, 300, Color.RED);
		EZRectangle [] recArrayBottomRow;;
		recArrayBottomRow = makeRectangles(8, 550, Color.BLUE);
		
		
		
		while(!EZInteraction.wasKeyPressed('q')) {
			System.out.println("q");
			EZText start = EZ.addText(EZ.getWindowWidth()/2, EZ.getWindowHeight() - 300, "PRESS [S] to START", Color.BLACK, 30);
			EZText quit = EZ.addText(EZ.getWindowWidth()/2, EZ.getWindowHeight() - 100, "PRESS [B] to QUIT", Color.BLACK, 20);
			start.setFont("Courier New");
			quit.setFont("Courier New");
			start.show();
			try {
			    Thread.sleep(400);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			start.hide();
			try {
			    Thread.sleep(400);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			
			if(EZInteraction.wasKeyPressed('s')) {
				start.hide();
				showRectangles(recArrayTopRow);
				showRectangles(recArrayBottomRow);
				System.out.println("sssssssssssss");
				number.setMsg("0");
				actualTime.setMsg("20.00");
				while(!EZInteraction.wasKeyPressed('b') && Double.parseDouble(actualTime.getMsg()) > 0) {
					
					title.hide();
					try {
					    Thread.sleep(1);
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					}
					timer(actualTime);
					
					if(EZInteraction.wasMouseLeftButtonPressed()) {
						checkClick(recArrayTopRow, number); 
						checkClick(recArrayBottomRow, number);
					}
						
					checkAndMove(recArrayTopRow, 0);
					checkAndMove(recArrayBottomRow, 1);
					
					randomShowRectangles(recArrayTopRow);
					randomShowRectangles(recArrayBottomRow);
					
					EZ.refreshScreen();
				}//End of game loop
				System.out.println("b was pressed");
				title.show();
				hideRectangles(recArrayTopRow);
				hideRectangles(recArrayBottomRow);
				//EZ.refreshScreen();
			}//End of Condition for game loop
		}//End of home loop
		title.hide();
		EZ.addText(EZ.getWindowWidth()/2, EZ.getWindowHeight() / 2, "Good Bye", Color.BLACK, 70);
		s.close();
	}
	
	
	
	 
	public static void checkClick(EZRectangle [] rArray, EZText number) {
		for (int i = 0; i < rArray.length; i++) {
			System.out.println(rArray[i].color);
			if(EZInteraction.getXMouse() > rArray[i].getXCenter() - (rArray[i].getWidth()/2) &&
				EZInteraction.getXMouse() < rArray[i].getXCenter() + (rArray[i].getWidth()/2) &&
				EZInteraction.getYMouse() > rArray[i].getYCenter() - (rArray[i].getHeight()/2)&&
				EZInteraction.getYMouse() < rArray[i].getYCenter() + (rArray[i].getHeight()/2)) {
				
				rArray[i].hide();
				
				int n = Integer.parseInt(number.getMsg());
				n = n + 10;
				number.setMsg(Integer.toString(n));
			}
		}
	}//end of checkClick
	
	
	
	
	public static void checkAndMove(EZRectangle [] rArray, int d) {
		boolean l = false;	
		boolean r = false;
		if (d == 0) {
			for (int i = 0; i < rArray.length; i++) {
				if(rArray[i].getXCenter() + (rArray[i].getWidth() / 2) < 0) {
					l = true;
				}
				else {
					l = false;
				}
				if(l == false) {//when check returns false
					rArray[i].translateBy(-5, 0);
				}
				else if(l == true) {//when check returns true
					rArray[i].translateTo(EZ.getWindowWidth(), rArray[i].getYCenter());
				}
			}
		}
		if (d == 1) {
			for (int i = 0; i < rArray.length; i++) {
				if(rArray[i].getXCenter() - (rArray[i].getWidth() / 2) > EZ.getWindowWidth()) {
					r = true;
				}
				else {
					r = false;
				}
				if(r == false) {//when check returns false
					rArray[i].translateBy(5, 0);
				}
				else if(r == true) {//when check returns true
					rArray[i].translateTo(0, rArray[i].getYCenter());
				}
			}
		}
	}//end of checkAndMove
	
	
	
	public static EZRectangle[] makeRectangles (int number, int yCenter, Color c) {
		EZRectangle [] rA = new EZRectangle [number];
		for (int i = 0; i < number; i++) {
			rA[i] = EZ.addRectangle(i * 200, yCenter, 100, 100, c, true);
			try {
			    Thread.sleep(75);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			rA[i].hide();
		}
		return rA;
	}
	
	
	
	public static void hideRectangles(EZRectangle [] rArray) {
		for (int i = 0; i < rArray.length; i++) {
			rArray[i].hide();
		}
	}
	public static void showRectangles(EZRectangle [] rArray) {
		for (int i = 0; i < rArray.length; i++) {
			rArray[i].show();
		}
	}
	public static void randomShowRectangles(EZRectangle [] rArray) {
		for (int i = 0; i < rArray.length; i++) {
			if(Math.random() < 0.002) {
				rArray[i].show();
			}
		}
	}
	public static void timer(EZText t) {
		double temp = Double.parseDouble(t.getMsg());
		temp = temp - 0.02;
		temp = Math.round(temp * 100);
		temp = temp/100;
		t.setMsg(Double.toString(temp));
	}
	
	
	
}//end of class
