package proyecto;

import java.sql.SQLOutput;
import java.util.*;
import javax.naming.OperationNotSupportedException;

import lombok.Getter;
import lombok.Setter;

import java.io.FileNotFoundException;

public class SparseMatrixCoordinateFormat {

    private LoadFile loader = LoadFile.getInstance();


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

    private int size_row;
    private int size_column;
    //private Map<String, Integer> elem;
    //private int valDef;

    public void createRepresentation(String inputFile) throws OperationNotSupportedException, FileNotFoundException {
        //Load data
        loader.loadFile(inputFile);
        matrix = loader.getMatrix();

        ArrayList<Integer> value = new ArrayList<Integer>();
        ArrayList<Integer> row = new ArrayList<Integer>();
        ArrayList<Integer> column = new ArrayList<Integer>();

        size_row = matrix.length;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                size_column = matrix[i].length;
                if (matrix[i][j] != 0) {
                    value.add(matrix[i][j]);
                    row.add(i);
                    column.add(j);
                }
            }
        }

        values = new int[value.size()];
        rows = new int[value.size()];
        columns = new int[value.size()];

        for (int i = 0; i < values.length; i++) {
            values[i] = value.get(i);

            rows[i] = row.get(i);

            columns[i] = column.get(i);
        }

    }

    public int getElement(int i, int j) throws OperationNotSupportedException {
        boolean prueba = false; // Variable usada para que sea una prueba si la columna y la fila coinciden en el mismo dato

        for (int k = 0; k < rows.length; k++) { //Recorrido de las filas
            if (this.rows[k] == i && this.columns[k] == j) { // Compara si coinciden con los datos dados de las columnas
                prueba = true;
                return values[k];
            }
        }
        if (prueba == false) { // Si el dato está en falso, es porque no lo encontró
            // Por ende retorna cero
            return 0; // Al no encontrar el elemento, devuelve cero
        }
            /*int c = representacion[0][i];
        while (c < j) {
            c++;
        }
        return representacion[2][c];*/
        throw new OperationNotSupportedException();
    }


    public int[] getRow(int fila) throws OperationNotSupportedException {
        // Se crea un array para almacenar la fila resultante
        int[] resultante = new int[size_column];
        // Se inicializa todos los valores del array a 0
        Arrays.fill(resultante, 0);
        // Se recorre los vectores de la representación
        for (int i = 0; i < rows.length; i++) {
            // Si el vector corresponde a la fila deseada, sumamos su valor en la posición correspondiente
            if (rows[i] == fila) {
                resultante[columns[i]] = getElement(fila, columns[i]);
            }
        }
        return resultante;
    }

    public int[] getColumn(int j) throws OperationNotSupportedException {
        ArrayList<Integer> columns1 = new ArrayList<Integer>();
        ArrayList<Integer> resultante = new ArrayList<Integer>(8);

        int result[] = new int[size_row];

        for (int i = 0; i < result.length; i++) {
            result[i] = 0;
        }
        for (int i = 0; i < rows.length; i++) {
            if (columns[i] == j) {
                columns1.add(this.rows[i]);
            }
        }
        for (int i = 0; i < result.length; i++) {
            for (Integer elemento : columns1) {
                if (i == elemento) {
                    result[i] = this.getElement(elemento, j);
                }
            }
        }
        return result;
    }

    public void setValue(int i, int j, int value) throws OperationNotSupportedException {


        /*if (value == 0) {
            return;
        }
        Triplet triplets = new Triplet(i, j, value);

        for (int k = 0; k < triplets.size(); k++) {
            Triplet triplet = triplets.get(k);
            if (triplet.row == i && triplet.col == j) {
                // Si se encuentra una tripleta con la fila y columna especificadas, se actualiza su valor
                triplet.value = value;
                return;*/
        throw new OperationNotSupportedException();
    }


    /*public SparseMatrixCoordinateFormat(int valorPorDefecto) {
        this.elem = new HashMap<>();
        this.valDef = valorPorDefecto;
    }

    public void setValue(int i, int j, int value) throws OperationNotSupportedException {
        if (value == valDef) {
            elem.remove(getKey(i,j));
        } else {
            elem.put(getKey(i,j),value);
        }
        System.out.println(elem);
    }

    private String getKey(int fila, int columna) {
        return fila + "," + columna;
    }*/


    /*
     * This method returns a representation of the Squared matrix
     * @return object that contests the squared matrix;
     */
    public SparseMatrixCoordinateFormat getSquareMatrix() throws OperationNotSupportedException {
        SparseMatrixCoordinateFormat squaredMatrix = new SparseMatrixCoordinateFormat();

        for (int i = 0; i < this.rows.length; i++) {
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
    public SparseMatrixCoordinateFormat getTransposedMatrix() throws OperationNotSupportedException {
        SparseMatrixCoordinateFormat squaredMatrix = new SparseMatrixCoordinateFormat();

        int [][] newmat = new int[matrix[0].length][matrix.length]; // Creacion de nueva matriz donde se hara el cambio

        for (int j = 0; j < matrix[0].length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                newmat[j][i] =matrix[i][j];
            }
        }
        squaredMatrix.setMatrix(newmat); // Recorrido de matriz y llenado de la nueva con diferentes valores

        int numcol=0;
        int numrow=0;
        int numelem=0; //Reinicia el array en el valor necesario
        int numvalue=0;

        for (int [] filas: newmat) {
            for (int elemento : filas) {
                if( elemento !=0){
                    numelem++;
                }
            }
        }
        //Numero de elementos creando los arrays con las capacidades correspondientes
        int[] nuevasfilas = new int[numelem];
        int[]  nuevasColumnas = new int[numelem];
        int[] nuevosValores = new int[numelem];


        for (int i = 0; i < newmat.length; i++) {
            for (int j = 0; j < newmat[0].length; j++) {
                if(newmat[i][j] != 0) {
                    // Llanado de la nueva matriz con el cambio de columnas por filas y filas por columnas
                    nuevasfilas[numrow] = i;
                    nuevasColumnas[numcol] = j;
                    nuevosValores[numvalue] = newmat[i][j];

                    numrow++;
                    numcol++;
                    numvalue++;
                }
            }
        }
        // Dando valores seteando cada valor
        squaredMatrix.setRows(nuevasfilas);
        squaredMatrix.setColumns(nuevasColumnas);
        squaredMatrix.setValues(nuevosValores);

        return squaredMatrix;
    }
}

    /*public class Triplet {
        @Setter
        @Getter
        public int row;
        @Setter
        @Getter
        public int col;
        @Setter
        @Getter
        public int value;
        private List<Triplet> triplets;

        public Triplet(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
            this.triplets = new ArrayList<>();
        }

        public List<Triplet> getTriplets() {
            return triplets;
        }

        public int size() {
            return triplets.size();
        }


    }

}*/



