package org.example.milleniumproject.model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;

public class ManagementTest {
	@Test
	public void gererEvenements() {
		Stage primaryStage = null;
		List<Button> boutonsComplets = new ArrayList<>();
		Management.handleEvents(primaryStage, boutonsComplets);
	}
}
