public class BidirectionalSearch implements PathFinding {
	public List<Node> getPath(Node start, Node end, List<Node> nodes, List<Edge> edges) {
	    Map<Node, Integer> forwardDistances = new HashMap<>();
	    Map<Node, Integer> backwardDistances = new HashMap<>();
	    Map<Node, Edge> forwardParents = new HashMap<>();
	    Map<Node, Edge> backwardParents = new HashMap<>();

	    // Initialization
	    for (Node node : nodes) {
	        forwardDistances.put(node, Integer.MAX_VALUE);
	        backwardDistances.put(node, Integer.MAX_VALUE);
	    }
	    forwardDistances.put(start, 0);
	    backwardDistances.put(end, 0);

	    PriorityQueue<Node> forwardQueue = new PriorityQueue<>((a, b) -> forwardDistances.get(a) - forwardDistances.get(b));
	    PriorityQueue<Node> backwardQueue = new PriorityQueue<>((a, b) -> backwardDistances.get(a) - backwardDistances.get(b));
	    forwardQueue.add(start);
	    backwardQueue.add(end);

	    while (!forwardQueue.isEmpty() && !backwardQueue.isEmpty()) {
	        // Forward search
	        Node currentNode = forwardQueue.poll();
	        if (backwardDistances.containsKey(currentNode)) {
	            // We have a meeting point
	            List<Node> path = new ArrayList<>();
	            path.add(currentNode);
	            Node n = currentNode;
	            while (n != start) {
	                path.add(forwardParents.get(n).from);
	                n = forwardParents.get(n).from;
	            }
	            Collections.reverse(path);
	            n = currentNode;
	            while (n != end) {
	                path.add(backwardParents.get(n).to);
	                n = backwardParents.get(n).to;
	            }
	            return path;
	        }
	        for (Edge edge : currentNode.edges) {
	            int newDistance = forwardDistances.get(currentNode) + edge.weight;
	            if (newDistance < forwardDistances.get(edge.to)) {
	                forwardDistances.put(edge.to, newDistance);
	                forwardParents.put(edge.to, edge);
	                forwardQueue.add(edge.to);
	            }
	        }

	        // Backward search
	        currentNode = backwardQueue.poll();
	        if (forwardDistances.containsKey(currentNode)) {
	            // We have a meeting point
	            List<Node> path = new ArrayList<>();
	            path.add(currentNode);
	            Node n = currentNode;
	            while (n != end) {
	                path.add(backwardParents.get(n).to);
	                n = backwardParents.get(n).to;
	            }
	            Collections.reverse(path);
	            n = currentNode;
	            while (n != start) {
	                path.add(forwardParents.get(n).from);
	                n = forwardParents.get(n).from;
	            }
	            return path;
	        }
	        for (Edge edge : currentNode.edges) {
	            int newDistance = backwardDistances.get(currentNode) + edge.weight

