package lunwen;

import java.util.Iterator;

public class Trouble extends Car implements Runnable{  
	Text text;  
	public Trouble(int x, int y, int direct) {  
    super(x, y, direct);  
    // TODO Auto-generated constructor stub  
	}  
	public void judge(int x,int y,int direct){  
		// 碰到边界，从边缘删除  
		if (x < 300 || x > 1100 || y < 25 || y > 675) {  
			this.isStop = false;  
			TrafficLight.pPanel.init();  
		}
		
		//是否碰到灯     
		for (int i = 0; i < TrafficLight.pPanel.vtLight.size(); i++){    
			Light light = TrafficLight.pPanel.vtLight.get(i); 
			if (direct == 0 || direct == 1) {
				if (x >= 580 && x <= 800 && y >= light.y && y <= light.y + 50) {
					if (light.direct == direct) {
						if (light.redLight == true || light.yellowLight == true) {
							text = new Text(100, 100, "出现违章车辆");  
							new Thread(text).start();
						}
					}
				}
			} else if (direct == 2 || direct == 3) {
				if (x >= light.x && x <= light.x + 50 && y >= 230 && y <= 450) {
					if (light.direct == direct) {
						if (light.redLight == true || light.yellowLight == true) {
							text = new Text(100, 100, "出现违章车辆");  
							new Thread(text).start();
						}
					}
				}
			} else if (direct == 4 || direct == 5) {
				if (x >= 600 && x <= 620 && y >= light.y && y <= light.y + 50) {
					if (light.direct == direct) {
						if (light.redLight == true || light.yellowLight == true) {
							text = new Text(100, 100, "出现违章车辆");  
							new Thread(text).start();
						}
					}
				}
			} else if (direct == 6 || direct == 7) {
				if (x >= light.x && x <= light.x + 50 && y >= 250 && y <= 270) {
					if (light.direct == direct) {
						if (light.redLight == true || light.yellowLight == true) {			
							text = new Text(100, 100, "出现违章车辆");  
							new Thread(text).start();
						}
					}
				}
			}
		}
	}	    
		
	@Override  
	public void run() {  
		// TODO Auto-generated method stub  
    	while (true){  
    		if (pause){  
    			switch (direct) {    
    			case 0:
					if (stopCar) {
						x -= speed;
					}
					judge(x - 10, y, direct);
					break;
				case 1:
					if (stopCar) {
						if(x >= 686)
						x -= speed;
					else
						y += speed;
					}
					judge(x - 10, y, direct);
					break;
				case 2:
					if (stopCar) {
						y -= speed;
					}
					judge(x, y - 10, direct);
					break;
				case 3:
					if (stopCar) {
						if(y >= 336)
						y -= speed;
					else
						x -= speed;
					}
					judge(x, y - 10, direct);
					break;
				case 4:
					if (stopCar) {
						x += speed;
					}
					judge(x + 20, y, direct);
					break;
				case 5:
					if (stopCar) {
						if(x <= 705)
						x += speed;
					else
						y -= speed;
					}
					judge(x + 20, y, direct);
					break;
				case 6:
					if (stopCar) {
						y += speed;
					}
					judge(x, y + 20, direct);
					break;
				case 7:
					if (stopCar) {
						if(y <= 355)
						y += speed;
					else
						x += speed;
					}
					judge(x, y + 20, direct);
					break;
				}
    		}  
        //如果死亡，退出线程  
    		if (isStop==false) {  
    			break;  
    		} 
    		try {  
    		Thread.sleep(50);  
    		} catch (InterruptedException e) {  
    			e.printStackTrace();  
    		}  
    	}  
	}  
}  

