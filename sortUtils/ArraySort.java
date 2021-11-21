package sortUtils;

public interface ArraySort {
    // 交换排序
    void bubbleSort(int[] arr);
    void bBubbleSort(int[] arr);
    void quickSort(int[] arr);

    // 选择排序
    void selectSort(int[] arr);
    void heapSort(int[] arr);

    // 插入排序
    void insertSort(int[] arr);
    void binaryInsertSort(int[] arr);
    void shellSort(int[] arr);

    // 归并排序
    void twoWayMergeSort(int[] arr);
}
