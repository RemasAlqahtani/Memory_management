## Memory_management
This project is about the Memory management algorithm. We written the code in Java to achevie the task.
#Allocation Algorithms ( First, Best, Worst Fit) 
Given memory partitions of 100K, 500K, 200K, 300K, and 600K (in order), 
hots would each of the First-fit, Best-fit, and Worst-fit algorithms place processes of 212K, 417K, 112K, and 426K (in order)? 
Which algorithm makes the most efficient use of memory? 

# Answer 
1. First-fit: 1. 212K is put in 500K partition 2. 417K is put in 600K partition 3. 112K is put in 288K partition (new partition 288K = 500K - 212K) 4. 426K must wait 2. Best-fit: I. 212K is put in 300K partition 2. 417K is put in 500K partition 3. 112K is put in 200K partition 4. 426K is put in 600K partition 3. Worst-fit: 1. 212K is put in 600K partition 2. 417K is put in 500K partition 3. 112K is put in 388K partition 4. 426K must wait 

#Output
![image](https://github.com/user-attachments/assets/d302fb61-9c3d-414d-a094-4c774ac94f61)

