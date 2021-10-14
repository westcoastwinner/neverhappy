import java.util.Arrays;

public class KMP {
    public static void main(String[] args) {
    String str1="BBC ABCDAB ABCDABCDABDE";
    String str2="ABCDABD";
    int[] next=kmpNext("ABCDABD" );
    System.out.println(Arrays.toString(next));
//        System.out.println(kmpSearch(str1,str2,next));
    }

    //暴力匹配算法
    public static int violenceMatch(String str1,String str2){
        char[] a=str1.toCharArray();
        char[] b=str2.toCharArray();
        int s1len=a.length;
        int s2len=b.length;
        int i=0;
        int j=0;
        while(i<s1len&&j<s2len){
            if(a[i]==b[j]){
                i++;
                j++;
            }else{
                i=i-j+1;
                j=0;
            }
        }
        if(j==s2len){
            return i-j;
        }else{
            return -1;
        }
    }

/*
* @param str1 源字符串
* @param str2 子串
* @param next 部分匹配表
* @return 如果未找到则返回-1，否则返回第一次出现的位置
* */
public static int kmpSearch(String str1,String str2,int[] next){
for(int i=0,j=0;i<str1.length();i++){

    while(j>0&&str1.charAt(i)!=str2.charAt(j)){
        j=next[j-1];
    }
    if (str1.charAt(i) == str2.charAt(j)) {
        j++;
    }
    if(j==str2.length()){
        return i-j+1;
    }

}
return -1;
}
    //获取一个字符串的部分匹配值表
    public static int[] kmpNext(String dest){
        //
        int[] next=new int[dest.length()];
        next[0]=0;

        for(int i=1,j=0;i<dest.length();i++){
            while(j>0&&dest.charAt(i)!=dest.charAt(j)){
               j=next[j-1];


//                System.out.println(j);
            }
            if(dest.charAt(i)==dest.charAt(j)){
                j++;
//                System.out.println("zheli " +j);
            }
            next[i]=j;
        }
        return next;
    }
}
