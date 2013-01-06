package com.jantox.baseinvasion;

import java.util.Random;

public class Task {
	
	static Random random = new Random();

	protected Entity worker;
	public Entity target;
	public double tx, ty, rad;
	
	public void init(Entity e) {
		this.worker = e;
		worker.setTask(this);
	}
	
	public Entity getWorker() {
		return worker;
	}
	
	public void setWorker(Entity e) {
		this.worker = e;
	}
	
	public void tick() {
		
	}
	
	public static class Goto extends Task {
		
		public Goto(Entity t) {
			this.target = t;
			this.tx = target.x;
			this.ty = target.y;
			this.rad = target.radius;
		}
		
		public void init(Entity worker) {
			this.worker = worker;
			this.worker.setTask(this);
		}
		
		public void setArrived() {
			this.worker.setTask(null);
		}
		
	}
	
	public static class Collect extends Task {
		
		public Collect(Entity target) {
			this.target = target;
		}
		
		public void init(Entity worker) {
			this.worker = worker;
			this.worker.setTask(this);
		}
		
		public void setGathered() { // now find a deposit tunnel
			worker.setTask(null);
			Task.Deposit t = new Task.Deposit();
			t.init(worker);
		}
		
	}
	
	public static class Deposit extends Task {
		
		public Deposit() {
			
		}
		
		public void init(Entity worker) {
			this.worker = worker;
			this.worker.setTask(this);
			this.target = worker.getClosestEntity(new EntityFilter(EntityFilter.LUMBERPOST));
			Entity th = worker.getClosestEntity(new EntityFilter(EntityFilter.TOWNHALL));
			if(target == null || worker.distance(th) < worker.distance(this.target))
				this.target = th;
			
			this.tx = target.x;
			this.ty = target.y;
		}
		
		public void setDeposited() { // go back to collecting
			Resources.deposit(((Slave)this.worker).rescar, 1);
			
			worker.setTask(null);
			Task.Goto t = null;
			t = new Task.Goto(worker.getClosestEntity(new EntityFilter(EntityFilter.TREE)));
			t.init(worker);
		}
		
	}
	
	public static class Build extends Task {
		
		public Build(Entity target) {
			this.target = target;
		}
		
		public void init(Entity worker) {
			this.worker = worker;
			this.worker.setTask(this);
		}
		
		public void setBuilt() {
			worker.setTask(null);
			Task.Goto t = null;
			if(worker.getClosestEntity(new EntityFilter(EntityFilter.INCOMPLETE)) == null) {
				t = new Task.Goto(worker.getClosestEntity(new EntityFilter(EntityFilter.TREE)));
			} else {
				t = new Task.Goto(worker.getClosestEntity(new EntityFilter(EntityFilter.INCOMPLETE)));
			}
			t.init(worker);
		}
		
	}
	
}
