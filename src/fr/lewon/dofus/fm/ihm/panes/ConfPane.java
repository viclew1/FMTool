package fr.lewon.dofus.fm.ihm.panes;

import java.util.ArrayList;
import java.util.List;

import fr.lewon.dofus.fm.ihm.ConstantsErrors;
import fr.lewon.dofus.fm.ihm.ConstantsLabels;
import fr.lewon.dofus.fm.ihm.scenes.ScreenShotScene;
import fr.lewon.ihm.builder.GenericPane;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfPane extends GenericPane {

	private ScreenShotScene screenShotScene;
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
		

		Stage screenshotStage = new Stage();
		screenshotStage.initModality(Modality.APPLICATION_MODAL);
		screenshotStage.initOwner(getStage());
		screenshotStage.setOpacity(0.5);
		screenshotStage.setTitle(ConstantsLabels.SELECT_FM_AREA_LABEL);
		screenShotScene = new ScreenShotScene(screenshotStage);
		screenshotStage.setScene(screenShotScene);
		
		selectFmAreaButton.setOnAction((e) -> processSelectFmAreaAction(e));
		showExampleButton.setOnAction((e) -> processShowExampleAction(e));

		pane.add(selectFmAreaButton, 0, 0);
		pane.add(showExampleButton, 1, 0);
		return pane;
	}

	@Override
	public List<String> checkErrors() {
		List<String> errors = new ArrayList<>();
		if (screenShotScene.getFmArea() == null) {
			errors.add(ConstantsErrors.NO_FM_AREA_ERROR);
		}
		return errors;
	}

	private void processShowExampleAction(ActionEvent e) {

	}

	private void processSelectFmAreaAction(ActionEvent e) {
		screenShotScene.getStage().showAndWait();
	}

}
