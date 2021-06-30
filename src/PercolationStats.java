import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
	 // perform independent trials on an n-by-n grid
	private double sum;
	private double T;
    public PercolationStats(int n, int trials) {
    	sum = 0;
    	T = trials;
    	for(int i = 0; i < trials; i++) {
    		Percolation p = new Percolation(n);
    		p.simulate();
    		sum+= (p.numberOfOpenSites()/(n*n));
    		
    	}
    	System.out.println(sum);
    }

    // sample mean of percolation threshold
    public double mean() {
    	return (sum/T);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
    	return 0;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
    	return 0;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
    	return 0;
    }

   // test client (see below)
   public static void main(String[] args) {
	   int n, T;
	   n = StdIn.readInt();
	   T = StdIn.readInt();
	   PercolationStats ps = new PercolationStats(n, T);
	   StdOut.println("mean 				=" + " " + String.format("%.20f", ps.mean()));
	   
   }
}
