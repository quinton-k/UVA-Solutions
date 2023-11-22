import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }

    void solve() {
        List<String> streets = new ArrayList<>();
        try (BufferedReader sc = new BufferedReader(new InputStreamReader(System.in))) {//use BufferedReader to avoid runtime exception on submission
            while (true) {
                String line = sc.readLine();
                if (line.isEmpty())
                    break;
                if (!line.equalsIgnoreCase("deadend")) {
                    int weight = line.length();//the distance of a street
                    int rowIndex = line.charAt(0) - 'a';//the indexed value of a lowercase alpha character at the beginning of the line
                    int colIndex = line.charAt(weight - 1) - 'a';//the indexed value of lowercase alpha character at the end of the line

                    degrees[rowIndex]++; // increments the degrees for the 1st character
                    degrees[colIndex]++; // increments the degrees for the 2nd character

                    adjacencyMatrix[rowIndex][colIndex] += weight;//increment the weight for the given indices
                    adjacencyMatrix[colIndex][rowIndex] += weight;

                    streets.add(line);
                } else {
                    int minWeight = streets.stream()
                            .mapToInt(String::length)
                            .min()
                            .orElse(0);//get the weight of the shortest street to be used in instances where we have no intersections as our return street
                    System.out.println(getTour(minWeight));
                    streets.clear();
                    adjacencyMatrix = new int[26][26];//reset the adjacency matrix
                    degrees = new int[26];//reset the degree array
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * calculates the minimum tour for a given route by summing the weights of all edges in the graph and using the
     * dijkstra algorithm to determine the shortest path for all odd degrees
     *
     * @param minStreetWeight the weight of the shortest street on the route to be used as the returning street in instances with no valid intersections
     * @return the minimum tour
     */
    int getTour(int minStreetWeight) {
        int tour = 0; //stores our tour distance
        int oddDegreeCount = 0; //stores the amount of odd degrees in our graph should never be more than 2
        int nonZeroValues = 0; //used to count the amount of non-zero values in our graph to determine if any valid intersections are present

        //loops through all of our degrees and filters out the odd ones
        for (int i = 0; i < degrees.length; i++) {
            if (degrees[i] % 2 == 1) {
                oddDegreeCount++;
            }
        }

        //loops through our adjacency matrix and filters out the upper triangle to avoid duplicate values
        for (int row = 0; row < 26; row++) {
            for (int col = row + 1; col < 26; col++) {
                if (adjacencyMatrix[row][col] != 0) {
                    tour += adjacencyMatrix[row][col];
                    nonZeroValues++;//this should always be above 1 unless there are no valid intersections
                }
            }
        }

        if (nonZeroValues > 1) { //checks if we have valid intersections
            if (oddDegreeCount == 0) { //if we have no odd degrees we return our tour
                return tour;
            } else { //handle odd degrees
                int[] oddDegreeVertices = new int[2];//stores up to 2 odd degrees we don't need more since 2 is the max
                int oddDegreeIndex = 0;//keeps track of the current index of our oddDegreeVertices array
                //loops through all of our degrees filters out the odd ones and stores them in our array of odd degrees
                for (int i = 0; i < degrees.length; i++) {
                    if (degrees[i] % 2 == 1) {
                        oddDegreeVertices[oddDegreeIndex++] = i;
                    }
                }

                int dijkstra = dijkstra(oddDegreeVertices[0], oddDegreeVertices[1]);//the shortest path using the dijkstra algorithm
                return dijkstra < Integer.MAX_VALUE ? (tour + dijkstra) : tour;//checks if we were able to reach the target and returns the tour + the shortest path if it was unreachable return the tour
            }
        }
        return tour + minStreetWeight;//returns the tour (weight of all streets) plus the shortest streets weight as our return path
    }

    /**
     * Standard Dijkstra algorithm to determine the shortest path between a source and target node
     *
     * @param source the source node
     * @param target the target node
     * @return the shortest path length
     */
    int dijkstra(int source, int target) {
        int vertices = adjacencyMatrix.length;//the total vertices in our graph
        int[] distance = new int[vertices];//stores the distances we've calculated
        boolean[] visited = new boolean[vertices];//stores the vertices we've visited

        Arrays.fill(distance, Integer.MAX_VALUE);//default all distance values to MAX_INT to represent an unreachable path
        Arrays.fill(visited, false);//default all to not visited

        distance[source] = 0;//the distance from self is always 0

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> distance[node.vertex])); //a PriorityQueue to process our nodes based on distance in ascending order
        priorityQueue.add(new Node(source, 0));//the source node is always 0

        while (!priorityQueue.isEmpty()) {//loops through our queue
            Node currentNode = priorityQueue.poll();
            int currentVertex = currentNode.vertex;

            if (visited[currentVertex]) {//checks if we've already visited a node
                continue;
            }

            visited[currentVertex] = true;//flags a node as visited

            //loops through all vertices checks if the nodes been visited, check that a node is present, checks that the node is reachable, and if the known distance from the source to the current vertex is shorter than it is to the neighbor node
            for (int neighbor = 0; neighbor < vertices; neighbor++) {
                if (!visited[neighbor] && adjacencyMatrix[currentVertex][neighbor] != 0 &&
                        distance[currentVertex] != Integer.MAX_VALUE &&
                        distance[currentVertex] + adjacencyMatrix[currentVertex][neighbor] < distance[neighbor]) {
                    distance[neighbor] = distance[currentVertex] + adjacencyMatrix[currentVertex][neighbor];
                    priorityQueue.add(new Node(neighbor, distance[neighbor]));
                }
            }

            //breaks out of the loop if we find our target
            if (currentVertex == target) {
                break;
            }
        }

        return distance[target];//returns the distance or MAX_INTEGER flagging that the target was unreachable
    }

    //stores data about a Node to be used in our Dijkstra algorithm
    static class Node {
        int vertex;//the vertex of the node
        int distance;//the distance of the node from the source

        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    int[][] adjacencyMatrix = new int[26][26];//stores our adjacency matrix
    int[] degrees = new int[26];//stores our degrees
}
