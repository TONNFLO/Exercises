import java.util.Arrays;
import java.util.Scanner;

public class Array {
    public static int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    public static void insert(int index, int value) {
        for (int i = arr.length-1; i > index; i--) {
            arr[i] = arr[i-1];
        }
        arr[index] = value;
    }
    public static void delete(int index) {
        for (int i = index; i < arr.length-1; i++) {
            arr[i] = arr[i+1];
        }
    }
    public static int[] enxtend(int[] arr,int index) {
        int[] newArr = new int[index];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    public static void printarr(int[] arr) {
        for (int element: arr) {
            System.out.print(element + " ");
        }
        System.out.println('\n');
    }
    public static int search(int value, int[] arr) {
        if (value<arr[0] || value>arr[arr.length-1]) {
            System.out.println("Value not found");
            return -1;            
        }
        int left = 0;
        int right = arr.length - 1;
        int mid = (left + right) / 2;
        while (left <= right) {
            if (arr[mid] == value) {
                return mid;
            } else if (arr[mid] < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
            mid = (left + right) / 2;
            
        }

        System.out.println("Value not found");
        return -1;
    }
    public static int search_2(int value, int[] arr) {
        if (value<arr[0] || value>arr[arr.length-1]) {
            System.out.println("Value not found");
            return -1;            
        }
        int left = 0;
        int right = arr.length;
        int mid = (left + right) / 2;
        while (left < right) {
            if (arr[mid] == value) {
                return mid;
            } else if (arr[mid] < value) {
                left = mid + 1;
            } else {
                right = mid;
            }
            mid = (left + right) / 2;
            
        }

        System.out.println("Value not found");
        return -1;
    }
    public static void removeElement(int[] arr, int index) {
        int n = arr.length;
        for(int element: arr) {
            if (arr[element] == index) {
                for (int i = element; i < n-1; i++) {
                    arr[i] = arr[i+1];
                }
                element--;
                n--;
            }
           
        }
        
    }
    public static void removeElement_2(int[] arr, int index) {
        int slow = 0;
        for (int fast = 0; fast < arr.length; fast++) {
            if (arr[fast] != index) {
                arr[slow] = arr[fast];
                slow++;
            }
        }
        
    }
    public static int[] sortedSquares(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] * arr[i];
        }
        Arrays.sort(arr);
        return arr;
    }
    public static int[] sortedSquares_2(int[] arr) {
        int right = arr.length - 1;
        int left = 0;
        int[] newArr = new int[arr.length];
        int index = arr.length - 1;
        while (left <= right) {
            if (Math.abs(arr[left]) > Math.abs(arr[right])) {
                newArr[index] = arr[left] * arr[left];
                left++;
            } else {
                newArr[index] = arr[right] * arr[right];
                right--;
            }
            index--;
        }
        
        return newArr;
    }
    public static int minSubArray(int[] arr, int target) {
        int result = Integer.MAX_VALUE;
        int sum = 0;
        int subLength = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum >= target) {
                    subLength = j - i + 1;
                    result = Math.min(result, subLength);
                    break;
                }
            }
        }
        return result==Integer.MAX_VALUE ? 0 : result;
    }
    public static int minSubArray_2(int[] arr, int target) {
        int result = Integer.MAX_VALUE;
        int sum = 0;
        int subLength = 0;
        int left = 0;
        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];
            while (sum >= target) {
                subLength = right - left + 1;
                result = Math.min(result, subLength);
                sum -= arr[left];
                left++;
            }
        }
        return result==Integer.MAX_VALUE ? 0 : result;
    }
    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int startX = 0;
        int startY = 0;
        int offset = 1;
        int count = 1;
        int loop = n / 2;
        int i, j;
        while (loop > 0) {
            i = startX;
            j = startY;
            for (j = startY; j < startY + n - offset; j++) {
                matrix[i][j] = count++;
            }
            for (i = startX; i < startX + n - offset; i++) {
                matrix[i][j] = count++;
            }
            for (j = startY + n - offset; j > startY; j--) {
                matrix[i][j] = count++;
            }
            for (i = startX + n - offset; i > startX; i--) {
                matrix[i][j] = count++;
            }
            loop--;
            startX++;
            startY++;
            offset += 2;
            
        }
        if (n % 2 != 0) {
            matrix[n/2][n/2] = count;
            
        }
        return matrix;
    }

    /*
     * Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int[] p = new int[n];
        int presum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            presum += arr[i];
            p[i] = presum;
        }
        int a = sc.nextInt();
        int b = sc.nextInt();
        int sum = p[b] - p[a] + arr[a];
        System.out.println(sum);
     */
    /*
     * int n=0,m=0;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int[][] arr = new int[n][m];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
                sum += arr[i][j];
            }
        }
        int[] horizontal = new int[n];
        int[] vertical = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                horizontal[i] += arr[i][j];
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                vertical[i] += arr[j][i];
            }
        }
        int result=Integer.MAX_VALUE;
        int horizontalCut = 0;
        int verticalCut = 0;
        for (int i = 0; i < n-1; i++) {
            horizontalCut += horizontal[i];
            result = Math.min(result, Math.abs(sum - horizontalCut-horizontalCut));
        }
        for (int i = 0; i < m-1; i++) {
            verticalCut += vertical[i];
            result = Math.min(result, Math.abs(sum - verticalCut-verticalCut));
        }
        System.out.println(result);
     */
    public static void main(String[] args) {
        int n=0,m=0;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int[][] arr = new int[n][m];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
                sum += arr[i][j];
            }
        }
        int result = Integer.MAX_VALUE;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                count += arr[i][j];
                if (j == m-1) {
                    result = Math.min(result, Math.abs(sum - count - count));
                }
            }
            
        }
        count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                count += arr[j][i];
                if(j == n-1) {
                    result = Math.min(result, Math.abs(sum - count - count));
                }
            }
        }
        System.out.println(result);
        sc.close();
    }
}
