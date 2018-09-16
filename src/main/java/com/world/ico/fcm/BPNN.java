package com.world.ico.fcm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class BPNN {
    /**
     * @param args
     */

    int inputnodenum=1;
    int hidennodenum=3;
    int outputnodenum=3;
    int maxstep=5000;
    int step;
    double theta=0.4;
    double lambda=1.0;
    double alpha=0.01;
    double eta=0.5;//动量系数
    double v[][];
    double w[][];
    double inputdata[];
    double hidendata[];
    double outputdata[];
    double rightoutput[];
    double deltav[][][];
    double deltaw[][][];
    HashMap<String, Integer> map;
    String [] classname;

    void init()
    {
        v=new double[inputnodenum][hidennodenum];
        w=new double[hidennodenum][outputnodenum];
        deltav=new double[maxstep+1][inputnodenum][hidennodenum];//0
        deltaw=new double[maxstep+1][hidennodenum][outputnodenum];
        inputdata=new double[inputnodenum];
        hidendata=new double[hidennodenum];
        outputdata=new double[outputnodenum];
        rightoutput=new double[outputnodenum];
        map=new HashMap<String, Integer>();
        classname=new String[]{"1","2","3"};//结果集
        for(int i=0;i<3;++i)
            map.put(classname[i], i);
        for(int i=0;i<inputnodenum;++i)//4
        {
            for(int j=0;j<hidennodenum;++j)//8
            {
                v[i][j]=Math.random();//第一层随机
            }
        }
        for(int i=0;i<hidennodenum;++i)//8
        {
            for(int j=0;j<outputnodenum;++j)//3
            {
                w[i][j]=Math.random();//第二层随机
            }
        }
    }

    void showweight()
    {
        System.out.println("V:");
        for(int i=0;i<inputnodenum;++i)
        {
            for(int j=0;j<hidennodenum;++j)
            {
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("W:");
        for(int i=0;i<hidennodenum;++i)
        {
            for(int j=0;j<outputnodenum;++j)
            {
                System.out.print(w[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    double sigmoid(double x)
    {
        return 1/(1+Math.exp(-x));
    }

    void train() throws FileNotFoundException
    {
        String input="";
        String inputline;
        String tmpdata[];
        Scanner jin;
        jin = new Scanner(new File("/Users/lsb/Tomcat/apache-tomcat-7.0.75/webapps/simple.data"));
        while(jin.hasNext())
        {
            inputline=jin.nextLine();
            input+=inputline+"\r\n";
        }
        for(step=1;step<=maxstep;++step)
        {
            jin = new Scanner(input);
            while(jin.hasNext())
            {
                inputline=jin.next();
                tmpdata=inputline.split(",");
                for(int i=0;i<inputnodenum;++i)//4
                {
                    inputdata[i]=Double.parseDouble(tmpdata[i]);//输入层数据
                }
                for(int i=0;i<3;++i)
                {

                    if(i==map.get(tmpdata[inputnodenum]))
                        rightoutput[i]=1;
                    else
                        rightoutput[i]=0;
                }
                for(int j=0;j<hidennodenum;++j)//
                {
                    hidendata[j]=-theta;
                    for(int i=0;i<inputnodenum;++i)
                        hidendata[j]+=v[i][j]*inputdata[i];
                    hidendata[j]=sigmoid(hidendata[j]);
                }

                for(int k=0;k<outputnodenum;++k)//
                {
                    outputdata[k]=-theta;
                    for(int j=0;j<hidennodenum;++j)
                        outputdata[k]+=w[j][k]*hidendata[j];
                    outputdata[k]=sigmoid(outputdata[k]);
                }

                for(int k=0;k<outputnodenum;++k)//
                {
                    for(int j=0;j<hidennodenum;++j)//
                        deltaw[step][j][k]=-eta*(-(rightoutput[k]-outputdata[k]))*(1-outputdata[k])*outputdata[k]*hidendata[j];
                }

                for(int j=0;j<hidennodenum;++j)
                {
                    for(int i=0;i<inputnodenum;++i)
                    {
                        double sum=0;
                        for(int k=0;k<outputnodenum;++k)
                            sum+=-(rightoutput[k]-outputdata[k])*(1-outputdata[k])*outputdata[k]*w[j][k];
                        deltav[step][i][j]=-eta*sum*(1-hidendata[j])*hidendata[j]*inputdata[i];
                    }
                }

                for(int k=0;k<outputnodenum;++k)//
                {
                    for(int j=0;j<hidennodenum;++j)
                        w[j][k]+=deltaw[step][j][k]+alpha*deltaw[step-1][j][k];
                }

                for(int j=0;j<hidennodenum;++j)
                {
                    for(int i=0;i<inputnodenum;++i)
                        v[i][j]+=deltav[step][i][j]+alpha*deltav[step-1][i][j];
                }
            }
        }
    }

    String[] test(double[] variance) throws FileNotFoundException
    {
        String kind[] =new String[3];
        int total=0;
        int right=0;
        double inputline;
        double[] tmpdata=new double[variance.length];
        for(int jin=0;jin<variance.length;jin++)
        {
            inputline=variance[jin];
            tmpdata[jin]=variance[jin];
            for(int i=0;i<inputnodenum;++i)
            {
                inputdata[i]=tmpdata[i];
            }

            for(int j=0;j<hidennodenum;++j)//
            {
                hidendata[j]=-theta;
                for(int i=0;i<inputnodenum;++i)
                    hidendata[j]+=v[i][j]*inputdata[i];
                hidendata[j]=sigmoid(hidendata[j]);
            }

            for(int k=0;k<outputnodenum;++k)//
            {
                outputdata[k]=-theta;
                for(int j=0;j<hidennodenum;++j)
                    outputdata[k]+=w[j][k]*hidendata[j];
                outputdata[k]=sigmoid(outputdata[k]);
            }

            int classid=0;

            for(int k=1;k<outputnodenum;++k)
            {
                if(outputdata[classid]<outputdata[k])
                    classid=k;
            }
            kind[classid]=classname[classid];
//            System.out.print(inputline+" "+classname[classid]+" "+"\n");
//            if(classid!=map.get(tmpdata[inputnodenum]))
//            {
//                System.out.println("\n");
//                right++;
//            }
//            else System.out.println("\n");
            total++;
        }
//        System.out.println();
//        System.out.println("The total number of test data:"+total);
//        System.out.println("The right number of test data:"+right);
//        System.out.println("The right ratio: of test data:"+(double)right/total);
        return kind;
    }

    String [] run(double[] variance) throws FileNotFoundException
    {
        long t1=System.currentTimeMillis();
        init();
        train();
        String[] kind=test(variance);
        System.out.println("Runtime:"+(System.currentTimeMillis()-t1)+"ms");
        return kind;
    }
    public static void main(String[] agrs) throws FileNotFoundException {
        Scanner jin;
        jin = new Scanner(new File("/Users/lsb/IdeaProjects/sshTest/src/main/resources/simple.data"));
        while(jin.hasNext())
        {
            System.out.println(jin.next());
        }
    }
}