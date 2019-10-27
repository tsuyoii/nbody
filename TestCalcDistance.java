package com.example.one.prj0;

public class TestCalcDistance {
    public static void main(String[] args){
        checkCalcDistance();
    }
    private static void checkCalcDistance(){
        System.out.println("正在检查  请稍等....");

        Body b1=new Body(1.0,1.0,3.0,4.0,5.0,"jupiter.gif");
        Body b2=new Body(2.0,1.0,3.0,4.0,5.0,"jupiter.gif");
        Body b3=new Body(4.0,5.0,3.0,4.0,5.0,"jupiter.gif");

        checkEquals(1.0,b1.calcDistance(b2),"b1与b2之间的距离",0.01);
        checkEquals(5.0,b1.calcDistance(b3),"b1与b3之间的距离",0.01);
    }
    private static void checkEquals(double expect, double actual, String label,double eps) {
        if(Double.isNaN(actual)||Double.isInfinite(actual)){
            System.out.println("物体之间力大小检查出错，请核实");
        }else if(Math.abs(expect-actual)<=eps*Math.max(expect,actual)){
            //期望值与实际值的误差绝对值<=容错程度*期望值或实际值中的最大值
            System.out.println("物体参数"+label+"核查通过，期望值："+expect+" 实际值："+actual+" 容错率："+eps);
        }
        else
            System.out.println("物体参数"+label+"检验失败，期望值："+expect+" 实际值："+actual+" 容错率："+eps);
    }
}
