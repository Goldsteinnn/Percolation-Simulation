import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	 // perform independent trials on an n-by-n grid
	private double sum;
	private double T;
	private double[] fractions;
    public PercolationStats(int n, int trials) {
    	if(n <= 0 || trials <=0) {
    		throw new IllegalArgumentException("Given n <= 0 || trial <= 0");
    	}
    	T = trials;
    	fractions = new double[trials];
    	for(int i = 0; i < trials; i++) {
    		Percolation p = new Percolation(n);
    		p.simulate();
    		sum = p.numberOfOpenSites();
    		double dec = (double)sum/(n * n);
    		fractions[i] = dec;
    		
    	}
    }

    // sample mean of percolation threshold
    public double mean() {
    	return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
    	 return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
    	return mean() - ((1.96 * stddev()) / Math.sqrt(T));
    
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
    	return mean() + ((1.96 * stddev()) / Math.sqrt(T));
    }

   // test client (see below)
   public static void main(String[] args) {
	   int n = StdIn.readInt();
	   int T = StdIn.readInt();
	   PercolationStats ps = new PercolationStats(n, T);
	   StdOut.println("mean                    = " + ps.mean());
	   StdOut.println("stddev                  = " + ps.stddev());
	   StdOut.println("95% confidence interval = [" + ps.confidenceHi() + ", " + ps.confidenceLo() + "]");
	   
   }
}
