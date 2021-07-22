import java.sql.Array;
import java.util.Arrays;

public class testMain {
    //构造一个数组，存放数据
    public int[] elem;
    public int usedSize;
    public testMain() {
        this.elem=new int[10];
    }
    //构造小堆
    public void createHelp(int[] array) {
        //将给定的数组存放在elem中
        for (int i = 0; i <array.length ; i++) {
            this.elem[i]=array[i];
            //用来记录存放了多少个元素
            this.usedSize++;
        }
        //parent代表根节点的下标   从最下边的一个树开始进行向下调整
        for(int parent=(array.length-1-1)/2;parent>=0;parent--) {
            adjustDown(parent,this.usedSize);
        }
    }
    public void adjustDown(int root,int len) {
        int parent=root;
        //child代表右子树的小标
        int child=parent*2+1;
        while (child<len) {
            //判断是否有左子树  如果右子树的值小于左子树的值 child++
            if(child+1<len&&elem[child]<elem[child+1]) {
                child++;
            }
            //交换
            if(elem[child]<elem[parent]) {
                int tmp=elem[child];
                elem[child]=elem[parent];
                elem[parent]=tmp;
                parent=child;
                child=parent*2+1;
            } else {
                break;
            }
        }
    }
    //向上调整  插入一个元素 将其放入最下边然后进行向上调整  传入子树的小标
    public void adjustUp(int child) {
        int parent=(child-1)/2;
        while (parent>=0) {
            if(elem[child]<elem[parent]) {
                int tmp=elem[child];
                elem[child]=elem[parent];
                elem[parent]=tmp;
                child=parent;
                parent=(child-1)/2;
            } else {
                break;
            }
        }
    }
    //插入
    public void push(int val) {
        //扩容
        if(isFull()) {
            this.elem= Arrays.copyOf(this.elem,this.elem.length*2);
        }
        elem[this.usedSize]=val;
        this.usedSize++;
        adjustUp(this.usedSize-1);

    }
    //判读数组是否满
    public boolean isFull() {
        return this.usedSize == this.elem.length;
    }
    //删除首元素
    public void pop() {
        if(isEmpty()) {
            return;
        }
        //将0下标元素与最后一个小标元素交换，进行向下调整
        int tmp=this.elem[0];
        this.elem[0]=this.elem[usedSize-1];
        this.elem[usedSize-1]=tmp;
        this.usedSize--;//删除
        adjustDown(0,this.usedSize-1);
    }
    public boolean isEmpty() {
        return this.usedSize == 0;
    }
    //弹出首元素
    public int peek() {
        if(isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return this.elem[0];
    }
    public void helpSort() {
        int end=this.usedSize-1;
        while (end>0) {
            int tmp=this.elem[0];
            this.elem[0]=this.elem[end];
            this.elem[end]=tmp;
            adjustDown(0,end);
            end--;
        }
    }
}
