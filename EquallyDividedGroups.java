/*
Given 1 ~ 9, Divide them into 3 groups.
Sum of each group is equal to 15.
How many combinations are qualified?
DFS Stack Version
*/
import java.util.*;

public class EquallyDividedGroups {

    static int num = 24;
    static int groups = 3;
    static int members;
    static int answer;
    static int average;
    static int[] intArray = new int[num];
    static boolean[] flagArray = new boolean[num + 1];
    static int length = 0;

    public static void main(String[] args) {
        members = num / groups;
        average = (1 + num) * num / 2 / groups;
        System.out.println("members = " + members + ", average = " + average);
        long startTime = System.currentTimeMillis();
        solve(average);
        long endTime = System.currentTimeMillis();
        System.out.println("answer = " + answer);
        System.out.println("Using " + (endTime - startTime)  / 1000.0 + " sec");
    }

    public static void solve(int resource) {

        if( length == (num - members) ) {
            answer++;
            //System.out.println(intArray);
            return;
        }

        int pos = length % members;

        if( pos == 0 ) {
            int min = 0;
            for(int i = 1; i <= num; i++) {
                if( flagArray[i] ) {
                    continue;
                }
                else {
                    min = i;
                    break;
                }
            }
            intArray[length] = min;
            flagArray[min] = true;
            length++;

            solve(resource - min);

            length--;
            int temp = intArray[length];
            intArray[length] = 0;
            flagArray[temp] = false;

        }
        else if( pos > 0 && pos < (members - 1) ) {
            int peek = intArray[length - 1];
            for(int i = peek + 1; i <= num; i++) {
                if( i > resource / 2 ) {
                    return;
                }
                if( flagArray[i] ) {
                    continue;
                }

                intArray[length] = i;
                flagArray[i] = true;
                length++;

                solve(resource - i);

                length--;
                int temp = intArray[length];
                intArray[length] = 0;
                flagArray[temp] = false;
            }
        }
        else {
            if( resource > num ) {
                return;
            }
            else if( flagArray[resource] ) {
                return;
            }
            else {
                int peek = intArray[length - 1];
                if( resource < peek ) {
                    return;
                }
                else {
                    intArray[length] = resource;
                    flagArray[resource] = true;
                    length++;

                    solve(average);

                    length--;
                    int temp = intArray[length];
                    intArray[length] = 0;
                    flagArray[temp] = false;
                }
            }
        }
        return;
    }

}
