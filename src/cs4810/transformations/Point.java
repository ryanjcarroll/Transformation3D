package cs4810.transformations;

/**
 * Class to create a point with x, y and z coordinate values. 
 * @author carroll
 *
 */
public class Point {

	public int x;
	public int y;
	public int z;

	/**
	 * Default constructor for the Point class.
	 * @param x The x coordinate of the point.
	 * @param y The y coordinate of the point.
	 * @param z The z coordinate of the point.
	 */
	public Point(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}//constructor

	/**
	 * Prints point coordinates.
	 */
	public void print() {
		System.out.println(x + "," + y + "," + z);
	}//print

	/**
	 * Returns point info as a String
	 */
	public String toString() {
		return Integer.toString(x) + ", " + Integer.toString(y) + ", " + Integer.toString(z);
	}//toString

	/**
	 * Adjust the point according to the result of a point/matrix multiplication.
	 * @param m The matrix by which to multiply.
	 */
	public Point multiply(Matrix m) {
		int newX, newY, newZ;

		newX = (int)((m.data[0][0] * x) + (m.data[1][0] * y) + (m.data[2][0] * z) + m.data[3][0]);
		newY = (int)((m.data[0][1] * x) + (m.data[1][1] * y) + (m.data[2][1] * z) + m.data[3][1]);
		newZ = (int)((m.data[0][2] * x) + (m.data[1][2] * y) + (m.data[2][2] * z) + m.data[3][2]);

		return new Point(newX, newY, newZ);
	}//multiply

	/**
	 * Adjust the point according to the result of a point/matrix multiplication.
	 * @param m The matrix by which to multiply.
	 */
	public Point multiply(Matrix2D m) {
		int newX = (int)((m.data[0][0] * x) + (m.data[1][0] * y) + m.data[2][0]);
		int newY = (int)((m.data[0][1] * x) + (m.data[1][1] * y) + m.data[2][1]);
		return new Point(newX,newY,0);
	}//multiply

	/**
	 * Returns the result of conversion to 2D for display.
	 * @return
	 */
	public Point convert(Point vp, Screen s) {
		int xs = 0;
		int ys = 0;

		Point P = this;
		Point V = vp;
		Point S = s.center;

		//convert to eye coordinate system rel to viewpoint
		int Pe_x = P.x - V.x;
		int Pe_y = P.y - V.y;
		int Pe_z = P.z - V.z;
		int Se_x = S.x - V.x;
		int Se_y = S.y - V.y;
		int Se_z = S.z - V.z;
		Point Pe = new Point(Pe_x, Pe_y, Pe_z);
		Point Se = new Point(Se_x, Se_y, Se_z);

		//if z axis oriented
		if(Math.abs(Se_z) >= Math.abs(Se_x) && Math.abs(Se_z) >= Math.abs(Se_y)) {

			//calculate angles for zx plane
			double thetaSx = Math.atan((double)Se_x / (double)Se_z);
			double thetaPx = Math.atan((double)Pe_x / (double)Pe_z);
			double thetaDiffX = thetaPx - thetaSx;

			//calculate angles for zy plane
			double thetaSy = Math.atan((double)Se_y / (double)Se_z);
			double thetaPy = Math.atan((double)Pe_y / (double)Pe_z);
			double thetaDiffY = thetaPy - thetaSy;

			//calculate lengths of hypotenuses
			int Sex_length = (int)Math.sqrt(Math.pow(Se.x, 2) + Math.pow(Se.z, 2));		
			int Sey_length = (int)Math.sqrt(Math.pow(Se.y, 2) + Math.pow(Se.z, 2));	

			//calculate final values
			ys = (int)(Sey_length * Math.tan(thetaDiffY));
			xs = (int)(Sex_length * Math.tan(thetaDiffX));
		}//z
		//if x axis oriented
		else if (Math.abs(Se_x) >= Math.abs(Se_y) && Math.abs(Se_x) >= Math.abs(Se_z)){

			//calculate angles for xy plane
			double thetaSy = Math.atan((double)Se_y / (double)Se_x);
			double thetaPy = Math.atan((double)Pe_y / (double)Pe_x);
			double thetaDiffY = thetaPy - thetaSy;

			//calculate angles for xz plane
			double thetaSz = Math.atan((double)Se_z / (double)Se_x);
			double thetaPz = Math.atan((double)Pe_z / (double)Pe_x);
			double thetaDiffZ = thetaPz - thetaSz;

			//calculate lengths of hypotenuses
			int Sey_length = (int)Math.sqrt(Math.pow(Se.y, 2) + Math.pow(Se.x, 2));
			int Sez_length = (int)Math.sqrt(Math.pow(Se.z, 2) + Math.pow(Se.x, 2));
			
			//calculate final values
			ys = (int)(Sey_length * Math.tan(thetaDiffY));
			xs = (int)(Sez_length * Math.tan(thetaDiffZ));
		}//x
		//if y axis oriented
		else if(Math.abs(Se_y) >= Math.abs(Se_x) && Math.abs(Se_y) >= Math.abs(Se_z)) {

			//calculate angles for yx plane
			double thetaSx = Math.atan((double)Se_x / (double)Se_y);
			double thetaPx = Math.atan((double)Pe_x / (double)Pe_y);
			double thetaDiffX = thetaPx - thetaSx;

			//calculate angles for yz plane
			double thetaSz = Math.atan((double)Se_z / (double)Se_y);
			double thetaPz = Math.atan((double)Pe_z / (double)Pe_y);
			double thetaDiffZ = thetaPz - thetaSz;

			//calculate lengths of hypotenuses
			int Sex_length = (int)Math.sqrt(Math.pow(Se.y, 2) + Math.pow(Se.x, 2));
			int Sez_length = (int)Math.sqrt(Math.pow(Se.y, 2) + Math.pow(Se.z, 2));
			
			//calculate final values
			xs = (int)(Sex_length * Math.tan(thetaDiffX));
			ys = (int)(Sez_length * Math.tan(thetaDiffZ));
	
		}//y


		//scale based on screen size to window size
		int sceneW = App.SCENE_WIDTH;
		int sceneH = App.SCENE_HEIGHT;
		double scaleX = (double)sceneW / (double)s.width;
		double scaleY = (double)sceneH / (double)s.height;

		int xFromCenter = (int)(xs * scaleX);
		int yFromCenter = (int)(ys * scaleY);

		int xFromOrigin = sceneW / 2 + xFromCenter;
		int yFromOrigin = sceneH / 2 - yFromCenter;

		return new Point(xFromOrigin,yFromOrigin,0);
	}//convert

}//point
