package proyecto;

import javax.naming.OperationNotSupportedException;

import lombok.Getter;
import lombok.Setter;

import java.io.FileNotFoundException;

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
        throw new OperationNotSupportedException();
    }

    public int getElement(int i, int j) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    public int[] getRow(int i) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
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
    public SparseMatrixCSC getSquareMatrix() throws OperationNotSupportedException {
        SparseMatrixCSC squaredMatrix = new SparseMatrixCSC();
        /*for (int i = 0; i < this.values.length; i++) {
            this.values[i] = (int) Math.pow(this.values[i], 2);
        }

        squaredMatrix.setRows(this.rows);
        squaredMatrix.setColumns(this.columns);
        squaredMatrix.setValues(this.values);
        return squaredMatrix;*/
        throw new OperationNotSupportedException();
    }

    /*
     * This method returns a representation of the transposed matrix
     * @return object that contests the transposed matrix;
     */
    public SparseMatrixCSC getTransposedMatrix() throws OperationNotSupportedException {
        SparseMatrixCSC squaredMatrix = new SparseMatrixCSC();
        throw new OperationNotSupportedException();
    }
}
