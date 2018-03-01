import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.Multiset;
import com.google.common.collect.SetMultimap;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.Graphs;
import com.google.common.graph.MutableGraph;
import com.google.common.graph.MutableNetwork;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.Network;
import com.google.common.graph.NetworkBuilder;
import com.google.common.graph.ValueGraphBuilder;

public class AlgorithmAndDatastructureExample {
	public AlgorithmAndDatastructureExample() {

	}

	public void Multimaps() {
		// creates a ListMultimap with tree keys and array list values
		// https://github.com/google/guava/wiki/NewCollectionTypesExplained#multiset
		ListMultimap<String, Integer> treeListMultimap = MultimapBuilder.treeKeys().arrayListValues().build();
		ListMultimap<String, Integer> hashListMultimap = MultimapBuilder.hashKeys().arrayListValues().build();
	}

	public void Graph() {
		// https://github.com/google/guava/wiki/GraphsExplained
		MutableGraph<Integer> graph = GraphBuilder.undirected().build();

		MutableValueGraph<String, Integer> roads = ValueGraphBuilder.directed().build();

		MutableNetwork<String, String> webSnapshot = NetworkBuilder.directed().allowsParallelEdges(true)
				.expectedNodeCount(100000).expectedEdgeCount(1000000).build();

	}

	public void GraphSimple() {
		MutableGraph<Integer> graph = GraphBuilder.directed().build();
		graph.addNode(1);
		graph.putEdge(2, 3); // also adds nodes 2 and 3 if not already present

		Set<Integer> successorsOfTwo = graph.successors(2); // returns {3}

		graph.putEdge(2, 3); // no effect; Graph does not support parallel edges
	}

	public void WeightedGraphSimple() {
		MutableValueGraph<Integer, Double> weightedGraph = ValueGraphBuilder.directed().build();
		weightedGraph.addNode(1);
		weightedGraph.putEdgeValue(2, 3, 1.5); // also adds nodes 2 and 3 if not already present
		weightedGraph.putEdgeValue(3, 5, 1.5); // edge values (like Map values) need not be unique
		// ...
		weightedGraph.putEdgeValue(2, 3, 2.0); // updates the value for (2,3) to 2.0
	}

	// Return all nodes reachable by traversing 2 edges starting from "node"
	// (ignoring edge direction and edge weights, if any, and not including "node").
	Set<Integer> getTwoHopNeighbors(com.google.common.graph.Graph<Integer> graph, Integer node) {
		Set<Integer> twoHopNeighbors = new HashSet<>();
		for (Integer neighbor : graph.adjacentNodes(node)) {
			twoHopNeighbors.addAll(graph.adjacentNodes(neighbor));
		}
		twoHopNeighbors.remove(node);
		return twoHopNeighbors;
	}
}
