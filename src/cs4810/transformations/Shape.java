package cs4810.transformations;

import java.awt.Color;

/**
 * Represents a polygon in 3D space.
 * @author carroll
 *
 */
public class Shape {

	public Line[] lines;
	public int numLines;

	/**
	 * Default constructor for Shape objects.
	 * @param a List of points which compose the shape, in order which to be drawn.
	 */
	public Shape(Line...lines) {
		this.lines = lines;
		numLines = lines.length;
	}//constructor

	/**
	 * Scan-converts the list of lines into an on-screen image, only displaying in viewport area.
	 */
	public void draw() {
		for(Line line : lines) {
			line.draw();
		}//for
	}//draw

	/**
	 * Draws over shape with a color.  Useful for erasing.
	 * Notably, draws over even if not in viewport area.
	 * @param color The color to draw with.
	 */
	public void drawOver(Color color) {
		App.graphics.setColor(color);
		draw();
		App.graphics.setColor(Color.GREEN);
	}//drawOver

	/** 
	 * Tester method which prints each line in the shape.
	 */
	public void printLines() {
		for(Line line : lines) {
			line.print();
		}//for
	}//printLines

	/**
	 * Transforms all points in a shape according to the input matrix.
	 * @param m The matrix by which to multiply each point.
	 * @return The transformed Shape object.
	 */
	private Shape transform (Matrix m) {	
		Line[] newLines = new Line[numLines];

		for(int i = 0; i < numLines; i++) {
			Point a = lines[i].a;
			Point b = lines[i].b;
			newLines[i] = new Line(a.multiply(m), b.multiply(m)); 
		}//for

		return new Shape(newLines);
	}//transform

	/**
	 * Translates by x, y, and z input coordinates.
	 * @param Tx
	 * @param Ty
	 * @param Tz
	 * @return
	 */
	public Shape translate(int Tx, int Ty, int Tz) {
		Matrix m = new Matrix();
		m.data[3][0] = Tx;
		m.data[3][1] = Ty;
		m.data[3][2] = Tz;
		
		return transform(m);
	}//translate

	/**
	 * Scales shape by set amount.
	 * @param Sx Horizontal scale factor.
	 * @param Sy Vertical scale factor.
	 * @param Sz Depth scale factor
	 * @return The transformed Shape object.
	 */
	private Shape basicScale(double Sx, double Sy, double Sz) {
		Matrix m = new Matrix();
		m.data[0][0] = Sx;
		m.data[1][1] = Sy;
		m.data[2][2] = Sz;

		return transform(m);
	}//Basic Scale
	
	/**
	 * Rotates shape around a fixed point.
	 * @param angle The angle (in degrees) to rotate clockwise.
	 * @param char axis The axis around which to rotate relatively (x, y, or z)
	 * @return The translated shape
	 */
	public Shape basicRotate(double angle, char axis) {
		double degrees = -(angle * 3.1415) / 180;
		double sin = Math.sin(degrees);
		double cos = Math.cos(degrees);
		
		Matrix m = new Matrix();
		
		if(axis == 'x') {
			m.data[1][1] = cos;
			m.data[1][2] = sin;
			m.data[2][1] = -sin;
			m.data[2][2] = cos;
		} else {
			if(axis == 'y') {
				m.data[0][0] = cos;
				m.data[0][2] = -sin;
				m.data[2][0] = sin;
				m.data[2][2] = cos;
			} else {
				if(axis == 'z') {
					m.data[0][0] = cos;
					m.data[0][1] = sin;
					m.data[1][0] = -sin;
					m.data[1][1] = cos;
				}//if z
			}//if y
		}//if x
		return transform(m);
	}//rotate
	
	/**
	 * Scales shape relative to a fixed point.
	 * @param Sx Horizontal scale factor
	 * @param Sy Vertical scale factor
	 * @param Sz Depth scale factor
	 * @param Cx X coordinate of pivot point
	 * @param Cy Y coordinate of pivot point
	 * @param Cz Z coordinate of pivot point
	 * @return The translated shape
	 */
	public Shape scale(double Sx, double Sy, double Sz, int Cx, int Cy, int Cz) {
		return translate(-Cx,-Cy,-Cz).basicScale(Sx,Sy,Sz).translate(Cx,Cy,Cz);			
	}//scale
	
	/**
	 * Rotates shape around a fixed point.
	 * @param angle The angle (in degrees) to rotate clockwise.
	 * @param axis The axis around which to rotate relatively (x, y, or z)
	 * @param Cx X coordinate of pivot point
	 * @param Cy Y coordinate of pivot point
	 * @param Cz Z coordinate of pivot point
	 * @return The translated shape
	 */
	public Shape rotate(double angle, char axis, int Cx, int Cy, int Cz) {
		return translate(-Cx,-Cy,-Cz).basicRotate(angle, axis).translate(Cx,Cy,Cz);		
	}//rotate
}//Shape
