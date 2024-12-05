//341. Flatten Nested List Iterator - https://leetcode.com/problems/flatten-nested-list-iterator/
//Time Complexity: O(n) ~ linearly recurrsing using recurrsive approach

//Brute Force
public class NestedIterator implements Iterator<Integer> {
    List<Integer> list;
    int idx;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.list = new ArrayList<>();
        this.idx = 0;
        dfs(nestedList); //recusion approach to flatten the nested list
    }

    private void dfs(List<NestedInteger> nestedList){
        for(NestedInteger nest : nestedList){
            //if Integer, add to list
            if(nest.isInteger()){
                list.add(nest.getInteger());
            }else{
                //traverse through the nested loist recurrsively
                dfs(nest.getList());
            }
        }
    }

    //Time: O(1)
    @Override
    public Integer next() {
        return list.get(idx++);
    }

    //Time: O(1)
    @Override
    public boolean hasNext() {
        return idx < list.size();
    }
}

//Optimal Approach
//Time Complexity: O(1) ~ for each hasNext() and next()
                // O(depth of thenested list ~ if nested list is [[[[[[[[6]]]]]]]]]
//Space Complexity: O(depth of the list)

public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextElement;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        st.push(nestedList.iterator()); //push the iterator
    }

    @Override
    public Integer next() {
        return nextElement.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){    //[1,2] -> pointer moves out of the list scenario
                st.pop();
            } else {
                nextElement = st.peek().next(); //hasNext
                if(nextElement.isInteger()){
                    return true;
                } else{
                    st.push(nextElement.getList().iterator()); //get list of the nestedLists, push the iterator to stack
                }
            }
        }
        return false;
    }
}