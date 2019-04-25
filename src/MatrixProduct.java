public class MatrixProduct {

    private static int[][] addMat(int[][] A, int[][] B)
    {
        int len = A.length;
        int[][] ret = new int[len][len];

        for(int i = 0; i < len; i++)
        {
            for(int j = 0; j < len; j++)
            {
                //System.out.print(A[i][j]+" + "+B[i][j]+" = ");
                ret[i][j] = A[i][j] + B[i][j];
                //System.out.print(ret[i][j]+" ");
            }
            //System.out.println();
        }
        //System.out.println();
        return ret;

    }

    private static int[][] matProd(int[][] A, int startrowA, int startcolA, int[][] B, int startrowB, int startcolB, int n)
    {
        int[][] C = new int[n][n];


        if(n==1)
        {
            C[0][0] = A[startrowA][startcolA] * B[startrowB][startcolB]; //C has one element

        }
        else
        {

            //c11,c12,c21,c22 are pointers, NOT new matrices
            //System.out.print("C11: ");
            int[][] C11 = addMat(matProd(A, startrowA, startcolA, B, startrowB,startcolB,n/2), matProd(A, startrowA, startcolA+n/2, B, startrowB+n/2,startcolB,n/2));
            //System.out.print("C12: ");
            int[][] C21 = addMat(matProd(A, startrowA, startcolA, B, startrowB,startcolB+n/2,n/2), matProd(A, startrowA, startcolA+n/2, B, startrowB+n/2,startcolB+n/2,n/2));
            //System.out.print("C21: ");
            int[][] C12 = addMat(matProd(A, startrowA+n/2, startcolA, B, startrowB,startcolB,n/2), matProd(A, startrowA+n/2, startcolA+n/2, B, startrowB+n/2,startcolB,n/2));
            //System.out.print("C22: ");
            int[][] C22 = addMat(matProd(A, startrowA+n/2, startcolA, B, startrowB,startcolB+n/2,n/2), matProd(A, startrowA+n/2, startcolA+n/2, B, startrowB+n/2,startcolB+n/2,n/2));

            //fill C matrix with values from matrices pointed to by C11, C12, C21, C22


            for(int i = 0; i < n/2; i ++)
            {
                for (int j = 0; j < n/2; j++)
                {
                    C[i][j] = C11[i][j];
                }
            }

            int ctr1 = 0;
            int ctr2 = 0;

            for(int ii = n/2; ii < n; ii++)
            {
                for (int jj = 0; jj < n/2; jj++)
                {
                    C[ii][jj] = C12[ctr1][ctr2];
                    ctr2++;
                }
                ctr2 =0;
                ctr1++;
            }
            ctr1 = 0;
            ctr2 = 0;

            for(int k = 0; k < n/2; k ++)
            {
                for (int m = n/2; m < n; m++)
                {
                    C[k][m] = C21[ctr1][ctr2];
                    ctr2++;
                }
                ctr2 =0;
                ctr1++;
            }

            ctr1 = 0;
            ctr2 = 0;
            for(int p = n/2; p < n; p ++)
            {
                for (int q = n/2; q < n; q++)
                {
                    C[p][q] = C22[ctr1][ctr2];
                    ctr2++;
                }
                ctr2 =0;
                ctr1++;
            }


        }

        return C;
    }

    public static int[][] matrixProduct_DAC(int[][] A, int[][] B) {
        //Compute and return the product of A, B matrices using “simple” DAC algorithm presented in class.
//        int[][] C;
        if(validityCheck(A,B))
        {


//            C = new int[A.length][A.length];
            int[][] C = matProd(A, 0, 0, B, 0, 0, A.length);
            return C;

        }
        else
        {
            throw new IllegalArgumentException();
            //System.exit(1);
        }





    }
//    public static int[][] matrixProduct_Strassen(int[][] A, int[][] B) {
//        //Compute and return the product of A, B matrixes using Strassen’s algorithm presented in class.
//
//        if(validityCheck(A,B))
//        {
//
//        }
//        else
//        {
//            throw new IllegalArgumentException();
//        }
//    }


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
        //checking if they are square, if (column != row)
        if (A[0].length != A.length)
        {
            System.err.println("A is not a square");
        }

        return true;
    }
}
