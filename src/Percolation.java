import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int grid[];
	private int N; 
	private WeightedQuickUnionUF id;
	private int virtualTop;
	private int virtualBottom;
	private int count;
	
	  // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
    	if(n <= 0) {
    		throw new IllegalArgumentException("Given N<=0");
    	}
    	count = 0;
    	N = n;
    	grid = new int[N*N];
    	virtualTop = N * N;
    	virtualBottom = N * N + 1;
    	//Virtual top site will be index 25 and virtual bottom site will be index 26
    	id = new WeightedQuickUnionUF(N * N + 2);
    	for(int i = 0; i < grid.length; i++ ) {
    		grid[i] = 0; // start with all sites closed 
    		
    	}
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
    	int indx = getIndex(row,col);
    	if(!isOpen(row,col)) {
    		grid[indx] = 1;
    		count++;
    		createUnion(row,col);
    		
    	}
    	if(row == 1) {
    		id.union(indx, virtualTop);
    	}
    	if(row == N) {
    		
    		id.union(indx, virtualBottom);
    	}
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
    	int indx = getIndex(row,col);
    	if(grid[indx] == 1) {
    		return true;
    	}
    	return  false;
    }
    
    //Get the index equivalent corresponding to the rows and column
    private int getIndex(int row, int col) {
    	return (N *(row - 1)) + (col - 1);
    }
    
    //creates a union with open neighbors
    private void createUnion(int row, int col) {
    	boolean rSite, lSite, uSite, dSite; //Holders to check if neighboring sites exist
    	boolean rOpen, lOpen, uOpen, dOpen; //Holderst to check if the neighboring site is open
    	int indx = getIndex(row,col);
    	rSite = isValidSite(row,col+1);
    	lSite = isValidSite(row,col-1);
    	uSite = isValidSite(row-1,col);
    	dSite = isValidSite(row+1,col);
    	//System.out.println("rSite, lSite, uSite, dSite: " + rSite + " " + lSite + " " + uSite + " " + dSite);
    	
    	//Create connection between current site and neighbors
    	if(rSite) {
    		rOpen = isOpen(row,col+1);
    		if(rOpen) {
    			id.union(indx, getIndex(row,col+1));
    		}
    	}
    	if(lSite ) {
    		lOpen = isOpen(row,col-1);
    		if(lOpen) {
    			id.union(indx, getIndex(row,col-1));
    			
    		}
    	}
    	if(uSite) {
    		uOpen = isOpen(row-1,col);
    		if(uOpen) {
    			id.union(indx, getIndex(row-1,col));
    		}
    	}
    	if(dSite) {
    		dOpen = isOpen(row+1,col);
    		if(dOpen) {
    			id.union(indx, getIndex(row+1,col));
    		}
    	}
    	
    	
    	
    }
    
    private boolean isValidSite(int row, int col) {
    	if(row > 0 && col > 0) {
    		if(row <= N && col <= N ) {
    			return true;
    		}
    	}
    	return false;
    }

    // is the site (row, col) full?
	public boolean isFull(int row, int col) {
    	int indx = getIndex(row, col);
    	if(id.connected(indx, virtualTop)) {
    		return true;
    	}
    	return false;
    }
    

    // returns the number of open sites
    public int numberOfOpenSites() {
    	
    	return count;
    }

    // does the system percolate?
	public boolean percolates() {
    	//return id.connected(virtualTop, virtualBottom);
    	return id.connected(virtualTop, virtualBottom);
    }
    
    //Displays current grid
    private void showGrid() {
    	for(int i = 1; i < N+1; i++) {
    		for(int j = 1; j < N+1; j++ ) {
    			int indx = getIndex(i,j);
    			System.out.print(grid[indx] + " ");
    		}
    		System.out.println();
    	}
    }
    

    // test client (optional)
    /*public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N  = Integer.parseInt(br.readLine());
    	Percolation start = new Percolation(N);
    	start.simulate();
    	
    
    }*/
}
