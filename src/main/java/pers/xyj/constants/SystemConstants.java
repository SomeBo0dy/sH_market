package pers.xyj.constants;

public class SystemConstants
{
    //商品尚未审核通过
    public static final String GOOD_STATUS_NOT_APPROVED = "1";
    //商品审核通过
    public static final String GOOD_STATUS_APPROVED = "0";
    //商品未出售
    public static final int GOOD_NOT_SOLD = 0;
    //商品已出售
    public static final int GOOD_SOLD = 1;
    //被删除
    public static final int GOOD_DEF = 1;

    public static final String STATUS_NORMAL = "0";
    //是普通用户
    public static final String IS_USER = "0";
    //是管理员
    public static final String IS_ADMIN = "1";
    //发送拒绝邮件
    public static final String GOOD_REJECTED_MESSAGE = "  很抱歉，您提交的商品不符合平台要求";
    //发送通过邮件
    public static final String GOOD_APPROVED_MESSAGE = "  您提交的商品已通过平台审核";
    public static final int ORDER_DONE = 1;
    public static final int ORDER_UNDO = 0;
}