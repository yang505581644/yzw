package lunwen;


import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.*;

	public class TrafficLight extends JFrame implements ActionListener {
		static PaintPanel pPanel;
		static int CarSum = 0;
		static int CarRunSum = 0;
		static int CarStopSum=0;
		JMenuBar jmb;
		JMenu jm1, jm2;
		JMenuItem jmi1,jmi2,jmi3,jmi4,jmi5;

		public static void main(String[] args) {
			new TrafficLight();		
		}

		public TrafficLight() {
			jmb = new JMenuBar();
			jm1 = new JMenu("Imitate");
			jm2 = new JMenu("Set");
			jmi1 = new JMenuItem("start");	
			jmi2 = new JMenuItem("pause");
			jmi3 = new JMenuItem("continue");
			jmi4 = new JMenuItem("stop");
			jmi5 = new JMenuItem("设置");
			jm1.add(jmi1);
			jm1.add(jmi2);
			jm1.add(jmi3);
			jm1.add(jmi4);
			jm2.add(jmi5);
			jmi1.addActionListener(this);
			jmi2.addActionListener(this);
			jmi3.addActionListener(this);
			jmi4.addActionListener(this);
			jmi5.addActionListener(this);		
			jmb.add(jm1);
			jmb.add(jm2);
			this.setJMenuBar(jmb);
			
			

			this.setTitle("TrafficLight");
			this.setSize(1200, 900);
			this.setResizable(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == jmi1) { 
				pPanel = new PaintPanel(); 
				//light.direct = 0;
				this.add(pPanel);  
				this.setVisible(true);  
			} else if (e.getSource() == jmi2) {  
				Car.pause = false;  
				pPanel.pause = false; 
				Light.start = 0;
				Light.pause = false;
			} else if (e.getSource() == jmi3) {  
				Car.pause = true;  
				pPanel.pause = true; 
				Light.start = 0;
				Light.pause = true;
			} else if (e.getSource() == jmi4) {			
				 System.exit(0);			
			} else if (e.getSource() == jmi5) {
				new Set(this, "设置", true);
			}
		}
	}
 	  
	class PaintPanel extends JPanel implements Runnable {
		static CopyOnWriteArrayList<CarDirect> vtCar = new CopyOnWriteArrayList<CarDirect>();
		static CopyOnWriteArrayList<Light> vtLight = new CopyOnWriteArrayList<Light>();
		Trouble tcar = null;
		Random r = new Random();
		boolean pause = true;
		static int carNum = 20;
		boolean creatMyCar = true;
		static int tCarSum = 0;
		public PaintPanel() {
			init();
			vtLight.add(new Light(580, 250, 0));  //E-W
			vtLight.add(new Light(580, 307, 1));  //E-S
			vtLight.add(new Light(757, 230, 2));  //S-N
			vtLight.add(new Light(702, 230, 3));  //S-W
			vtLight.add(new Light(799, 407, 4));  //W-E
			vtLight.add(new Light(799, 352, 5));  //W-N
			vtLight.add(new Light(600, 449, 6));  //N-S
			vtLight.add(new Light(657, 449, 7));  //N-E
			Thread t = new Thread(new Light());
			t.start();
			// 启动生成对象车线程
			new Thread(this).start();
		}
  
		// 生成肇事车
		public void init() {		
			switch (r.nextInt(8)) {
			case 0:
				tcar = new Trouble(1080, 279, 0);
				break;
			case 1:
				tcar = new Trouble(1080, 336, 1);
				break;
			case 2:
				tcar = new Trouble(760, 655, 2);
				break;
			case 3:
				tcar = new Trouble(705, 655, 3);
				break;
			case 4:
				tcar = new Trouble(310, 410, 4);
				break;
			case 5:
				tcar = new Trouble(310, 355, 5);
				break;
			case 6:
				tcar = new Trouble(629, 35, 6);
				break;
			case 7:
				tcar = new Trouble(686, 35, 7);
				break;
			}
			new Thread(tcar).start();
		}

		public void paint(Graphics g) {
			super.paint(g);
			// 画十字车道
			g.setColor(Color.lightGray);
			g.fillRect(300, 250, 800, 200);
			g.fillRect(600, 25, 200, 650);		
			g.setColor(Color.white);
			g.fillRect(300, 350, 800, 2);
			g.fillRect(700, 25, 2, 650);
			
			g.setColor(Color.white);
			g.fillRect(310, 300, 40, 1);
			g.fillRect(370, 300, 50, 1);
			g.fillRect(440, 300, 50, 1);
			g.fillRect(510, 300, 50, 1);
			g.fillRect(580, 300, 50, 1);
			g.fillRect(650, 300, 50, 1);
			g.fillRect(720, 300, 50, 1);
			g.fillRect(790, 300, 50, 1);
			g.fillRect(860, 300, 50, 1);
			g.fillRect(930, 300, 50, 1);
			g.fillRect(1000, 300, 50, 1);
			g.fillRect(1070, 300, 25, 1);
			
			g.fillRect(310, 400, 40, 1);
			g.fillRect(370, 400, 50, 1);
			g.fillRect(440, 400, 50, 1);
			g.fillRect(510, 400, 50, 1);
			g.fillRect(580, 400, 50, 1);
			g.fillRect(650, 400, 50, 1);
			g.fillRect(720, 400, 50, 1);
			g.fillRect(790, 400, 50, 1);
			g.fillRect(860, 400, 50, 1);
			g.fillRect(930, 400, 50, 1);
			g.fillRect(1000, 400, 50, 1);
			g.fillRect(1070, 400, 25, 1);
			
			g.fillRect(650, 35, 1, 40);
			g.fillRect(650, 95, 1, 50);
			g.fillRect(650, 165, 1, 50);
			g.fillRect(650, 235, 1, 50);
			g.fillRect(650, 305, 1, 50);
			g.fillRect(650, 375, 1, 50);
			g.fillRect(650, 445, 1, 50);
			g.fillRect(650, 515, 1, 50);
			g.fillRect(650, 585, 1, 50);
			g.fillRect(650, 655, 1, 30);
			
			g.fillRect(750, 35, 1, 40);
			g.fillRect(750, 95, 1, 50);
			g.fillRect(750, 165, 1, 50);
			g.fillRect(750, 235, 1, 50);
			g.fillRect(750, 305, 1, 50);
			g.fillRect(750, 375, 1, 50);
			g.fillRect(750, 445, 1, 50);
			g.fillRect(750, 515, 1, 50);
			g.fillRect(750, 585, 1, 50);
			g.fillRect(750, 655, 1, 30);
			
			//绘制灯
			g.setColor(Color.black);
			//E-W
			g.drawRect(579, 250, 21, 42);
			
			//E-S
			g.drawRect(579, 307, 21, 42);
			
			//S-N
			g.drawRect(757, 229, 42, 21);
			
			//S-W
			g.drawRect(702, 229, 42, 21);
			
			//W-E
			g.drawRect(798, 407, 21, 42);
			
			//W-N
			g.drawRect(798, 352, 21, 42);
			
		    //N-S
			g.drawRect(600, 448, 42, 21);
		
			//N-E
			g.drawRect(657, 448, 42, 21);
			
			for (int i = 0; i < vtLight.size(); i++) {  
				Light light = vtLight.get(i);  
				g.setColor(Color.white);  
				if (light.direct == 2||light.direct == 3
						||light.direct == 6||light.direct == 7) {  
					g.fillRect(light.x, light.y, 42, 21);  
				} else if(light.direct == 0||light.direct == 1
						||light.direct == 4||light.direct == 5){  
					g.fillRect(light.x, light.y, 21, 42);  
				}  
			//绿灯
				g.setColor(Color.green);  
				if (light.greenLight == true) {  
					if (light.direct == 2||light.direct == 3) {  
						g.fillOval(light.x + 29, light.y + 5, 10, 10); 
					} else if(light.direct == 6||light.direct ==7){  
						g.fillOval(light.x + 3, light.y + 5, 10, 10);  
					}  else if(light.direct == 0||light.direct == 1){  
						g.fillOval(light.x + 5, light.y + 3, 10, 10);
					}  else if(light.direct == 4||light.direct == 5){  
						g.fillOval(light.x + 5, light.y + 29, 10, 10);
					}
				}  
				// 黄灯
				g.setColor(Color.yellow);  
				if (light.yellowLight == true) {  
					if (light.direct == 2||light.direct == 3) {  
						g.fillOval(light.x + 16, light.y + 5, 10, 10); 
					} else if(light.direct == 6||light.direct == 7){  
						g.fillOval(light.x + 16, light.y + 5, 10, 10);  
					} else if(light.direct == 0||light.direct == 1){  
						g.fillOval(light.x + 5, light.y + 16, 10, 10);
					}  else if(light.direct == 4||light.direct == 5){  
						g.fillOval(light.x + 5, light.y + 16, 10, 10); 
					}
				}
				// 红灯  
				g.setColor(Color.red);
				if (light.redLight == true) {  
					if (light.direct == 2||light.direct == 3) {  
						g.fillOval(light.x + 3, light.y + 5, 10, 10); 
					} else if(light.direct == 6||light.direct == 7){  
						g.fillOval(light.x + 29, light.y + 5, 10, 10);  
					} else if(light.direct ==0||light.direct == 1){  
						g.fillOval(light.x + 5, light.y + 29, 10, 10);
					} else if(light.direct == 4||light.direct == 5){  
						g.fillOval(light.x + 5, light.y + 3, 10, 10); 
					}
				}          
			}	
			// 画车
			Iterator<CarDirect> iter = TrafficLight.pPanel.vtCar.iterator();
			while (iter.hasNext()) {
				CarDirect cd = iter.next();
				g.setColor(cd.colorCar);
				g.fill3DRect(cd.x, cd.y, 10, 10, true);
			}
			// 画肇事车
			g.setColor(Color.red);
			g.fill3DRect(tcar.x, tcar.y, 10, 10, true);
			// 画肇事文字
			if (tcar.text != null) {
				g.setFont(new Font("微软雅黑", Font.BOLD, 20));
				g.drawString(tcar.text.info, tcar.text.x, tcar.text.y);
			}
			//统计通行量
			g.setFont(new Font("微软雅黑", Font.BOLD, 20));
			g.drawString("总车数："+tCarSum, 100, 150);
			//g.drawString("停车数："+TrafficLight.CarStopSum,100,200);
			//g.drawString("总通行量："+(TrafficLight.CarSum-TrafficLight.CarStopSum), 100, 250);
		}

		// 到十字路口要走的方向
		public int Direct(int d) {
			int i = r.nextInt(8);
			while (true) {
				if (i == d) {
					i = r.nextInt(8);
				} else {
					break;
				}
			}
			return i;
		}

		@Override
		public void run() {
			CarDirect cd = null;
			//int newDirect;
			// TODO Auto-generated method stub
			while (true) {
				if (pause) {
					if (vtCar.size() < carNum) {
						if (this.creatMyCar) {
							switch (r.nextInt(8)) { 
							case 0:  
								//newDirect = Direct(0);  
								cd = new CarDirect(1080, 266, 0, Color.orange);  
								vtCar.add(cd); 
								tCarSum++; 
						 		break;  
						 	case 1:  
						 		//newDirect = Direct(1);  
						 		cd = new CarDirect(1080, 323, 1, Color.orange);  
						 		vtCar.add(cd);  
						 		tCarSum++; 
						 		break;  
						 	case 2:  
						 		//newDirect = Direct(2);  
						 		cd = new CarDirect(773, 655, 2, Color.orange);  
						 		vtCar.add(cd);  
						 		tCarSum++; 
						 		break;  
						 	case 3:  
						 		//newDirect = Direct(3);  
						 		cd = new CarDirect(718, 655, 3, Color.orange);  
						 		vtCar.add(cd);  
						 		tCarSum++; 
						 		break;  
						 	case 4:  
						 		//newDirect = Direct(4);  
						 		cd = new CarDirect(310, 423, 4, Color.orange);  
						 		vtCar.add(cd);  
						 		tCarSum++; 
						 		break; 
						 	case 5:  
						 		//newDirect = Direct(5);  
						 		cd = new CarDirect(310, 368, 5, Color.orange);  
						 		vtCar.add(cd);  
						 		tCarSum++; 
						 		break; 
						 	case 6:  
						 		//newDirect = Direct(6);  
						 		cd = new CarDirect(616, 35, 6, Color.orange);  
						 		vtCar.add(cd); 
						 		tCarSum++;  
						 		break; 
						 	case 7:  
						 		//newDirect = Direct(7);  
						 		cd = new CarDirect(673, 35, 7, Color.orange);  
						 		vtCar.add(cd);  
						 		tCarSum++; 
						 		break; 
						 }  
						 new Thread(cd).start();  
					 }  
				 }  
					 TrafficLight.CarSum = tCarSum;
			 }
				
			 try {  
				 Thread.sleep(500); 

			 } catch (InterruptedException e) {  	
				 e.printStackTrace();  
				
		 	 }  
		 }  
	 } 
}