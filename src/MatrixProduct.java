public class MatrixProduct {

    public static int[][] matrixProduct_DAC(int[][] A, int[][] B) {
        //Compute and return the product of A, B matrices using “simple” DAC algorithm presented in class.
        if(validityCheck(A,B))
        {

        }
        else
        {
            throw new IllegalArgumentException();
        }

    }
    public static int[][] matrixProduct_Strassen(int[][] A, int[][] B) {
        //Compute and return the product of A, B matrixes using Strassen’s algorithm presented in class.

        if(validityCheck(A,B))
        {

        }
        else
        {
            throw new IllegalArgumentException();
        }
    }


    private static boolean validityCheck(int[][] A, int[][]B)
    {
        if(A.length != A[0].length)
        {
            return false;
        }
        if(B.length != B[0].length)
        {
            return false;
        }
        if(A.length != B.length)
        {
            return false;
        }
        int check = A.length % 2;
        if(check !=0)
        {
            return false;
        }

        return true;
    }
}
