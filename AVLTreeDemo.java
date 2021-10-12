public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr={4,3,6,5,7,8};
        int[] arr={10,12,8,9,7,6};

        AVLTree avlTree=new AVLTree();
        for(int i=0;i< arr.length;i++){
            avlTree.add(new Node(arr[i]));
        }

        avlTree.infixOrder();
        System.out.println(avlTree.getRoot().height());
        System.out.println(avlTree.getRoot().leftHeight());
        System.out.println(avlTree.getRoot().rightHeight());
    }
}
class AVLTree{
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void add(Node node){
        if(root==null){
            root=node;
        }else{
            root.add(node);
        }
    }

    public void infixOrder(){
        if(root==null){
            throw new RuntimeException("the root is empty");
        }else{
            root.infixOrder();
        }

    }

    public int delRightTreeMin(Node node){
        Node target =node;
        while(target.left!=null){
            target=target.left;
        }
        delNode(target.value);
        return target.value;
    }

    public Node search(int value){
        if(root==null){
            return null;
        }else{
            return root.search(value);
        }
    }

    public Node searchParent(int value){
        if(root==null){
            return null;
        }else{
            return root.searchParent(value);
        }
    }

    public void delNode(int value){
        if(root==null){
            return;
        }else{
            Node targetNode=search(value);
            if(targetNode==null){
                return ;
            }
            if(root.left==null&&root.right==null){
                root=null;
                return;
            }
            Node parent=searchParent(value);
            if(targetNode.left==null&&targetNode.right==null){
                if(parent.left!=null&&parent.left.value==value){
                    parent.left=null;
                }else{
                    parent.right=null;
                }
            }else if(targetNode.left!=null&&targetNode.right!=null){
                int minVal=delRightTreeMin(targetNode.right);
                targetNode.value=minVal;
            }else{
                if(targetNode.left!=null){

                    if(parent.left.value==value){
                        if(parent!=null){
                            parent.left=targetNode.left;
                        }else{
                            root=targetNode.left;
                        }
                    }
                    else{if(parent!=null) {
                        parent.right = targetNode.left;
                    }else {
                        parent.left=targetNode.right;
                    }
                    }

                }
                else{
                    if(parent.left.value==value){
                        parent.left=targetNode.right;
                    }else{
                        parent.right=targetNode.right;

                    }
                }
            }
        }
    }

}
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;

    }

    public void leftRotate() {
        Node newNode = new Node(value);
        newNode.left = this.left;
        newNode.right = this.right.left;
        this.value = this.right.value;
        this.right = this.right.right;
        this.left = newNode;

    }

    public void rightRotate() {
        Node newNode = new Node(value);
        newNode.left = this.left.right;
        newNode.right = this.right;
        this.value = this.left.value;
        this.right = newNode;
        this.left = this.left.left;
    }

    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    public void add(Node node) {
        if (node == null) {
            return;
        }

        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }

        if (rightHeight() - leftHeight() > 1) {
            if (right != null && right.leftHeight() - right.rightHeight() > 1) {
                right.rightRotate();
                leftRotate();
            } else {
                leftRotate();
            }
            return;
        }
        if (leftHeight() - rightHeight() > 1) {
            if (left != null && left.rightHeight() - left.leftHeight() > 1) {
                left.leftRotate();
                rightRotate();
            } else {
                rightRotate();
            }

        }
    }

        public void infixOrder () {
            if (this.left != null) {
                this.left.infixOrder();
            }
            System.out.println(this);
            if (this.right != null) {
                this.right.infixOrder();
            }
        }

        @Override
        public String toString () {
            return "Node [value= " + this.value + "]";
        }

        public Node search ( int value){
            if (value == this.value) {
                return this;
            } else if (value < this.value) {
                if (this.left == null) {
                    return null;
                }
                return this.left.search(value);
            } else {
                if (this.right == null) {
                    return null;
                }
                return this.right.search(value);
            }

        }

        public Node searchParent ( int value){
            if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
                return this;
            } else {
                if (value < this.value && this.left != null) {
                    return this.left.searchParent(value);
                } else if (value >= this.value && this.right != null) {
                    return this.right.searchParent(value);
                } else {
                    return null;
                }
            }

        }
    }


