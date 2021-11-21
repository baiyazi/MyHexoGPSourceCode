package sortUtils.impl;

import sortUtils.ArraySort;

public class ArraySortImpl implements ArraySort {

    @Override
    public void twoWayMergeSort(int[] arr) {
        twoWaymergeSort(arr, 0, arr.length - 1);
    }

    private void twoWaymergeSort(int[] arr, int low, int high) {
        if(low < high){
            int mid = (low + high) / 2;
            twoWaymergeSort(arr, low, mid);
            twoWaymergeSort(arr, mid+1, high);
            merge(arr, low, mid, high);
        }
    }

    private void merge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[high -low + 1];
        int index = 0;
        int p = low, q = mid + 1;
        while(p <= mid && q <= high){
            if(arr[p] > arr[q]){
                temp[index++] = arr[q++];
            }else{
                temp[index++] = arr[p++];
            }
        }
        while(p <= mid){
            temp[index++] = arr[p++];
        }
        while(q <= high){
            temp[index++] = arr[q++];
        }
        for (int i = 0; i < temp.length; i++) {
            arr[low + i] = temp[i];
        }
    }


    @Override
    public void shellSort(int[] arr) {
        for (int d = arr.length / 2; d > 0; d = d / 2) {
            for (int j = d; j < arr.length; j++) {
                int index = j - d;
                int value = arr[j];
                while (index >=0 && arr[index] > value) {
                    arr[index + d] = arr[index];
                    index -= d;
                }
                arr[index + d] = value;
            }
        }
    }

    @Override
    public void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int index = i - 1;
            int value = arr[i];
            while (index >= 0 && value < arr[index]) {
                arr[index + 1] = arr[index];
                index--;
            }
            arr[index + 1] = value;
        }
    }

    @Override
    public void binaryInsertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int value = arr[i];
            int left = 0;
            int right = i - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (arr[mid] > value) {
                    right = mid - 1;
                }
                if (arr[mid] < value) {
                    left = mid + 1;
                }
            }
            for (int j = i - 1; j >= left; j--) {
                arr[j + 1] = arr[j];
            }
            arr[left] = value;
        }
    }



    @Override
    public void heapSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

        int size = arr.length;
        swap(arr, --size, 0);
        while (size > 0) {
            heapify(arr, 0, size);
            swap(arr, 0, --size);
        }
    }

    private void heapify(int[] arr, int i, int size) {
        int left = i * 2 + 1;
        while (left < size) {
            int largest = left + 1 < size && arr[left] < arr[left + 1] ? left + 1 : left;
            largest = arr[i] > arr[largest] ? i : largest;
            if (largest == i) {
                break;
            }
            swap(arr, i, largest);
            i = largest;
            left = i * 2 + 1;
        }
    }


    private void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    @Override
    public void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min_idx] > arr[j]) {
                    min_idx = j;
                }
            }
            swap(arr, min_idx, i);
        }
    }

    @Override
    public void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    private void swap(int[] arr, int i, int j) {

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @Override
    public void bBubbleSort(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            for (int j = left; j < right; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
            right--;
            for (int j = right; j > left; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                }
            }
            left++;
        }
    }


    @Override
    public void quickSort(int[] arr) {
        qSort(arr, 0, arr.length - 1);
    }

    private void qSort(int[] arr, int left, int right) {
        if (left < right) {
            int value = partition(arr, left, right);
            qSort(arr, left, value - 1);
            qSort(arr, value + 1, right);
        }
    }

    private int partition(int[] arr, int left, int right) {
        int value = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= value) {
                right--;
            }
            swap(arr, right, left);
            while (left < right && arr[left] <= value) {
                left++;
            }
            swap(arr, right, left);
        }
        return left;
    }


}
