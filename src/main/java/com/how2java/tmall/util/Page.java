package com.how2java.tmall.util;

public class Page {
    private int start;//开始位置

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    private int count;//每页显示的数量
    private int total;//总共有多少条数据
    private String param;//参数

    private static final int defaultCount=5;//每页默认显示5条
     public Page(){
         count =defaultCount;
     }
    public Page(int start,int count){
         this();
         this.start=start;
         this.count=count;
    }

    public int getTotalPage(){
         int totalPage;
         if(total%count==0)
             totalPage=total/count;
         else
             totalPage=total/count+1;
         if(totalPage==0)
             totalPage=1;
         return totalPage;
    }

    public int getLast(){
         int last;
         if(total%count==0)
             last=total-count;
         else last=total-total%count;
        last = last<0?0:last;
        return last;
    }
     public boolean isHasPreviouse(){
         if(start==0)return false;
         else return true;
     }
     public boolean isHasNext(){
         if(start==getLast())return false;
         else return true;
     }
     @Override
    public String toString(){
         return "Page [start=" + start + ", count=" + count + ", total=" + total + ", getStart()=" + getStart()
                 + ", getCount()=" + getCount() + ", isHasPreviouse()=" + isHasPreviouse() + ", isHasNext()="
                 + isHasNext() + ", getTotalPage()=" + getTotalPage() + ", getLast()=" + getLast() + "]";
     }
}
