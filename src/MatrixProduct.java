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
    ////////////////////STRASSEN HERE ///////////////////
    public static int[][] matrixProduct_Strassen(int[][] A, int[][] B) {
        //Compute and return the product of A, B matrices using “simple” DAC algorithm presented in class.
//        int[][] C;
        if(validityCheck(A,B))
        {
//            C = new int[A.length][A.length];
            int[][] C = multiply_s(A, B);
            return C;

        }
        else
        {
            throw new IllegalArgumentException();
            //System.exit(1);
        }
    }


    public static int[][] multiply_s(int[][] A, int[][] B) {
        //Compute and return the product of A, B matrixes using Strassen’s algorithm presented in class.

        int n = A.length; //length of one side
        int[][] C_strassen = new int[n][n];

        //C has one element
        if (n==1)
        {
            C_strassen[0][0] = A[0][0] * B[0][0];
        }
        else
        {
            //Matrix A
            int[][] a = new int[n/2][n/2]; //A11
            int[][] b = new int[n/2][n/2]; //A12
            int[][] c = new int[n/2][n/2]; //A21
            int[][] d = new int[n/2][n/2]; //A22
            //Matrix B
            int[][] e = new int[n/2][n/2]; //B11
            int[][] f = new int[n/2][n/2]; //B12
            int[][] g = new int[n/2][n/2]; //B21
            int[][] h = new int[n/2][n/2]; //B22
            //Matrix C
            int[][] C11 = new int[n/2][n/2]; //B11
            int[][] C12 = new int[n/2][n/2]; //B12
            int[][] C21 = new int[n/2][n/2]; //B21
            int[][] C22 = new int[n/2][n/2]; //B22

            //spliting matrix A
            splitMat(A, a, 0, 0);
            splitMat(A, b, 0, n/2);
            splitMat(A, c, n/2, 0);
            splitMat(A, d, n/2, n/2);

            //spliting matrix B
            splitMat(A, e, 0, 0);
            splitMat(A, f, 0, n/2);
            splitMat(A, g, n/2, 0);
            splitMat(A, h, n/2, n/2);

            //matrixes for calculation purposes
            int[][] p1 = new int[n/2][n/2];
            int[][] p2 = new int[n/2][n/2];
            int[][] p3 = new int[n/2][n/2];
            int[][] p4 = new int[n/2][n/2];
            int[][] p5 = new int[n/2][n/2];
            int[][] p6 = new int[n/2][n/2];
            int[][] p7 = new int[n/2][n/2];

            p1 = multiply_s(addMat(a,d), addMat(e,h));
            p2 = multiply_s(addMat(c,d),e);
            p3 = multiply_s(a, subMat(f, h));
            p4 = multiply_s(d, subMat(g, e));
            p5 = multiply_s(addMat(a,b), h);
            p6 = multiply_s(subMat(c, a), addMat(e,f));
            p7 = multiply_s(subMat(b,d), addMat(g,h));

            C11 = addMat(subMat(addMat(p1, p4), p5), p7);
            C12 = addMat(p3, p5);
            C21 = addMat(p2, p4);
            C22 = addMat(subMat(addMat(p1, p3), p2), p6);

            joinMat(C_strassen, C11, 0, 0);
            joinMat(C_strassen, C12, 0, n/2);
            joinMat(C_strassen, C21, n/2, 0);
            joinMat(C_strassen, C22, n/2, n/2);

        }

        return C_strassen;

    }

    //subtract two matrixes of the same size
    private static int[][] subMat(int[][] A, int[][] B)
    {
        int len = A.length;
        int[][] ret = new int[len][len];

        for(int i = 0; i < len; i++)
        {
            for(int j = 0; j < len; j++)
            {
                //System.out.print(A[i][j]+" + "+B[i][j]+" = ");
                ret[i][j] = A[i][j] - B[i][j];
                //System.out.print(ret[i][j]+" ");
            }
            //System.out.println();
        }
        //System.out.println();
        return ret;

    }

    //split parent matrix into child matrix
    public static void splitMat(int[][] P, int[][] C, int row_p, int col_p)
    {
        for (int i1 = 0, i2 = row_p; i1 < C.length; i1++, i2++)
            for (int j1 = 0, j2 = col_p; j1 < C.length; j1++, j2++)
            {
                C[i1][j1] = P[i2][j2];
            }
    }

    //join arrays to make the big boi C
    public static void joinMat(int [][] P, int[][] C, int row_p, int col_p)
    {
        for (int i1 = 0, i2 = row_p; i1 < C.length; i1++, i2++)
            for (int j1 = 0, j2 = col_p; j1 < C.length; j1++, j2++)
            {
                P[i2][j2] = C[i1][j1];
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
        //checking if they are square, if (column != row)
        if (A[0].length != A.length)
        {
            System.err.println("A is not a square");
        }

        return true;
    }
}
