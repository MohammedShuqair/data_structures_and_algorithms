package Graph.Algorithm;

import Graph.Graph;
import Graph.Vertex;
import Graph.Edge;

import java.util.*;

class WayInfo {
    Integer wight;
    List<Integer> way;

    public WayInfo(Integer wight, List<Integer> way) {
        this.wight = wight;
        this.way = way;
    }

    public WayInfo() {
        this.wight = null;
        this.way = null;
    }

    public void setWight(Integer wight) {
        this.wight = wight;
    }

  /*  public void addVertex(String vertex) {
        if (Objects.equals(this.way, "")) {
            this.way = vertex;
        } else {
            this.way += " ->" + vertex + " ";
        }
    }*/

    @Override
    public String toString() {
        return "\nwight : " + wight + ", way : " + way + "\n";
    }
}

public class Dijkstra {
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<Integer>();
        _initGraph2(graph);
        for (Vertex<Integer> v : graph.vertices) {
            System.out.println(new Dijkstra().sort(graph, v.data));
        }
    }

    private static Integer getMinWightId(HashSet<Integer> visitSet, HashMap<Integer, WayInfo> wightMap) {
        Integer min = null;
        Integer minId = null;
        for (Integer vId : wightMap.keySet()) {
            WayInfo wayInfo = wightMap.get(vId);
            if (!visitSet.contains(vId)) {
                if (wayInfo != null) {
                    if (min == null) {
                        min = wayInfo.wight;
                        minId = vId;
                    } else {
                        if (wayInfo.wight < min) {
                            min = wayInfo.wight;
                            minId = vId;
                        }
                    }
                }
            }
        }
        return minId;
    }

    private static void _initGraph1(Graph<Integer> graph) {
        graph.setWighted(true);
        graph.setUnDirected(false);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(1, 2, 200);
        graph.addEdge(1, 5, 100);
        graph.addEdge(1, 4, 300);
        graph.addEdge(2, 3, 10);
        graph.addEdge(3, 4, 20);
        graph.addEdge(4, 5, 20);
        graph.addEdge(5, 2, 40);
        graph.addEdge(5, 3, 70);
    }

    private static void _initGraph2(Graph<Integer> graph) {
        graph.setWighted(true);
        graph.setUnDirected(false);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);


        graph.addEdge(1, 2, 90);
        graph.addEdge(1, 3, 100);
        graph.addEdge(1, 4, 70);
        graph.addEdge(2, 1, 40);
        graph.addEdge(2, 3, 5);
        graph.addEdge(2, 4, 10);
        graph.addEdge(3, 1, 7);
        graph.addEdge(3, 4, 4);
        graph.addEdge(4, 1, 20);
        graph.addEdge(4, 2, 10);
        graph.addEdge(4, 3, 7);

    }

    public static String addVertexToWay(String way, String vertex) {
        if (Objects.equals(way, "")) {
            way = vertex;
        } else {
            way += "->" + vertex;
        }
        return way;
    }

    public HashMap<Integer, WayInfo> sort(Graph<Integer> graph, int sourceData) {
        Vertex<Integer> source = graph.getVertexByValue(sourceData);
        HashSet<Integer> visitSet = new HashSet<Integer>();
        visitSet.add(source.data);
        HashMap<Integer, WayInfo> wightMap = new HashMap<>();
        _initWightMap(graph.vertices, source, wightMap);

        while (visitSet.size() != graph.size()) {
            Integer minId = getMinWightId(visitSet, wightMap);
            if (minId == null) {
                break;
            }
            visitSet.add(minId);
            WayInfo midVertexInfo = wightMap.get(minId);

            for (Integer vId : wightMap.keySet()) {
                if (!visitSet.contains(vId)) {
                    WayInfo currentInfo = wightMap.get(vId);
                    Integer turnWight = graph.getWight(minId, vId);
                    WayInfo newWight = calcNewWight(currentInfo, midVertexInfo, turnWight, vId);
                    wightMap.put(vId, newWight);
                }
            }
        }
        return wightMap;
    }

    private WayInfo calcNewWight(WayInfo currentWight, WayInfo midVertexWight, Integer turnWight, Integer vid) {
        WayInfo newWayInfo;
        if (turnWight == null) {
            newWayInfo = currentWight;
        } else {
            List<Integer> fullTurnWay = new ArrayList<>(midVertexWight.way);
            fullTurnWay.add(vid);
            Integer fullTurnWight = midVertexWight.wight + turnWight;
            WayInfo fullTurnInfo = new WayInfo(fullTurnWight, fullTurnWay);
            if (currentWight == null) {
                newWayInfo = fullTurnInfo;
            } else {
                if (currentWight.wight < fullTurnInfo.wight) {
                    newWayInfo = currentWight;
                } else {
                    newWayInfo = fullTurnInfo;
                }

            }
        }
        return newWayInfo;
    }

    private void _initWightMap(List<Vertex<Integer>> vertices, Vertex<Integer> source, HashMap<Integer, WayInfo> wightMap) {
        for (Vertex<Integer> vertex : vertices) {
            if (!Objects.equals(vertex.data, source.data)) {
                wightMap.put(vertex.data, null);
            }
        }
        for (Edge<Integer> edge : source.edges) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(edge.source.data);
            list.add(edge.sink.data);
            wightMap.put(edge.sink.data, new WayInfo(edge.wight, list));
        }
    }
}
