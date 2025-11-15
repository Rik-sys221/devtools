package dev.rikelmearaujo.devtools.data;

import java.util.ArrayList;
import java.util.List;

public class Node<T, U> {
    private U type;
    private T value;
    private List<Node<T, U>> children;

    public Node(U type, T value, List<Node<T, U>> children) {
        this.type = type;
        this.value = value;
        this.children = children;
    }
    public Node(U type, T value) {
        this.type = type;
        this.value = value;
        this.children = new ArrayList<>();
    }

    public void add(Node<T, U> node) {
        this.children.add(node);
    }

    public void remove(Node<T, U> node) {
        this.children.remove(node);
    }

    public Node<T, U> getChild(int index) {
        return this.children.get(index);
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public List<Node<T, U>> getChildrens() {
        return children;
    }

    @Override
    public String toString() {
        String childrenStr = children.stream()
                .map(Node::toString)
                .collect(java.util.stream.Collectors.joining(", "));

        return "{ type: " + type + ", value: " + value + ", children: [" + childrenStr + "] }";
    }

}
