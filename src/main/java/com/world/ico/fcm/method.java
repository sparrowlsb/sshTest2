package com.world.ico.fcm;

import com.world.ico.dto.EndResult;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/9/11 0011.
 */
public class method {
    public int readline( String readfile ) {
        int j;
        int line=0;
        try {
            String encoding = "GBK";
            int ii = 0;
//            System.out.println("./"+readfile);
            File file = new File("./"+readfile);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    line++;
                }
            }
        }
        catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return line;
    }
    public ArrayList<Double> readfile(double[][] x, int  M, List<EndResult> read) {
        int j;
        ArrayList<Double> count = new ArrayList<Double>();
        int ii = 0;
        for (EndResult endResult : read) {
            count.add(Double.valueOf(endResult.getProbability()));
            for (j = 0; j < M; j++) {
                x[ii][j] = endResult.getProbability();
                ii++;
            }

        }
        return count;
    }
    public HashMap<String, String> findCountry(List<EndResult> read) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        int ii = 0;
        for (EndResult endResult : read) {
            hashMap.put(endResult.getProbability().toString(), endResult.getDateTime());
        }

        return hashMap;
    }
    public double[] variance(ArrayList<Double> vv) {
        double res=0;
        double sum=0;
        int count=0;
        double avg=0;
        Iterator<Double> uj=vv.iterator();
        Iterator<Double> j=vv.iterator();
        while(uj.hasNext()){
            sum=sum+uj.next();
            count++;
        }
        avg=sum/count;
        System.out.println("均值---------------avg"+"\n"+avg);
        while(j.hasNext()){
            res=res+Math.pow(j.next()-avg,2);
        }

        double variance=res/count;
        variance=Math.sqrt(variance);
        double[] var=new double[1];
        var[0]=variance;
        return var;
    }

    public double[][] resultRank(double[][] vv, double[][] rank) {
        double re=0;
        double[][] res=new double[vv.length][vv[0].length];
        for(int i=0;i<vv[0].length;i++){
            for(int j=0;j<vv.length;j++){
                re=vv[j][i];
                for(int u=0;u<rank[0].length;u++) {
                    for (int w = 0; w < rank.length; w++) {
                        if(rank[w][u]==re){
                            res[j][i]=w;
                        }
                    }
                }
            }
        }

        return res;
    }

    public double[][] rank(double[][] vv,int M,int C) {
        double temp=0;
        double[][] v=new double[vv.length][vv[0].length];
        for (int i = 0; i<vv.length; i++)
        {
            for (int j = 0; j<vv[0].length; j++)
            {
                v[i][j]=vv[i][j];
            }
        }
        for(int i=0;i<v[0].length;i++){
            for(int j=0;j<v.length;j++) {
                for (int k = j + 1; k < v.length; k++) {
                    if (v[k][i] < v[j][i]) {
                        temp = v[k][i];
                        v[k][i] = v[j][i];
                        v[j][i] = temp;
                    }
                }
            }
        }

        return v;
    }

    //计算距离
    public double compute_dis(double mat1[][],int k_row, double mat2[][],int i_row,int n_col,int d)
    {
        double x,max;
        int j;
        max=0;
        switch(d)
        {
            case 1:         //Chebyshev 距离
//                System.out.println("Chebyshev距离");
                max=0;
                for(j=0;j<n_col;j++)
                {
                    x=Math.abs(mat1[k_row][j]-mat2[i_row][j]);
                    if(x>max) max=x;
                }
                return max;
            case 2:        //Euclid距离
//                System.out.println("Euclid距离");
                max=0;
                for(j=0;j<n_col;j++)
                {
                    max+=Math.pow((mat1[k_row][j]-mat2[i_row][j]),2);
                }
                return Math.sqrt(max);
            case 3:        //Hamming距离
//                System.out.println("Hamming距离");
                max=0;
                for(j=0;j<n_col;j++)
                {
                    max+=Math.abs(mat1[k_row][j]-mat2[i_row][j]);
                }
                return max;
        }
        return max;
    }
    //计算聚类中心
    public double[][] FCMCenter(double R[][],double X[][],int N,int M,int C,double Q)
    {
        double V[][]=new double[C][M];
        int i,j,k;
        double n_sum,m_sum;
        for(i=0;i<C;i++)
            for(j=0;j<M;j++)
            {
                n_sum=0;
                m_sum=0;
                for(k=0;k<N;k++)
                {
                    n_sum+=Math.pow(R[i][k],Q)*X[k][j];
                    m_sum+=Math.pow(R[i][k],Q);
                }
                V[i][j]=n_sum/m_sum;
            }//计算c个聚类中心ci，i=1,…,c
        return V;
    }
    //修正模糊分类矩阵
    public double[][] modifyR(double X[][],double V[][],int N,int M,int C,int D,double q)
    {
        double kj_sum;
        double R1[][]=new double[C][N];
        int i,k,j;
        for(i=0;i<C;i++)
            for(k=0;k<N;k++)
            {
                kj_sum=0;
                for(j=0;j<C;j++)
                {
                    if(j!=i)
                        kj_sum+=Math.pow((compute_dis(X,k,V,i,M,D)/compute_dis(X,k,V,j,M,D)),(2/(q-1)));
                }
                R1[i][k]=Math.pow((kj_sum+1),-1);
            }
        return R1;
    }
    public void display2(double[][] vv, double[][] resultRank) {
        for (int i = 0; i<vv.length; i++)
        {
            for (int j = 0; j<vv[0].length; j++)
            {
                //Matrix[i][j]=Math.round(Matrix[i][j]);
                System.out.printf("%8.7f,%1.0f",vv[i][j],resultRank[i][j]+1);
            }
            System.out.println ("");
        }
    }
    //输出矩阵
    public void display(double Matrix[][])
    {
        for (int i = 0; i<Matrix.length; i++)
        {
            for (int j = 0; j<Matrix[0].length; j++)
            {
                //Matrix[i][j]=Math.round(Matrix[i][j]);
                System.out.printf("%8.7f",Matrix[i][j]);
                System.out.print("  ");
            }
            System.out.println ("");
        }
    }
    //转换为确定性分类
    public double[][] displayClass(double x[][],double R1[][],int N,int C,double rank[][],int M,List<EndResult> readfile,HashMap<String,String>hasemap) {
//        File file = new File("./iris/"+readfile+"预测.txt");
//        FileWriter out = null;
        String country=null;
        String ra=null;
        String[][] line = new String[N][M];
        Iterator iter = hasemap.entrySet().iterator();
        int i,j;
        int max;
        double CR[][]=new double[C][N];
        for(j=0;j<N;j++){
            for(i=0;i<C;i++){
                CR[i][j]=0;
            }
        }
        for(j=0;j<N;j++)
        {
            max=0;
            for(i=0;i<C;i++)
            {
                if(R1[i][j]>R1[max][j])
                {
                    max=i;
                }
            }
            CR[max][j]=1;
        }
        double CQ[] =new double[N];
        for(i=0;i<N;i++){
            for(j=0;j<C;j++){
                if(CR[j][i]==1){
                    CQ[i]=rank[j][0];
                }
            }
        }

//            out = new FileWriter(file);
        for(i=0;i<N;i++) {
            if(C==1) {
                ra="风险中";
                CQ[i]=CQ[i]+1;
                for(int u=0;u<M;u++) {
                    CQ[i]=CQ[i]+1;
                    for (String key : hasemap.keySet()) {
                        if(Double.parseDouble((String)(key))==x[i][0]){
                            country=hasemap.get(key);

                        }
                    }
                    System.out.println("总数"+x[i][0]+"  ,  "+"等级"+CQ[i]+"  ,  "+"国家"+country+"  ,  "+ra+"\n");
                }

            }
            else {
                for (int u = 0; u < M; u++) {
                    for (String key : hasemap.keySet()) {
                        if(Double.parseDouble((String)(key))==x[i][0]){
                            country=hasemap.get(key);

                        }
                    }
                    CQ[i]=CQ[i]+1;
                    if(CQ[i]==1)
                        ra="风险低";
                    if(CQ[i]==2)
                        ra="风险中";
                    if(CQ[i]==3)
                        ra="风险高";
                    for (EndResult endResult:readfile){
                        if(endResult.getDateTime().equalsIgnoreCase(country)){
                            endResult.setRisk(ra);
                        }
                    }
//                    System.out.println("总数"+x[i][0]+"  ,  "+"等级"+CQ[i]+"  ,  "+"国家"+country+"  ,  "+ra+"\n");
                    country=null;
                }
            }
        }
//            out.close();

        System.out.println();
        return CR;
    }
    //计算分类系数和平均模糊熵
    public double[] ClassFactor(double R1[][],int N,int C)
    {
        int i,j;
        double ClassF[]={0,0};
        for(i=0;i<C;i++)
            for(j=0;j<N;j++)
            {
                ClassF[0]+=Math.pow(R1[i][j],2);
                ClassF[1]+=R1[i][j]*Math.log(R1[i][j]);//e为低
            }
//        System.out.println(N);
        ClassF[0]=ClassF[0]/N;   //分类系数
        ClassF[1]=-ClassF[1]/N;  //平均模糊熵
        return ClassF;
    }

    public double[][] beginRo(int C,int N) {
        int i,j,k;
        double R0[][] = new double[C][N];
        for (i = 0; i < N; i++) {
            double sum = 0;
            for (j = 0; j < C; j++) {
                if (j == C - 1)
                    R0[j][i] = 1 - sum;
                else {
                    R0[j][i] = (1 - sum) * Math.random();
                    sum = sum + R0[j][i];
                }
            }
        }
        return R0;
    }


    public int Kind(String [] kind) {
        int c;
        for(int j=0;j<kind.length;j++)
            if(kind[j]!=null) {
                c = Integer.parseInt(kind[j]);
                return c;
            }
        return 0;
    }

}
