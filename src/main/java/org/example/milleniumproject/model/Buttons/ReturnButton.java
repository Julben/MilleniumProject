/*package org.example.milleniumproject.model.Buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.example.milleniumproject.view.Menu;

public class ButtonRetour extends Button {

    private final Stage primaryStage;

    public ButtonRetour(double x, double y, Stage primaryStage) {
        super("Retour");
        this.primaryStage = primaryStage;
        setLayoutX(x);
        setLayoutY(y);
        setPrefSize(360, 50);
        setTextFill(Color.WHITE);
        setBackground(null);
        setFont(Font.font("Cardo", FontWeight.BOLD,35));
        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Action à effectuer lors du clic sur le bouton "Retour"
                // Changer la racine de la scène principale pour afficher le menu
                Menu menu = new Menu(); // Passer la référence de primaryStage à Menu
                primaryStage.getScene().setRoot(menu);
            }
        });
    }
}*/

package org.example.milleniumproject.model.Buttons;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ReturnButton extends Button {

    public ReturnButton(Runnable action) {
        super("Retour");

        // Gestionnaire d'événements pour le clic du bouton
        setOnAction(event -> {action.run();});

        // Définir la couleur, la police et la taille du texte
        setTextFill(Color.WHITE);
        setBackground(null);
        setFont(Font.font("Cardo", 18));
        setPrefWidth(100);

        /// Changement de style lorsque survolé
        setOnMouseEntered(event -> {
            setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(50), Insets.EMPTY)));
            setTextFill(Color.BLACK);
        });

        // Rétablissement du style initial lorsque le curseur quitte le bouton
        setOnMouseExited(event -> {
            setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(5), Insets.EMPTY)));
            setTextFill(Color.WHITE);
        });
    }
}