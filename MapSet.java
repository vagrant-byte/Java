import java.util.*;

public class MapSet {
    //只出现一次的数字
    public int singleNumber(int[] nums) {
        HashSet<Integer> set=new HashSet<>();
        for (int i = 0; i <nums.length ; i++) {
            if(set.contains(nums[i])) {
                set.remove(nums[i]);
            } else {
                set.add(nums[i]);
            }
        }
        for (int i = 0; i <nums.length ; i++) {
            if(set.contains(nums[i])) {
                return nums[i];
            }
        }
        return -1;
    }
    //复制带随机指针的链表  重要
   /* public Node copyRandomList(Node head) {
        if(head==null) {
            return null;
        }
        Node cur=head;
        HashMap<Node,Node> map=new HashMap<>();
        while (cur!=null) {
            Node val=new Node(cur.val);
            map.put(cur,val);
            cur=cur.next;
        }
        cur=head;
        while (cur!=null) {
            map.get(cur).next=map.get(cur.next);
            map.get(cur).random=map.get(cur.random);
            cur=cur.next;
        }
        return map.get(head);

    }*/
//旧键盘
    public static void main2(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s1=scanner.nextLine();
        String s2=scanner.nextLine();
        HashSet<Character> set=new HashSet<>();
        for (char ch:s2.toUpperCase().toCharArray()) {
            set.add(ch);
        }
        HashSet<Character> set1=new HashSet<>();
        for (char ch:s1.toUpperCase().toCharArray()) {
            if(!set.contains(ch)&&!set1.contains(ch)) {
                set1.add(ch);
                System.out.println(ch);
            }
        }

    }
    //宝石与石头
    public int numJewelsInStones(String jewels, String stones) {
        HashSet<Character> hashSet=new HashSet<>();

        for (int i = 0; i <jewels.length() ; i++) {
            hashSet.add(jewels.charAt(i));
        }
        int count=0;
        for (int i = 0; i <stones.length() ; i++) {
            if(hashSet.contains(stones.charAt(i))) {
                count++;
            }
        }
        return count;
    }
    //找到第一个重复的数据
    public static void main1(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10_0000; i++) {
            list.add(random.nextInt(10000));
        }
        HashSet<Integer> set=new HashSet<>();
        for (int i = 0; i <list.size() ; i++) {
            if(set.contains(list.get(i))) {
                System.out.println(list.get(i));
                break;
            } else {
                set.add(list.get(i));
            }
        }
    }
    //数据重复出现的次数
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10_0000; i++) {
            list.add(random.nextInt(10000));
        }
        HashMap<Integer,Integer> map=new HashMap<>();
        for (Integer key:list) {
            if(map.get(key)==null) {
                map.put(key,1);
            } else {
                int count=map.get(key);
                map.put(key,count+1);
            }
        }
        for (Map.Entry<Integer,Integer> entry:map.entrySet()) {
            if(entry.getValue()>1) {
                System.out.println("重复出现的数字"+entry.getKey()+"重复次数"+entry.getValue());
            }
        }
    }

}
