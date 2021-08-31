final int gridSize = 100;
boolean cells[][] = new boolean[gridSize][gridSize];

void setup() { 
  size(900, 900); // Screen size
  frameRate(10); // Set to 5 to see state switches
  
  // Init cells
  for (int i = 0; i < gridSize; i++) {
     for (int j = 0; j < gridSize; j++) {
        cells[i][j] = ceil(random(0, 2)) == 1;
     }
  }
}

void draw() {
   background(0); // set background as black board

   checkLifeOfCells();

   // Display Cells
   for (int i = 0; i < gridSize; i++) {
     for (int j = 0; j < gridSize; j++) {
        if (cells[i][j]) {
          fill(255);
        } else {
          fill(0);
        }

        rect(width/gridSize*j, height/gridSize*i, width/gridSize, height/gridSize); // x, y, xSize, ySize
     }
  }

}

void checkLifeOfCells() {
    boolean newCells[][] = new boolean[gridSize][gridSize];
  
    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
         int nOfNeighbour = numberOfAliveNeighbour(i, j);
         
         if (nOfNeighbour == 3) {
            newCells[i][j] = true;
         } else if (nOfNeighbour == 2) {
            newCells[i][j] = cells[i][j];
         } else if (nOfNeighbour < 2 || nOfNeighbour > 3) {
            newCells[i][j] = false;
         }
       }
    }
    
    cells = newCells;
}

int numberOfAliveNeighbour(int x, int y) {
   int num = 0;
   
   for (int i = x-1; i <= x+1; i++) {
     for (int j = y-1; j <= y+1; j++) {
       if (i >= 0 && i < gridSize && j >= 0 && j < gridSize) {
         if (!(i == x && j == y)) {
           if (cells[i][j]) {
             num++;
           }
         }
       }
     }
   }
   
   return num;
}
