package proyecto;

import javax.naming.OperationNotSupportedException;

import lombok.Getter;
import lombok.Setter;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SparseMatrixCSC {
    private LoadFile loader = LoadFile.getInstance();
    private int[][] matrix;
    @Setter
    @Getter
    private int[] rows;
    @Setter
    @Getter
    private int[] columns;
    @Setter
    @Getter
    private int[] values;

    public void createRepresentation(String inputFile) throws OperationNotSupportedException, FileNotFoundException {
        //Load data
        loader.loadFile(inputFile);
        matrix = loader.getMatrix();

        int size = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0) {
                    size++; //Recorrido de la matriz para conocer su tamaño
                }
            }
        }
        values = new int[size];
        rows = new int[size];
        columns = new int[matrix[0].length + 1];

        int idx = 0;
        for (int j = 0; j < matrix[0].length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][j] != 0) {
                    values[idx] = matrix[i][j]; // Va llenando la matriz deacuerdo con el indice recorrido por los ciclos
                    rows[idx] = i; //Filas llenandose deacuerdo al indice
                    idx++;
                }
            }
            columns[j + 1] = idx; // se suma 1 en columnas para que no sobreescriba con el indice recorrido
        }
    }

    public int getElement(int i, int j) throws OperationNotSupportedException {
        // Revisa si la columna esta vacia
        if (columns[j] == columns[j + 1]) {
            return 0;
        }

        // Revisa si el elemnto existe en la columna
        for (int row = columns[j]; row < columns[j + 1]; row++) {
            if (rows[row] == i) {
                return values[row];
            }
        }

        // Si el elemento no se encuentra retorna cero
        return 0;
    }

    public int[] getRow(int i) throws OperationNotSupportedException {
        int[] row = new int[columns.length - 1];
        boolean exist = false; // Se evaluea si existe la fila

        for (int r : rows) { // Recorrido y evaluacion de existencia de las filas
            if (r == i) {
                exist = true;
            }
        }
        if (exist) { // Evaluacion de obtencion de resultados de los recorridos de las filas
            for (int j = 0; j < row.length; j++) {
                if (columns[j] != columns[j + 1]) {
                    for (int r = columns[j]; r < columns[j + 1]; r++) {
                        if (rows[r] == i) {
                            row[j] = values[r];
                        }
                    }
                }
            }
        }
        return row;
    }

    public int[] getColumn(int j) throws OperationNotSupportedException {
        int[] col = new int[matrix.length];

        // Evaluacion  de  la fila row  la cual es la posición del arreglo rows desde donde empieza la columna
        if (columns[j] != columns[j + 1]) {
            for (int row = columns[j]; row < columns[j + 1]; row++) {
                col[rows[row]] = values[row];
            }
        }
        return col;
    }

    public void setValue(int i, int j, int value) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    /*
     * This method returns a representation of the Squared matrix
     * @return object that contests the squared matrix;
     */
    public SparseMatrixCSC getSquareMatrix() throws OperationNotSupportedException {
        SparseMatrixCSC squaredMatrix = new SparseMatrixCSC();
        for (int i = 0; i < this.values.length; i++) {
            this.values[i] = (int) Math.pow(this.values[i], 2);
        }

        squaredMatrix.setRows(this.rows);
        squaredMatrix.setColumns(this.columns);
        squaredMatrix.setValues(this.values);
        return squaredMatrix;
    }

    /*
     * This method returns a representation of the transposed matrix
     * @return object that contests the transposed matrix;
     */
    public SparseMatrixCSC getTransposedMatrix() throws OperationNotSupportedException {
        SparseMatrixCSC transposedMatrix = new SparseMatrixCSC();
        transposedMatrix.setValues(values);
        transposedMatrix.setRows(rows);

        // Crea un nuevo arreglo, uno de filas para la matriz transpuesta
        int[] transposedColumns = new int[matrix.length + 1];

        int columnI = 0; // Indices columnas
        int valueI = 0; // Indices valores
        for (int i = 0; i < matrix.length; i++) {
            transposedColumns[columnI] = valueI;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    values[valueI] = matrix[i][j];
                    rows[valueI] = j;
                    valueI++;
                }
            }
            columnI++;
        }

        transposedColumns[columnI] = valueI;

        transposedMatrix.setColumns(transposedColumns);

        return transposedMatrix;
    }
}
