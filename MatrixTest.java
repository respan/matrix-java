package matrix;

import java.util.Arrays;
import org.junit.*;
import static org.junit.Assert.*;

public class MatrixTest
{

   /**
   * Test of plus method, of class Matrix.
   */
   @Test
   public void testPlus()
   {
     System.out.println("plus");
     double[][] array1 = {{1, 1, 2}, {6, 24, 120}};
     double[][] array2 = {{-1, 0, -1}, {-4, -21, -115}};
     double[][] result = {{0, 1, 1}, {2, 3, 5}};
     Matrix x = new Matrix(array1);
     Matrix instance = new Matrix(array2);
     assertEquals(true, instance.plus(x).equals(new Matrix(result)));

     //fail("The test case is a prototype.");
   }

   /**
   * Test of minus method, of class Matrix.
   */
   @Test
   public void testMinus()
   {
      System.out.println("minus");
      double[][] array1 = {{0, 1, 1}, {2, 3, 5}};
      double[][] array2 = {{8, 13, 21}, {34, 55, 89}};
      double[][] result = {{-8, -12, -20}, {-32, -52, -84}};
      Matrix x = new Matrix(array1);
      Matrix instance = new Matrix(array2);
      assertEquals(true, x.minus(instance).equals(new Matrix(result)));

      //fail("The test case is a prototype.");
   }

   /**
   * Test of multiply method, of class Matrix.
   */
   @Test
   public void testMultiply()
   {
      System.out.println("multiply");
      double[][] array1 = {{0, 2, 4, 6, 8}, {10, 12, 14, 16, 18},
                         {20, 22, 24, 26, 28}, {30, 32, 34, 36, 38}};
      double[][] array2 = {{1, 3, 5, 7}, {9, 11, 13, 15}, {17, 19, 21, 23},
                         {25, 27, 29, 31}, {33, 35, 37, 39}};
      double[][] result = {{500, 540, 580, 620}, {1350, 1490, 1630, 1770},
                         {2200, 2440, 2680, 2920}, {3050, 3390, 3730, 4070}};

      Matrix x = new Matrix(array1);
      Matrix instance = new Matrix(array2);
      Matrix expression_result = x.multiply(instance);
      assertEquals(true , expression_result.equals(new Matrix(result)));

      double[][] double_result = {{1000, 1080, 1160, 1240}, {2700, 2980, 3260, 3540},
                         {4400, 4880, 5360, 5840}, {6100, 6780, 7460, 8140}};
      assertEquals(true, expression_result.multiply(2).equals(new Matrix(double_result)));

      //fail("The test case is a prototype.");
   }

   /**
   * Test of negatate method, of class Matrix.
   */
   @Test
   public void testNegatate()
   {
      System.out.println("negatate");
      double[][] array = {{1, 10, -7}, {-21, -15, 9}};
      double[][] result = {{-1, -10, 7}, {21, 15, -9}};
      Matrix instance = new Matrix(array);
      instance.negatate();
      assertEquals(true, instance.equals(new Matrix(result)));

      //fail("The test case is a prototype.");
   }

   /**
   * Test of setZero method, of class Matrix.
   */
   @Test
   public void testSetZero()
   {
      System.out.println("setZero");
      double[][] array = {{1, 0, -7}, {111, -15, 9}};
      double[][] result = {{0, 0, 0}, {0, 0, 0}};
      Matrix instance = new Matrix(array);
      instance.setZero();
      assertEquals(true, instance.equals(new Matrix(result)));

      //fail("The test case is a prototype.");
   }

   /**
   * Test of toString method, of class Matrix.
   */
   @Test
   public void testToString()
   {
      System.out.println("toString");
      double[][] array = {{1, 1, 2}, {1, 0, 3}, {5, -2, 3}};
      String result = "1.0 1.0 2.0 1.0 0.0 3.0 5.0 -2.0 3.0";
      Matrix instance = new Matrix(array);
      assertEquals(true, instance.toString().equals(result));
      //fail("The test case is a prototype.");
   }

   /**
   * Test of transpose method, of class Matrix.
   */
   @Test
   public void testTranspose()
   {
      System.out.println("transpose");
      double[][] array1 = {{1, 0, 5}, {2, -1, 3}};
      double[][] result = {{1, 2}, {0, -1}, {5, 3}};
      Matrix instance = new Matrix(array1);
      assertEquals(true, instance.transpose().equals(new Matrix(result)));

      double[][] array2 = {{1, 0}, {2, -1}};
      Matrix x = new Matrix(array2);
      assertEquals(false, x.transpose().equals(new Matrix(array2)));
      //fail("The test case is a prototype.");
   }

   /**
   * Test of getElement method, of class Matrix.
   */
   @Test
   public void testGetElement()
   {
      System.out.println("getElement");
      double[][] array = {{111, 222, 333}, {444, 555, 666}, {777, 888, 999}};
      Matrix instance = new Matrix(array);
      assertEquals(666, instance.getElement(1, 2), 0.0);

      //fail("The test case is a prototype.");
   }

   /**
   * Test of getRowsNumber method, of class Matrix.
   */
   @Test
   public void testGetRowsNumber()
   {
      System.out.println("getRowsNumber");
      Matrix instance = new Matrix(32, 64);
      assertEquals(32, instance.getRowsNumber());

      //fail("The test case is a prototype.");
   }

   /**
   * Test of getColumnNumber method, of class Matrix.
   */
   @Test
   public void testGetColumnNumber()
   {
      System.out.println("getColumnNumber");
      Matrix instance = new Matrix(666);
      assertEquals(666, instance.getColumnNumber());

      //fail("The test case is a prototype.");
   }

   /**
   * Test of getRow method, of class Matrix.
   */
   @Test
   public void testGetRow()
   {
      System.out.println("getRow");
      double[][] array = {{1, 0, 1}, {1, 5, 0}, {20, 3, 1}};
      Matrix instance = new Matrix(array);
      assertEquals(true, Arrays.equals(array[2], instance.getRow(2)));

      //fail("The test case is a prototype.");
   }

   /**
   * Test of getColumn method, of class Matrix.
   */
   @Test
   public void testGetColumn()
   {
      System.out.println("getColumn");
      double[][] array = {{2, 6, 10}, {-1, -7, -13}, {0, 0, 0}};
      double[] result = {6, -7, 0};
      Matrix instance = new Matrix(array);
      assertEquals(true, Arrays.equals(result, instance.getColumn(1)));

      //fail("The test case is a prototype.");
   }

   /**
   * Test of setElement method, of class Matrix.
   */
   @Test
   public void testSetElement()
   {
      System.out.println("setElement");
      double[][] array = {{4, 0, 15}, {16, 23, 0}};
      double[][] result = {{4, 8, 15}, {16, 23, 42}};
      Matrix instance = new Matrix(array);
      instance.setElement(0, 1, 8);
      instance.setElement(1, 2, 42);
      assertEquals(true, instance.equals(new Matrix(result)));

      //fail("The test case is a prototype.");
   }

   /**
   * Test of setRow method, of class Matrix.
   */
   @Test
   public void testSetRow()
   {
      System.out.println("setRow");
      double[][] array = {{0, 0, 0}, {1, 1, 1}, {1, 1, 1}};
      double[] row1 = {2, -2, 2};
      double[] row2 = {5, -7, 2};
      double[] row3 = {34, -56, 78};
      double[][] result = {{2, -2, 2}, {5, -7, 2}, {34, -56, 78}};
      Matrix instance = new Matrix(array);
      instance.setRow(0, row1);
      instance.setRow(1, row2);
      instance.setRow(2, row3);
      assertEquals(true, instance.equals(new Matrix(result)));

      //fail("The test case is a prototype.");
   }

   /**
   * Test of setColumn method, of class Matrix.
   */
   @Test
   public void testSetColumn()
   {
      System.out.println("setColumn");
      double[][] array = {{0, 1, 1}, {0, 1, 1}, {0, 1, 1}};
      double[] column1 = {2, -2, 2};
      double[] column2 = {5, 0, 3};
      double[] column3 = {6, 8, 12};
      double[][] result = {{2, 5, 6}, {-2, 0, 8}, {2, 3, 12}};
      Matrix instance = new Matrix(array);
      instance.setColumn(0, column1);
      instance.setColumn(1, column2);
      instance.setColumn(2, column3);
      assertEquals(true, instance.equals(new Matrix(result)));

      //fail("The test case is a prototype.");
   }

   /**
   * Test of random method, of class Matrix.
   */
   @Test
   public void testRandom()
   {
      System.out.println("random");
      Matrix instance = new Matrix(2, 3);
      instance.random().printf("%1.1f ");

      //fail("The test case is a prototype.");
   }

   /**
   * Test of print method, of class Matrix.
   */
   @Test
   public void testPrint()
   {
      System.out.println("print");
      double[][] array = {{2, 4, 8, 16, 32 }, {64, 128, 256, 512, 1024}};
      Matrix instance = new Matrix(array);
      instance.print();

      //fail("The test case is a prototype.");
   }

   /**
   * Test of printf method, of class Matrix.
   */
   @Test
   public void testPrintf()
   {
      System.out.println("printf");
      double[][] array = {{2.22, 4, 8, 16.1616, 32 }, {64, 128, 256.256, 512, 1024.1024}};
      Matrix instance = new Matrix(array);
      instance.printf("%1.0f ");

      //fail("The test case is a prototype.");
   }

   /**
   * Test of determinant method, of class Matrix.
   */
   @Test
   public void testDeterminant()
   {
      System.out.println("determinant");
      double[][] array = {{1, 1, 2}, {1, 0, 3}, {5, 2, 3}};
      Matrix instance = new Matrix(array);
      assertEquals(10, instance.determinant(), 0);

      //fail("The test case is a prototype.");
   }


   /**
   * Test of inverse method, of class Matrix.
   */
   @Test
   public void testInverse()
   {
      System.out.println("inverse");
      double[][] array1 = {{1, -2, 0, 4, 5}, {-6, -7, 8, 9, 10},
                         {666, 777, 666, 777, 1024}, {777, 666, 777, 666, 777},
                         {1024, 777, 666, 777, 666}};
      double[][] array2 = {{134, 256, 378, 490, 245}, {12, 13, 14, 15, 16},
                         {333, 666, 777, 888, 0}, {-123, -234, -345, -456, -567},
                         {1024, 2048, 5000, 7000, 1000000000}};

      Matrix x = new Matrix(array1);
      Matrix instance = new Matrix(array2);
      Matrix expression_result = x.multiply(instance);
      Matrix y = x.inverse();
      assertEquals(true, y.multiply(expression_result).equals(instance));
      //fail("The test case is a prototype.");
   }

   /**
    * Test of fastMultiply method, of class Matrix.
    * @throws InterruptedException
   */
   @Test
   public void testFastMultiply() throws InterruptedException
   {
      System.out.println("fastMultiply");
      double[][] array1 = {{0, 2, 4, 6, 8}, {10, 12, 14, 16, 18},
                         {20, 22, 24, 26, 28}, {30, 32, 34, 36, 38}};
      double[][] array2 = {{1, 3, 5, 7}, {9, 11, 13, 15}, {17, 19, 21, 23},
                         {25, 27, 29, 31}, {33, 35, 37, 39}};
      double[][] result = {{500, 540, 580, 620}, {1350, 1490, 1630, 1770},
                         {2200, 2440, 2680, 2920}, {3050, 3390, 3730, 4070}};
      Matrix x = new Matrix(array1);
      Matrix instance = new Matrix(array2);
      Matrix expression_result;
      expression_result = x.fastMultiply(instance, 1);
      assertEquals(true , expression_result.equals(new Matrix(result)));

      long s1, s2, s3, s4, s5, s6;
      x = new Matrix(400);
      instance = new Matrix(400);
      x.random();
      instance.random();

      s1 = System.currentTimeMillis();
      expression_result = x.fastMultiply(instance, 1);
      s2 = System.currentTimeMillis();
      expression_result = x.fastMultiply(instance, 2);
      s3 = System.currentTimeMillis();
      expression_result = x.fastMultiply(instance, 3);
      s4 = System.currentTimeMillis();
      expression_result = x.fastMultiply(instance, 4);
      s5 = System.currentTimeMillis();
      expression_result = x.fastMultiply(instance, 100);
      s6 = System.currentTimeMillis();

      System.out.println("1 thread: " + (s2 - s1));
      System.out.println("2_threads: " + (s3 - s2));
      System.out.println("3_threads: " + (s4 - s3));
      System.out.println("4_threads: " + (s5 - s4));
      System.out.println("100_threads: " + (s6 - s5));

      //fail("The test case is a prototype.");
   }
}
