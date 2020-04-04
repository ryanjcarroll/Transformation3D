package cs4810.transformations;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Application;

/**
 * Application for displaying a 3D object using perspective projection and applying matrix transformations.
 * @author carroll
 *
 */
public class App extends Application {

	private Scene scene;
	public static int SCENE_WIDTH = 800;
	public static int SCENE_HEIGHT = 800;

	public static Graphics graphics;
	public static BufferedImage buffered;
	public static ImageView frame;
	public static VBox root;

	public static Shape shape;
	public static Shape lastShape;

	public static Point viewpoint;
	public static Screen screen;

	/**
	 * Contains all non-setup method calls for program to execute.
	 */
	public void execute() {
		viewpoint = new Point(0,0,-400);
		Point screenCenter = new Point(0,0,-350);
		screen = new Screen(screenCenter,100,100); //empty constructor
		
		Point a = new Point(-100,-100,-100); 
		Point b = new Point(-100,100,-100);
		Point c = new Point(100,-100,-100);
		Point d = new Point(100,100,-100);
		Point a1 = new Point(-100,-100,100); 
		Point b1 = new Point(-100,100,100);
		Point c1 = new Point(100,-100,100);
		Point d1 = new Point(100,100,100);

		Line ab = new Line(a,b);
		Line bd = new Line(b,d);
		Line cd = new Line(c,d);
		Line ac = new Line(a,c);
		Line ab1 = new Line(a1,b1);
		Line bd1 = new Line(b1,d1);
		Line cd1 = new Line(c1,d1);
		Line ac1 = new Line(a1,c1);
		Line as = new Line(a,a1);
		Line bs = new Line(b,b1);
		Line cs = new Line(c,c1);
		Line ds = new Line(d,d1);

		shape = new Shape(ab,bd,cd,ac,ab1,bd1,cd1,ac1,as,bs,cs,ds);
		shape.draw();
	}//execute

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start(Stage stage){

		root = new VBox();
		scene = new Scene(root);

		//initialize stage and scene
		root = new VBox();
		scene = new Scene(root);

		//stage settings
		stage.setScene(scene);
		stage.setWidth(SCENE_WIDTH+1);
		stage.setHeight(SCENE_HEIGHT+1);
		stage.setTitle("3D Line App!");
		stage.show();

		TransformBar transformBar = new TransformBar();
		root.getChildren().add(transformBar);
		
		//initialize a BufferedImage to serve as the canvas
		buffered = new BufferedImage(SCENE_WIDTH, SCENE_HEIGHT, BufferedImage.TYPE_INT_RGB);
		frame = new ImageView(SwingFXUtils.toFXImage(buffered, null));
		root.getChildren().add(frame);
		stage.sizeToScene();

		//line graphics settings
		graphics = buffered.getGraphics();
		graphics.setColor(Color.GREEN);

		execute();
	}//start

	/**
	 * Sets the new active shape to the input shape, and colors the previous shape gray,
	 * while erasing the shape previous to that.
	 * @param newShape The new shape to set.
	 */
	public static void setShape(Shape newShape) {
		lastShape = shape;
		lastShape.drawOver(Color.BLACK);
		shape = newShape;
		shape.draw();		
	}//setShape

}//App
