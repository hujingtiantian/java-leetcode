package github.leetcode.april.findMedianSortedArrays;

/**
 * Created by hujingtian on 2019/4/16.
 */
public class FindMedianSortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums = new int[nums1.length+nums2.length];
        if(nums1.length == 0){
            nums = nums2;
            return midian(nums);
        }
        if(nums2.length == 0){
            nums = nums1;
            return midian(nums);
        }
        int i = 0,j=0;
        int sum = 0;
        while ( sum < nums.length-1){
            if(nums1[i] <= nums2[j]){
                nums[sum] = nums1[i];
                nums[sum+1] = nums2[j];
                sum++;
                if(i == nums1.length -1){
                    j++;
                    for (;j<nums2.length;j++){
                        nums[++sum]=nums2[j];
                    }
                }else {
                    i++;
                }
            }else {
                nums[ sum] = nums2[j];
                nums[ sum+1]= nums1[i];
                sum++;
                if(j == nums2.length -1){
                    i++;
                    for (;i<nums1.length;i++){
                        nums[++sum]=nums1[i];
                    }
                }else {
                    j++;
                }

            }
        }
        return midian(nums);
    }

    private static double midian(int[] nums) {
        if(nums.length % 2 == 1){
            return Double.valueOf(nums[nums.length/2]);
        }else {
            return Double.valueOf((nums[nums.length/2 -1] + nums[nums.length/2])) /2;
        }
    }

    public static void main(String[] args){
        System.out.print(findMedianSortedArrays(new int[]{1,3},new int[]{2,4}));
    }
}
