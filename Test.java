小Q在学习许多排序算法之后灵机一动决定自己发明一种排序算法，小Q希望能将n个不同的数排序为升序。小Q发明的排序算法在每轮允许两种操作：

1、 将当前序列中前n-1个数排为升序

2、 将当前序列中后n-1个数排为升序

小Q可以任意次使用上述两种操作，小Q现在想考考你最少需要几次上述操作可以让序列变为升序。
import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            int[] arr=new int[n];
            for(int i=0;i<n;i++){
                arr[i]=sc.nextInt();
            }
            boolean isSorted=true;
            int min=0;
            int max=0;
            for(int i=1;i<n;i++){
                min=arr[min]<arr[i]?min:i;
                max=arr[max]<arr[i]?i:max;
                if(arr[i]<arr[i-1]) isSorted=false;
            }
            
            int res=0;
            if(min==0&&max==n-1){
                if(isSorted) res=0;
                else res=1;
            }else if(min==n-1&&max==0){
                res=3;
            }else if(min!=0&&max!=n-1){
                res=2;
            }else{
                if(min==0||max==n-1){
                    res=1;
                }else{
                    res=2;
                }
            }
            System.out.println(res);
        }
    }
}

小Q和牛博士在玩一个石子合并的游戏，初始一共有n堆石子，每堆石子有w[i]个石子。小Q和牛博士他们需要对石子堆进行合并，每次他们可以任意选择两堆石子进行合并。一堆有x个石子的石子堆和一堆有y个石子的石子堆合并将得到一堆x+y个石子的石子堆，这次合并得分为x*y，当只剩下一堆石子的时候游戏结束。

、小Q和牛博士希望采取优秀的策略获得最大得分，希望你能来帮他们算算最大得分多少
import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            int[] arr=new int[n];
            for(int i=0;i<n;i++){
                arr[i]=sc.nextInt();
            }
            
            int res=0;
            int cur=arr[0];
            for(int i=1;i<n;i++){
                res+=cur*arr[i];
                cur+=arr[i];
            }
            System.out.println(res);
        }
    }
}


小Q搜寻了整个魔法世界找到了四块魔法石所在地，当4块魔法石正好能构成一个正方形的时候将启动魔法阵，小Q就可以借此实现一个愿望。

现在给出四块魔法石所在的坐标，小Q想知道他是否能启动魔法阵
import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            while(n--!=0){
                String[] x=sc.next().split("");
                String[] y=sc.next().split("");
                int[][] arr=new int[4][2];
                for(int i=0;i<4;i++){
                    arr[i][0]=Integer.parseInt(x[i]);
                }
                for(int i=0;i<4;i++){
                    arr[i][1]=Integer.parseInt(y[i]);
                }
                Arrays.sort(arr,(a,b)->{
                    return a[0]==b[0]?a[1]-b[1]:a[0]-b[0];
                });
                if(arr[0][0]==arr[1][0]&&arr[0][1]==arr[2][1]&&arr[3][0]==arr[2][0]
                  &&arr[3][1]==arr[1][1]&&arr[1][1]-arr[0][1]==arr[2][0]-arr[0][0]){
                    System.out.println("Yes");
                }else{
                    System.out.println("No");
                }
            }
        }
    }
}

在一组数的编码中，若任意两个相邻的代码只有一位二进制数不同， 则称这种编码为格雷码(Gray Code)，请编写一个函数，使用递归的方法生成N位的格雷码。

给定一个整数n，请返回n位的格雷码，顺序为从0开始。
import java.util.*;

public class GrayCode {
    public String[] getGray(int n) {
        // write code here
        if(n==1) return new String[]{"0","1"};
        String[] pre=getGray(n-1);
        String[] arr=new String[(int)Math.pow(2,n)];
        for(int i=0;i<pre.length;i++){
            arr[i]="0"+pre[i];
            arr[arr.length-1-i]="1"+pre[i];
        }
        return arr;
    }
}

假定一种编码的编码范围是a ~ y的25个字母，从1位到4位的编码，如果我们把该编码按字典序排序，形成一个数组如下： a, aa, aaa, 
aaaa, aaab, aaac, … …, b, ba, baa, baaa, baab, baac … …, yyyw, yyyx, yyyy 其中a的Index为0，aa的Index为1，aaa的Index为2，
以此类推。 编写一个函数，输入是任意一个编码，输出这个编码对应的Index.
import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String s=sc.next();
            int res=0;
            int len=s.length();
            for(int i=0;i<len;i++){
                int index=s.charAt(i)-'a';
                int tmp=0;
                for(int j=0;j<4-i;j++){
                    tmp+=(int)Math.pow(25,j);
                }
                res+=index*tmp;
                if(i!=0) res++;
            }
            System.out.println(res);
        }
    }
}



