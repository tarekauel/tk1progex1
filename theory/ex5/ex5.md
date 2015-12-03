# Theory Exercise 5

<p align="right">Tarek Auel, Tiange Hu, Benedikt Christoph Naumann, Markus Schanz</p>

## Task 1: Routing with Subscriptions
### Subtask a
#### Step 1:
![Step 1][state1]

<table>
    <tr>
        <td>
            <table>
                <tr>
                    <th colspan="2">Router 1</th>
                </tr>
                <tr>
                    <td><i>D</i></td>
                    <td><i>Filter</i></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </td>

        <td>
            <table>
                <tr>
                    <th colspan="2">Router 2</th>
                </tr>
                <tr>
                    <td><i>D</i></td>
                    <td><i>Filter</i></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </td>

        <td>
            <table>
                <tr>
                    <th colspan="2">Router 3</th>
                </tr>
                <tr>
                    <td><i>D</i></td>
                    <td><i>Filter</i></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </td>
    </tr>

    <tr>
        <td>
            <table>
                <tr>
                    <th colspan="2">Router 4</th>
                </tr>
                <tr>
                    <td><i>D</i></td>
                    <td><i>Filter</i></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </td>

        <td>
            <table>
                <tr>
                    <th colspan="2">Router 5</th>
                </tr>
                <tr>
                    <td><i>D</i></td>
                    <td><i>Filter</i></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </td>

        <td>
            <table>
                <tr>
                    <th colspan="2">Router 6</th>
                </tr>
                <tr>
                    <td><i>D</i></td>
                    <td><i>Filter</i></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </td>
    </tr>
</table>

**In the upcoming steps only those router tables are shown, which actually changed!**

#### Step 2:
![Step 2][state2]

<table>
    <tr>
        <th colspan="2">Router 2</th>
    </tr>
    <tr>
        <td><i>D</i></td>
        <td><i>Filter</i></td>
    </tr>
    <tr>
        <td>1</td>
        <td>F</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>
</table>

#### Step 3:
![Step 3][state3]

<table>
    <tr>
        <td>
            <table>
                <tr>
                    <th colspan="2">Router 3</th>
                </tr>
                <tr>
                    <td><i>D</i></td>
                    <td><i>Filter</i></td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>F</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </td>
        <td>
            <table>
                <tr>
                    <th colspan="2">Router 4</th>
                </tr>
                <tr>
                    <td><i>D</i></td>
                    <td><i>Filter</i></td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>F</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </td>
    </tr>
</table>

#### Step 4:
![Step 4][state4]

<table>
    <tr>
        <th colspan="2">Router 5</th>
    </tr>
    <tr>
        <td><i>D</i></td>
        <td><i>Filter</i></td>
    </tr>
    <tr>
        <td>3</td>
        <td>F</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>
</table>

#### Step 5:
![Step 5][state5]

<table>
    <tr>
        <th colspan="2">Router 5</th>
    </tr>
    <tr>
        <td><i>D</i></td>
        <td><i>Filter</i></td>
    </tr>
    <tr>
        <td>3</td>
        <td>F</td>
    </tr>
    <tr>
        <td>4</td>
        <td>F</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>
</table>

#### Step 6:
![Step 6][state6]

<table>
    <tr>
        <th colspan="2">Router 6</th>
    </tr>
    <tr>
        <td><i>D</i></td>
        <td><i>Filter</i></td>
    </tr>
    <tr>
        <td>5</td>
        <td>F</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>
</table>

#### Final routing tables
<table>
    <tr>
        <td>
            <table>
                <tr>
                    <th colspan="2">Router 1</th>
                </tr>
                <tr>
                    <td><i>D</i></td>
                    <td><i>Filter</i></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </td>

        <td>
            <table>
                <tr>
                    <th colspan="2">Router 2</th>
                </tr>
                <tr>
                    <td><i>D</i></td>
                    <td><i>Filter</i></td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>F</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </td>

        <td>
            <table>
                <tr>
                    <th colspan="2">Router 3</th>
                </tr>
                <tr>
                    <td><i>D</i></td>
                    <td><i>Filter</i></td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>F</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </td>
    </tr>

    <tr>
        <td>
            <table>
                <tr>
                    <th colspan="2">Router 4</th>
                </tr>
                <tr>
                    <td><i>D</i></td>
                    <td><i>Filter</i></td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>F</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </td>

        <td>
            <table>
                <tr>
                    <th colspan="2">Router 5</th>
                </tr>
                <tr>
                    <td><i>D</i></td>
                    <td><i>Filter</i></td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>F</td>
                </tr>
                <tr>
                    <td>4</td>
                    <td>F</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </td>

        <td>
            <table>
                <tr>
                    <th colspan="2">Router 6</th>
                </tr>
                <tr>
                    <td><i>D</i></td>
                    <td><i>Filter</i></td>
                </tr>
                <tr>
                    <td>5</td>
                    <td>F</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </td>
    </tr>
</table>

[state1]: graphs/graph1.png "Initial State"
[state2]: graphs/graph2.png "Propagate 1->2"
[state3]: graphs/graph3.png "Propagate 2->3 and 2->4"
[state4]: graphs/graph4.png "Propagate 3->5"
[state5]: graphs/graph5.png "Propagate 4->5"
[state6]: graphs/graph6.png "Propagate 5->6"

### Subtask b
(Note: The term *router* is used in favor of the term *broker*)

When router 5 receives subscription from router 3, the algorithm dictates that
the subscription must be forwarded to all neighbors (except to the sender itself).
This includes router 4, which does not make sense for the given network mesh.
The reason why this problem occurs, is that the network tree is cyclic.

Another, more cirtical problem is, that the subscription message would be flooded
within the network infinitely because of the cyclic chain (5->4->2->3->5->...).
This is also the reason why router 5 in the solution above does forward the
subscription updates of router 3 and 4 only to router 6.

One way to solve this problem is to remove either of the brokers 3 and 4. Another
way to solve the problem is to create an acyclic tree - e.g. using the spanning
tree algorithm. For example, removing the link between node 4 and 5 would result
in an acyclic tree.

### Subtask c
Yes, if a notification from P is received by router 5, it duplicates the
notification and sends it to router 3 and 4. They continue to forward the
notification to router 2 (according to their routing tables) which eventually
delivers both of them to router 1 and theirfore to subscriber S.

The problem can be solved the same way as described before.

## Task 2: Addressing
TODO

## Task 3: Router Topologies
(See slide 28 of the TK1-2.2 slideset)

a) Centralized Server, because of the hierachie, which ends at a centralized server
b) Acyclic Peer-to-Peer, because of the undirected routing graph (acyclic)
c) Generic Peer-to-Peer, because of the undirected routing graph (with cycles)
