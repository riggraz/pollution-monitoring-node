package org.example.beans;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class NodeList {
    private List<Node> list;
    private static NodeList instance;

    private NodeList() {
        list = new ArrayList<Node>();
    }

    // If not synchronized two instances could be concurrently created
    public synchronized static NodeList getInstance() {
        if (instance == null) instance = new NodeList();
        return instance;
    }

    // If not synchronized there could be race condition problems
    // because ArrayList constructor loops all list elements while
    // other threads may be altering the list
    public synchronized List<Node> getList() {
        sortList();
        return new ArrayList<Node>(list);
    }

    public synchronized void setList(List<Node> l) {
        this.list = l;
    }

    public synchronized void addNode(Node newNode) {
        for (Node node : list) {
            if (node.getId().equals(newNode.getId())) return;
        }
        list.add(newNode);
    }

    public synchronized void deleteNode(UUID nodeId) {
        for (Node node : list) {
            if (node.getId().equals(nodeId)) {
                list.remove(node);
                break;
            }
        }
    }

    private synchronized void sortList() {
        list.sort(new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return n1.compareTo(n2);
            }
        });
    }
}
