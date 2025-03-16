import java.util.ArrayList;
import java.util.List;

public class CurrencyConversion {
    static List<Double> ls=new ArrayList<>();
    static List<Double> ress=new ArrayList<>();
    static int min=(int)1e9;
    static boolean vis[]=new boolean[6];
    public static void main(String args[]) {

        /**
         0->EUR
         1->GBP
         2->USD
         3->MXN
         4->CAD
         5->AED
         **/
        double curr[][]={
                {1,0,1.5},
                {2,1,1.1},
                {3,1,100},
                {5,4,0.85},
                {2,3,10}
        };


        ArrayList<ArrayList<Currency>> adj=getAdj(curr,6);
        converter(2,0,adj);

        if(ress.size()==0)System.out.println("-1");
        else{
            double mult=1.0;
            for(double d:ress)mult*=d;
            System.out.println(mult*1000+ress.size());
        }
    }
    public static void converter(int source,int dest,ArrayList<ArrayList<Currency>> adj){

        if(source==dest){

            if(ls.size()<min){
                min=ls.size();
                List<Double> temp=new ArrayList<>();
                temp.addAll(ls);
                ress=temp;
                for(double d:temp)System.out.println(d);
            }
            return;
        }
        vis[source]=true;

        for(Currency c:adj.get(source)){
            if(!vis[c.curr]){

                ls.add(c.mult);
                converter(c.curr,dest,adj);
                ls.remove(ls.size()-1);
            }
        }
        vis[source]=false;

    }
    public static ArrayList<ArrayList<Currency>> getAdj(double curr[][],int n){

        ArrayList<ArrayList<Currency>> adj=new ArrayList<>();

        for(int i=0;i<n;i++)adj.add(new ArrayList<Currency>());

        int m=curr.length;
        for(int i=0;i<m;i++){

            int currI=(int)curr[i][0];
            int currF=(int)curr[i][1];
            double mult = curr[i][2];
            adj.get(currI).add(new Currency(currF,mult));
            adj.get(currF).add(new Currency(currI,mult));

        }
        return adj;
    }
}
class Currency{

    int curr;
    double mult;
    Currency(int c,double m){

        this.curr=c;
        this.mult=m;
    }
}

