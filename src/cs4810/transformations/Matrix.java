package cs4810.transformations;

/**
 * Represents a 4x4 matrix for use in 3D transformations.
 * @author carroll
 *
 */
public class Matrix {

	public double[][] data = new double[4][4];
	
	/**
	 * Constructor creates the 4x4 identity matrix.
	 */
	public Matrix() {
		data[0][0] = 1;
		data[0][1] = 0;
		data[0][2] = 0;
		data[0][3] = 0; 
		data[1][0] = 0;
		data[1][1] = 1;
		data[1][2] = 0;
		data[1][3] = 0;
		data[2][0] = 0;
		data[2][1] = 0;
		data[2][2] = 1;
		data[2][3] = 0;
		data[3][0] = 0;
		data[3][1] = 0;
		data[3][2] = 0;
		data[3][3] = 1;
	}//identity constructor
	
	/**
	 * Prints the data of the matrix in 4 rows.
	 */
	public void print() {
		for(int row = 0; row < 4; row++) {
			for(int col = 0; col < 4; col++) {
				System.out.print(data[row][col] + " ");
			}//for col
			System.out.println();
		}//for row
	}//print
	
	
	/**
	 * Multiplies a matrix by the input and sets matrix data to the result.
	 * Handles 4x4 matrices only.
	 * @param m The matrix to multiply by.
	 */
	public void multiply(Matrix m){
		Matrix n = new Matrix();
		
		//make temp matrix to store resultant info
		n.data[0][0] = (data[0][0] * m.data[0][0]) + (data[0][1] * m.data[1][0]) + (data[0][2] * m.data[2][0]) + (data[0][3] * m.data[3][0]);
		n.data[0][1] = (data[0][0] * m.data[0][1]) + (data[0][1] * m.data[1][1]) + (data[0][2] * m.data[2][1]) + (data[0][3] * m.data[3][1]);
		n.data[0][2] = (data[0][0] * m.data[0][2]) + (data[0][1] * m.data[1][2]) + (data[0][2] * m.data[2][2]) + (data[0][3] * m.data[3][2]);
		n.data[0][3] = (data[0][0] * m.data[0][3]) + (data[0][1] * m.data[1][3]) + (data[0][2] * m.data[2][3]) + (data[0][3] * m.data[3][3]);
		n.data[1][0] = (data[1][0] * m.data[0][0]) + (data[1][1] * m.data[1][0]) + (data[1][2] * m.data[2][0]) + (data[1][3] * m.data[3][0]);
		n.data[1][1] = (data[1][0] * m.data[0][0]) + (data[1][1] * m.data[1][0]) + (data[1][2] * m.data[2][0]) + (data[1][3] * m.data[3][0]);
		n.data[1][2] = (data[1][0] * m.data[0][0]) + (data[1][1] * m.data[1][0]) + (data[1][2] * m.data[2][0]) + (data[1][3] * m.data[3][0]);
		n.data[1][3] = (data[1][0] * m.data[0][0]) + (data[1][1] * m.data[1][0]) + (data[1][2] * m.data[2][0]) + (data[1][3] * m.data[3][0]);
		n.data[2][0] = (data[2][0] * m.data[0][0]) + (data[2][1] * m.data[1][0]) + (data[2][2] * m.data[2][0]) + (data[2][3] * m.data[3][0]);
		n.data[2][1] = (data[2][0] * m.data[0][0]) + (data[2][1] * m.data[1][0]) + (data[2][2] * m.data[2][0]) + (data[2][3] * m.data[3][0]);
		n.data[2][2] = (data[2][0] * m.data[0][0]) + (data[2][1] * m.data[1][0]) + (data[2][2] * m.data[2][0]) + (data[2][3] * m.data[3][0]);
		n.data[2][3] = (data[2][0] * m.data[0][0]) + (data[2][1] * m.data[1][0]) + (data[2][2] * m.data[2][0]) + (data[2][3] * m.data[3][0]);
		n.data[3][0] = (data[3][0] * m.data[0][0]) + (data[3][1] * m.data[1][0]) + (data[3][2] * m.data[2][0]) + (data[3][3] * m.data[3][0]);
		n.data[3][1] = (data[3][0] * m.data[0][0]) + (data[3][1] * m.data[1][0]) + (data[3][2] * m.data[2][0]) + (data[3][3] * m.data[3][0]);
		n.data[3][2] = (data[3][0] * m.data[0][0]) + (data[3][1] * m.data[1][0]) + (data[3][2] * m.data[2][0]) + (data[3][3] * m.data[3][0]);
		n.data[3][3] = (data[3][0] * m.data[0][0]) + (data[3][1] * m.data[1][0]) + (data[3][2] * m.data[2][0]) + (data[3][3] * m.data[3][0]);			
			
		//transfer to current matrix
		data[0][0] = n.data[0][0];
		data[0][1] = n.data[0][1];
		data[0][2] = n.data[0][2];
		data[0][3] = n.data[0][3];
		data[1][0] = n.data[1][0];
		data[1][1] = n.data[1][1];
		data[1][2] = n.data[1][2];
		data[1][3] = n.data[1][3];
		data[2][0] = n.data[2][0];
		data[2][1] = n.data[2][1];
		data[2][2] = n.data[2][2];
		data[2][3] = n.data[2][3];
		data[3][0] = n.data[3][0];
		data[3][1] = n.data[3][1];
		data[3][2] = n.data[3][2];
		data[3][3] = n.data[3][3];
	}//multiply	
	
}//Matrix