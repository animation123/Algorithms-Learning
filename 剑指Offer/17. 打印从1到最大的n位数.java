// 做法一：字符串累加
class Solution {
    public static void printOneToMaxOfNDigits(int n) {
        if (n <= 0) {
            return;
        }

        // 创建一个字符串对象
        StringBuffer number = new StringBuffer();

        for (int i = 0; i < n; i++) {
            number.append('0');
        }

        while (!increment(number)) {
            printNumber(number);
        }
    }

    // 在字符串中进行 +1 操作
    private static boolean increment(StringBuffer number) {
        // isOverFlow表示溢出，如999 + 1 = 1000，此时1000就不需要打印了
        boolean isOverFlow = false;
        // carry表示进位的值
        int carry = 0;
        int length = number.length();
        // length - 1表示字符串最右一位，代表数字中的个位
        for (int i = length - 1; i >= 0; i--) {
            // 求当前位与进位数字之和
            int sum = number.charAt(i) - '0' + carry;
            // 如果当前位是个位，直接++
            if (i == length - 1) {
                sum++;
            }
            // sum的值满10进1
            if (sum >= 10) {
                if (i == 0) {
                    // 超过n位了，表示溢出
                    isOverFlow = true;
                } else {
                    sum -= 10;
                    carry = 1;
                    number.setCharAt(i, (char) ('0' + sum));
                }
            } else {
                number.setCharAt(i, (char) ('0' + sum));
                break;
            }
        }
        return isOverFlow;
    }

    // 打印数字
    private static void printNumber(StringBuffer number) {
        // 设置标记位，表示字符串是否以0开头，如00110，最后要输出为110
        boolean isBeginWithZero = true;
        for (int i = 0; i < number.length(); i++) {
            if (isBeginWithZero && number.charAt(i) != '0') {
                isBeginWithZero = false;
            }
            if (!isBeginWithZero) {
                System.out.print(number.charAt(i));
            }
        }
        System.out.println();
    }

    // 测试
    public static void main(String[] args) {
        printOneToMaxOfNDigits(3);
    }
}


// 方法二：全排列
class Solution {
    public static void printOneToMaxOfNDigits(int n) {
        if (n <= 0) {
            return;
        }
        char[] number = new char[n];
        // 初始化为0
        Arrays.fill(number, '0');

        // 全排列：每一个位置上都会出现0~9
        for (int i = 0; i < 10; i++) {
            number[0] = (char) (i + '0');
            printOneToMaxOfNDigitsRecursive(number, n, 0);
        }

    }

    private static void printOneToMaxOfNDigitsRecursive(char[] number, int length, int index) {
        if (index == length - 1) {
            print(number);
            return;
        }
        for (int i = 0; i < 10; i++) {
            number[index + 1] = (char) (i + '0');
            printOneToMaxOfNDigitsRecursive(number, length, index + 1);
        }
    }

    // 打印字符串
    private static void print(char[] number) {
        StringBuilder str = new StringBuilder();
        boolean isBeginAtOne = false;
        int length = number.length;

        for (int i = 0; i < length; i++) {
            if (number[i] != '0' && !isBeginAtOne) {
                isBeginAtOne = true;
            }
            if (isBeginAtOne) {
                str.append(String.valueOf(number[i]));
            }
        }
        if (str.toString().matches("^9+$")) {
            System.out.println(str.toString() + " ");
        } else {
            System.out.print(str.toString() + " ");
        }
    }

    // 测试
    public static void main(String[] args) {
        printOneToMaxOfNDigits(3);
    }
}