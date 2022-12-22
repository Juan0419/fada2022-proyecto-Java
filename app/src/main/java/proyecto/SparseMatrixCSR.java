package proyecto;

import javax.naming.OperationNotSupportedException;

import lombok.Getter;
import lombok.Setter;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class SparseMatrixCSR {
    private LoadFile loader = LoadFile.getInstance();
    @Getter
    @Setter
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
        // Revisa si la fila esta vacia
        if (rows[i] == rows[i + 1]) {
            return 0;
        }

        // Revisa si el elemnto existe en la fila
        for (int column = rows[i]; column < rows[i + 1]; column++) {
            if (columns[column] == j) {
                return values[column];
            }
        }

        // Si el elemento no se encuentra retorna cero
        return 0;
    }

    public int[] getRow(int i) throws OperationNotSupportedException {
        int maxCol = columns[0]; // Inicializando la max con el primer valor de columns

        for (int j : columns) {
            if (j > maxCol)
                maxCol = j; // Si el valor de j es mayor que maxcol se actualiza maxcol
        }
        int col_ini = rows[i];
        int[] filaGet = new int[maxCol + 1];


        for (int colAux = col_ini; colAux < rows[i + 1]; colAux++) {
            int var = columns[colAux]; // en la posicion  de la variable del for se inicializa la variable
            filaGet[var] = values[colAux]; // Se asigna el valor de este elemnto en la posicion auxiliar
        }
        return filaGet;
    }

    public int[] getColumn(int j) throws OperationNotSupportedException {
        int[] arrCol = new int[rows.length - 1]; // Definiendo un arreglo para guardar columnas

        for (int i = 0; i < rows.length - 1; i++) {
            arrCol[i] = 0; // Inicializa el elemento en cero

            for (int k = rows[i]; k < rows[i + 1]; k++) {
                if (columns[k] == j) {
                    arrCol[i] = values[k];
                    break; // sale del ciclo interno una vez que encuentra un elemento no nulo
                }
            }
        }
        return arrCol;
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
    public SparseMatrixCSR getTransposedMatrix() throws OperationNotSupportedException{ // Profe este es el metodo que dijimos que faltaba el test implementado en python
        SparseMatrixCSR transposedMatrix = new SparseMatrixCSR();
        // Se usa la matriz instanciada anteriormente para setear los valores
        transposedMatrix.setValues(values);
        transposedMatrix.setColumns(columns);

        // Crea un nuevo arreglo, uno de filas para la matriz transpuesta
        int[] transposedRows = new int[matrix[0].length + 1];

        int rowI = 0; // Indices filas
        int valueI = 0; // Indices valores
        for (int j = 0; j < matrix[0].length; j++) {
            transposedRows[rowI] = valueI;
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][j] != 0) {
                    values[valueI] = matrix[i][j];
                    columns[valueI] = i;
                    valueI++;
                }
            }
            rowI++;
        }
        transposedRows[rowI] = valueI;

        transposedMatrix.setRows(transposedRows);

        return transposedMatrix;
    }

}
