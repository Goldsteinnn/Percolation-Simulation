import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int grid[];
	private int N; 
	private WeightedQuickUnionUF id;
	private int virtualTop = 25;
	private int virtualBottom = 26;
	
	  // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
    	N = n;
    	grid = new int[N*N];
    	//Virtual top site will be index 25 and virtual bottom site will be index 26
    	id = new WeightedQuickUnionUF(N * N + 2);
    	for(int i = 0; i < grid.length; i++ ) {
    		grid[i] = 0; // start with all sites closed 
    		
    	}
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
    	int indx = getIndex(row,col);
    	if(isOpen(row,col)) {
    		grid[indx] = 1;
    		createUnion(row,col);
    		
    	}
    	if(row == 1) {
    		id.union(indx, virtualTop);
    	}
    	else if(row == N) {
    		id.union(indx, virtualBottom);
    	}
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
    	int indx = getIndex(row,col);
    	if(grid[indx] == 0) {
    		return true;
    	}
    	return  false;
    }
    
    //Get the index equivalent corresponding to the rows and column
    private int getIndex(int row, int col) {
    	return (N *(col - 1)) + (row - 1);
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
    		dOpen = isOpen(row+1,col+1);
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
    @SuppressWarnings("deprecation")
	public boolean isFull(int row, int col) {
    	int indx = getIndex(row, col);
    	if(id.connected(indx, virtualTop)) {
    		return true;
    	}
    	return false;
    }
    

    // returns the number of open sites
    public int numberOfOpenSites() {
    	
    	return 0;
    }

    // does the system percolate?
    @SuppressWarnings("deprecation")
	public boolean percolates() {
    	if(id.connected(virtualTop, virtualBottom)) {
    		return true;
    	}
    	return false;
    }
    
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
    public static void main(String[] args) {
    	Percolation start = new Percolation(5);
    	while(!start.percolates()) {
    		start.open(StdRandom.uniform(1, 5),StdRandom.uniform(1, 5));
    		start.showGrid();
    		System.out.println();
    	}
    	start.showGrid();
    
    }
}
