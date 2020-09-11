package ru.job4j;

public class MergeSorting {
    public static void mergeSort(int[] A, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(A, p, q);
            mergeSort(A, q + 1, r);
            merge(A, p, q, r);
        }
    }

    public static void merge(int[] A, int p, int q, int r) {
        int[] tmp = new int[r - p + 1];
        int i = p, j = q + 1, k = 0;
        while (i <= q || j <= r) {
            if (i > q) {
                tmp[k++] = A[j++];
            } else {
                if (j > r) {
                    tmp[k++] = A[i++];
                } else {
                    if (A[i] < A[j]) {
                        tmp[k++] = A[i++];
                    } else {
                        tmp[k++] = A[j++];
                    }
                }
            }
        }
        for (j = 0; j < (r - p + 1); j++) {
            A[p + j] = tmp[j];
        }
    }
}
