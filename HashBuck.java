import java.util.Objects;

class Person {
    public String id;
    public Person(String id) {
        this.id=id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id.equals(person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
public class HashBuck<k,v> {
    static class Node<k,v> {
        public k key;
        public v val;
        public Node<k,v> next;
        public Node(k key,v val) {
            this.key=key;
            this.val=val;
        }
    }

    public Node<k,v>[] array=(Node<k,v>[]) new Node[10];
    public int usedSize;

    public void put(k key,v val) {
        int hash=key.hashCode();
        int index=hash%this.array.length;
        Node<k,v> cur=array[index];
        while (cur!=null) {
            if(cur.equals(key)) {
                cur.val=val;
            }
            cur=cur.next;
        }
        Node<k,v> node=new Node<>(key, val);
        node.next=array[index];
        array[index]=node;
        usedSize++;
        //扩容
        if(loadFactor()>=0.75) {
          resize();
        }
    }
    public double loadFactor() {
        return this.usedSize*1.0/this.array.length;
    }
    public void resize() {
        Node<k,v>[] newArray=(Node<k,v>[]) new Node[2*this.array.length];
        for (int i = 0; i <array.length ; i++) {
            Node<k,v> cur=array[i];
            Node<k,v> curNext=null;
            while (cur!=null) {
                curNext=cur.next;
                int index=cur.key.hashCode()%newArray.length;
                cur.next=newArray[index];
                newArray[index]=cur;
                cur=curNext;
            }
        }
        this.array=newArray;
    }
    public v get(k key) {
        int hash=key.hashCode();
        int index=hash%this.array.length;
        Node<k,v> cur=array[index];
        while (cur!=null) {
            if(cur.equals(key)) {
                return cur.val;
            }
            cur=cur.next;
        }
        return null;
    }
}
