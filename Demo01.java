import java.util.List;

class ListNode{
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}
//删除链表中等于给定值 **val** 的所有节点
public class Demo01 {
    public ListNode removeElements(ListNode head,int val){
        //1.链表为空的情况
        if(head == null){
            return null;
        }
        //2.处理非头结点
        ListNode prev = head;
        ListNode node = head.next;
        while(node != null){
            if(node.val == val){
                prev.next = node.next;
                node = prev.next;
            }else{
                prev = node;
                node = node.next;
            }
        }
        //3.处理头结点
        if(head.val == val){
            head = head.next;
        }
        return head;
    }
    //反转一个单链表
    public ListNode reverseList(ListNode head){
        //1.判定链表为空
        if(head == null){
            return null;
        }
        //2.只有一个元素
        if(head.next == null){
            return head;
        }
        //3.处理一般情况
        ListNode newHead = null;
        ListNode cur = head;
        ListNode prev = null;
        while(cur != null){
            ListNode next = cur.next;
            if(next == null){
                //已经指向最后一个节点了
                newHead = cur;
            }
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return newHead;
    }
    //给定一个带有头结点 head 的非空单链表，返回链表的中间结点。如果有两个中间结点，则返回第二个中间结点
    public ListNode middleNode(ListNode head){
        ListNode cur = head;
        int steps = size(head) / 2;
        for(int i = 0;i < steps;i++){
            cur = cur.next;
        }
        return cur;
    }
    //输入一个链表，输出该链表中倒数第k个结点。
    public ListNode FindKthToTail(ListNode head,int k){
        int len = size(head);
        if(head == null ||k <= 0 ||k > len){
            return null;
        }
        int offest = len - k;
        ListNode cur = head;
        for(int i = 0;i < offest;i++){
            cur = cur.next;
        }
        return cur;
    }
    //合并两个单链表
    public ListNode mergeTwoLists(ListNode l1,ListNode l2){
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        ListNode newList = new ListNode(0);
        ListNode tail = newList;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                tail.next = new ListNode(l1.val);
                l1 = l1.next;
                tail = tail.next;
            }else{
                tail.next = new ListNode(l2.val);
                l2 = l2.next;
                tail = tail.next;
            }
        }
        if(l1 != null){
            tail.next = l1;
        }else{
            tail.next = l2;
        }
        return newList.next;
    }

    //以给定值x为基准将链表分割成两部分，所有小于x的结点排在大于或等于x的结点之前
    public ListNode partition(ListNode pHead,int x){
        //1.判定链表是否为空
        if(pHead == null){
            return null;
        }
        //2.只有一个节点
        if(pHead.next == null){
            return pHead;
        }
        //3.正常情况时
        ListNode smallHead = new ListNode(-1);
        ListNode bigHead = new ListNode(-1);
        ListNode smallTail = smallHead;
        ListNode bigTail = bigHead;
        ListNode cur = pHead;
        while(cur != null){
            if(cur.val < x){
                smallTail.next = new ListNode(cur.val);
                smallTail = smallTail.next;
                cur = cur.next;
            }else{
                bigTail.next = new ListNode(cur.val);
                bigTail = bigTail.next;
                cur = cur.next;
            }
        }
        smallTail.next = bigTail.next;
        return smallHead.next;
    }
    //删除重复结点
    public ListNode deleteDuplication(ListNode pHead){
        //1.判定链表是否为空
        if(pHead == null){
            return null;
        }
        //2.只有一个结点
        if(pHead.next == null){
            return pHead;
        }
        //3.正常情况下
        ListNode newHead = new ListNode(0);
        newHead.next = pHead;
        ListNode prev = newHead;
        ListNode node = prev.next;
        while(node != null){
            if(node.next != null && node.val == node.next.val){
                while(node.next != null && node.val == node.next.val){
                    node = node.next;
                }
                prev.next = node.next;
                node = node.next;
            }else{
                prev = prev.next;
                node = node.next;
            }
        }
        return newHead.next;
    }
    //判定链表的回文结构
    public boolean chkPalindrome(ListNode A){
        ListNode B = null;
        if(A == null && A.next == null){
            return true;
        }
        ListNode cur = A;
        while(cur.next != null){
            B = addFirst(cur);
            cur = cur.next;
        }
        while(A != null){
            if(A.val != B.val){
                return false;
            }
            A = A.next;
            B = B.next;
        }
        return true;
    }
    public ListNode addFirst(ListNode head){
        ListNode node = new ListNode(head.val);
        if(head == null){
            head = node;
        }
        node.next = head;
        head = node;
        return head;
    }
    //输入两个链表，找出它们的第一个公共结点
    public ListNode getIntersectionNode(ListNode headA,ListNode headB){
        if(headA == null || headB == null){
            return null;
        }
        int lenA = size(headA);
        int lenB = size(headB);
        if(lenA > lenB){
            for(int i = 0;i < lenA - lenB;i++){
                headA = headA.next;
            }else if(lenB > lenA){
                for(int i = 0;i < lenB - lenA;i++){
                    headB = headB.next;
                }
            }
        }
        while(headB != null){
            if(headB == headA){
                return headB;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }
    public int size(ListNode head){
        int size = 0;
        for(ListNode cur = head;cur != null;cur = cur.next){
            size++;
        }
        return size;
    }
    //给定一个链表，判断链表中是否有环
    public boolean hasCycle(ListNode head){
        if(head == null){
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }
    //给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null
    public ListNode detectCycle(ListNode head){
        //从链表头部出发，到入口点的距离和从快慢指针的交汇处出发，到入口点的距离相等
        if(head == null){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                break;
            }
        }
        if(fast == null fast.next == null){
            //不带环
            return null;
        }
        //循环结束后，fast和slow就已经重合了
        ListNode cur1 = head;
        ListNode cur2 = fast;
        while(cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }
}
