/*// Méthode pour vérifier si deux boutons sont voisins dans le GridPane
    private boolean isNeighbourButton(Button button1, Button button2) {
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
    }*/