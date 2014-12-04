/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stscorrection.Evaluation;

/**
 *
 * @author sergei
 */
public class StrArrayComperator {

    public static double calcScore(String[] i, String[] j) {
        return 1 - algX(i, j) / Math.max(i.length, j.length);
    }

    //Levenshtein Distance
    private static double algX(String[] A, String[] B) {
        int[][] D = new int[A.length + 1][B.length + 1];

        for (int i = 1; i <= A.length; i++) {
            D[i][0] = D[i - 1][0] + cost(A[i - 1], null);
        }
        for (int j = 1; j <= B.length; j++) {
            D[0][j] = D[0][j - 1] + cost(null, B[j - 1]);
        }

        int m1, m2, m3;
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                m1 = D[i - 1][j - 1] + cost(A[i - 1], B[j - 1]);
                m2 = D[i - 1][j] + cost(A[i - 1], "");
                m3 = D[i][j - 1] + cost("", B[j - 1]);
                D[i][j] = Math.min(m1, Math.min(m2, m3));
            }
        }
        return D[A.length][B.length];
    }

    private static int cost(String a, String b) {
        if (a == null && b == null) {
            return 0;
        } else if (a != null && b != null) {
            if (a.equals(b)) {
                return 0;
            }
        }
        return 1;
    }
}
