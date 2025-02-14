package Graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex<T> {
    public T data;
    public List<Edge<T>> edges;

    public Vertex(T data) {
        edges = new ArrayList<>();
        this.data = data;
    }

    public Vertex() {
        edges = new ArrayList<>();
        data = null;
    }

    void setData(T data) {
        this.data = data;
    }

    void addEdge(Vertex<T> sink, Integer wight) {
        edges.add(new Edge<T>(this, sink, wight));
    }

    Edge<T> getEdgeMatchSink(Vertex<T> sink) {
        for (Edge<T> edge : edges) {
            if (edge.sink.data.equals(sink.data)) {
                return edge;
            }
        }
        return null;
    }

    void removeEdge(Vertex<T> sink) {
        Edge<T> edge = getEdgeMatchSink(sink);
        if (edge != null) {
            edges.remove(edge);
        }
    }

    void print(boolean showWight) {
        String message = "";
        if (edges.size() == 0) {
            System.out.println(data + " --->");
            return;
        }
        for (int i = 0; i < edges.size(); i++) {
            Edge<T> currentEdge = edges.get(i);
            if (i == 0) {
                message += currentEdge.source.data + " --> ";
            }
            message += currentEdge.sink.data;
            if (showWight) {
                message += " (" + currentEdge.wight + ") ";
            }
            if (i != edges.size() - 1) {
                message += ", ";
            }
        }
        System.out.println(message);
    }

}
