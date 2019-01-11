package fr.lewon.dofus.fm.ihm.panes;

import java.util.ArrayList;
import java.util.List;

import fr.lewon.dofus.fm.ihm.ConstantsErrors;
import fr.lewon.dofus.fm.ihm.ConstantsLabels;
import fr.lewon.dofus.fm.ihm.scenes.ScreenShotScene;
import fr.lewon.ihm.builder.GenericPane;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfPane extends GenericPane {

	private Rectangle fmArea;
	private Button selectFmAreaButton;
	private Button showExampleButton;

	public ConfPane(Stage stage) {
		super(stage, ConstantsLabels.CONF_PANE_LABEL);
	}

	@Override
	protected Pane generateContent() {
		GridPane pane = new GridPane();
		selectFmAreaButton = new Button(ConstantsLabels.SELECT_FM_AREA_LABEL);
		showExampleButton = new Button(ConstantsLabels.SHOW_EXAMPLE_LABEL);
		selectFmAreaButton.setOnAction((e) -> processSelectFmAreaAction(e));
		showExampleButton.setOnAction((e) -> processShowExampleAction(e));

		pane.add(selectFmAreaButton, 0, 0);
		pane.add(showExampleButton, 1, 0);
		return pane;
	}

	@Override
	public List<String> checkErrors() {
		List<String> errors = new ArrayList<>();
		if (fmArea == null) {
			errors.add(ConstantsErrors.NO_FM_AREA_ERROR);
		}
		return errors;
	}

	private void processShowExampleAction(ActionEvent e) {

	}

	private void processSelectFmAreaAction(ActionEvent e) {
		final Stage screenshotStage = new Stage();
		screenshotStage.initModality(Modality.APPLICATION_MODAL);
		screenshotStage.initOwner(getStage());
		screenshotStage.setMaximized(true);
		Scene screenshotScene = new ScreenShotScene(screenshotStage);
		screenshotStage.setScene(screenshotScene);
		screenshotStage.show();
	}

}
