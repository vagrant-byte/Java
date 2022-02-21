package dao;

import common.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProblemDao {
    //新增题目
    public void insert(Problem problem) {
        Connection connection=null;
        PreparedStatement statement=null;
        try {
            connection=DBUtil.getConnection();
            String sql="insert into oj_table values(null,?,?,?,?,?)";
            statement=connection.prepareStatement(sql);
            statement.setString(1,problem.getTitle());
            statement.setString(2,problem.getLevel());
            statement.setString(3,problem.getDescription());
            statement.setString(4,problem.getTemplateCode());
            statement.setString(5,problem.getTestCode());
            int ret=statement.executeUpdate();
            if(ret!=1) {
                System.out.println("新增题目失败");
            } else {
                System.out.println("新增题目成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
    }
    //删除题目
    public void delete(int id) {
        Connection connection=null;
        PreparedStatement statement=null;
        try {
            connection=DBUtil.getConnection();
            String sql="delete from oj_table where id=?";
            statement=connection.prepareStatement(sql);
            statement.setInt(1,id);
            int ret=statement.executeUpdate();
            if(ret!=1) {
                System.out.println("删除题目失败");
            } else {
                System.out.println("删除题目成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
    }
    //查询所有题目
    public List<Problem> selectAll() {
        List<Problem> list=new ArrayList<>();
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            connection=DBUtil.getConnection();
            String sql="select * from oj_table";
            statement=connection.prepareStatement(sql);
            resultSet=statement.executeQuery();
            while (resultSet.next()) {
                Problem problem=new Problem();
                problem.setId(resultSet.getInt("id"));
                problem.setTitle(resultSet.getString("title"));
                problem.setLevel(resultSet.getString("level"));
                list.add(problem);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }
    //查询指定题目详情
    public Problem selectOne(int id) {
        Problem problem=new Problem();
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            connection=DBUtil.getConnection();
            String sql="select * from oj_table where id=?";
            statement=connection.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet=statement.executeQuery();
            if(resultSet.next()) {
                problem.setId(resultSet.getInt("id"));
                problem.setTitle(resultSet.getString("title"));
                problem.setLevel(resultSet.getString("level"));
                problem.setDescription(resultSet.getString("description"));
                problem.setTemplateCode(resultSet.getString("templateCode"));
                return problem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }

    private static void testInsert() {
        ProblemDao problemDAO=new ProblemDao();
        Problem problem=new Problem();
        problem.setTitle("两数之和");
        problem.setLevel("简单");
        problem.setDescription("给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target的那两个整数，并返回它们的数组下标。\n" +
                "\n" +
                "你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。\n" +
                "\n" +
                "你可以按任意顺序返回答案。\n" +
                "\n" +
                "\n" +
                "\n" +
                "示例 1：\n" +
                "\n" +
                "输入：nums = [2,7,11,15], target = 9\n" +
                "输出：[0,1]\n" +
                "解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。\n" +
                "示例 2：\n" +
                "\n" +
                "输入：nums = [3,2,4], target = 6\n" +
                "输出：[1,2]\n" +
                "示例 3：\n" +
                "\n" +
                "输入：nums = [3,3], target = 6\n" +
                "输出：[0,1]\n" +
                "\n" +
                "\n" +
                "提示：\n" +
                "\n" +
                "2 <= nums.length <= 104\n" +
                "-109 <= nums[i] <= 109\n" +
                "-109 <= target <= 109\n" +
                "只会存在一个有效答案\n" +
                "\n" );
        problem.setTemplateCode("class Solution {\n" +
                "    public int[] twoSum(int[] nums, int target) {\n" +
                "\n" +
                "    }\n" +
                "}");
        problem.setTestCode("public static void main(String[] args) {\n" +
                "        Solution solution=new Solution();\n" +
                "        //testcase1\n" +
                "        int[] nums={2,7,11,15};\n" +
                "        int target=9;\n" +
                "        int[] result=solution.twoSum(nums,target);\n" +
                "        if(result.length==2 &&result[0]==0&&result[1]==1) {\n" +
                "            System.out.println(\"testcase1 Ok\");\n" +
                "        } else {\n" +
                "            System.out.println(\"testcase1 failed\");\n" +
                "        }\n" +
                "        //testcase2\n" +
                "        int[] nums2={3,2,4};\n" +
                "        int target2=6;\n" +
                "        int[] result2=solution.twoSum(nums2,target2);\n" +
                "        if(result.length==2 &&result2[0]==1&&result2[1]==2) {\n" +
                "            System.out.println(\"testcase2 Ok\");\n" +
                "        } else {\n" +
                "            System.out.println(\"testcase2 failed\");\n" +
                "        }\n" +
                "    }");
        problemDAO.insert(problem);
    }
    public static void testDelete() {
        ProblemDao problemDao=new ProblemDao();
        problemDao.delete(5);
    }

    public static void testSelectAll() {
        ProblemDao problemDao=new ProblemDao();
        List<Problem> problems=problemDao.selectAll();
        System.out.println(problems);
    }
    public static void testSelectOne() {
        ProblemDao problemDao=new ProblemDao();
        Problem problem=problemDao.selectOne(7);
        System.out.println(problem);
    }

    public static void main(String[] args) {
        //testInsert();
        //testDelete();
        //testSelectAll();
        testSelectOne();
    }

}
