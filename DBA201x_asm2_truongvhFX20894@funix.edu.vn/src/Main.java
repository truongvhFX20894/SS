import java.io.*;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static double[] a;

    public static void Menu() {
        System.out.println("+-------------------Menu------------------+"+
                "\n|      1.Manual Input                     |"+
                "\n|      2.File input                       |"+
                "\n|      3.Bubble sort                      |"+
                "\n|      4.Selection sort                   |"+
                "\n|      5.Insertion sort                   |"+
                "\n|      6.Search > value                   |"+
                "\n|      7.Search = value                   |"+
                "\n|      0.Exit                             |"+
                "\n+----------------------------------------.+");
        System.out.print("Please select function: ");
    }

    public static void numFunction(int num) {
        switch (num) {
            case 0:
                System.out.println("Thanks!!!");
                System.exit(0);
            case 1:
                manualIn();
                break;
            case 2:
                a = fileIn();
                break;
            case 3:
                bubble(a);
                break;
            case 4:
                selection(a);
                break;
            case 5:
                insertion(a);
                break;
            case 6:
                linear(a);
                break;
            case 7:
                binary(a);
                break;
            default:
                System.out.println("No matching function. Please select again.");
                break;
        }
    }

    public static void main(String[] args) {
        while (true) {
            Menu();
            try {
                int num = Integer.parseInt(scanner.nextLine());
                numFunction(num);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number from 0-7.");
            }
        }
    }

    public static void printArray(double[] a) {
        for (double j : a) {
            System.out.print(j + " ");
        }
    }

    public static void manualIn() {
        System.out.print("Please enter input number of elements: ");
        List<Double> elements = new ArrayList<>();
        int n;
        while (true) {
            try {
                n = Integer.parseInt(scanner.nextLine());
                if (n<=20) {
                    for (int i = 0; i < n; i++) {
                        System.out.print("Please enter input no." + (i + 1) + " elements: ");
                        while (true) {
                            try {
                                double in = Double.parseDouble(scanner.nextLine());
                                elements.add(in);
                                break;
                            } catch (NumberFormatException e) {
                                System.out.print("The input data must be a number. Please re-enter: ");
                            }
                        }
                    }
                    break;
                } else {
                    System.out.println("n must be less than 20.");
                }
            } catch (NumberFormatException e) {
                System.out.print("The input data must be a number. Please re-enter: ");
            }
        }
        a = new double[elements.size()];
        for (int i=0;i< a.length;i++) {
            a[i]=elements.get(i);
        }
        System.out.println("Array: "+elements);
        try (FileWriter input = new FileWriter("INPUT.txt")) {
            for (double element : elements) {
                input.write(element + ",");
            }
            System.out.println("Saved data to file.");
        } catch (IOException e) {
            System.out.println("Can not write to file.");
        }
    }

    public static double[] fileIn() {
        List<Double> elements = new ArrayList<>();
        System.out.print("Please enter the file path: ");
        String filePath = scanner.nextLine();
        try (Scanner sc = new Scanner(new FileReader(filePath))) {
            sc.useDelimiter(",");
            while (sc.hasNextLine()) {
                double element = sc.nextDouble();
                sc.skip(sc.delimiter());
                elements.add(element);
            }
            System.out.println("Read the file successfully");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        double[] elementArray = new double[elements.size()];
        for (int i=0;i< elementArray.length;i++) {
            elementArray[i]=elements.get(i);
        }
        System.out.println("Array: "+elements);
        return elementArray;
    }

    public static void bubble(double[] a) {
        double[] b = Arrays.copyOfRange(a, 0, a.length);;
        System.out.print("Original array: ");
        printArray(b);

            for (int i=0;i<b.length-1;i++) {
                // So sánh từng đôi một phần tử để tìm ra phần tử lớn nhất và xếp nó ở vị trí cuối cùng
                for (int j=0;j<b.length-1-i;j++) {
                    if (b[j+1]<b[j]) {
                        double tg = b[j];
                        b[j]=b[j+1];
                        b[j+1]=tg;
                    }
                }
                System.out.print("\nStep "+(i+1)+": ");
                printArray(b);

            }
        try (FileWriter input = new FileWriter("OUTPUT1.txt")) {
            for (double b1 : b) {
                input.write(b1+" ");
            }
        } catch (IOException e) {
            System.out.println("Can not write to file.");
        }
        System.out.println(" ");
    }

    public static void selection(double[] a) {
        double[] b = Arrays.copyOfRange(a, 0, a.length);
        System.out.print("Original array: ");
        printArray(b);
        int min;
        // Tìm phần tử nhỏ nhất trong dãy và xếp nó vào vị trí đầu tiên
        for (int i=0;i<b.length-1;i++) {
            min = i;
            for (int j=i+1;j<b.length;j++) {
                if (b[min]>b[j]) {
                    min=j;
                }
            }
            double tg = b[i];
            b[i]=b[min];
            b[min]=tg;
            System.out.print("\nStep "+(i+1)+": ");
            printArray(b);
        }
        System.out.println(" ");
        try (FileWriter input = new FileWriter("OUTPUT2.txt")) {
            for (double b1 : b) {
                input.write(b1+" ");
            }
        } catch (IOException e) {
            System.out.println("Can not write to file.");
        }
    }

    public static void insertion(double[] a) {
        double[] b = Arrays.copyOfRange(a, 0, a.length);
        System.out.print("Original array: ");
        printArray(b);
        try (FileWriter input = new FileWriter("OUTPUT3.txt")) {
            for (int i=1;i<b.length;i++) {
                double key = b[i];
                int j=i-1;
                // Di chuyển phần từ có giá trị lớn hơn key lùi về sau 1 đơn vị
                while (j>=0 && b[j]>key) {
                    b[j+1]=b[j];
                    j=j-1;
                }
                // Vị trí cuối cùng của key là j+1
                b[j+1]=key;
                System.out.print("\nStep "+i+": ");
                printArray(b);
                input.write("\nStep "+i+": ");
                for (double b1 : b) {
                    input.write(b1+" ");
                }
            }
            System.out.println(" ");
        } catch (IOException e) {
            System.out.println("Can not write to file.");
        }

    }

    public static void linear(double[] a) {
        List<Double> b = new ArrayList<>();
        System.out.println("Please enter searched input value: ");
        try {
            // Quét tuần tự qua mảng, nếu giá trị j thỏa mãn lớn hơn hoặc bằng value thì thêm vào list
            double value = Double.parseDouble(scanner.nextLine());
            for (double j : a) {
                if (j >= value) {
                    b.add(j);
                }
            }
            // Kiểm tra nếu có giá trị lớn hơn thì in giá trị đó ra, nếu không thì thông báo kết quả
            if (b.size()>0) {
                System.out.println("Large position: "+b);
                try (FileWriter input = new FileWriter("OUTPUT4.txt")) {
                    for (double b1 : b) {
                        input.write(b1+" ");
                    }
                }
            } else {
                System.out.println("The value does not exist!");
            }
        } catch (NumberFormatException e) {
            System.out.println("The value does not exist!");
        } catch (IOException e) {
            System.out.println("Can not write to file.");
        }
    }

    public static void binary(double[] a) {
        double[] b = Arrays.copyOfRange(a, 0, a.length);
        // Sắp xếp mảng trước khi tìm kiếm nhị phân
        for (int i=0;i<b.length;i++) {
            for (int j=i+1;j<b.length;j++) {
                if (b[i]>b[j]) {
                    double tg = b[i];
                    b[i] = b[j];
                    b[j] = tg;
                }
            }
        }
        System.out.println("Please enter searched input value: ");
        try {
            double value = Double.parseDouble(scanner.nextLine());
            // Gọi hàm tìm kiếm nhị phân
            binarySearch(b, value, 0, b.length);
        } catch (NumberFormatException e) {
            System.out.println("The value does not exist!");
        }
    }

    public static void binarySearch(double[] a, double value, int start, int end) {
        if (start>end) {
            System.out.println("The value does not exist!");
            return;
        }
        // Chia mảng đã cho tại vị trí key là điểm giữa của mảng
        // Nếu giá trị tại key không bằng value thì chạy đệ quy lại hàm
        try (FileWriter input = new FileWriter("OUTPUT5.txt")) {
            int key = (end-start)/2;
            if (value==a[start+key]) {
                System.out.println("The right position: "+(start+key));
                input.write(value+","+(start+key));
            } else if (value>a[start+key]) {
                binarySearch(a, value, start+key+1, end);
            } else {
                binarySearch(a, value, start, start+key-1);
            }
        } catch (IOException e) {
            System.out.println("Can not write to file.");
        }
    }
}