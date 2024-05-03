/*package org.example.milleniumproject.model;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.*;

public class ButtonProfil {
    private int indexImage;

    // Méthode pour créer des boutons avec des paramètres spécifiques
    public static void createButtons(double[] xPositions, double[] yPositions, Pane container) {
        ButtonProfil buttonProfil = new ButtonProfil(); // Crée une instance de ButtonProfil pour accéder à ses méthodes non statiques
        for (int i = 0; i < 4; i++) {
            Button button = new Button();
            button.setPrefSize(200, 200);
            button.setLayoutX(xPositions[i]);
            button.setLayoutY(yPositions[i]);
            button.setBackground(null);
            buttonProfil.setIndex(buttonProfil.getIndexFromFile(i)); // Définit l'index à partir du fichier
            buttonProfil.updateImage(button); // Met à jour l'image du bouton
            container.getChildren().add(button);
        }
    }

    public int getIndexFromFile(int i) {
        int index = 0; // Valeur par défaut si le fichier n'existe pas ou s'il y a une erreur lors de la lecture

        try (BufferedReader reader = new BufferedReader(new FileReader("Save/save" + i + ".txt"))) {
            String line = reader.readLine();
            if (line != null) {
                index = Integer.parseInt(line.trim());
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            // Gérer les exceptions d'entrée/sortie ou de formatage des nombres
        }

        return index;
    }

    public void updateImage(Button button) {
        ImageEnum selectedImage = ImageEnum.values()[getIndex()];
        String imagePath = selectedImage.getImageFile();
        File file = new File(imagePath);
        ImageView imageView = new ImageView(file.toURI().toString());
        imageView.setFitWidth(200); // Définir la largeur de l'image
        imageView.setFitHeight(200);
        // Définir l'image comme graphique du bouton
        button.setGraphic(imageView);
    }

    public int getIndex() {
        return indexImage;
    }

    public void setIndex(int index) {
        this.indexImage = index;
        // Vérifie si l'index est hors des limites des images disponibles
        if (index < 0) {
            this.indexImage = ImageEnum.values().length - 1; // Retourne à la dernière image si l'index est négatif
        } else if (index >= ImageEnum.values().length) {
            this.indexImage = 0; // Retourne à la première image si l'index dépasse le nombre d'images disponibles
        }
    }
}
*/