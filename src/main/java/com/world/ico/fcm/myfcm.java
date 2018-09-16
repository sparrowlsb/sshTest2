package com.world.ico.fcm;

import com.world.ico.dto.EndResult;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class myfcm {

    public void beginfcm(List<EndResult>read)  {
        //R0:初始分类矩阵,生成满足条件的随机数
        //n:样本数
        //m:特征值个数
        //c:分类数
        //e:精度
        //q:FCM指标
        //double x[][]={{0.9637,0.2536,0.7415},{0.4659,1,0.5815},{1,0.7893,1},{0.5437,0.9915,0},{0.3062,0.6861,0.2364}};
//        System.out.println(read);
        double b[][] = {{1, 5, 8}, {5, 10, -3}};
        double VV[][];
        double variance[];
        double RR[][];
        double CR[][];
        String[] kind=new String[3];
        double resultRank[][];
        double rank[][];
        int i, j, k;
        double ClassF[];
        int N=0;
        int M = 1;
        int C = 3;
        double E = 0.001;
        double Q = 2;
        int D = 2;
        //************读取数据集
        double re[][] = new double[C][M];
        method fcm;
        fcm = new method();
        N=read.size();
        ArrayList<Double> COUNT=new ArrayList<Double>();
        double x[][] = new double[N][M];//  特征指标矩阵
        COUNT=fcm.readfile(x,M,read);
//        Iterator<Integer>hh=COUNT.iterator();
//        while (hh.hasNext()){
//            System.out.println();
//        }
        HashMap<String, String>map= fcm.findCountry(read);
        //************初始化初始分类矩阵R0
        double R0[][]=fcm.beginRo(C,N);

        //总体是1随机分random
        //打印R0[][];
//        for(i=0;i<N;i++)
//        {
//            for(j=0;j<C;j++)
//            {
//                //System.out.print(R0[j][i]+" ");
//            }
//            //System.out.println();
//        }

        //由构造函数创建类的实例
        double max;
        variance = fcm.variance(COUNT);
        System.out.println("各个时间下=对应该商品销售总量方差--------------------V");
        for (int t = 0; t < variance.length; t++) {
            System.out.println(variance[t]);
            variance[t] = (double)1 / (1 + Math.exp((double)-variance[t]/400));

            System.out.printf("采用S函数1/(1+Math.exp(-z))"+"\n"+"%8.9f\n", variance[t]);
        }
        System.out.println("模糊神经网络预测分类级别规划--------------------V");
        BPNN bpnn = new BPNN();
        try {
            kind=bpnn.run(variance);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        C=fcm.Kind(kind);
        System.out.println("等级类别"+C);
        do {
            max = 0;
            VV = fcm.FCMCenter(R0, x, N, M, C, Q);//由特征指标矩阵X和初始分类矩阵R0计算聚类中心
            //System.out.println("迭代中的聚类中心");
            //fcm.display(VV);
            RR = fcm.modifyR(x, VV, N, M, C, D, Q);//由特征指标矩阵X和聚类中心V修正分类矩阵RR
//            System.out.println("迭代中的模糊分类矩阵");
//            fcm.display(RR);
            //计算RR和R0的精度差
            for (i = 0; i < C; i++)
                for (j = 0; j < N; j++) {
                    if (Math.abs(RR[i][j] - R0[i][j]) > max)
                        max = Math.abs(RR[i][j] - R0[i][j]);
                }
            if (max > E) {
                for (i = 0; i < C; i++)
                    for (j = 0; j < N; j++)
                        R0[i][j] = RR[i][j];
            }
            //max=0.00001;
        } while (max > E);

        //输出迭代次数

        System.out.println("最终聚类中心等级划分--------------------V");
        rank = fcm.rank(VV, C, M);
        resultRank = fcm.resultRank(VV, rank);
        fcm.display2(VV, resultRank);
        //输出分类矩阵
        System.out.println("模糊分类矩阵--------------------每行表示一个聚类中心，每列表示一个国家");
        System.out.println();
        fcm.display(RR);
        //输出确定性分类
        System.out.println("最终确定性分类结果--------------------CR");
        System.out.println();
        CR = fcm.displayClass(x,RR, N, C, resultRank,M,read,map);//计算确定性分类
//        fcm.display(CR);


        //输出分类系数和平均模糊熵
        ClassF = fcm.ClassFactor(RR, N, C);
        System.out.println("最终分类系数   每个元素pow(R1[i][j],2)相加总和 除以总国家数");
        System.out.printf("%10.8f", ClassF[0]);
        System.out.println();
        System.out.println("最终平均模糊熵  每个元素R1[i][j]*Math.log(R1[i][j])相加总和 除以总国家数");
        System.out.printf("%10.8f", ClassF[1]);
        System.out.println();


        //dis=fcm.compute_dis(a,1,b,1,3,D);
        //System.out.println(dis);
    }


}