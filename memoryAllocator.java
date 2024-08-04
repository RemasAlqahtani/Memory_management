import java.io.*;
import java.util.*;

class MemoryAllocation {
    ArrayList<Integer> blocks = new ArrayList<Integer>();
    int blocksArray[] = new int[100];
    ArrayList<Integer> processes = new ArrayList<Integer>();
    int processesArray[] = new int[100];
    int numBlocks;
    int numProcesses;
    Scanner sc = new Scanner(System.in);
    
    void processRequest() {
        blocksInput();
        processesInput();
        choice();
    }
    
    // This function takes blocks information from user 
    void blocksInput() {
        System.out.print("Number of blocks: ");
        int n = sc.nextInt();
        System.out.print("Block Sizes: ");
        for (int i = 0; i < n; i++) {
            int j = sc.nextInt();
            blocks.add(j);
        }
        numBlocks = blocks.size();
        for (int i = 0; i < numBlocks; i++) {
            blocksArray[i] = blocks.get(i);
        }
    }

    // This function takes processes information from user 
    void processesInput() {
        System.out.print("Number of processes: ");
        int n = sc.nextInt();
        System.out.print("Process Sizes: ");
        for (int i = 0; i < n; i++) {
            int j = sc.nextInt();
            processes.add(j);
        }
        numProcesses = processes.size();
        for (int i = 0; i < numProcesses; i++) {
            processesArray[i] = processes.get(i);
        }
    }
    
    // Menu for memory algorithm selection
    void choice() {
        boolean running = true;
        while(running) {
            System.out.print("\nChoose Algorithm for Memory Allocation\n");
            System.out.print("[1] First Fit\n");
            System.out.print("[2] Best Fit\n");
            System.out.print("[3] Worst Fit\n");
            System.out.print("[4] Exit\n");
            
            System.out.print("Provide your choice (1-4): ");
            int fitType = sc.nextInt();
            switch(fitType) {
                case 1:
                    System.out.println("\n\t\tAfter First Fit \n");
                    firstFit(blocksArray, numBlocks, processesArray, numProcesses);
                    break;
                case 2:
                    System.out.println("\n\t\tAfter Best Fit  \n");
                    bestFit(blocksArray, numBlocks, processesArray, numProcesses);
                    break;
                case 3:
                    System.out.println("\n\t\tAfter Worst Fit \n");
                    worstFit(blocksArray, numBlocks, processesArray, numProcesses);
                    break;
                case 4: 
                    running = false;
                    break;
                default:
                    System.out.println("\nPlease enter a number between 1 and 4.\n");
            }
        }
    }

    void firstFit(int blockSize[], int m, int processSize[], int n) { 
        // Stores block id of the  
        // block allocated to a process
        int allocation[] = new int[n];
        // Initially no block is assigned to any process 
        for (int i = 0; i < allocation.length; i++) {
            allocation[i] = -1; 
        }
        // pick each process and find suitable blocks 
        // according to its size and assign to it
        for (int i = 0; i < n; i++) { 
            for (int j = 0; j < m; j++) { 
                if (blockSize[j] >= processSize[i]) { 
                    allocation[i] = j; 
                    blockSize[j] -= processSize[i]; 
                    break; 
                } 
            } 
        }
         
        System.out.println("\nProcess No.\tProcess Size\tBlock no."); 
        for (int i = 0; i < n; i++) { 
            System.out.print(" " + (i+1) + "\t\t" +  
                             processSize[i] + "\t\t"); 
            if (allocation[i] != -1) 
                System.out.print(allocation[i] + 1); 
            else
                System.out.print("Not Allocated"); 
            System.out.println(); 
        } 
    }

    void bestFit(int blockSize[], int m, int processSize[], int n) 
    {
        // Stores block id of the  
        // block allocated to a process
        int allocation[] = new int[n];
        // Initially no block is assigned to any process
        for (int i = 0; i < allocation.length; i++) {
            allocation[i] = -1;
        } 
        // pick each process and find suitable blocks 
        // according to its size and assign to it
        for (int i = 0; i < n; i++) { 
            int bestIdx = -1; 
            // Find the best fit block for current process
            for (int j = 0; j < m; j++) { 
                if (blockSize[j] >= processSize[i]) { 
                    if (bestIdx == -1) {
                        bestIdx = j; 
                    }
                    else if (blockSize[bestIdx] > blockSize[j]) {
                        bestIdx = j; 
                    }
                }
            } 
            // If we could find a block for current process
            if (bestIdx != -1) { 
                allocation[i] = bestIdx; 
                blockSize[bestIdx] -= processSize[i]; 
            } 
        }
       
        System.out.println("\nProcess No.\tProcess Size\tBlock no."); 
        for (int i = 0; i < n; i++) { 
            System.out.print("   " + (i+1) + "\t\t" + processSize[i] + "\t\t"); 
            if (allocation[i] != -1) { 
                System.out.print(allocation[i] + 1);
            } else {
                System.out.print("Not Allocated"); 
            }
            System.out.println(); 
        } 
    }

    void worstFit(int blockSize[], int m, int processSize[], int n) 
    {
        // pick each process and find suitable blocks 
        // according to its size and assign to it
        int allocation[] = new int[n]; 
        // Initially no block is assigned to any process
        for (int i = 0; i < allocation.length; i++) {
            allocation[i] = -1; 
        }
        // pick each process and find suitable blocks 
        // according to its size and assign to it 
        for (int i = 0; i < n; i++) { 
            int wstIdx = -1; 
            // Find the best fit block for current process 
            for (int j = 0; j < m; j++) { 
                if (blockSize[j] >= processSize[i]) { 
                    if (wstIdx == -1) { 
                        wstIdx = j; 
                    }
                    else if (blockSize[wstIdx] < blockSize[j]) {
                        wstIdx = j; 
                    }
                } 
            } 
            // If we could find a block for current process 
            if (wstIdx != -1) { 
                allocation[i] = wstIdx; 
                blockSize[wstIdx] -= processSize[i]; 
            } 
        } 
       
        System.out.println("\nProcess No.\tProcess Size\tBlock no."); 
        for (int i = 0; i < n; i++) 
        { 
            System.out.print("   " + (i+1) + "\t\t" + processSize[i] + "\t\t"); 
            if (allocation[i] != -1) 
                System.out.print(allocation[i] + 1); 
            else
                System.out.print("Not Allocated"); 
            System.out.println(); 
        } 
    } 

    public static void main(String args[]) throws IOException
    {
        MemoryAllocation m = new MemoryAllocation();
        m.processRequest();
    }
}