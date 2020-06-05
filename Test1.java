游戏里面有很多各式各样的任务，其中有一种任务玩家只能做一次，这类任务一共有1024个，任务ID范围[1,1024]。请用32个unsigned int类型来
记录着1024个任务是否已经完成。初始状态都是未完成。 输入两个参数，都是任务ID，需要设置第一个ID的任务为已经完成；并检查第二个ID的
任务是否已经完成。 输出一个参数，如果第二个ID的任务已经完成输出1，如果未完成输出0。如果第一或第二个ID不在[1,1024]范围，则输出-1。
import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner  sc=new Scanner(System.in);
        int[] arr=new int[32];
        while(sc.hasNext()){
            int a=sc.nextInt();
            int b=sc.nextInt();
            if(a>=1&&b>=1&&a<=1024&&b<=1024){
                int index1=(a-1)/32;
                int set=(a-1)%32;
                arr[index1]|=(1<<set);
                
                int index2=(b-1)/32;
                int get=(b-1)%32;
                if((arr[index2]&(1<<get))==0){
                    System.out.println("0");
                }else{
                    System.out.println("1");
                }
            }else{
                System.out.println("-1");
            }
            
        }
    }
}


小Q十分富有，拥有非常多的硬币，小Q拥有的硬币是有规律的，对于所有的非负整数K，小Q恰好各有两个面值为2^K的硬币，所以小Q拥有的硬币就
是1,1,2,2,4,4,8,8,…。小Q有一天去商店购买东西需要支付n元钱，小Q想知道有多少种方案从他拥有的硬币中选取一些拼凑起来恰好是n元
（如果两种方案某个面值的硬币选取的个数不一样就考虑为不一样的方案）。
import java.util.*;
public class Main{
    private static Map<Long,Long> map=new HashMap<>();
    private static long dfs(long n){
        if(map.containsKey(n)) return map.get(n);
        if(n==0) return 1;
        long res=0;
        if((n&1)==1) res=dfs(n>>1);
        else res=dfs(n>>1)+dfs((n>>1)-1);
        map.put(n,res);
        return res;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            long n=sc.nextLong();
            System.out.println(dfs(n));
        }
    }
}


小Q从牛博士那里获得了一个数字转换机，这台数字转换机必须同时输入两个正数a和b，并且这台数字转换机有一个红色的按钮和一个蓝色的按钮：

当按下了红色按钮，两个数字同时加1。

当按下了蓝色按钮，两个数字同时乘2。

小Q现在手中有四个整数a，b，A，B，他希望将输入的两个整数a和b变成A，B（a对应A，b对应B）。因为牛博士允许小Q使用数字转换机的时间有限，所以小Q希望按动按钮的次数越少越好。请你帮帮小Q吧。
import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int a=sc.nextInt();
            int b=sc.nextInt();
            int A=sc.nextInt();
            int B=sc.nextInt();
            Queue<int[]> queue=new LinkedList<>();
            queue.offer(new int[]{a,b});
            int res=0;
            boolean isFind=false;
            while(!queue.isEmpty()){
                int size=queue.size();
                while(size--!=0){
                    int[] tmp=queue.poll();
                    int x=tmp[0];
                    int y=tmp[1];
                    if(x==A&&y==B){
                        isFind=true;
                        break;
                    }
                    if(x+1<=A&&y+1<=B) queue.offer(new int[]{x+1,y+1});
                    if(x*2<=A&&y*2<=B) queue.offer(new int[]{x*2,y*2});
                }
                if(isFind) break;
                res++;
            }
            
            if(isFind) System.out.println(res);
            else System.out.println("-1");
        }
    }
}

geohash编码：geohash常用于将二维的经纬度转换为字符串，分为两步：第一步是经纬度的二进制编码，第二步是base32转码。
此题考察纬度的二进制编码：算法对纬度[-90, 90]通过二分法进行无限逼近（取决于所需精度，本题精度为6）。注意，本题进行二分法逼近过程中只采用向下取整来进行二分，针对二分中间值属于右区间。算法举例如下： 针对纬度为80进行二进制编码过程：
1) 区间[-90, 90]进行二分为[-90, 0),[0, 90]，成为左右区间，可以确定80为右区间，标记为1；
2) 针对上一步的右区间[0, 90]进行二分为[0, 45),[45, 90]，可以确定80是右区间，标记为1；
3) 针对[45, 90]进行二分为[45, 67),[67,90],可以确定80为右区间，标记为1；
4) 针对[67,90]进行二分为[67, 78),[78,90]，可以确定80为右区间，标记为1；
5) 针对[78, 90]进行二分为[78, 84),[84, 90]，可以确定80为左区间，标记为0；
6) 针对[78, 84)进行二分为[78, 81), [81, 84)，可以确定80为左区间，标记为0；
import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            int left=-90;
            int right=90;
            int len=0;
            StringBuilder sb=new StringBuilder();
            while(len<6&&left<right){
                int mid=(right+left)/2;
                if(n>=mid){
                    left=mid;
                    sb.append("1");
                    len++;
                }else{
                    right=mid;
                    sb.append("0");
                    len++;
                }
            }
            while(len<6){
                sb.append("0");
                len++;
            }
            System.out.println(sb);
        }
    }
}


给定一个正整数，编写程序计算有多少对质数的和等于输入的这个正整数，并输出结果。输入值小于1000。
如，输入为10, 程序应该输出结果为2。（共有两对质数的和为10,分别为(5,5),(3,7)）
import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            boolean[] isPrim=new boolean[n+1];
            Arrays.fill(isPrim,true);
            Set<Integer> set=new HashSet<>();
            for(int i=2;i<=n;i++){
                if(isPrim[i]){
                    set.add(i);
                    for(int j=i*i;j<=n;j+=i){
                        isPrim[j]=false;
                    }
                }
            }
            
            int res=0;
            for(int i:set){
                if(set.contains(n-i)) res++;
            }
            System.out.println((res+1)/2);
        }
    }
}

一个数组有 N 个元素，求连续子数组的最大和。 例如：[-1,2,1]，和最大的连续子数组为[2,1]，其和为 3
import java.util.Scanner;
public class Main{
    public static void main(String[] srga){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            int[] arr=new int[n];
            for(int i=0;i<n;i++){
                arr[i]=sc.nextInt();
            }
            int max=arr[0];
            int sum=0;
            for(int i=0;i<n;i++){
                if(sum<0){
                    sum=arr[i];
                }else{
                    sum+=arr[i];
                }
                max=Math.max(max,sum);
            }
            System.out.println(max);
        }
    }
}

某餐馆有n张桌子，每张桌子有一个参数：a 可容纳的最大人数； 有m批客人，每批客人有两个参数:b人数，c预计消费金额。
 在不允许拼桌的情况下，请实现一个算法选择其中一部分客人，使得总预计消费金额最大
 import java.util.*;
public class Main{
    private static int binary(int[] arr,int target){
        int left=0;
        int right=arr.length-1;
        while(left<right){
            int mid=left+(right-left)/2;
            if(arr[mid]<target){
                left=mid+1;
            }else{
                right=mid;
            }
        }
        return left;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            int m=sc.nextInt();
            int[] table=new int[n];
            for(int i=0;i<n;i++){
                table[i]=sc.nextInt();
            }
            int[][] customer=new int[m][2];
            for(int i=0;i<m;i++){
                customer[i][0]=sc.nextInt();
                customer[i][1]=sc.nextInt();
            }
            
            Arrays.sort(table);
            Arrays.sort(customer,(o1,o2)->o2[1]-o1[1]);
            
            boolean[] isVisited=new boolean[n];
            long res=0;
            for(int i=0;i<m;i++){
                if(customer[i][0]>table[n-1]) continue;
                int index=binary(table,customer[i][0]);
                while(index<n&&isVisited[index]){
                    index++;
                }
                if(index!=n){
                    res+=customer[i][1];
                    isVisited[index]=true;
                }
            }
            System.out.println(res);
        }
    }
}




