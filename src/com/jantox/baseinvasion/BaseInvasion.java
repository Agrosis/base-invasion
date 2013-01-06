package com.jantox.baseinvasion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Collections;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BaseInvasion extends JPanel implements Runnable, KeyListener, MouseMotionListener, MouseListener {
	
	private Thread thread;
	
	public boolean running = true;
	public boolean paused = false;
	
	boolean up, down, left, right;
	
	protected Area area;
	
	Building hold;
	
	Entity select = null;

	public BaseInvasion(int x, int y) {
		this.setSize(x, y);
		this.setBackground(new Color(76, 181, 66));
		this.addKeyListener(this);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		this.setFocusable(true);
		
		Images.loadImages();
		
		area = new Area();
		
		hold = new LumberPost(0,0);
	}
	
	public void addNotify() {
        super.addNotify();
        thread = new Thread(this);
        thread.start();
    }

	@Override
	public void run() {
		long pretime, dif, sleep;
		pretime = System.currentTimeMillis();
		
		while(running) {
			this.tick();
			this.repaint();
			//System.out.println(pretime);
			
			dif = System.currentTimeMillis() - pretime;
			sleep = 15 - dif;
			
			if(sleep < 0) {
				sleep = 1;
			}
			
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			pretime = System.currentTimeMillis();
		}
	}
	
	public void tick() {
		Collections.sort(area.entities, new EntityComparator());
		for(int i = 0; i < area.entities.size(); i++) {
			area.entities.get(i).tick();
			if(area.entities.get(i) instanceof Incomplete) {
				if(((Incomplete)area.entities.get(i)).progress >= 4000) {
					area.entities.add(new LumberPost(area.entities.get(i).x,area.entities.get(i).y));
					area.entities.remove(i);
				}
			}
		}
		//hold.tick();
		if(left) {
			View.x-=3;
		} else if(right) {
			View.x+=3;
		}
		if(up) {
			View.y-=3;
		} else if(down) {
			View.y+=3;
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		for(int i = 0; i < area.entities.size(); i++) {
			area.entities.get(i).render(g);
		}
		if(area.isEmpty(hold))
			hold.render(g);
		else
			g.drawImage(Images.cross, (int)hold.x - 16 - View.x, (int)hold.y - 16 - View.y, null);
		
		g.setColor(new Color(192, 192, 192));
		g.fillRect(0,0,800, 30);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Sans-Serif", Font.PLAIN, 14));
		
		g.drawImage(Images.logs, 700, -2, null);
		g.drawString(String.valueOf(Resources.wood), 740, 20);
		
		g.drawImage(Images.slave, 610, 7, null);
		g.drawString(String.valueOf(Resources.population) + " / " + String.valueOf(Resources.maxpop), 645, 20);
		
		g.dispose();
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		if(ke.getKeyCode() == KeyEvent.VK_LEFT){
			left = true;
		} else if(ke.getKeyCode() == KeyEvent.VK_RIGHT){
			right = true;
		}
		if(ke.getKeyCode() == KeyEvent.VK_UP){
			up = true;
		} else if(ke.getKeyCode() == KeyEvent.VK_DOWN){
			down = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		if(ke.getKeyCode() == KeyEvent.VK_LEFT){
			left = false;
		} else if(ke.getKeyCode() == KeyEvent.VK_RIGHT){
			right = false;
		}
		if(ke.getKeyCode() == KeyEvent.VK_UP){
			up = false;
		} else if(ke.getKeyCode() == KeyEvent.VK_DOWN){
			down = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		for(Entity e : Area.entities) {
			if(e instanceof Slave) {
				if(e.distance(me.getX() + View.x, me.getY() + View.y) < 10) {
					select = e;
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent me) {
		hold.x = me.getX() + View.x;
		hold.y = me.getY() + View.y;
		
		if(area.isEmpty(hold)) {
			if(Resources.canAfford(CostType.getCostOf(new LumberPost(0,0)))) {
				Entity e = new Incomplete(me.getX() + View.x, me.getY() + View.y, 1);
				area.entities.add(e);
				
				Task t = new Task.Goto(e);
				t.init(select);
				
				Resources.purchase(CostType.getCostOf(new LumberPost(0,0)));
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent me) {
		hold.x = me.getX() + View.x;
		hold.y = me.getY() + View.y;
	}

	@Override
	public void mouseMoved(MouseEvent me) {
		hold.x = me.getX() + View.x;
		hold.y = me.getY() + View.y;
	}
	
}
