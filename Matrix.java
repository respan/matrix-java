package matrix;

public class Matrix
{
   private class MultiplicationThread extends Thread
   {
      private Matrix a_;
      private Matrix b_;
      private Matrix c_;
      private int from_;
      private int to_;

      public MultiplicationThread(Matrix a, Matrix b, Matrix c, int from, int to)
      {
         this.a_ = a;
         this.b_ = b;
         this.c_ = c;
         this.from_ = from;
         this.to_ = to;
      }

      @Override
      public void run()
      {
         for(int l = from_; l <= to_; ++l)
         {
            int i = l / c_.column_;
            int j = l % c_.column_;
            for(int k = 0; k < a_.column_; ++k)
               c_.data_[i][j] += a_.data_[i][k] * b_.data_[k][j];            
         }
      }

   }
   private int row_;
   private int column_;
   private double[][] data_;

   public Matrix(int N)
   {
     row_ = N;
     column_ = N;
     data_ = new double[N][N];
   }

   public Matrix(int N, int M)
   {
     row_ = N;
     column_ = M;
     data_ = new double[N][M];
   }

   public Matrix(double[][] array)
   {
     row_ = array.length;
     column_ = array[0].length;
     data_ = new double[row_][column_];
     for(int i = 0; i < row_; i++)
         System.arraycopy(array[i], 0, data_[i], 0, column_);
   }

   public Matrix(Matrix x)
   {
     this(x.data_);
   }

   private Matrix operation(Matrix x, String c)
   {
     if(row_ != x.row_ || column_ != x.column_)
         throw new RuntimeException("Matrix size is illegal");
     Matrix y = new Matrix(row_, column_);
     for (int i = 0; i < row_; i++) {
      for (int j = 0; j < column_; j++)
      {
         y.data_[i][j] = c.equals("+") ? data_[i][j] + x.data_[i][j] : data_[i][j] - x.data_[i][j];
      }
   }
     return y;
   }

   public Matrix plus(Matrix x)
   {
     return this.operation(x, "+");
   }

   public Matrix minus(Matrix x)
   {
     return this.operation(x, "-");
   }


   public Matrix fastMultiply(Matrix x, int threads) throws InterruptedException
   {
      Matrix y = new Matrix(row_, x.column_);
      int elements = y.row_ * x.column_;
      int pace = elements / threads;

      MultiplicationThread thread = null;
      MultiplicationThread[] thr = new MultiplicationThread[threads];

      
      int elements_of_y = y.row_ * y.column_;
      for(int i = 0, z = 0; i < elements_of_y; i += pace + 1, z++)
      {
         int from = i;
         int to = i + pace;
         if(to >= elements_of_y) to = elements_of_y - 1;
         thread = new MultiplicationThread(this, x, y, from, to);
         thread.start();
         thr[z] = thread;
      }

      for(int i = 0; i < threads; ++i)
      {
         thr[i].join();
      }
      return y;

   }

   public Matrix multiply(Matrix x)
   {
     if(column_ != x.row_)
         throw new RuntimeException("number of columns != number of rows");
     Matrix y = new Matrix(row_, x.column_);
     for(int i = 0; i < y.row_; i++)
         for(int j = 0; j < y.column_; j++)
             for(int k = 0; k < column_; k++)
                 y.data_[i][j] += (data_[i][k] * x.data_[k][j]);
     return y;
   }

   public Matrix multiply(double x)
   {
     Matrix y = new Matrix(row_, column_);
     for(int i = 0; i < y.row_; i++)
         for(int j = 0; j < y.column_; j++)
             y.data_[i][j] = (data_[i][j] * x);
     return y;
   }

   public void negatate()
   {
     this.data_ = this.multiply(-1).data_;
   }

   public void setZero()
   {
     this.data_ = this.multiply(0).data_;
   }

   @Override
   public String toString()
   {
     String s = "";
     for(int i = 0; i < row_; i++)
         for(int j = 0; j < column_; j++)
         {
             s += data_[i][j];
             s += (i != row_ - 1 || j != column_ - 1) ? " " : "";
         }

     return s;
   }

   public Matrix transpose()
   {
     Matrix x = new Matrix(column_, row_);
     for(int i = 0; i < row_; i++)
         for(int j = 0; j < column_; j++)
             x.data_[j][i] = data_[i][j];
     return x;
   }

   public double getElement(int row, int column)
   {
     if(row < 0 || row > row_ || column < 0 || column > column_)
         throw new RuntimeException("Invalid row or column");
     return data_[row][column];
   }

   public int getRowsNumber()
   {
     return row_;
   }

   public int getColumnNumber()
   {
     return column_;
   }

   public double[] getRow(int row)
   {
     if(row > row_)
         throw new RuntimeException("Row out of bounds");
     return data_[row];
   }

   public double[] getColumn(int column)
   {
     if(column > column_)
         throw new RuntimeException("Column out of bounds");
     double[] temp = new double[row_];
     for(int i = 0; i <  row_; i++)
         temp[i] = data_[i][column];
     return temp;
   }

   public void setElement(int row, int column, double element)
   {
     if(row < 0 || row > row_ || column < 0 || column > column_)
         throw new RuntimeException("Invalid row or column");
     data_[row][column]= element;
   }

   public void setRow(int row, double[] array)
   {
     if(row > row_)
         throw new RuntimeException("Row out of bounds");
     if(data_.length != array.length)
         throw new RuntimeException("Invalid array length");
     data_[row] = array;
   }

   public void setColumn(int column, double[] array)
   {
     if(column > column_)
         throw new RuntimeException("Column out of bounds");
     for(int i = 0; i <  row_; i++)
         data_[i][column] = array[i];
   }

   public Matrix random()
   {
     for(int i = 0; i < row_; i++)
         for(int j = 0; j < column_; j++)
             data_[i][j] = Math.random();
     return this;
   }

   public void print()
   {
     for(int i = 0; i < row_; i++)
     {
         for(int j = 0; j < column_; j++)
             System.out.print(data_[i][j] + " ");
         System.out.println();
     }
   }

   public void printf(String format)
   {
     for(int i = 0; i < row_; i++)
     {
         for(int j = 0; j < column_; j++)
            System.out.format(format, data_[i][j]);
         System.out.println();
     }
   }

   public boolean equals(Matrix x)
   {
     if(row_ != x.row_ || column_ != x.column_) return false;
     for(int i = 0; i < row_; i++)
         for(int j = 0; j < column_; j++)
             if(data_[i][j] - x.data_[i][j] > 0.0001) return false;
     return true;
   }

   public double determinant()
   {
     if(row_ != column_)
         throw new RuntimeException("Matrix is not squere");
     if(row_ == 1) return data_[0][0];
     if(row_ == 2) return data_[0][0] * data_[1][1] - data_[0][1] * data_[1][0];

     Matrix tmp = this;
     double result = 0;
     int k = 1;
     for(int i = 0; i < row_; i++)
     {
             if(i % 2 == 1) k = -1;
             else k = 1;
             result += k * data_[0][i] * tmp.getMinor(tmp.data_, 0, i).determinant();
     }
     return result;

   }

   private Matrix getMinor(double[][] matrix, int row, int column)
   {
     int minor_length = matrix.length - 1;
     double[][] minor = new double[minor_length][minor_length];
     int ii = 0;
     int jj = 0;

     for(int i = 0; i <= minor_length; i++)
     {
         jj = 0;
         for(int j = 0; j <= minor_length; j++)
         {
             if(i == row) ii = 1;
             else
             {
                 if(j == column) jj = 1;
                 else
                 {
                     minor[i - ii][j - jj] = matrix[i][j];
                 }
             }
         }

     }
     return new Matrix(minor);
   }
   private Matrix adjoint()
   {
     if (row_ != column_)
         throw new RuntimeException("Illegal matrix dimensions.");
     double adj[][] = new double[row_][row_];
     int ti, tj;
     for (int i = 0; i < column_; i++)
     {
         for (int j = 0; j < column_; j++)
         {
             ti = 0;
             tj = 0;
             double t[][] = new double[column_-1][column_-1];
             for (int ii = 0; ii < column_; ii++)
             {
                 for (int jj = 0; jj < column_; jj++)
                 {
                     if (ii != i && jj != j)
                     {
                         t[ti][tj] = data_[ii][jj];
                         ++tj;
                     }
                 }
                 tj = 0;
                 if (ii != i) ++ti;
             }
             Matrix tmpMatr = new Matrix(t);
             adj[i][j] = Math.pow(-1, i+j) * tmpMatr.determinant();
         }
     }
     Matrix matr = new Matrix(adj);
     return matr.transpose();
   }

   public Matrix inverse()
   {
     if (column_ != row_)
         throw new RuntimeException("Illegal matrix dimensions.");
     double determinant = this.determinant();
     if (determinant == 0)
         throw new RuntimeException("Determinant = 0.");
     double number = 1 / determinant;
     return this.adjoint().multiply(number);
   }

}
