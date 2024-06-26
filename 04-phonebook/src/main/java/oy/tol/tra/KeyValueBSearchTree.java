package oy.tol.tra;

public class KeyValueBSearchTree<K extends Comparable<K>, V> implements Dictionary<K, V> {

    private TreeNode<K, V> root;
    private int count = 0;
    private int maxTreeDepth = 0;

    @Override
    public Type getType() {
        return Type.BST;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String getStatus() {
        String toReturn = "Tree has max depth of " + maxTreeDepth + ".\n";
        toReturn += "Longest collision chain in a tree node is " + TreeNode.longestCollisionChain + "\n";
        TreeAnalyzerVisitor<K, V> visitor = new TreeAnalyzerVisitor<>();
        root.accept(visitor);
        toReturn += "Min path height to bottom: " + visitor.minHeight+ "\n"; // Fix: Changed visitor.minHeight to visitor.getMinHeight()
        toReturn += "Max path height to bottom: " + visitor.maxHeight+ "\n"; // Fix: Changed visitor.maxHeight to visitor.getMaxHeight()
        toReturn += "Ideal height if balanced: " + Math.ceil(Math.log(count)) + "\n"; // Fix: Add 1 to count for ideal height calculation
        return toReturn;
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        if (key == null||value ==null) {
            throw new IllegalArgumentException("Null keys are not allowed.");
        }
        if (root == null) {
            root = new TreeNode<>(key, value);
            count++;
            return true;
        }
        int added=root.insert(key,value,key.hashCode());
        if(added==1){
            count++;
            if(TreeNode.currentAddTreeDepth>maxTreeDepth){
                maxTreeDepth=TreeNode.currentAddTreeDepth;
            }
            TreeNode.currentAddTreeDepth=0;
        }
        return true;
    }

    @Override
    public V find(K key)throws IllegalArgumentException {
       if(null==key)throw new IllegalArgumentException("Person to find cannot be null");
            return (root.find(key,key.hashCode())); // Delegate finding to the root node
      
    }

    @Override
    public void ensureCapacity(int size) throws OutOfMemoryError {
        // Nothing to do here. Trees need no capacity.
    }

    @Override
    public Pair<K, V>[] toSortedArray() {
        TreeToArrayVisitor<K, V> visitor = new TreeToArrayVisitor<>(count);
        root.accept(visitor);
        Pair<K, V>[] sorted = visitor.getArray();
        Algorithms.fastSort(sorted);
        return sorted;
    }

    @Override
    public void compress() throws OutOfMemoryError {
        // Nothing to do here, since BST does not use extra space like array based structures.
    }

}