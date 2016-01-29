# Theory Exercise 9

<p align="right">Tarek Auel, Markus Schanz</p>

## Task 1: ‘Snapshot’-Algorithm of Chandy and Lamport
### a)
Illustration 1.I is correct, as P<sub>1</sub> forwards the marker message to P<sub>2</sub> after recording its state and before sending any other message.

*Example for figure 1.I:*  
P<sub>1</sub> receives the marker message and saves its local state immediately. It forwards the marker message to process P<sub>2</sub>, followed by a regular message. P<sub>2</sub> receives the marker message first and creates a local snapshot. It then receives the regular message which is not part of the local snapshot, as it is received by P<sub>1</sub> after the receiving the marker message. For both processes the message is not part of the snapshot, so the snapshot itself is consistent.

*Example for figure 1.II:*  
P<sub>1</sub> receives the marker message and saves its local state immediately. It then sends a regular message to P<sub>2</sub>, followed by the marker message. The regular message is not part of the local snapshot, as it happens after the receiving of the marker message. Now P<sub>2</sub> receives the regular message first, followed by the marker message. It creates a local snapshot, consisting of all previous events, including the receiving of the regular message from P<sub>1</sub>. After the algorithm finishes, the global snapshot that is created out of the local snapshots is inconsistent because there exists an receiving event for a message without a corresponding sending event.

### b)
*Variant 1:*  
![Variant 1](snapshot_var_1.png "Variant 1")

**States**:  
<table>
    <tr>
        <td>S_P<sub>1</sub>: &lt;&gt;</td>
        <td>C(P<sub>2</sub>, P<sub>1</sub>): &lt;m1, m2&gt;</td>
        <td>C(P<sub>3</sub>, P<sub>1</sub>): &lt;&gt;</td>
    </tr>
    <tr>
        <td>S_P<sub>2</sub>: &lt;m<sub>1</sub>, m<sub>2</sub>&gt;</td>
        <td>C(P<sub>1</sub>, P<sub>2</sub>): &lt;&gt;</td>
        <td>C(P<sub>3</sub>, P<sub>2</sub>): &lt;m<sub>3</sub>, m<sub>4</sub>&gt;</td>
    </tr>
    <tr>
        <td>S_P<sub>3</sub>: &lt;m<sub>3</sub>, m<sub>4</sub>&gt;</td>
        <td>C(P<sub>1</sub>, P<sub>3</sub>): &lt;&gt;</td>
        <td>C(P<sub>2</sub>, P<sub>3</sub>): &lt;&gt;</td>
    </tr>
</table>

## Task 2: Snapshot vs. Actual Program Flow
### a)
### b)
