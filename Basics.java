

public class Basics{
    public static class Node{
        int data;
        Node next;

        public Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    public static Node head;
    public static Node tail;
    public static int size;

    public void addFirst(int data)
    {
        //step1 = create new Node
        Node newNode = new Node(data);
        size++;
        if(head == null)
        {
            head = tail = newNode;
            return;
        }
        //step2 = newNode next = head
        newNode.next = head; //link

        //step3 = head = newNode
        head = newNode;
    }

    public void addLast(int data)
    {
        Node newNode = new Node(data);
        size++;
        if(head == null)
        {
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    //Add in the middle
    public void add(int idx, int data)
    {
        if(idx == 0)
        {
            addFirst(data);
            return;
        }
        Node newNode = new Node(data);
        size++;
        Node temp = head;
        int i =0;

        while(i < idx-1)
        {
            temp = temp.next;
            i++;
        }
        //i = idx-1; temp -> prev
        newNode.next = temp.next;
        temp.next = newNode;
    }

    public void print(){
        if(head == null)
        {
            System.out.println("LL is empty");
            return;
        }
        Node temp = head;
        while(temp != null)
        {
            System.out.print(temp.data+"->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    //remove First
    public int removeFirst(){
        if(size == 0)
        {
            System.out.println("LL is empty");
            return Integer.MIN_VALUE;
        }else if(size == 1)
        {
            int val = head.data;
            head = tail = null;
            size=0;
            return val;
        }
        int val = head.data;
        head = head.next;
        size--;
        return val;
    }

    //remove Last
    public int removeLast(){
        if(size == 0)
        {
            System.out.println("LL is empty");
            return Integer.MIN_VALUE;
        }else if(size == 1)
        {
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }
        //prev: i = size-2
        Node prev = head;
        for(int i=0; i<size-2; i++)
        {
            prev = prev.next;
        }
        int val = prev.next.data; //tail.data
        prev.next = null;
        tail = prev;
        size--;
        return val;

    }

    //Search iterater
    public int itrSearch(int key) //0(n)
    {
        Node temp = head;
        int i =0;

        while(temp != null)
        {
            if(temp.data == key) //key found
            {
                return i;
            }
            temp = temp.next;
            i++;
        }

        //key not found
        return -1;
    }

    //Recursive Search
    public int helper(Node head, int key)
    {
        if(head == null)
        {
            return -1;
        }
        if(head.data == key)
        {
            return 0;
        }
        int idx = helper(head.next, key);
        if(idx == -1){
            return -1;
        }
        return idx+1;
    }

    public int recSearch(int key){
        return helper(head, key);
    }

    //Reverse a Linked List
    public void reverse(){ //o(n)
        Node prev = null;
        Node curr = tail = head;
        Node next;

        while(curr != null)
        {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }


    //Remove Nth node from end
    public void deleteNthfromEnd(int n){
        //calculate size
        int sz = 0;
        Node temp = head;
        while(temp != null)
        {
            temp = temp.next;
            sz++;
        }
        if(n == sz)
        {
            head = head.next;  //removeFirst
            return;
        }

        //sz-n
        int i=1;
        int iToFind = sz-n;
        Node prev = head;
        while(i < iToFind)
        {
            prev = prev.next;
            i++;
        }
        prev.next = prev.next.next;
        return;
    }


    // Find middle node(slow fast approch )
    public Node findMid(Node head)
    {
        Node slow =head;
        Node fast = head;

        while(fast != null && fast.next != null)
        {
            slow = slow.next; //+1
            fast = fast.next.next; //+2
        }
        return slow; //slow is my mid Node
    }

    public boolean checkPalindrome() {
        if (head == null || head.next == null) {
            return true;
        }
        //step-1 find mid
        Node midNode = findMid(head);

        //step-2 reverse 2nd half
        Node prev = null;
        Node curr = midNode;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Node right = prev; //right half head
        Node left = head;

        //step3 check left half & right half
        while (right != null) {
            if (left.data != right.data) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }
    

    // Detect a loop/cycle in a LL
    public static boolean isCycle() {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    private Node getMid(Node head) {
        Node slow = head;
        Node fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private Node merge(Node head1, Node head2) {
        Node mergeLL = new Node(-1);
        Node temp = mergeLL;

        while (head1 != null && head2 != null) {
            if (head1.data <= head2.data) {
                temp.next = head1;
                head1 = head1.next;
                temp = temp.next;
            } else {
                temp.next = head2;
                head2 = head2.next;
                temp = temp.next;
            }
        }

        while (head1 != null) {
            temp.next = head1;
            head1 = head1.next;
            temp = temp.next;
        }

        while (head2 != null) {
            temp.next = head2;
            head2 = head2.next;
            temp = temp.next;
        }

        return mergeLL.next;

    }

    public Node mergeSort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        // find mid
        Node mid = getMid(head);

        // left & right MS
        Node rightHead = mid.next;
        mid.next = null;
        Node newLeft = mergeSort(head);
        Node newRight = mergeSort(rightHead);

        // merge
        return merge(newLeft, newRight);
    }

    //Remove Cycle
    public static void removeCycle() {
        Node slow = head;
        Node fast = head;
        Node prev = null;
        boolean cycle = false;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                cycle = true;
                break;
            }
        }
        if (cycle == false) {
            return;
        }

        //find meeting point
        slow = head;
        while (slow != fast) {
            prev = fast;
            slow = slow.next;
            fast = fast.next;
        }

        //remove cycle - last.next = null
        prev.next = null;

    }
    

    public void zigZag() {
        //find mid
        Node slow = head;
        Node fast = head.next;
        while(fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node mid = slow;

        //reverse 2nd half
        Node curr = mid.next;
        mid.next = null;
        Node prev = null;
        Node next;

        while(curr != null)
        {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Node left = head;
        Node right = prev;
        Node nextL, nextR;

        //alt merge - zig-zag merge
        while (left != null && right != null) {
            nextL = left.next;
            left.next = right;
            nextR = right.next;
            right.next = nextL;

            left = nextL;
            right = nextR;
        }

     }
    public static void main(String args[]){
        // Basics ll = new Basics();
        // ll.addFirst(1);
        // ll.addFirst(2);
        // ll.addLast(3);
        // ll.addLast(1);
        //ll.add(2,9);
       // ll.print();
        // System.out.println(ll.size);

        // ll.removeFirst();
        // ll.print();
        // System.out.println(ll.size);

        // ll.removeLast();
        // ll.print();
        // System.out.println(ll.size);

       // System.out.println(ll.recSearch(9));
       // System.out.println(ll.recSearch(10));

    //    ll.reverse();
    //    ll.print();
        // ll.deleteNthfromEnd(3);
        // ll.print();

        // System.out.println(ll.checkPalindrome());
       
        // head = new Node(1);
        // Node temp = new Node(2);
        // head.next = temp;
        // head.next.next = new Node(3);
        // head.next.next.next = temp;
        // // head.next.next.next = head;
        // // 1->2->3->2
        // System.out.println(isCycle());
        // removeCycle();
        // System.out.println(isCycle());

        Basics ll = new Basics();
        ll.addFirst(5);
        ll.addFirst(4);
        ll.addFirst(3);
        ll.addFirst(2);
        ll.addFirst(1);

        ll.print();
        //ll.head = ll.mergeSort(ll.head);
        ll.zigZag();
        ll.print();

    }
}