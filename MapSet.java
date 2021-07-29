import java.util.*;

public class MapSet {
    //前k个高频单词
    public List<String> topKFrequent(String[] words, int k) {
        List<String> list=new ArrayList<>();
        //1.统计每次单词出现的频率
        HashMap<String,Integer> map=new HashMap<>();
        for (String s:words) {
            if(map.get(s)==null) {
                map.put(s,1);
            } else {
                int count=map.get(s);
                map.put(s,count+1);
            }
        }
        //2.创建一个小堆
        PriorityQueue<Map.Entry<String,Integer>> minHeap=new PriorityQueue<>(k, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o1.getValue()==o2.getValue()) {
                    return o2.getKey().compareTo(o1.getKey());
                }
                return o1.getValue()-o2.getValue();
            }
        });
        //遍历map 将前K个先放入小堆 然后从第K+1个开始 和堆顶元素比较
        for (Map.Entry<String,Integer> entry:map.entrySet()) {
            if(minHeap.size()<k) {
                minHeap.offer(entry);
            } else {
                Map.Entry<String,Integer> tmp=minHeap.peek();
                if(tmp!=null) {
                    //频率相同 判断字母
                    if(tmp.getValue()==entry.getValue()) {
                        if(tmp.getKey().compareTo(entry.getKey())>0) {
                            minHeap.poll();
                            minHeap.offer(entry);
                        }
                    } else {
                        if(tmp.getValue()<entry.getValue()) {
                            minHeap.poll();
                            minHeap.offer(entry);
                        }
                    }
                }
            }
        }
        for (int i = 0; i <k ; i++) {
            Map.Entry<String,Integer> top = minHeap.poll();
            if(top != null) {
                String key = top.getKey();
                list.add(key);
            }
        }
        Collections.reverse(list);
        return list;

    }
    //最常见的单词
    public String mostCommonWord(String paragraph, String[] banned) {
        String[] par=paragraph.split("[,.;!?]");
        HashMap<String,Integer> map=new HashMap<>();
        String ans=null;
        int max=0;
        for (int i=0;i<par.length;i++) {
            boolean flag=false;
            for (int j=0;j<banned.length;j++) {
                if(par[i].equals(banned[j])) {
                    flag=true;
                    break;
                }
            }
            if(!flag) {
                map.put(par[i],map.getOrDefault(par[i],0)+1);
                if(!par[i].equals("")&&map.get(par[i])>max) {
                    max=map.get(par[i]);
                    ans=par[i];
                }
            }
        }
        return ans;
    }
    //字符串中的第一个唯一字符
    public int firstUniqChar(String s) {
        if(s==null) {
            return -1;
        }
        HashMap<Character,Integer> map=new HashMap<>();
        for (char ch:s.toCharArray()) {
            if(map.get(ch)==null) {
                map.put(ch,1);
            } else {
                int count=map.get(ch);
                map.put(ch,count+1);
            }
        }
        for (int i = 0; i <s.length() ; i++) {
            if(map.get(s.charAt(i))==1) {
                return i;
            }
        }
        return -1;

    }
    //两个数的交集
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return null;
        }
        HashSet<Integer> set=new HashSet<>();
        for (int i = 0; i <nums1.length ; i++) {
            set.add(nums1[i]);
        }
        HashSet<Integer> set1=new HashSet<>();
        for (int i = 0; i <nums2.length ; i++) {
            if(set.contains(nums2[i])) {
                set1.add(nums2[i]);
            }
        }
        int[] array=new int[set1.size()];
        int count=0;
        for (int i:set1) {
            array[count]=i;
            count++;
        }
        return array;
    }
    //同构字符串
    public boolean isIsomorphic(String s, String t) {
        if(s.length()!=t.length()) {
            return false;
        }
        HashMap<Character,Character> map=new HashMap<>();
        for (int i = 0; i <s.length() ; i++) {
            if(!map.containsKey(s.charAt(i))) {
                if(map.containsValue(t.charAt(i))) {
                    return false;
                }
                map.put(s.charAt(i),t.charAt(i));
            } else {
                if(map.get(s.charAt(i))!=t.charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }
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
