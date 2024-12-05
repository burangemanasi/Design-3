//146. LRU Cache - https://leetcode.com/problems/lru-cache/
// Time Complexity: O(1) for all operations


class LRUCache {
    HashMap<Integer, Node> map;
    Node head, tail;
    int capacity;

    class Node{
        int key, value;
        Node prev, next;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        head.next=tail;
        tail.prev=head;
    }

    private void removeNode(Node curr){
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
        curr.next = null;
        curr.prev = null;
    }

    private void addToHead(Node curr){
        curr.prev = head;
        curr.next = head.next;
        head.next.prev = curr;
        head.next = curr;
    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            addToHead(node);
        } else{
            if(map.size() == capacity){
                Node toRremove = tail.prev;
                removeNode(toRremove);
                map.remove(toRremove.key);
            }
            Node newNode = new Node(key,value);
            map.put(key, newNode);
            addToHead(newNode);
        }
    }
}