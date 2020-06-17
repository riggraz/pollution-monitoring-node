package org.example.beans;

public class ThisNode {
    private Node node;
    private boolean isExiting = false;
    private static ThisNode instance;

    // dummy object used to have a different lock between node and isExiting
    private Object isExitingLock = new Object();

    private ThisNode() {
        node = new Node();
    }

    public synchronized static ThisNode getInstance() {
        if (instance == null) instance = new ThisNode();
        return instance;
    }

    public synchronized void setNode(Node n) {
        this.node = n;
    }

    public synchronized Node getNode() {
        Node nodeCopy = new Node();
        nodeCopy.setId(node.getId());
        nodeCopy.setIp(node.getIp());
        nodeCopy.setPort(node.getPort());
        return nodeCopy;
    }

    public boolean getIsExiting() {
        synchronized (isExitingLock) {
            return isExiting;
        }
    }

    public void setIsExiting(boolean isExiting) {
        synchronized (isExitingLock) {
            this.isExiting = isExiting;
        }
    }
}
