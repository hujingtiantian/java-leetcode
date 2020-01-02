package github.leetcode.最小时间差;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hujingtian on 2019/7/15.
 * 给定一个 24 小时制（小时:分钟）的时间列表，找出列表中任意两个时间的最小时间差并已分钟数表示。
 */
public class Solution {


    public static void main(String[] args){
        Solution solution = new Solution();
        solution.findMinDifference(new ArrayList<String>(){{
            add("23:59");
            add("00:00");
            add("00:00");
        }});
    }

    public int findMinDifference(List<String> timePoints) {
        List<Integer> timeList = new ArrayList<>(timePoints.size());
        timePoints.forEach(time ->{
            timeList.add(changeStrTimeTONumber(time));
        });
        Collections.sort(timeList);
        List<Integer> subList = new ArrayList<>(timePoints.size());
        for(int i = 0 ; i < timePoints.size()-1;i++){
            subList.add(timeList.get(i+1) - timeList.get(i));
        }
        subList.add(24*60 - timeList.get(timePoints.size()-1) + timeList.get(0));
        Collections.sort(subList);
        return subList.get(0);
    }

    private Integer changeStrTimeTONumber(String time) {
        String[] times = time.split(":");
        String hour = times[0];
        String min = times[1];
        if(hour.startsWith("0")){
            hour = hour.substring(1);
        }
        if(min.startsWith("0")){
            min = min.substring(1);
        }
        return 60*(Integer.valueOf(hour))+Integer.valueOf(min);
    }
}
