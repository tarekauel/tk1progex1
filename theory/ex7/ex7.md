# Theory Exercise 7

<p align="right">Tarek Auel, Markus Schanz</p>

## Task 1: Local View
In the graph below, the 2-hop neighborhood graph is formed only by the bold
nodes and edges, i.e. node 9 and the edge between node 4 and 5 and do not belong
to the 2-hop neighborhood graph (they were kept because the comparison to the
original graph is more easy this way).

![Two Hop Graph for Node 1](2-hop-graph.png "Two Hop Graph for Node 1")

## Task 2: LOCAL Model
First argument: **Inherently non-local Problem**  
A problem is called inherently non-local if the output of a node *v* depends on
the initial input of a node outside of G<sup>k</sup>(v). The algorithm used to
build a spanning tree in a network is such a problem because a node *v* has only
a limited view on the network (as given by G<sup>k</sup>(v)) for a given k.  
For example, let k=2 and G<sup>2</sup>(1) be the 2-hop neighborhood graph from
task 1. From the perspective of node 1, the removal of the edge between node 3
and 4 would encapsulate node 4, but there is another edge that connects node 4
to the network which is not seen by node 1.  
In the example above, the problem could be solved for k=3. However, for a larger
network k=3 would also be insufficient and there is no upper bound for k which 
solved the problem in general.

Second argument: **Impossibility of Symmetry Breaking**  
Because the LOCAL model is deterministic, a given distributed algorithm produces
the same result on nodes which have an equal view on the network, i.e. there
neighborhood graph G<sup>k</sup> looks alike.  
For exmaple, this problem comes into play for symmetric network graphs (e.g. n-cycle).
A distributed algorithm which is used to self-assign an address for each node in
the network would assign each node the same address because they all have the
same view on the network. 

## Task 3: Topology Control
