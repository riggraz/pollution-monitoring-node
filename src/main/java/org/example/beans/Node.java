package org.example.beans;

import java.util.UUID;

public class Node implements Comparable<Node> {
    private UUID id;
    private String ip;
    private int port;

    public Node() { }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Node{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }

    @Override
    public int compareTo(Node otherNode) {
        return this.getId().compareTo(otherNode.getId());
    }
}
