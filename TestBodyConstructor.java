package com.example.one.prj0;

public class TestBodyConstructor {
    public static void main(String[] args){
        checkBodyConstructor();
    }
    private static void checkBodyConstructor(){
        System.out.println("正在检查第一个物体  请稍等....");

        double xxPos = 1.0;
        double yyPos = 2.0;
        double xxVel = 3.0;
        double yyVel = 4.0;
        double mass =5.0;
        String imgFileName ="jupiter.gif";

        Body b = new Body(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
        checkEquals(xxPos,b.xxPos,"xxPos");
        checkEquals(yyPos,b.yyPos,"yyPos");
        checkEquals(xxVel,b.xxVel,"xxVel");
        checkEquals(yyVel,b.yyVel,"yyVel");
        checkEquals(mass,b.mass,"mass");
        checkStringEquals(imgFileName,b.imgFileName,"path to img");



        System.out.println("正在检查第二个物体  请稍等....");
        Body copy = new Body(b);//副本方式
        checkEquals(b.xxPos,copy.xxPos,"xxPos");
        checkEquals(b.yyPos,copy.yyPos,"yyPos");
        checkEquals(b.xxVel,copy.xxVel,"xxVel");
        checkEquals(b.yyVel,copy.yyVel,"yyVel");
        checkEquals(b.mass,copy.mass,"mass");
        checkStringEquals(b.imgFileName,copy.imgFileName,"path to img");
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
            System.out.println("物体参数检查出错，请核实");
        }else if(expect==actual){
            System.out.println("物体参数"+label+"核查通过，期望值："+expect+" 实际值："+actual);
        }
        else
            System.out.println("物体参数"+label+"检验失败，期望值："+expect+" 实际值："+actual);
    }
}
