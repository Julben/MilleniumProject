package org.example.milleniumproject.view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class Menu extends Parent {

    public void afficherMenu(Stage primaryStage, ImageView menuImageView, List<Button> boutons1, List<Button> boutons2, List<Button> boutonsComplets) {
        StackPane root = (StackPane) primaryStage.getScene().getRoot();
        root.getChildren().clear(); // Supprime tous les nœuds existants dans la pile

        // Ajouter l'ImageView du menu à la pile
        root.getChildren().add(menuImageView);

        // Créer une HBox pour contenir les VBox des boutons
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);

        // Créer une boîte verticale pour contenir les boutons
        VBox boutonsLayout = new VBox(30); // Espacement vertical de 20 pixels entre les boutons
        boutonsLayout.setAlignment(Pos.CENTER); // Centrer les boutons
        boutonsLayout.setPadding(new Insets(100, 0, 0, 0)); // Ajouter un padding-top de 100 pixels
        boutonsLayout.setMaxSize(400, 300); // Taille maximale de la boîte de boutons
        boutonsLayout.getChildren().addAll(boutons1);
        HBox.setMargin(boutonsLayout, new Insets(0, 0, 0, 350)); // Marge de 50 pixels à gauche

        // Créer une boîte verticale pour contenir les boutons
        VBox boutonsLayout1 = new VBox(30); // Espacement vertical de 20 pixels entre les boutons
        boutonsLayout1.setAlignment(Pos.CENTER); // Centrer les boutons
        boutonsLayout1.setPadding(new Insets(100, 0, 0, 0)); // Ajouter un padding-top de 100 pixels
        boutonsLayout1.setMaxSize(400, 300); // Taille maximale de la boîte de boutons
        boutonsLayout1.getChildren().addAll(boutons2);

        // Rendre les boutons invisibles et non-cliquables
        for (Button bouton : boutons2) {
            bouton.setVisible(false);
            bouton.setDisable(true);
        }

        // Ajouter les VBox à la HBox
        hbox.getChildren().addAll(boutonsLayout, boutonsLayout1);

        // Ajouter la HBox à la pile
        root.getChildren().add(hbox);

        // Maximiser la fenêtre
        primaryStage.setMaximized(true);

        // Gestionnaire d'événements pour le bouton "Quitter"
        for (Button bouton : boutonsComplets) {
            if (bouton.getText().equals("Nouvelle Partie")) {
                bouton.setOnAction(event -> {
                    // Rendre les boutons "Campagne", "Jouer contre l'ordinateur" et "Joueur contre joueur" visibles et cliquables
                    for (Button b : boutonsComplets) {
                        if (b.getText().equals("Campagne") || b.getText().equals("Jouer contre l'ordinateur") || b.getText().equals("Joueur contre joueur")) {
                            b.setVisible(true);
                            b.setDisable(false);
                        } else if (b.getText().equals("Video") || b.getText().equals("Audio")) {
                            b.setVisible(false);
                            b.setDisable(true);
                        }
                    }
                });
            } else if (bouton.getText().equals("Jouer contre l'ordinateur")) {
                bouton.setOnAction(event -> {
                    // Rendre les boutons "Campagne", "Jouer contre l'ordinateur" et "Joueur contre joueur" visibles et cliquables
                    for (Button b : boutonsComplets) {
                        if (b.getText().equals("Audio") || b.getText().equals("Video") || b.getText().equals("Campagne") || b.getText().equals("Jouer contre l'ordinateur") || b.getText().equals("Joueur contre joueur")) {
                            b.setVisible(false);
                            b.setDisable(true);
                        }
                    }
                    Jcia jcia = new Jcia();
                    primaryStage.getScene().setRoot(jcia); // Afficher la vidéo lorsque le bouton "Video" est cliqué
                });
            } else if (bouton.getText().equals("Joueur contre joueur")) {
                bouton.setOnAction(event -> {
                    // Rendre les boutons "Campagne", "Jouer contre l'ordinateur" et "Joueur contre joueur" visibles et cliquables
                    for (Button b : boutonsComplets) {
                        if (b.getText().equals("Audio") || b.getText().equals("Video") || b.getText().equals("Campagne") || b.getText().equals("Jouer contre l'ordinateur") || b.getText().equals("Joueur contre joueur")) {
                            b.setVisible(false);
                            b.setDisable(true);
                        }
                    }
                    Jcj jcj = new Jcj();
                    primaryStage.getScene().setRoot(jcj); // Afficher la vidéo lorsque le bouton "Video" est cliqué
                });
            } else if (bouton.getText().equals("Charger une Partie")) {
                bouton.setOnAction(event -> {
                    // Rendre les boutons "Campagne", "Jouer contre l'ordinateur" et "Joueur contre joueur" visibles et cliquables
                    for (Button b : boutonsComplets) {
                        if (b.getText().equals("Audio") || b.getText().equals("Video") || b.getText().equals("Campagne") || b.getText().equals("Jouer contre l'ordinateur") || b.getText().equals("Joueur contre joueur")) {
                            b.setVisible(false);
                            b.setDisable(true);
                        }
                    }
                });
            } else if (bouton.getText().equals("Profil")) {
                bouton.setOnAction(event -> {
                    // Rendre les boutons "Campagne", "Jouer contre l'ordinateur" et "Joueur contre joueur" visibles et cliquables
                    for (Button b : boutonsComplets) {
                        if (b.getText().equals("Audio") || b.getText().equals("Video") || b.getText().equals("Campagne") || b.getText().equals("Jouer contre l'ordinateur") || b.getText().equals("Joueur contre joueur")) {
                            b.setVisible(false);
                            b.setDisable(true);
                        }
                    }
                    Profil profil = new Profil(primaryStage, menuImageView, boutons1, boutons2, boutonsComplets);
                    primaryStage.getScene().setRoot(profil); // Afficher la vidéo lorsque le bouton "Video" est cliqué
                });
            } else if (bouton.getText().equals("Paramètres")) {
                bouton.setOnAction(event -> {
                    // Rendre les boutons "Campagne", "Jouer contre l'ordinateur" et "Joueur contre joueur" visibles et cliquables
                    for (Button b : boutonsComplets) {
                        if (b.getText().equals("Audio") || b.getText().equals("Video")) {
                            b.setVisible(true);
                            b.setDisable(false);
                        } else if (b.getText().equals("Campagne") || b.getText().equals("Jouer contre l'ordinateur") || b.getText().equals("Joueur contre joueur")) {
                            b.setVisible(false);
                            b.setDisable(true);
                        }
                    }
                });
            } else if (bouton.getText().equals("Audio")) {
                bouton.setOnAction(event -> {
                    // Rendre les boutons "Campagne", "Jouer contre l'ordinateur" et "Joueur contre joueur" visibles et cliquables
                    for (Button b : boutonsComplets) {
                        if (b.getText().equals("Audio") || b.getText().equals("Video") || b.getText().equals("Campagne") || b.getText().equals("Jouer contre l'ordinateur") || b.getText().equals("Joueur contre joueur")) {
                            b.setVisible(false);
                            b.setDisable(true);
                        }
                    }
                    Audio audio = new Audio();
                    primaryStage.getScene().setRoot(audio); // Afficher la vidéo lorsque le bouton "Video" est cliqué
                });
            } else if (bouton.getText().equals("Video")) {
                bouton.setOnAction(event -> {
                    // Rendre les boutons "Campagne", "Jouer contre l'ordinateur" et "Joueur contre joueur" visibles et cliquables
                    for (Button b : boutonsComplets) {
                        if (b.getText().equals("Audio") || b.getText().equals("Video") || b.getText().equals("Campagne") || b.getText().equals("Jouer contre l'ordinateur") || b.getText().equals("Joueur contre joueur")) {
                            b.setVisible(false);
                            b.setDisable(true);
                        }
                    }
                    Video video = new Video(primaryStage);
                    primaryStage.getScene().setRoot(video); // Afficher la vidéo lorsque le bouton "Video" est cliqué
                });
            } else if (bouton.getText().equals("Quitter")) {
                bouton.setOnAction(event -> Platform.exit()); // Quitter l'application lorsqu'on clique sur le bouton "Quitter"
            }
        }
    }
}