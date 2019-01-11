package fr.lewon.dofus.fm.ihm.scenes;

import java.awt.Rectangle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.lewon.dofus.fm.ihm.ConstantsLabels;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ScreenShotScene extends Scene {

	private static final Logger logger = LoggerFactory.getLogger(ScreenShotScene.class);

	private Stage stage;
	private BorderPane pane;
	private Rectangle fmArea;
	private HBox selecBox;

	public ScreenShotScene(Stage stage) {
		super(new BorderPane());
		this.stage = stage;
		
		pane = (BorderPane) getRoot();
		pane.resize(200, 300);

		selecBox = new HBox();
		selecBox.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		pane.setCenter(selecBox);

		Button validButton = new Button(ConstantsLabels.VALID_BUTTON_LABEL);
		validButton.setOpacity(1);
		validButton.setOnAction((e) -> processValid(e));
		BorderPane.setAlignment(validButton, Pos.CENTER);
		pane.setBottom(validButton);
		pane.setPadding(new Insets(5));
		
		stage.setOnHidden((e) -> replaceWindow(e));
	}
	
	public Stage getStage() {
		return stage;
	}

	public Rectangle getFmArea() {
		return fmArea;
	}
	
	private void replaceWindow(WindowEvent e) {
		if (fmArea == null) {
			return;
		}
		Bounds bounds = selecBox.localToScreen(selecBox.getBoundsInLocal());
		
		double diffX = fmArea.getX() - bounds.getMinX();
		double diffY = fmArea.getY() - bounds.getMinY();
		double diffW = fmArea.getWidth() - bounds.getWidth();
		double diffH = fmArea.getHeight() - bounds.getHeight();
		
		stage.setX(stage.getX() + diffX);
		stage.setY(stage.getY() + diffY);
		stage.setWidth(stage.getWidth() + diffW);
		stage.setHeight(stage.getHeight() + diffH);
	}

	private void processValid(ActionEvent e) {
		Bounds bounds = selecBox.localToScreen(selecBox.getBoundsInLocal());
		int x = (int) bounds.getMinX();
		int y = (int) bounds.getMinY();
		int w = (int) bounds.getWidth();
		int h = (int) bounds.getHeight();
		fmArea = new Rectangle(x, y, w, h);
		stage.close();
	}

}
