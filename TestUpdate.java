package com.example.one.prj0;

public class TestUpdate {
    public static void main(String[] args){
        checkUpdate();
    }
    private static void checkUpdate(){
        System.out.println("正在检查  请稍等....");

        Body b1 = new Body(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        b1.update(2.0,1.0,-0.5);

        checkEquals(7.8,b1.xxPos,"xxPos update",0.01);
        checkEquals(8.6,b1.yyPos,"yyPos update",0.01);
        checkEquals(3.4,b1.xxVel,"xxVel update",0.01);
        checkEquals(3.8,b1.yyVel,"yyVel update",0.01);
    }
    private static void checkEquals(double expect, double actual, String label,double eps) {
        if(Double.isNaN(actual)||Double.isInfinite(actual)){
            System.out.println("物体速度位移变化参数检查出错，请核实");
        }else if(Math.abs(expect - actual) <= eps * Math.max(expect, actual)){
            System.out.println("物体参数"+label+"核查通过，期望值："+expect+" 实际值："+actual);
        }
        else
            System.out.println("物体参数"+label+"检验失败，期望值："+expect+" 实际值："+actual);
    }
}
