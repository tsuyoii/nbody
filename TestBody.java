package com.example.one.prj0;

public class TestBody {
    private static final double g=6.67e-11;
    public static void main(String[] args){
        checkBody();
    }
    private static void checkBody() {
        System.out.println("正在创建物体  请稍等....");
        Body b1=new Body(1.0,2.0,3.0,4.0,5.0,"jupiter.gif");
        Body b2=new Body(2.0,3.0,3.0,4.0,5.0,"jupiter.gif");

        double f = b1.calcForceExertedBy(b2);
        System.out.println("两物体间的力为"+f);

    }
    private static void checkStringEquals(String expect, String actual, String label) {
        if(expect.equals(actual)){
            //注意，数字int或double等类型比较用“==”，但字符串String的比较需要用.equals方法
            System.out.println("物体参数"+label+"核查通过，期望值："+expect+" 实际值："+actual);
        }
        else
            System.out.println("物体参数"+label+"检验失败，期望值："+expect+" 实际值："+actual);
    }

    private static void checkEquals(double expect, double actual, String label) {
        if(Double.isNaN(actual)||Double.isInfinite(actual)){
            System.out.println("物体参数出错，请核实");
        }else if(expect==actual){
            System.out.println("物体参数"+label+"核查通过，期望值："+expect+" 实际值："+actual);
        }
        else
            System.out.println("物体参数"+label+"检验失败，期望值："+expect+" 实际值："+actual);
    }
}
