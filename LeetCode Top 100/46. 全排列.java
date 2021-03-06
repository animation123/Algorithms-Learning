class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            // 注意此处的处理方法为new ArrayList，直接list.add(tempList)会报错
            list.add(new ArrayList<>(tempList));
        }
        for (int i = 0; i < nums.length; i++) {
            if (tempList.contains(nums[i])) continue; // temp中已经存在该元素，跳过本次循环
            tempList.add(nums[i]);
            backtrack(list, tempList, nums);
            tempList.remove(tempList.size() - 1);
        }
    }
}
