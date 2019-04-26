public class Tester {
    public static void main(String[] args)
    {

        int[][] m = new int[4][4];
        m[0][0] = 1;
        m[0][1] = 2;
        m[0][2] = 3;
        m[0][3] = 4;

        m[1][0] = 5;
        m[1][1] = 6;
        m[1][2] = 7;
        m[1][3] = 8;

        m[2][0] = 9;
        m[2][1] = 10;
        m[2][2] = 11;
        m[2][3] = 12;

        m[3][0] = 13;
        m[3][1] = 14;
        m[3][2] = 15;
        m[3][3] = 16;


        int[][] n = new int[4][4];
        n[0][0] = 8;
        n[0][1] = 1;
        n[0][2] = 1;
        n[0][3] = 1;

        n[1][0] = 1;
        n[1][1] = 1;
        n[1][2] = 1;
        n[1][3] = 1;

        n[2][0] = 1;
        n[2][1] = 1;
        n[2][2] = 1;
        n[2][3] = 1;

        n[3][0] = 1;
        n[3][1] = 1;
        n[3][2] = 1;
        n[3][3] = 1;

        int[][] c = MatrixProduct.matrixProduct_DAC(m,n);

        for(int i = 0; i < c.length; i ++)
        {
            for(int j = 0; j < c.length; j++)
            {
                System.out.print(c[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
        int [][] c_s = MatrixProduct.matrixProduct_Strassen(m,n);
        for(int i = 0; i < c.length; i ++)
        {
            for(int j = 0; j < c.length; j++)
            {
                System.out.print(c[i][j] + " ");
            }
            System.out.println();
        }

//        int[][] w = new int[2][2];
//        w[0][0] = 1;
//        w[0][1] = 2;
//
//        w[1][0] = 5;
//        w[1][1] = 6;
//
//        int[][] k = new int[2][2];
//        k[0][0] = 3;
//        k[0][1] = 4;
//
//        k[1][0] = 7;
//        k[1][1] = 8;
//
//
//        int[][] cc = MatrixProduct.matrixProduct_DAC(w,k);
//        for(int i = 0; i < cc.length; i ++)
//        {
//            for(int j = 0; j < cc.length; j++)
//            {
//                System.out.print(cc[i][j] + " ");
//            }
//            System.out.println();
//        }

    }
}

