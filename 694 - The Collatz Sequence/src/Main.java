import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Problem {@link <a href="https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=635">694 - The Collatz Sequence</a>}
 * Status: Accepted
 * Run Time: 1.470
 */
class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }

    void solve() {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            int caseNumber = 1;//keeps track of the case number
            while ((line = input.readLine()) != null) {
                List<Integer> params = getParams(line);
                int aImmutable = params.get(0);//stores the initial value of a required for the output
                int l = params.get(1);//the limit value remains unchanged throughout execution
                int terms = 0;//the number of terms between a and the limit
                int a = aImmutable;//the copy of a that will be used int our sequence
                if (a < 0 && l < 0)//ends the loop when we reach the negative numbers
                    break;

                while (a <= l && a > 0) {//loops while a is less than or equal to the limit and a is non-negative
                    terms++;//increment our terms
                    if (a % 2 == 0) {//check if a is even
                        a = a / 2;
                    } else {
                        a = 3 * a + 1;
                    }

                    if (a == 1) {//ensure we increase our terms and end the loop when a is 1
                        terms++;
                        break;
                    }
                }
                System.out.println("Case " + caseNumber + ": A = " + aImmutable + ", limit = " + l + ", number of terms = " + terms);
                caseNumber++;//increment the case number
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    List<Integer> getParams(String line) {
        List<Integer> values = new ArrayList<>();
        String[] params = line.split(" ");//split the line by the spaces
        for (String param : params) {
            if (param.trim().matches("-?[0-9]+"))//include only the valid numeric characters and remove extra white space
                values.add(Integer.parseInt(param));
        }
        return values;
    }
}
