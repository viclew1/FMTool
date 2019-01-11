package fr.lewon.dofus.fm.ihm.scenes;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.lewon.dofus.fm.ihm.ConstantsErrors;
import fr.lewon.dofus.fm.ihm.ConstantsLabels;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ScreenShotScene extends Scene {

	private static final Logger logger = LoggerFactory.getLogger(ScreenShotScene.class);

	private Robot robot;
	private Stage stage;
	private Group group;
	private double originX;
	private double originY;
	private Rectangle selec;
	private BufferedImage fmArea;

	public ScreenShotScene(Stage stage) {
		super(new Group());

		this.stage = stage;
		group = (Group) getRoot();
		BorderPane pane = new BorderPane();
		group.getChildren().add(pane);
		try {
			robot = new Robot();
		} catch (AWTException e) {
			logger.error(ConstantsErrors.IMPOSSIBLE_SCREENSHOT_ERROR);
			pane.setCenter(new Text(ConstantsErrors.IMPOSSIBLE_SCREENSHOT_ERROR));
			return;
		}
		java.awt.Rectangle dimRect = new java.awt.Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		dimRect.grow(0, -50);
		BufferedImage screenshot = robot.createScreenCapture(dimRect);
		ImageView imageView = new ImageView(SwingFXUtils.toFXImage(screenshot, null));
		imageView.setFitWidth(Screen.getPrimary().getVisualBounds().getWidth());
		imageView.setFitHeight(Screen.getPrimary().getVisualBounds().getHeight() - 50);

		this.selec = new Rectangle();
		this.selec.setOpacity(0.25);
		this.selec.setStroke(Color.WHITE);
		group.getChildren().add(selec);

		pane.setCenter(imageView);

		Button validButton = new Button(ConstantsLabels.VALID_BUTTON_LABEL);
		validButton.setOnAction((e) -> processValid(e));
		BorderPane.setAlignment(validButton, Pos.CENTER);
		pane.setBottom(validButton);


		imageView.setOnMousePressed((e) -> processPress(e));
		imageView.setOnMouseDragged((e) -> processDrag(e));
	}

	public BufferedImage getFmArea() {
		return fmArea;
	}

	private void processValid(ActionEvent e) {
		selec.setVisible(false);
		java.awt.Rectangle rect = new java.awt.Rectangle(
				(int) selec.getX(),
				(int) selec.getY(),
				(int) selec.getWidth(),
				(int) selec.getHeight());
		fmArea = robot.createScreenCapture(rect);
		stage.close();
	}

	private void processPress(MouseEvent e) {
		originX = e.getX();
		originY = e.getY();
		this.selec.setX(originX);
		this.selec.setY(originY);
		this.selec.setWidth(0);
		this.selec.setHeight(0);
	}

	private void processDrag(MouseEvent e) {
		double xTo = e.getX();
		double yTo = e.getY();
		double xFrom = originX;
		double yFrom = originY;
		if (xTo < xFrom) {
			double tmp = xTo;
			xTo = xFrom;
			xFrom = tmp;
		}
		if (yTo < yFrom) {
			double tmp = yTo;
			yTo = yFrom;
			yFrom = tmp;
		}
		this.selec.setX(xFrom);
		this.selec.setY(yFrom);
		this.selec.setWidth(xTo - xFrom);
		this.selec.setHeight(yTo - yFrom);
	}

}
