package cs4810.transformations;

/**
 * Represents the viewing screen on which 2D is displayed.
 * @author carroll
 *
 */
public class Screen {

	public Point center;
	public int width;
	public int height;
	
	/**
	 * Constructor for screen objects.
	 */
	public Screen(Point center, int height, int width) {
		this.center = center;
		this.width = width;
		this.height = height;
	}//constructor
	
}//Screen
