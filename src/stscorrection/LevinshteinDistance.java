/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stscorrection;

/**
 *
 * @author sergei
 */
public class LevinshteinDistance {
    
///*****************************
        /// Compute Levenshtein distance 
        /// Memory efficient version
        ///*****************************
        public static double iLD(SystemEvent[] sNew, SystemEvent[] sOld) 
        {
            int RowLen = sNew.length;  // length of sRow
            int ColLen = sOld.length;  // length of sCol
            int RowIdx;                // iterates through sRow
            int ColIdx;                // iterates through sCol
            int Row_i;                // ith character of sRow
            int Col_j;                // jth character of sCol
            int cost;                   // cost

            /// Test string length
//            if (Math.max(sNew.length, sOld.length) > Math.pow(2, 31)){
//                System.out.println("\nMaximum string length in Levenshtein.iLD is " + Math.pow(2, 31) + ".\nYours is " + Math.max(sNew.length, sOld.length) + ".");
//                System.out.print("0000000");
//                return 0;
//            }
                //throw (new Exception("\nMaximum string length in Levenshtein.iLD is " + Math.pow(2, 31) + ".\nYours is " + Math.max(sNew.length, sOld.length) + "."));

            // Step 1

            if (RowLen == 0)
            {
                return ColLen;
            }

            if (ColLen == 0)
            {
                return RowLen;
            }

            /// Create the two vectors
            int[] v0 = new int[RowLen + 1];
            int[] v1 = new int[RowLen + 1];
            int[] vTmp;


            
            /// Step 2
            /// Initialize the first vector
            for (RowIdx = 1; RowIdx <= RowLen; RowIdx++)
            {
                v0[RowIdx] = RowIdx;
            }

            // Step 3

            /// Fore each column
            for (ColIdx = 1; ColIdx <= ColLen; ColIdx++)
            {
                /// Set the 0'th element to the column number
                v1[0] = ColIdx;

                Col_j = sOld[ColIdx - 1]._Operation;


                // Step 4

                /// Fore each row
                for (RowIdx = 1; RowIdx <= RowLen; RowIdx++)
                {
                    Row_i = sNew[RowIdx - 1]._Operation;


                    // Step 5

                    if (Row_i == Col_j)
                    {
                        cost = 0;
                    }
                    else
                    {
                        cost = 1;
                    }

                    // Step 6

                    /// Find minimum
                    int m_min = v0[RowIdx] + 1;
                    int b = v1[RowIdx - 1] + 1;
                    int c = v0[RowIdx - 1] + cost;

                    if (b < m_min)
                    {
                        m_min = b;
                    }
                    if (c < m_min)
                    {
                        m_min = c;
                    }

                    v1[RowIdx] = m_min;
                }

                /// Swap the vectors
                vTmp = v0;
                v0 = v1;
                v1 = vTmp;

            }
            return (double) v0[RowLen];
        }

        ///*****************************
        /// Compute the min
        ///*****************************

        private int Minimum(int a, int b, int c)
        {
            int mi = a;

            if (b < mi)
            {
                mi = b;
            }
            if (c < mi)
            {
                mi = c;
            }

            return mi;
        }

        ///*****************************
        /// Compute Levenshtein distance         
        ///*****************************

        public int LD(SystemEvent[] sNew, SystemEvent[] sOld) throws Exception
        {
            int[][] matrix;              // matrix
            int sNewLen = sNew.length;  // length of sNew
            int sOldLen = sOld.length;  // length of sOld
            int sNewIdx; // iterates through sNew
            int sOldIdx; // iterates through sOld
            int sNew_i; // ith character of sNew
            int sOld_j; // jth character of sOld
            int cost; // cost

            /// Test string length
            if (Math.max(sNew.length, sOld.length) > Math.pow(2, 31))
                throw (new Exception("\nMaximum string length in Levenshtein.LD is " + Math.pow(2, 31) + ".\nYours is " + Math.max(sNew.length, sOld.length) + "."));

            // Step 1

            if (sNewLen == 0)
            {
                return sOldLen;
            }

            if (sOldLen == 0)
            {
                return sNewLen;
            }

            matrix = new int[sNewLen + 1][sOldLen + 1];

            // Step 2

            for (sNewIdx = 0; sNewIdx <= sNewLen; sNewIdx++)
            {
                matrix[sNewIdx][0] = sNewIdx;
            }

            for (sOldIdx = 0; sOldIdx <= sOldLen; sOldIdx++)
            {
                matrix[0][sOldIdx] = sOldIdx;
            }

            // Step 3

            for (sNewIdx = 1; sNewIdx <= sNewLen; sNewIdx++)
            {
                sNew_i = sNew[sNewIdx - 1]._Operation;

                // Step 4

                for (sOldIdx = 1; sOldIdx <= sOldLen; sOldIdx++)
                {
                    sOld_j = sOld[sOldIdx - 1]._Operation;

                    // Step 5

                    if (sNew_i == sOld_j)
                    {
                        cost = 0;
                    }
                    else
                    {
                        cost = 1;
                    }

                    // Step 6

                    matrix[sNewIdx][sOldIdx] = Minimum(matrix[sNewIdx - 1][sOldIdx] + 1, matrix[sNewIdx][sOldIdx - 1] + 1, matrix[sNewIdx - 1][sOldIdx - 1] + cost);

                }
            }

            // Step 7

            /// Value between 0 - 100
            /// 0==perfect match 100==totaly different
            System.out.println("Dist=" + matrix[sNewLen][sOldLen]);
            int max = Math.max(sNewLen, sOldLen);
            return (100 * matrix[sNewLen][sOldLen]) / max;
        }
    }



