package Graph;


import BinaryTree.Tween;
import Queue.Queue;
import Stack.Stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph<T> {
    public List<Vertex<T>> vertices;
    private boolean isUnDirected = true;
    private boolean isWighted = false;


    public Graph() {
        this.vertices = new ArrayList<>();
    }

    public static void main(String[] args) {
        Graph<Integer> friends = new Graph<Integer>();
//        friends.addVertex("Hamzar");
//        friends.addVertex("Diab");
//        friends.addVertex("Mo Masry");
//        friends.addVertex("Ali");
//        friends.addEdge("Hamzar", "Mo Masry");
//        friends.addEdge("Ali", "Mo Masry");
//        friends.addEdge("Ali", "Hamzar");
//        friends.addEdge("Hamzar", "Diab");
//        friends.addEdge("Mo Masry", "Diab");
//        friends.prent();
//        friends.removeVertex("Diab");
//        System.out.println("\ndelete diab");
//        friends.prent();
        friends.addVertex(10);
        friends.addVertex(5);
        friends.addVertex(20);
        friends.addVertex(7);
        friends.addVertex(6);
        friends.addVertex(8);
        friends.addVertex(25);
        friends.addVertex(15);
        friends.addVertex(4);

        friends.addEdge(10, 5);
        friends.addEdge(10, 20);
        friends.addEdge(5, 4);
        friends.addEdge(5, 7);
        friends.addEdge(20, 15);
        friends.addEdge(20, 25);
        friends.addEdge(7, 6);
        friends.addEdge(7, 8);


        System.out.println("depthFirstSearch***\n");
        friends.depthFirstSearch();
        System.out.println("\nbreadthFirstSearch***");
        friends.breadthFirstSearch();
    }

    public int size() {
        return vertices.size();
    }

    void breadthFirstSearch() {
        Queue<Vertex<T>> queue = new Queue<Vertex<T>>();
        Map<T, Boolean> visited = new HashMap<>();
        _initVisited(visited);
        if (!vertices.isEmpty()) {
            queue.enqueue(vertices.get(0));
            visited.put(vertices.get(0).data, true);
            while (queue.isNotEmpty()) {
                Vertex<T> current = queue.dequeue();
                System.out.println(current.data);
                for (Vertex<T> sink : current.edges.stream().map(e -> e.sink).toList()) {
                    if (!visited.get(sink.data)) {
                        queue.enqueue(sink);
                        visited.put(sink.data, true);
                    }

                }
            }
        }
    }

    private void _initVisited(Map<T, Boolean> visited) {
        for (Vertex<T> vertex : vertices) {
            visited.put(vertex.data, false);
        }
    }

    public void depthFirstSearch() {
        Stack<Vertex<T>> stack = new Stack<Vertex<T>>();
        Map<T, Boolean> visited = new HashMap<>();
        _initVisited(visited);
        if (!vertices.isEmpty()) {
            stack.push(vertices.get(0));
            while (!stack.isEmpty()) {
                Vertex<T> current = stack.pop();
                if (!visited.get(current.data)) {
                    System.out.println(current.data);
                    visited.put(current.data, true);
                    for (Vertex<T> sink : current.edges.stream().map(e -> e.sink).toList()) {
                        stack.push(sink);
                    }
                }

            }
        }
    }

    public void setUnDirected(boolean unDirected) {
        isUnDirected = unDirected;
    }

    public void setWighted(boolean wighted) {
        isWighted = wighted;
    }

    public Vertex<T> addVertex(T data) {
        Vertex<T> newVertex = new Vertex<T>(data);
        vertices.add(newVertex);
        return newVertex;
    }

    public void removeVertex(T data) {
        Vertex<T> vertex = getVertexByValue(data);
        removeVertex(vertex);
    }

    public void removeVertex(Vertex<T> vertex) {
        vertices.remove(vertex);
        vertices.forEach(
                v -> {
                    v.removeEdge(vertex);
                }
        );
    }

    public Integer getWight(T sourceData, T sinkData) {
        if (isWighted) {
            Vertex<T> source = getVertexByValue(sourceData);
            Vertex<T> sink = getVertexByValue(sinkData);
            Edge<T> edge = source.getEdgeMatchSink(sink);
            if (edge != null) {
                return edge.wight;
            }
        }
        return null;
    }

    public Vertex<T> getVertexByValue(T value) {
        for (int i = 0; i < vertices.size(); i++) {
            Vertex<T> current = vertices.get(i);
            if (current.data.equals(value)) {
                return current;
            }
        }
        return null;
    }

    public Tween<Vertex<T>> getEdgeVertices(T sourceData, T sinkData) {
        Vertex<T> source = null;
        Vertex<T> sink = null;
        for (Vertex<T> current : vertices) {
            if (current.data.equals(sourceData)) {
                source = current;
            } else if (current.data.equals(sinkData)) {
                sink = current;
            }
        }
        return new Tween<>(source, sink);
    }

    void prent() {
        for (Vertex<T> current : vertices) {
            current.print(isWighted);
        }
    }

    public void addEdge(T sourceData, T sinkData) {
        addEdge(sourceData, sinkData, null);
    }

    public void addEdge(T sourceData, T sinkData, Integer wight) {
        Tween<Vertex<T>> vertexTween = getEdgeVertices(sourceData, sinkData);
        Vertex<T> source = vertexTween.first;
        Vertex<T> sink = vertexTween.second;
        _add(source, sink, wight);
    }

    public void addEdge(Vertex<T> source, Vertex<T> sink, Integer wight) {
        _add(source, sink, wight);
    }

    private void _add(Vertex<T> source, Vertex<T> sink, Integer wight) {
        if (source != null && sink != null) {
            source.addEdge(sink, wight);
            if (isUnDirected) {
                sink.addEdge(source, wight);
            }
        }
    }

    public void deleteEdge(T sourceData, T sinkData) {
        Tween<Vertex<T>> vertexTween = getEdgeVertices(sourceData, sinkData);
        Vertex<T> source = vertexTween.first;
        Vertex<T> sink = vertexTween.second;
        _delete(source, sink);
    }

    public void deleteEdge(Vertex<T> source, Vertex<T> sink) {
        _delete(source, sink);
    }

    private void _delete(Vertex<T> source, Vertex<T> sink) {
        if (source != null && sink != null) {
            source.removeEdge(sink);
            if (isUnDirected) {
                sink.removeEdge(source);
            }
        }
    }
}
