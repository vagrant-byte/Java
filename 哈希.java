import java.util.HashMap;
import java.util.Random;

public class 哈希 {
    //设计一种结构，实现一下三个功能
    //1.insert 将某个key加入到该结构，做到不能重复
    //2.delete  将原来在结构中的某个key移除
    //3.getRandom 等概率随机返回结构中的任何一个key
    public class Pool<K> {
        private HashMap<K,Integer> keyIndexMap;
        private HashMap<Integer,K> indexKeyMap;
        private int size;

        public Pool() {
            this.indexKeyMap=new HashMap<>();
            this.keyIndexMap=new HashMap<>();
            this.size=0;
        }

        public void insert(K key) {
            if(!this.keyIndexMap.containsKey(key)) {
                this.keyIndexMap.put(key,this.size);
                this.indexKeyMap.put(this.size++,key);
            }
        }

        public void delete(K key) {
            if(this.indexKeyMap.containsKey(key)) {
                int deleteIndex=this.keyIndexMap.get(key);//获取要删除key所对应的数值
                int lastIndex=--this.size;
                K lastKey=this.indexKeyMap.get(lastIndex);//获取最后位置所对应的key
                this.keyIndexMap.put(lastKey,deleteIndex);
                this.indexKeyMap.put(deleteIndex,lastKey);
                this.keyIndexMap.remove(key);
                this.indexKeyMap.remove(lastIndex);
            }
        }

        public K random() {
            if(this.size==0) {
                return null;
            }
            int randomIndex= (int) (Math.random()*this.size);
            return this.indexKeyMap.get(randomIndex);
        }
    }

}
