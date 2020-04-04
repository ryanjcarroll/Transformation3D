package cs4810.transformations;

import java.awt.Color;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Contains buttons and controls to carry out transformations on a shape during runtime.
 * @author carroll
 */
public class TransformBar extends VBox {

	public TransformBar() {
		//Drop-down menu and Select button
		Button selectButton = new Button("Transform!");
		ChoiceBox<String> choiceBox = new ChoiceBox<>();
		choiceBox.getItems().addAll("Translate","Scale","Rotate");
		VBox leftItems = new VBox(choiceBox, selectButton);

		//Spacer and viewport settings
		Pane spacerLeft = new Pane();
		spacerLeft.setMinSize((App.SCENE_WIDTH/2) - 140, 1);
		Button viewpointButton = new Button("Adjust Viewpoint and Screen");
		viewpointButton.setOnAction(viewpointButtonHandler);
		VBox middleItems = new VBox(viewpointButton);

		//Add all items to scene
		HBox buttonBar = new HBox(leftItems, spacerLeft, middleItems);
		getChildren().addAll(buttonBar);

		//Handler for Translate! button, performs action selected by drop-down menu
		EventHandler<ActionEvent> buttonHandler = event ->
		{
			System.out.println(choiceBox.getValue());
			
			if (choiceBox.getValue() == "Translate") {
				displayTranslateOptions();
			} else if (choiceBox.getValue() == "Scale") {
				displayScaleOptions();
			} else if(choiceBox.getValue() == "Rotate") {
				displayRotateOptions();
			}
		};
		selectButton.setOnAction(buttonHandler);
	}//constructor


	/**
	 * Handler for button which displays viewpoint and screen adjustment options.
	 */
	private EventHandler<ActionEvent> viewpointButtonHandler = event ->
	{
		//Stage and background field
		Stage vpStage = new Stage();
		VBox field = new VBox(10);

		//viewpoint coordinates
		Text vpInfo = new Text(" Enter coords of viewpoint:");
		Text xText = new Text(" x-coord: ");
		Text yText = new Text(" y-coord: ");
		Text zText = new Text(" z-coord: ");
		TextField xField = new TextField(Integer.toString(App.viewpoint.x));
		TextField yField = new TextField(Integer.toString(App.viewpoint.y));
		TextField zField = new TextField(Integer.toString(App.viewpoint.z));
		HBox xBox = new HBox(xText, xField);
		HBox yBox = new HBox(yText, yField);
		HBox zBox = new HBox(zText, zField);
		
		//screen center coordinates
		Text screenInfo = new Text(" Enter coords of screen center:");
		Text xSText = new Text(" x-coord: ");
		Text ySText = new Text(" y-coord: ");
		Text zSText = new Text(" z-coord: ");
		TextField xSField = new TextField(Integer.toString(App.screen.center.x));
		TextField ySField = new TextField(Integer.toString(App.screen.center.y));
		TextField zSField = new TextField(Integer.toString(App.screen.center.z));
		HBox xSBox = new HBox(xSText, xSField);
		HBox ySBox = new HBox(ySText, ySField);
		HBox zSBox = new HBox(zSText, zSField);
		
		//screen size info
		Text screenSizeInfo = new Text(" Enter dimensions of screen:");
		Text widthText = new Text(" width: ");
		Text heightText = new Text(" height: ");
		TextField widthField = new TextField(Integer.toString(App.screen.width));
		TextField heightField = new TextField(Integer.toString(App.screen.height));
		HBox widthBox = new HBox(widthText, widthField);
		HBox heightBox = new HBox(heightText, heightField);
		
		//enter button
		Button enterButton = new Button("Enter");
		enterButton.setAlignment(Pos.BASELINE_CENTER);
		EventHandler<ActionEvent> enterHandler = e ->
		{
			int Cx = Integer.parseInt(xField.getText());
			int Cy = Integer.parseInt(yField.getText());
			int Cz = Integer.parseInt(zField.getText());
			
			int sCx = Integer.parseInt(xSField.getText());
			int sCy = Integer.parseInt(ySField.getText());
			int sCz = Integer.parseInt(zSField.getText());
			int sW = Integer.parseInt(widthField.getText());
			int sH = Integer.parseInt(heightField.getText());
			
			Point newViewpoint = new Point(Cx,Cy,Cz);
			Point center = new Point(sCx,sCy,sCz);
			Screen newScreen = new Screen(center, sW, sH);
			App.shape.drawOver(Color.BLACK);
			App.viewpoint = newViewpoint;
			App.screen = newScreen;		
			App.setShape(App.shape); //re-draws the exact same shape
			vpStage.close();
		};
		enterButton.setOnAction(enterHandler);
		
		//add elements to scene
		field.getChildren().addAll(vpInfo,xBox,yBox,zBox,screenInfo,xSBox,ySBox,zSBox,screenSizeInfo,widthBox,heightBox,enterButton);
		Scene vpScene = new Scene(field,200,390);
		vpStage.setScene(vpScene);
		vpStage.show();
		//screen size
		
	};//viewpointButtonHandler


	/**
	 * Displays a dialog box that takes input for translation in x, y, and z directions.
	 */
	private void displayTranslateOptions() {
		//Stage and background field
		Stage translateStage = new Stage();
		VBox field = new VBox(10);

		//Text and Inputs for Shift
		Text shiftInfo = new Text(" Enter coordinates of shift:");
		Text xText = new Text(" x-direction: ");
		Text yText = new Text(" y-direction: ");
		Text zText = new Text(" z-direction: ");
		TextField xField = new TextField("0");
		TextField yField = new TextField("0");
		TextField zField = new TextField("0");
		HBox xBox = new HBox(xText, xField);
		HBox yBox = new HBox(yText, yField);
		HBox zBox = new HBox(zText, zField);

		//Enter Button
		Button enterButton = new Button("Enter");
		enterButton.setAlignment(Pos.BASELINE_CENTER);
		EventHandler<ActionEvent> enterHandler = e ->
		{
			System.out.println("Tx: " + xField.getText());
			System.out.println("Ty: " + yField.getText());
			System.out.println("Tz: " + zField.getText());
			
			Shape newShape = App.shape.translate(Integer.parseInt(xField.getText()), Integer.parseInt(yField.getText()), Integer.parseInt(zField.getText()));
			App.setShape(newShape);

			translateStage.close();
		};
		enterButton.setOnAction(enterHandler);

		//Add elements to scene
		field.getChildren().addAll(shiftInfo, xBox, yBox, zBox, enterButton);			
		Scene translateScene = new Scene(field,200,190);
		translateStage.setScene(translateScene);
		translateStage.show();
	}//displayTranslateOptions
	
	/**
	 * Displays a dialog box that takes input for scale factors and center of scale.
	 */
	private void displayScaleOptions() {
		//Stage and background field
		Stage scaleStage = new Stage();
		VBox field = new VBox(10);

		//Text and Inputs for Scale Factors
		Text scaleInfo = new Text(" Enter scale factors:");
		Text horzText = new Text(" horizontal: ");
		Text vertText = new Text(" vertical: ");
		Text depText = new Text(" depth:" );
		TextField horzField = new TextField("1");
		TextField vertField = new TextField("1");
		TextField depField = new TextField("1");
		HBox horzBox = new HBox(horzText, horzField);
		HBox vertBox = new HBox(vertText, vertField);
		HBox depBox = new HBox(depText, depField);

		//Text and Inputs for Center
		Text centerInfo = new Text(" Enter coordinates of center:");
		Text xText = new Text(" x-coord: ");
		Text yText = new Text(" y-coord: ");
		Text zText = new Text(" z-coord: ");
		TextField xField = new TextField("0");
		TextField yField = new TextField("0");
		TextField zField = new TextField("0");
		HBox xBox = new HBox(xText, xField);
		HBox yBox = new HBox(yText, yField);
		HBox zBox = new HBox(zText, zField);

		//Enter Button
		Button enterButton = new Button("Enter");
		enterButton.setAlignment(Pos.BASELINE_CENTER);
		EventHandler<ActionEvent> enterHandler = e ->
		{
			System.out.println("Sx: " + horzField.getText());
			System.out.println("Sy: " + vertField.getText());
			System.out.println("Sz: " + depField.getText());
			System.out.println("Cx: " + xField.getText());
			System.out.println("Cy: " + yField.getText());
			System.out.println("Cz: " + zField.getText());
			
			double Sx = Double.parseDouble(horzField.getText());
			double Sy = Double.parseDouble(vertField.getText());
			double Sz = Double.parseDouble(depField.getText());
			int Cx = Integer.parseInt(xField.getText());
			int Cy = Integer.parseInt(yField.getText());
			int Cz = Integer.parseInt(zField.getText());
			Shape newShape = App.shape.scale(Sx,Sy,Sz,Cx,Cy,Cz);
			App.setShape(newShape);
			
			scaleStage.close();
		};
		enterButton.setOnAction(enterHandler);

		//Add elements to scene
		field.getChildren().addAll(scaleInfo, horzBox, vertBox, depBox, centerInfo, xBox, yBox, zBox, enterButton);			
		Scene scaleScene = new Scene(field,200,300);
		scaleStage.setScene(scaleScene);
		scaleStage.show();
	}//displayScaleOptions

	/**
	 * Displays a dialog box that takes input for angle and center of rotation.
	 */
	private void displayRotateOptions() {
		//Stage and background field
		Stage rotateStage = new Stage();
		VBox field = new VBox(10);

		//Text and Inputs for Angle
		Text angleInfo = new Text(" Enter angle in degrees to rotate:");
		Text angleText = new Text(" angle: ");
		TextField angleField = new TextField("0");
		HBox angleBox = new HBox(angleText, angleField);

		//Text and Inputs for Center
		Text centerInfo = new Text(" Enter coordinates of center:");
		Text xText = new Text(" x-coord: ");
		Text yText = new Text(" y-coord: ");
		Text zText = new Text(" z-coord: ");
		TextField xField = new TextField("0");
		TextField yField = new TextField("0");
		TextField zField = new TextField("0");
		HBox xBox = new HBox(xText, xField);
		HBox yBox = new HBox(yText, yField);
		HBox zBox = new HBox(zText, zField);

		Text axisInfo = new Text(" Enter relative axis (x, y or z): ");
		Text axisText = new Text(" axis: ");
		TextField axisField = new TextField("x");
		HBox axisBox = new HBox(axisText, axisField);

		//Enter Button
		Button enterButton = new Button("Enter");
		enterButton.setAlignment(Pos.BASELINE_CENTER);
		EventHandler<ActionEvent> enterHandler = e ->
		{
			System.out.println("A: " + angleField.getText());
			System.out.println("Cx: " + xField.getText());
			System.out.println("Cy: " + yField.getText());
			System.out.println("Cz: " + zField.getText());
			
			double angle = Double.parseDouble(angleField.getText());
			char axis = axisField.getText().toLowerCase().charAt(0);
			int Cx = Integer.parseInt(xField.getText());
			int Cy = Integer.parseInt(yField.getText());
			int Cz = Integer.parseInt(zField.getText());
			Shape newShape = App.shape.rotate(angle, axis,Cx,Cy,Cz);
			App.setShape(newShape);
			rotateStage.close();
		};
		enterButton.setOnAction(enterHandler);

		//Add elements to scene
		field.getChildren().addAll(angleInfo, angleBox, centerInfo, xBox, yBox, zBox, axisInfo, axisBox, enterButton);			
		Scene rotateScene = new Scene(field,200,290);
		rotateStage.setScene(rotateScene);
		rotateStage.show();
	}//displayRotateOptions
}//TransformBar
