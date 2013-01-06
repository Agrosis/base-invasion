package com.jantox.baseinvasion;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {

	public static BufferedImage tree;
	public static BufferedImage townhall;
	public static BufferedImage lumberpost;
	public static BufferedImage stonepost;
	
	public static BufferedImage stone;
	public static BufferedImage gold;
	
	public static BufferedImage logs;
	
	public static BufferedImage slave;
	
	public static BufferedImage cross;
	
	public static BufferedImage enemyb;
	
	public static BufferedImage log;
	
	public static void loadImages() {
		try {
			tree = ImageIO.read(new File("res/tree.png"));
			townhall = ImageIO.read(new File("res/townhall.png"));
			lumberpost = ImageIO.read(new File("res/lumberpost.png"));
			stone = ImageIO.read(new File("res/stone.png"));
			gold = ImageIO.read(new File("res/gold.png"));
			logs = ImageIO.read(new File("res/logs.png"));
			slave = ImageIO.read(new File("res/slavesheet.png"));
			cross = ImageIO.read(new File("res/cross.png"));
			stonepost = ImageIO.read(new File("res/stonepost.png"));
			enemyb = ImageIO.read(new File("res/enemyb.png"));
			log = ImageIO.read(new File("res/log.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
