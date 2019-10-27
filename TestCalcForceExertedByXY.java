package com.example.one.prj0;

public class TestCalcForceExertedByXY {
    public static void main(String[] args){
        checkCalcForceExertedByXY();
    }
    private static void checkCalcForceExertedByXY(){
        System.out.println("正在检查XY方向上的力的大小  请稍等....");

        Body b1=new Body(1.0,1.0,3.0,4.0,5.0,"jupiter.gif");
        Body b2=new Body(2.0,1.0,3.0,4.0,4e11,"jupiter.gif");
        Body b3=new Body(4.0,5.0,3.0,4.0,5.0,"jupiter.gif");

        checkEquals(133.4,b1.calcForceExertedByX(b2),"b1与b2之间X方向上的力",0.01);
        checkEquals(4.002e-11,b1.calcForceExertedByX(b3),"b1与b3之间X方向上的力",0.01);
        checkEquals(0.0,b1.calcForceExertedByY(b2),"b1与b2之间Y方向上的力",0.01);
        checkEquals(5.336e-11,b1.calcForceExertedByY(b3),"b1与b3之间Y方向上的力",0.01);
    }
    private static void checkEquals(double expect, double actual, String label,double eps) {
        if(Double.isNaN(actual)||Double.isInfinite(actual)){
            System.out.println("物体XY方向上的分力检查出错，请核实");
        }else if(Math.abs(expect-actual)<=eps*Math.max(expect,actual)){
            //这里在Body类中力有方向，需要注意区分，此处给的期望值是+
            System.out.println("物体参数"+label+"核查通过，期望值："+expect+" 实际值："+actual+" 容错率："+eps);
        }
        else
            System.out.println("物体参数"+label+"检验失败，期望值："+expect+" 实际值："+actual+" 容错率："+eps);
    }
}
