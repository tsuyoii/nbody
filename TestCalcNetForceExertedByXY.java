package com.example.one.prj0;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestCalcNetForceExertedByXY {
    public static void main(String[] args){
        checkNetCalcForceExertedByXY();
    }
    private static void checkNetCalcForceExertedByXY(){
        System.out.println("正在检查XY方向上合力的大小  请稍等....");

        Body b1=new Body(1.0,1.0,3.0,4.0,5.0,"jupiter.gif");
        Body b2=new Body(2.0,1.0,3.0,4.0,4e11,"jupiter.gif");
        Body b3=new Body(4.0,5.0,3.0,4.0,5.0,"jupiter.gif");
        Body b4=new Body(3.0,2.0,3.0,4.0,5.0,"jupiter.gif");

        Body[] bodies = {b2,b3,b4};
        double xNetForce = b1.calcNetForceExertedByX(bodies);
        double yNetForce = b1.calcNetForceExertedByY(bodies);
        checkEquals(133.4,round(xNetForce,2),"b1与b234之间X方向上的合力",0.01);
        checkEquals(0.0,round(yNetForce,2),"b1与b234之间Y方向上的合力",0.01);


        System.out.println("用另一种形式再次输入....");

        bodies = new Body[]{b1,b2,b3,b4};
        xNetForce = b1.calcNetForceExertedByX(bodies);
        yNetForce = b1.calcNetForceExertedByY(bodies);
        checkEquals(133.4,round(xNetForce,2),"X方向上的合力",0.01);
        checkEquals(0.0,round(yNetForce,2),"Y方向上的合力",0.01);

    }
    /***大概理解为多精度的四舍五入方式***/
    private  static double round(double value,int sit){
        if(sit<0||Double.isNaN(value)||Double.isInfinite(value)){
            System.out.println("转换失败，请检查输入是否正确");
            throw new IllegalArgumentException();
        }
        //java.math包中提供的API类，用来对超过16位有效位的数字进行精确运算
        BigDecimal bd = new BigDecimal(value);
        bd=bd.setScale(sit, RoundingMode.HALF_UP);//向距离最近的一边舍入，除非两边距离相等时向上舍入，
        return  bd.doubleValue();//强制类型转换
    }
    private static void checkEquals(double expect, double actual, String label,double eps) {
        if(Double.isNaN(actual)||Double.isInfinite(actual)){
            System.out.println("物体XY方向上的合力检查出错，请核实");
        }else if(expect==actual){
            System.out.println("物体参数"+label+"核查通过，期望值："+expect+" 实际值："+actual);
        }
        else
            System.out.println("物体参数"+label+"检验失败，期望值："+expect+" 实际值："+actual);
    }
}

