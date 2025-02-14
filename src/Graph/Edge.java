package Graph;

public class Edge<T> {
    public Vertex<T> source;
    public Vertex<T> sink;
    public Integer wight;

    public Edge(Vertex<T> source, Vertex<T> sink, Integer wight) {
        this.source = source;
        this.sink = sink;
        this.wight = wight;
    }
}
