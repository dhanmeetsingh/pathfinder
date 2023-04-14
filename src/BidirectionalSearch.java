public class BidirectionalSearch implements PathFinding {
    public List<Node> getPath(Node start, Node end, List<Node> nodes, List<Edge> edges) {
        // Initialize forward and backward priority queues
        PriorityQueue<Node> forwardQueue = new PriorityQueue<>();
        PriorityQueue<Node> backwardQueue = new PriorityQueue<>();

        // Initialize start and end nodes in the queues
        start.setDistance(0);
        end.setDistance(0);
        forwardQueue.offer(start);
        backwardQueue.offer(end);

        // Initialize the visited sets for the forward and backward searches
        Set<Node> forwardVisited = new HashSet<>();
        Set<Node> backwardVisited = new HashSet<>();

        // Perform the bidirectional search
        while (!forwardQueue.isEmpty() && !backwardQueue.isEmpty()) {
            // Dequeue node with smallest distance from forward queue
            Node forwardNode = forwardQueue.poll();
            forwardVisited.add(forwardNode);

            // Check if forward node has been visited in backward search
            if (backwardVisited.contains(forwardNode)) {
                return mergePaths(forwardNode, forwardVisited, backwardVisited);
            }

            // Expand the forward node
            for (Edge edge : forwardNode.getEdges()) {
                Node neighbor = edge.getTo();
                if (!forwardVisited.contains(neighbor)) {
                    int distance = forwardNode.getDistance() + edge.getWeight();
                    if (distance < neighbor.getDistance()) {
                        neighbor.setDistance(distance);
                        neighbor.setPrevious(forwardNode);
                        forwardQueue.offer(neighbor);
                    }
                }
            }

            // Dequeue node with smallest distance from backward queue
            Node backwardNode = backwardQueue.poll();
            backwardVisited.add(backwardNode);

            // Check if backward node has been visited in forward search
            if (forwardVisited.contains(backwardNode)) {
                return mergePaths(backwardNode, forwardVisited, backwardVisited);
            }

            // Expand the backward node
            for (Edge edge : backwardNode.getEdges()) {
                Node neighbor = edge.getTo();
                if (!backwardVisited.contains(neighbor)) {
                    int distance = backwardNode.getDistance() + edge.getWeight();
                    if (distance < neighbor.getDistance()) {
                        neighbor.setDistance(distance);
                        neighbor.setPrevious(backwardNode);
                        backwardQueue.offer(neighbor);
                    }
                }
            }
        }

        // No path found
        return Collections.emptyList();
    }

    // Helper method to merge paths from the forward and backward searches
    private List<Node> mergePaths(Node node, Set<Node> forwardVisited
