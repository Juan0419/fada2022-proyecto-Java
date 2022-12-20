package proyecto;

import javax.naming.OperationNotSupportedException;

import lombok.Getter;
import lombok.Setter;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class SparseMatrixCSR {
    private LoadFile loader = LoadFile.getInstance();
    private int[][] matrix;
    @Getter
    @Setter
    private int[] rows;
    @Getter
    @Setter
    private int[] columns;
    @Getter
    @Setter
    private int[] values;

    public void createRepresentation(String inputFile) throws OperationNotSupportedException, FileNotFoundException {
        //Load data
        loader.loadFile(inputFile);
        matrix = loader.getMatrix();
        // Calcular el tamaño de la matriz de columnas
        int size = 0;
        for (int[] row : matrix) {
            for (int value : row) {
                if (value != 0) {
                    size++;
                }
            }
        }

        // Se inicializan las matrices de columnas, valores y filas
        columns = new int[size];
        values = new int[size];
        rows = new int[matrix.length + 1];

        // Llena las matrices de columnas, valores y filas
        size = 0;
        for (int i = 0; i < matrix.length; i++) {
            rows[i] = size;
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0) {
                    values[size] = matrix[i][j];
                    columns[size] = j;
                    size++;
                }
            }
        }
        rows[matrix.length] = size;
    }

    public int getElement(int i, int j) throws OperationNotSupportedException {
        /*int jb = rows[i];
        int pos = columns[jb];

        while (pos != j) {

            jb++;
            if (jb > columns.length) {
                pos = 0;
                break;
            }
        }
        return values[pos];*/
        throw new OperationNotSupportedException();
    }

    public int[] getRow(int i) throws OperationNotSupportedException {
        int maxCol = columns[0];

        for (int j : columns) {
            if (j > maxCol)
                maxCol = j;
        }
        int col_ini = rows[i];
        int[] filaAns = new int[maxCol + 1];


        for (int colAux = col_ini; colAux < rows[i + 1]; colAux++) {
            int var = columns[colAux];
            filaAns[var] = values[colAux];
        }
        return filaAns;
    }

    public int[] getColumn(int j) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    public void setValue(int i, int j, int value) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    /*
     * This method returns a representation of the Squared matrix
     * @return object that contests the squared matrix;
     */
    public SparseMatrixCSR getSquareMatrix() throws OperationNotSupportedException {
        SparseMatrixCSR squaredMatrix = new SparseMatrixCSR();

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
    public SparseMatrixCSR getTransposedMatrix() throws OperationNotSupportedException {
        SparseMatrixCSR squaredMatrix = new SparseMatrixCSR();
        throw new OperationNotSupportedException();
    }

}
