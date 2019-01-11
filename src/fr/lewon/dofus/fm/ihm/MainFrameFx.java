package fr.lewon.dofus.fm.ihm;

import fr.lewon.dofus.fm.ihm.groups.HomeGroup;
import fr.lewon.ihm.builder.GenericApplication;
import fr.lewon.ihm.builder.GenericGroup;
import javafx.stage.Stage;

public class MainFrameFx extends GenericApplication {

	public MainFrameFx() {
		super(ConstantsLabels.MAIN_FRAME_LABEL);
	}

	@Override
	protected GenericGroup getBaseGroup(Stage stage) {
		return new HomeGroup(stage);
	}

}
