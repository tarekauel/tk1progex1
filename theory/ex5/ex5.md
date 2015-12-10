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

#### Step 4.1:
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

#### Step 4.2:
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

#### Step 5:
![Step 6][state6]
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
                  <td>5</td>
                  <td>F</td>
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
                  <td>5</td>
                  <td>F</td>
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

#### Step 6.1:
![Step 7][state7]
<table>
  <tr>
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
                  <td>3</td>
                  <td>F</td>
              </tr>
          </table>
        </td>
  </tr>
</table>

#### Step 6.2:
![Step 8][state8]
<table>
  <tr>
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
                  <td>3</td>
                  <td>F</td>
              </tr>
              <tr>
                  <td>4</td>
                  <td>F</td>
              </tr>
          </table>
        </td>
  </tr>
</table>

#### Step 7:
![Step 9][state9]
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
                 <td>2</td>
                 <td>F</td>
             </tr>
         </table>
      </td>
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
                    <td>2</td>
                    <td>F</td>
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
                    <td>3</td>
                    <td>F</td>
                </tr>
                <tr>
                    <td>4</td>
                    <td>F</td>
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
                    <td>5</td>
                    <td>F</td>
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
                    <td>5</td>
                    <td>F</td>
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

[state1]: graphs/graph0.png "Initial State"
[state2]: graphs/graph1.png "Propagate 1->2"
[state3]: graphs/graph2.png "Propagate 2->3 and 2->4"
[state4]: graphs/graph3.png "Propagate 3->5"
[state5]: graphs/graph4.png "Propagate 4->5"
[state6]: graphs/graph5.png "Propagate 5->6, 5->3 and 5->4"
[state7]: graphs/graph6.png "Propagate 3->2"
[state8]: graphs/graph7.png "Propagate 4->2"
[state9]: graphs/graph8.png "Propagate 2->1"

#### Notes
Step 4.1 and 4.2 may be in different order.

Step 6.1 and 6.2 may be in different order.

### Subtask b
(Note: The term *router* is used in favor of the term *broker*)

When router 5 receives subscription from router 3, the algorithm dictates that
the subscription must be forwarded to all neighbors (except to the sender itself).
This includes router 4, which does not make sense for the given network mesh.
The reason why this problem occurs, is that the network tree is cyclic.

Another, more critical problem is, that the subscription message would be flooded
within the network infinitely because of the cyclic chain (5->4->2->3->5->...).

One way to solve this problem is to remove either of the brokers 3 and 4. Another
way to solve the problem is to create an acyclic tree - e.g. by using the spanning
tree algorithm. For example, removing the link between node 4 and 5 would result
in an acyclic tree (but also render node 4 useless). Another approach might be to
keep a history of the nodes for a path. The filter f reaches node 5 on two different
paths: 1->2->3 and 1->2->4. If node 5 recognizes it got a filter from two nodes
and their path share one or more nodes it discards one of the filters. This avoids
the infinity loop and any duplication.

### Subtask c
Yes, if a notification from P is received by router 5, it duplicates the
notification and sends it to router 3 and 4. They continue to forward the
notification to router 2 (according to their routing tables) which eventually
delivers both of them to router 1 and therefore to subscriber S.

The problem can be solved the same way as described before. Of course, the
routing tables would require an update afterwards! (e.g. remove entry (4,F) from
router 5). Another approach is to keep a history of messages that a node reached.
If a messages reaches a node again it simply discards it. This doesn't prevent
duplication but an infinity loop. A third approach is to add a TTL to a message.
This avoids infinity loop but not a duplication and if the TTL is to small a message
doesn't reach all receivers.

## Task 2: Addressing
<table>
    <tr>
        <th>&nbsp;</th>
        <th>Task 1: Subscription</th>
        <th>Task 2: Filtering</th>
    </tr>
    <tr>
        <td><b>Channel based</b></td>
        <td>Subscriber subscribes to a specific channel. There is no connection between two
        channels (i.e. no parent/child relationship</td>
        <td>Publisher publish to channel. All messages of a channel are sent to all 
            subscribers of that channel</td>
    </tr>
    <tr>
        <td><b>Topic based</b></td>
        <td>Subscriber subscribes to a specific topic. He receives all messages of the topic,
        all subtopics and all parent topics</td>
        <td>Publisher publish message to a topic and it's then published to all subtopics as well.
        Subscriber of each (sub)topic will get the message (but only once).</td>
    </tr>
    <tr>
        <td><b>Type based</b></td>
        <td>Subscriber subscribes to a specific type. He receives all messages of that
        type and all subtypes.</td>
        <td>Each message is sent to all type subscriptions where the type is equal to the
        message type or a supertype.</td>
    </tr>
    <tr>
        <td><b>Subject based</b></td>
        <td>Subscriber subscribes by providing a filter (e.g. regular expression) that filters
        the messages he receives. The filter is applied just on the subjects.</td>
        <td>Each filter is applied to the subject of each message. If the return value is true
        the message will be sent to the receiver.</td>
    </tr>
    <tr>
        <td><b>Content based</b></td>
        <td>Subscriber subscribes by providing a filter (e.g. regular expression) that filters
        the messages he receives. The filter is applied on the content.</td>
        <td>Each filter is applied to the content of each message. If the return value is true
        the message will be sent to the receiver.</td>
    </tr>
</table>

## Task 3: Router Topologies
(See slide 28 of the TK1-2.2 slideset)

- a) Centralized Server, because of the hierarchy, which ends at a centralized server
- b) Acyclic Peer-to-Peer, because of the undirected network tree (acyclic)
- c) Generic Peer-to-Peer, because of the undirected network graph (with cycles)
