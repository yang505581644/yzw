package lunwen;

import java.util.Iterator;

public class Light implements Runnable{  
	int x;  
	int y;  
	boolean redLight;  
	boolean greenLight;
	boolean yellowLight;
	int direct;  
	//红绿灯切换  
	static int start=0; 
	int carNum;
	int t;
	static boolean pause = true;
	static int Y1;//tag first Yellow station;
	static int Y2;//tag second Yellow station; 
	public Light(){}   
	public Light(int x,int y,int direct){  
		this.x = x;    
		this.y = y;    
		this.direct = direct;   
	}  
	
	@Override  
	public void run() {  
    // TODO Auto-generated method stub 
//		start=0;
    	while (true) { 
    		//计算不同方向线内车辆数量
    		int CarDirNum[] = {0,0,0,0,0,0,0,0};
    		Iterator<CarDirect> iter = TrafficLight.pPanel.vtCar.iterator();
    		while (iter.hasNext()) {
    			CarDirect cd = iter.next();
    			switch (cd.direct) {
    			case 0:
    				//if(cd.x < 800) 
    				CarDirNum[0]++;
    				break;
    			case 1:
    				//if(cd.x < 800) 
    				CarDirNum[1]++;
    				break;
    			case 2:
    				//if(cd.y < 450) 
    				CarDirNum[2]++;
    				break;
    			case 3:
    				//if(cd.y < 450)
    				CarDirNum[3]++;
    				break;	
    			case 4:
    				//if(cd.x > 600) 
    				CarDirNum[4]++;
    				break;
    			case 5:
    				//if(cd.x > 600)
    				CarDirNum[5]++;
    				break;
    			case 6:
    				//if(cd.y > 250)
    				CarDirNum[6]++;
    				break;
    			case 7:
    				//if(cd.y > 250)
    				CarDirNum[7]++;
    				break;
    			}
    		}
    		int t = 0;
    		for(int i = 0;i < 8;i++){
    			t = t + CarDirNum[i];
    			
    		}
    		TrafficLight.CarStopSum = t;

    		Light light; 
    		if (start == 0){ 
    			int y = 0;
    			for (int i = 0; i < PaintPanel.vtLight.size(); i++){  
    				light = PaintPanel.vtLight.get(i);
    				if (light.direct % 4 == 0){ 	
    					light.greenLight = true;
    					light.yellowLight = false;
    					light.redLight = false; 
    					if(y == 0)
    						Y1 = i;
						if(y == 1)
							Y2 = i;
						y++;
    				} else { 
    					light.redLight = true;
    					light.greenLight = false; 
						light.yellowLight = false;	
    				} 
    			}
    			this.start = 1;   
    			if (CarDirNum[0]>CarDirNum[4]) {
    				t = CarDirNum[0]*1000;
    			} else {
    				t = CarDirNum[4]*1000;    			
    			}
    		} else if (start == 1) { 
    			int y = 0;
    			for (int i = 0; i < PaintPanel.vtLight.size(); i++) {  
    				light = PaintPanel.vtLight.get(i);    
    				if (light.direct % 4 == 1) { 
						light.greenLight = true;
						light.yellowLight = false;
						light.redLight = false; 
						if (y == 0)
    						Y1 = i;
						if (y == 1)
							Y2 = i;
						y++;
    				} else {  
    					light.redLight = true;
						light.greenLight = false;  
						light.yellowLight = false;	
					} 
    			}
    			this.start = 2;
      			if (CarDirNum[1]>CarDirNum[5]) {
      				t=CarDirNum[1]*1000;
      			} else {
      				t=CarDirNum[5]*1000;   
      			}
    		} else if (start == 2) {
    			int y = 0;
    			for (int i = 0; i < PaintPanel.vtLight.size(); i++) {  
    				light = PaintPanel.vtLight.get(i);  
    				if (light.direct % 4 == 2) { 
						light.greenLight = true; 
						light.yellowLight = false;
						light.redLight = false; 
						if (y == 0)
    						Y1 = i;
						if (y == 1)
							Y2 = i;
						y++;
    				} else {  
    					light.redLight = true;
						light.greenLight = false;  
						light.yellowLight = false;	
					} 
    			}
    			this.start = 3; 
      			if (CarDirNum[2]>CarDirNum[6]) {
      				t=CarDirNum[2]*1000;
      			} else {
    				t=CarDirNum[6]*1000;   
    			}
    		} else if (start == 3) {
    			int y = 0;
    			for (int i = 0; i < PaintPanel.vtLight.size(); i++) {  
    				light = PaintPanel.vtLight.get(i);  
    				if (light.direct % 4 == 3) { 
						light.greenLight = true; 
						light.yellowLight = false;
						light.redLight = false;
						if (y == 0)
    						Y1 = i;
						if (y == 1)
							Y2 = i;
						y++;
					} else {  
						light.redLight = true;
						light.greenLight = false;   
						light.yellowLight = false;	
					}
    			} 
    			this.start = 0;
      			if (CarDirNum[3]>CarDirNum[7]) {
      				t=CarDirNum[3]*1000;
      			} else {
    				t=CarDirNum[7]*1000;   
    			}
    		} 
    		try {  				
				Thread.sleep(t);
				light = PaintPanel.vtLight.get(Y1);
				light.yellowLight = true;
				light.greenLight = false;
				light = PaintPanel.vtLight.get(Y2);
				light.yellowLight = true;
				light.greenLight = false;
				Thread.sleep(2000);
				light.yellowLight = false;
			} catch (InterruptedException e) {  
				e.printStackTrace();  
			}   
    	}  
	}  
}  
