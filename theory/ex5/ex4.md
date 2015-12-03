# Theory Exercise 5

<p align="right">Tarek Auel, Tiange Hu, Benedikt Christoph Naumann, Markus Schanz</p>

## Task 1: Routing with Subscriptions
### Step 1:
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

### Step 2:
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
</table>


[state1]: graph1.mmd.png "Initial State"
[state2]: graph2.mmd.png "Propagate to Broker 2"

## Task 2: Addressing

## Task 3: Router Topologies
(See slide 28 of the TK1-2.2 slideset)

a) Centralized Server, because of the hierachie, which ends at a centralized server
b) Acyclic Peer-to-Peer, because of the undirected routing graph (acyclic)
c) Generic Peer-to-Peer, because of the undirected routing graph (with cycles)
