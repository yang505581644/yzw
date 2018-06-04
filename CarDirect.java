package lunwen;
import java.awt.Color;
import java.util.Iterator;

public class CarDirect extends Car implements Runnable {
	
	Color colorCar;
	public boolean exit = true;

	public CarDirect(int x, int y, int direct, Color colorCar) {
		super(x, y, direct);
		this.colorCar = colorCar;
	}

	// 直行/左转
	public void run() {
		while (exit) {
			if (pause) {
				switch (direct) {
				case 0:
					if (stopCar) {
						x -= speed;
					}
					panDuan(x - 10, y, direct);
					break;
				case 1:
					if (stopCar) {
						if(x >= 673)
						x -= speed;
					else
						y += speed;
					}
					panDuan(x - 10, y, direct);
					break;
				case 2:
					if (stopCar) {
						y -= speed;
					}
					panDuan(x, y - 10, direct);
					break;
				case 3:
					if (stopCar) {
						if(y >= 323)
						y -= speed;
					else
						x -= speed;
					}
					panDuan(x, y - 10, direct);
					break;
				case 4:
					if (stopCar) {
						x += speed;
					}
					panDuan(x + 20, y, direct);
					break;
				case 5:
					if (stopCar) {
						if(x <= 718)
						x += speed;
					else
						y -= speed;
					}
					panDuan(x + 20, y, direct);
					break;
				case 6:
					if (stopCar) {
						y += speed;
					}
					panDuan(x, y + 20, direct);
					break;
				case 7:
					if (stopCar) {
						if(y <= 368)
						y += speed;
					else
						x += speed;
					}
					panDuan(x, y + 20, direct);
					break;
				}
			}
			// 碰到边界，删除
			if (x < 300 || x > 1100 || y < 25 || y > 675) {
				TrafficLight.pPanel.vtCar.remove(this);
				this.exit = false;// 如果死亡，退出线程
				this.isStop=false;
			}
			TrafficLight.pPanel.repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	} 

	public void panDuan(int x, int y, int direct) {
		// 车距
		Iterator<CarDirect> iter = TrafficLight.pPanel.vtCar.iterator();
		while (iter.hasNext()) {
			CarDirect cd = iter.next();
			if (x >= cd.x && x <= cd.x + 15 && y >= cd.y && y <= cd.y + 15) {
				if (cd.stopCar == false) {
					stopCar = false;
				} else {
					stopCar = true;
				}
			}
			if(this.isStop==false){
				TrafficLight.pPanel.vtCar.remove(cd);
			}
		}
			
		// 是否碰到灯
		for (int i = 0; i < TrafficLight.pPanel.vtLight.size(); i++) {
			Light light = TrafficLight.pPanel.vtLight.get(i);
			if (direct == 0 || direct == 1) {
				if (x >= 580 && x <= 800 && y >= light.y && y <= light.y + 50) {
					if (light.direct == direct) {
						if (light.redLight == true || light.yellowLight == true) {
							if(x < 800){
								stopCar = true;
							}else{
								stopCar = false;
							}
						} else {
							stopCar = true;
							Iterator<CarDirect> iter1 = TrafficLight.pPanel.vtCar.iterator();
							while (iter1.hasNext()) {
								CarDirect cd = iter1.next();
								if (light.direct == cd.direct) {
									cd.stopCar = true;
								}
							}
						}
					}
				}
			} else if (direct == 2 || direct == 3) {
				if (x >= light.x && x <= light.x + 50 && y >= 230 && y <= 450) {
					if (light.direct == direct) {
						if (light.redLight == true || light.yellowLight == true) {
							if(y < 450){
								stopCar = true;
							}else{
								stopCar = false;
							}
						} else {
							stopCar = true;
							Iterator<CarDirect> iter2 = TrafficLight.pPanel.vtCar.iterator();
							while (iter2.hasNext()) {
								CarDirect cd = iter2.next();
								if (light.direct == cd.direct) {
									cd.stopCar = true;
								}
							}
						}
					}
				}
			} else if (direct == 4 || direct == 5) {
				if (x >= 600 && x <= 620 && y >= light.y && y <= light.y + 50) {
					if (light.direct == direct) {
						if (light.redLight == true || light.yellowLight == true) {
							if(x > 600){
								stopCar = true;
							}else{
								stopCar = false;
							}
						} else {
							stopCar = true;
							Iterator<CarDirect> iter2 = TrafficLight.pPanel.vtCar.iterator();
							while (iter2.hasNext()) {
								CarDirect cd = iter2.next();
								if (light.direct == cd.direct) {
									cd.stopCar = true;
								}
							}
						}
					}
				}
			} else if (direct == 6 || direct == 7) {
				if (x >= light.x && x <= light.x + 50 && y >= 250 && y <= 270) {
					if (light.direct == direct) {
						if (light.redLight == true || light.yellowLight == true) {
							if (y > 250) {
								stopCar = true;
							}else{
								stopCar = false;
							}
						} else {
							stopCar = true;
							Iterator<CarDirect> iter2 = TrafficLight.pPanel.vtCar.iterator();
							while (iter2.hasNext()) {
								CarDirect cd = iter2.next();
								if (light.direct == cd.direct) {
									cd.stopCar = true;
								}
							}
						}
					}
				}
			}
		}
	}
}