###Functional Requirements:

- [X] Provide a (simple) graphical user interface (GUI)
- [X] The GUI asks the user name to login on startup.
- [X] Every user can publish messages and can add tags for every message.
- [X] Every user can subscribe other users or tags.
- [X] JMS coordination: There is no server anymore; each client manages its own blog and exchanges
the related information with the other clients.

###Non-functional Requirements:
- [X] Apache ActiveMQ is already installed in the current version of the VM, which has to
be used.
- [X] Use the pre-installed Apache ActiveMQ Version 5.6.0 as message broker.
- [X] To initialize and execute Apache ActiveMQ, run the following commands in the VM:
```shell
cd /etc/activemq/instances-enabled/
sudo ln –s ../instances-available/main
sudo /etc/init.d/activemq restart
```
- [X] Use an ANT-Script to start two clients!
- [X] We will start Apache ActiveMQ for your submission. Don’t start it in the ANT script.
- [X] Use the Model-View-Control pattern for your implementation