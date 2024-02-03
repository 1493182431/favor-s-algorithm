import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author：Favor
 * @date: 2024/2/3
 */
public class tmp {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Map<Node, Node> map = new HashMap<>();
        Node ans = new Node(head.val);
        Node node = new Node(head.val);
        Node head2 = head;
        Node node2 = node;
        ans.next = node;
        while (head != null) {
            node.val = head.val;
            map.put(head, node);
            head = head.next;
            if (head != null) {
                node.next = new Node(0);
                node = node.next;
            }
        }
        node.next = null;
        while (head2 != null) {
            if (head2.random != null) {
                node2.random = map.get(head2.random);
            } else node2.random = null;
            head2 = head2.next;
            node2 = node2.next;
        }
        return ans.next;
    }
}
