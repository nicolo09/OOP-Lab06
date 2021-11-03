package it.unibo.oop.lab06.generics1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class GraphImpl<N> implements Graph<N> {

    Collection<N> nodes = new HashSet<>();
    HashMap<N, Set<N>> edges = new HashMap<>();

    public GraphImpl() {
    }

    @Override
    public void addNode(N node) {
	nodes.add(node);
	edges.put(node, new HashSet<N>());
    }

    @Override
    public void addEdge(N source, N target) {
	if (edges.containsKey(source) && edges.containsKey(target)) {
	    edges.get(source).add(target);
	}
    }

    @Override
    public Set<N> nodeSet() {
	return new HashSet<N>(nodes);
    }

    @Override
    public Set<N> linkedNodes(N node) {
	if (edges.containsKey(node)) {
	    return new HashSet<N>(edges.get(node));
	}
	return new HashSet<N>();
    }

    /**
     * Execute BFS algorithm returning the array of fathers
     * 
     * @param source
     * @param target
     * @return
     */
    private HashMap<N, N> bfs(N source, N target) {
	HashMap<N, N> fathers = new HashMap<>();
	// Create queue
	Queue<N> q = new ArrayDeque<N>();
	// Add source node as first in queue, himself as father
	q.add(source);
	fathers.put(source, source);
	// Start taking out nodes from queue
	while (!q.isEmpty()) {
	    // Dequeue a node
	    N currentNode = q.remove();
	    // Every adjacent node not already explored get him as father
	    for (N adjacent : edges.get(currentNode)) {
		if (!fathers.containsKey(adjacent)) {
		    fathers.put(adjacent, currentNode);
		    q.add(adjacent);
		}
	    }
	}
	return fathers;
    }

    @Override
    public List<N> getPath(N source, N target) {
	HashMap<N, N> fathersMap = bfs(source, target);
	List<N> result = new ArrayList<>();
	N current = target;
	// Rebuild fathers relationship from target to source based on BFS fathers tree
	while (!current.equals(source)) {
	    result.add(current);
	    current = fathersMap.get(current);
	}
	// Add last node (source)
	result.add(current);
	Collections.reverse(result);
	return result;
    }

}
