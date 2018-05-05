import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats
{
    private double avsarray[];
    private int T;
    public static void main(String[] args)
    {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n,t);
        System.out.println("mean = "+percolationStats.mean());
        System.out.println("deviation = "+percolationStats.stddev());
        System.out.println("95% confidence interval = "+percolationStats.confidenceLo()+","+percolationStats.confidenceHi());
    }
    public PercolationStats(int n, int trials)
    {
        T = trials;
        avsarray = new double[trials];
        for (int i = 0; i < trials; i++)
        {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates())
            {
                percolation.open(StdRandom.uniform(n)+1, StdRandom.uniform(n)+1);
            }
            avsarray[i] = (double) percolation.numberOfOpenSites()/(n*n);
        }
    }
    public double mean()
    {
        return StdStats.mean(avsarray);
    }
    public double stddev()
    {
        return StdStats.stddev(avsarray);
    }
    public double confidenceLo()
    {
        return StdStats.mean(avsarray) - (1.96 * StdStats.stddev(avsarray)) / Math.pow(T, 0.5);
    }
    public double confidenceHi()
    {
        return StdStats.mean(avsarray) + (1.96 * StdStats.stddev(avsarray)) / Math.pow(T, 0.5);
    }
}
