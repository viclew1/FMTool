package fr.lewon.dofus.fm.ihm.groups;

import fr.lewon.dofus.fm.ihm.panes.ConfPane;
import fr.lewon.ihm.builder.GenericGroup;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HomeGroup extends GenericGroup {

	private ConfPane confPane;

	public HomeGroup(Stage stage) {
		super(stage);
	}

	@Override
	protected Node generateChildren(Stage stage) {
		GridPane pane = new GridPane();
		confPane = new ConfPane(stage);
		pane.add(confPane, 0, 0);
		return pane;
	}

}
