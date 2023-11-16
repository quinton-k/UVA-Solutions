import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Problem {@link <a href="https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=94&page=show_problem&problem=429">Online Judge Problem 429</a>}
 */
class Main {

    public static void main(String[] args) {
        Main main = new Main();
        StringBuilder result = new StringBuilder();//stores our result to be edited later StringBuilders are used to run within time limit
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int cases = Integer.parseInt(reader.readLine());//the total number of cases
            reader.readLine(); //consume empty line
            for (int i = 0; i < cases; i++) {//loops through all cases
                int amplitude = Integer.parseInt(reader.readLine());//the largest value of our wave
                int frequency = Integer.parseInt(reader.readLine());//the number of waves to print
                reader.readLine(); //consume empty line
                String results = main.printWaves(frequency, amplitude); //returns all of our waves with the correct heights
                if (i != (cases - 1)) { //applies to every case except the last case
                    result.append(results).append('\n');//adds an empty line between the cases
                } else {
                    result.append(results);
                }
                result.delete(result.length() - 2, result.length());//deletes the empty lines from the case to meet presentation requirements
                System.out.println(result);
                result.setLength(0);//resets the results to begin the next case
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a wave pattern based on the specified frequency and amplitude.
     *
     * @param frequency The number of repetitions of the wave pattern.
     * @param amplitude The amplitude of the wave pattern.
     * @return A string representing the generated wave pattern.
     */
    String printWaves(int frequency, int amplitude) {
        StringBuilder result = new StringBuilder();//stores the result
        int waveLength = 2 * amplitude;//double the given amplitude to handle the increase and decrease of wave size
        for (int f = 0; f < frequency; f++) {//loops through the amount of waves to print
            for (int i = 1; i <= waveLength; i++) {//loops through the entire length of the wave
                int currentAmplitude = Math.min(i, waveLength - i);//store the current amplitude to be print 1, 22, 333, etc.
                appendWave(result, currentAmplitude);
                result.append('\n');//adds a line break to begin the next wave
            }
        }
        return result.toString();
    }

    /**
     * Appends a segment of the wave pattern to the StringBuilder based on the current amplitude.
     *
     * @param result           The StringBuilder to which the wave pattern segment is appended.
     * @param currentAmplitude The current amplitude of the wave pattern segment.
     */
    void appendWave(StringBuilder result, int currentAmplitude) {
        for (int i = 1; i <= currentAmplitude; i++) {
            result.append(currentAmplitude);
        }
    }
}
