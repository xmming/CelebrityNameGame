1.How to run the code:

Note: The program was written using Java. In order to run the program, JDK 1.8 is used to develop this program. In order to run on other machines, corresponding java running environment is required. (The effect of using lower version is unknown.)

celebrity.txt is the input file, with about 13,000 names taken from IMDb. Each name is a line in the file.

output.txt is the longest name chain I got from this celebrity.txt using my program.

Step to run this program:
1) git clone https://github.com/xmming/CelebrityNameGame.git
2) change your current working directory to: CelebrityNameGame/src/celebrity/name/game/

cd CelebrityNameGame/src/celebrity/name/game/
(The format might be different in different operating system)

3)compile the program, run:
javac -d . *.java

4)Execute the program, run:
java celebrity/name/game/CelebrityNameGameStarter celebrity.txt
(The program takes one input file each time.)

5)The result will be printed in the console.


2.Big(O) analysis of the algorithm:

DFS(Depth-first-search) is the main method used to find the longest name chain. 

If no cycle exists in the graph, then the running time is about O(N + M).N is the node number or number of names, M is the number of edges in the graph.

This is because I use a hash map to cache the longest name chain starting from each node. If that node is visited again, then we simply return the cached result instead of doing DFS again. So that each node and each edge is visited once and the running time is O(N + M).

When there are cycles existing in the graph, the running time can reach polynomial time or even worse. This is because my algorithm cannot cache the longest name chain starting from node if that node is in the cycle. If very few node among many nodes are in a cycle, then the running time is still close to O(N + M). In the worst case, when the graph is a complete graph, that is every pair of nodes is connected by a bidirectional edge, then this is a NP-hard problem, which can be reduced from Hamiltonian path problem. (Refer from https://en.wikipedia.org/wiki/Longest_path_problem)


3.Unit test:

I have 3 unit tests. 3 all passed. The coverage rate is about 94%, analyzed by EclEmma plug-in in Eclipse.

In order to run the unit test, JUnit and Mockito are required. I used Unit 4 and mockito-all-1.9.5.jar.

4.Functional test:
Functional testing passed.

Two small input file are used for the functional testing. sample.in is the graph with no cycle formed, sample2.in is the graph with cycle.



