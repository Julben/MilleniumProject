package org.example.milleniumproject.model;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
/**
 * Classe utilitaire pour la gestion des boutons dans une grille.
 */
public class ButtonUtils
{
    /**
     * Met en surbrillance les boutons alignés dans une grille.
     *
     * @param gridPane La grille contenant les boutons.
     */
    public static void highlightAlignedButtons(GridPane gridPane) {
        for (int i = 0; i < gridPane.getRowCount(); i++) {
            highlightAlignedButtonsInRow(gridPane, i);
        }
        for (int i = 0; i < gridPane.getColumnCount(); i++) {
            highlightAlignedButtonsInColumn(gridPane, i);
        }
    }

    private static void highlightAlignedButtonsInRow(GridPane gridPane, int rowIndex) {
        Button[] buttonsInRow = new Button[gridPane.getColumnCount()];
        for (int colIndex = 0; colIndex < gridPane.getColumnCount(); colIndex++) {
            Node node = getNodeByRowColumnIndex(rowIndex, colIndex, gridPane);
            if (node instanceof Button) {
                buttonsInRow[colIndex] = (Button) node;
            }
        }
        if (buttonsInRow[0] != null && buttonsInRow[1] != null && buttonsInRow[2] != null
                && buttonsInRow[0].getGraphic() != null && buttonsInRow[1].getGraphic() != null && buttonsInRow[2].getGraphic() != null) {
            ImageView imageView1 = (ImageView) buttonsInRow[0].getGraphic();
            ImageView imageView2 = (ImageView) buttonsInRow[1].getGraphic();
            ImageView imageView3 = (ImageView) buttonsInRow[2].getGraphic();
            if (imageView1.getImage().equals(imageView2.getImage()) && imageView1.getImage().equals(imageView3.getImage())) {
                for (Button button : buttonsInRow) {
                    button.setStyle("-fx-background-color: yellow;");
                }
            }
        }
    }

    private static void highlightAlignedButtonsInColumn(GridPane gridPane, int colIndex) {
        Button[] buttonsInColumn = new Button[gridPane.getRowCount()];
        for (int rowIndex = 0; rowIndex < gridPane.getRowCount(); rowIndex++) {
            Node node = getNodeByRowColumnIndex(rowIndex, colIndex, gridPane);
            if (node instanceof Button) {
                buttonsInColumn[rowIndex] = (Button) node;
            }
        }
        if (buttonsInColumn[0] != null && buttonsInColumn[1] != null && buttonsInColumn[2] != null
                && buttonsInColumn[0].getGraphic() != null && buttonsInColumn[1].getGraphic() != null && buttonsInColumn[2].getGraphic() != null) {
            ImageView imageView1 = (ImageView) buttonsInColumn[0].getGraphic();
            ImageView imageView2 = (ImageView) buttonsInColumn[1].getGraphic();
            ImageView imageView3 = (ImageView) buttonsInColumn[2].getGraphic();
            if (imageView1.getImage().equals(imageView2.getImage()) && imageView1.getImage().equals(imageView3.getImage())) {
                for (Button button : buttonsInColumn) {
                    button.setStyle("-fx-background-color: yellow;");
                }
            }
        }
    }



    /**
     * Vérifie si deux boutons sont voisins dans la grille.
     *
     * @param button1 Le premier bouton.
     * @param button2 Le deuxième bouton.
     * @return true si les boutons sont voisins, sinon false.
     */
    public static boolean isNeighbourButton(Button button1, Button button2) {
        GridPane gridPane = (GridPane) button1.getParent();
        Integer rowIndex1 = GridPane.getRowIndex(button1);
        Integer colIndex1 = GridPane.getColumnIndex(button1);
        Integer rowIndex2 = GridPane.getRowIndex(button2);
        Integer colIndex2 = GridPane.getColumnIndex(button2);

        // Vérifier si les boutons sont dans les mêmes colonnes
        if (colIndex1.equals(colIndex2)) {
            // Parcourir les lignes entre les deux boutons
            int startRow = Math.min(rowIndex1, rowIndex2);
            int endRow = Math.max(rowIndex1, rowIndex2);
            for (int row = startRow + 1; row < endRow; row++) {
                if (row == 3 && colIndex1 == 3) {
                    return false; // Arrêter le scan si la coordonnée (3,3) est un mur
                }
                Node node = getNodeByRowColumnIndex(row, colIndex1, gridPane);
                if (node instanceof Button) {
                    return false; // Il y a un bouton entre les deux, donc ils ne sont pas voisins
                }
            }
            return true;
        }
        // Vérifier si les boutons sont dans les mêmes lignes
        else if (rowIndex1.equals(rowIndex2)) {
            // Parcourir les colonnes entre les deux boutons
            int startCol = Math.min(colIndex1, colIndex2);
            int endCol = Math.max(colIndex1, colIndex2);
            for (int col = startCol + 1; col < endCol; col++) {
                if (rowIndex1 == 3 && col == 3) {
                    return false; // Arrêter le scan si la coordonnée (3,3) est un mur
                }
                Node node = getNodeByRowColumnIndex(rowIndex1, col, gridPane);
                if (node instanceof Button) {
                    return false; // Il y a un bouton entre les deux, donc ils ne sont pas voisins
                }
            }
            return true;
        }

        return false;
    }
    /**
     * Obtient le nœud situé à la ligne et à la colonne spécifiées dans une grille.
     *
     * @param row       L'index de la ligne.
     * @param column    L'index de la colonne.
     * @param gridPane  La grille contenant les nœuds.
     * @return Le nœud à la position spécifiée.
     */
    static Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }
}
