import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Arrays;

class HashMapDemo {
    public void printHashMap(Map<Integer, String> map) {
        for (Map.Entry<Integer, String> kv : map.entrySet()) {
            System.out.println(kv.getKey() + ":" + kv.getValue());
        }
    }

    public void printKey(Map<Integer, String> map) {
        for (int key : map.keySet()) {
            System.out.println(key);
        }
    }

    public void preintValue(Map<Integer, String> map) {
        for (String value : map.values()) {
            System.out.println(value);
        }
    }
}

class Pair {
    public int key;
    public String value;

    public Pair(int key, String value) {
        this.key = key;
        this.value = value;
    }
}

class HashMapDemoList {
    public class ArrayHashMap {
        private List<Pair> bucket;

        public ArrayHashMap() {
            bucket = new ArrayList<Pair>();
            for (int i = 0; i < 100; i++) {
                bucket.add(null);
            }
        }

        private int hash(int key) {
            int index = key % 100;
            return index;
        }

        public String get(int key) {
            int index = hash(key);
            Pair pair = bucket.get(index);
            if (pair == null) {
                return null;
            }
            return pair.value;
        }

        public void put(int key, String value) {
            int index = hash(key);
            Pair pair = new Pair(key, value);
            bucket.set(index, pair);
        }

        public void remove(int key) {
            int index = hash(key);
            bucket.set(index, null);
        }

        public List<Pair> pairSet() {
            List<Pair> pairSet = new ArrayList<Pair>();
            for (Pair pair : bucket) {
                if (pair != null) {
                    pairSet.add(pair);
                }
            }
            return pairSet;
        }

        public List<Integer> keySet() {
            List<Integer> keySet = new ArrayList<Integer>();
            for (Pair pair : bucket) {
                if (pair != null) {
                    keySet.add(pair.key);
                }
            }
            return keySet;
        }

        public List<String> valueSet() {
            List<String> valueSet = new ArrayList<String>();
            for (Pair pair : bucket) {
                if (pair != null) {
                    valueSet.add(pair.value);
                }
            }
            return valueSet;
        }

        public void print() {
            for (Pair pair : bucket) {
                if (pair != null) {
                    System.out.println(pair.key + ":" + pair.value);
                }
            }
        }

    }
}

class HashMapChaining {
    int size;
    int capacity;
    double loadThreshold;
    int extendRatio;
    List<List<Pair>> bucket;

    public HashMapChaining() {
        size = 0;
        capacity = 4;
        loadThreshold = 2 / 3;
        extendRatio = 2;
        bucket = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            bucket.add(new ArrayList<>());
        }
    }

    private int hash(int key) {
        return key % capacity;
    }

    private double loadThreshold() {
        return (double) size / capacity;
    }

    public String get(int key) {
        int index = hash(key);
        List<Pair> pairs = bucket.get(index);
        for (Pair pair : pairs) {
            if (pair.key == key) {
                return pair.value;
            }
        }
        return null;
    }

    public void put(int key, String value) {
        if (loadThreshold() > loadThreshold) {
            extend();
        }
        int index = hash(key);
        List<Pair> pairs = bucket.get(index);
        for (Pair pair : pairs) {
            if (pair.key == key) {
                pair.value = value;
                return;
            }
        }
        pairs.add(new Pair(key, value));
        size++;
    }

    public void remove(int key) {
        int index = hash(key);
        List<Pair> pairs = bucket.get(index);
        for (Pair pair : pairs) {
            if (pair.key == key) {
                pairs.remove(pair);
                size--;
                return;
            }
        }
    }

    public void extend() {
        List<List<Pair>> oldBucket = bucket;
        capacity *= extendRatio;
        List<List<Pair>> bucket = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            bucket.add(new ArrayList<>());
        }
        size = 0;
        for (List<Pair> pairs : oldBucket) {
            for (Pair pair : pairs) {
                put(pair.key, pair.value);
            }
        }
    }

    public void print() {
        for (List<Pair> pairs : bucket) {
            for (Pair pair : pairs) {
                System.out.println(pair.key + ":" + pair.value);
            }
        }
    }

}

class HashMapOpenAddressing {
    private int size = 0;
    private int capacity = 4;
    private final double loadThreshold = 2 / 3;
    private final int extendRatio = 2;
    private Pair[] bucket;
    private final Pair DELETED = new Pair(-1, "-1");

    public HashMapOpenAddressing() {
        size = 0;
        bucket = new Pair[capacity];
    }
    
    private int hash(int key) {
        return key % capacity;
    }
    /* 
    private int addHash(int key){
        long hash = 0;
        final int MODULUS = 1000000007;
        for(char c:String.valueOf(key).toCharArray()){
            hash = (hash + (int)c) % MODULUS;
        }
        return (int)hash;
    }
    
    private int mulHash(int key){
        long hash = 0;
        final int MODULUS = 1000000007;
        for(char c:String.valueOf(key).toCharArray()){
            hash = (hash * 31 + (int)c) % MODULUS;
        }
        return (int)hash;
    }
    private int xorHash(int key){
        int hash = 0;
        final int MODULUS = 1000000007;
        for(char c:String.valueOf(key).toCharArray()){
            hash ^=(int) c;
        }
        return hash % MODULUS;
    }
    private int rotHash(int key){
        long hash = 0;
        final int MODULUS = 1000000007;
        for(char c:String.valueOf(key).toCharArray()){
            hash = (hash << 5) ^ (hash >> 27) ^ (int)c;
        }
        return (int)hash % MODULUS;
    }
*/
    private double loadThreshold() {
        return (double) size / capacity;
    }

    public int findBucket(int key) {
        int index = hash(key);
        int firstDELETED = -1;
        while (bucket[index] != null) {
            if (bucket[index].key == key) {
                if (firstDELETED != -1) {
                    bucket[firstDELETED] = bucket[index];
                    bucket[index] = DELETED;
                    return firstDELETED;
                }
                return index;
            }
            if (bucket[index] == DELETED && firstDELETED == -1) {
                firstDELETED = index;
            }
            index = (index + 1) % capacity;
        }
        return firstDELETED == -1 ? index : firstDELETED;
    }

    public String get(int key) {
        int index = findBucket(key);
        if (bucket[index] != null && bucket[index] != DELETED) {
            return bucket[index].value;
        }
        return null;
    }

    public void put(int key, String value) {
        if (loadThreshold() > loadThreshold) {
            extend();
        }
        int index = findBucket(key);
        if (bucket[index] == null || bucket[index] == DELETED) {
            bucket[index] = new Pair(key, value);
            size++;
        } else {
            bucket[index].value = value;
        }
    }

    public void remove(int key) {
        int index = findBucket(key);
        if (bucket[index] != null && bucket[index] != DELETED) {
            bucket[index] = DELETED;
            size--;
        }
    }

    public void extend() {
        Pair[] oldBucket = bucket;
        capacity *= extendRatio;
        bucket = new Pair[capacity];
        size = 0;
        for (Pair pair : oldBucket) {
            if (pair != null && pair != DELETED) {
                put(pair.key, pair.value);
            }
        }
    }

    public void print() {
        for (Pair pair : bucket) {
            if (pair == null){
                System.out.println("null");
            } else if (pair == DELETED) {
                System.out.println("DELETED");
            } else {
                System.out.println(pair.key + ":" + pair.value);
            }
            
        }
    }

}

public class HashTable {
    public boolean isAnagram(String s, String t) {
        int[] record = new int[26];
        for (char c : s.toCharArray()) {
            record[c - 'a']++;
        }
        for (char c: t.toCharArray()) {
            record[c - 'a']--;
        }
        for (int i : record) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null|| nums1.length == 0 || nums2.length == 0){
            return new int[0];
        }
        Set<Integer> set = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        for(int i:nums1){
            set.add(i);
        }
        for(int i:nums2){
            if(set.contains(i)){
                res.add(i);
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
    public int[] intersection2(int[] nums1, int[] nums2) {
        int[] hash1 = new int[1001];
        int[] hash2 = new int[1001];
        for(int i:nums1){
            hash1[i]++;
        }
        for(int i:nums2){
            hash2[i]++;
        }
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < 1001; i++){
            if(hash1[i]>0 && hash2[i]>0){
                res.add(i);
            }
        }
        int index;
        int[] result = new int[res.size()];
        for(index = 0; index < res.size(); index++){
            result[index] = res.get(index);
        }
        return result;
    }
    int getSum(int n){
        int sum = 0;
        while(n > 0){
            sum += (n % 10)*(n % 10);
            n /= 10;
        }
        return sum;
    }
    boolean isHappy(int n){
        Set<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)){
            set.add(n);
            n = getSum(n);
        }
        return n == 1;
    }

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if (nums == null || nums.length == 0) {
            return res;            
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int temp = target - nums[i];
            if(map.containsKey(temp)){
                res[0] = map.get(temp);
                res[1] = i;
                return res;
            }
            map.put(nums[i], i);
        }
        return res;
    }
    public int[] twoSum_2(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int temp = target - nums[i];
            if(map.containsKey(temp)){
                return new int[]{i,map.get(temp)};
            }
            map.put(nums[i],i);
        }
        return null;
    }
    public int[] twoSum_3(int[] nums, int target) {
        int[] res = new int[2];
        int[] temp = new int[nums.length];
        System.arraycopy(nums, 0, temp, 0, nums.length);
        Arrays.sort(temp);
        int n=0,m=0;
        for(int i=0,j=temp.length-1;i<j;){
            if(temp[i] + temp[j] == target){
                m = i;
                n = j;
                break;
            }else if(temp[i] + temp[j] < target){
                i++;
            }else{
                j--;
            }
        }
        for(int i=0;i<nums.length;i++){
            if(nums[i] == temp[m]){
                res[0] = i;
                break;
            }
        }
        for(int i=0;i<nums.length;i++){
            if(nums[i] == temp[n] && i != res[0]){
                res[1] = i;
                break;
            }
        }
        return res;
    }
    
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int a:A){
            for(int b:B){
                map.put(a+b,map.getOrDefault(a+b,0)+1);
            }
        }
        int count = 0;
        for(int c:C){
            for(int d:D){
                count+=map.getOrDefault(-c-d,0);
            }
        }
        return count;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] record = new int[26];
        if(ransomNote.length() > magazine.length()){
            return false;
        }
        for(char c:magazine.toCharArray()){
            record[c-'a']++;
        }
        for(char c:ransomNote.toCharArray()){
            if(record[c-'a'] == 0){
                return false;
            }
            record[c-'a']--;
        }
        return true;
    }

    public List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 3){
            return res;
        }
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++){
            if (nums[i] > 0) {
                break;                
            }
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            Set<Integer> set = new HashSet<>();
            for(int j = i+1; j < nums.length; j++){
                if(j > i+2 && nums[j] == nums[j-1] && nums[j-1] == nums[j-2]){
                    continue;
                }
                int c = -nums[i]-nums[j];
                if(set.contains(c)){
                    res.add(Arrays.asList(nums[i],nums[j],c));
                    set.remove(c);
                }
                set.add(nums[j]);
            }
            
        }
        return res;
    }
    public List<List<Integer>> threeSum_2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 3){
            return res;
        }
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0){
                break;
            }
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            int left = i+1;
            int right = nums.length-1;
            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum == 0){
                    res.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    while(left < right && nums[left] == nums[left+1]){
                        left++;
                    }
                    while(left < right && nums[right] == nums[right-1]){
                        right--;
                    }
                    left++;
                    right--;
                }else if(sum < 0){
                    left++;
                }else{
                    right--;
                }
            }
        }
        return res;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 4){
            return res;
        }
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++){
            if (nums[i] > target && nums[i] >= 0) {
                break;                                
            }
            if (nums[i] == nums[i-1] && i > 0) {
                continue;                                
            }
            for(int j = i+1; j < nums.length; j++){
                if(nums[i]+nums[j] > target && nums[i]+nums[j] >= 0){
                    break;
                }
                if (nums[j] == nums[j-1] && j > i+1) {
                    continue;                                
                }
                int left = j+1;
                int right = nums.length-1;
                while(left < right){
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if(sum == target){
                        res.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right]));
                        while(left < right && nums[left] == nums[left+1]){
                            left++;
                        }
                        while(left < right && nums[right] == nums[right-1]){
                            right--;
                        }
                        left++;
                        right--;
                    }else if(sum < target){
                        left++;
                    }else{
                        right--;
                    }
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {

    }

}
