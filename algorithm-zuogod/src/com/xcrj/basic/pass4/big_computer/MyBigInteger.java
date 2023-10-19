package com.xcrj.basic.pass4.big_computer;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rocky on 14-3-26.
 */
public class MyBigInteger {
    private char sign = '0';   // 0 表示正数  - 表示负数
    private byte[] data;

    public MyBigInteger() {
        this.data = "0".getBytes();
    }

    public MyBigInteger(String value) throws Exception {
        //正则表达式，输入字符串要求以 零个或一个 - 开头，其余都是数字
        Pattern pattern = Pattern.compile("^-?\\d+$");

        if (value == null || value.length() <= 0) {
            value = "0";
        }
        Matcher matcher = pattern.matcher(value);
        if (!matcher.find()) {
            throw new Exception("the value is not a number string :" + value);
        }
        //获取字符串的第一个字符
        char firstChar = value.charAt(0);

        //data应该保存的是第一个非0数字后的字符串
        if (firstChar == '-') {  //说明输入的是个负数
            if (value.length() >= 2) {
                sign = firstChar;
                value = value.substring(1);
                value = getTemp(value); //得到value中第一个非0后的子字符串。
            }
        } else {
            value = getTemp(value);
        }
        this.data = value.getBytes();
    }

    /**
     * 得到一个字符串第一个非0后的字符串，如果没有找到，则返回 "0" 。如：00003435534，则返回3435534
     *
     * @return
     */
    private String getTemp(String value) {
        Pattern pattern = Pattern.compile("[^0]{1}");
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            value = value.substring(matcher.start());
        } else {
            value = "0";
        }
        return value;
    }

    public MyBigInteger add(MyBigInteger other) {
        MyBigInteger result = new MyBigInteger();
        int thisLength = this.data.length;
        int otherLength = other.data.length;
        int shorterLength = thisLength > otherLength ? otherLength : thisLength;
        ArrayList<Byte> resultData = new ArrayList<Byte>();
        int flag = 0;  //表示相加时的 进位，或相减时的 借位
        int i = thisLength - 1;
        int j = otherLength - 1;
        int k = shorterLength;

        //两个数的符号相同
        if (other.sign == this.sign) {
            //从两个整数的个位开始依次相加
            while (k > 0) {
                Integer temp = new Integer(new String(new byte[]{this.data[i]})) + new Integer(new String(new byte[]{other.data[j]})) + flag;
                flag = temp / 10;  //相加结果超过10时的进位。没有超过10，进位为 0
                resultData.add(0, ((temp % 10) + "").getBytes()[0]);  //把相加结果保存起来
                k--;
                i--;
                j--;
            }
            //把多出的位加入到结果中
            if (i == -1) {
                while (j >= 0) {
                    Integer temp = new Integer(new String(new byte[]{other.data[j]})) + flag;
                    flag = temp / 10;
                    resultData.add(0, ((temp % 10) + "").getBytes()[0]);
                    j--;
                }
            } else if (j == -1) {
                while (i >= 0) {
                    Integer temp = new Integer(new String(new byte[]{this.data[i]})) + flag;
                    flag = temp / 10;
                    resultData.add(0, ((temp % 10) + "").getBytes()[0]);
                    i--;
                }
            }
            //最后把flag加进结果中
            if (flag != 0) {
                for (byte by : (flag + "").getBytes()) {
                    resultData.add(0, by);
                }
            }
            result.sign = other.sign;
        } else {  //符号不同
            if (thisLength > otherLength) {  //说明this表示的整数绝对值大，所以最终结果的符号为this的符号
                result.sign = this.sign;
                resultData = subtract(this.data, other.data);  //执行减法
            } else if (thisLength < otherLength) {  //other表示的整数绝对值大，所以最终结果的符号为other的符号
                result.sign = other.sign;
                resultData = subtract(other.data, this.data);
            } else {  //如果两个数据的位数相同
                Integer thisInt = 0;
                Integer otherInt = 0;
                //从第一位开始比较，直到两者不相等
                for (int n = 0; n < thisLength; n++) {
                    thisInt = new Integer(new String(new byte[]{this.data[n]}));
                    otherInt = new Integer(new String(new byte[]{other.data[n]}));
                    if (!thisInt.equals(otherInt)) {   //注意这里要使用equals方法，因为这里需要比较的是两者的内容
                        break;
                    }
                }

                //如果this的绝对值大
                if (thisInt > otherInt) {
                    result.sign = this.sign;
                    resultData = subtract(this.data, other.data);
                } else {
                    result.sign = other.sign;
                    resultData = subtract(other.data, this.data);
                }
            }
        }
        result.data = new byte[resultData.size()];
        for (int m = 0; m < resultData.size(); m++) {
            result.data[m] = resultData.get(m);
        }
        return result;
    }

    private ArrayList<Byte> subtract(byte[] larger, byte[] smaller) {
        ArrayList<Byte> resultData = new ArrayList<Byte>();
        int flag = 0;
        int i = smaller.length - 1;
        int j = larger.length - 1;
        int k = smaller.length;
        while (k > 0) {
            Integer temp = new Integer(new String(new byte[]{larger[j]})) + flag - new Integer(new String(new byte[]{smaller[i]}));
            if (temp < 0) { //如果相减结果小于0，说明需要借位，则把flag置为 -1，以便下一位减去
                flag = -1;
                temp += 10;
            } else {       //如果大于零，需要把flag置为 0.不要忘记了
                flag = 0;
            }
            resultData.add(0, (temp + "").getBytes()[0]);
            j--;
            i--;
            k--;
        }
        //下面的代码就不写注释了
        while (j >= 0) {
            Integer temp = new Integer(new String(new byte[]{larger[j]})) + flag;
            if (temp < 0) {
                flag = -1;
                temp += 10;
            } else {
                flag = 0;
            }
            resultData.add(0, (temp + "").getBytes()[0]);
            j--;
        }
        return resultData;
    }


    @Override
    public String toString() {
        String str = new String(this.data);
        str = getTemp(str);
        if (sign == '-' && str != "0") {
            str = sign + str;
        }
        return str;
    }

    public static void main(String[] args) throws Exception {
        String a1 = "-5453450543044355356576980545345054545453453454344435353254545345054304435535657698087756454543454345454534534543444353532545453450543044355356454543454354353450136546534534545345345054353450136546534534545345345043044355356576980657698087756454543454354353450136546534534545345345054353450136546534534545345345043044355356576980877564545434543543534501877564545434543543534501";
        String b1 = "4545453453454344435353254545345054304435535657698087756454543454354345454534534543444353532545453450543044355356576980877564545434545454534534564545434543543534501365465345345453453450543534501365465345345453453450430443553565769804344435353254545345054304435535657698087756454543454354353450136546534534545345345043543534501365465345345453453450534501365465345345453453450";

        MyBigInteger a = new MyBigInteger(a1);
        MyBigInteger b = new MyBigInteger(b1);
        MyBigInteger c = a.add(b);
        System.out.println(c);

        BigInteger a2 = new BigInteger(a1);
        BigInteger b2 = new BigInteger(b1);
        BigInteger c2 = a2.add(b2);
        System.out.println(c2);

        System.out.println(c2.toString().equals(c.toString()));
    }
}
