import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation
{
    private int array[];
    private int size;
    private int openedsite = 0;
    private WeightedQuickUnionUF A;
    public Percolation(int n)//create n-by-n grid, with all sites blocked
    {
        int m = 0;
        size = n;
        array = new int[n * n];
        A = new WeightedQuickUnionUF(size * size);// imported class from jar package
        for (int x = 1; x <= n; x++)
        {
            for (int y = 1; y <= n; y++)
            {
                array[(x - 1) * size + y -1] = 1;
                m++;
            }
        }
        Virtalpoint();
    }
    public void open(int row, int col) // open site (row, col) if it is not open already
    {
        if (array[(row - 1) * size + col - 1] == 1)
        {
            openedsite++;
            array[(row - 1) * size + col - 1] = 0;
            if (col - 1 >= 1)
            {
                if (array[(row - 1) * size + col - 1] == 0)
                {
                    A.union((row - 1) * size + col - 1, (row - 1) * size + col - 2);
                }
            }
            if (col + 1 <= size)
            {
                if (array[(row - 1) * size + col + 1 - 1] == 0)
                {
                    A.union((row - 1) * size + col - 1, (row - 1) * size + col);
                }
            }
            if (row - 1 >= 1)
            {
                if (array[(row - 2) * size + col - 1] == 0)
                {
                    A.union((row - 1) * size + col - 1, (row - 2) * size + col - 1);
                }
            }
            if (row + 1 <= size)
            {
                if (array[row * size + col - 1] == 0)
                {
                    A.union((row - 1) * size + col -1 , row * size + col - 1);
                }
            }
        }
    }
    public boolean isOpen(int row, int col)// is site (row, col) open?
    {
        return array[(row - 1) * size + col - 1] == 0;
    }
    public boolean isFull(int row, int col)// is site (row, col) full?
    {
        if(isOpen(row, col))
        {
            return Connected(0,(row - 1) * size + col - 1);
        }
        return false;
    }
    public int numberOfOpenSites()// number of open sites
    {
        return openedsite;
    }
    public boolean percolates()// does the system percolate?
    {
        return A.find(0) == find(size*size -1);
    }
    private int find(int p)
    {
        return A.find(p);
    }
    private void Virtalpoint()
    {
        for (int i = 1; i < size; i++)
        {
            A.union(0,i);
            A.union(size*(size - 1),size*(size - 1) + i);
        }
    }
    private boolean Connected(int p,int q)
    {
        return A.find(p) == A.find(q);
    }
}
