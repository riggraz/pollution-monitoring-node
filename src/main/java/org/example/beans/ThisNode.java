package org.example.beans;

import java.util.List;

public class ThisNode {
    private Node node;
    private boolean isExiting;
    private static ThisNode instance;

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

    public synchronized boolean getIsExiting() { return isExiting; }

    public synchronized void setIsExiting(boolean isExiting) { this.isExiting = isExiting; }
}
