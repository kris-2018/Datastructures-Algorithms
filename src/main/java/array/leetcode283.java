package array;

public class leetcode283 {
    public static void main(String[] args) {
        leetcode283 leetcode283 = new leetcode283();
        int[] numbers = new int[5];
        int[] numbs = {0,1,0,3,12};

        for (int i : leetcode283.moveZeroes(numbs)) {
            System.out.print(i);
        }
    }
    public int[] moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0;i < nums.length; i++){
            if (nums[i] != 0){
                if (i != j){
                    nums[j] = nums[i];
                    nums[i] = 0;
                }
                j++;
            }
        }
        return nums;
    }



}
