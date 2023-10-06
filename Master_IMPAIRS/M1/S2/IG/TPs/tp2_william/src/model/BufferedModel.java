package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import utils.Observable;
import utils.Observer;

public class BufferedModel extends BufferedImage implements Observable {
	
	private Graphics g;
	private Color paintColor;
	private List<Observer> listObserver;

	public BufferedModel(int width, int height, int imageType) {
		super(width, height, imageType);
		this.g = super.getGraphics();
		this.paintColor = Color.WHITE;
		g.setColor(paintColor);
		this.listObserver = new ArrayList<Observer>();
	}

	@Override
	public void subscribe(Observer observer) {
		listObserver.add(observer);
	}

	@Override
	public void notifyObserver() {
		for(Observer item : listObserver) {
			item.update(this);
		}
	}

	public Graphics getGraphics() {
		return this.g;
	}
	
	public void setColorPaint(Color color) {
		paintColor = color;
		g.setColor(paintColor);
	}
	

}
