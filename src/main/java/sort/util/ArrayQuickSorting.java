package sort.util;

import dto.SortingDto;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class ArrayQuickSorting {
    public static void quickSortUtil(int[] buttonValues,
                                     int from, int to, SortingDto sortingDto) {
        if (from < to) {
            int divideIndex = partition(sortingDto.getButtonValues(), from, to, sortingDto);
            quickSortUtil(buttonValues, from, divideIndex - 1, sortingDto);
            quickSortUtil(buttonValues, divideIndex, to, sortingDto);
        } else {
            for (int i = from; i <= to; i++) {
                sortingDto.getButtons().get(i).setBackground(Color.BLUE);
                sortingDto.getButtons().get(i).setForeground(Color.white);
            }
        }
        if (sortingDto.getButtons().get(buttonValues.length - 1).getBackground() == Color.BLUE) {
            sortingDto.getSortButton().setEnabled(true);
            sortingDto.getResetButton().setEnabled(true);
        }
    }

    public static int partition(int[] arr, int from, int to, SortingDto sortingDto) {
        int rightIndex = to;
        int leftIndex = from;
        int pivot = arr[from + (to - from) / 2];
        sortingDto.getButtons().get(from + (to - from) / 2).setBackground(Color.pink);
        sortingDto.getButtons().get(from + (to - from) / 2).setBorder(new LineBorder(Color.pink));

        while (leftIndex <= rightIndex) {
            sortingDto.getButtons().get(rightIndex).setBorder(new LineBorder(Color.RED));
            while (arr[leftIndex] < pivot) {
                try {
                    sortingDto.getButtons().get(leftIndex).setBorder(new LineBorder(Color.RED));
                    sortingDto.getThread().sleep(1000 / Integer
                            .parseInt(sortingDto.getTextSpeedSort().getText()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sortingDto.getButtons().get(leftIndex).setBorder(new LineBorder(Color.white));
                leftIndex++;
            }
            sortingDto.getButtons().get(leftIndex).setBorder(new LineBorder(Color.RED));

            while (arr[rightIndex] > pivot) {
                try {
                    sortingDto.getButtons().get(rightIndex).setBorder(new LineBorder(Color.RED));
                    sortingDto.getThread().sleep(1000 / Integer
                            .parseInt(sortingDto.getTextSpeedSort().getText()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sortingDto.getButtons().get(rightIndex).setBorder(new LineBorder(Color.white));
                rightIndex--;
            }
            sortingDto.getButtons().get(rightIndex).setBorder(new LineBorder(Color.RED));

            if (leftIndex <= rightIndex) {
                swap(arr, rightIndex, leftIndex, sortingDto);
                sortingDto.getButtons().get(leftIndex).setBorder(new LineBorder(Color.white));
                sortingDto.getButtons().get(rightIndex).setBorder(new LineBorder(Color.white));
                leftIndex++;
                rightIndex--;
            }
        }
        sortingDto.getButtons().get(from + (to - from) / 2).setBackground(Color.GREEN);
        sortingDto.getButtons().get(from + (to - from) / 2).setBorder(new LineBorder(Color.GREEN));
        if (leftIndex >= 0) {
            sortingDto.getButtons().get(leftIndex).setBorder(new LineBorder(Color.white));
        }
        if (rightIndex >= 0) {
            sortingDto.getButtons().get(rightIndex).setBorder(new LineBorder(Color.white));
        }
        return leftIndex;
    }

    private static void swap(int[] array, int firstIndex,
                             int secondIndex, SortingDto sortingDto) {
        int tmp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = tmp;
        sortingDto.getButtons().get(firstIndex)
                .setText(String.valueOf(sortingDto.getButtonValues()[firstIndex]));
        sortingDto.getButtons().get(secondIndex)
                .setText(String.valueOf(sortingDto.getButtonValues()[secondIndex]));
        sortingDto.getButtons().get(firstIndex).setForeground(Color.red);
        sortingDto.getButtons().get(secondIndex).setForeground(Color.red);
        try {
            sortingDto.getThread().sleep(1000 / Integer.parseInt(sortingDto
                    .getTextSpeedSort().getText()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sortingDto.getButtons().get(firstIndex).setForeground(Color.white);
        sortingDto.getButtons().get(secondIndex).setForeground(Color.white);
    }
}
