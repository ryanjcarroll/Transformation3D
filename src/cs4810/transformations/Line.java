package cs4810.transformations;

import javafx.embed.swing.SwingFXUtils;

/**
 * Class to create a line with 3D coordinate endpoints.
 * @author carroll
 * 
 */
public class Line {

	public Point a;
	public Point b;

	/**
	 * Default constructor for 3D Line objects which takes in endpoints.
	 * @param a The first endpoint.
	 * @param b The second endpoint.
	 */
	public Line(Point a, Point b) {
		this.a = a;
		this.b = b;
	}//point constructor

	/**
	 * Constructor for 3D Line objects which takes in coordinate values.
	 * @param ax Point a, x coordinate.
	 * @param ay Point a, y coordinate.
	 * @param az Point a, z coordinate.
	 * @param bx Point b, x coordinate.
	 * @param by Point b, y coordinate.
	 * @param bz Point b, z coordinate.
	 */
	public Line(int ax, int ay, int az, int bx, int by, int bz) {
		a = new Point(ax, ay, az);
		b = new Point(bx, by, bz);
	}//value constructors


	/**
	 * Activates pixels using a line drawing algorithm and scan-converts line onto screen.
	 */
	public void draw() {
		Point newA = a.convert(App.viewpoint, App.screen);
		Point newB = b.convert(App.viewpoint, App.screen);
		
		drawBasicLine(newA.x, newA.y, newB.x, newB.y);
	}//draw


	/**
	 * Prints info about line endpoints.
	 */
	public void print() {
		a.print();
		System.out.print(" to ");
		b.print();
		System.out.println();
	}//print

	/**
	 * Activates the pixel at the specified coordinates.
	 * @param x The x coordinate of the pixel to activate.
	 * @param y The y coordinate of the pixel to activate.
	 * @param type The type of line generating the pixels - triggers a specific graphics entity.
	 */
	public void activatePixel(int x, int y) {
		App.graphics.drawLine(x, y, x, y);
	}//activatePixel

	/**
	 * Draws a line between the given coordinates using the basic line-drawing algorithm.
	 * Only capable of drawing a 2D, flat-surface line.  Must be converted first if 3D.
	 * @param x0 The first x coordinate.
	 * @param y0 The first y coordinate.
	 * @param x1 The second x coordinate.
	 * @param y1 The second y coordiante.
	 */
	public void drawBasicLine(int x0, int y0, int x1, int y1) {
		int dx = Math.abs(x1-x0);
		int dy = Math.abs(y1-y0);
		int x, y;
		double m;

		//always draw left to right
		if(x0 > x1) {
			int tempX = x1;
			int tempY = y1;
			x1 = x0;
			x0 = tempX;
			y1 = y0;
			y0 = tempY;
		}

		//set slope value based on orientation
		if(dx < dy) {
			m = (double)dx/(double)dy;
		} else {
			m = (double)dy/(double)dx;
		}

		//main pixel activation loop
		if(dx < dy) {
			//vertical orientation loop
			for(int i = 0; i <= dy; i++) {
				if(y0 < y1) { //if drawn top to bottom
					y = y0 + i;
				} else { //if drawn bottom to top
					y = y0 - i;
				}
				x = (int)(x0 + (m * i));
				//activate appropriate pixels
				activatePixel(x,y);
			}
		} else {
			//horizontal orientation loop (and neither)
			for(int i = 0; i <= dx; i++) {
				x = x0 + i;
				if(y0 < y1) { //if drawn top to bottom
					y = (int)(y0 + (m * i));
				} else { //if drawn bottom to top
					y = (int)(y0 - (m * i));
				}
				//activate appropriate pixels
				activatePixel(x,y);
			}
		}
		App.frame.setImage(SwingFXUtils.toFXImage(App.buffered, null));
	}//drawBasicLine

}//Line
