package com.example.one.prj0;
/****
 *
 * 编译错误及解决
 *D:\one\src\main\java\com\example\one\prj0>javac Nbody.java
 * 错误: 编码GBK的不可映射字符
 * 解决：javac -encoding utf-8 Body.java
 *
 *
 *Nbody.java:61: 错误: 找不到符号
 *     public static Body[] readBodies(String filename){
 *                   ^
 *   符号:   类 Body
 *   位置: 类 Nbody
 *错误: 找不到符号
 *        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
 *         ^
 *   符号:   变量 StdDraw
 *   位置: 类 Body
 *解决：由于类与类之间的调用，需要同时编译或先编译StdDraw再编译Body,但好像前者更可行
 * 即javac -encoding utf-8 *.java
 *
 *
 *D:\one\src\main\java\com\example\one\prj0>java Nbody
 * 错误: 找不到或无法加载主类 Nbody
 * 原因: java.lang.NoClassDefFoundError: com/example/one/prj0/Nbody (wrong name: Nbody)
 * 解决：D:\one\src\main\java>java com.example.one.prj0.Nbody
 *
 *
 * 错误：Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 0
 *         at com.example.one.prj0.Nbody.main(Nbody.java:41)
 * 解决：跟上文件
 *D:\one\src\main\java>java com.example.one.prj0.Nbody 157788000.0 25000.0 data/planets.txt
 *
 *
 *
 *
 * ******/
public class Nbody {
    // create a note (sine wave) of the given frequency (Hz), for the given
    // duration (seconds) scaled to the given volume (amplitude)
    public static double[] note(double hz, double duration, double amplitude) {
        int N = (int) (StdAudio.SAMPLE_RATE * duration);
        double[] a = new double[N+1];
        for (int i = 0; i <= N; i++)
            a[i] = amplitude * Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
        return a;
    }
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);        //将第0个命令行参数存储为名为T的双精度类型值
        double dt = Double.parseDouble(args[1]);//参数以字符串形式出现，所以需要将String转换为doubles
        String filename = args[2];

        double R = readRadius(filename);
        Body[] bodies = readBodies(filename);
        int N = bodies.length;
//        StdAudio audio = new StdAudio();
//        audio.init();
//        audio.play("audio/2001.mid");

        //开始绘制

        StdDraw.setScale(-R,R);
        StdDraw.clear();
        StdDraw.picture(0,0,"images/starfield.jpg");
        for(Body b:bodies){
            b.draw();
        }
        StdDraw.enableDoubleBuffering();


        double time = 0;
        while (time < T) {
            double[] xForces = new double[N];
            double[] yForces = new double[N];

            for (int i = 0; i < N; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }

            for (int i = 0; i < N; i++) {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Body b: bodies) {
                b.draw();
            }
            StdDraw.show();
            //播放音频，2001.mid无法播放
            double freq = 450.0;
            for (int j = 0; j <= StdAudio.SAMPLE_RATE; j++) {
                StdAudio.play(0.5 * Math.sin(2*Math.PI * freq * j / StdAudio.SAMPLE_RATE));
            }
            int[] steps = { 0, 2, 4, 6, 7, 9, 11, 12 };
            for (int i = 0; i < steps.length; i++) {
                double hz = 440.0 * Math.pow(2, steps[i] / 12.0);
                StdAudio.play(note(hz, 1.0, 0.5));
            }

            StdDraw.pause(10);
            time += dt;

        }

        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", R);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
        // need to call this in non-interactive stuff so the program doesn't terminate
        // until all the sound leaves the speaker.
        StdAudio.close();

        // need to terminate a Java program with sound
        System.exit(0);
    }
    //返回一个对应于该文件中宇宙半径值
    public static double readRadius(String filename){
        In in = new In(filename);
        int N = in.readInt();
        Double R = in.readDouble();
        return R;
    }
    //返回一个含多个行星的数组
    public static Body[] readBodies(String filename){
        In in = new In(filename);
        int N = in.readInt();
        Double R = in.readDouble();
        Body[] bodies = new Body[N];
        for(int i=0;i<N;i++){
            bodies[i] =new Body(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
        }
        return bodies;
    }
}
