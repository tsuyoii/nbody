/****
 * Body类中的实例用来表示宇宙中的行星，恒星或各种物体
 *
 *
 * ***/
package com.example.one.prj0;

public class Body {
    public double xxPos;//描述物体当前位置的x坐标
    public double yyPos;//描述物体当前位置的y坐标
    public double xxVel;//描述物体当前在x方向上的速度
    public double yyVel;//描述物体当前在y方向上的速度
    public double mass;//描述物体的质量
    public String imgFileName;//与描述正文的图像相对应的文件名，例如jupiter.gif

    private static final double g=6.67E-11;

    /*用于初始化Body类的实例的构造函数*/
    /***注意
     * 这里参数名与相应的实例变量名不同，如果要相同，必须确保正确使用“this”关键字
     * ***/
    public Body(double xP,double yP,double xV,double yV,double m,String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    /**
     * 第二个构造函数，接收一个Body对象，并初始化一个相同的Body对象（即副本）
     * 副本数据改变原数据不变
     * **/
    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }
    //计算两个物体之间的距离
    public double calcDistance(Body b){
        double dy=this.yyPos-b.yyPos;
        double dx=xxPos-b.xxPos;//可以不要this
        return Math.sqrt(dy*dy+dx*dx);
    }
    //描述给定物体施加在该物体上的力
    public double calcForceExertedBy(Body b){
        double r = calcDistance(b);
        return (g*mass*b.mass)/(r*r);
    }
    //描述给定物体施加在该物体上的沿X方向的力
    public double calcForceExertedByX(Body b){
        double dx=b.xxPos-xxPos;
        double r = calcDistance(b);
        double f = calcForceExertedBy(b);
        return (f*dx)/r;
    }
    //描述给定物体施加在该物体上的沿Y方向的力
    public double calcForceExertedByY(Body b){
        double dx=b.yyPos-yyPos;
        double r = calcDistance(b);
        double f = calcForceExertedBy(b);
        return (f*dx)/r;
    }
    /***
     *
     * 物体不能对自己施加重力！您能想到为什么会这样吗（提示：宇宙可能会自
     * 身崩溃，摧毁包括您在内的所有事物）？为避免此问题，请忽略数组中与
     * 当前主体相同的任何主体。要比较两个主体，请使用.equals方法代替==：
     * （ samh.equals(samh)将返回true）
     *
     * ***/
    //描述该物体在沿X方向上受到的净力，即所有力的矢量和
    public double calcNetForceExertedByX(Body[] bodies){
        double fx=0;
        for(Body b:bodies){
            //判断是否是同一个物体，防止出现r=0而造成错误
            if(!this.equals(b)) {
                double f = calcForceExertedByX(b);
                fx += f;
            }
        }
        return fx;
    }
    //描述该物体在沿Y方向上受到的净力，即所有力的矢量和
    public double calcNetForceExertedByY(Body[] bodies){
        double fy=0;
        for(Body b:bodies){
            //判断是否是同一个物体，防止出现r=0而造成错误
            if(!this.equals(b)) {
                double f = calcForceExertedByY(b);
                fy+= f;
            }
        }
        return fy;
    }
    //更新物体的位置和速度
    public void update(double dt,double fx,double fy){
        double xa=fx/mass;
        double ya=fy/mass;
        xxVel+=xa*dt;
        yyVel+=ya*dt;
        xxPos+=dt*xxVel;
        yyPos+=dt*yyVel;
    }
    //画自己所在位置画出自己
    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
    }
}
